
package itson.org.restaurantedominio;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author juanl
 */
@Entity
@Table(name = "clientes_frecuentes")
@PrimaryKeyJoinColumn(name = "id_cliente")
@DiscriminatorValue("Frecuente")
public class ClienteFrecuente extends Cliente{

    @Column(name = "puntos_acumulados", nullable = false)
    private Integer puntosAcumulados;

    @Column(name = "total_gastado", nullable = false)
    private Double totalGastado;

    @Column(name = "numero_visitas", nullable = false)
    private Integer numeroVisitas;
    
    public ClienteFrecuente() {
    }

    public ClienteFrecuente(String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, LocalDate fechaRegistro) {
        super(nombre, apellidoPaterno, apellidoMaterno, telefono, fechaRegistro);
        this.puntosAcumulados = 0;
        this.totalGastado = 0d;
        this.numeroVisitas = 0;
    }

    public ClienteFrecuente(String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correo, LocalDate fechaRegistro) {
        super(nombre, apellidoPaterno, apellidoMaterno, telefono, correo, fechaRegistro);
        this.puntosAcumulados = 0;
        this.totalGastado = 0d;
        this.numeroVisitas = 0;
    }

    public Integer getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public void setPuntosAcumulados(Integer puntosAcumulados) {
        this.puntosAcumulados = puntosAcumulados;
    }

    public Double getTotalGastado() {
        return totalGastado;
    }

    public void setTotalGastado(Double totalGastado) {
        this.totalGastado = totalGastado;
    }

    public Integer getNumeroVisitas() {
        return numeroVisitas;
    }

    public void setNumeroVisitas(Integer numeroVisitas) {
        this.numeroVisitas = numeroVisitas;
    }
    
    

   
}
