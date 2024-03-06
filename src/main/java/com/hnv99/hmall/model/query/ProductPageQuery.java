package com.hnv99.hmall.model.query;

import com.hnv99.hmall.common.core.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Product page query object")
@Data
public class ProductPageQuery extends BasePageQuery {

    @Schema(description="Keywords")
    private String keywords;

    @Schema(description="Sort field name")
    private String sortField;

    @Schema(description="Sort rule (asc: ascending; desc: descending)")
    private String sort;
}

