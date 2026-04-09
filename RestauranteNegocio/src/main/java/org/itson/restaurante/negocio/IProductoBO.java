/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.restaurante.negocio;

import java.util.List;
import org.itson.restaurante.dtos.NuevoProductoDTO;
import org.itson.restaurante.dtos.ProductoActualizadoDTO;
import org.itson.restaurante.dtos.ProductoDTO;
import org.itson.restaurante.dtos.TipoProducto;

/**
 *
 * @author juanl
 */
public interface IProductoBO {
    
    public ProductoDTO registrarProducto(NuevoProductoDTO nuevoProducto) throws NegocioException;
    
    public ProductoDTO actualizarProducto(ProductoActualizadoDTO productoActualizado) throws NegocioException;
    
    public List<ProductoDTO> consultarProductos() throws NegocioException;
    
    public List<ProductoDTO> consultarProductosFiltro(String nombre, TipoProducto categoria) throws NegocioException;
    
    public ProductoDTO productoExistente(String nombre) throws NegocioException;
    
    public void cambiarEstadoProducto(Long id) throws NegocioException;
}
