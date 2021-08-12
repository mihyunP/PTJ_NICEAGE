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
	@Transactional
	public Board save(BoardDTO boardDto) {
		Board board = boardDto.toEntity();
		return boardRepository.save(board);
	}

	@Transactional
	public Board findById(Long boardId) {
		return boardRepository.findByBoardId(boardId);
	}

	@Transactional
	public List<Board> findAll() {
		return boardRepository.findAll();
	}

	@Transactional
	public Board update(BoardDTO boardDto) {
		Board board = boardDto.toEntity();
		return boardRepository.save(board);
		
	}

	@Transactional
	public boolean isAbleDelete(Long userNo, Long boardWriterNo, Long boardId) {
		if (userNo == boardWriterNo) {
			boardRepository.deleteById(boardId);
			return true;
		} else {
			return false;
		}
	}

}
