package com.retos.bombapp.constants;

/**
 * Constantes generales del aplicativo
 */
public class Constantes {
    private Constantes() {
    }

    public static final String TOKEN_EXPIRED = "Acceso no autorizado, el token ha expirado.";
    public static final String TOKEN_INVALID = "Acceso no autorizado, el token es inválido.";
    public static final String NO_TOKEN_PROVIDE = "Acceso no autorizado, el token no se ha proporcionado.";
    public static final String TOKEN_ATTRIBUTE = "tokenAttr";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    public static final String CHARSET_UTF_8 = "UTF-8";
    public static final String AUTHORIZATION = "Authorization";
    public static final String PASS_AUTO_GENERATE = "Generación automática de contraseña";
    public static final String PASS_RECOVERY = "Recuperación de contraseña";
    public static final String ACTIVE = "A";
    public static final String INACTIVE = "I";
    public static final String BEARER_AUTHENTICATION = "Bearer Authentication";
    public static final String TYPE_USER_PLANT = "DE PLANTA";
    public static final String TYPE_USER_TEMPORARY = "TEMPORAL";
    public static final String FORMAT_DATE_ONE = "yyyy-MM-dd";
    public static final String FORMAT_DATE_TWO = "yyyy-MM-dd hh:mm:ss";
    public static final String FORMAT_DATE_THREE = "dd-MM-yyyy";
    public static final String STATUS_PROJECT_EXPIRED = "VENCIDO";
    public static final String STATUS_PROJECT_PROCESS = "EN PROCESO";
    public static final String STATUS_PROJECT_COMPLETED = "FINALIZADO";
    public static final String STATUS_ASSIGNMENTLESS = "SIN ASIGNACIONES";
    public static final String STATUS_ASSIGNMENT_TO_START = "SIN INICIAR";
    public static final String STATUS_ASSIGNMENT_PROCESS = "EN PROCESO";
    public static final String STATUS_ASSIGNMENT_REVIEW = "EN REVISIÓN";
    public static final String STATUS_ASSIGNMENT_SETTINGS = "CON AJUSTES";
    public static final String STATUS_ASSIGNMENT_COMPLETED = "FINALIZADO";
    public static final String FORMAT_DECIMAL_ONE = "#.#";
    public static final String TEMPLATE_ASSIGNMENT_REPORT = "reporte-asignaciones";
}
