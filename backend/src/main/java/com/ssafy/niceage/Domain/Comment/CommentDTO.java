package com.ssafy.niceage.Domain.Comment;

import org.junit.Assert;

import com.ssafy.niceage.Controller.Request.CommentRequest;
import com.ssafy.niceage.Domain.Board.Board;
import com.ssafy.niceage.Domain.User.User;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ApiModel(value = "댓글 DTO")
public class CommentDTO {
	private Long commentId;
    private String commentContents;
    private User user;
    private Board board;
    
    @Builder
	public CommentDTO(Comment comment) {
		Assert.assertNotNull("commentContents must not be null", comment.getCommentContents());
		Assert.assertNotNull("user must not be null", comment.getUser());
		Assert.assertNotNull("board must not be null", comment.getBoard());
		
		this.commentId = comment.getCommentId();
		this.commentContents = comment.getCommentContents();
		this.user = comment.getUser();
		this.board = comment.getBoard();
    }
    
    @Builder
    public CommentDTO(CommentRequest request, User user, Board board) {
    	Assert.assertNotNull("commentContents must not be null", request.getCommentContents());
    	Assert.assertNotNull("user must not be null", user);
    	Assert.assertNotNull("board must not be null", board);
    	
    	this.commentContents = request.getCommentContents();
    	this.user = user;
    	this.board = board;
    }
    
    public Comment toEntity() {
    	return Comment.builder()
    			.commentId(this.commentId)
    			.commentContents(this.commentContents)
    			.user(this.user)
    			.board(this.board)
    			.build();
    }


}
