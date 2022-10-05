package com.retos.bombapp.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.commons.lang3.RandomStringUtils;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.stereotype.Component;

import com.retos.bombapp.constants.ConstantesParametros;

import lombok.extern.slf4j.Slf4j;

/**
 * Utilidades generales
 */
@Component
@Slf4j
public class UtilGeneral {

    private UtilGeneral() {
    }

    /**
     * Encripta una cadena de String
     *
     * @param str String
     * @return String
     */
    public static String encriptar(String str) {
        if (Objects.isNull(str)) {
            return null;
        }

        return new StrongPasswordEncryptor().encryptPassword(str);
    }

    /**
     * Desencripta una cadena de String
     *
     * @param str        String
     * @param strEncrypt String
     * @return boolean
     */
    public static boolean desencriptar(String str, String strEncrypt) {
        if (Objects.isNull(str) || Objects.isNull(strEncrypt)) {
            return false;
        }

        return new StrongPasswordEncryptor().checkPassword(str, strEncrypt);
    }

    /**
     * Genera una cadena de string de acuerdo a un tamaño
     *
     * @param size int
     * @return String
     */
    public static String generateString(int size) {
        return RandomStringUtils.randomAlphanumeric(size).toUpperCase();
    }

    /**
     * Realiza un filtro de registro único por una llave de un objeto
     *
     * @param <T>
     * @param keyExtractor Function
     * @return <T>
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * Convierte una fecha de formato String a Date
     *
     * @param dateStr String
     * @param format  String
     * @return Date
     */
    public static Date convertStringToDate(String dateStr, String format) {
        if (Objects.isNull(dateStr)) {
            return null;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
        return date;
    }

    /**
     * Convierte una fecha de formato Date a String
     *
     * @param date   Date
     * @param format String
     * @return String
     */
    public static String convertDateToString(Date date, String format) {
        if (Objects.isNull(date)) {
            return null;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * Genera el código del parámetro
     *
     * @param types String
     * @param name  String
     * @param order short
     * @param regex String
     * @return String
     */
    public static String generateCodeParameter(String types, String name, short order, String regex) {
        if (Objects.isNull(types) || Objects.isNull(name)
                || Objects.isNull(order) || Objects.isNull(regex)) {
            return null;
        }

        String[] type = types.split(regex);

        StringBuilder sb = new StringBuilder();
        if (type.length != 1) {
            sb.append(type[0].charAt(0)).append(type[1].charAt(0));
        } else {
            sb.append(type[0].charAt(0)).append(type[0].charAt(1));
        }

        sb.append((name.length() >= 3) ? name.replaceAll("\\s", "").substring(0, 3) : name.replaceAll("\\s", ""));
        sb.append(order);

        return sb.toString().toUpperCase();
    }

    /**
     * Compara y retorna si es o no artista por el código del rol
     * 
     * @param rolCode String
     * @return boolean
     */
    public static boolean isArtist(String rolCode) {
        return rolCode.equals(ConstantesParametros.getCodesRoles()[3]);
    }

    /**
     * Convierte un array de bytes a tring base64
     * 
     * @param bytes byte[]
     * @return String
     */
    public static String convertBytesToBase64(byte[] bytes) {
        return Objects.nonNull(bytes) ? Base64.getEncoder().encodeToString(bytes) : null;
    }

    /**
     * Convierte un string a capitalize (La primera letra mayúscula, las demás
     * minúsculas)
     * 
     * @param str String
     * @return String
     */
    public static String toCapitalize(String str) {
        if (Objects.isNull(str) || str.isEmpty())
            return "";
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    /**
     * Formatea un decimal limitando el número de decimales
     * 
     * @param decimal double
     * @param format  String
     * @return String
     */
    public static String formatDecimal(double decimal, String format) {
        DecimalFormat decimalFormat = new DecimalFormat(format);
        return decimalFormat.format(decimal);
    }
}
