package com.hnv99.hmall.common.core.base;

import cn.hutool.core.util.ObjectUtil;

import java.util.EnumSet;

/**
 * Common interface for enumerations
 */
public interface IBaseEnum<T> {

    T getValue();

    String getLabel();

    /**
     * Get the enumeration by its value
     *
     * @param value
     * @param clazz
     * @param <E>   Enumeration
     * @return
     */
    static <E extends Enum<E> & IBaseEnum> E getEnumByValue(Object value, Class<E> clazz) {

        if (value == null) {
            return null;
        }

        EnumSet<E> allEnums = EnumSet.allOf(clazz); // Get all enumerations of the type
        E matchEnum = allEnums.stream()
                .filter(e -> ObjectUtil.equal(e.getValue(), value))
                .findFirst()
                .orElse(null);
        return matchEnum;
    }

    /**
     * Get the value by its text label
     *
     * @param value
     * @param clazz
     * @param <E>
     * @return
     */
    static <E extends Enum<E> & IBaseEnum> String getLabelByValue(Object value, Class<E> clazz) {
        if (value == null) {
            return null;
        }

        EnumSet<E> allEnums = EnumSet.allOf(clazz); // Get all enumerations of the type
        E matchEnum = allEnums.stream()
                .filter(e -> ObjectUtil.equal(e.getValue(), value))
                .findFirst()
                .orElse(null);

        String label = null;
        if (matchEnum != null) {
            label = matchEnum.getLabel();
        }
        return label;
    }

    /**
     * Get the value by its text label
     *
     * @param label
     * @param clazz
     * @param <E>
     * @return
     */
    static <E extends Enum<E> & IBaseEnum> Object getValueByLabel(String label, Class<E> clazz) {
        if (label == null) {
            return null;
        }

        EnumSet<E> allEnums = EnumSet.allOf(clazz); // Get all enumerations of the type
        String finalLabel = label;
        E matchEnum = allEnums.stream()
                .filter(e -> ObjectUtil.equal(e.getLabel(), finalLabel))
                .findFirst()
                .orElse(null);

        Object value = null;
        if (matchEnum != null) {
            value = matchEnum.getValue();
        }
        return value;
    }
}
