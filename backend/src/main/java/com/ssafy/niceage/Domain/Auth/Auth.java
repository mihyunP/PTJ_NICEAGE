package com.ssafy.niceage.Domain.Auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ssafy.niceage.Controller.Request.AuthRequest;

import lombok.Data;

@Entity
@Data
public class Auth {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authId;

    @Column(nullable = false)
    private String phoneNumber;

    @Column
    private String authNumber;

    @Column
    private boolean authValid;

    public static Auth createAuth(AuthRequest authRequset) {
        Auth authInput = new Auth();
        authInput.authId = authRequset.getAuthId();
        authInput.setAuthId(authRequset.getAuthId());;
        authInput.setPhoneNumber(authRequset.getPhoneNumber());
        authInput.setAuthNumber(authRequset.getAuthNumber());
        authInput.setAuthValid(true);
        return authInput;
    }
}
