package com.rds.vaapibbw.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "bbw_user")
@Data
public class BbwUser {

    @Id
    @Column(name = "client_id")
    private String clientId;

    @Column(name = "account_name")
    private String accountName;

}
