
package org.itson.restaurante.negocio;

import java.util.ArrayList;
import java.util.List;
import org.itson.restaurante.adapters.ComandaAComandaDTOAdapter;
import org.itson.restaurante.dominio.Comanda;
import org.itson.restaurante.dominio.Mesa;
import org.itson.restaurante.dtos.ComandaDTO;
import org.itson.restaurante.dtos.NuevaComandaDTO;
import org.itson.restaurante.dtos.NuevoProductoComandaDTO;
import org.itson.restaurante.persistencia.ComandaDAO;
import org.itson.restaurante.persistencia.IComandaDAO;
import org.itson.restaurante.persistencia.IMesaDAO;
import org.itson.restaurante.persistencia.PersistenciaException;

/**
 *
 * @author oliro
 */
public class ComandaBO implements IComandaBO {

    private IComandaDAO comandaDAO;
    private IMesaDAO mesaDAO;
    private IInventarioBO inventarioBO;
    
    public ComandaBO() {
        this.comandaDAO = new ComandaDAO();
        this.inventarioBO = new InventarioBO();
    }
    
    @Override
    public void registrarNuevaComanda(NuevaComandaDTO nuevaComanda) throws NegocioException {
        
        for (NuevoProductoComandaDTO pc : nuevaComanda.getProductosComanda()) {
            inventarioBO.sePuedePreparar(pc.getIdProducto(), pc.getCantidad());
        }
        
        try {
            Mesa mesa = mesaDAO.consultarMesa(nuevaComanda.getIdMesa());
        } catch (PersistenciaException ex) {
            throw new NegocioException("", null);
        }
        
        
    }
     
    @Override
    public List<ComandaDTO> consultarComandas() throws NegocioException {
        try {
            List<Comanda> comandasDominio = comandaDAO.consultarComandas();
            List<ComandaDTO> comandasDTO = new ArrayList<>();
            for (Comanda comanda : comandasDominio) {
                comandasDTO.add(ComandaAComandaDTOAdapter.adapter(comanda));
            }
            return comandasDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("No fue posible consultar comandas.", ex);
        }
        
    }
    
    
    

}
