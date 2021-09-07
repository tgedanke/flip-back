package com.vbsoft.Utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Tools {

    public static <T extends Enum<T>> T getEnumByAnnotation(final Class<T> ENUM_CLASS, final String ANNOTATION_VALUE) {

        Optional<Field> foundField = Arrays.stream(ENUM_CLASS.getEnumConstants()[0].getClass().getDeclaredFields()).filter(field -> {
            if (field != null)
                if (field.isAnnotationPresent(JsonProperty.class))
                    return field.getAnnotation(JsonProperty.class).value().equalsIgnoreCase(ANNOTATION_VALUE);
            else
                return false;
            else
                return false;
        }).findFirst();

        return foundField.map(field -> Enum.valueOf(ENUM_CLASS, field.getName())).orElse(null);
    }


}
