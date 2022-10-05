package com.retos.bombapp.services.impl;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.constants.Constantes;
import com.retos.bombapp.constants.ConstantesParametros;
import com.retos.bombapp.entities.Parametros;
import com.retos.bombapp.entities.Usuarios;
import com.retos.bombapp.entities.UsuariosCargos;
import com.retos.bombapp.exceptions.BombappException;
import com.retos.bombapp.models.CargoDTO;
import com.retos.bombapp.models.EmailDTO;
import com.retos.bombapp.models.UsuarioCargoDTO;
import com.retos.bombapp.models.UsuarioDTO;
import com.retos.bombapp.repositories.ParametroRepository;
import com.retos.bombapp.repositories.UsuarioCargoRepository;
import com.retos.bombapp.repositories.UsuarioRepository;
import com.retos.bombapp.services.*;
import com.retos.bombapp.utils.UtilGeneral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Servicio de usuarios
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UtilService utilService;

    @Autowired
    private UsuarioCargoService usuarioCargoService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ParametroRepository parametroRepository;

    @Autowired
    private UsuarioCargoRepository usuarioCargoRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Usuarios insertUser(UsuarioDTO usuarioDTO) {
        String password = UtilGeneral.generateString(8);

        usuarioDTO.setId(null);
        Usuarios usuarios = (Usuarios) utilService.mapDTO(usuarioDTO, new Usuarios());
        usuarios.setStatus(Constantes.ACTIVE);
        usuarios.setPassword(Objects.nonNull(password) ? UtilGeneral.encriptar(password) : null);
        usuarios.setUpdatePassword(true);

        Usuarios usuariosInsert;
        try {
            usuariosInsert = usuarioRepository.save(usuarios);
        } catch (Exception e) {
            throw new BombappException(CodigoRespuestaApi.NO_INSERT_USER);
        }

        usuarioCargoService.deleteAllByUsuarios(usuarios.getId());

        if (Objects.nonNull(usuarioDTO.getCargos()) && !usuarioDTO.getCargos().isEmpty()) {
            List<UsuariosCargos> usuariosCargosList = mapUsuariosCargos(usuarioDTO.getCargos(), usuarios.getId());
            usuarioCargoService.insertAllUsuariosCargos(usuariosCargosList);
        }

        sendEmail(usuariosInsert, password, Constantes.PASS_AUTO_GENERATE);

        return usuariosInsert;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UsuarioDTO> getAllUsuariosYCargo() {
        List<Usuarios> usuarios = usuarioRepository.findAll();
        List<Parametros> parametros = parametroRepository.findByType(ConstantesParametros.TYPE_POSITION);
        List<UsuariosCargos> usuariosCargos = usuarioCargoRepository.findAll();

        List<UsuarioDTO> listUsuario = new ArrayList<>();
        for (Usuarios u : usuarios) {
            if (u.getRoles().isVisibility()) {
                UsuarioDTO usuarioDTO = (UsuarioDTO) utilService.mapDTO(u, new UsuarioDTO());

                List<UsuariosCargos> usuariosCargosByUsuario = usuariosCargos
                        .stream()
                        .filter(uc -> Objects.equals(uc.getUsuarios().getId(), u.getId()))
                        .toList();

                List<CargoDTO> listCargos = new ArrayList<>();
                for (UsuariosCargos uc : usuariosCargosByUsuario) {
                    Optional<Parametros> param = parametros.stream()
                            .filter(pm -> Objects.equals(pm.getId(), uc.getPositionId()))
                            .findFirst();

                    param.ifPresent(value
                            -> listCargos.add(CargoDTO
                                    .builder()
                                    .id(value.getId())
                                    .name(value.getName())
                                    .build()
                            )
                    );
                }
                usuarioDTO.setListPositions(listCargos);
                listUsuario.add(usuarioDTO);
            }
        }

        return listUsuario;
    }

    @Override
    public UsuarioDTO getByIdUsuariosYCargo(Long id) {
        Usuarios usuarios = usuarioRepository.findById(id).orElse(null);

        if (Objects.isNull(usuarios)) {
            throw new BombappException(CodigoRespuestaApi.USER_NOT_FOUND);
        }

        UsuarioDTO usuarioDTO = (UsuarioDTO) utilService.mapDTO(usuarios, new UsuarioDTO());

        List<Parametros> parametros = parametroRepository.findByType(ConstantesParametros.TYPE_POSITION);
        List<UsuariosCargos> usuariosCargosByUsuario = usuarioCargoRepository.findByUsuarios(usuarios);

        List<CargoDTO> listCargos = new ArrayList<>();
        for (UsuariosCargos uc : usuariosCargosByUsuario) {
            Optional<Parametros> param = parametros.stream()
                    .filter(pm -> Objects.equals(pm.getId(), uc.getPositionId()))
                    .findFirst();

            param.ifPresent(value
                    -> listCargos.add(CargoDTO
                            .builder()
                            .id(value.getId())
                            .name(value.getName())
                            .build()
                    )
            );
        }
        usuarioDTO.setListPositions(listCargos);

        return usuarioDTO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteUser(Long id) {
        try {
            usuarioCargoService.deleteAllByUsuarios(id);
            usuarioRepository.deleteById(id);
        } catch (Exception e) {
            throw new BombappException(CodigoRespuestaApi.NO_DELETE_USER);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void recoverPassword(String email) {
        Usuarios usuarios = usuarioRepository.findByEmail(email).orElse(null);
        if (Objects.isNull(usuarios)) {
            throw new BombappException(CodigoRespuestaApi.EMAIL_NOT_FOUND);
        }

        String password = UtilGeneral.generateString(8);
        usuarios.setPassword(Objects.nonNull(password) ? UtilGeneral.encriptar(password) : null);
        usuarios.setUpdatePassword(true);

        try {
            usuarioRepository.save(usuarios);
        } catch (Exception e) {
            throw new BombappException(CodigoRespuestaApi.NO_UPDATE_PASS);
        }

        sendEmail(usuarios, password, Constantes.PASS_RECOVERY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Usuarios updateUser(Long id, UsuarioDTO usuarioDTO) {
        Usuarios usuarios = usuarioRepository.findById(id).orElse(null);
        if (Objects.isNull(usuarios)) {
            throw new BombappException(CodigoRespuestaApi.USER_NOT_FOUND);
        }

        Usuarios usuarioUpdate = (Usuarios) utilService.mapDTO(usuarioDTO, new Usuarios());
        usuarioUpdate.setId(id);
        usuarioUpdate.setPassword(usuarios.getPassword());
        usuarioUpdate.setUpdatePassword(usuarios.isUpdatePassword());

        Usuarios usuariosResp;
        try {
            usuariosResp = usuarioRepository.save(usuarioUpdate);
        } catch (Exception e) {
            throw new BombappException(CodigoRespuestaApi.NO_UPDATE_USER);
        }

        usuarioCargoService.deleteAllByUsuarios(usuarios.getId());

        if (Objects.nonNull(usuarioDTO.getCargos()) && !usuarioDTO.getCargos().isEmpty()) {
            List<UsuariosCargos> usuariosCargosList = mapUsuariosCargos(usuarioDTO.getCargos(), usuarios.getId());
            usuarioCargoService.insertAllUsuariosCargos(usuariosCargosList);
        }

        return usuariosResp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changePassword(Long id, String password) {
        Usuarios usuarios = usuarioRepository.findById(id).orElse(null);

        if (Objects.isNull(usuarios)) {
            throw new BombappException(CodigoRespuestaApi.USER_NOT_FOUND);
        }

        usuarios.setPassword(Objects.nonNull(password) ? UtilGeneral.encriptar(password) : null);
        usuarios.setUpdatePassword(false);

        try {
            usuarioRepository.save(usuarios);
        } catch (Exception e) {
            throw new BombappException(CodigoRespuestaApi.NO_UPDATE_PASS);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeStatus(Long id, String status) {
        Usuarios usuarios = usuarioRepository.findById(id).orElse(null);

        if (Objects.isNull(usuarios)) {
            throw new BombappException(CodigoRespuestaApi.USER_NOT_FOUND);
        }

        if (status.equalsIgnoreCase(usuarios.getStatus())) {
            return;
        }

        usuarios.setStatus(status.equalsIgnoreCase(Constantes.ACTIVE) ? Constantes.ACTIVE : Constantes.INACTIVE);
        try {
            usuarioRepository.save(usuarios);
        } catch (Exception e) {
            throw new BombappException(CodigoRespuestaApi.NO_UPDATE_STATUS_USER);
        }
    }

    /**
     * Inserta los usuarioCargo
     *
     * @param cargos List<String>
     * @param idUser Long
     * @return List<UsuariosCargos>
     */
    private List<UsuariosCargos> mapUsuariosCargos(List<Long> cargos, Long idUser) {
        List<UsuariosCargos> usuariosCargosList = new ArrayList<>();
        UsuarioCargoDTO usuarioCargoDTO;
        for (Long cargoId : cargos) {
            usuarioCargoDTO = new UsuarioCargoDTO();
            usuarioCargoDTO.setUsuarios(Usuarios.builder().id(idUser).build());
            usuarioCargoDTO.setPositionId(cargoId);
            UsuariosCargos usuariosCargos = (UsuariosCargos) utilService.mapDTO(usuarioCargoDTO, new UsuariosCargos());
            usuariosCargosList.add(usuariosCargos);
        }

        return usuariosCargosList;
    }

    /**
     * Envía el email
     *
     * @param usuarios Usuarios
     * @param password String
     */
    private void sendEmail(Usuarios usuarios, String password, String subject) {
        try {
            String html = emailService.getTemplateHTML(usuarios, password);
            EmailDTO emailDTO = EmailDTO
                    .builder()
                    .subject(subject)
                    .addressee(usuarios.getEmail())
                    .html(html)
                    .build();
            emailService.sendMail(emailDTO);
        } catch (Exception e) {
            throw new BombappException(CodigoRespuestaApi.ERROR_SEND_EMAIL);
        }
    }
}
