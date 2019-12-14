/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dao.exceptions.NonexistentEntityException;
import Dto.Egresado;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Dto.Programa;
import Dto.Estudio;
import java.util.ArrayList;
import java.util.List;
import Dto.Experiencia;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ESTUDIANTE
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
        if (egresado.getEstudioList() == null) {
            egresado.setEstudioList(new ArrayList<Estudio>());
        }
        if (egresado.getExperienciaList() == null) {
            egresado.setExperienciaList(new ArrayList<Experiencia>());
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
            List<Estudio> attachedEstudioList = new ArrayList<Estudio>();
            for (Estudio estudioListEstudioToAttach : egresado.getEstudioList()) {
                estudioListEstudioToAttach = em.getReference(estudioListEstudioToAttach.getClass(), estudioListEstudioToAttach.getId());
                attachedEstudioList.add(estudioListEstudioToAttach);
            }
            egresado.setEstudioList(attachedEstudioList);
            List<Experiencia> attachedExperienciaList = new ArrayList<Experiencia>();
            for (Experiencia experienciaListExperienciaToAttach : egresado.getExperienciaList()) {
                experienciaListExperienciaToAttach = em.getReference(experienciaListExperienciaToAttach.getClass(), experienciaListExperienciaToAttach.getId());
                attachedExperienciaList.add(experienciaListExperienciaToAttach);
            }
            egresado.setExperienciaList(attachedExperienciaList);
            em.persist(egresado);
            if (programa != null) {
                programa.getEgresadoList().add(egresado);
                programa = em.merge(programa);
            }
            for (Estudio estudioListEstudio : egresado.getEstudioList()) {
                Egresado oldEgresadoOfEstudioListEstudio = estudioListEstudio.getEgresado();
                estudioListEstudio.setEgresado(egresado);
                estudioListEstudio = em.merge(estudioListEstudio);
                if (oldEgresadoOfEstudioListEstudio != null) {
                    oldEgresadoOfEstudioListEstudio.getEstudioList().remove(estudioListEstudio);
                    oldEgresadoOfEstudioListEstudio = em.merge(oldEgresadoOfEstudioListEstudio);
                }
            }
            for (Experiencia experienciaListExperiencia : egresado.getExperienciaList()) {
                Egresado oldEgresadoOfExperienciaListExperiencia = experienciaListExperiencia.getEgresado();
                experienciaListExperiencia.setEgresado(egresado);
                experienciaListExperiencia = em.merge(experienciaListExperiencia);
                if (oldEgresadoOfExperienciaListExperiencia != null) {
                    oldEgresadoOfExperienciaListExperiencia.getExperienciaList().remove(experienciaListExperiencia);
                    oldEgresadoOfExperienciaListExperiencia = em.merge(oldEgresadoOfExperienciaListExperiencia);
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
            List<Estudio> estudioListOld = persistentEgresado.getEstudioList();
            List<Estudio> estudioListNew = egresado.getEstudioList();
            List<Experiencia> experienciaListOld = persistentEgresado.getExperienciaList();
            List<Experiencia> experienciaListNew = egresado.getExperienciaList();
            if (programaNew != null) {
                programaNew = em.getReference(programaNew.getClass(), programaNew.getCodigo());
                egresado.setPrograma(programaNew);
            }
            List<Estudio> attachedEstudioListNew = new ArrayList<Estudio>();
            for (Estudio estudioListNewEstudioToAttach : estudioListNew) {
                estudioListNewEstudioToAttach = em.getReference(estudioListNewEstudioToAttach.getClass(), estudioListNewEstudioToAttach.getId());
                attachedEstudioListNew.add(estudioListNewEstudioToAttach);
            }
            estudioListNew = attachedEstudioListNew;
            egresado.setEstudioList(estudioListNew);
            List<Experiencia> attachedExperienciaListNew = new ArrayList<Experiencia>();
            for (Experiencia experienciaListNewExperienciaToAttach : experienciaListNew) {
                experienciaListNewExperienciaToAttach = em.getReference(experienciaListNewExperienciaToAttach.getClass(), experienciaListNewExperienciaToAttach.getId());
                attachedExperienciaListNew.add(experienciaListNewExperienciaToAttach);
            }
            experienciaListNew = attachedExperienciaListNew;
            egresado.setExperienciaList(experienciaListNew);
            egresado = em.merge(egresado);
            if (programaOld != null && !programaOld.equals(programaNew)) {
                programaOld.getEgresadoList().remove(egresado);
                programaOld = em.merge(programaOld);
            }
            if (programaNew != null && !programaNew.equals(programaOld)) {
                programaNew.getEgresadoList().add(egresado);
                programaNew = em.merge(programaNew);
            }
            for (Estudio estudioListOldEstudio : estudioListOld) {
                if (!estudioListNew.contains(estudioListOldEstudio)) {
                    estudioListOldEstudio.setEgresado(null);
                    estudioListOldEstudio = em.merge(estudioListOldEstudio);
                }
            }
            for (Estudio estudioListNewEstudio : estudioListNew) {
                if (!estudioListOld.contains(estudioListNewEstudio)) {
                    Egresado oldEgresadoOfEstudioListNewEstudio = estudioListNewEstudio.getEgresado();
                    estudioListNewEstudio.setEgresado(egresado);
                    estudioListNewEstudio = em.merge(estudioListNewEstudio);
                    if (oldEgresadoOfEstudioListNewEstudio != null && !oldEgresadoOfEstudioListNewEstudio.equals(egresado)) {
                        oldEgresadoOfEstudioListNewEstudio.getEstudioList().remove(estudioListNewEstudio);
                        oldEgresadoOfEstudioListNewEstudio = em.merge(oldEgresadoOfEstudioListNewEstudio);
                    }
                }
            }
            for (Experiencia experienciaListOldExperiencia : experienciaListOld) {
                if (!experienciaListNew.contains(experienciaListOldExperiencia)) {
                    experienciaListOldExperiencia.setEgresado(null);
                    experienciaListOldExperiencia = em.merge(experienciaListOldExperiencia);
                }
            }
            for (Experiencia experienciaListNewExperiencia : experienciaListNew) {
                if (!experienciaListOld.contains(experienciaListNewExperiencia)) {
                    Egresado oldEgresadoOfExperienciaListNewExperiencia = experienciaListNewExperiencia.getEgresado();
                    experienciaListNewExperiencia.setEgresado(egresado);
                    experienciaListNewExperiencia = em.merge(experienciaListNewExperiencia);
                    if (oldEgresadoOfExperienciaListNewExperiencia != null && !oldEgresadoOfExperienciaListNewExperiencia.equals(egresado)) {
                        oldEgresadoOfExperienciaListNewExperiencia.getExperienciaList().remove(experienciaListNewExperiencia);
                        oldEgresadoOfExperienciaListNewExperiencia = em.merge(oldEgresadoOfExperienciaListNewExperiencia);
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
                programa.getEgresadoList().remove(egresado);
                programa = em.merge(programa);
            }
            List<Estudio> estudioList = egresado.getEstudioList();
            for (Estudio estudioListEstudio : estudioList) {
                estudioListEstudio.setEgresado(null);
                estudioListEstudio = em.merge(estudioListEstudio);
            }
            List<Experiencia> experienciaList = egresado.getExperienciaList();
            for (Experiencia experienciaListExperiencia : experienciaList) {
                experienciaListExperiencia.setEgresado(null);
                experienciaListExperiencia = em.merge(experienciaListExperiencia);
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
