
package com.mycompany.restaurantedtos;

import java.time.LocalDate;

/**
 *
 * @author joset
 */
public class ClienteFrecuenteDTO {
    
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String correo;
    private int visitas;
    private double totalGastado;
    private int puntos;
    private LocalDate fechaRegistro;

    public ClienteFrecuenteDTO(String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correo, int visitas, double totalGastado, int puntos, LocalDate fechaRegistro) {
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getVisitas() {
        return visitas;
    }

    public void setVisitas(int visitas) {
        this.visitas = visitas;
    }

    public double getTotalGastado() {
        return totalGastado;
    }

    public void setTotalGastado(int totalGastado) {
        this.totalGastado = totalGastado;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    
}
