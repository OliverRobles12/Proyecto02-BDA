/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapters;

import com.mycompany.restaurantedtos.ClienteFrecuenteDTO;
import itson.org.restaurantedominio.ClienteFrecuente;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joset
 */
public class ClienteFrecuenteAClienteDTOAdapter {
    
    
    
    public static ClienteFrecuenteDTO convertirADTO(ClienteFrecuente c) {
        LocalDate fechaUltimaComanda = LocalDate.now(); // TODO conectar con comandaDAO
        return new ClienteFrecuenteDTO(
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
        );
    }

    // método para convertir una lista a DTOs
    public static List<ClienteFrecuenteDTO> convertirListaClientesADTO(List<ClienteFrecuente> clientes) {
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
    }

}
