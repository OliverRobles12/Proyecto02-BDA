
package org.itson.restaurante.dominio;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author oliro
 */
@Entity
@Table(name = "Comandas")
public class Comanda implements Serializable {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comanda", nullable = true)
    private Long id;

    @Column(name = "folio", length = 20, unique = true, nullable = false) 
    private String folio;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", length = 9, nullable = false)
    private EstadoComanda estado;
    
    @Column(name = "total_acumulado", nullable = false)
    private Double totalAcumulado;
    
    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_mesa", nullable = false)
    private Mesa mesa;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    
    @OneToMany(mappedBy = "comanda", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<ProductoComanda> productos;

    public Comanda() {
    }

    public Comanda(String folio, EstadoComanda estado, Double totalAcumulado, LocalDateTime fechaHora, Mesa mesa, Cliente cliente, List<ProductoComanda> productos) {
        this.folio = folio;
        this.estado = estado;
        this.totalAcumulado = totalAcumulado;
        this.fechaHora = fechaHora;
        this.mesa = mesa;
        this.cliente = cliente;
        this.productos = productos;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public EstadoComanda getEstado() {
        return estado;
    }

    public void setEstado(EstadoComanda estado) {
        this.estado = estado;
    }

    public Double getTotalAcumulado() {
        return totalAcumulado;
    }

    public void setTotalAcumulado(Double totalAcumulado) {
        this.totalAcumulado = totalAcumulado;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ProductoComanda> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoComanda> productos) {
        this.productos = productos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comanda)) {
            return false;
        }
        Comanda other = (Comanda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.itson.restaurante.dominio.Comanda[ id=" + id + " ]";
    }
    
}
