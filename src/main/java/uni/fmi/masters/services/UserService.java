package uni.fmi.masters.services;

import java.util.Collection;
import java.util.HashSet;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import uni.fmi.masters.models.CommentBean;
import uni.fmi.masters.models.RoleBean;
import uni.fmi.masters.models.UserBean;
import uni.fmi.masters.repos.CommentRepo;
import uni.fmi.masters.repos.RoleRepo;
import uni.fmi.masters.repos.UserRepo;

@Service
public class UserService {

	private UserRepo userRepo;
	private CommentRepo commentRepo;
	private PasswordEncoder passwordEncoder;
	RoleRepo roleRepo;

	@Autowired
	public UserService(UserRepo userRepo, CommentRepo commentRepo, PasswordEncoder passwordEncoder, RoleRepo roleRepo) {
		this.userRepo = userRepo;
		this.commentRepo = commentRepo;
		this.passwordEncoder = passwordEncoder;
		this.roleRepo = roleRepo;
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
			UserBean user1 = new UserBean("Ivan", passwordEncoder.encode("password"), "test@test.com");
			RoleBean role = new RoleBean();
			role.setCode("Admin");
			HashSet<RoleBean> roles = new HashSet<>();
			roles.add(roleRepo.save(role));
			user1.setRoles(roles);
			UserBean user2 = new UserBean("Petar", passwordEncoder.encode("password"), "test1@test.com");
			UserBean user3 = new UserBean("Gosho", passwordEncoder.encode("password"), "test2@test.com");

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
