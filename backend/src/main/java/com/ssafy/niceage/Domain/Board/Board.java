package com.ssafy.niceage.Domain.Board;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.junit.Assert;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ssafy.niceage.Controller.Request.BoardRequest;
import com.ssafy.niceage.Domain.Comment.Comment;
import com.ssafy.niceage.Domain.User.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "board_id")
	private Long boardId;
	
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval =true)
	List<Comment> comments = new ArrayList<Comment>();
	
	@Column (name = "board_title", nullable = false, length = 45)
    private String boardTitle;
	
	@Column (name = "board_contents", nullable = false, length = 255)
    private String boardContents;
	
	@CreationTimestamp
	@Column (name = "board_date")
	private LocalDateTime boardDate;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name = "user_no")
    private User user;

		
}
