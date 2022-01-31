package com.rds.vaapibbw.service.transaction;

import com.rds.vaapibbw.model.payment.PaymentRequest;
import com.rds.vaapibbw.model.payment.PaymentResponse;
import com.rds.vaapibbw.model.transaction.TransactionRequest;
import com.rds.vaapibbw.model.user.UserResponse;

public interface TransactionService {
    UserResponse get(TransactionRequest virtualAccount);
    PaymentResponse save(PaymentRequest paymentRequest);
}
