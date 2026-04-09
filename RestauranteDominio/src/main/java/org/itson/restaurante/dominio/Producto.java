
package org.itson.restaurante.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author juanl
 */
@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProducto")
    private Long id;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "precio")
    private Double precio;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoProducto tipoProducto;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoProducto estado;

    @Column(name = "descripcion")
    private String descripcion;
    
    @Lob
    @Column(name = "imagen")
    private byte[] imagen;

    @OneToMany(mappedBy = "producto",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ProductoIngrediente> receta = new ArrayList<>();
    
    public Producto() {
    }

    public Producto(Long id, String nombre, Double Precio, TipoProducto tipoProducto, EstadoProducto estado, String descripcion, byte[] imagen) {
        this.id = id;
        this.nombre = nombre;
        this.precio = Precio;
        this.tipoProducto = tipoProducto;
        this.estado = estado;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public Producto(String nombre, Double Precio, TipoProducto tipoProducto, EstadoProducto estado, String descripcion, byte[] imagen) {
        this.nombre = nombre;
        this.precio = Precio;
        this.tipoProducto = tipoProducto;
        this.estado = estado;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double Precio) {
        this.precio = Precio;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public EstadoProducto getEstado() {
        return estado;
    }

    public void setEstado(EstadoProducto estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public List<ProductoIngrediente> getReceta() {
        return receta;
    }

    public void setReceta(List<ProductoIngrediente> receta) {
        this.receta = receta;
    }

    
   
    
}
