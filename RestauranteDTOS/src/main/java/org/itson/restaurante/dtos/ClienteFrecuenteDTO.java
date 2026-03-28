
package org.itson.restaurante.dtos;

import java.time.LocalDate;

/**
 *
 * @author oliro
 */
public class ClienteFrecuenteDTO {

    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String correo;
    private LocalDate fechaRegistro;
    
    private Integer puntosAcumulados;
    private Double totalGastado;
    private Integer numeroVisitas;
    
    private LocalDate fechaUltimaComanda;

    public ClienteFrecuenteDTO() {
    }

    public ClienteFrecuenteDTO(Long id, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correo, LocalDate fechaRegistro, Integer puntosAcumulados, Double totalGastado, Integer numeroVisitas, LocalDate fechaUltimaComanda) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaRegistro = fechaRegistro;
        this.puntosAcumulados = puntosAcumulados;
        this.totalGastado = totalGastado;
        this.numeroVisitas = numeroVisitas;
    }
    
    public ClienteFrecuenteDTO(String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correo, LocalDate fechaRegistro, Integer puntosAcumulados, Double totalGastado, Integer numeroVisitas, LocalDate fechaUltimaComanda) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaRegistro = fechaRegistro;
        this.puntosAcumulados = puntosAcumulados;
        this.totalGastado = totalGastado;
        this.numeroVisitas = numeroVisitas;
        this.fechaUltimaComanda = fechaUltimaComanda;
    }

    
      
    
    public Long getId() {
        return id;
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

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public Integer getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public Double getTotalGastado() {
        return totalGastado;
    }

    public Integer getNumeroVisitas() {
        return numeroVisitas;
    }

    public LocalDate getFechaUltimaComanda() {
        return fechaUltimaComanda;
    }
    
    
}
