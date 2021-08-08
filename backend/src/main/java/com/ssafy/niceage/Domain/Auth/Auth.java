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
	@Column(name = "auth_id")
    private Long authId;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "auth_number")
    private String authNumber;

    @Column(name = "auth_valid")
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
