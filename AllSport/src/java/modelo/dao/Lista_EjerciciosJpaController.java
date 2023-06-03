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
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.dao.exceptions.NonexistentEntityException;
import modelo.entidades.Lista_Ejercicios;

/**
 *
 * @author alanr
 */
public class Lista_EjerciciosJpaController implements Serializable {

    public Lista_EjerciciosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Lista_Ejercicios lista_Ejercicios) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(lista_Ejercicios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Lista_Ejercicios lista_Ejercicios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            lista_Ejercicios = em.merge(lista_Ejercicios);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = lista_Ejercicios.getId();
                if (findLista_Ejercicios(id) == null) {
                    throw new NonexistentEntityException("The lista_Ejercicios with id " + id + " no longer exists.");
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
            Lista_Ejercicios lista_Ejercicios;
            try {
                lista_Ejercicios = em.getReference(Lista_Ejercicios.class, id);
                lista_Ejercicios.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lista_Ejercicios with id " + id + " no longer exists.", enfe);
            }
            em.remove(lista_Ejercicios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Lista_Ejercicios> findLista_EjerciciosEntities() {
        return findLista_EjerciciosEntities(true, -1, -1);
    }

    public List<Lista_Ejercicios> findLista_EjerciciosEntities(int maxResults, int firstResult) {
        return findLista_EjerciciosEntities(false, maxResults, firstResult);
    }

    private List<Lista_Ejercicios> findLista_EjerciciosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Lista_Ejercicios.class));
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

    public Lista_Ejercicios findLista_Ejercicios(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Lista_Ejercicios.class, id);
        } finally {
            em.close();
        }
    }

    public int getLista_EjerciciosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Lista_Ejercicios> rt = cq.from(Lista_Ejercicios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    
        public List<Lista_Ejercicios> findLista_EjerciciosEntitiesByIdCliente(long idCliente) {
        EntityManager em = getEntityManager();
        try {
            // Crear la consulta para obtener los equipos de la liga
            TypedQuery<Lista_Ejercicios> query = em.createQuery("SELECT l FROM Lista_Ejercicios l where l.usuario.id = :idCliente", Lista_Ejercicios.class);
            query.setParameter("idCliente", idCliente);
            // Ejecutar la consulta y devolver los resultados
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
}
