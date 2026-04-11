/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.restaurante.adapters;

import java.util.ArrayList;
import java.util.List;
import org.itson.restaurante.dominio.Producto;
import org.itson.restaurante.dominio.ProductoIngrediente;
import org.itson.restaurante.dtos.IngredienteRecetaDTO;
import org.itson.restaurante.dtos.ProductoDTO;

/**
 *
 * @author juanl
 */
public class ProductoDominioADTO {
    
    
    public static ProductoDTO productoADTO(Producto producto){
        List<IngredienteRecetaDTO> listaIngredientes = new ArrayList<>();
            for (ProductoIngrediente IngredienteReceta: producto.getReceta()) {
                    IngredienteRecetaDTO ingrediente = new IngredienteRecetaDTO(
                            IngredienteReceta.getId(),
                            IngredienteReceta.getIngrediente().getNombre(),
                            IngredienteReceta.getCantidad()
                    );
                    listaIngredientes.add(ingrediente);
                    
                    
            }
            
            ProductoDTO productoDto = new ProductoDTO(producto.getId(),producto.getNombre(),producto.getPrecio(),
                    org.itson.restaurante.dtos.TipoProducto.valueOf(producto.getTipoProducto().name()),
                    org.itson.restaurante.dtos.EstadoProducto.valueOf(producto.getEstado().name()),producto.getDescripcion(),
                    producto.getImagen(),listaIngredientes
                    
            );
            return productoDto;
    
    }
}
