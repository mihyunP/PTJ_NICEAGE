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

	public Comment save(CommentDTO commentDto) {
		Comment comment = commentDto.toEntity();
		return commentRepository.save(comment);
	}

	public Comment update(CommentDTO commentDto) {
		Comment comment = commentDto.toEntity();
		return commentRepository.save(comment);
		
	}

	public Comment findById(Long commentId) {
		return commentRepository.findByCommentId(commentId);
	}

	public boolean isAbleDelete(Long userNo, Long commentWriterNo, Long commentId) {
		if (userNo == commentWriterNo) {
			commentRepository.deleteById(commentId);
			return true;
		} else {
			return false;			
		}
	}

}
