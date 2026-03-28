
package org.itson.restaurante.dtos;

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
    private LocalDate fechaRegistro;

    public NuevoClienteFrecuenteDTO(String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, LocalDate fechaRegistro) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
    }

    public NuevoClienteFrecuenteDTO(String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correo, LocalDate fechaRegistro) {
        this(nombre, apellidoPaterno, apellidoMaterno, telefono, fechaRegistro);
        this.correo = correo;
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

}
