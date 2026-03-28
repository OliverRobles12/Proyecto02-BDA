/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.restaurante.dtos;

/**
 *
 * @author joset
 */
public class IngredienteActualizadoDTO {
    private Long id;
    private String nombre; 
    private Integer stock;
    private byte[] imagen;

    public IngredienteActualizadoDTO(Long id, String nombre, Integer stock, byte[] imagen) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.imagen = imagen;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getStock() {
        return stock;
    }

    public byte[] getImagen() {
        return imagen;
    }
    
}
