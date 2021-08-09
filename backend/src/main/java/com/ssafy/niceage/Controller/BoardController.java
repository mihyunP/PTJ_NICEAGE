package com.ssafy.niceage.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.niceage.Controller.Request.BoardRequest;
import com.ssafy.niceage.Controller.Request.UserRequest;
import com.ssafy.niceage.Domain.Board.Board;
import com.ssafy.niceage.Domain.Board.BoardDTO;
import com.ssafy.niceage.Domain.Senior_Citizen_Center.Senior_Citizen_Center;
import com.ssafy.niceage.Domain.User.User;
import com.ssafy.niceage.Service.BoardService;
import com.ssafy.niceage.Service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;
	private final UserService userService;
	
	@ApiOperation(value = "게시판 불러오기", response = MainResponse.class)
	@GetMapping("/list")
	public MainResponse showBoardList() {

		MainResponse response = null;

		List<Board> boardList = boardService.findAll();
		try {
			response = new MainResponse("success", boardList);
		} catch (Exception e) {
			response = new MainResponse("fail", e.getMessage());
		}
		
		return response;
	}

	@ApiOperation(value = "게시판 글 작성", response = MainResponse.class)
	@PostMapping("/create")
	public MainResponse createBoard(@RequestBody BoardRequest request) {

		MainResponse response = null;

		try {
			// 로그인 아이디로 회원객체 먼저 가져오기
			User user = userService.findById(request.getUserId());
			BoardDTO boardDto = new BoardDTO(request, user);
			boardService.save(boardDto);
			response = new MainResponse("success", "게시글 작성 완료");
		} catch (Exception e) {
			response = new MainResponse("fail", e.getMessage());
		}

		return response;

	}

	@ApiOperation(value = "게시판 글 읽기", response = MainResponse.class)
	@GetMapping("/read/{userId}")
	public MainResponse readBoard(@ApiParam(value = "아이디")@PathVariable String userId, Long boardId) {
		MainResponse response = null;

		try {
			User user = userService.findById(userId);
			Board board = boardService.findById(boardId);
			boolean isCheck = boardService.checkUserId(user.getUserNo(), board.getUser().getUserNo());
			response = new MainResponse("success", isCheck);
		} catch (Exception e) {
			response = new MainResponse("게시글을 불러오지 못 했습니다", e.getMessage());
		}
		return response;

	}

	@ApiOperation(value = "게시판 글 수정", response = MainResponse.class)
	@PutMapping("/update")
	public MainResponse updateBoard(@RequestBody BoardRequest request) {

		MainResponse response = null;

		try {
			// 로그인 아이디로 회원객체 먼저 가져오기
			User user = userService.findById(request.getUserId());
			BoardDTO boardDto = new BoardDTO(request, user);
			boardService.update(boardDto);
			response = new MainResponse("success", "게시글 수정 완료");
		} catch (Exception e) {
			response = new MainResponse("fail", e.getMessage());
		}

		return response;

	}

	@ApiOperation(value = "게시판 글 삭제", response = MainResponse.class)
	@DeleteMapping("/delete/{userId}")
	public MainResponse deleteBoard(@ApiParam(value = "아이디")@PathVariable String userId, Long boardId) {
		
		MainResponse response = null;

		try {
			User user = userService.findById(userId);
			Board board = boardService.findById(boardId);
			// 현재 로그인한 회원이 작성한 글이 맞는지 체크
			boolean isCheck = boardService.checkUserId(user.getUserNo(), board.getUser().getUserNo());
			boolean isAbleDelete = boardService.deleteBoard(isCheck, boardId);
			if (isAbleDelete) {
				response = new MainResponse("success", "게시글이 삭제되었습니다.");
			} else {
				response = new MainResponse("faile", "본인이 작성한 게시글이 아닙니다.");
			}
		} catch (Exception e) {
			response = new MainResponse("fail", e.getMessage());
		}
		return response;

	}

}
