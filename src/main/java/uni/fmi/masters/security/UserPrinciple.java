package uni.fmi.masters.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import uni.fmi.masters.models.RoleBean;
import uni.fmi.masters.models.UserBean;

public class UserPrinciple implements UserDetails {
	private static final long serialVersionUID = 1L;
	private UserBean user;
	private Collection<GrantedAuthority> authorities = new ArrayList<>();

	public UserPrinciple(final UserBean user) {
		this.user = user;
		final Set<RoleBean> roles = user.getRoles();
		if (null != roles && !roles.isEmpty()) {
			roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getCode())));
		} else {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public UserBean getUser() {
		return user;
	}

}
