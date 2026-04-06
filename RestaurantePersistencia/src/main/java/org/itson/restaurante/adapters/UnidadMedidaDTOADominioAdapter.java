/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.restaurante.adapters;

import org.itson.restaurante.dominio.UnidadMedida;
import org.itson.restaurante.dtos.UnidadMedidaDTO;

/**
 *
 * @author joset
 */
public class UnidadMedidaDTOADominioAdapter {

    public static UnidadMedida convertirUnidadMedida(UnidadMedidaDTO unidadMedida) {
        UnidadMedida unidadDominio = UnidadMedida.PIEZAS;
        if (unidadMedida == UnidadMedidaDTO.GRAMOS) {
            unidadDominio = UnidadMedida.GRAMOS;
        } else if (unidadMedida == UnidadMedidaDTO.MILILITROS) {
            unidadDominio = UnidadMedida.MILILITROS;
        }
        return unidadDominio;
    }
}
