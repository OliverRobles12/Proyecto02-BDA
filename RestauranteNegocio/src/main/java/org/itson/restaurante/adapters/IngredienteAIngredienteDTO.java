/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.restaurante.adapters;

import java.util.ArrayList;
import java.util.List;
import org.itson.restaurante.dominio.Ingrediente;
import org.itson.restaurante.dominio.UnidadMedida;
import org.itson.restaurante.dtos.IngredienteDTO;
import org.itson.restaurante.dtos.UnidadMedidaDTO;

/**
 *
 * @author joset
 */
public class IngredienteAIngredienteDTO {

    public static IngredienteDTO convertirADTO(Ingrediente ingrediente) {
        UnidadMedidaDTO unidadMedida = UnidadMedidaDTO.PIEZAS; 

        if (ingrediente.getUnidadMedida() == UnidadMedida.GRAMOS) {
            unidadMedida = UnidadMedidaDTO.GRAMOS;
        } else if (ingrediente.getUnidadMedida() == UnidadMedida.MILILITROS) {
            unidadMedida = UnidadMedidaDTO.MILILITROS;
        }

        return new IngredienteDTO(
                ingrediente.getId(),
                ingrediente.getNombre(),
                unidadMedida, 
                ingrediente.getStock(),
                ingrediente.getImagen()
        );
    }
     public static List<IngredienteDTO> convertirListaIngredientesADTO(List<Ingrediente> ingredientes) {
        List<IngredienteDTO> ingredientesDTO = new ArrayList<>();
        
        
        
        for (Ingrediente ingre : ingredientes) {
            UnidadMedidaDTO unidadMedida = UnidadMedidaDTO.PIEZAS; 

            if (ingre.getUnidadMedida() == UnidadMedida.GRAMOS) {
                unidadMedida = UnidadMedidaDTO.GRAMOS;
            } else if (ingre.getUnidadMedida() == UnidadMedida.MILILITROS) {
                unidadMedida = UnidadMedidaDTO.MILILITROS;
            }
            ingredientesDTO.add(new IngredienteDTO(
                    ingre.getId(),
                    ingre.getNombre(),
                    unidadMedida, 
                    ingre.getStock(),
                    ingre.getImagen()
            ));
        }
        return ingredientesDTO;
    }
}
