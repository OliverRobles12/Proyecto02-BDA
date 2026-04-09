/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.restaurante.persistencia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.itson.restaurante.adapters.ProductoDTOADominioAdapter;
import org.itson.restaurante.dominio.EstadoProducto;
import org.itson.restaurante.dominio.Ingrediente;
import org.itson.restaurante.dominio.Producto;
import org.itson.restaurante.dominio.ProductoIngrediente;
import org.itson.restaurante.dominio.TipoProducto;
import org.itson.restaurante.dtos.IngredienteRecetaDTO;
import org.itson.restaurante.dtos.NuevoIngredienteDTO;
import org.itson.restaurante.dtos.NuevoProductoDTO;
import org.itson.restaurante.dtos.ProductoActualizadoDTO;

/**
 *
 * @author juanl
 */
public class ProductosDAO implements IProductosDAO{

    private static final Logger LOGGER = Logger.getLogger(ProductosDAO.class.getName());

    
    /**
     * Registra un nuevo producto en el sistema ya con su receta 
     * Y convierte la DTO que recibe y la transforma a una entidad de dominios y
     * le agrega los ingredientes existentes
     * @param nuevoProducto DTO que recibe la informacion del producto que se quiere registrar
     * @return El producto que se guardó en la base de datos
     * @throws PersistenciaException error durante la persistencia o si 
     * algun ingrediente de la receta no se encuentra
     */
    @Override
    public Producto registrarProducto(NuevoProductoDTO nuevoProducto) throws PersistenciaException {
        EntityManager entityManager = null;
        try{
            entityManager = ManejadorConexiones.crearEntityManager();
            entityManager.getTransaction().begin();
            Producto producto = ProductoDTOADominioAdapter.adapter(nuevoProducto);
            
            if (nuevoProducto.getReceta() != null) {
            for (IngredienteRecetaDTO ingredienteRecetaDTO : nuevoProducto.getReceta()) {
                Ingrediente ing = entityManager.find(Ingrediente.class,ingredienteRecetaDTO.getIdIngrediente());
                
                if (ing == null) {
                    throw new PersistenciaException("No se encontró el ingrediente con ID: " + ingredienteRecetaDTO.getIdIngrediente());
                }
                
                ProductoIngrediente pi = new ProductoIngrediente();
                pi.setCantidad(ingredienteRecetaDTO.getCantidad());
                pi.setProducto(producto);
                pi.setIngrediente(ing);
                producto.getReceta().add(pi);
                }
            }
            
            entityManager.persist(producto);
            entityManager.getTransaction().commit();

            return producto;        

        }catch(PersistenceException ex){
            if (entityManager != null && entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
            }
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No se pudo registrar el producto",ex);
            
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    /**
     * Actualiza la informacion de un producto que ya existe
     * Permite modificar sus datos basicos y volver a hacer su receta
     * @param productoActualizado DTO con los nuevos datos del producto y su Id
     * @return El producto actualizado.
     * @throws PersistenciaException Si el producto no existe o hay un error
     * en la base de datos
     */
    @Override
    public Producto actualizarProducto(ProductoActualizadoDTO productoActualizado) throws PersistenciaException {
        EntityManager entityManager = null;
        try{
            entityManager = ManejadorConexiones.crearEntityManager();
            entityManager.getTransaction().begin();
            Producto producto = entityManager.find(Producto.class, productoActualizado.getId());
            if (producto == null) {
                
                throw new PersistenciaException("No se encontró el producto a actualizar con ID: " + productoActualizado.getId());
                
            }
            
            producto.setNombre(productoActualizado.getNombre());
            producto.setPrecio(productoActualizado.getPrecio());
            if (productoActualizado.getTipoProducto() != null) {
                producto.setTipoProducto(org.itson.restaurante.dominio.TipoProducto.valueOf(productoActualizado.getTipoProducto().name()));
            }
            producto.setEstado(org.itson.restaurante.dominio.EstadoProducto.valueOf(productoActualizado.getEstado().name()  ));
            producto.setDescripcion(productoActualizado.getDescripcion());
            producto.setImagen(productoActualizado.getImagen());
            producto.getReceta().clear();
            
            if (productoActualizado.getReceta() != null) {
            for (IngredienteRecetaDTO ingredienteRecetaDTO : productoActualizado.getReceta()) {
                Ingrediente ing = entityManager.find(Ingrediente.class,ingredienteRecetaDTO.getIdIngrediente());
                
                if (ing == null) {
                    throw new PersistenciaException("No se encontró el ingrediente con ID: " + ingredienteRecetaDTO.getIdIngrediente());
                }
                
                ProductoIngrediente pi = new ProductoIngrediente();
                pi.setCantidad(ingredienteRecetaDTO.getCantidad());
                pi.setProducto(producto);
                pi.setIngrediente(ing);
                producto.getReceta().add(pi);
                }
            }
            
            entityManager.merge(producto);
            entityManager.getTransaction().commit();

            return producto;        

        }catch(PersistenceException ex){
            if (entityManager != null && entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
            }
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No se pudo actualizar el producto",ex);
            
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    
    /**
     * Obtiene la lista de todos los productos registrados en el sistema
     * @return Lista de todos los producto
     * @throws PersistenciaException Si ocurre un error al realizar la consulta
     */
    @Override
    public List<Producto> ConsultarProductos() throws PersistenciaException {
        EntityManager entityManager = null;
        
        try{
            entityManager = ManejadorConexiones.crearEntityManager();
            String jpql = """
                          SELECT p FROM Producto p
                          """;
            TypedQuery<Producto> query = entityManager.createQuery(jpql, Producto.class);
            return query.getResultList();
        
        }catch(PersistenceException ex){
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No hay productos registrados",ex);
            
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
                
    }

    /**
     * Busca productos filtrando opcionalmente por nombre (coincidencia parcial)
     * y/o categoria
     * Usa criteria API para la construccion de la consulta.
     * @param nombre Nombre i parte del nombre a filtrar
     * @param categoria categoria a filtrar
     * @return Un lista de productos con las coincidencias al aplicar los filtros
     * @throws PersistenciaException Si ocurre un error en la ejecucion del Criteria query
     */
    @Override
    public List<Producto> ConsultarProductosFiltro(String nombre, TipoProducto categoria) throws PersistenciaException {
       EntityManager entityManager = null; 
       try{
            entityManager = ManejadorConexiones.crearEntityManager();
            
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            
            CriteriaQuery<Producto> criteria = builder.createQuery(Producto.class);
            
            Root<Producto> root = criteria.from(Producto.class);
            
            List<Predicate> lista = new ArrayList<>();
            
            if(nombre != null && !nombre.isEmpty()){
                lista.add(builder.like(root.get("nombre"), "%" + nombre + "%"));
                
            }
            if(categoria != null){
                lista.add(builder.equal(root.get("tipoProducto"), categoria));
            }
            
            criteria.where(builder.and(lista.toArray(new Predicate[0])));
            return entityManager.createQuery(criteria).getResultList();
            
            
       }catch(PersistenceException ex){
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No se encontro el producto",ex);
            
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    
    }

    
    /**
     * Verifica si existe un producto mediante su nombre exacto
     * Este se utiliza validar nombre duplicados
     * @param nombre Nombre exacto del producto
     * @return El producto si se encontró o nulo si no existe
     * @throws PersistenciaException Si hay un error en la consulta
     */
    @Override
    public Producto ProductoExistente(String nombre) throws PersistenciaException {
        EntityManager entityManager = null; 
        try{
            entityManager = ManejadorConexiones.crearEntityManager();
            String jpql = """
                          SELECT p FROM Producto p WHERE p.nombre = :nombre
                          """;
            TypedQuery<Producto> query = entityManager.createQuery(jpql, Producto.class);
            query.setParameter("nombre", nombre);
            
            List<Producto> producto = query.getResultList();
            if(producto.isEmpty()){
                return null;
            }else{
                return producto.get(0);
            }
            
            
        }catch(PersistenceException ex){
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No se encontro el producto",ex);
            
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    /**
     * Realiza el cambio de estado de un producto pasandolo de Activo a Inactivo
     * @param id El identificador del producto a modificar
     * @throws PersistenciaException Si el producto no existe
     */
    @Override
    public void CambiarEstadoProducto(Long id) throws PersistenciaException {
        EntityManager entityManager = null; 
        try{
            entityManager = ManejadorConexiones.crearEntityManager();
            entityManager.getTransaction().begin();
            Producto productoEncontrado =entityManager.find(Producto.class,id);
            
            if(productoEncontrado != null && productoEncontrado.getEstado().equals(EstadoProducto.ACTIVO)){
                productoEncontrado.setEstado(EstadoProducto.INACTIVO);
            }
            entityManager.merge(productoEncontrado);
            entityManager.getTransaction().commit();
            
            
        }catch(PersistenceException ex){
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No se encontro el producto",ex);
            
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    
        
    }
    
}
