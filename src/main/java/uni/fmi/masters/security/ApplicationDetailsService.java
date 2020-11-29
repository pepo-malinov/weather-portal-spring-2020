package uni.fmi.masters.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import uni.fmi.masters.models.UserBean;
import uni.fmi.masters.repos.UserRepo;

@Service
public class ApplicationDetailsService implements UserDetailsService {

	private UserRepo userRepo;

	public ApplicationDetailsService(final UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserBean user = userRepo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User with username ''" + username + "'' does not exist!");
		}
		return new UserPrinciple(user);
	}

}
