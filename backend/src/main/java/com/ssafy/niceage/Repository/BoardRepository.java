package com.ssafy.niceage.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.niceage.Domain.Board.Board;
import com.ssafy.niceage.Domain.Board.BoardDTO;
import com.ssafy.niceage.Domain.User.User;

public interface BoardRepository extends JpaRepository<Board, Long> {

	Board findByBoardId(Long boardId);
	
}
