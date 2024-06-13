package com.project.open_weekend.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;

public class Util {
    public static <T extends Enum<T>> T getEnumName(String name, Class<T> enumType) {
        if (!isValidEnum(enumType, name)) {
            throw new IllegalStateException("Invalid " + enumType.getSimpleName() + " name");
        }
        return Enum.valueOf(enumType, name.toUpperCase());
    }

    public static <T extends Enum<T>> boolean isValidEnum(Class<T> enumType, String name) {
        return Arrays.stream(enumType.getEnumConstants())
                .anyMatch(enumConstant -> enumConstant.name().equalsIgnoreCase(name));
    }

    public static String getFormattedDate(LocalDateTime localDateTime){
        String pattern = "yyyy-MM-dd HH:mm:ss";
        var formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault());
        return localDateTime.format(formatter);

    }

    public  static LocalDateTime getLocalDateTime(String timestamp){
        String pattern = "yyyy-MM-dd HH:mm:ss";
        var formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault());
        return LocalDateTime.parse(timestamp, formatter);
    }
}
