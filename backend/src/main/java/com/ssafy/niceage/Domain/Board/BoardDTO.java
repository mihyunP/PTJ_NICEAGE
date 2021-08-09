package com.ssafy.niceage.Domain.Board;

import java.util.Date;
import java.util.List;

import com.ssafy.niceage.Controller.Request.BoardRequest;
import com.ssafy.niceage.Domain.Comment.Comment;
import com.ssafy.niceage.Domain.User.User;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "게시판 DTO", description = "ㅇ")
public class BoardDTO {
	private Long boardId;
	private List<Comment> comments;
	private String boardTitle;
	private String boardContents;
	private Date boardDate;
	private User user;
	
	public class BoardWrite {
		
	}
	
	@Builder
	public BoardDTO(BoardRequest request, User user) {
		this.boardId = request.getBoardId();
//		this.comments = request.getComments();
		this.boardTitle = request.getBoardTitle();
		this.boardContents = request.getBoardContents();
		this.boardDate = request.getBoardDate();
		this.user = user;
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
