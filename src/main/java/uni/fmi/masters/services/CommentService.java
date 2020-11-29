package uni.fmi.masters.services;

import java.util.Collection;

import org.springframework.stereotype.Service;

import uni.fmi.masters.models.CommentBean;
import uni.fmi.masters.repos.CommentRepo;

@Service
public class CommentService {
	private CommentRepo commentRepo;

	public CommentService(CommentRepo commentRepo) {
		this.commentRepo = commentRepo;
	}
	
	public CommentBean findById(long id) {
		return commentRepo.findById(id).orElse(null);
	}

	public Collection<CommentBean> findAll() {
		// TODO Auto-generated method stub
		return commentRepo.findAll();
	}
}
