package com.ssafy.niceage.Domain.Board;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Board {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "board_id")
	private Long boardId;
	
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval =true)
	List<Comment> comments;
	
	@Column (name = "board_title", nullable = false, length = 45)
    private String boardTitle;
	
	@Column (name = "board_contents", nullable = false, length = 255)
    private String boardContents;
	
	@JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss")
	@Column (name = "board_date", updatable = true)
	private LocalDateTime boardDate;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name = "user_no")
    private User user;

}
