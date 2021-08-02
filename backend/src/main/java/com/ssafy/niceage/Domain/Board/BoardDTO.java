package com.ssafy.niceage.Domain.Board;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class BoardDTO {
	@Id @GeneratedValue
	@Column (name = "board_id")
	private int Id;
	@Column (name = "board_title")
    private String Title;
	@Column (name = "board_contents")
    private String Contents;
	@Column (name = "board_date")
    private String Date;
	@Column (name = "user_no")
    private int userNo;
	public BoardDTO(int id, String title, String contents, String date, int no) {
		super();
		Id = id;
		Title = title;
		Contents = contents;
		Date = date;
		userNo = no;
	}

	
}
