package org.itson.restaurante.adapters;

/**
 *
 * @author joset
 */
import org.itson.restaurante.dominio.Ingrediente;
import org.itson.restaurante.dominio.UnidadMedida;
import org.itson.restaurante.dtos.NuevoIngredienteDTO;
import org.itson.restaurante.dtos.UnidadMedidaDTO;

public class IngredienteDTOADominioAdapter {

    public static Ingrediente adapter(NuevoIngredienteDTO nuevoIngrediente) {
        
        UnidadMedida unidadMedida = UnidadMedida.PIEZAS;
        if (nuevoIngrediente.getUnidadMedida() == UnidadMedidaDTO.GRAMOS) {
            unidadMedida = UnidadMedida.GRAMOS;
        } else if (nuevoIngrediente.getUnidadMedida() == UnidadMedidaDTO.MILILITROS) {
            unidadMedida = UnidadMedida.MILILITROS;
        }

        Ingrediente ingrediente = new Ingrediente(
            nuevoIngrediente.getNombre(),
            unidadMedida,
            nuevoIngrediente.getStock()
        );

        if (nuevoIngrediente.getImagen() != null) {
            ingrediente.setImagen(nuevoIngrediente.getImagen());
        }

        return ingrediente;
    }
}