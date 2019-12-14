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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Alexander
 */
public class EstudioJpaController implements Serializable {

    public EstudioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estudio estudio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Egresado egresado = estudio.getEgresado();
            if (egresado != null) {
                egresado = em.getReference(egresado.getClass(), egresado.getId());
                estudio.setEgresado(egresado);
            }
            Nivel nivel = estudio.getNivel();
            if (nivel != null) {
                nivel = em.getReference(nivel.getClass(), nivel.getId());
                estudio.setNivel(nivel);
            }
            em.persist(estudio);
            if (egresado != null) {
                egresado.getEstudioCollection().add(estudio);
                egresado = em.merge(egresado);
            }
            if (nivel != null) {
                nivel.getEstudioCollection().add(estudio);
                nivel = em.merge(nivel);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estudio estudio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudio persistentEstudio = em.find(Estudio.class, estudio.getId());
            Egresado egresadoOld = persistentEstudio.getEgresado();
            Egresado egresadoNew = estudio.getEgresado();
            Nivel nivelOld = persistentEstudio.getNivel();
            Nivel nivelNew = estudio.getNivel();
            if (egresadoNew != null) {
                egresadoNew = em.getReference(egresadoNew.getClass(), egresadoNew.getId());
                estudio.setEgresado(egresadoNew);
            }
            if (nivelNew != null) {
                nivelNew = em.getReference(nivelNew.getClass(), nivelNew.getId());
                estudio.setNivel(nivelNew);
            }
            estudio = em.merge(estudio);
            if (egresadoOld != null && !egresadoOld.equals(egresadoNew)) {
                egresadoOld.getEstudioCollection().remove(estudio);
                egresadoOld = em.merge(egresadoOld);
            }
            if (egresadoNew != null && !egresadoNew.equals(egresadoOld)) {
                egresadoNew.getEstudioCollection().add(estudio);
                egresadoNew = em.merge(egresadoNew);
            }
            if (nivelOld != null && !nivelOld.equals(nivelNew)) {
                nivelOld.getEstudioCollection().remove(estudio);
                nivelOld = em.merge(nivelOld);
            }
            if (nivelNew != null && !nivelNew.equals(nivelOld)) {
                nivelNew.getEstudioCollection().add(estudio);
                nivelNew = em.merge(nivelNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estudio.getId();
                if (findEstudio(id) == null) {
                    throw new NonexistentEntityException("The estudio with id " + id + " no longer exists.");
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
            Estudio estudio;
            try {
                estudio = em.getReference(Estudio.class, id);
                estudio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estudio with id " + id + " no longer exists.", enfe);
            }
            Egresado egresado = estudio.getEgresado();
            if (egresado != null) {
                egresado.getEstudioCollection().remove(estudio);
                egresado = em.merge(egresado);
            }
            Nivel nivel = estudio.getNivel();
            if (nivel != null) {
                nivel.getEstudioCollection().remove(estudio);
                nivel = em.merge(nivel);
            }
            em.remove(estudio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estudio> findEstudioEntities() {
        return findEstudioEntities(true, -1, -1);
    }

    public List<Estudio> findEstudioEntities(int maxResults, int firstResult) {
        return findEstudioEntities(false, maxResults, firstResult);
    }

    private List<Estudio> findEstudioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estudio.class));
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

    public Estudio findEstudio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estudio.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstudioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estudio> rt = cq.from(Estudio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
