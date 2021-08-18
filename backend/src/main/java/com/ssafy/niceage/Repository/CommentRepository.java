package com.ssafy.niceage.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.niceage.Domain.Comment.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	Comment findByCommentId(Long commentId);
	
}
