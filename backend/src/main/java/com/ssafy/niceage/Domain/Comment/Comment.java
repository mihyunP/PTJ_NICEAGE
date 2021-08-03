package com.ssafy.niceage.Domain.Comment;

import javax.persistence.*;

import com.ssafy.niceage.Domain.Board.Board;
import com.ssafy.niceage.Domain.User.User;

import lombok.Data;

@Entity
@Data
public class Comment {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "comment_id")
	private int Id;
	
	@Column (name = "comment_contents", nullable = false, length = 255)
    private String Contents;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name = "user_no")
    private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name = "board_id")
    private Board board;

	public Comment(int id, String contents, User user, Board board) {
		super();
		Id = id;
		Contents = contents;
		this.user = user;
		this.board = board;
	}
}
