package com.ssafy.niceage.Domain.Sms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ssafy.niceage.Controller.Request.SmsRequest;

import lombok.Data;

@Entity
@Data
public class Sms {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long smsId;

    @Column(nullable = false)
    private String phoneNumber;

    @Column
    private String authNumber;

    @Column
    private boolean valid;

    public static Sms createSms(SmsRequest smsRequset) {
        Sms smsInput = new Sms();
        smsInput.smsId = smsRequset.getSmsId();
        smsInput.setSmsId(smsRequset.getSmsId());;
        smsInput.setPhoneNumber(smsRequset.getPhoneNumber());
        smsInput.setAuthNumber(smsRequset.getAuthNumber());
        smsInput.setValid(true);
        return smsInput;
    }
}
