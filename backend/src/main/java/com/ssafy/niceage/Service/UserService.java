package com.ssafy.niceage.Service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.niceage.Controller.Request.UserRequest;
import com.ssafy.niceage.Domain.User.User;
import com.ssafy.niceage.Repository.UserRepository;

import antlr.collections.List;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	
	/**
     * 회원가입
     */
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }
    
    /**
     * 아이디로 유저객체 찾기
     */
    @Transactional
    public User findById(String Id){
        return userRepository.findByUserId(Id);
    }
    
    /**
     * 로그인 시 , 아이디 비밀번호로 유저 찾기
     */
    public User findByIdAndPassword(String Id, String Password) {
        return userRepository.findByUserIdAndUserPassword(Id, Password);
    }
    
    /**
     * 아이디로 중복확인
     */
    @Transactional
    public boolean checkUserId(String userId) {
        if(userRepository.findByUserId(userId)==null){ // null이면 중복X
            return false;
        }else{
            return true;
        }
    }
    
    /**
     * 하나의 번호로 하나의 유저만 가입
     * 핸드폰번호로 유저찾기
     */
    @Transactional
    public User findByUserPhone(String userPhone) {
        return userRepository.findByUserPhone(userPhone);
    }
}
