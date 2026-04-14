
package org.itson.restaurante.persistencia;

import java.time.LocalDate;
import java.util.List;
import org.itson.restaurante.dominio.Comanda;
import org.itson.restaurante.dtos.NuevaComandaDTO;

/**
 *
 * @author oliro
 */
public interface IComandaDAO {

    /**
     * Metodo encargado de registrar una nueva comanda. Ademas de consecuentemente cambiar de estado la mesa
     * asociada, restar el stock de los ingredientes necesarios para la elaboracion de los productos, agregar
     * datos/estadisticas dependiendo el tipo de cliente.
     * @param nuevaComanda Comanda empaquetada con datos de la mesa, cliente, mapa de ingredeintes, producos asociados y 
     * detalle de cada producto seleccionado.
     * @return Comanda ya armada.
     * @throws PersistenciaException Se ejecuta una transaccion para todo el procedimiento, regresa una persistencia exception
     * en caso de algun fallo.
     */
    public abstract Comanda registrarNuevaComanda(NuevaComandaDTO nuevaComanda) throws PersistenciaException;
    
    /**
     * Metodo encargado de actualizar datos simples como cambio de estado en una comanda.
     * @param comanda Comanda de dominio.
     * @return Resultado de la operacion.
     * @throws PersistenciaException 
     */
    public abstract boolean actualizarComanda(Comanda comanda) throws PersistenciaException;
    
    /**
     * Metodo encargado de consultar las comandas registradas.
     * @return Lista de comandas registrada.
     * @throws PersistenciaException 
     */
    public abstract List<Comanda> consultarComandas() throws PersistenciaException;
    
    /**
     * Metodo encargado de consultar una comanda por su folio.
     * @param folio
     * @return
     * @throws PersistenciaException 
     */
    public abstract Comanda consultarComanda(String folio) throws PersistenciaException;
    
    /**
     * Metodo encargado de consultar las comandas en un rango de fechas.
     * @param fechaInicio
     * @param fechaFin
     * @return Lista de comandas desntro del rango de fechas.
     * @throws PersistenciaException 
     */
    public abstract List<Comanda> consultarComandasFecha(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException;
    
}
