package com.ssafy.niceage.Domain.Board;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.ssafy.niceage.Controller.Request.BoardRequest;
import com.ssafy.niceage.Domain.Comment.Comment;
import com.ssafy.niceage.Domain.Comment.CommentDTO;
import com.ssafy.niceage.Domain.User.User;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ApiModel(value = "게시판 DTO")
public class BoardDTO {
	private Long boardId;
	private List<CommentDTO> commentsDto;
	private String boardTitle;
	private String boardContents;
	private LocalDateTime boardDate;
	private User user;

	/**
	 * Assert.assertNotNull null값이 입력되면 안되는 컬럼들을 위한 안전장치
	 * 
	 * @param request
	 * @param user
	 */
	@Builder
	public BoardDTO(BoardRequest request, User user) {
		Assert.assertNotNull("boardTitle must not be null", request.getBoardTitle());
		Assert.assertNotNull("boardContents must not be null", request.getBoardContents());
		Assert.assertNotNull("user must not be null", user);

		this.boardId = request.getBoardId();
		this.boardTitle = request.getBoardTitle();
		this.boardContents = request.getBoardContents();
		this.boardDate = request.getBoardDate();
		this.user = user;
	}


	/**
	 * Assert.assertNotNull null값이 입력되면 안되는 컬럼들을 위한 안전장치
	 * 
	 * @param request
	 * @param user
	 */
	@Builder
	public BoardDTO(BoardRequest request, User user, List<CommentDTO> commentsDto) {
		Assert.assertNotNull("boardTitle must not be null", request.getBoardTitle());
		Assert.assertNotNull("boardContents must not be null", request.getBoardContents());
		Assert.assertNotNull("user must not be null", user);

		this.boardId = request.getBoardId();
		this.commentsDto = commentsDto;
		this.boardTitle = request.getBoardTitle();
		this.boardContents = request.getBoardContents();
		this.boardDate = request.getBoardDate();
		this.user = user;
	}

	/**
	 * 게시글 불러오기에서 불필요한 정보를 제외한 편의성 클래스
	 */
	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@ApiModel(value = "게시글 불러오기 response에 담을 DTO")
	public class BoardListDTO {
		private Long boardId;
		private String boardTitle;
		private String boardContents;
		private LocalDateTime boardDate;
		private String userName;
		
		public BoardListDTO(Board board) {
			Assert.assertNotNull("boardTitle must not be null", board.getBoardTitle());
			Assert.assertNotNull("boardContents must not be null", board.getBoardContents());
			
			this.boardId = board.getBoardId();
			this.boardTitle = board.getBoardTitle();
			this.boardContents = board.getBoardContents();
			this.boardDate = board.getBoardDate();
			this.userName = board.getUser().getUserName();
		}		
	}
	
	
	/**
	 * 게시글 읽기에서 불필요한 정보를 제외한 편의성 클래스
	 */
	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@ApiModel(value = "게시글 읽기에 response에 담을 DTO")
	public class BoardResponseDTO {
		private Long boardId;
		private String boardTitle;
		private String boardContents;
		private LocalDateTime boardDate;
		private User user;
		private List<CommentDTO.CommentResponseDTO> commentsDto;
		
		public BoardResponseDTO(Board board) {
			this.boardId = board.getBoardId();
			this.boardTitle = board.getBoardTitle();
			this.boardContents = board.getBoardContents();
			this.boardDate = board.getBoardDate();
			this.user = board.getUser();
			if (board.getComments() != null) {
				List<CommentDTO.CommentResponseDTO> commentList = new ArrayList<>();
				// CommentDTO 클래스의 inner클래스의 객체를 생성하는 과정
				for (Comment comment : board.getComments()) {
					CommentDTO commentDto = new CommentDTO();
					CommentDTO.CommentResponseDTO commentResponseDto = commentDto.new CommentResponseDTO(comment.getCommentId(), 
									comment.getCommentContents(), 
									comment.getUser().getUserName());
					commentList.add(commentResponseDto);
				}
				this.commentsDto = commentList;
			}
		}
		
	}
	
	public Board toEntity() {
		return Board.builder().
				boardId(this.boardId).
				boardTitle(this.boardTitle).
				boardContents(this.boardContents)
				.user(this.user).build();
	}
}
