/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dao.exceptions.NonexistentEntityException;
import Dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Dto.*;
import java.util.ArrayList;
import java.util.Collection;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Alexander
 */
public class ProgramaJpaController implements Serializable {

    public ProgramaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Programa programa) throws PreexistingEntityException, Exception {
        if (programa.getEgresadoCollection() == null) {
            programa.setEgresadoCollection(new ArrayList<Egresado>());
        }
        if (programa.getUsuarioCollection() == null) {
            programa.setUsuarioCollection(new ArrayList<Usuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Egresado> attachedEgresadoCollection = new ArrayList<Egresado>();
            for (Egresado egresadoCollectionEgresadoToAttach : programa.getEgresadoCollection()) {
                egresadoCollectionEgresadoToAttach = em.getReference(egresadoCollectionEgresadoToAttach.getClass(), egresadoCollectionEgresadoToAttach.getId());
                attachedEgresadoCollection.add(egresadoCollectionEgresadoToAttach);
            }
            programa.setEgresadoCollection(attachedEgresadoCollection);
            Collection<Usuario> attachedUsuarioCollection = new ArrayList<Usuario>();
            for (Usuario usuarioCollectionUsuarioToAttach : programa.getUsuarioCollection()) {
                usuarioCollectionUsuarioToAttach = em.getReference(usuarioCollectionUsuarioToAttach.getClass(), usuarioCollectionUsuarioToAttach.getUsuario());
                attachedUsuarioCollection.add(usuarioCollectionUsuarioToAttach);
            }
            programa.setUsuarioCollection(attachedUsuarioCollection);
            em.persist(programa);
            for (Egresado egresadoCollectionEgresado : programa.getEgresadoCollection()) {
                Programa oldProgramaOfEgresadoCollectionEgresado = egresadoCollectionEgresado.getPrograma();
                egresadoCollectionEgresado.setPrograma(programa);
                egresadoCollectionEgresado = em.merge(egresadoCollectionEgresado);
                if (oldProgramaOfEgresadoCollectionEgresado != null) {
                    oldProgramaOfEgresadoCollectionEgresado.getEgresadoCollection().remove(egresadoCollectionEgresado);
                    oldProgramaOfEgresadoCollectionEgresado = em.merge(oldProgramaOfEgresadoCollectionEgresado);
                }
            }
            for (Usuario usuarioCollectionUsuario : programa.getUsuarioCollection()) {
                Programa oldProgramaOfUsuarioCollectionUsuario = usuarioCollectionUsuario.getPrograma();
                usuarioCollectionUsuario.setPrograma(programa);
                usuarioCollectionUsuario = em.merge(usuarioCollectionUsuario);
                if (oldProgramaOfUsuarioCollectionUsuario != null) {
                    oldProgramaOfUsuarioCollectionUsuario.getUsuarioCollection().remove(usuarioCollectionUsuario);
                    oldProgramaOfUsuarioCollectionUsuario = em.merge(oldProgramaOfUsuarioCollectionUsuario);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPrograma(programa.getCodigo()) != null) {
                throw new PreexistingEntityException("Programa " + programa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Programa programa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programa persistentPrograma = em.find(Programa.class, programa.getCodigo());
            Collection<Egresado> egresadoCollectionOld = persistentPrograma.getEgresadoCollection();
            Collection<Egresado> egresadoCollectionNew = programa.getEgresadoCollection();
            Collection<Usuario> usuarioCollectionOld = persistentPrograma.getUsuarioCollection();
            Collection<Usuario> usuarioCollectionNew = programa.getUsuarioCollection();
            Collection<Egresado> attachedEgresadoCollectionNew = new ArrayList<Egresado>();
            for (Egresado egresadoCollectionNewEgresadoToAttach : egresadoCollectionNew) {
                egresadoCollectionNewEgresadoToAttach = em.getReference(egresadoCollectionNewEgresadoToAttach.getClass(), egresadoCollectionNewEgresadoToAttach.getId());
                attachedEgresadoCollectionNew.add(egresadoCollectionNewEgresadoToAttach);
            }
            egresadoCollectionNew = attachedEgresadoCollectionNew;
            programa.setEgresadoCollection(egresadoCollectionNew);
            Collection<Usuario> attachedUsuarioCollectionNew = new ArrayList<Usuario>();
            for (Usuario usuarioCollectionNewUsuarioToAttach : usuarioCollectionNew) {
                usuarioCollectionNewUsuarioToAttach = em.getReference(usuarioCollectionNewUsuarioToAttach.getClass(), usuarioCollectionNewUsuarioToAttach.getUsuario());
                attachedUsuarioCollectionNew.add(usuarioCollectionNewUsuarioToAttach);
            }
            usuarioCollectionNew = attachedUsuarioCollectionNew;
            programa.setUsuarioCollection(usuarioCollectionNew);
            programa = em.merge(programa);
            for (Egresado egresadoCollectionOldEgresado : egresadoCollectionOld) {
                if (!egresadoCollectionNew.contains(egresadoCollectionOldEgresado)) {
                    egresadoCollectionOldEgresado.setPrograma(null);
                    egresadoCollectionOldEgresado = em.merge(egresadoCollectionOldEgresado);
                }
            }
            for (Egresado egresadoCollectionNewEgresado : egresadoCollectionNew) {
                if (!egresadoCollectionOld.contains(egresadoCollectionNewEgresado)) {
                    Programa oldProgramaOfEgresadoCollectionNewEgresado = egresadoCollectionNewEgresado.getPrograma();
                    egresadoCollectionNewEgresado.setPrograma(programa);
                    egresadoCollectionNewEgresado = em.merge(egresadoCollectionNewEgresado);
                    if (oldProgramaOfEgresadoCollectionNewEgresado != null && !oldProgramaOfEgresadoCollectionNewEgresado.equals(programa)) {
                        oldProgramaOfEgresadoCollectionNewEgresado.getEgresadoCollection().remove(egresadoCollectionNewEgresado);
                        oldProgramaOfEgresadoCollectionNewEgresado = em.merge(oldProgramaOfEgresadoCollectionNewEgresado);
                    }
                }
            }
            for (Usuario usuarioCollectionOldUsuario : usuarioCollectionOld) {
                if (!usuarioCollectionNew.contains(usuarioCollectionOldUsuario)) {
                    usuarioCollectionOldUsuario.setPrograma(null);
                    usuarioCollectionOldUsuario = em.merge(usuarioCollectionOldUsuario);
                }
            }
            for (Usuario usuarioCollectionNewUsuario : usuarioCollectionNew) {
                if (!usuarioCollectionOld.contains(usuarioCollectionNewUsuario)) {
                    Programa oldProgramaOfUsuarioCollectionNewUsuario = usuarioCollectionNewUsuario.getPrograma();
                    usuarioCollectionNewUsuario.setPrograma(programa);
                    usuarioCollectionNewUsuario = em.merge(usuarioCollectionNewUsuario);
                    if (oldProgramaOfUsuarioCollectionNewUsuario != null && !oldProgramaOfUsuarioCollectionNewUsuario.equals(programa)) {
                        oldProgramaOfUsuarioCollectionNewUsuario.getUsuarioCollection().remove(usuarioCollectionNewUsuario);
                        oldProgramaOfUsuarioCollectionNewUsuario = em.merge(oldProgramaOfUsuarioCollectionNewUsuario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = programa.getCodigo();
                if (findPrograma(id) == null) {
                    throw new NonexistentEntityException("The programa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programa programa;
            try {
                programa = em.getReference(Programa.class, id);
                programa.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programa with id " + id + " no longer exists.", enfe);
            }
            Collection<Egresado> egresadoCollection = programa.getEgresadoCollection();
            for (Egresado egresadoCollectionEgresado : egresadoCollection) {
                egresadoCollectionEgresado.setPrograma(null);
                egresadoCollectionEgresado = em.merge(egresadoCollectionEgresado);
            }
            Collection<Usuario> usuarioCollection = programa.getUsuarioCollection();
            for (Usuario usuarioCollectionUsuario : usuarioCollection) {
                usuarioCollectionUsuario.setPrograma(null);
                usuarioCollectionUsuario = em.merge(usuarioCollectionUsuario);
            }
            em.remove(programa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Programa> findProgramaEntities() {
        return findProgramaEntities(true, -1, -1);
    }

    public List<Programa> findProgramaEntities(int maxResults, int firstResult) {
        return findProgramaEntities(false, maxResults, firstResult);
    }

    private List<Programa> findProgramaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Programa.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Programa findPrograma(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Programa.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Programa> rt = cq.from(Programa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
