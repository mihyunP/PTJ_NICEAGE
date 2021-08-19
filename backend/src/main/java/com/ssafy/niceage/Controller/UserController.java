package com.ssafy.niceage.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.niceage.Controller.Request.ChangePwRequest;
import com.ssafy.niceage.Controller.Request.ReportRequest;
import com.ssafy.niceage.Controller.Request.UserRequest;
import com.ssafy.niceage.Domain.Report.Report;
import com.ssafy.niceage.Domain.Report.ReportDTO;
import com.ssafy.niceage.Domain.User.User;
import com.ssafy.niceage.Domain.User.UserDTO;
import com.ssafy.niceage.Service.ReportService;
import com.ssafy.niceage.Service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private final ReportService reportService;
	
	@ApiOperation(value = "회원가입", response = MainResponse.class)
    @PostMapping("/join")
    public MainResponse join(@ApiParam(value = "회원 정보") @RequestBody UserRequest request){

        MainResponse response = null;
        try{
            User user = User.createUser(request);

            userService.save(user);
            response = new MainResponse("success", "가입 완료");
        }catch(IllegalStateException e){
            response = new MainResponse("fail",e.getMessage());
        }
        return response;
    }
	
	@ApiOperation(value = "아이디 중복 검사", response = MainResponse.class)
    @GetMapping("/check/{userId}")
    public MainResponse duplicateUserLoginId(@ApiParam(value = "아이디")@PathVariable String userId){
        MainResponse response = null;
        try{
            boolean isCheck = userService.checkUserId(userId);
            if(isCheck){
                response = new MainResponse("success", true);
            }else{
                response = new MainResponse("success", false);
            }
        }catch(Exception e){
            response = new MainResponse("fail", e.getMessage());
        }
        return response;
    }
	

    @ApiOperation(value = "휴대폰 번호에 해당하는 회원 찾기", response = MainResponse.class)
    @GetMapping("/phone/{userPhone}")
    public MainResponse findUserByPhone(@ApiParam(value = "휴대폰 번호") @PathVariable String userPhone){
        MainResponse response = null;
        try{
            User findUser = userService.findByUserPhone(userPhone);
            if(findUser == null){
                response = new MainResponse("fail",null);
            }else{
                response = new MainResponse("success", findUser);
            }
        }
        catch(Exception e){
            response = new MainResponse("fail",e.getMessage());
        }
        return response;
    }
    
    @ApiOperation(value = "아이디,휴대폰 번호에 해당하는 회원 찾기", notes = "찾으면 success, 못찾으면 fail", response = MainResponse.class)
    @GetMapping("/{userId}/{userPhone}")
    public MainResponse findUserByIdAndPhone(@ApiParam(value = "아이디") @PathVariable String userId,
                                             @ApiParam(value = "휴대폰 번호") @PathVariable String userPhone){
    	MainResponse response = null;
        try{
            User findUser = userService.findByUserIdAndUserPhone(userId, userPhone);
            if(findUser == null){
                response = new MainResponse("fail",null);
            }else{
                response = new MainResponse("success", findUser);
            }
        }
        catch(Exception e){
            response = new MainResponse("fail",e.getMessage());
        }
        return response;
    }
    
    @ApiOperation(value = "내 정보", notes = "회원정보를 반환", response = MainResponse.class)
    @GetMapping("/{userId}")
    public MainResponse findUser(@ApiParam(value = "아이디") @PathVariable String userId){
    	MainResponse response = null;
        try{
            User findUser = userService.findById(userId);
            UserDTO userDto = new UserDTO(findUser);
            response = new MainResponse("success", userDto);
        }
        catch(Exception e){
            response = new MainResponse("fail",e.getMessage());
        }
        return response;
    }
    
    @ApiOperation(value = "회원 정보 수정", notes = "정보 수정 성공 시 수정 성공 반환", response = MainResponse.class)
    @PutMapping("/{userId}") // 전체 수정은 put
    public MainResponse updateUser(@ApiParam(value = "아이디")@PathVariable String userId,
                                   @ApiParam(value = "회원 객체")@RequestBody UserRequest request) {
    	MainResponse response = null;
        try {
            userService.updateUser(userId, request);
            response = new MainResponse("success", "수정 성공");
        } catch (Exception e ) {
            response = new MainResponse("fail", e.getMessage());
        }
        return response;
    }
    
    @ApiOperation(value = "비밀번호 수정", notes = "", response = MainResponse.class)
    @PostMapping("/pwd") // 부분 수정은 post
    public MainResponse updatePassword(@ApiParam(value = "아이디, 패스워드")@RequestBody ChangePwRequest request) {

       MainResponse response = null;
        try {
            userService.updatePw(request);
            response = new MainResponse("success", "수정 성공");
        } catch (Exception e) {
            response = new MainResponse("fail", e.getMessage());
        }
        return response;
    }
    
    @ApiOperation(value = "회원 탈퇴", notes = "성공 시 삭제 성공 반환", response = MainResponse.class)
    @DeleteMapping("/{userId}")
    public MainResponse deleteUser(@ApiParam(value = "사용자 로그인 정보")@PathVariable String userId){
    	MainResponse response = null;
        try {
            User user = userService.findById(userId);
            userService.deleteUser(userId); // 유저정보 삭제
            response = new MainResponse("success", "삭제 성공");
        } catch (IllegalStateException e) {
            response = new MainResponse("fail", e.getMessage());
        }
        return response;
    }
    
    @ApiOperation(value = "모든 회원 목록 불러오기", notes = "모든 회원정보를 반환", response = MainResponse.class)
    @GetMapping
    public MainResponse findUsers(){
    	MainResponse response = null;
        try{
            List<User> findUsers = userService.findAll();
            List<UserDTO> collect = findUsers.stream()
                    .map(m-> new UserDTO(m))
                    .collect(Collectors.toList());
            response = new MainResponse("success", collect);
        }
        catch(Exception e){
            response = new MainResponse("fail", e.getMessage());
        }
        return response;
    }
    
	@ApiOperation(value = "신고", response = MainResponse.class)
    @PostMapping("/report")
    public MainResponse report(@ApiParam(value = "회원 정보") @RequestBody ReportRequest request){

        MainResponse response = null;
        try{
        	Report report = Report.receiptReport(request);

        	reportService.save(report);
            response = new MainResponse("success", "가입 완료");
        }catch(IllegalStateException e){
            response = new MainResponse("fail",e.getMessage());
        }
        return response;
    }
	
    @ApiOperation(value = "모든 신고 목록 불러오기", notes = "모든 신고정보를 반환", response = MainResponse.class)
    @GetMapping("/report/all")
    public MainResponse findReports(){
    	MainResponse response = null;
        try{
            List<Report> findReports = reportService.findAll();
            List<ReportDTO> collect = findReports.stream()
                    .map(m-> new ReportDTO(m))
                    .collect(Collectors.toList());
            response = new MainResponse("success", collect);
        }
        catch(Exception e){
            response = new MainResponse("fail", e.getMessage());
        }
        return response;
    }
}
