package com.ssafy.niceage.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.niceage.Domain.Sms.Sms;

public interface SmsRepository extends JpaRepository<Sms, Long>{
    List<Sms> findByPhoneNumber(String phoneNumber);
    Sms findByAuthNumber(String smsCheckNum);
}
