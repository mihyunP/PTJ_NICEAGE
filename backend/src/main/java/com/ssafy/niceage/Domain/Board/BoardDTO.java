package com.ssafy.niceage.Domain.Board;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "게시판 DTO", description = "ㅇ")
public class BoardDTO {
	private Long boardId;
	private String boardTitle;
	private String boardContents;
	private Date boardDate;
	private Long userNo;
	
	
	public BoardDTO(Board board) {
		this.boardTitle = board.getBoardTitle();
		this.boardContents = board.getBoardContents();
		
	
	}
	
	
}
