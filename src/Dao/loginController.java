package Dao;

import Dao.exceptions.NonexistentEntityException;
import Dto.Egresado;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Dto.Programa;
import Dto.Usuario;
import Dto.Estudio;
import java.util.ArrayList;
import java.util.List;
import Dto.Experiencia;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class loginController {
	
	private EntityManagerFactory emf = null;
	
	public loginController (EntityManagerFactory emf) {	
		this.emf = emf;
	}
	
	public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
	
	/**
	 * 
	 */
	public List querylogin(String user, String pass) 
	{
		System.out.println(user);
		EntityManager em = getEntityManager();
		 try {
	            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
	            cq.select(cq.from(Usuario.class));
	            Query q = em.createQuery(cq);
	            System.out.println(q);
	            return q.getResultList();
	        } finally {
	            em.close();
	        }
	}

}
