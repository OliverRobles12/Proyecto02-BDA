
package org.itson.restaurante.controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import org.itson.restaurante.dtos.ClienteFrecuenteDTO;
import org.itson.restaurante.dtos.ComandaDTO;
import org.itson.restaurante.dtos.IngredienteRecetaDTO;
import org.itson.restaurante.dtos.MesaDTO;
import org.itson.restaurante.dtos.NuevaComandaDTO;
import org.itson.restaurante.dtos.NuevoProductoComandaDTO;
import org.itson.restaurante.dtos.ProductoDTO;
import org.itson.restaurante.negocio.ComandaBO;
import org.itson.restaurante.negocio.IComandaBO;
import org.itson.restaurante.negocio.IInventarioBO;
import org.itson.restaurante.negocio.IMesaBO;
import org.itson.restaurante.negocio.IProductoBO;
import org.itson.restaurante.negocio.InventarioBO;
import org.itson.restaurante.negocio.MesaBO;
import org.itson.restaurante.negocio.NegocioException;
import org.itson.restaurante.negocio.ProductoBO;
import org.itson.restaurante.presentacion.PantallaComandas;
import org.itson.restaurante.presentacion.PantallaFormularioComanda;

/**
 *
 * @author oliro
 */
public class ControladorComandas {

    private IComandaBO comandaBO = new ComandaBO();
    private IInventarioBO inventarioBO = new InventarioBO();
    private IProductoBO producoBO = new ProductoBO();
    private IMesaBO mesaBO = new MesaBO();
    
    // Estado de la comanda
    private List<NuevoProductoComandaDTO> productosSeleccionados;
    private Map<Long, Double> ingredientesRequeridos;
    private Long idClienteReferido;
    
    public ControladorComandas() {
    }
    
    /**
     * Metodo encargado de mostrar la pantalla principal de comandas, ademas de llamar al metodo
     * que carga en la tabla las comandas activas.
     * @param pantallaActual Referencia a la pantalla.
     */
    public void mostarPantallaComandas(JFrame pantallaActual) {
        PantallaComandas vistaComandas = new PantallaComandas(this);
        vistaComandas.setVisible(true);
        cargarTablaComandas(vistaComandas);
        
        if (pantallaActual != null) {
            pantallaActual.dispose(); 
        }
        
    }
    
    /**
     * Metodo encargado de mostrar la pantallaFormularioComanda e iniciar variables de seguimiento.
     * @param pantallaActual Referencia a la pantalla.
     */
    public void mostrarPantallaFomularioComandas(JFrame pantallaActual) {
        
        this.productosSeleccionados = new ArrayList<>();
        this.ingredientesRequeridos = new HashMap<>();
        this.idClienteReferido = null;
        
        PantallaFormularioComanda vistaFormulario = new PantallaFormularioComanda(this);
        vistaFormulario.setVisible(true);
        cargarCbxMesasDisponibles(vistaFormulario);
        
        if (pantallaActual != null) {
            pantallaActual.dispose(); 
        }
        
    }
    
    // Pantalla Comandas
    
    /**
     * Metodo encargado de solicitar la lista de comandas a la BO pertinente.
     * @param vistaComandas Referencia a la pantalla.
     */
    private void cargarTablaComandas(PantallaComandas vistaComandas) {
        
        try {
            List<ComandaDTO> lista = comandaBO.consultarComandas();
            vistaComandas.LlenarTabla(lista);
        } catch (NegocioException ex) {
            vistaComandas.mostarMensaje("No fue posible mostrar las comandas registradas.", true);
        }
        
    }
    
    // Pantalla nueva comanda
    
    private void cargarCbxMesasDisponibles(PantallaFormularioComanda vistaFormulario) {
        
        try {
            List<MesaDTO> mesas = mesaBO.consultarMesasDisponibles();
            if (mesas.isEmpty()) {
                vistaFormulario.mostarMensaje("No hay mesas disponibles en este momento.", true);
                return;
            }
            vistaFormulario.llenarCbxMesas(mesas);
        } catch (NegocioException ex) {
            vistaFormulario.mostarMensaje("Erorr al cargar las mesas disponibles.", true);
        }
        
    }
    
    /**
     * Metodo encargado de contactar con el buscador de clientes dialog y guardar el cliente referido.
     * @param vistaNuevaComanda Referencia a la pantalla.
     */
    public void seleccionarClienteReferido(PantallaFormularioComanda vistaNuevaComanda) {
        ClienteFrecuenteDTO cliente = Controlador.getIntancia().getControladorClientes().mostrarBuscadorClienteDialog(vistaNuevaComanda);
        if (cliente != null) {
            this.idClienteReferido = cliente.getId();
            String nombreCompleto = cliente.getNombre() + " " + cliente.getApellidoPaterno()  + " " + cliente.getApellidoMaterno();
            vistaNuevaComanda.setTextoClienteReferido(nombreCompleto);
        }
    }
    
    /**
     * Metodo encargado de quitar al cliente referido de la comanda.
     * @param vistaNuevaComanda Referencia a la pantalla.
     */
    public void seleccionarClienteGeneral(PantallaFormularioComanda vistaNuevaComanda) {
        this.idClienteReferido = null;
        vistaNuevaComanda.setTextoClienteReferido("Cliente general");
    }
    
    /**
     * Metodo encargado de contactar con el buscador de productos para luego comprobar la 
     * disponibilidad de ingrediente y proceder a agregarlo a la comanda.
     * Agrega el mismo producto dos veces solo si hay diferencias con los detalles del pedido si no
     * suma a la cantidad deseada.
     * @param vistaNuevaComanda Referencia a la pantalla.
     */
    public void agreagarProductoComanda(PantallaFormularioComanda vistaNuevaComanda, Integer idTemporal) {
        
        ProductoDTO producto = Controlador.getIntancia().getControladorProductos().mostrarPantallaBusquedaProductos(vistaNuevaComanda);
        
        if (producto == null){
            return;
        }
        
        Map<Long, Double> ingredientesProyectados = new HashMap<>(this.ingredientesRequeridos);
        
        for (IngredienteRecetaDTO receta : producto.getReceta()){
            ingredientesProyectados.merge(receta.getIdIngrediente(), receta.getCantidad(), Double::sum);
        }
        
        try {
            inventarioBO.ingredientesNecesarios(ingredientesProyectados);
        } catch (NegocioException ex) {
            vistaNuevaComanda.mostarMensaje("No hay ingredientes suficientes", true);
            return;
        }
        
        NuevoProductoComandaDTO nuevoProductoComanda = new NuevoProductoComandaDTO(
                    idTemporal, 
                    producto.getId(), 
                    producto.getNombre(), 
                    1, 
                    "", 
                    producto.getPrecio(), 
                    producto.getPrecio()
            );
        
        this.productosSeleccionados.add(nuevoProductoComanda);
        this.ingredientesRequeridos = ingredientesProyectados;
        vistaNuevaComanda.llenarTabla(productosSeleccionados);
        
    }
    
    /**
     * Metodo encargado de aumentar la cantidad de un producto seleccionado.
     * Revisa si hay stock suficiente para preparar la nueva cantidad.
     * @param vistaNuevaComanda Referencia a la pantalla.
     * @param idRegistro Identificador del registro de un producto ya en la comanda.
     */
    public void sumarCantidadProducto(PantallaFormularioComanda vistaNuevaComanda, Integer idRegistro){
        try {
            for(NuevoProductoComandaDTO pc : this.productosSeleccionados) {
                if (pc.getIdTemporal() == idRegistro) {

                    ProductoDTO producto = producoBO.consultarProductoPorId(pc.getIdProducto());

                    Map<Long, Double> ingredientesProyectados = new HashMap<>(this.ingredientesRequeridos);
                    
                    for (IngredienteRecetaDTO receta : producto.getReceta()){
                        ingredientesProyectados.merge(receta.getIdIngrediente(), receta.getCantidad(), Double::sum);
                    }
                    
                    if (!inventarioBO.ingredientesNecesarios(ingredientesProyectados)) {
                        vistaNuevaComanda.mostarMensaje("No hay ingredientes suficientes", true);
                        return;
                    }
                    
                    pc.setCantidad(pc.getCantidad() + 1);
                    pc.setSubtotal(pc.getCantidad() * pc.getPrecioUnitario());
                    
                    this.ingredientesRequeridos = ingredientesProyectados;
                    vistaNuevaComanda.llenarTabla(this.productosSeleccionados);
                    break;
                }
            }
        } catch (NegocioException ex) {
            vistaNuevaComanda.mostarMensaje("No ha sido posible agregar el producto a la comanda.", true);
        }
    }
    
    /**
     * Metodo encargado de decrementar la cantidad de un producto seleccionado.
     * Si la cantidad llega a 0 muestra un mensaje de confirmarcion y elimina el producto de la comanda.
     * @param vistaNuevaComanda Referencia a la pantalla.
     * @param idRegistro Identificador del registro de un producto ya en la comanda.
     */
    public void eliminarProducto(PantallaFormularioComanda vistaNuevaComanda, int idRegistro) {
        try {
            
            NuevoProductoComandaDTO productoEliminar = null;
            boolean restarOEliminar = false;
            // Buscamos dentro los productos seleccionados el produto el que eliminar
            for(NuevoProductoComandaDTO pc : this.productosSeleccionados) {
                if (pc.getIdTemporal() == idRegistro) {
                    // Restaremos o eliminamos dependiendo de la cantidad del producto
                    if (pc.getCantidad() > 1) {
                        pc.setCantidad(pc.getCantidad() - 1);
                        pc.setSubtotal(pc.getCantidad() * pc.getPrecioUnitario());
                        restarOEliminar = true;
                    } else if (vistaNuevaComanda.mostrarConfirmacionEliminar(pc.getNombreProducto())) {
                        productoEliminar = pc;
                        restarOEliminar = true;
                    }
                    
                    if (restarOEliminar) {
                        // Consultamos el producto para poder obtener su receta
                        ProductoDTO producto = producoBO.consultarProductoPorId(pc.getIdProducto());
                        // Eliminaremos tambien los ingredientes requeridos
                        for (IngredienteRecetaDTO ingrediente : producto.getReceta()){
                            // En caso de no necesitar mas ese ingrediente lo eliminamos del mapa
                            this.ingredientesRequeridos.computeIfPresent(ingrediente.getIdIngrediente(), (id, cantidadActual) -> {
                                Double nuevaCantidad = cantidadActual - ingrediente.getCantidad();
                                return nuevaCantidad > 0 ? nuevaCantidad : null;
                            });
                        }
                    }
                    break;
                }
            }
            
            if (productoEliminar != null) {
               this.productosSeleccionados.remove(productoEliminar);
            }
            
            vistaNuevaComanda.llenarTabla(this.productosSeleccionados);
            
        } catch (NegocioException ex) {
            vistaNuevaComanda.mostarMensaje("No ha sido posible eliminar el producto.", true);
        }
    }
    
    /**
     * Metodo encargado de verificar y tomar los datos necesarios apra armar una NuevaComandaDTO
     * posteriormente contacta con comandaBO para registrar la nueva comanda.
     * @param vistaNuevaComanda 
     */
    public void registrarComanda(PantallaFormularioComanda vistaNuevaComanda) {
        
        Object selecionado = vistaNuevaComanda.getMesaSeleccionada();
        MesaDTO mesa;
        
        if (selecionado instanceof MesaDTO) {
            mesa = (MesaDTO) selecionado;
        } else {
            vistaNuevaComanda.mostarMensaje("Es necesario seleccionar una mesa disponible.", false);
            return;
        }
        
        if (this.productosSeleccionados.isEmpty()) {
            vistaNuevaComanda.mostarMensaje("Solo se puede registrar comandas con al menos 1 producto.", false);
            return;
        }
        
        NuevaComandaDTO nuevaComandaDTO = new NuevaComandaDTO(
                mesa.getId(),
                idClienteReferido,
                productosSeleccionados,
                ingredientesRequeridos
        );
        
        try {
            comandaBO.registrarNuevaComanda(nuevaComandaDTO);
            vistaNuevaComanda.mostarMensaje("La monda ha sido registrada con exito.", false);
        } catch (NegocioException ex) {
            vistaNuevaComanda.mostarMensaje("No ha sido posioble registrar la comanda.", true);
        }
        
    }
    
}
