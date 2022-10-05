package com.retos.bombapp.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Enumeradores para respuestas y errores de los servicios
 */
@Getter
@AllArgsConstructor
public enum CodigoRespuestaApi {
    // GENERALES
    ERROR_UTILS("GEN001", "Clase utilitaria", HttpStatus.INTERNAL_SERVER_ERROR),
    OK("GEN002", "OK", HttpStatus.OK),
    INTERNAL_SERVER_ERROR("GEN003", "Error interno grave.", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_AUTHORIZED("GEN004", "Acceso no autorizado.", HttpStatus.UNAUTHORIZED),
    // FIN GENERALES

    // LOGIN
    NO_UPDATE_PASS("LOG001", "No se pudo actualizar la contraseña.", HttpStatus.INTERNAL_SERVER_ERROR),
    CREDENTIALS_INCORRECT("LOG002", "Credenciales incorrectas.", HttpStatus.UNAUTHORIZED),
    NO_ROLES_ASSOCIATE("LOG003", "El usuario no posee roles asociados.", HttpStatus.UNAUTHORIZED),
    USER_INACTIVE("LOG004", "El usuario está inactivo en el sistema.", HttpStatus.BAD_REQUEST),
    // FIN LOGIN

    // USUARIOS
    USER_NOT_FOUND("USU001", "Usuario no encontrado.", HttpStatus.NOT_FOUND),
    NO_INSERT_USER("USU002", "No se pudo insertar el usuario.", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_UPDATE_USER("USU003", "No se pudo actualizar el usuario.", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_DELETE_USER("USU004", "No se pudo eliminar el usuario.", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_UPDATE_STATUS_USER("USU005", "No se pudo actualizar el estado del usuario.", HttpStatus.INTERNAL_SERVER_ERROR),
    CHANGE_STATUS_USER_OK("USU006", "Se ha cambiado el estado del usuario exitosamente.", HttpStatus.OK),
    CHANGE_PASS_OK("USU007", "Se ha cambiado la contraseña exitosamente.", HttpStatus.OK),
    // FIN USUARIOS

    // EMAIL
    ERROR_SEND_EMAIL("EMA001", "Error al enviar el correo electrónico.", HttpStatus.INTERNAL_SERVER_ERROR),
    EMAIL_NOT_FOUND("EMA002", "Email no encontrado.", HttpStatus.NOT_FOUND),
    SEND_EMAIL_PASS_OK("EMA003", "Se ha enviado la contraseña al correo exitosamente.", HttpStatus.OK),
    // FIN EMAIL

    // RESPUESTAS GENERALES SERVICIOS
    INSERT_OK("SER001", "Se ha insertado correctamente.", HttpStatus.OK),
    DELETE_OK("SER002", "Se ha eliminado correctamente.", HttpStatus.OK),
    UPDATE_OK("SER003", "Se ha actualizado correctamente.", HttpStatus.OK),
    // FIN RESPUESTAS GENERALES SERVICIOS

    // CLIENTE
    NO_INSERT_CLIENT("CLI001", "No se pudo insertar el cliente.", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_UPDATE_CLIENT("CLI002", "No se pudo actualizar el cliente.", HttpStatus.NOT_FOUND),
    CLIENT_NOT_FOUND("CLI003", "Cliente no encontrado.", HttpStatus.NOT_FOUND),
    CLIENT_CHANGE_STATUS_OK("CLI004", "Se ha cambiado el estado del cliente correctamente.", HttpStatus.OK),
    CLIENT_CHANGE_STATUS_ERROR("CLI005", "No se pudo cambiar el estado del cliente.", HttpStatus.INTERNAL_SERVER_ERROR),
    // FIN CLIENTE

    // PARAMETROS
    NO_INSERT_PARAMETER("PAR001", "No se pudo insertar el parámetro.", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_UPDATE_PARAMETER("PAR002", "No se pudo actualizar el parámetro.", HttpStatus.INTERNAL_SERVER_ERROR),
    PARAMETER_NOT_FOUND("PAR003", "Parámetro no encontrado.", HttpStatus.NOT_FOUND),
    PARAMETER_CHANGE_STATUS_OK("PAR004", "Se ha cambiado el estado del parámetro correctamente.", HttpStatus.OK),
    PARAMETER_CHANGE_STATUS_ERROR("PAR005", "No se pudo cambiar el estado del parámetro.", HttpStatus.INTERNAL_SERVER_ERROR),
    PARAMETER_CHANGE_ORDER_ERROR("PAR006", "No se pudo cambiar el orden al parámetro.", HttpStatus.INTERNAL_SERVER_ERROR),
    PARAMETER_CHANGE_ORDER_OK("PAR007", "Se ha cambiado el orden del parámetro.", HttpStatus.OK),
    PARAMETER_EXISTS("PAR008", "El parámetro ya existe.", HttpStatus.BAD_REQUEST),
    // FIN PARAMETROS

    // USUARIOS CARGOS
    NO_INSERT_USER_POSITION("UXC001", "No se pudo insertar el usuario cargo.", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_DELETE_USER_POSITION("UXC002", "No se pudo eliminar el usuario cargo.", HttpStatus.INTERNAL_SERVER_ERROR),
    // FIN USUARIOS CARGOS

    // ROLES
    NO_INSERT_ROL("ROL001", "No se pudo insertar el rol.", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_UPDATE_ROL("ROL002", "No se pudo actualizar el rol.", HttpStatus.INTERNAL_SERVER_ERROR),
    ROL_NOT_FOUND("ROL003", "Rol no encontrado.", HttpStatus.NOT_FOUND),
    // FIN ROLES

    // PERMISOS
    NO_INSERT_PERMISSION("ROL001", "No se pudo insertar los permisos.", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_UPDATE_PERMISSION("ROL002", "No se pudo actualizar los permisos.", HttpStatus.INTERNAL_SERVER_ERROR),
    PERMISSION_NOT_FOUND("ROL003", "Permiso no encontrado.", HttpStatus.NOT_FOUND),
    // FIN PERMISOS

    // FUNCIONALIDADES
    NO_INSERT_FUNCTIONALITY("FUN001", "No se pudo insertar la funcionalidad.", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_UPDATE_FUNCTIONALITY("FUN002", "No se pudo actualizar la funcionalidad.", HttpStatus.INTERNAL_SERVER_ERROR),
    FUNCTIONALITY_NOT_FOUND("FUN003", "Funcionalidad no encontrado.", HttpStatus.NOT_FOUND),
    // FIN FUNCIONALIDADES

    // CONFIGURACIÓN INICIAL
    CONFIG_INITIAL_OK("CNI001", "La configuración inicial se ha realizado correctamente.", HttpStatus.OK),
    CONFIG_INITIAL_PARAMETER_INACTIVE("CNI002", "El parámetro de la configuración inicial se encuentra INACTIVO.", HttpStatus.OK),
    // FIN CONFIGURACIÓN INICIAL

    // PROYECTOS
    NO_INSERT_PROJECT("PRO001", "No se pudo insertar el proyecto.", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_UPDATE_PROJECT("PRO002", "No se pudo actualizar el proyecto.", HttpStatus.INTERNAL_SERVER_ERROR),
    PROJECT_NOT_FOUND("PRO003", "Proyecto no encontrado.", HttpStatus.NOT_FOUND),
    PROJECT_ARTIST_NO_PERMISSION("PRO004", "El rol es artista y no tiene permisos para ver todos los proyectos.", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_DELETE_PROJECT("PRO004", "No se pudo eliminar el proyecto.", HttpStatus.INTERNAL_SERVER_ERROR),
    // FIN PROYECTOS

    // DOCUMENTOS
    NO_INSERT_DOCUMENT("DOC001", "No se pudo insertar el documento.", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_UPDATE_DOCUMENT("DOC002", "No se pudo actualizar el documento.", HttpStatus.INTERNAL_SERVER_ERROR),
    DOCUMENT_NOT_FOUND("DOC003", "Documento no encontrado.", HttpStatus.NOT_FOUND),
    // FIN PROYECTOS

    // ETAPAS
    NO_INSERT_STAGE("ETA001", "No se pudo insertar la etapa.", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_DELETE_STAGE("ETA002", "No se pudo eliminar la etapa.", HttpStatus.INTERNAL_SERVER_ERROR),
    STAGE_INSERT_EMPTY("ETA003", "Los datos de inserción de la etapas son inválidos.", HttpStatus.INTERNAL_SERVER_ERROR),
    // FIN ETAPAS

    // ASIGNACIONES
    NO_INSERT_ASSIGNMENT("ASI001", "No se pudo insertar la asignación.", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_DELETE_ASSIGNMENT("ASI002", "No se pudo eliminar la asignación.", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_PROJECT_ASSIGNMENT("ASI003", "La asignación debe estar relacionada a un proyecto.", HttpStatus.INTERNAL_SERVER_ERROR),
    ASSIGNMENT_NOT_FOUND("ASI004", "Asignación no encontrada.", HttpStatus.NOT_FOUND),
    NO_UPDATE_ASSIGNMENT("ASI005", "No se pudo actualizar la asignación.", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_DATA_REPORT_ASSIGNMENT("ASI006", "Datos inválidos o faltantes del reporte de las asignaciones del proyecto.", HttpStatus.INTERNAL_SERVER_ERROR),
    // FIN ASIGNACIONES

    // USUARIOS ASIGNACIONES
    NO_INSERT_USER_ASSIGNMENT("UXA001", "No se pudo insertar el usuario de la asignación.", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_DELETE_USER_ASSIGNMENT("UXA002", "No se pudo eliminar el usuario de la asignación.", HttpStatus.INTERNAL_SERVER_ERROR),
    // FIN USUARIOS ASIGNACIONES

    // TOKEN
    NOT_FOUND_TOKEN("TOK001", "No se suministró el token de acceso.", HttpStatus.INTERNAL_SERVER_ERROR),
    PAYLOAD_NOT_FOUND_TOKEN("TOK002", "No se pudo extraer el payload del token de acceso.", HttpStatus.INTERNAL_SERVER_ERROR),
    // FIN TOKEN

    // ELEMENTOS
    NO_INSERT_ELEMENT("ELE001", "No se pudo insertar el elemento.", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_DELETE_ELEMENT("ELE002", "No se pudo eliminar el elemento.", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_UPDATE_ELEMENT("ELE003", "No se pudo actualizar el elemento.", HttpStatus.INTERNAL_SERVER_ERROR),
    ELEMENT_NOT_FOUND("ELE004", "Elemento no encontrado.", HttpStatus.NOT_FOUND),
    NO_CHANGE_STATUS_ELEMENT("ELE005", "No se pudo cambiar el estado del elemento.", HttpStatus.INTERNAL_SERVER_ERROR),
    CHANGE_STATUS_ELEMENT_OK("ELE006", "Se cambiado el estado del elemento.", HttpStatus.OK),
    // FIN ELEMENTOS

    // COMENTARIOS
    NO_INSERT_COMMENT("COM001", "No se pudo insertar el comentario.", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_DELETE_COMMENT("COM002", "No se pudo eliminar el comentario.", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_UPDATE_COMMENT("COM003", "No se pudo actualizar el comentario.", HttpStatus.INTERNAL_SERVER_ERROR),
    COMMENT_NOT_FOUND("COM004", "Comentario no encontrado.", HttpStatus.NOT_FOUND),
    NO_CHANGE_STATUS_COMMENT("COM005", "No se pudo cambiar el estado del comentario.", HttpStatus.INTERNAL_SERVER_ERROR),
    CHANGE_STATUS_COMMENT_OK("COM006", "Se cambiado el estado del comentario.", HttpStatus.OK),
    // FIN COMENTARIOS
    ;

    private final String code;
    private final String description;
    private final HttpStatus httpStatus;
}
