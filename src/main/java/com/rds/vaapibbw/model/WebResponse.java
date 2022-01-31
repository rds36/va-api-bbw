package com.rds.vaapibbw.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WebResponse<T> {
    private String code;
    private String status;
    private T data;
}
