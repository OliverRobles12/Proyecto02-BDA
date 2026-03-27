
package itson.org.restaurantenegocio;

import com.mycompany.restaurantedtos.ClienteFrecuenteActualizadoDTO;
import com.mycompany.restaurantedtos.ClienteFrecuenteDTO;
import com.mycompany.restaurantedtos.NuevoClienteFrecuenteDTO;
import itson.org.restaurantedominio.Cliente;
import itson.org.restaurantedominio.ClienteFrecuente;
import itson.org.restaurantepersistencia.ClienteDAO;
import itson.org.restaurantepersistencia.PersistenciaException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author juanl
 */
public class ClienteFrecuenteBO implements IClienteFrecuenteBO {

    private final ClienteDAO clientesDAO;
    private static final Logger LOGGER = Logger.getLogger(ClienteFrecuenteBO.class.getName());

    public ClienteFrecuenteBO() {
        this.clientesDAO = new ClienteDAO();
    }

    @Override
    public ClienteFrecuenteDTO registrarCliente(NuevoClienteFrecuenteDTO nuevoCliente) throws NegocioException {

        if (nuevoCliente == null) {
            throw new NegocioException("El cliente no puede ser nulo", null);
        }
        if (nuevoCliente.getNombre() == null || nuevoCliente.getNombre().isBlank()) {
            throw new NegocioException("El nombre no puede ser nulo", null);
        }
        
        if(!(nuevoCliente.getNombre().matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+"))) {
            throw new NegocioException("El nombre no puede contener numeros", null);
        }
        
        if (nuevoCliente.getApellidoPaterno() == null || nuevoCliente.getApellidoPaterno().isBlank()) {
            throw new NegocioException("El apellido paterno no puede ser nulo", null);
        }

        if (!(nuevoCliente.getApellidoPaterno().matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+"))) {
            throw new NegocioException("El apellido paterno no puede contener numeros", null);
        }

        if (nuevoCliente.getApellidoMaterno() == null || nuevoCliente.getApellidoMaterno().isBlank()) {
            throw new NegocioException("El apellido materno no puede ser nulo", null);
        }

        if (!(nuevoCliente.getApellidoMaterno().matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+"))) {
            throw new NegocioException("El apellido materno no puede contener numeros", null);
        }

        if (nuevoCliente.getCorreo() == null || nuevoCliente.getCorreo().isBlank()) {
        } else {
            if (!(nuevoCliente.getCorreo().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))) {
                throw new NegocioException("El correo es invalido", null);
            }
        }

        if (nuevoCliente.getTelefono() == null || nuevoCliente.getTelefono().isBlank()) {
            throw new NegocioException("El telefono no puede ser nulo", null);
        }

        if (!(nuevoCliente.getTelefono().matches("^\\d{3}[- ]?\\d{3}[- ]?\\d{4}$"))) {
            throw new NegocioException("El telefono es invalido", null);
        }
        

        try {
            Cliente cliente = clientesDAO.registrarClienteFrecuente(nuevoCliente);
            //TODO
            //LocalDate fechaUltimaComanda = clientesDAO.consultarFechaUltimaComanda(cliente.getId());
            LocalDate fechaUltimaComanda = LocalDate.now(); //Eliminar esto
            return new ClienteFrecuenteDTO(
                    cliente.getId(),
                    cliente.getNombre(),
                    cliente.getApellidoPaterno(),
                    cliente.getApellidoMaterno(),
                    cliente.getTelefono(),
                    cliente.getCorreo(),
                    cliente.getFechaRegistro(), 0, 0.0d, 0,
                    fechaUltimaComanda
            );

        } catch (PersistenciaException ex) {
            throw new NegocioException("No fue posible registrar al cliente", ex);
        }
    }

    @Override
    public ClienteFrecuenteDTO actualizarCliente(ClienteFrecuenteDTO clienteActualizado) throws NegocioException {
        if (clienteActualizado.getNombre() == null || clienteActualizado.getNombre().isBlank()) {
            throw new NegocioException("El nombre no puede ser nulo", null);
            
        } else if (!(clienteActualizado.getNombre().matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+"))) {
            throw new NegocioException("El nombre no puede contener numeros", null);
        }

        if (clienteActualizado.getApellidoPaterno() == null || clienteActualizado.getApellidoPaterno().isBlank()) {
            throw new NegocioException("El apellido paterno no puede ser nulo", null);
        }

        if (!(clienteActualizado.getApellidoPaterno().matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+"))) {
            throw new NegocioException("El apellido paterno no puede contener numeros", null);
        }

        if (clienteActualizado.getApellidoMaterno() == null || clienteActualizado.getApellidoMaterno().isBlank()) {
            throw new NegocioException("El apellido materno no puede ser nulo", null);
        }

        if (!(clienteActualizado.getApellidoMaterno().matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+"))) {
            throw new NegocioException("El apellido materno no puede contener numeros", null);
        }
        
        if (clienteActualizado.getCorreo() == null || clienteActualizado.getCorreo().isBlank()) {
        } else {
            if (!(clienteActualizado.getCorreo().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))) {
                throw new NegocioException("El correo es invalido", null);
            }
        }

        if (clienteActualizado.getTelefono() == null || clienteActualizado.getTelefono().isBlank()) {
            throw new NegocioException("El telefono no puede ser nulo", null);
        }

        if (!(clienteActualizado.getTelefono().matches("^\\d{3}[- ]?\\d{3}[- ]?\\d{4}$"))) {
            throw new NegocioException("El telefono es invalido", null);
        }
        try {
            ClienteFrecuenteActualizadoDTO clienteConversion = new ClienteFrecuenteActualizadoDTO(
                    clienteActualizado.getId(),
                    clienteActualizado.getNombre(),
                    clienteActualizado.getApellidoPaterno(),
                    clienteActualizado.getApellidoMaterno(),
                    clienteActualizado.getTelefono(),
                    clienteActualizado.getCorreo()
            );
            ClienteFrecuente cliente = clientesDAO.actualizarClienteFrecuente(clienteConversion);
            //TODO
            //LocalDate fechaUltimaComanda = clientesDAO.consultarFechaUltimaComanda(cliente.getId());
            LocalDate fechaUltimaComanda = LocalDate.now(); //Eliminar esto
            return new ClienteFrecuenteDTO(
                    cliente.getId(),
                    cliente.getNombre(),
                    cliente.getApellidoPaterno(),
                    cliente.getApellidoMaterno(),
                    cliente.getTelefono(),
                    cliente.getCorreo(),
                    cliente.getFechaRegistro(),
                    cliente.getPuntosAcumulados(),
                    cliente.getTotalGastado(),
                    cliente.getNumeroVisitas(),
                    fechaUltimaComanda
            );
        } catch (PersistenciaException ex) {
            throw new NegocioException("No fue posible registrar al cliente", ex);
        }

    }

    @Override
    public List<ClienteFrecuenteDTO> consultarClienteFiltro(String filtro) throws NegocioException {
        try {

            List<ClienteFrecuente> clientes = clientesDAO.consultarClientesFrecuentesFiltro(filtro);

            List<ClienteFrecuenteDTO> clientesDTO = new ArrayList<>();

            for (ClienteFrecuente c : clientes) {
                //TODO
                //LocalDate fechaUltimaComanda = clientesDAO.consultarFechaUltimaComanda(cliente.getId());
                LocalDate fechaUltimaComanda = LocalDate.now(); //Eliminar esto
                clientesDTO.add(new ClienteFrecuenteDTO(
                        c.getId(),
                        c.getNombre(),
                        c.getApellidoPaterno(),
                        c.getApellidoMaterno(),
                        c.getTelefono(),
                        c.getCorreo(),
                        c.getFechaRegistro(),
                        c.getPuntosAcumulados(),
                        c.getTotalGastado(),
                        c.getNumeroVisitas(),
                        fechaUltimaComanda
                ));
            }
            return clientesDTO;

        } catch (PersistenciaException ex) {
            throw new NegocioException("No fue posible registrar al cliente", ex);
        }
    }

    @Override
    public ClienteFrecuenteDTO EliminarCliente() throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ClienteFrecuenteDTO> consultarClientes() throws NegocioException {
        try {

            List<ClienteFrecuente> clientes = clientesDAO.consultarClientesFrecuentes();

            List<ClienteFrecuenteDTO> clientesDTO = new ArrayList<>();

            for (ClienteFrecuente c : clientes) {
                //TODO
                //LocalDate fechaUltimaComanda = clientesDAO.consultarFechaUltimaComanda(cliente.getId());
                LocalDate fechaUltimaComanda = LocalDate.now(); //Eliminar esto
                clientesDTO.add(new ClienteFrecuenteDTO(
                        c.getId(),
                        c.getNombre(),
                        c.getApellidoPaterno(),
                        c.getApellidoMaterno(),
                        c.getTelefono(),
                        c.getCorreo(),
                        c.getFechaRegistro(),
                        c.getPuntosAcumulados(),
                        c.getTotalGastado(),
                        c.getNumeroVisitas(),
                        fechaUltimaComanda
                ));
            }
            return clientesDTO;

        } catch (PersistenciaException ex) {
            throw new NegocioException("No fue posible registrar al cliente", ex);
        }
    }

}
