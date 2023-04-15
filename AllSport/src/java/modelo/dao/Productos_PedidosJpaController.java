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
import modelo.entidades.Productos_Pedidos;

/**
 *
 * @author alanr
 */
public class Productos_PedidosJpaController implements Serializable {

    public Productos_PedidosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Productos_Pedidos productos_Pedidos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(productos_Pedidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Productos_Pedidos productos_Pedidos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            productos_Pedidos = em.merge(productos_Pedidos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = productos_Pedidos.getId();
                if (findProductos_Pedidos(id) == null) {
                    throw new NonexistentEntityException("The productos_Pedidos with id " + id + " no longer exists.");
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
            Productos_Pedidos productos_Pedidos;
            try {
                productos_Pedidos = em.getReference(Productos_Pedidos.class, id);
                productos_Pedidos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productos_Pedidos with id " + id + " no longer exists.", enfe);
            }
            em.remove(productos_Pedidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Productos_Pedidos> findProductos_PedidosEntities() {
        return findProductos_PedidosEntities(true, -1, -1);
    }

    public List<Productos_Pedidos> findProductos_PedidosEntities(int maxResults, int firstResult) {
        return findProductos_PedidosEntities(false, maxResults, firstResult);
    }

    private List<Productos_Pedidos> findProductos_PedidosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Productos_Pedidos.class));
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

    public Productos_Pedidos findProductos_Pedidos(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Productos_Pedidos.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductos_PedidosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Productos_Pedidos> rt = cq.from(Productos_Pedidos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
