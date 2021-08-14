package com.ssafy.niceage.Domain.Board;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.print.attribute.standard.DateTimeAtCreation;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.niceage.Domain.Comment.Comment;
import com.ssafy.niceage.Domain.User.User;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Board implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "board_id")
	private Long boardId;
	
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval =true)
	List<Comment> comments = new ArrayList<Comment>();
	
	@Column (name = "board_title", nullable = false, length = 45)
    private String boardTitle;
	
	@Column (name = "board_contents", nullable = false, length = 255)
    private String boardContents;
	
	@JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss")
	@Column (name = "board_date", updatable=false)
	private Date boardDate;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name = "user_no")
    private User user;

	@Builder
	public Board(Long boardId, String boardTitle, String boardContents, User user) {
		this.boardId = boardId;
		this.boardTitle = boardTitle;
		this.boardContents = boardContents;
		this.user = user;
	}
	
	@PrePersist
	private void onCreate() {
        this.boardDate = new Date();
    }

}
