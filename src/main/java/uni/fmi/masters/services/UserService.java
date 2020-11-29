package uni.fmi.masters.services;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uni.fmi.masters.models.UserBean;
import uni.fmi.masters.repos.UserRepo;

@Service
public class UserService {

	private UserRepo userRepo;

	@Autowired
	public UserService(UserRepo userRepo) {
		this.userRepo = userRepo;
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
		}
	}

	public void remove(UserBean selectedUser) {
		userRepo.delete(selectedUser);

	}

}
