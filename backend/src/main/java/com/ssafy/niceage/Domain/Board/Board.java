package com.ssafy.niceage.Domain.Board;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.ssafy.niceage.Domain.Comment.Comment;
import com.ssafy.niceage.Domain.User.User;

import lombok.Data;

@Entity
@Data
public class Board {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "board_id")
	private int Id;
	
	@OneToMany(mappedBy = "comment")
	List<Comment> comments = new ArrayList<Comment>();
	
	@Column (name = "board_title", nullable = false, length = 45)
    private String Title;
	
	@Column (name = "board_contents", nullable = false, length = 255)
    private String Contents;
	
	@Column (name = "board_date")
	@Temporal(TemporalType.TIMESTAMP)
    private Date Date;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name = "user_no")
    private User user;

	public Board(int id, String title, String contents, java.util.Date date, User user) {
		super();
		Id = id;
		Title = title;
		Contents = contents;
		Date = date;
		this.user = user;
	}
}
