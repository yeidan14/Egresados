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
import Dto.Egresado;
import Dto.Programa;
import java.util.ArrayList;
import java.util.List;
import Dto.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ESTUDIANTE
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
        if (programa.getEgresadoList() == null) {
            programa.setEgresadoList(new ArrayList<Egresado>());
        }
        if (programa.getUsuarioList() == null) {
            programa.setUsuarioList(new ArrayList<Usuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Egresado> attachedEgresadoList = new ArrayList<Egresado>();
            for (Egresado egresadoListEgresadoToAttach : programa.getEgresadoList()) {
                egresadoListEgresadoToAttach = em.getReference(egresadoListEgresadoToAttach.getClass(), egresadoListEgresadoToAttach.getId());
                attachedEgresadoList.add(egresadoListEgresadoToAttach);
            }
            programa.setEgresadoList(attachedEgresadoList);
            List<Usuario> attachedUsuarioList = new ArrayList<Usuario>();
            for (Usuario usuarioListUsuarioToAttach : programa.getUsuarioList()) {
                usuarioListUsuarioToAttach = em.getReference(usuarioListUsuarioToAttach.getClass(), usuarioListUsuarioToAttach.getUsuario());
                attachedUsuarioList.add(usuarioListUsuarioToAttach);
            }
            programa.setUsuarioList(attachedUsuarioList);
            em.persist(programa);
            for (Egresado egresadoListEgresado : programa.getEgresadoList()) {
                Programa oldProgramaOfEgresadoListEgresado = egresadoListEgresado.getPrograma();
                egresadoListEgresado.setPrograma(programa);
                egresadoListEgresado = em.merge(egresadoListEgresado);
                if (oldProgramaOfEgresadoListEgresado != null) {
                    oldProgramaOfEgresadoListEgresado.getEgresadoList().remove(egresadoListEgresado);
                    oldProgramaOfEgresadoListEgresado = em.merge(oldProgramaOfEgresadoListEgresado);
                }
            }
            for (Usuario usuarioListUsuario : programa.getUsuarioList()) {
                Programa oldProgramaOfUsuarioListUsuario = usuarioListUsuario.getPrograma();
                usuarioListUsuario.setPrograma(programa);
                usuarioListUsuario = em.merge(usuarioListUsuario);
                if (oldProgramaOfUsuarioListUsuario != null) {
                    oldProgramaOfUsuarioListUsuario.getUsuarioList().remove(usuarioListUsuario);
                    oldProgramaOfUsuarioListUsuario = em.merge(oldProgramaOfUsuarioListUsuario);
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
            List<Egresado> egresadoListOld = persistentPrograma.getEgresadoList();
            List<Egresado> egresadoListNew = programa.getEgresadoList();
            List<Usuario> usuarioListOld = persistentPrograma.getUsuarioList();
            List<Usuario> usuarioListNew = programa.getUsuarioList();
            List<Egresado> attachedEgresadoListNew = new ArrayList<Egresado>();
            for (Egresado egresadoListNewEgresadoToAttach : egresadoListNew) {
                egresadoListNewEgresadoToAttach = em.getReference(egresadoListNewEgresadoToAttach.getClass(), egresadoListNewEgresadoToAttach.getId());
                attachedEgresadoListNew.add(egresadoListNewEgresadoToAttach);
            }
            egresadoListNew = attachedEgresadoListNew;
            programa.setEgresadoList(egresadoListNew);
            List<Usuario> attachedUsuarioListNew = new ArrayList<Usuario>();
            for (Usuario usuarioListNewUsuarioToAttach : usuarioListNew) {
                usuarioListNewUsuarioToAttach = em.getReference(usuarioListNewUsuarioToAttach.getClass(), usuarioListNewUsuarioToAttach.getUsuario());
                attachedUsuarioListNew.add(usuarioListNewUsuarioToAttach);
            }
            usuarioListNew = attachedUsuarioListNew;
            programa.setUsuarioList(usuarioListNew);
            programa = em.merge(programa);
            for (Egresado egresadoListOldEgresado : egresadoListOld) {
                if (!egresadoListNew.contains(egresadoListOldEgresado)) {
                    egresadoListOldEgresado.setPrograma(null);
                    egresadoListOldEgresado = em.merge(egresadoListOldEgresado);
                }
            }
            for (Egresado egresadoListNewEgresado : egresadoListNew) {
                if (!egresadoListOld.contains(egresadoListNewEgresado)) {
                    Programa oldProgramaOfEgresadoListNewEgresado = egresadoListNewEgresado.getPrograma();
                    egresadoListNewEgresado.setPrograma(programa);
                    egresadoListNewEgresado = em.merge(egresadoListNewEgresado);
                    if (oldProgramaOfEgresadoListNewEgresado != null && !oldProgramaOfEgresadoListNewEgresado.equals(programa)) {
                        oldProgramaOfEgresadoListNewEgresado.getEgresadoList().remove(egresadoListNewEgresado);
                        oldProgramaOfEgresadoListNewEgresado = em.merge(oldProgramaOfEgresadoListNewEgresado);
                    }
                }
            }
            for (Usuario usuarioListOldUsuario : usuarioListOld) {
                if (!usuarioListNew.contains(usuarioListOldUsuario)) {
                    usuarioListOldUsuario.setPrograma(null);
                    usuarioListOldUsuario = em.merge(usuarioListOldUsuario);
                }
            }
            for (Usuario usuarioListNewUsuario : usuarioListNew) {
                if (!usuarioListOld.contains(usuarioListNewUsuario)) {
                    Programa oldProgramaOfUsuarioListNewUsuario = usuarioListNewUsuario.getPrograma();
                    usuarioListNewUsuario.setPrograma(programa);
                    usuarioListNewUsuario = em.merge(usuarioListNewUsuario);
                    if (oldProgramaOfUsuarioListNewUsuario != null && !oldProgramaOfUsuarioListNewUsuario.equals(programa)) {
                        oldProgramaOfUsuarioListNewUsuario.getUsuarioList().remove(usuarioListNewUsuario);
                        oldProgramaOfUsuarioListNewUsuario = em.merge(oldProgramaOfUsuarioListNewUsuario);
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
            List<Egresado> egresadoList = programa.getEgresadoList();
            for (Egresado egresadoListEgresado : egresadoList) {
                egresadoListEgresado.setPrograma(null);
                egresadoListEgresado = em.merge(egresadoListEgresado);
            }
            List<Usuario> usuarioList = programa.getUsuarioList();
            for (Usuario usuarioListUsuario : usuarioList) {
                usuarioListUsuario.setPrograma(null);
                usuarioListUsuario = em.merge(usuarioListUsuario);
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
