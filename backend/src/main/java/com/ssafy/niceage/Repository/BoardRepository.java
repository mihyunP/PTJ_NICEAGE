package com.ssafy.niceage.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.niceage.Domain.Board.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	
}
