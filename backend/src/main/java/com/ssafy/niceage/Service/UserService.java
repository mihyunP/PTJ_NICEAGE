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
	public User create(User user) {
		return UserRepository.create(user);
	}
	
	/**
	 * 회원 전체 리스트
	 */
	@Transactional
	public List<User> findAll(){
		return UserRepository.findAll();
	}
	
	/**
	 * 비밀번호 변경
	 */
	@Transactional
	public void modifyPassword() {
		
	}
	
	/**
	 * 회원정보 수정
	 */
	@Transactional
	public void modifyUser(String userId, UserRequest request) {
		
	}
	
	/**
	 * 회원 탈퇴
	 */
	@Transactional
	public void deleteUser(String userId) {
		
	}
}
