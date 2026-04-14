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
import org.itson.restaurante.persistencia.ClienteDAO;
import org.itson.restaurante.persistencia.IClienteDAO;
import org.itson.restaurante.persistencia.PersistenciaException;

/**
 *
 * @author juanl
 */
public class ClienteAReporteCliente {

    private IClienteDAO dao;

    public ClienteAReporteCliente() {
        this.dao = new ClienteDAO();
    }

    public LocalDate ultimaFecha(ClienteFrecuente c)
            throws PersistenciaException {

        return dao.consultarFechaUltimaComanda(c.getId());
    }

    public ReporteClienteDTO convertir(ClienteFrecuente c)
            throws PersistenciaException {

        if (c == null) {
            return null;
        }

        ReporteClienteDTO dto = new ReporteClienteDTO();

        dto.setNombre(c.getNombre() != null ? c.getNombre() : "Sin nombre");
        dto.setApellidoPaterno(c.getApellidoPaterno() != null ? c.getApellidoPaterno() : "");
        dto.setApellidoMaterno(c.getApellidoMaterno() != null ? c.getApellidoMaterno() : "");
        dto.setTelefono(c.getTelefono() != null ? c.getTelefono() : "No registrado");
        dto.setCorreo(c.getCorreo() != null ? c.getCorreo() : "Sin correo");
        dto.setFechaRegistro(c.getFechaRegistro() != null ? c.getFechaRegistro() : LocalDate.now());
        dto.setPuntosAcumulados(c.getPuntosAcumulados() != null ? c.getPuntosAcumulados() : 0);
        dto.setTotalGastado(c.getTotalGastado() != null ? c.getTotalGastado() : 0.0);
        dto.setNumeroVisitas(c.getNumeroVisitas() != null ? c.getNumeroVisitas() : 0);

        dto.setFechaUltimaComanda(ultimaFecha(c));

        return dto;
    }

    public List<ReporteClienteDTO> convertirLista(List<ClienteFrecuente> lista)
            throws PersistenciaException {

        List<ReporteClienteDTO> resultado = new ArrayList<>();

        for (ClienteFrecuente c : lista) {
            resultado.add(convertir(c));
        }

        return resultado;
    }
}