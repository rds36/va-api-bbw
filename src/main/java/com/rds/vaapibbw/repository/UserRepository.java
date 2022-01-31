package com.rds.vaapibbw.repository;

import com.rds.vaapibbw.entity.BbwUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<BbwUser, String> {
}
