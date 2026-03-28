package org.itson.restaurante.persistencia;

import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.itson.restaurante.adapters.IngredienteDTOADominioAdapter;
import org.itson.restaurante.dominio.Ingrediente;
import org.itson.restaurante.dominio.UnidadMedida;
import org.itson.restaurante.dtos.IngredienteActualizadoDTO;
import org.itson.restaurante.dtos.NuevoIngredienteDTO;
import org.itson.restaurante.dtos.UnidadMedidaDTO;

/**
 *
 * @author joset
 */
public class IngredientesDAO implements IIngredientesDAO {

    private static final Logger LOGGER = Logger.getLogger(IngredientesDAO.class.getName());

    @Override
    public Ingrediente registrarIngrediente(NuevoIngredienteDTO nuevoIngrediente) throws PersistenciaException {
        Ingrediente ingrediente = IngredienteDTOADominioAdapter.adapter(nuevoIngrediente);
        
        try {
            EntityManager em = ManejadorConexiones.crearEntityManager();
            em.getTransaction().begin();
            em.persist(ingrediente);
            em.getTransaction().commit();
            return ingrediente;
        } catch (PersistenceException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No ha sido posible registrar el ingrediente", ex);
        }
    }

    @Override
    public Ingrediente actualizarIngrediente(IngredienteActualizadoDTO ingrediente) throws PersistenciaException {
       
        try {
            EntityManager em = ManejadorConexiones.crearEntityManager();
            
            em.getTransaction().begin();
            
            Ingrediente ingredienteGuardado = em.find(Ingrediente.class, ingrediente.getId());

            ingredienteGuardado.setNombre(ingrediente.getNombre());
            
            ingredienteGuardado.setStock(ingrediente.getStock());

            if (ingrediente.getImagen() != null) {
                
                ingredienteGuardado.setImagen(ingrediente.getImagen());
            }

            em.persist(ingredienteGuardado);
            
            em.getTransaction().commit();
           
            return ingredienteGuardado;
            
        } catch (PersistenceException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No ha sido posible actualizar el ingrediente", ex);
        }
    }

    @Override
    public List<Ingrediente> consultarIngredientes() throws PersistenciaException {
        try {
            EntityManager em = ManejadorConexiones.crearEntityManager();
            
            String JPQL = "SELECT i FROM Ingrediente i";
            
            TypedQuery<Ingrediente> query = em.createQuery(JPQL, Ingrediente.class);
            
            return query.getResultList();
        } catch (PersistenceException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No ha sido posible consultar los ingredientes", ex);
        }
    }

    @Override
    public List<Ingrediente> consultarIngredientesFiltro(String filtro) throws PersistenciaException {
        
        try {
            EntityManager em = ManejadorConexiones.crearEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Ingrediente> cq = cb.createQuery(Ingrediente.class);
            Root<Ingrediente> ingrediente = cq.from(Ingrediente.class);

            if (filtro == null || filtro.isBlank()) {
                return consultarIngredientes();
            }

            String patron = "%" + filtro.trim().toLowerCase() + "%";

            Predicate pNombre = cb.like(cb.lower(ingrediente.get("nombre")), patron);
            Predicate pUnidad = cb.like(cb.lower(ingrediente.get("unidadMedida")), patron);
            cq.where(cb.or(pNombre,pUnidad));

            return em.createQuery(cq).getResultList();
        } catch (PersistenceException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No ha sido posible consultar los ingredientes", ex);
        } 
    }
    

}
