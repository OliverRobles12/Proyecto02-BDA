/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.restaurante.persistencia;

import java.util.List;
import org.itson.restaurante.dominio.EstadoProducto;
import org.itson.restaurante.dominio.Producto;
import org.itson.restaurante.dominio.TipoProducto;
import org.itson.restaurante.dtos.NuevoProductoDTO;
import org.itson.restaurante.dtos.ProductoActualizadoDTO;
import org.itson.restaurante.dtos.ProductoDTO;

/**
 *
 * @author juanl
 */
public interface IProductoDAO {
    
    public Producto registrarProducto (NuevoProductoDTO nuevoProducto) throws PersistenciaException;
    
    public Producto actualizarProducto (ProductoActualizadoDTO  productoActualizado)throws PersistenciaException;
    
    public List<Producto> consultarProductos () throws PersistenciaException;
    
    public List<Producto> consultarProductosFiltro (String nombre, TipoProducto categoria) throws PersistenciaException;
    
    public ProductoDTO consultarProductoPorId(Long id) throws PersistenciaException;
    
    public Producto productoExistente(String nombre)throws PersistenciaException;
    
    public void cambiarEstadoProducto(Long id) throws PersistenciaException;
    
}
