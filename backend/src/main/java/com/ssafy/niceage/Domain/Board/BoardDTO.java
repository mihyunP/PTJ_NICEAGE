package com.ssafy.niceage.Domain.Board;

import java.util.ArrayList;
import java.util.Date;
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
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "게시판 DTO")
public class BoardDTO {
	private Long boardId;
	private List<CommentDTO> commentsDto;
	private String boardTitle;
	private String boardContents;
	private Date boardDate;
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
//		this.comments = request.getComments();
		this.boardTitle = request.getBoardTitle();
		this.boardContents = request.getBoardContents();
		this.boardDate = request.getBoardDate();
		this.user = user;
	}

	@Builder
	public BoardDTO(Board board) {
		Assert.assertNotNull("boardTitle must not be null", board.getBoardTitle());
		Assert.assertNotNull("boardContents must not be null", board.getBoardContents());

		this.boardId = board.getBoardId();
		this.boardTitle = board.getBoardTitle();
		this.boardContents = board.getBoardContents();
		this.boardDate = board.getBoardDate();
		this.user = board.getUser();

		if (board.getComments() != null) {
			List<CommentDTO> commentList = new ArrayList<>();
			for (Comment comment : board.getComments()) {
				CommentDTO commentDTO = new CommentDTO(comment);
				commentList.add(commentDTO);
			}
			this.commentsDto = commentList;
		}
	}

	public Board toEntity() {
		return Board.builder()
				.boardId(this.boardId)
				.boardTitle(this.boardTitle)
				.boardContents(this.boardContents)
				.user(this.user)
				.build();
	}

}
