package com.ssafy.niceage.Service;

import org.springframework.stereotype.Service;

import com.ssafy.niceage.Controller.Request.BoardRequest;
import com.ssafy.niceage.Domain.Board.Board;
import com.ssafy.niceage.Repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;

	public Board save(BoardRequest request) {
//		return boardRepository.save(request);
		return null;
	}
}
