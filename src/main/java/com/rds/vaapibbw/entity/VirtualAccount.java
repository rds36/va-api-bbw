package com.rds.vaapibbw.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "virtual_account")
@Data
public class VirtualAccount {

    @Id
    private String virtualAccount;

    @OneToOne
    @JoinColumn(name = "transaction_number")
    private Transaction transactionNumber;

}
