/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.restaurante.adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.itson.restaurante.dominio.Producto;
import org.itson.restaurante.dtos.EstadoProducto;
import org.itson.restaurante.dtos.IngredienteRecetaDTO;
import org.itson.restaurante.dtos.ProductoDTO;
import org.itson.restaurante.dtos.TipoProducto;
import org.itson.restaurante.dtos.UnidadMedidaDTO;

/**
 *
 * @author juanl
 */
public class ProductoAProductoDTO {
    
    public ProductoDTO convertirEntidadADto(Producto entidad, TipoProducto tipoDTO, List<IngredienteRecetaDTO> recetaDTO) {
        
        if (entidad == null) {
            return null;
        }

        return new ProductoDTO(
            entidad.getId(),
            entidad.getNombre(),
            entidad.getPrecio(),
            tipoDTO,              
            EstadoProducto.valueOf(entidad.getEstado().name()),  
            entidad.getDescripcion(),
            entidad.getImagen(),
            recetaDTO             
        );
    }
    
    public List<ProductoDTO> convertirListaEntidadADto(List<Producto> productos) {
        List<ProductoDTO> productosDtos = new ArrayList<>();
        
        for (Producto p : productos) {
            List<IngredienteRecetaDTO> recetaDto = p.getReceta().stream().map(ingrediente -> 
                new IngredienteRecetaDTO(
                    ingrediente.getIngrediente().getId(), 
                    ingrediente.getIngrediente().getNombre(), 
                    ingrediente.getCantidad() 
                ) 
            ).collect(Collectors.toList());

            productosDtos.add(new ProductoDTO(
                p.getId(),
                p.getNombre(),
                p.getPrecio(),
                TipoProducto.valueOf(p.getTipoProducto().name()), 
                EstadoProducto.valueOf(p.getEstado().name()),     
                p.getDescripcion(),
                p.getImagen(),
                recetaDto
            ));
        }
        return productosDtos;
    }
    public ProductoDTO entidadADtoCompleto(Producto producto) {
        if (producto == null) {
            return null;
        }

        List<IngredienteRecetaDTO> recetaDto = producto.getReceta().stream().map(ingrediente -> 
            new IngredienteRecetaDTO(
                ingrediente.getIngrediente().getId(),
                ingrediente.getIngrediente().getNombre(),
                ingrediente.getCantidad()
            )
        ).collect(Collectors.toList());

        return new ProductoDTO(
            producto.getId(),
            producto.getNombre(),
            producto.getPrecio(),
            TipoProducto.valueOf(producto.getTipoProducto().name()), 
            EstadoProducto.valueOf(producto.getEstado().name()),     
            producto.getDescripcion(),
            producto.getImagen(),
            recetaDto
        );
    }
}
