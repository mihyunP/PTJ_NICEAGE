package com.ssafy.niceage.Domain.Comment;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class CommentDTO {
	
	@Id @GeneratedValue
	@Column (name = "comment_id")
	private int Id;
	@Column (name = "comment_contents")
    private String Contents;
	@Column (name = "user_no")
    private String userNo;
	@Column (name = "board_id")
    private int boardId;
	
	public CommentDTO(int id, String contents, String userNo, int boardId) {
		super();
		Id = id;
		Contents = contents;
		this.userNo = userNo;
		this.boardId = boardId;
	}
	
	
}
