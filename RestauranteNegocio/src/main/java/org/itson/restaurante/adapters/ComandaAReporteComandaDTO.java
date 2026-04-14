package org.itson.restaurante.adapters;

import java.util.ArrayList;
import java.util.List;
import org.itson.restaurante.dominio.Comanda;
import org.itson.restaurante.dtos.ReporteComandaDTO;

/**
 *
 * @author joset
 */

public class ComandaAReporteComandaDTO {

    public static ReporteComandaDTO convertir(Comanda c) {

        if (c == null) {
            return null;
        }

        ReporteComandaDTO dto = new ReporteComandaDTO();

        dto.setFolio(c.getFolio());

        dto.setFecha(c.getFechaHora());

        dto.setNumeroMesa(
                c.getMesa() != null ? c.getMesa().getNoMesa() : null
        );

        dto.setEstado(
                c.getEstado() != null ? c.getEstado().name() : "DESCONOCIDO"
        );

        dto.setTotal(
                c.getTotalAcumulado() != null ? c.getTotalAcumulado() : 0.0
        );

        dto.setNombreCliente(
                (c.getCliente() != null && c.getCliente().getNombre() != null)
                ? c.getCliente().getNombre()
                : "Mostrador"
        );

        return dto;
    }

    public static List<ReporteComandaDTO> convertirLista(List<Comanda> lista) {

        List<ReporteComandaDTO> resultado = new ArrayList<>();

        for (Comanda c : lista) {
            resultado.add(convertir(c));
        }

        return resultado;
    }
}
