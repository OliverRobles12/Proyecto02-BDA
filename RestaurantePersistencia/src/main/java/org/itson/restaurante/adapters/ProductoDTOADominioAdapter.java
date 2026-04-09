/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.restaurante.adapters;

import org.itson.restaurante.dominio.EstadoProducto;
import org.itson.restaurante.dominio.Producto;
import org.itson.restaurante.dominio.TipoProducto;
import org.itson.restaurante.dtos.NuevoProductoDTO;

/**
 *
 * @author juanl
 */
public class ProductoDTOADominioAdapter {
    
    public static Producto adapter(NuevoProductoDTO nuevoProducto) {
        
        TipoProducto tipoDominio = null;
        if (nuevoProducto.getTipoProducto() != null) {
            tipoDominio = TipoProducto.valueOf(nuevoProducto.getTipoProducto().name());
        }

        Producto producto = new Producto(
                nuevoProducto.getNombre(),
                nuevoProducto.getPrecio(),
                tipoDominio,
                EstadoProducto.ACTIVO,
                nuevoProducto.getDescripcion(),
                nuevoProducto.getImagen()
        );

        return producto;
    }

}
