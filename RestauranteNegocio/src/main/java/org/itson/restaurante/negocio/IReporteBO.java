/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.restaurante.negocio;

import java.time.LocalDate;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author joset
 */

public interface IReporteBO {
    public abstract JasperPrint generarReporteComandas(LocalDate inicio, LocalDate fin) throws Exception;
    
    public abstract JasperPrint generarReporteCliente(String nombreBusqueda) throws Exception;
}