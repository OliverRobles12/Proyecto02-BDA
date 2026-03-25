/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurantedtos;

import java.time.LocalDate;

/**
 *
 * @author joset
 */
public class NuevoClienteFrecuenteDTO {

    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String correo;
    private int visitas;
    private double totalGastado;
    private int puntos;
    private LocalDate fechaRegistro;

    public NuevoClienteFrecuenteDTO(String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correo, int visitas, double totalGastado, int puntos, LocalDate fechaRegistro) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.correo = correo;
        this.visitas = visitas;
        this.totalGastado = totalGastado;
        this.puntos = puntos;
        this.fechaRegistro = fechaRegistro;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public int getVisitas() {
        return visitas;
    }

    public double getTotalGastado() {
        return totalGastado;
    }

    public int getPuntos() {
        return puntos;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

}
