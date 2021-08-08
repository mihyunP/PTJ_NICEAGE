package com.ssafy.niceage.Domain.Comment;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ssafy.niceage.Domain.Board.Board;
import com.ssafy.niceage.Domain.User.User;

import lombok.Data;

@Entity
@Data
public class Comment {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "comment_id")
	private Long commentId;
	
	@Column (name = "comment_contents", nullable = false, length = 255)
    private String commentContents;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name = "user_no")
    private User user;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name = "board_id")
    private Board board;
}
