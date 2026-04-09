/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.restaurante.dtos;

/**
 *
 * @author juanl
 */
public class IngredienteRecetaDTO {
 
    private Long idIngrediente;
    private String nombre;
    private Double cantidad;

    public IngredienteRecetaDTO(Long idIngrediente, String nombre, Double cantidad) {
        this.idIngrediente = idIngrediente;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public Long getIdIngrediente() {
        return idIngrediente;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getCantidad() {
        return cantidad;
    }
    
    
}
