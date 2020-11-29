package uni.fmi.masters.services;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uni.fmi.masters.models.CommentBean;
import uni.fmi.masters.models.UserBean;
import uni.fmi.masters.repos.CommentRepo;
import uni.fmi.masters.repos.UserRepo;

@Service
public class UserService {

	private UserRepo userRepo;
	private CommentRepo commentRepo;

	@Autowired
	public UserService(UserRepo userRepo, CommentRepo commentRepo) {
		this.userRepo = userRepo;
		this.commentRepo = commentRepo;
	}

	public UserBean findUserByUsernameAndPassword(String username, String password) {
		// validation
		return userRepo.findUserByUsernameAndPassword(username, password);
	}

	public UserBean findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	public Collection<UserBean> findAll() {
		return userRepo.findAll();
	}

	public Collection<UserBean> findByUsernameContaining(final String partOfUsername) {
		if (null == partOfUsername || partOfUsername.isEmpty()) {
			return findAll();
		} else {
			return findByUsernameContaining(partOfUsername);
		}
	}

	@PostConstruct
	public void generateTestData() {
		if (userRepo.count() == 0) {
			UserBean user1 = new UserBean("Ivan", "password", "test@test.com");
			UserBean user2 = new UserBean("Petar", "password", "test1@test.com");
			UserBean user3 = new UserBean("Gosho", "password", "test2@test.com");
			
			userRepo.save(user1);
			userRepo.save(user2);
			userRepo.save(user3);
			CommentBean commentBean = new CommentBean();
			commentBean.setCity("Пловдив");
			commentBean.setComment("Времето е супер!");
			commentBean.setTemp(22.5);
			commentBean.setUser(user1);
			commentRepo.save(commentBean);
			
		}
	}

	public void remove(UserBean selectedUser) {
		userRepo.delete(selectedUser);

	}

	public void save(final UserBean selectedUser) {
		userRepo.save(selectedUser);
		
	}

}
