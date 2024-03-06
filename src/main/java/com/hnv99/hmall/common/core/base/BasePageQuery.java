package com.hnv99.hmall.common.core.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Base Pagination Request Object
 */
@Getter
@Setter
@Schema
public class BasePageQuery implements Serializable {

    @Schema(description = "Page number", example = "1")
    private int pageNum = 1;

    @Schema(description = "Number of records per page", example = "10")
    private int pageSize = 10;
}
