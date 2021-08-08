package com.ssafy.niceage.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.niceage.Domain.Auth.Auth;

public interface AuthRepository extends JpaRepository<Auth, Long>{
    List<Auth> findByPhoneNumber(String phoneNumber);
    Auth findByAuthNumber(String smsCheckNum);
}
