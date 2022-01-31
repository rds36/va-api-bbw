package com.rds.vaapibbw.repository;

import com.rds.vaapibbw.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
