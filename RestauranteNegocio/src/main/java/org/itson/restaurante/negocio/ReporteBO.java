package org.itson.restaurante.negocio;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.itson.restaurante.adapters.ComandaAReporteComandaDTO;
import org.itson.restaurante.dominio.Comanda;
import org.itson.restaurante.dtos.ReporteComandaDTO;
import org.itson.restaurante.persistencia.ComandaDAO;
import org.itson.restaurante.persistencia.IComandaDAO;
import org.itson.restaurante.persistencia.PersistenciaException;

public class ReporteBO implements IReporteBO {

    private IComandaDAO comandaDAO;

    public ReporteBO() {
        this.comandaDAO = new ComandaDAO();
    }

    @Override
    public JasperPrint generarReporteComandas(LocalDate inicio, LocalDate fin) throws NegocioException {
        try {
            // 1. Obtener datos
            List<Comanda> lista = comandaDAO.consultarComandas();
            List<ReporteComandaDTO> listaDTO = ComandaAReporteComandaDTO.convertirLista(lista);

            // 2. Formato de fechas
            DateTimeFormatter formatoSimple = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // 3. Parámetros
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("fechaInicio", inicio.format(formatoSimple));
            parametros.put("fechaFin", fin.format(formatoSimple));

            // 4. DataSource
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaDTO);

            // 5. Cargar reporte DESDE resources (IMPORTANTE)
            InputStream reporte = getClass().getResourceAsStream("/Comandas.jrxml");

            if (reporte == null) {
                throw new NegocioException("No se encontró el archivo ReporteComandas.jrxml en resources", null);
            }

            // 6. Compilar
            JasperReport report = JasperCompileManager.compileReport(reporte);

            // 7. Llenar reporte
            return JasperFillManager.fillReport(report, parametros, dataSource);

        } catch (PersistenciaException | JRException ex) {
            ex.printStackTrace();
            throw new NegocioException("No se pudo generar el reporte: " + ex.getMessage(), ex);
        }
    }
}