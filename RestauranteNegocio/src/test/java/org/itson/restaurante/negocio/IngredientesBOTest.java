package org.itson.restaurante.negocio;

import java.util.List;
import org.itson.restaurante.dtos.IngredienteDTO;
import org.itson.restaurante.dtos.NuevoIngredienteDTO;
import org.itson.restaurante.dtos.UnidadMedidaDTO;
import org.itson.restaurante.persistencia.IngredientesDAO;
import org.itson.restaurante.persistencia.PersistenciaException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author joset
 */
public class IngredientesBOTest {

    private IngredientesBO bo;
    private IngredientesDAO dao;
    private Long idIngredientePrueba;

    public IngredientesBOTest() {
    }

    @BeforeEach
    public void setup() {
        bo = new IngredientesBO();
        dao = new IngredientesDAO();

        NuevoIngredienteDTO ingredientePrueba = new NuevoIngredienteDTO(
                "Ingrediente Base Test",
                UnidadMedidaDTO.GRAMOS,
                100,
                null
        );

        try {
            IngredienteDTO ingredienteRegistrado = bo.registrarIngrediente(ingredientePrueba);
            this.idIngredientePrueba = ingredienteRegistrado.getId();
        } catch (NegocioException ex) {
            Assertions.fail("No se pudo generar el ingrediente de prueba: " + ex.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        if (this.idIngredientePrueba != null) {
            try {
                dao.eliminarIngrediente(this.idIngredientePrueba);
            } catch (PersistenciaException ex) {
                System.err.println("No se pudo limpiar el ingrediente de prueba: " + ex.getMessage());
            }
        }
    }

    @Test
    public void testRegistrarIngredienteFuncionaOk() {
        NuevoIngredienteDTO nuevoIngrediente = new NuevoIngredienteDTO(
                "Harina Test BO",
                UnidadMedidaDTO.GRAMOS,
                500,
                null
        );

        IngredienteDTO ingrediente = Assertions.assertDoesNotThrow(() -> {
            return bo.registrarIngrediente(nuevoIngrediente);
        });

        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertNotNull(ingrediente);
            Assertions.assertNotNull(ingrediente.getId());
            Assertions.assertEquals("Harina Test BO", ingrediente.getNombre());
            Assertions.assertEquals(500, ingrediente.getStock());
            dao.eliminarIngrediente(ingrediente.getId());
        });
    }

    @Test
    public void testRegistrarIngredienteNuloLanzaExcepcion() {
        Assertions.assertThrows(NegocioException.class, () -> {
            bo.registrarIngrediente(null);
        });
    }

    @Test
    public void testRegistrarIngredienteNombreVacioLanzaExcepcion() {
        NuevoIngredienteDTO nuevoIngrediente = new NuevoIngredienteDTO(
                "",
                UnidadMedidaDTO.GRAMOS,
                100,
                null
        );

        Assertions.assertThrows(NegocioException.class, () -> {
            bo.registrarIngrediente(nuevoIngrediente);
        });
    }

    @Test
    public void testRegistrarIngredienteNombreConNumerosLanzaExcepcion() {
        NuevoIngredienteDTO nuevoIngrediente = new NuevoIngredienteDTO(
                "Harina123",
                UnidadMedidaDTO.GRAMOS,
                100,
                null
        );

        Assertions.assertThrows(NegocioException.class, () -> {
            bo.registrarIngrediente(nuevoIngrediente);
        });
    }

    @Test
    public void testRegistrarIngredienteUnidadNulaLanzaExcepcion() {
        NuevoIngredienteDTO nuevoIngrediente = new NuevoIngredienteDTO(
                "Azucar Test",
                null,
                100,
                null
        );

        Assertions.assertThrows(NegocioException.class, () -> {
            bo.registrarIngrediente(nuevoIngrediente);
        });
    }

    @Test
    public void testRegistrarIngredienteStockNegativoLanzaExcepcion() {
        NuevoIngredienteDTO nuevoIngrediente = new NuevoIngredienteDTO(
                "Azucar Test",
                UnidadMedidaDTO.GRAMOS,
                -10,
                null
        );

        Assertions.assertThrows(NegocioException.class, () -> {
            bo.registrarIngrediente(nuevoIngrediente);
        });
    }

    @Test
    public void testActualizarIngredienteFuncionaOk() throws NegocioException {
        IngredienteDTO ingredienteOriginal = bo.consultarIngredientesFiltro("Ingrediente Base Test").get(0);

        IngredienteDTO ingredienteActualizado = new IngredienteDTO(
                ingredienteOriginal.getId(),
                "Ingrediente Actualizado BO",
                ingredienteOriginal.getUnidadMedida(),
                250,
                ingredienteOriginal.getImagen()
        );

        IngredienteDTO actualizado = Assertions.assertDoesNotThrow(() -> {
            return bo.actualizarIngrediente(ingredienteActualizado);
        });

        Assertions.assertNotNull(actualizado);
        Assertions.assertEquals("Ingrediente Actualizado BO", actualizado.getNombre());
        Assertions.assertEquals(250, actualizado.getStock());
    }

    @Test
    public void testActualizarIngredienteNuloLanzaExcepcion() {
        Assertions.assertThrows(NegocioException.class, () -> {
            bo.actualizarIngrediente(null);
        });
    }

    @Test
    public void testActualizarIngredienteSinIdLanzaExcepcion() {
        IngredienteDTO ingrediente = new IngredienteDTO(
                null,
                "Ingrediente Sin ID",
                UnidadMedidaDTO.GRAMOS,
                100,
                null
        );

        Assertions.assertThrows(NegocioException.class, () -> {
            bo.actualizarIngrediente(ingrediente);
        });
    }

    @Test
    public void testActualizarIngredienteNombreVacioLanzaExcepcion() throws NegocioException {
        IngredienteDTO ingredienteOriginal = bo.consultarIngredientesFiltro("Ingrediente Base Test").get(0);

        IngredienteDTO ingredienteInvalido = new IngredienteDTO(
                ingredienteOriginal.getId(),
                "",
                ingredienteOriginal.getUnidadMedida(),
                ingredienteOriginal.getStock(),
                ingredienteOriginal.getImagen()
        );

        Assertions.assertThrows(NegocioException.class, () -> {
            bo.actualizarIngrediente(ingredienteInvalido);
        });
    }

    @Test
    public void testActualizarIngredienteNombreConNumerosLanzaExcepcion() throws NegocioException {
        IngredienteDTO ingredienteOriginal = bo.consultarIngredientesFiltro("Ingrediente Base Test").get(0);

        IngredienteDTO ingredienteInvalido = new IngredienteDTO(
                ingredienteOriginal.getId(),
                "Ingrediente123",
                ingredienteOriginal.getUnidadMedida(),
                ingredienteOriginal.getStock(),
                ingredienteOriginal.getImagen()
        );

        Assertions.assertThrows(NegocioException.class, () -> {
            bo.actualizarIngrediente(ingredienteInvalido);
        });
    }

    @Test
    public void testActualizarIngredienteStockNegativoLanzaExcepcion() throws NegocioException {
        IngredienteDTO ingredienteOriginal = bo.consultarIngredientesFiltro("Ingrediente Base Test").get(0);

        IngredienteDTO ingredienteInvalido = new IngredienteDTO(
                ingredienteOriginal.getId(),
                ingredienteOriginal.getNombre(),
                ingredienteOriginal.getUnidadMedida(),
                -50,
                ingredienteOriginal.getImagen()
        );

        Assertions.assertThrows(NegocioException.class, () -> {
            bo.actualizarIngrediente(ingredienteInvalido);
        });
    }

    @Test
    public void testConsultarIngredientesFuncionaOk() {
        List<IngredienteDTO> ingredientes = Assertions.assertDoesNotThrow(() -> {
            return bo.consultarIngredientes();
        });

        Assertions.assertNotNull(ingredientes);
        Assertions.assertFalse(ingredientes.isEmpty());
    }

    @Test
    public void testConsultarIngredientesFiltroFuncionaOk() {
        List<IngredienteDTO> ingredientes = Assertions.assertDoesNotThrow(() -> {
            return bo.consultarIngredientesFiltro("Ingrediente Base Test");
        });

        Assertions.assertNotNull(ingredientes);
        Assertions.assertFalse(ingredientes.isEmpty());
    }

    @Test
    public void testConsultarIngredientesFiltroVacioDevuelveTodos() {
        List<IngredienteDTO> ingredientes = Assertions.assertDoesNotThrow(() -> {
            return bo.consultarIngredientesFiltro("");
        });

        Assertions.assertNotNull(ingredientes);
        Assertions.assertFalse(ingredientes.isEmpty());
    }

    @Test
    public void testConsultarIngredientesFiltroSinResultados() {
        List<IngredienteDTO> ingredientes = Assertions.assertDoesNotThrow(() -> {
            return bo.consultarIngredientesFiltro("xyzingredientequenoexiste");
        });

        Assertions.assertNotNull(ingredientes);
        Assertions.assertTrue(ingredientes.isEmpty());
    }

    @Test
    public void testEliminarIngredienteFuncionaOk() throws NegocioException {
        NuevoIngredienteDTO nuevoIngrediente = new NuevoIngredienteDTO(
                "Ingrediente Eliminar BO",
                UnidadMedidaDTO.GRAMOS,
                0,
                null
        );

        IngredienteDTO ingredienteRegistrado = bo.registrarIngrediente(nuevoIngrediente);

        IngredienteDTO eliminado = Assertions.assertDoesNotThrow(() -> {
            return bo.eliminarIngrediente(ingredienteRegistrado);
        });

        Assertions.assertNotNull(eliminado);
        Assertions.assertEquals(ingredienteRegistrado.getId(), eliminado.getId());
    }


}
