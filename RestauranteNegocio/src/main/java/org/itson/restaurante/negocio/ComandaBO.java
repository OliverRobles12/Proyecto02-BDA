
package org.itson.restaurante.negocio;

import java.util.ArrayList;
import java.util.List;
import org.itson.restaurante.adapters.ComandaAComandaDTOAdapter;
import org.itson.restaurante.dominio.Comanda;
import org.itson.restaurante.dtos.ComandaDTO;
import org.itson.restaurante.dtos.NuevaComandaDTO;
import org.itson.restaurante.persistencia.ComandaDAO;
import org.itson.restaurante.persistencia.IComandaDAO;
import org.itson.restaurante.persistencia.PersistenciaException;

/**
 *
 * @author oliro
 */
public class ComandaBO implements IComandaBO {

    private IComandaDAO dao;
    
    public ComandaBO() {
        dao = new ComandaDAO();
    }
    
    @Override
    public ComandaDTO registrarNuevaComanda(NuevaComandaDTO nuevaComanda) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public List<ComandaDTO> consultarComandas() throws NegocioException {
        
        try {
            
            List<Comanda> comandasDominio = dao.consultarComandas();
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
