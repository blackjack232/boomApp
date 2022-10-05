package com.retos.bombapp.services.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;
import com.retos.bombapp.constants.Constantes;
import com.retos.bombapp.entities.Parametros;
import com.retos.bombapp.entities.Proyectos;
import com.retos.bombapp.models.PdfDTO;
import com.retos.bombapp.models.ReporteAsignacionDTO;
import com.retos.bombapp.repositories.ParametroRepository;
import com.retos.bombapp.repositories.ProyectoRepository;
import com.retos.bombapp.services.GenerarPDFService;
import com.retos.bombapp.services.ReporteAsignacionService;
import com.retos.bombapp.utils.UtilGeneral;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GenerarPDFServiceImpl implements GenerarPDFService {
    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private ReporteAsignacionService reporteAsignacionService;

    @Autowired
    private ParametroRepository parametroRepository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Override
    public String generatePdf(String templateName, Map<String, Object> data) {
        Context context = new Context();
        context.setVariables(data);

        String htmlContent = templateEngine.process(templateName, context);

        String base64 = null;
        // try {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            // OutputStream outputStream = new FileOutputStream("D://reporte.pdf");
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(baos, false);
            // renderer.createPDF(outputStream, false);
            renderer.finishPDF();
            // outputStream.close();
            base64 = UtilGeneral.convertBytesToBase64(baos.toByteArray());
        } catch (DocumentException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return base64;
    }

    @Override
    public PdfDTO reporteAsignacionesPdf(ReporteAsignacionDTO reporteAsignacionDTO) {
        List<ReporteAsignacionDTO> list = reporteAsignacionService.filterAllReportByProject(reporteAsignacionDTO);

        String nameProject = getNameProject(reporteAsignacionDTO.getProjectId());
        String nameStage = getNameParameterStage(reporteAsignacionDTO.getStageId());
        String creationDate = UtilGeneral.convertDateToString(new Date(), Constantes.FORMAT_DATE_THREE);

        Map<String, Object> map = new TreeMap<>();
        map.put("asignaciones", list);
        map.put("proyecto", nameProject);
        map.put("etapa", nameStage);
        map.put("fechaCreacion", creationDate);
        map.put("progreso", calculateProgressStage(list));

        String base64 = generatePdf(Constantes.TEMPLATE_ASSIGNMENT_REPORT, map);

        return PdfDTO.builder()
                .name(generateFileName(nameProject, nameStage, creationDate))
                .file(base64)
                .creationDate(creationDate)
                .build();
    }

    // ------------------------------PRIVADOS-----------------------------

    /**
     * Obtiene el nombre del parámetro de la etapa
     * 
     * @param id Long
     * @return String
     */
    private String getNameParameterStage(Long id) {
        Parametros param = parametroRepository.findById(id).orElse(null);
        return Objects.nonNull(param) ? UtilGeneral.toCapitalize(param.getName()) : null;
    }

    /**
     * Obtiene el nombre del proyecto
     * 
     * @param id Long
     * @return String
     */
    private String getNameProject(Long id) {
        Proyectos project = proyectoRepository.findById(id).orElse(null);
        return Objects.nonNull(project) ? UtilGeneral.toCapitalize(project.getName()) : null;
    }

    /**
     * Calcula el progreso de la etapa Formular => (Finalizadas / Asignadas) * 100
     * 
     * @param list List<ReporteAsignacionDTO>
     * @return String
     */
    private String calculateProgressStage(List<ReporteAsignacionDTO> list) {
        int count = 0;
        for (ReporteAsignacionDTO r : list) {
            if (r.getStatus().equalsIgnoreCase(Constantes.STATUS_ASSIGNMENT_COMPLETED)) {
                count++;
            }
        }

        double percentage = (count != 0) ? ((count * 100) / list.size()) : 0;

        return UtilGeneral.formatDecimal(percentage, Constantes.FORMAT_DECIMAL_ONE) + "%";
    }

    /**
     * Genera el nombre del archivo pdf
     * 
     * @param nameProject  String
     * @param nameStage    String
     * @param creationDate String
     * @return String
     */
    private String generateFileName(String nameProject, String nameStage, String creationDate) {
        String separator = "_";
        String ext = ".pdf";
        return nameProject.replaceAll("\\s", "_") + separator + nameStage + separator + creationDate + ext;
    }
}
