package com.ssafy.niceage.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.niceage.Controller.Request.BoardRequest;
import com.ssafy.niceage.Controller.Request.CommentRequest;
import com.ssafy.niceage.Domain.Board.Board;
import com.ssafy.niceage.Domain.Board.BoardDTO;
import com.ssafy.niceage.Domain.Comment.Comment;
import com.ssafy.niceage.Domain.Comment.CommentDTO;
import com.ssafy.niceage.Domain.User.User;
import com.ssafy.niceage.Service.BoardService;
import com.ssafy.niceage.Service.CommentService;
import com.ssafy.niceage.Service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
	private final BoardService boardService;
	private final UserService userService;
	private final CommentService commentService;

	@ApiOperation(value = "댓글 작성", response = MainResponse.class)
	@PostMapping("/create")
	public MainResponse createComment(@RequestBody CommentRequest request) {

		MainResponse response = null;

		try {
			User user = userService.findById(request.getUserId());
			Board board = boardService.findById(request.getBoardId());
			CommentDTO commentDto = new CommentDTO(request, user, board);
			commentService.save(commentDto);
			response = new MainResponse("success", "댓글 작성 완료");
		} catch (Exception e) {
			response = new MainResponse("fail", e.getMessage());
		}

		return response;

	}
	
	@ApiOperation(value = "댓글 수정", response = MainResponse.class)
	@PutMapping("/update")
	public MainResponse updateComment(@RequestBody CommentRequest request) {

		MainResponse response = null;

		try {
			// 로그인 아이디로 회원객체 먼저 가져오기
			User user = userService.findById(request.getUserId());
			Board board = boardService.findById(request.getBoardId());
			CommentDTO commentDto = new CommentDTO(request, user, board);
			commentService.update(commentDto);
			response = new MainResponse("success", "게시글 수정 완료");
		} catch (Exception e) {
			response = new MainResponse("fail", e.getMessage());
		}

		return response;

	}
	
	@ApiOperation(value = "댓글 삭제", response = MainResponse.class)
	@DeleteMapping("/delete/{userId}/{boardId}/{commentId}")
	public MainResponse deleteComment(@ApiParam(value = "아이디")@PathVariable("userId") String userId,@PathVariable("boardId") Long boardId,@PathVariable("commentId") Long commentId) {
		
		MainResponse response = null;

		try {
			User user = userService.findById(userId);
			Board board = boardService.findById(boardId);
			Comment comment = commentService.findByCommentId(commentId);
			// 현재 로그인한 회원이 작성한 글이 맞는지 체크
			boolean isAbleDelete = commentService.isAbleDelete(user.getUserNo(), comment.getUser().getUserNo(), commentId);
			if (isAbleDelete) {
				response = new MainResponse("success", "게시글이 삭제되었습니다.");
			} else {
				response = new MainResponse("fail", "본인이 작성한 게시글이 아닙니다.");
			}
		} catch (Exception e) {
			response = new MainResponse("fail", e.getMessage());
		}
		return response;

	}
}
