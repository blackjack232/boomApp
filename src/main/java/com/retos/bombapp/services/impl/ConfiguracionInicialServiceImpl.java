package com.retos.bombapp.services.impl;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.constants.ConfiguracionInicial;
import com.retos.bombapp.constants.Constantes;
import com.retos.bombapp.constants.ConstantesParametros;
import com.retos.bombapp.entities.*;
import com.retos.bombapp.exceptions.BombappException;
import com.retos.bombapp.repositories.*;
import com.retos.bombapp.services.ConfiguracionInicialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Servicio de la configuración inicial
 */
@Service
public class ConfiguracionInicialServiceImpl implements ConfiguracionInicialService {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FuncionalidadRepository funcionalidadRepository;

    @Autowired
    private ParametroRepository parametroRepository;

    @Autowired
    private PermisoRepository permisoRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void config() {
        configInitial();
    }

    /**
     * Hace el llamado a los métodos que realizan los cambios en la base de
     * datos
     */
    private void configInitial() {
        consultParameterConfigInitial();
        insertParametersConfigInitial();
        insertParametersAdditionalsInitial();
        insertAllRol();
        insertUserAdmin();
        insertFunctionalities();
        insertPermissionsAdminConfigInitial();
    }

    /**
     * Consulta y valida el parámetro de configuración inicial en qué estado se
     * encuentra
     */
    private void consultParameterConfigInitial() {
        List<Parametros> listParametros = parametroRepository.findByType(ConstantesParametros.TYPE_CONFIG_INITIAL);
        if (Objects.nonNull(listParametros) && !listParametros.isEmpty() && Objects.nonNull(listParametros.get(0))) {
            boolean flag = listParametros.get(0).getType().equals(Constantes.ACTIVE);
            if (!flag) {
                throw new BombappException(CodigoRespuestaApi.CONFIG_INITIAL_PARAMETER_INACTIVE);
            }
        }
    }

    /**
     * Inserta los roles iniciales a la base de datos
     */
    private void insertAllRol() {
        List<Roles> listRoles = ConfiguracionInicial.configListRoles();
        try {
            rolRepository.saveAll(listRoles);
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_INSERT_ROL);
        }
    }

    /**
     * Inserta el usuario administrador inicial a la base de datos
     */
    private void insertUserAdmin() {
        Roles rol = rolRepository.findByCode(ConstantesParametros.getCodesRoles()[0]).orElse(null);

        if (Objects.isNull(rol)) {
            throw new BombappException(CodigoRespuestaApi.ROL_NOT_FOUND);
        }

        List<Parametros> listParametros = parametroRepository.findByType(ConstantesParametros.TYPE_TYPE_COLLABORATOR);

        if (Objects.isNull(listParametros) || listParametros.isEmpty()) {
            throw new BombappException(CodigoRespuestaApi.PARAMETER_NOT_FOUND);
        }

        Parametros param = listParametros
                .stream()
                .filter(p -> p.getName().equalsIgnoreCase(Constantes.TYPE_USER_PLANT))
                .findFirst()
                .orElse(null);

        Usuarios usuarios = ConfiguracionInicial.configUserAdmin(rol.getId(), param.getId());
        try {
            usuarioRepository.save(usuarios);
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_INSERT_USER);
        }
    }

    /**
     * Inserta las funcionalidades iniciales a la base de datos
     */
    private void insertFunctionalities() {
        List<Funcionalidades> listFuncionalidades = ConfiguracionInicial.configListFunctionalities();
        try {
            funcionalidadRepository.saveAll(listFuncionalidades);
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_INSERT_FUNCTIONALITY);
        }
    }

    /**
     * Inserta los parámetros de configuración inicial en la base de datos
     */
    private void insertParametersConfigInitial() {
        List<Parametros> listParameters = ConfiguracionInicial.configParametersInicial();
        try {
            parametroRepository.saveAll(listParameters);
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_INSERT_PARAMETER);
        }
    }

    /**
     * Inserta todos los permisos del administrador principal
     */
    private void insertPermissionsAdminConfigInitial() {
        List<Funcionalidades> listFunctionalities = funcionalidadRepository.findAll();
        Roles rol = rolRepository.findByCode(ConstantesParametros.getCodesRoles()[0]).orElse(null);

        if (Objects.isNull(rol)) {
            throw new BombappException(CodigoRespuestaApi.ROL_NOT_FOUND);
        }

        List<Permisos> listPermissions = ConfiguracionInicial.configPermissionAdmin(listFunctionalities, rol.getId());
        try {
            permisoRepository.saveAll(listPermissions);
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_INSERT_PERMISSION);
        }
    }

    /**
     * Inserta los parámetros adicionales
     */
    private void insertParametersAdditionalsInitial() {
        List<Parametros> listParameters = ConfiguracionInicial.configParametersAdditionalsInitial();
        try {
            parametroRepository.saveAll(listParameters);
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_INSERT_PARAMETER);
        }
    }
}
