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
<<<<<<< HEAD
	 * 회원가입
	 */
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}
	
//	/**
//	 * 회원 전체 리스트
//	 */
//	@Transactional
//	public List<User> findAll(){
//		return userRepository.findAll();
//	}
	
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
