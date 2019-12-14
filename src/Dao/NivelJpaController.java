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
import Dto.Estudio;
import Dto.Nivel;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ESTUDIANTE
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
        if (nivel.getEstudioList() == null) {
            nivel.setEstudioList(new ArrayList<Estudio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Estudio> attachedEstudioList = new ArrayList<Estudio>();
            for (Estudio estudioListEstudioToAttach : nivel.getEstudioList()) {
                estudioListEstudioToAttach = em.getReference(estudioListEstudioToAttach.getClass(), estudioListEstudioToAttach.getId());
                attachedEstudioList.add(estudioListEstudioToAttach);
            }
            nivel.setEstudioList(attachedEstudioList);
            em.persist(nivel);
            for (Estudio estudioListEstudio : nivel.getEstudioList()) {
                Nivel oldNivelOfEstudioListEstudio = estudioListEstudio.getNivel();
                estudioListEstudio.setNivel(nivel);
                estudioListEstudio = em.merge(estudioListEstudio);
                if (oldNivelOfEstudioListEstudio != null) {
                    oldNivelOfEstudioListEstudio.getEstudioList().remove(estudioListEstudio);
                    oldNivelOfEstudioListEstudio = em.merge(oldNivelOfEstudioListEstudio);
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
            List<Estudio> estudioListOld = persistentNivel.getEstudioList();
            List<Estudio> estudioListNew = nivel.getEstudioList();
            List<Estudio> attachedEstudioListNew = new ArrayList<Estudio>();
            for (Estudio estudioListNewEstudioToAttach : estudioListNew) {
                estudioListNewEstudioToAttach = em.getReference(estudioListNewEstudioToAttach.getClass(), estudioListNewEstudioToAttach.getId());
                attachedEstudioListNew.add(estudioListNewEstudioToAttach);
            }
            estudioListNew = attachedEstudioListNew;
            nivel.setEstudioList(estudioListNew);
            nivel = em.merge(nivel);
            for (Estudio estudioListOldEstudio : estudioListOld) {
                if (!estudioListNew.contains(estudioListOldEstudio)) {
                    estudioListOldEstudio.setNivel(null);
                    estudioListOldEstudio = em.merge(estudioListOldEstudio);
                }
            }
            for (Estudio estudioListNewEstudio : estudioListNew) {
                if (!estudioListOld.contains(estudioListNewEstudio)) {
                    Nivel oldNivelOfEstudioListNewEstudio = estudioListNewEstudio.getNivel();
                    estudioListNewEstudio.setNivel(nivel);
                    estudioListNewEstudio = em.merge(estudioListNewEstudio);
                    if (oldNivelOfEstudioListNewEstudio != null && !oldNivelOfEstudioListNewEstudio.equals(nivel)) {
                        oldNivelOfEstudioListNewEstudio.getEstudioList().remove(estudioListNewEstudio);
                        oldNivelOfEstudioListNewEstudio = em.merge(oldNivelOfEstudioListNewEstudio);
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
            List<Estudio> estudioList = nivel.getEstudioList();
            for (Estudio estudioListEstudio : estudioList) {
                estudioListEstudio.setNivel(null);
                estudioListEstudio = em.merge(estudioListEstudio);
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
