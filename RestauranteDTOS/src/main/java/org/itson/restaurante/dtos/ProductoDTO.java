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
public class ProductoDTO {
    
    private Long id;
    private String nombre;
    private Double precio;
    private TipoProducto tipoProducto;
    private EstadoProducto estado;
    private String descripcion;
    private byte[] imagen;
    private List<IngredienteRecetaDTO> receta;

   

    public ProductoDTO(Long id, String nombre, Double precio, TipoProducto tipoProducto, EstadoProducto estado, String descripcion, byte[] imagen, List<IngredienteRecetaDTO> receta) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.tipoProducto = tipoProducto;
        this.estado = estado;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.receta = receta;
    }


    public Long getId() {
        return id;
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

    public EstadoProducto getEstado() {
        return estado;
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

     public void setReceta(List<IngredienteRecetaDTO> receta) {
        this.receta = receta;
    }
    
}
