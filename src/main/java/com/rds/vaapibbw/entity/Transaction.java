package com.rds.vaapibbw.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
@Data
public class Transaction {

    @Id
    private String transactionNumber;

    @Column(name = "reference_number")
    private String referenceNumber;

    @Column(name = "amount")
    private String amount;

    @Column(name = "note")
    private String note;

    @Column(
            name = "virtual_account",
            unique = true
    )
    private String virtualAccount;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private BbwUser bbwUser;

}
