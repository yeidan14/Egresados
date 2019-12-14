/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dao.exceptions.NonexistentEntityException; 

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.Collection;
import Dto.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Alexander
 */
public class EgresadoJpaController implements Serializable {

    public EgresadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Egresado egresado) {
        if (egresado.getEstudioCollection() == null) {
            egresado.setEstudioCollection(new ArrayList<Estudio>());
        }
        if (egresado.getExperienciaCollection() == null) {
            egresado.setExperienciaCollection(new ArrayList<Experiencia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programa programa = egresado.getPrograma();
            if (programa != null) {
                programa = em.getReference(programa.getClass(), programa.getCodigo());
                egresado.setPrograma(programa);
            }
            Collection<Estudio> attachedEstudioCollection = new ArrayList<Estudio>();
            for (Estudio estudioCollectionEstudioToAttach : egresado.getEstudioCollection()) {
                estudioCollectionEstudioToAttach = em.getReference(estudioCollectionEstudioToAttach.getClass(), estudioCollectionEstudioToAttach.getId());
                attachedEstudioCollection.add(estudioCollectionEstudioToAttach);
            }
            egresado.setEstudioCollection(attachedEstudioCollection);
            Collection<Experiencia> attachedExperienciaCollection = new ArrayList<Experiencia>();
            for (Experiencia experienciaCollectionExperienciaToAttach : egresado.getExperienciaCollection()) {
                experienciaCollectionExperienciaToAttach = em.getReference(experienciaCollectionExperienciaToAttach.getClass(), experienciaCollectionExperienciaToAttach.getId());
                attachedExperienciaCollection.add(experienciaCollectionExperienciaToAttach);
            }
            egresado.setExperienciaCollection(attachedExperienciaCollection);
            em.persist(egresado);
            if (programa != null) {
                programa.getEgresadoCollection().add(egresado);
                programa = em.merge(programa);
            }
            for (Estudio estudioCollectionEstudio : egresado.getEstudioCollection()) {
                Egresado oldEgresadoOfEstudioCollectionEstudio = estudioCollectionEstudio.getEgresado();
                estudioCollectionEstudio.setEgresado(egresado);
                estudioCollectionEstudio = em.merge(estudioCollectionEstudio);
                if (oldEgresadoOfEstudioCollectionEstudio != null) {
                    oldEgresadoOfEstudioCollectionEstudio.getEstudioCollection().remove(estudioCollectionEstudio);
                    oldEgresadoOfEstudioCollectionEstudio = em.merge(oldEgresadoOfEstudioCollectionEstudio);
                }
            }
            for (Experiencia experienciaCollectionExperiencia : egresado.getExperienciaCollection()) {
                Egresado oldEgresadoOfExperienciaCollectionExperiencia = experienciaCollectionExperiencia.getEgresado();
                experienciaCollectionExperiencia.setEgresado(egresado);
                experienciaCollectionExperiencia = em.merge(experienciaCollectionExperiencia);
                if (oldEgresadoOfExperienciaCollectionExperiencia != null) {
                    oldEgresadoOfExperienciaCollectionExperiencia.getExperienciaCollection().remove(experienciaCollectionExperiencia);
                    oldEgresadoOfExperienciaCollectionExperiencia = em.merge(oldEgresadoOfExperienciaCollectionExperiencia);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Egresado egresado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Egresado persistentEgresado = em.find(Egresado.class, egresado.getId());
            Programa programaOld = persistentEgresado.getPrograma();
            Programa programaNew = egresado.getPrograma();
            Collection<Estudio> estudioCollectionOld = persistentEgresado.getEstudioCollection();
            Collection<Estudio> estudioCollectionNew = egresado.getEstudioCollection();
            Collection<Experiencia> experienciaCollectionOld = persistentEgresado.getExperienciaCollection();
            Collection<Experiencia> experienciaCollectionNew = egresado.getExperienciaCollection();
            if (programaNew != null) {
                programaNew = em.getReference(programaNew.getClass(), programaNew.getCodigo());
                egresado.setPrograma(programaNew);
            }
            Collection<Estudio> attachedEstudioCollectionNew = new ArrayList<Estudio>();
            for (Estudio estudioCollectionNewEstudioToAttach : estudioCollectionNew) {
                estudioCollectionNewEstudioToAttach = em.getReference(estudioCollectionNewEstudioToAttach.getClass(), estudioCollectionNewEstudioToAttach.getId());
                attachedEstudioCollectionNew.add(estudioCollectionNewEstudioToAttach);
            }
            estudioCollectionNew = attachedEstudioCollectionNew;
            egresado.setEstudioCollection(estudioCollectionNew);
            Collection<Experiencia> attachedExperienciaCollectionNew = new ArrayList<Experiencia>();
            for (Experiencia experienciaCollectionNewExperienciaToAttach : experienciaCollectionNew) {
                experienciaCollectionNewExperienciaToAttach = em.getReference(experienciaCollectionNewExperienciaToAttach.getClass(), experienciaCollectionNewExperienciaToAttach.getId());
                attachedExperienciaCollectionNew.add(experienciaCollectionNewExperienciaToAttach);
            }
            experienciaCollectionNew = attachedExperienciaCollectionNew;
            egresado.setExperienciaCollection(experienciaCollectionNew);
            egresado = em.merge(egresado);
            if (programaOld != null && !programaOld.equals(programaNew)) {
                programaOld.getEgresadoCollection().remove(egresado);
                programaOld = em.merge(programaOld);
            }
            if (programaNew != null && !programaNew.equals(programaOld)) {
                programaNew.getEgresadoCollection().add(egresado);
                programaNew = em.merge(programaNew);
            }
            for (Estudio estudioCollectionOldEstudio : estudioCollectionOld) {
                if (!estudioCollectionNew.contains(estudioCollectionOldEstudio)) {
                    estudioCollectionOldEstudio.setEgresado(null);
                    estudioCollectionOldEstudio = em.merge(estudioCollectionOldEstudio);
                }
            }
            for (Estudio estudioCollectionNewEstudio : estudioCollectionNew) {
                if (!estudioCollectionOld.contains(estudioCollectionNewEstudio)) {
                    Egresado oldEgresadoOfEstudioCollectionNewEstudio = estudioCollectionNewEstudio.getEgresado();
                    estudioCollectionNewEstudio.setEgresado(egresado);
                    estudioCollectionNewEstudio = em.merge(estudioCollectionNewEstudio);
                    if (oldEgresadoOfEstudioCollectionNewEstudio != null && !oldEgresadoOfEstudioCollectionNewEstudio.equals(egresado)) {
                        oldEgresadoOfEstudioCollectionNewEstudio.getEstudioCollection().remove(estudioCollectionNewEstudio);
                        oldEgresadoOfEstudioCollectionNewEstudio = em.merge(oldEgresadoOfEstudioCollectionNewEstudio);
                    }
                }
            }
            for (Experiencia experienciaCollectionOldExperiencia : experienciaCollectionOld) {
                if (!experienciaCollectionNew.contains(experienciaCollectionOldExperiencia)) {
                    experienciaCollectionOldExperiencia.setEgresado(null);
                    experienciaCollectionOldExperiencia = em.merge(experienciaCollectionOldExperiencia);
                }
            }
            for (Experiencia experienciaCollectionNewExperiencia : experienciaCollectionNew) {
                if (!experienciaCollectionOld.contains(experienciaCollectionNewExperiencia)) {
                    Egresado oldEgresadoOfExperienciaCollectionNewExperiencia = experienciaCollectionNewExperiencia.getEgresado();
                    experienciaCollectionNewExperiencia.setEgresado(egresado);
                    experienciaCollectionNewExperiencia = em.merge(experienciaCollectionNewExperiencia);
                    if (oldEgresadoOfExperienciaCollectionNewExperiencia != null && !oldEgresadoOfExperienciaCollectionNewExperiencia.equals(egresado)) {
                        oldEgresadoOfExperienciaCollectionNewExperiencia.getExperienciaCollection().remove(experienciaCollectionNewExperiencia);
                        oldEgresadoOfExperienciaCollectionNewExperiencia = em.merge(oldEgresadoOfExperienciaCollectionNewExperiencia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = egresado.getId();
                if (findEgresado(id) == null) {
                    throw new NonexistentEntityException("The egresado with id " + id + " no longer exists.");
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
            Egresado egresado;
            try {
                egresado = em.getReference(Egresado.class, id);
                egresado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The egresado with id " + id + " no longer exists.", enfe);
            }
            Programa programa = egresado.getPrograma();
            if (programa != null) {
                programa.getEgresadoCollection().remove(egresado);
                programa = em.merge(programa);
            }
            Collection<Estudio> estudioCollection = egresado.getEstudioCollection();
            for (Estudio estudioCollectionEstudio : estudioCollection) {
                estudioCollectionEstudio.setEgresado(null);
                estudioCollectionEstudio = em.merge(estudioCollectionEstudio);
            }
            Collection<Experiencia> experienciaCollection = egresado.getExperienciaCollection();
            for (Experiencia experienciaCollectionExperiencia : experienciaCollection) {
                experienciaCollectionExperiencia.setEgresado(null);
                experienciaCollectionExperiencia = em.merge(experienciaCollectionExperiencia);
            }
            em.remove(egresado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Egresado> findEgresadoEntities() {
        return findEgresadoEntities(true, -1, -1);
    }

    public List<Egresado> findEgresadoEntities(int maxResults, int firstResult) {
        return findEgresadoEntities(false, maxResults, firstResult);
    }

    private List<Egresado> findEgresadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Egresado.class));
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

    public Egresado findEgresado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Egresado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEgresadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Egresado> rt = cq.from(Egresado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
