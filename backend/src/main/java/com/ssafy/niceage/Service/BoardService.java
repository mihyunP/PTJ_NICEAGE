package com.ssafy.niceage.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.niceage.Controller.MainResponse;
import com.ssafy.niceage.Controller.Request.BoardRequest;
import com.ssafy.niceage.Domain.Board.Board;
import com.ssafy.niceage.Domain.Board.BoardDTO;
import com.ssafy.niceage.Domain.User.User;
import com.ssafy.niceage.Repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
	private final BoardRepository boardRepository;

	/**
	 * 게시글 작성
	 * @param boardDto
	 * @return
	 */
	public Board save(BoardDTO boardDto) {
		Board board = boardDto.toEntity();
		return boardRepository.save(board);
	}

	/**
	 * 게시글을 읽는데 내가 쓴 게시글인지 다른 사람이 쓴 게시글인지 true, false로 return
	 * @param boardDto
	 * @param user
	 * @return true : 내가 작성한 게시글 / false : 다른 사람이 작성한 게시글
	 */
	public boolean checkUserId(Long userNo, Long boardWriterNo) {
		if (userNo == boardWriterNo) {
			return true;
		}
		else {
			return false;
		}
	}

	public Board findById(Long boardId) {
		return boardRepository.findByBoardId(boardId);
	}

	public boolean deleteBoard(boolean isCheck, Long boardId) {
		if (isCheck) {
			boardRepository.deleteByBoardId(boardId);
			return true;
		} else {
			return false;
		}
	}

	public List<Board> findAll() {
		return boardRepository.findAll();
	}

//	public Board update(BoardDTO boardDto) {
//		Board board = boardDto.toEntity();
//		return boardRepository.save(board);
//		
//	}

}
