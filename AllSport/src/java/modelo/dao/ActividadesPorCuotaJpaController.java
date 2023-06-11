/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.dao.exceptions.NonexistentEntityException;
import modelo.entidades.ActividadesPorCuota;

/**
 *
 * @author alanr
 */
public class ActividadesPorCuotaJpaController implements Serializable {

    public ActividadesPorCuotaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ActividadesPorCuota actividadesPorCuota) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(actividadesPorCuota);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ActividadesPorCuota actividadesPorCuota) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            actividadesPorCuota = em.merge(actividadesPorCuota);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = actividadesPorCuota.getId();
                if (findActividadesPorCuota(id) == null) {
                    throw new NonexistentEntityException("The actividadesPorCuota with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ActividadesPorCuota actividadesPorCuota;
            try {
                actividadesPorCuota = em.getReference(ActividadesPorCuota.class, id);
                actividadesPorCuota.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actividadesPorCuota with id " + id + " no longer exists.", enfe);
            }
            em.remove(actividadesPorCuota);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ActividadesPorCuota> findActividadesPorCuotaEntities() {
        return findActividadesPorCuotaEntities(true, -1, -1);
    }

    public List<ActividadesPorCuota> findActividadesPorCuotaEntities(int maxResults, int firstResult) {
        return findActividadesPorCuotaEntities(false, maxResults, firstResult);
    }

    private List<ActividadesPorCuota> findActividadesPorCuotaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ActividadesPorCuota.class));
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

    public ActividadesPorCuota findActividadesPorCuota(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ActividadesPorCuota.class, id);
        } finally {
            em.close();
        }
    }

    public int getActividadesPorCuotaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ActividadesPorCuota> rt = cq.from(ActividadesPorCuota.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
