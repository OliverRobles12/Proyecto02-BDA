/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.restaurantenegocio;

import com.mycompany.restaurantedtos.ClienteFrecuenteActualizadoDTO;
import com.mycompany.restaurantedtos.NuevoClienteFrecuenteDTO;
import itson.org.restaurantedominio.Cliente;
import itson.org.restaurantepersistencia.ClienteDAO;
import itson.org.restaurantepersistencia.PersistenciaException;
import java.time.LocalDate;
import java.util.logging.Logger;

/**
 *
 * @author juanl
 */
public class ClienteFrecuenteBO implements IClienteFrecuenteBO{

    private final ClienteDAO clientesDAO;
    private static final Logger LOGGER = Logger.getLogger(ClienteFrecuenteBO.class.getName());

    public ClienteFrecuenteBO(ClienteDAO clientesDAO) {
        this.clientesDAO = clientesDAO;
    }

    @Override
    public Cliente registrarCliente(NuevoClienteFrecuenteDTO nuevoCliente) throws NegocioException {
        
        if(nuevoCliente == null){
            throw new NegocioException("El cliente no puede ser nulo",null);
        }
        
        if(nuevoCliente.getNombre() == null || nuevoCliente.getNombre().isBlank()){
            throw new NegocioException("El nombre no puede ser nulo",null);
        }
        
        if(nuevoCliente.getNombre().matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+")){
            throw new NegocioException("El nombre no puede contener numeros",null);
        }
        
        if(nuevoCliente.getApellidoPaterno() == null || nuevoCliente.getApellidoPaterno().isBlank()){
            throw new NegocioException("El apellido paterno no puede ser nulo",null);
        }
        
        if(nuevoCliente.getApellidoPaterno().matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+")){
            throw new NegocioException("El apellido paterno no puede contener numeros",null);
        }
        
        if(nuevoCliente.getApellidoMaterno() == null || nuevoCliente.getApellidoMaterno().isBlank()){
            throw new NegocioException("El apellido materno no puede ser nulo",null);
        }
        
        if(nuevoCliente.getApellidoMaterno().matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+")){
            throw new NegocioException("El apellido materno no puede contener numeros",null);
        }
        
        if(nuevoCliente.getCorreo().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
            throw new NegocioException("El correo es invalido",null);
        }
        
        if(nuevoCliente.getTelefono() == null || nuevoCliente.getTelefono().isBlank()){
            throw new NegocioException("El telefono no puede ser nulo",null);
        }
        
        if(nuevoCliente.getTelefono().matches("^[1-9]\\d{2}-\\d{3}-\\d{4}$")){
            throw new NegocioException("El telefono es invalido",null);
        }
        
        if(nuevoCliente.getFechaRegistro().isAfter(LocalDate.now())){
            throw new NegocioException("La fecha del registro no puede ser futura",null);
        }
        
        try {
            Cliente cliente = clientesDAO.registrarCliente(nuevoCliente);
            return cliente;
        } catch (PersistenciaException ex) {
            throw new NegocioException("No fue posible registrar al cliente", ex);
        }
    }

    @Override
    public Cliente actualizarCliente(ClienteFrecuenteActualizadoDTO clienteActualizado) throws NegocioException{
        if(clienteActualizado.getNombre() == null || clienteActualizado.getNombre().isBlank()){
            throw new NegocioException("El nombre no puede ser nulo",null);
        }
        
        if(clienteActualizado.getNombre().matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+")){
            throw new NegocioException("El nombre no puede contener numeros",null);
        }
        
        if(clienteActualizado.getApellidoPaterno() == null || clienteActualizado.getApellidoPaterno().isBlank()){
            throw new NegocioException("El apellido paterno no puede ser nulo",null);
        }
        
        if(clienteActualizado.getApellidoPaterno().matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+")){
            throw new NegocioException("El apellido paterno no puede contener numeros",null);
        }
        
        if(clienteActualizado.getApellidoMaterno() == null || clienteActualizado.getApellidoMaterno().isBlank()){
            throw new NegocioException("El apellido materno no puede ser nulo",null);
        }
        
        if(clienteActualizado.getApellidoMaterno().matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+")){
            throw new NegocioException("El apellido materno no puede contener numeros",null);
        }
        
        if(clienteActualizado.getCorreo().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
            throw new NegocioException("El correo es invalido",null);
        }
        
        if(clienteActualizado.getTelefono() == null || clienteActualizado.getTelefono().isBlank()){
            throw new NegocioException("El telefono no puede ser nulo",null);
        }
        
        if(clienteActualizado.getTelefono().matches("^[1-9]\\d{2}-\\d{3}-\\d{4}$")){
            throw new NegocioException("El telefono es invalido",null);
        }
        
        try{
        Cliente cliente = clientesDAO.actualizarCliente(clienteActualizado);
        return cliente;
        } catch (PersistenciaException ex) {
            throw new NegocioException("No fue posible registrar al cliente", ex);
        }
          
        
    }

    @Override
    public Cliente EliminarCliente() throws NegocioException{
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
