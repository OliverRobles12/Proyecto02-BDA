
package org.itson.restaurante.persistencia;

import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import org.itson.restaurante.dominio.EstadoMesa;
import org.itson.restaurante.dominio.Mesa;

/**
 *
 * @author oliro
 */
public class MesaDAO implements IMesaDAO{

    private static final Logger LOGGER = Logger.getLogger(MesaDAO.class.getName());
    
    @Override
    public Mesa registrarMesa(Integer noMesa) throws PersistenciaException {
        
        EntityManager em = null;
        Mesa mesa = new Mesa(EstadoMesa.DISPONIBLE, noMesa);
        
        try {
             em = ManejadorConexiones.crearEntityManager();
             em.getTransaction().begin();
             em.persist(mesa);
             em.getTransaction().commit();
             return mesa;
        } catch (PersistenceException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No ha sido posible registrar la mesa.", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
    }

    @Override
    public Mesa eliminarMesa(Long idMesa) throws PersistenciaException {
        
        EntityManager em = null;
        
        try {
             em = ManejadorConexiones.crearEntityManager();
             em.getTransaction().begin();
             Mesa mesa = em.find(Mesa.class, idMesa);
             em.remove(mesa);
             em.getTransaction().commit();
             return mesa;
        } catch (PersistenceException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No ha sido posible eliminar la mesa.", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
    }
    
    @Override
    public List<Mesa> consultarMesasDisponibles() throws PersistenciaException {
        EntityManager em = null;
        try {
            em = ManejadorConexiones.crearEntityManager();
            String JPQL = "Select m FROM Mesa m WHERE m.estado = :estadoDisponible";
            TypedQuery<Mesa> query = em.createQuery(JPQL, Mesa.class);
            query.setParameter("estadoDisponible", EstadoMesa.DISPONIBLE);
            return query.getResultList();
        } catch  (PersistenceException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No se ha podido consultar las mesas disponibles.");
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    @Override
    public Mesa consultarMesa(Long id) throws PersistenciaException {
        EntityManager em = null;
        try {
            em = ManejadorConexiones.crearEntityManager();
            em.getTransaction().begin();
            Mesa mesa = em.find(Mesa.class, id);
            em.getTransaction().commit();
            return mesa;
        } catch  (PersistenceException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No ha sido posible consultar la mesa con id " + id);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    @Override
    public Integer consultarNumeroUltimaMesa() throws PersistenciaException {
        EntityManager em = null;
        try {
            em = ManejadorConexiones.crearEntityManager();
            String JPQL = "Select MAX(m.noMesa) FROM Mesa m";
            Integer numeroMesa = em.createQuery(JPQL, Integer.class).getSingleResult();
            if (numeroMesa == null) {
                return 0;
            }
            return numeroMesa;
        } catch (PersistenceException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No se ha podido consultar las mesas disponibles.");
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
    }
    
}
