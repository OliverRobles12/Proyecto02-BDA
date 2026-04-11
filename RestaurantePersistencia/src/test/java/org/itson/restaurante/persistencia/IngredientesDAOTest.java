package org.itson.restaurante.persistencia;

import java.util.List;
import org.itson.restaurante.dominio.Ingrediente;
import org.itson.restaurante.dominio.Producto;
import org.itson.restaurante.dominio.ProductoIngrediente;
import org.itson.restaurante.dtos.IngredienteActualizadoDTO;
import org.itson.restaurante.dtos.IngredienteDTO;
import org.itson.restaurante.dtos.NuevoIngredienteDTO;
import org.itson.restaurante.dtos.UnidadMedidaDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author joset
 */
public class IngredientesDAOTest {

    private IngredientesDAO dao;
    private Long idIngrediente;

    public IngredientesDAOTest() {
    }

    @BeforeEach
    public void setup() {
        dao = new IngredientesDAO();

        NuevoIngredienteDTO ingredientePrueba = new NuevoIngredienteDTO(
                "Ingrediente Test",
                UnidadMedidaDTO.GRAMOS,
                100,
                null
        );

        try {
            Ingrediente ingrediente = dao.registrarIngrediente(ingredientePrueba);
            this.idIngrediente = ingrediente.getId();
        } catch (Exception ex) {
            Assertions.fail("No se pudo generar el ingrediente de prueba: " + ex.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        if (this.idIngrediente != null) {
            try {
                dao.eliminarIngrediente(this.idIngrediente);
            } catch (Exception ex) {
                System.err.println("No se pudo limpiar el ingrediente de prueba: " + ex.getMessage());
            }
        }
    }

    @Test
    public void testRegistrarIngredienteFuncionaOk() {
        NuevoIngredienteDTO nuevoIngrediente = new NuevoIngredienteDTO(
                "Harina",
                UnidadMedidaDTO.GRAMOS,
                500,
                null
        );

        Ingrediente ingrediente = Assertions.assertDoesNotThrow(() -> {
            return dao.registrarIngrediente(nuevoIngrediente);
        });

        Assertions.assertNotNull(ingrediente.getId());

        Assertions.assertDoesNotThrow(() -> {
            dao.eliminarIngrediente(ingrediente.getId());
        });
    }

    @Test
    public void testActualizarIngredienteFuncionaOk() {
        IngredienteActualizadoDTO ingredienteActualizado = new IngredienteActualizadoDTO(
                idIngrediente,
                "Ingrediente Actualizado",
                200,
                null
        );

        Ingrediente ingrediente = Assertions.assertDoesNotThrow(() -> {
            return dao.actualizarIngrediente(ingredienteActualizado);
        });

        Assertions.assertEquals("Ingrediente Actualizado", ingrediente.getNombre());
        Assertions.assertEquals(200, ingrediente.getStock());
    }

    @Test
    public void testConsultarIngredientesFuncionaOk() {
        List<Ingrediente> ingredientes = Assertions.assertDoesNotThrow(() -> {
            return dao.consultarIngredientes();
        });

        Assertions.assertFalse(ingredientes.isEmpty());
    }

    @Test
    public void testConsultarIngredientesFiltroFuncionaOk() {
        List<Ingrediente> ingredientes = Assertions.assertDoesNotThrow(() -> {
            return dao.consultarIngredientesFiltro("Ingrediente Test");
        });

        Assertions.assertFalse(ingredientes.isEmpty());
    }

    @Test
    public void testConsultarIngredientesFiltroVacioDevuelveTodos() {
        List<Ingrediente> ingredientes = Assertions.assertDoesNotThrow(() -> {
            return dao.consultarIngredientesFiltro("");
        });

        Assertions.assertFalse(ingredientes.isEmpty());
    }

    @Test
    public void testConsultarIngredientesFiltroSinResultados() {
        List<Ingrediente> ingredientes = Assertions.assertDoesNotThrow(() -> {
            return dao.consultarIngredientesFiltro("xyzingredientequenoexiste");
        });

        Assertions.assertTrue(ingredientes.isEmpty());
    }
}
