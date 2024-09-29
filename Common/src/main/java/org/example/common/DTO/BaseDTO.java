package org.example.common.DTO;

import java.lang.reflect.Field;

public abstract class BaseDTO {

    /**
     * 判断对象的指定字段是否为 null
     * @param fieldName 字段名
     * @return true 如果字段为 null，false 否则
     */
    public boolean isFieldNull(String fieldName) {
        try {
            Field field = this.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(this) == null;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error accessing field: " + fieldName, e);
        }
    }

    /**
     * 判断对象的指定字段是否不为 null
     * @param fieldName 字段名
     * @return true 如果字段不为 null，false 否则
     */
    public boolean isFieldNotNull(String fieldName) {
        return !isFieldNull(fieldName);
    }

    /**
     * 判断整个对象是否为 null 或者所有字段都为 null
     * @return true 如果对象所有字段都为 null，false 否则
     */
    public boolean isNull() {
        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(this) != null) {
                    return false;  // 如果有任何字段不为 null，则对象不为空
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error accessing field", e);
            }
        }
        return true;  // 如果所有字段都为 null，则对象为空
    }

    /**
     * 判断对象是否至少有一个字段不为 null
     * @return true 如果至少有一个字段不为 null，false 否则
     */
    public boolean isNotNull() {
        return !isNull();
    }
}
