
package org.itson.restaurante.negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.itson.restaurante.adapters.ComandaAComandaDTOAdapter;
import org.itson.restaurante.dominio.Cliente;
import org.itson.restaurante.dominio.Comanda;
import org.itson.restaurante.dominio.EstadoComanda;
import org.itson.restaurante.dominio.EstadoMesa;
import org.itson.restaurante.dtos.ComandaDTO;
import org.itson.restaurante.dtos.NuevaComandaDTO;
import org.itson.restaurante.persistencia.ClienteDAO;
import org.itson.restaurante.persistencia.ComandaDAO;
import org.itson.restaurante.persistencia.IClienteDAO;
import org.itson.restaurante.persistencia.IComandaDAO;
import org.itson.restaurante.persistencia.PersistenciaException;

/**
 *
 * @author oliro
 */
public class ComandaBO implements IComandaBO {

    private IComandaDAO comandaDAO;
    private IMesaBO mesaBO;
    private IClienteDAO clienteDAO;
    private IInventarioBO inventarioBO;
    
    public ComandaBO() {
        this.comandaDAO = new ComandaDAO();
        this.clienteDAO = new ClienteDAO();
        this.mesaBO = new MesaBO();
        this.inventarioBO = new InventarioBO();
    }
    
    @Override
    public boolean registrarNuevaComanda(NuevaComandaDTO nuevaComanda) throws NegocioException {
        
        try {
            inventarioBO.ingredientesNecesarios(nuevaComanda.getIngredientesRequeridos());
            
            if(!mesaBO.mesaDisponible(nuevaComanda.getIdMesa())){
                throw new NegocioException("La mesa con ID: " + nuevaComanda.getIdMesa() + " no se encuentra disponible", null);
            }
            
            Cliente cliente = null;
            if (nuevaComanda.getIdCliente() != null) {
                cliente = clienteDAO.consultarClienteFrecuente(nuevaComanda.getIdCliente());
                if (cliente == null) {
                    throw new NegocioException("El cliente con ID: " + nuevaComanda.getIdCliente() + " no se encuentra en la BD", null);
                }
            }
            
            comandaDAO.registrarNuevaComanda(nuevaComanda);
            return true;
            
        } catch (PersistenciaException ex) {
            throw new NegocioException("No ha sido posible registrar la comanda.", null);
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

    @Override
    public boolean marcarEntregaComanda(String folio) throws NegocioException {
        try {
            Comanda comanda = comandaDAO.consultarComanda(folio);
            comanda.setEstado(EstadoComanda.ENTREGADA);
            comanda.getMesa().setEstado(EstadoMesa.DISPONIBLE);
            comandaDAO.actualizarComanda(comanda);
            return true;
        } catch(PersistenciaException ex) {
            throw new NegocioException("No fue posible marcar entrega de la comanda", ex);
        }
    }

    @Override
    public ComandaDTO consultarComanda(String folio) throws NegocioException {
        try {
            Comanda comanda = comandaDAO.consultarComanda(folio);
            return ComandaAComandaDTOAdapter.adapter(comanda);
        } catch(PersistenciaException ex) {
            throw new NegocioException("No fue posible consultar la comanda con folio: " + folio, ex);
        }
    }

    @Override
    public List<ComandaDTO> consultarComandasFechas(LocalDate inicio,LocalDate fin) throws NegocioException {
        try {
            List<Comanda> comandasDominio = comandaDAO.consultarComandasFecha(inicio, fin);
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
