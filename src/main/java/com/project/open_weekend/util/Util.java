package com.project.open_weekend.util;

import java.util.Arrays;

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
}
