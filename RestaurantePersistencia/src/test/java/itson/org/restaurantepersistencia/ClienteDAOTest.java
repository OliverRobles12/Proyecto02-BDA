
package itson.org.restaurantepersistencia;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author oliro
 */
public class ClienteDAOTest {
    
    public ClienteDAOTest() {
    }

    @Test
    public void testCrearClienteFuncionaOk() {
        ClienteDAO dao = new ClienteDAO();
        Assertions.assertDoesNotThrow(() -> {
            dao.crearClientePrueba();
        });
        
    }
    
}
