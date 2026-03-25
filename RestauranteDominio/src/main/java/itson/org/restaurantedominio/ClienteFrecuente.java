/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.restaurantedominio;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author juanl
 */
@Entity
@Table(name = "clientes_frecuentes")
@PrimaryKeyJoinColumn(name = "id_cliente") // Llave primaria que es a su vez foránea a Cliente
@DiscriminatorValue("Frecuente")
public class ClienteFrecuente extends Cliente{

    @Column(name = "puntos_acumulados")
    private Integer puntosAcumulados;

    @Column(name = "total_gastado")
    private Double totalGastado;

    @Column(name = "numero_visitas")
    private Integer numeroVisitas;
    
    public ClienteFrecuente(String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correo, LocalDate fechaRegistro) {
        super(null,nombre,apellidoPaterno,apellidoMaterno,telefono,correo,fechaRegistro);
        this.puntosAcumulados = 0;
        this.totalGastado = 0.0;
        this.numeroVisitas = 0;
    }

    
   
}
