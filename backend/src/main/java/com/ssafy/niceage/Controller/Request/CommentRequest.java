package com.ssafy.niceage.Controller.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(value = "클라이언트에서 댓글 작성시 넘어오는 board, user, comment 정보", description = "댓글 CRUD에 필요한 회원ID, 게시글ID와 제목, 내용")
public class CommentRequest {
	@ApiModelProperty(value = "CommentId")
	private Long commentId;
	
	@ApiModelProperty(value = "BoardId")
	private Long boardId;

	@ApiModelProperty(value = "UserID")
	private String userId;
	
	@ApiModelProperty(value = "CommentContents")
	private String commentContents;
}
