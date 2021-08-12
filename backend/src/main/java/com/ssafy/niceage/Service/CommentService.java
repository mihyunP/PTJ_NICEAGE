package com.ssafy.niceage.Service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.niceage.Domain.Comment.Comment;
import com.ssafy.niceage.Domain.Comment.CommentDTO;
import com.ssafy.niceage.Repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
	
	private final CommentRepository commentRepository;

	@Transactional
	public Comment save(CommentDTO commentDto) {
		Comment comment = commentDto.toEntity();
		return commentRepository.save(comment);
	}

	@Transactional
	public Comment update(CommentDTO commentDto) {
		Comment comment = commentDto.toEntity();
		return commentRepository.save(comment);
		
	}

	@Transactional
	public Comment findByCommentId(Long commentId) {
		return commentRepository.findByCommentId(commentId);
	}

	@Transactional
	public boolean isAbleDelete(Long userNo, Long commentWriterNo, Long commentId) {
		if (userNo == commentWriterNo) {
			commentRepository.deleteById(commentId);
			return true;
		} else {
			return false;			
		}
	}

}
