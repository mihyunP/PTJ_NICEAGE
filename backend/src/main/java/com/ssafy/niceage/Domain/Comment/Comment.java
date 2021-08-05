package com.ssafy.niceage.Domain.Comment;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.ssafy.niceage.Domain.Board.Board;
import com.ssafy.niceage.Domain.User.User;

import lombok.Data;

@Entity
@Data
public class Comment {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "comment_id")
	private int commentId;
	
	@Column (name = "comment_contents", nullable = false, length = 255)
    private String commentContents;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn (name = "user_no")
    private User user;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn (name = "board_id")
    private Board board;
}
