package com.hnv99.hmall.common.core.enums;

import com.hnv99.hmall.common.core.base.IBaseEnum;
import lombok.Getter;

/**
 * Status enumeration
 */
public enum StatusEnum implements IBaseEnum<Integer> {

    ENABLE(1, "Enable"),
    DISABLE(0, "Disable");

    @Getter
    private Integer value;

    @Getter
    private String label;

    StatusEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}
