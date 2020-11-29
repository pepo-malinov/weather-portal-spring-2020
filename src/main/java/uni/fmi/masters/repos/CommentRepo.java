package uni.fmi.masters.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uni.fmi.masters.models.CommentBean;
@Repository
public interface CommentRepo extends JpaRepository<CommentBean, Long>{

}
