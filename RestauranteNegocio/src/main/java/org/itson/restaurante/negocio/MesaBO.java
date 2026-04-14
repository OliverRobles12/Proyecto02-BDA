
package org.itson.restaurante.negocio;

import java.util.ArrayList;
import java.util.List;
import org.itson.restaurante.adapters.MesaAMesaDTOAdapter;
import org.itson.restaurante.dominio.EstadoMesa;
import org.itson.restaurante.dominio.Mesa;
import org.itson.restaurante.dtos.MesaDTO;
import org.itson.restaurante.persistencia.MesaDAO;
import org.itson.restaurante.persistencia.PersistenciaException;

/**
 *
 * @author oliro
 */
public class MesaBO implements IMesaBO{

    private MesaDAO dao;
    
    public MesaBO() {
        dao = new MesaDAO();
    }
    
    @Override
    public void registrarMesa(Integer cantidad) throws NegocioException {
        
        try {
            Integer noUltimo = dao.consultarNumeroUltimaMesa();
            int limite = noUltimo + cantidad;
            for (int noMesa = noUltimo + 1; noMesa <= limite; noMesa++) {
                dao.registrarMesa(noMesa);
            }
        } catch (PersistenciaException ex) {
            throw new NegocioException("No ha sido posible registrar " + cantidad + " mesas", ex);
        }
        
    }
    
    @Override
    public List<MesaDTO> consultarMesasDisponibles() throws NegocioException {
        try {
            List<Mesa> mesas = dao.consultarMesasDisponibles();
            
            List<MesaDTO> mesasDto = new ArrayList<>();
            for (Mesa m : mesas) {
                 mesasDto.add(MesaAMesaDTOAdapter.adapter(m));
            }
            return mesasDto;
        } catch (PersistenciaException ex) {
            throw new NegocioException("No ha sido posible consultar las mesas disponibles.", ex);
        }
    }

    @Override
    public boolean mesaDisponible(Long idMesa) throws NegocioException {
        try {
            Mesa mesa = dao.consultarMesa(idMesa);
            if (mesa == null) {
                throw new NegocioException("La mesa con ID " + idMesa + " no existe en el sistema.", null);
            }
            return mesa.getEstado() == EstadoMesa.DISPONIBLE;
            
        } catch (PersistenciaException ex) {
            throw new NegocioException("No ha sido posible verificar la disponibilidad de la mesa.", ex);
        }
    }

    @Override
    public boolean cambiarEstadoDisponible(Long idMesa) throws NegocioException {
        try {
            Mesa mesa = dao.consultarMesa(idMesa);
            
            if (mesa == null) {
                throw new NegocioException("La mesa con ID: " + idMesa + " no existe en el sistema.", null);
            }
            
            if (mesa.getEstado() == EstadoMesa.DISPONIBLE) {
                throw new NegocioException("La mesa con ID: " + idMesa + " ya se encuentra disponible.", null);
            }
            
            mesa.setEstado(EstadoMesa.DISPONIBLE);
            return dao.actualizarMesa(mesa);
            
        } catch (PersistenciaException ex) {
            throw new NegocioException("No ha sido posible cambiar el estado de la mesa a DISPONIBLE.", ex);
        }
    }

    @Override
    public boolean cambiarEstadoOcupada(Long idMesa) throws NegocioException {
        try {
            Mesa mesa = dao.consultarMesa(idMesa);
            
            if (mesa == null) {
                throw new NegocioException("La mesa con ID: " + idMesa + " no existe en el sistema.", null);
            }
            
            if (mesa.getEstado() == EstadoMesa.OCUPADA) {
                throw new NegocioException("La mesa con ID: " + idMesa + " ya se encuentra ocupada.", null);
            }
            
            mesa.setEstado(EstadoMesa.OCUPADA);
            return dao.actualizarMesa(mesa);
            
        } catch (PersistenciaException ex) {
            throw new NegocioException("No ha sido posible cambiar el estado de la mesa a OCUPADA.", ex);
        }
    }

    
    
}
