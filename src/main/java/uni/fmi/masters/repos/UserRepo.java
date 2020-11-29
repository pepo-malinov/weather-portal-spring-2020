package uni.fmi.masters.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uni.fmi.masters.models.UserBean;

@Repository
public interface UserRepo extends JpaRepository<UserBean, Long>{

	UserBean findUserByUsernameAndPassword(String username, String password);
	
	UserBean findByUsername(String username);
	
	List<UserBean> findByUsernameContaining(String partOfUsername);
}
