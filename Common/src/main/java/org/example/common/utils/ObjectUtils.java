package org.example.common.utils;

import java.lang.reflect.Field;

public class ObjectUtils {

    /**
     * 判断整个对象是否为 null 或者所有字段都为 null
     * @param obj 要判断的对象
     * @return true 如果对象为 null 或所有字段都为 null
     */
    public static boolean isNull(Object obj) {
        if (obj == null) {
            return true;
        }

        // 遍历对象的所有字段，检查是否有非空字段
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(obj) != null) {
                    return false;  // 有字段不为 null，返回 false
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error accessing field: " + field.getName(), e);
            }
        }
        return true;  // 所有字段都为 null，返回 true
    }

    /**
     * 判断对象是否不为 null 并且至少有一个字段不为 null
     * @param obj 要判断的对象
     * @return true 如果至少有一个字段不为 null
     */
    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    /**
     * 判断对象的指定字段是否为 null
     * @param obj 需要判断的对象
     * @param fieldName 需要判断的字段名称
     * @return true 如果字段为 null
     */
    public static boolean isFieldNull(Object obj, String fieldName) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj) == null;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error accessing field: " + fieldName, e);
        }
    }

    /**
     * 判断对象的指定字段是否不为 null
     * @param obj 需要判断的对象
     * @param fieldName 需要判断的字段名称
     * @return true 如果字段不为 null
     */
    public static boolean isFieldNotNull(Object obj, String fieldName) {
        return !isFieldNull(obj, fieldName);
    }
}
