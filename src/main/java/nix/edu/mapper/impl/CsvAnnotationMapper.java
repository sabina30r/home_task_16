package nix.edu.mapper.impl;

import nix.edu.annotation.CsvField;
import nix.edu.mapper.CsvMapper;
import nix.edu.parser.CsvTable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class CsvAnnotationMapper implements CsvMapper {

    private <T> T getInstance(Class<T> type) {
        T instance = null;
        try {
            instance = type.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public <T> List<T> map(CsvTable csvTable, Class<T> type) {
        List<T> listFromCsv = new ArrayList<>();
        for (int i = 0; i < csvTable.height(); i++) {
            T instance = getInstance(type);
            for (Field field : type.getDeclaredFields()) {
                CsvField annotation = field.getAnnotation(CsvField.class);
                if (annotation != null) {
                    String value = annotation.value().isEmpty() ?
                            csvTable.get(i, field.getName()) : csvTable.get(i, annotation.value());
                    initField(field, value, instance);
                }
            }
            listFromCsv.add(instance);
        }
        return listFromCsv;
    }

    private <T> void initField(Field field, String value, T instance) {
        field.setAccessible(true);
        Class<?> fieldType = field.getType();
        try {
            if (fieldType.equals(String.class)) {
                field.set(instance, value);
            } else if (fieldType == Integer.class || fieldType == int.class) {
                field.setInt(instance, Integer.parseInt(value));
            } else if (fieldType == Double.class || fieldType == double.class) {
                field.setDouble(instance, Double.parseDouble(value));
            } else if (fieldType == Long.class || fieldType == long.class) {
                field.setLong(instance, Long.parseLong(value));
            } else if (fieldType == Boolean.class || fieldType == boolean.class) {
                field.setBoolean(instance, Boolean.parseBoolean(value));
            } else if (fieldType.isEnum()) {
                Object[] enumConstants = fieldType.getEnumConstants();
                for (Object constant : enumConstants) {
                    if (String.valueOf(constant).equalsIgnoreCase(value)) {
                        field.set(instance, constant);
                    }
                }
            } else {
                throw new UnsupportedOperationException("Unsupported field type (" +
                        fieldType.getName() + ") is required for field " +
                        field.getName());
            }

        } catch (IllegalAccessException e) {
            e.getMessage();
        }

    }
}
