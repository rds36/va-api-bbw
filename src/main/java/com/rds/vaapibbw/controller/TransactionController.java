package com.rds.vaapibbw.controller;

import com.rds.vaapibbw.model.payment.PaymentRequest;
import com.rds.vaapibbw.model.payment.PaymentResponse;
import com.rds.vaapibbw.model.user.AccountNameResponse;
import com.rds.vaapibbw.model.user.UserResponse;
import com.rds.vaapibbw.model.transaction.TransactionRequest;
import com.rds.vaapibbw.model.WebResponse;
import com.rds.vaapibbw.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(
            TransactionService virtualAccountService
    ) {
        this.transactionService = virtualAccountService;
    }

    @GetMapping(
            value = "/api/virtual_account",
            produces = "application/json",
            consumes = "application/json"
    )
    public WebResponse<AccountNameResponse> getUserByVirtualAccount(
            @RequestBody TransactionRequest body
    ) {
        UserResponse userResponse = transactionService.get(body);
        AccountNameResponse accountNameResponse =
                new AccountNameResponse(userResponse.getAccountName());
        return new WebResponse<>(
                "000",
                "Success",
                accountNameResponse
        );
    }

    @PostMapping(
            value = "/api/payment",
            produces = "application/json",
            consumes = "application/json"
    )
    public WebResponse<PaymentResponse> savePayment(
            @RequestBody PaymentRequest paymentRequest
    ) {

        PaymentResponse paymentResponse = transactionService.save(paymentRequest);
        return new WebResponse<>(
                "000",
                "Success",
                paymentResponse
        );
    }

}
