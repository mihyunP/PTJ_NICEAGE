package com.ssafy.niceage.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.niceage.Domain.Board.Board;
import com.ssafy.niceage.Domain.Board.BoardDTO;
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

	public Board findById(Long boardId) {
		return boardRepository.findByBoardId(boardId);
	}

	public List<Board> findAll() {
		return boardRepository.findAll();
	}

	public Board update(BoardDTO boardDto) {
		Board board = boardDto.toEntity();
		return boardRepository.save(board);
		
	}

	public boolean isAbleDelete(Long userNo, Long boardWriterNo, Long boardId) {
		if (userNo == boardWriterNo) {
<<<<<<< HEAD
			boardRepository.deleteByBoardId(boardId);
=======
			boardRepository.deleteById(boardId);
>>>>>>> develope
			return true;
		} else {
			return false;
		}
	}

}
