/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.restaurante.adapters;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.itson.restaurante.dominio.Cliente;
import org.itson.restaurante.dominio.ClienteFrecuente;
import org.itson.restaurante.dtos.ReporteClienteDTO;

/**
 *
 * @author juanl
 */
public class ClienteAReporteCliente {
    
    public static ReporteClienteDTO convertir(ClienteFrecuente c) {

        if (c == null) {
            return null;
        }

        ReporteClienteDTO dto = new ReporteClienteDTO();

        dto.setNombre(
                c.getNombre() != null ? c.getNombre() : "Sin nombre"
        );

        dto.setApellidoPaterno(
                c.getApellidoPaterno() != null ? c.getApellidoPaterno() : ""
        );

        dto.setApellidoMaterno(
                c.getApellidoMaterno() != null ? c.getApellidoMaterno() : ""
        );

        dto.setTelefono(
                c.getTelefono() != null ? c.getTelefono() : "No registrado"
        );

        dto.setFechaRegistro(
                c.getFechaRegistro() != null ? c.getFechaRegistro() : LocalDate.now()
        );

        return dto;
    }

    public static List<ReporteClienteDTO> convertirLista(List<ClienteFrecuente> lista) {

        List<ReporteClienteDTO> resultado = new ArrayList<>();

        for (ClienteFrecuente c : lista) {
            resultado.add(convertir(c));
        }

        return resultado;
    }
}
