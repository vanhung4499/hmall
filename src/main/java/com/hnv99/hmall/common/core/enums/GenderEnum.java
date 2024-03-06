package com.hnv99.hmall.common.core.enums;

import com.hnv99.hmall.common.core.base.IBaseEnum;
import lombok.Getter;

/**
 * Gender enumeration
 */
public enum GenderEnum implements IBaseEnum<Integer> {

    MALE(1, "Male"),
    FEMALE(2, "Female"),
    UNKNOWN(0, "Unknown");

    @Getter
    // @EnumValue // Annotation provided by MyBatis-Plus to indicate that this value should be inserted into the database
    private Integer value;

    @Getter
    // @JsonValue // Annotation indicates that this field should be returned when the enumeration is serialized
    private String label;

    GenderEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}
