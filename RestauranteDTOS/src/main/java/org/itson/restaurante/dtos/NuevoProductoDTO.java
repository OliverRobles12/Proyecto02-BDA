/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.restaurante.dtos;

import java.util.List;

/**
 *
 * @author juanl
 */
public class NuevoProductoDTO {
    
    private String nombre;
    private Double precio;
    private TipoProducto tipoProducto;
    private String descripcion;
    private byte[] imagen;
    private List<IngredienteRecetaDTO> receta;

    public NuevoProductoDTO() {
    }

    
    public NuevoProductoDTO(String nombre, Double precio, TipoProducto tipoProducto, String descripcion, byte[] imagen, List<IngredienteRecetaDTO> receta) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipoProducto = tipoProducto;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.receta = receta;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public List<IngredienteRecetaDTO> getReceta() {
        return receta;
    }
    
    
}
