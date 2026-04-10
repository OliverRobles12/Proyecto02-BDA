
package org.itson.restaurante.controladores;

import java.util.List;
import javax.swing.JFrame;
import org.itson.restaurante.dtos.ComandaDTO;
import org.itson.restaurante.negocio.ComandaBO;
import org.itson.restaurante.negocio.IComandaBO;
import org.itson.restaurante.negocio.NegocioException;
import org.itson.restaurante.presentacion.PantallaComandas;
import org.itson.restaurante.presentacion.PantallaFormularioComanda;

/**
 *
 * @author oliro
 */
public class ControladorComandas {

    private IComandaBO comandaBO ;
    
    public ControladorComandas() {
        this.comandaBO = new ComandaBO();
        
    }
    
    public void mostarPantallaComandas(JFrame pantallaActual) {
        PantallaComandas vistaComandas = new PantallaComandas(this);
        vistaComandas.setVisible(true);
        cargarTablaComandas(vistaComandas);
        
        if (pantallaActual != null) {
            pantallaActual.dispose(); 
        }
        
    }
    
    public void mostrarPantallaFomulario(JFrame pantallaActual) {
        PantallaFormularioComanda vistaFormulario = new PantallaFormularioComanda(this);
        vistaFormulario.setVisible(true);
        
        if (pantallaActual != null) {
            pantallaActual.dispose(); 
        }
        
    }
    
    public void cargarTablaComandas(PantallaComandas vistaComandas) {
        
        try {
            List<ComandaDTO> lista = comandaBO.consultarComandas();
            vistaComandas.LlenarTabla(lista);
        } catch (NegocioException ex) {
            vistaComandas.mostarMensaje("No fue posible mostrar las comandas registradas.", true);
        }
        
    }
    
    public void buscarComandas() { 
        
    }
    
}
