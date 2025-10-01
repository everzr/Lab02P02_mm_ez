/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Controladores.exceptions.RollbackFailureException;
import Entity.DetalleReservacion;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 * JPA Controller for DetalleReservacion entity (RESOURCE_LOCAL transactions)
 */
public class DetalleReservacionJpaController implements Serializable {

    @Resource
    private UserTransaction utx; // Not used with RESOURCE_LOCAL, kept for parity

    private final EntityManagerFactory emf;

    public DetalleReservacionJpaController() {
        this.emf = Persistence.createEntityManagerFactory("pruebaJPAPU");
    }

    public DetalleReservacionJpaController(UserTransaction utx) {
        this();
        this.utx = utx;
    }

    public DetalleReservacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetalleReservacion detalle) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(detalle);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                if (em != null && em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetalleReservacion detalle) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            detalle = em.merge(detalle);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                if (em != null && em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detalle.getIDDetalle();
                if (findDetalleReservacion(id) == null) {
                    throw new NonexistentEntityException("The detalleReservacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleReservacion detalle;
            try {
                detalle = em.getReference(DetalleReservacion.class, id);
                detalle.getIDDetalle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleReservacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(detalle);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                if (em != null && em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetalleReservacion> findDetalleReservacionEntities() {
        return findDetalleReservacionEntities(true, -1, -1);
    }

    public List<DetalleReservacion> findDetalleReservacionEntities(int maxResults, int firstResult) {
        return findDetalleReservacionEntities(false, maxResults, firstResult);
    }

    private List<DetalleReservacion> findDetalleReservacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            javax.persistence.criteria.CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<DetalleReservacion> cq = cb.createQuery(DetalleReservacion.class);
            Root<DetalleReservacion> rt = cq.from(DetalleReservacion.class);
            cq.select(rt);
            TypedQuery<DetalleReservacion> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public DetalleReservacion findDetalleReservacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetalleReservacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleReservacionCount() {
        EntityManager em = getEntityManager();
        try {
            javax.persistence.criteria.CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<DetalleReservacion> rt = cq.from(DetalleReservacion.class);
            cq.select(cb.count(rt));
            TypedQuery<Long> q = em.createQuery(cq);
            return q.getSingleResult().intValue();
        } finally {
            em.close();
        }
    }
}
