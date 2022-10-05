package com.retos.bombapp.constants;

import java.util.TreeMap;

import lombok.Getter;

public class ConstantesParametros {

    private ConstantesParametros() {

    }

    // CONFIGURACIÓN INICIAL
    public static final String EMAIL_ADMIN = "admin@gmail.com";
    public static final String NAMES_ADMIN = "Administrador";
    public static final String LASTNAMES_ADMIN = "General";
    public static final String CELLPHONE_ADMIN = "1010101010";
    public static final String PASSWORD_ADMIN = "bdryTQ7ADTJzT8Pzsy+mUCOC0hiaTbv5LBISRe3iJwKfPaCD2v73r6X0dWNiNQ6i";
    public static final String TYPE_CONFIG_INITIAL = "CONFIGURACION_INICIAL";
    public static final String NAME_CONFIG_INITIAL = "Configuración inicial";
    public static final String CODE_CONFIG_INITIAL = "CICON";
    // FIN CONFIGURACIÓN INICIAL

    // TIPOS
    public static final String TYPE_POSITION = "CARGO";
    public static final String TYPE_TYPE_COLLABORATOR = "TIPO_COLABORADOR";
    public static final String TYPE_STATUS_COLLABORATOR = "ESTADO_COLABORADOR";
    public static final String TYPE_COUNTRY = "PAIS";
    public static final String TYPE_TECHNIQUE_PROJECT = "TECNICAS_PROYECTO";
    public static final String TYPE_PROJECT_TYPE = "TIPO_PROYECTO";
    public static final String TYPE_FRAME_RATE = "FRAME_RATE";
    public static final String TYPE_STAGE = "ETAPA";
    public static final String TYPE_STATUS_PROJECT = "ESTADO_PROYECTO";
    public static final String TYPE_STATUS_ASSIGNMENT = "ESTADO_ASIGNACION";
    public static final String TYPE_STATUS_ELEMENT = "ESTADO_ELEMENTO";
    // FIN TIPO

    // ROLES
    @Getter
    private static final String[] namesRoles = {NAMES_ADMIN, "Director", "Productor", "Artista"};

    @Getter
    private static final String[] codesRoles = {"ADM", "DIR", "PRD", "ART"};

    @Getter
    private static final boolean[] visibilitiesRoles = {false, true, true, true};
    // FIN ROLES

    // FUNCIONALIDADES
    @Getter
    private static final String[] namesFunctionalities = {
        "Proyectos", "Asignaciones", "Elementos", "Revisiones", "Reportes", "Colaboradores", "Roles",
        "Clientes", "Parámetros"
    };

    @Getter
    private static final String[] codesFunctionalities = {
        "PRO", "ASG", "ELM", "REV", "REP", "COL", "ROL", "CLI", "PAR"
    };
    // FIN FUNCIONALIDADES

    @Getter
    private static final String[] namesParameter = {
        NAME_CONFIG_INITIAL,
        "Activo", "Inactivo",
        "12", "24", "25", "29.97"
    };

    @Getter
    private static final String[] codesParameter = {
        CODE_CONFIG_INITIAL,
        "A", "I",
        "FRRA01", "FRRA02", "FRRA03", "FRRA04"
    };

    @Getter
    private static final String[] typesParameter = {
        TYPE_CONFIG_INITIAL,
        TYPE_STATUS_COLLABORATOR, TYPE_STATUS_COLLABORATOR,
        TYPE_FRAME_RATE, TYPE_FRAME_RATE, TYPE_FRAME_RATE, TYPE_FRAME_RATE
    };
    // FIN PARÁMETROS ESTÁTICOS

    // PARÁMETROS ADICIONALES
    @Getter
    private static final String[] namesTypeUser = {
        Constantes.TYPE_USER_PLANT, Constantes.TYPE_USER_TEMPORARY
    };

    @Getter
    private static final String[] namesCountries = {
        "Argentina", "Bolivia", "Brasil", "Chile", "Colombia", "Costa Rica", "Cuba", "Ecuador",
        "El Salvador", "Guatemala",
        "Honduras", "Jamaica", "México", "Nicaragua", "Paraguay", "Panamá", "Perú", "Puerto Rico",
        "República Dominicana",
        "Uruguay", "Venezuela"
    };

    @Getter
    private static final String[] namesTechniqueProject = {
        "Tradicional", "Tradigital", "Cut Out", "Stop Motion", "Rotoscopia", "3D", "Mixta", "Otros"
    };

    @Getter
    private static final String[] namesTypeProject = {
        "Largometraje", "Cortometraje", "Serie animada", "Comercial", "Infografía", "Video corporativo"
    };

    @Getter
    private static final String[] namesStatusProject = {
        Constantes.STATUS_PROJECT_EXPIRED,
        Constantes.STATUS_PROJECT_PROCESS,
        Constantes.STATUS_PROJECT_COMPLETED,
        Constantes.STATUS_ASSIGNMENTLESS,};

    @Getter
    private static final String[] namesStatusAssignments = {
        Constantes.STATUS_ASSIGNMENT_TO_START,
        Constantes.STATUS_ASSIGNMENT_PROCESS,
        Constantes.STATUS_ASSIGNMENT_REVIEW,
        Constantes.STATUS_ASSIGNMENT_SETTINGS,
        Constantes.STATUS_ASSIGNMENT_COMPLETED
    };

    @Getter
    private static final String[] namesStages = {
        "Pre-producción", "Producción", "Post-producción"
    };
    
    @Getter
    private static final String[] namesStatusElements = {
        "Revisado", "Con revisiones"
    };
    // FIN PARÁMETROS ADICIONALES

    // TREE MAP ADICIONALES
    @Getter
    private static final TreeMap<String, String[]> additionals = new TreeMap<>();

    public static final void setAdditionals() {
        additionals.put(TYPE_TYPE_COLLABORATOR, getNamesTypeUser());
        additionals.put(TYPE_COUNTRY, getNamesCountries());
        additionals.put(TYPE_TECHNIQUE_PROJECT, getNamesTechniqueProject());
        additionals.put(TYPE_PROJECT_TYPE, getNamesTypeProject());
        additionals.put(TYPE_STATUS_PROJECT, getNamesStatusProject());
        additionals.put(TYPE_STAGE, getNamesStages());
        additionals.put(TYPE_STATUS_ASSIGNMENT, getNamesStatusAssignments());
        additionals.put(TYPE_STATUS_ELEMENT, getNamesStatusElements());
    }
    // FIN TREE MAP ADICIONALES
}
