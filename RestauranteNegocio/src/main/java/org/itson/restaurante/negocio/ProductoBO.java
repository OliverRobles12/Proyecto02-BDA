/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.restaurante.negocio;

import java.util.List;
import org.itson.restaurante.adapters.ProductoAProductoDTO;
import org.itson.restaurante.dominio.Producto;
import org.itson.restaurante.dtos.EstadoProducto;
import org.itson.restaurante.dtos.IngredienteRecetaDTO;
import org.itson.restaurante.dtos.NuevoProductoDTO;
import org.itson.restaurante.dtos.ProductoActualizadoDTO;
import org.itson.restaurante.dtos.ProductoDTO;
import org.itson.restaurante.dtos.TipoProducto;
import org.itson.restaurante.persistencia.IProductoDAO;
import org.itson.restaurante.persistencia.PersistenciaException;
import org.itson.restaurante.persistencia.ProductoDAO;

/**
 *
 * @author juanl
 */
public class ProductoBO implements IProductoBO{
    
    private final IProductoDAO productoDAO;
    private final ProductoAProductoDTO adapter;

    public ProductoBO() {
        this.productoDAO = new ProductoDAO(); 
        this.adapter = new ProductoAProductoDTO();
    }

    
    
    private void validarCamposProducto(String nombre, Double precio, TipoProducto tipoProducto, String descripcion, List<IngredienteRecetaDTO> receta) throws NegocioException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NegocioException("El nombre del producto no puede ser nulo o vacío", null);
        }
        if (nombre.length() > 100) {
            throw new NegocioException("El nombre del producto es muy largo", null);
        }
        if (precio == null || precio < 0) {
            throw new NegocioException("El precio del producto no puede ser negativo o nulo", null);
        }
        if (tipoProducto == null) {
            throw new NegocioException("El producto no puede estar sin categoría", null);
        }
        if (descripcion != null && descripcion.length() > 100) {
            throw new NegocioException("La descripción del producto es muy larga", null);
        }
        if (receta == null || receta.isEmpty()) {
            throw new NegocioException("El producto necesita ingredientes", null);
        }
    }
    
    
    @Override
    public ProductoDTO registrarProducto(NuevoProductoDTO nuevoProducto) throws NegocioException {
        
        if(nuevoProducto == null){
            throw new NegocioException("El producto no puede ser nulo", null);
        }
        
        validarCamposProducto(
            nuevoProducto.getNombre(), 
            nuevoProducto.getPrecio(), 
            nuevoProducto.getTipoProducto(), 
            nuevoProducto.getDescripcion(), 
            nuevoProducto.getReceta()
        );
        
        if (this.productoExistente(nuevoProducto.getNombre()) != null) {
            
            throw new NegocioException("Ya existe un producto registrado con el nombre: " + nuevoProducto.getNombre(), null);
            
         }
        try {
            Producto productoGuardado = productoDAO.registrarProducto(nuevoProducto);
            
            return adapter.convertirEntidadADto(
                productoGuardado, 
                nuevoProducto.getTipoProducto(), 
                nuevoProducto.getReceta()
            );
            
            
        } catch (PersistenciaException ex) {
            throw new NegocioException("Ocurrió un error al intentar registrar el producto en la base de datos.", ex);
        }
        
    }

    @Override
    public ProductoDTO actualizarProducto(ProductoDTO productoActualizado) throws NegocioException {
        
        if (productoActualizado == null) {
            throw new NegocioException("El producto a actualizar no puede ser nulo", null);
        }
        if (productoActualizado.getId() == null) {
            throw new NegocioException("El ID del producto es obligatorio para actualizarlo", null);
        }
        
        validarCamposProducto(
            productoActualizado.getNombre(), 
            productoActualizado.getPrecio(), 
            productoActualizado.getTipoProducto(), 
            productoActualizado.getDescripcion(), 
            productoActualizado.getReceta()
        );
         try {
            
            Producto producto = productoDAO.actualizarProducto(productoActualizado);
            ProductoDTO productoDTO = adapter.convertirEntidadADto(producto, 
            productoActualizado.getTipoProducto(), productoActualizado.getReceta());
             
            return productoDTO;
            
        } catch (PersistenciaException ex) {
        throw new NegocioException("Ocurrió un error al intentar actualizar el producto en la base de datos.", ex);
     }
        
    }

    @Override
    public List<ProductoDTO> consultarProductos() throws NegocioException {
        try {
            List<Producto> listaEntidades = productoDAO.consultarProductos();
            
            if (listaEntidades == null || listaEntidades.isEmpty()) {
                throw new NegocioException("La lista de productos esta vacia", null);
            }
            
            return adapter.convertirListaEntidadADto(listaEntidades);
            
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al obtener la lista de productos.", ex);
        }
    }

    @Override
    public List<ProductoDTO> consultarProductosFiltro(String nombre, TipoProducto categoria) throws NegocioException {
        try {
            
        org.itson.restaurante.dominio.TipoProducto filtroDominio = null;
        if (categoria != null) {
            filtroDominio = org.itson.restaurante.dominio.TipoProducto.valueOf(categoria.name());
        }
        
        List<Producto> productosFiltrados = productoDAO.consultarProductosFiltro(nombre, filtroDominio);
        if (productosFiltrados == null || productosFiltrados.isEmpty()) {
            throw new NegocioException("No hay coincidencias con esos filtros", null);
        }
        
        return adapter.convertirListaEntidadADto(productosFiltrados);

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al filtrar los productos.", ex);
        }
    }

    @Override
    public ProductoDTO productoExistente(String nombre) throws NegocioException {
        try {
        Producto producto = productoDAO.productoExistente(nombre);
        
        if (producto == null) {
            return null;
        }
        return adapter.entidadADtoCompleto(producto);
        
    } catch (PersistenciaException ex) {
        throw new NegocioException("Error al verificar la existencia del producto.", ex);
    }
    }

    @Override
    public void cambiarEstadoProducto(Long id) throws NegocioException {
        
        if (id == null) {
            throw new NegocioException("Se requiere el ID del producto para cambiar su estado.", null);
        }
        try {
            productoDAO.cambiarEstadoProducto(id);
            
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al cambiar el estado del producto.", ex);
        }
        
    }

    @Override
    public ProductoDTO consultarProductoPorId(Long id) throws NegocioException {
         if (id == null) {
            throw new NegocioException("Se requiere el ID del producto para buscarlo", null);
        }
        try {
            ProductoDTO producto = productoDAO.consultarProductoPorId(id);
            return producto;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al cambiar el estado del producto.", ex);
        }
    }
    
    
    
}
