package com.rds.vaapibbw.model.user;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserRequest {
    private String client_id;
    private String account_name;
}
