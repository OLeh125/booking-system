package com.learn.booking.bookingsystem.service;

import com.learn.booking.bookingsystem.exception.NotFoundException;
import com.learn.booking.bookingsystem.service.annotations.Immutable;
import java.lang.reflect.Field;
import java.util.Arrays;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class PatchHandler {

    @SneakyThrows(IllegalAccessException.class)
    public <T> T apply(T applyTo, Object ops){
        Field[] applyToFields = applyTo.getClass().getDeclaredFields();
        Field[] opsFields = ops.getClass().getDeclaredFields();
        for (Field field: applyToFields) {
            field.setAccessible(true);
            Field opsField = Arrays.stream(opsFields).filter(f -> f.getName().equals(field.getName()))
                .findFirst().orElseThrow(() -> new NotFoundException("Field is not found"));
            if (Arrays.stream(field.getAnnotations()).anyMatch(a -> a instanceof Immutable) && opsField.get(ops) == null){
                throw new IllegalArgumentException("Field " + field.getName() + " is immutable");
            }
            field.set(applyTo, opsField.get(ops));
        }
        return applyTo;
    }

}
