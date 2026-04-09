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

/**
 *
 * @author juanl
 */
public interface IProductosDAO {
    
    public Producto registrarProducto (NuevoProductoDTO nuevoProducto) throws PersistenciaException;
    
    public Producto actualizarProducto (ProductoActualizadoDTO  productoActualizado)throws PersistenciaException;
    
    public List<Producto> ConsultarProductos () throws PersistenciaException;
    
    public List<Producto> ConsultarProductosFiltro (String nombre, TipoProducto categoria) throws PersistenciaException;
    
    public Producto ProductoExistente(String nombre)throws PersistenciaException;
    
    public void CambiarEstadoProducto(Long id) throws PersistenciaException;
    
}
