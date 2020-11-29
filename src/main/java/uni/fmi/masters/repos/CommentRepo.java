package uni.fmi.masters.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import uni.fmi.masters.models.CommentBean;

public interface CommentRepo extends JpaRepository<CommentBean, Long>{

}
