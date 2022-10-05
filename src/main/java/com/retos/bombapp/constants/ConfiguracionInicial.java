package com.retos.bombapp.constants;

import com.retos.bombapp.entities.*;
import com.retos.bombapp.utils.UtilGeneral;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ConfiguracionInicial {
    private ConfiguracionInicial() {
    }

    /**
     * Configura la lista inicial de roles
     *
     * @return List<Roles>
     */
    public static List<Roles> configListRoles() {
        String[] names = ConstantesParametros.getNamesRoles();
        String[] codes = ConstantesParametros.getCodesRoles();
        boolean[] visibilities = ConstantesParametros.getVisibilitiesRoles();

        List<Roles> listaRoles = new ArrayList<>();

        int index = 0;
        while (index < names.length) {
            Roles roles = Roles
                    .builder()
                    .name(names[index])
                    .code(codes[index])
                    .description("Rol " + names[index])
                    .visibility(visibilities[index])
                    .build();
            index++;
            listaRoles.add(roles);
        }

        return listaRoles;
    }

    /**
     * Configura el usuario inicial administrador
     *
     * @param idRol Long
     * @param idParameter Long
     * @return Usuarios
     */
    public static Usuarios configUserAdmin(Long idRol, Long idParameter) {
        return Usuarios
                .builder()
                .email(ConstantesParametros.EMAIL_ADMIN)
                .type(idParameter)
                .status(Constantes.ACTIVE)
                .names(ConstantesParametros.NAMES_ADMIN)
                .lastnames(ConstantesParametros.LASTNAMES_ADMIN)
                .cellphone(ConstantesParametros.CELLPHONE_ADMIN)
                .password(ConstantesParametros.PASSWORD_ADMIN)
                .updatePassword(false)
                .roles(Roles.builder().id(idRol).build())
                .build();
    }

    /**
     * Configura la lista inicial de funcionalidades
     *
     * @return List<Funcionalidades>
     */
    public static List<Funcionalidades> configListFunctionalities() {
        String[] names = ConstantesParametros.getNamesFunctionalities();
        String[] codes = ConstantesParametros.getCodesFunctionalities();

        List<Funcionalidades> listFunc = new ArrayList<>();
        int index = 0;
        while (index < names.length) {
            Funcionalidades funcionalidades = Funcionalidades
                    .builder()
                    .name(names[index])
                    .code(codes[index])
                    .description("Funcionalidad " + names[index])
                    .build();
            listFunc.add(funcionalidades);
            index++;
        }

        return listFunc;
    }

    /**
     * Configura la lista de parámetros iniciales
     *
     * @return List<Parametros>
     */
    public static List<Parametros> configParametersInicial() {
        String[] codesParameter = ConstantesParametros.getCodesParameter();
        String[] namesParameter = ConstantesParametros.getNamesParameter();
        String[] typesParameter = ConstantesParametros.getTypesParameter();

        List<Parametros> listParameters = new ArrayList<>();

        int index = 0;
        short order = 1;
        String auxType = "";
        while (index < codesParameter.length) {
            if (!auxType.equalsIgnoreCase(typesParameter[index])) {
                order = 1;
                auxType = typesParameter[index];
            }

            listParameters.add(
                    Parametros
                            .builder()
                            .type(typesParameter[index].toUpperCase())
                            .name(namesParameter[index].toUpperCase())
                            .code(codesParameter[index].toUpperCase())
                            .order(order++)
                            .status(index == 0 ? Constantes.INACTIVE : Constantes.ACTIVE)
                            .build());
            index++;
        }

        return listParameters;
    }

    /**
     * Configura los permisos de todas las funcionalidades del rol administrador
     * @param listFunctionalities List<Funcionalidades>
     * @param idRol Long
     * @return List<Permisos>
     */
    public static List<Permisos> configPermissionAdmin(List<Funcionalidades> listFunctionalities, Long idRol) {
        List<Permisos> listPermissions = new ArrayList<>();
        for (Funcionalidades f : listFunctionalities) {
            listPermissions.add(
                    Permisos
                            .builder()
                            .roles(Roles.builder().id(idRol).build())
                            .funcionalidades(Funcionalidades.builder().id(f.getId()).build())
                            .consultAction(true)
                            .createAction(true)
                            .deleteAction(true)
                            .updateAction(true)
                            .build());
        }

        return listPermissions;
    }

    /**
     * Configura los parámetros adicionales iniciales
     * 
     * @return List<Parametros>
     */
    public static List<Parametros> configParametersAdditionalsInitial() {
        ConstantesParametros.setAdditionals();
        TreeMap<String, String[]> parameters = ConstantesParametros.getAdditionals();

        List<Parametros> listParameters = new ArrayList<>();

        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            short order = 1;
            for (String str: entry.getValue()) {
                listParameters.add(
                        Parametros
                                .builder()
                                .type(entry.getKey().toUpperCase())
                                .name(str.toUpperCase())
                                .code(UtilGeneral.generateCodeParameter(
                                        entry.getKey(),
                                        str,
                                        order,
                                        "_"))
                                .order(order++)
                                .status(Constantes.ACTIVE)
                                .build());
            }
        }

        return listParameters;
    }
}
