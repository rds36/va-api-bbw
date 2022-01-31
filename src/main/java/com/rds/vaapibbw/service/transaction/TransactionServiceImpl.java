package com.rds.vaapibbw.service.transaction;

import com.rds.vaapibbw.entity.BbwUser;
import com.rds.vaapibbw.entity.Transaction;
import com.rds.vaapibbw.entity.VirtualAccount;
import com.rds.vaapibbw.error.UserNotFoundException;
import com.rds.vaapibbw.error.VirtualAccountNotFoundException;
import com.rds.vaapibbw.model.payment.PaymentRequest;
import com.rds.vaapibbw.model.payment.PaymentResponse;
import com.rds.vaapibbw.model.user.UserResponse;
import com.rds.vaapibbw.model.transaction.TransactionRequest;
import com.rds.vaapibbw.repository.TransactionRepository;
import com.rds.vaapibbw.repository.UserRepository;
import com.rds.vaapibbw.repository.VirtualAccountRepository;
import com.rds.vaapibbw.validation.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private UserRepository userRepository;
    private TransactionRepository transactionRepository;
    private VirtualAccountRepository virtualAccountRepository;
    private ValidationUtil validationUtil;

    @Autowired
    public TransactionServiceImpl(
            ValidationUtil validationUtil,
            UserRepository userRepository,
            TransactionRepository transactionRepository,
            VirtualAccountRepository virtualAccountRepository
    ) {
        this.transactionRepository = transactionRepository;
        this.virtualAccountRepository = virtualAccountRepository;
        this.userRepository = userRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public UserResponse get(TransactionRequest transactionRequest){
        Optional<VirtualAccount> mVirtualAccount = virtualAccountRepository.findById(transactionRequest.getVirtualAccount());
        Transaction mTransaction;
        if (mVirtualAccount.isPresent()){
            mTransaction = mVirtualAccount.get().getTransactionNumber();
            UserResponse response = convertTransactionToUserResponse(mTransaction);
            if (!response.getClientId().equals(transactionRequest.getClientId())){
                throw new UserNotFoundException();
            }
            return response;
        } else {
            throw new VirtualAccountNotFoundException();
        }
    }

    @Override
    public PaymentResponse save(PaymentRequest paymentRequest) {
        //validate
        validationUtil.validate(paymentRequest);
        PaymentResponse paymentResponse = new PaymentResponse();
        VirtualAccount virtualAccount = new VirtualAccount();
        Transaction transaction = convertPaymentRequestToTransaction(paymentRequest);

        paymentResponse.setTransaction_number(transaction.getTransactionNumber());
        virtualAccount.setVirtualAccount(transaction.getVirtualAccount());
        virtualAccount.setTransactionNumber(transaction);

        transactionRepository.save(transaction);
        virtualAccountRepository.save(virtualAccount);
        return paymentResponse;
    }


    public Transaction convertPaymentRequestToTransaction(PaymentRequest paymentRequest){
        Date date = new Date();
        Optional<BbwUser> user = userRepository.findById(paymentRequest.getClient_id());

        if (!user.isPresent()){
            throw new UserNotFoundException();
        }

        Transaction transaction = new Transaction();
        transaction.setVirtualAccount(paymentRequest.getVirtual_account());
        transaction.setReferenceNumber(paymentRequest.getReference_number());
        transaction.setNote(paymentRequest.getNote());
        transaction.setAmount(paymentRequest.getAmount());
        transaction.setTransactionNumber(Long.toString(date.getTime()));
        transaction.setBbwUser(user.get());

        return transaction;
    }

    public UserResponse convertTransactionToUserResponse(Transaction transaction){
        UserResponse response = new UserResponse();
        response.setAccountName(transaction.getBbwUser().getAccountName());
        response.setClientId(transaction.getBbwUser().getClientId());

        return response;
    }

}
