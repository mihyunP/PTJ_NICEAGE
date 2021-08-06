package com.ssafy.niceage.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.niceage.Controller.Request.BoardRequest;
import com.ssafy.niceage.Controller.Request.UserRequest;
import com.ssafy.niceage.Domain.Board.Board;
import com.ssafy.niceage.Service.BoardService;
import com.ssafy.niceage.Service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/board")
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;
	private final UserService userService;
	
	@ApiOperation(value = "게시판 클릭시", response = BaseResponse.class)
	@GetMapping("/")
	public BaseResponse createBoard(@RequestBody BoardRequest request) {
		
		BaseResponse response = null;
		
		long boardWriterNo = userService.findById(request.getUserId()).getUserNo();
		
		Board board = Board.builder()
					.boardTitle(request.getBoardTitle())
					.boardContents(request.getBoardContents())
					.userNo(boardWriterNo)
					.build();
		
		response = new BaseResponse("success", "게시글 작성 완료");
		
		return response;
		
	}
	
	@ApiOperation(value = "게시판 클릭시", response = BaseResponse.class)
	@GetMapping("/")
	public BaseResponse readBoard(@RequestBody UserRequest request) {
		return null;
		
	}
	
	@ApiOperation(value = "게시판 클릭시", response = BaseResponse.class)
	@GetMapping("/")
	public BaseResponse updateBoard(@RequestBody UserRequest request) {
		return null;
		
	}
	
	@ApiOperation(value = "게시판 클릭시", response = BaseResponse.class)
	@GetMapping("/")
	public BaseResponse deleteBoard(@RequestBody UserRequest request) {
		return null;
	
	}
	
}
