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
     * 아이디로 유저객체 찾기
     */
    @Transactional
    public User findById(String Id){
        return userRepository.findByUId(Id);
    }
    
    /**
     * 로그인 시 , 아이디 비밀번호로 유저 찾기
     */
    public User findByIdAndPassword(String Id, String Password) {
        return userRepository.findByUIdAndUPassword(Id, Password);
    }
}
