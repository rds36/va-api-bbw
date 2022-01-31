package com.rds.vaapibbw.model.transaction;

import lombok.Data;

@Data
public class TransactionRequest {
    private String clientId;
    private String referenceNumber;
    private String virtualAccount;
}
