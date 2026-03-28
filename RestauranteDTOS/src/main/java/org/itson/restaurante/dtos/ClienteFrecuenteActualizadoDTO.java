
package org.itson.restaurante.dtos;

/**
 *
 * @author juanl
 */
public class ClienteFrecuenteActualizadoDTO {
    
    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String correo;

    public ClienteFrecuenteActualizadoDTO() {
    }

    public ClienteFrecuenteActualizadoDTO(Long id, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.correo = correo;
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
    
}
