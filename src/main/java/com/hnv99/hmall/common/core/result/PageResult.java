package com.hnv99.hmall.common.core.result;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

@Data
public class PageResult<T> implements Serializable {

        private String code;

        private Data<T> data;

        private String msg;

        public static <T> PageResult success(Page<T> page) {
            PageResult result = new PageResult<>();
            result.setCode(ResultCode.SUCCESS.getCode());

            Data data = new Data<T>();
            data.setList(page.getContent());
            data.setTotal(page.getTotalElements());

            result.setData(data);
            result.setMsg(ResultCode.SUCCESS.getMsg());
            return result;
        }

        @lombok.Data
        public static class Data<T> {

            private List<T> list;

            private long total;

        }
}
