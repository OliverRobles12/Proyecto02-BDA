package org.itson.restaurante.negocio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.itson.restaurante.adapters.ClienteAReporteCliente;
import org.itson.restaurante.adapters.ComandaAReporteComandaDTO;
import org.itson.restaurante.dominio.ClienteFrecuente;
import org.itson.restaurante.dominio.Comanda;
import org.itson.restaurante.dtos.ReporteClienteDTO;
import org.itson.restaurante.dtos.ReporteComandaDTO;
import org.itson.restaurante.persistencia.ClienteDAO;
import org.itson.restaurante.persistencia.ComandaDAO;
import org.itson.restaurante.persistencia.IClienteDAO;
import org.itson.restaurante.persistencia.IComandaDAO;
import org.itson.restaurante.persistencia.PersistenciaException;

public class ReporteBO implements IReporteBO {

    private IComandaDAO comandaDAO;
    private IClienteDAO clienteDAO;

    public ReporteBO() {
        this.comandaDAO = new ComandaDAO();
        this.clienteDAO = new ClienteDAO();
    }

    @Override
    public JasperPrint generarReporteComandas(LocalDate inicio, LocalDate fin) throws NegocioException {
        try {
            List<Comanda> lista = comandaDAO.consultarComandas();
            List<ReporteComandaDTO> listaDTO = ComandaAReporteComandaDTO.convertirLista(lista);

            DateTimeFormatter formatoSimple = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("fechaInicio", inicio.format(formatoSimple));
            parametros.put("fechaFin", fin.format(formatoSimple));

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaDTO);

            JasperReport report = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reportes/comandas.jrxml"));

            return JasperFillManager.fillReport(report, parametros, dataSource);

        } catch (PersistenciaException | JRException ex) {
            ex.printStackTrace();
            throw new NegocioException("No se pudo generar el reporte: " + ex.getMessage(), ex);
        }
    }

    @Override
    public JasperPrint generarReporteCliente(String nombreBusqueda) throws NegocioException {
        try {
            List<ClienteFrecuente> lista = clienteDAO.consultarClientesFrecuentesFiltro(nombreBusqueda);
            ClienteAReporteCliente mapper = new ClienteAReporteCliente();
            List<ReporteClienteDTO> reporte = mapper.convertirLista(lista);

            Map<String, Object> parametros = new HashMap<>();

            parametros.put("filtroNombre", (nombreBusqueda == null || nombreBusqueda.isEmpty())
                    ? "Todos los clientes"
                    : nombreBusqueda);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reporte);
            JasperReport report = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/reportes/clienteFrecuentes.jrxml")
            );

            return JasperFillManager.fillReport(report, parametros, dataSource);

        } catch (PersistenciaException | JRException ex) {
            ex.printStackTrace();
            throw new NegocioException(
                    "No se pudo generar el reporte para: " + nombreBusqueda + ". " + ex.getMessage(), ex
            );
        }
    }
}
