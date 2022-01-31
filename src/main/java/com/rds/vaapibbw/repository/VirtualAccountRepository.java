package com.rds.vaapibbw.repository;

import com.rds.vaapibbw.entity.VirtualAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VirtualAccountRepository extends JpaRepository<VirtualAccount, String> {
}
