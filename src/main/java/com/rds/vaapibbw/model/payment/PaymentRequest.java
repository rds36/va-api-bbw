package com.rds.vaapibbw.model.payment;

import lombok.Data;

@Data
public class PaymentRequest {
    private String client_id;
    private String reference_number;
    private String virtual_account;
    private String amount;
    private String note;
}
