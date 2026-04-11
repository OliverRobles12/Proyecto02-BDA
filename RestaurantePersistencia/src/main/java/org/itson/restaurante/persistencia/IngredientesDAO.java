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
import org.itson.restaurante.adapters.UnidadMedidaDTOADominioAdapter;
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
    
    /**
     * Registra un nuevo ingrediente en el sistema 
     *
     * @param nuevoIngrediente Objeto con los datos del nuevo ingrediente a
     * registrar.
     * @return Ingrediente Objeto  con los datos del ingrediente ya registrado
     * @throws PersistenciaException Si algún dato es inválido o si ocurre un error
     * al comunicarse con la base de datos.
     */
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
    /**
     * Actualiza un ingrediente del sistema 
     *
     * @param ingrediente Objeto con los datos del ingrediente a
     * actualizar.
     * @return IngredienteDTO Objeto DTO con los datos del ingrediente ya actualizado
     * @throws PersistenciaException Si algún dato es inválido o si ocurre un error
     * al comunicarse con la base de datos.
     */
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
    /**
     * Consulta todos los ingredientes existentes en el sistema
     * 
     * @return ListIngredienteDTO lista de objetos DTO con los datos de cada ingrediente
     * @throws PersistenciaException Si algún dato es inválido o si ocurre un error
     * al comunicarse con la base de datos.
     */
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
    /**
     * Consulta todos los ingredientes existentes en el sistema que concuerden con el filtro recibido
     * 
     * @param filtro String que filtrara los ingredientes 
     * @return ListIngrediente lista de objetos con los datos de cada ingrediente
     * @throws PersistenciaException Si algún dato es inválido o si ocurre un error
     * al comunicarse con la base de datos.
     */
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
            cq.where(cb.or(pNombre, pUnidad));

            return em.createQuery(cq).getResultList();
        } catch (PersistenceException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No ha sido posible consultar los ingredientes", ex);
        }
    }
    /**
     * Consulta que no exista un ingrediente con las mismas caracteristicas que se ingresaron
     * 
     * 
     * @param idExcluir id del ingrediente que se esta actualizando, cuando se ingresa un ingrediente nuevo este es null
     * @param nombre nombre del ingrediente que se actualiza o crea
     * @param unidad unidad de medida del ingrediente que se actualiza o crea
     * @return boolean segun si encuentra o no un ingrediente con las mismas caracteristicas
     * @throws PersistenciaException Si algún dato es inválido o si ocurre un error
     * al comunicarse con la base de datos.
     */
    @Override
    public boolean existeIngrediente(Long idExcluir, String nombre, UnidadMedidaDTO unidad) throws PersistenciaException {

        try {
            EntityManager em = ManejadorConexiones.crearEntityManager();

            UnidadMedida unidadDominio = UnidadMedidaDTOADominioAdapter.convertirUnidadMedida(unidad);

            if (idExcluir != null) {
                String JPQL = "SELECT COUNT(i) FROM Ingrediente i "
                        + "WHERE LOWER(i.nombre) = LOWER(:nombre) "
                        + "AND i.unidadMedida = :unidad "
                        + "AND i.id != :id";
                Long count = em.createQuery(JPQL, Long.class)
                        .setParameter("nombre", nombre.trim())
                        .setParameter("unidad", unidadDominio)
                        .setParameter("id", idExcluir)
                        .getSingleResult();
                return count > 0;
            } else {
                String JPQL = "SELECT COUNT(i) FROM Ingrediente i "
                        + "WHERE LOWER(i.nombre) = LOWER(:nombre) "
                        + "AND i.unidadMedida = :unidad";
                Long count = em.createQuery(JPQL, Long.class)
                        .setParameter("nombre", nombre.trim())
                        .setParameter("unidad", unidadDominio)
                        .getSingleResult();
                return count > 0;
            }

        } catch (PersistenceException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No ha sido posible verificar el ingrediente", ex);
        }
    }
    /**
     * Busca y elimina el ingrediente seleccionado por su id.
     * 
     * @param id ingrediente que se desea eliminar
     * @return Ingrediente objeto  con todos los datos del ingrediente eliminado
     * @throws PersistenciaException Si algún dato es inválido o si ocurre un error
     * al comunicarse con la base de datos.
     */
    @Override
    public Ingrediente eliminarIngrediente(Long id) throws PersistenciaException {

        try {
            EntityManager em = ManejadorConexiones.crearEntityManager();
            em.getTransaction().begin();
            Ingrediente ingrediente = em.find(Ingrediente.class, id);
            em.remove(ingrediente);
            em.getTransaction().commit();
            return ingrediente;
        } catch (PersistenceException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No ha sido posible eliminar el ingrediente", ex);
        }
    }
    /**
     * Busca el ingrediente seleccionado por su id.
     * 
     * @param id ingrediente que se desea eliminar
     * @return Ingrediente objeto  con todos los datos del ingrediente eliminado
     * @throws PersistenciaException Si algún dato es inválido o si ocurre un error
     * al comunicarse con la base de datos.
     */
    @Override
    public Ingrediente consultarIngrediente(Long id) throws PersistenciaException {

        try {
            EntityManager em = ManejadorConexiones.crearEntityManager();
            em.getTransaction().begin();
            Ingrediente ingrediente = em.find(Ingrediente.class, id);
            em.getTransaction().commit();
            return ingrediente;
        } catch (PersistenceException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No ha sido posible eliminar el ingrediente", ex);
        }
    }
}
