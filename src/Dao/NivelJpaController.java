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
public class NivelJpaController implements Serializable {

    public NivelJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Nivel nivel) {
        if (nivel.getEstudioCollection() == null) {
            nivel.setEstudioCollection(new ArrayList<Estudio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Estudio> attachedEstudioCollection = new ArrayList<Estudio>();
            for (Estudio estudioCollectionEstudioToAttach : nivel.getEstudioCollection()) {
                estudioCollectionEstudioToAttach = em.getReference(estudioCollectionEstudioToAttach.getClass(), estudioCollectionEstudioToAttach.getId());
                attachedEstudioCollection.add(estudioCollectionEstudioToAttach);
            }
            nivel.setEstudioCollection(attachedEstudioCollection);
            em.persist(nivel);
            for (Estudio estudioCollectionEstudio : nivel.getEstudioCollection()) {
                Nivel oldNivelOfEstudioCollectionEstudio = estudioCollectionEstudio.getNivel();
                estudioCollectionEstudio.setNivel(nivel);
                estudioCollectionEstudio = em.merge(estudioCollectionEstudio);
                if (oldNivelOfEstudioCollectionEstudio != null) {
                    oldNivelOfEstudioCollectionEstudio.getEstudioCollection().remove(estudioCollectionEstudio);
                    oldNivelOfEstudioCollectionEstudio = em.merge(oldNivelOfEstudioCollectionEstudio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Nivel nivel) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Nivel persistentNivel = em.find(Nivel.class, nivel.getId());
            Collection<Estudio> estudioCollectionOld = persistentNivel.getEstudioCollection();
            Collection<Estudio> estudioCollectionNew = nivel.getEstudioCollection();
            Collection<Estudio> attachedEstudioCollectionNew = new ArrayList<Estudio>();
            for (Estudio estudioCollectionNewEstudioToAttach : estudioCollectionNew) {
                estudioCollectionNewEstudioToAttach = em.getReference(estudioCollectionNewEstudioToAttach.getClass(), estudioCollectionNewEstudioToAttach.getId());
                attachedEstudioCollectionNew.add(estudioCollectionNewEstudioToAttach);
            }
            estudioCollectionNew = attachedEstudioCollectionNew;
            nivel.setEstudioCollection(estudioCollectionNew);
            nivel = em.merge(nivel);
            for (Estudio estudioCollectionOldEstudio : estudioCollectionOld) {
                if (!estudioCollectionNew.contains(estudioCollectionOldEstudio)) {
                    estudioCollectionOldEstudio.setNivel(null);
                    estudioCollectionOldEstudio = em.merge(estudioCollectionOldEstudio);
                }
            }
            for (Estudio estudioCollectionNewEstudio : estudioCollectionNew) {
                if (!estudioCollectionOld.contains(estudioCollectionNewEstudio)) {
                    Nivel oldNivelOfEstudioCollectionNewEstudio = estudioCollectionNewEstudio.getNivel();
                    estudioCollectionNewEstudio.setNivel(nivel);
                    estudioCollectionNewEstudio = em.merge(estudioCollectionNewEstudio);
                    if (oldNivelOfEstudioCollectionNewEstudio != null && !oldNivelOfEstudioCollectionNewEstudio.equals(nivel)) {
                        oldNivelOfEstudioCollectionNewEstudio.getEstudioCollection().remove(estudioCollectionNewEstudio);
                        oldNivelOfEstudioCollectionNewEstudio = em.merge(oldNivelOfEstudioCollectionNewEstudio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nivel.getId();
                if (findNivel(id) == null) {
                    throw new NonexistentEntityException("The nivel with id " + id + " no longer exists.");
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
            Nivel nivel;
            try {
                nivel = em.getReference(Nivel.class, id);
                nivel.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nivel with id " + id + " no longer exists.", enfe);
            }
            Collection<Estudio> estudioCollection = nivel.getEstudioCollection();
            for (Estudio estudioCollectionEstudio : estudioCollection) {
                estudioCollectionEstudio.setNivel(null);
                estudioCollectionEstudio = em.merge(estudioCollectionEstudio);
            }
            em.remove(nivel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Nivel> findNivelEntities() {
        return findNivelEntities(true, -1, -1);
    }

    public List<Nivel> findNivelEntities(int maxResults, int firstResult) {
        return findNivelEntities(false, maxResults, firstResult);
    }

    private List<Nivel> findNivelEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Nivel.class));
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

    public Nivel findNivel(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Nivel.class, id);
        } finally {
            em.close();
        }
    }

    public int getNivelCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Nivel> rt = cq.from(Nivel.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
