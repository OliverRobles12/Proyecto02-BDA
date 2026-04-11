
package org.itson.restaurante.persistencia;

import java.util.List;
import org.itson.restaurante.dominio.Mesa;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author oliro
 */
public class MesasDAOTest {
    
    private MesaDAO dao;
    private Long idUltimaMesa;
    
    public MesasDAOTest() {
    }

    @BeforeEach
    public void setup () {
        dao = new MesaDAO();
        try {
            Integer ultimaMesa = dao.consultarNumeroUltimaMesa();
            Mesa mesa = dao.registrarMesa(ultimaMesa + 1);
            this.idUltimaMesa = mesa.getId();
        } catch (PersistenciaException ex) {
            Assertions.fail("No se pudo generar la mesa de prueba: " + ex.getMessage());
        }
    }
    
    @AfterEach
    public void tearDown() {
        try {
            dao.eliminarMesa(idUltimaMesa);
        } catch (PersistenciaException ex) {
            System.err.println("No se pudo limpiar la mesa de prueba: " + ex.getMessage());
        }
    }
    
    @Test
    public void registrarMesaFuncionaOk() {
        Mesa mesa = assertDoesNotThrow(() -> {
            Integer ultimaMesa = dao.consultarNumeroUltimaMesa();
            return dao.registrarMesa(ultimaMesa + 1);
        });
        assertNotNull(mesa.getId());
        // teardown
        assertDoesNotThrow(() -> {
            dao.eliminarMesa(mesa.getId());
        });
    }
    
    @Test
    public void consultarUltimaMesaPorNoMesaFuncionaOk() {
        // Ejecucion + verificacion
        Integer numerMesa = assertDoesNotThrow(() -> {
            return dao.consultarNumeroUltimaMesa();
        });
        assertNotNull(numerMesa);
    }
    
    @Test
    public void consultarMesasDisponiblesFuncionaOk() {
        // Ejecucion + verificacion
        List<Mesa> mesas = assertDoesNotThrow(() -> {
           return dao.consultarMesasDisponibles();
        });
        assertNotNull(mesas.isEmpty());
    }
    
}
