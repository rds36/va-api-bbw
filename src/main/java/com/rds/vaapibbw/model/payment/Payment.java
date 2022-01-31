package com.rds.vaapibbw.model.payment;

import com.rds.vaapibbw.entity.BbwUser;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Payment {

    private String transactionNumber;

    private String referenceNumber;

    private String amount;

    private String note;

    private String virtualAccount;

    private String clientId;
}
