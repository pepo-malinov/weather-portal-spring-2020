package uni.fmi.masters.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class UserBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "username", nullable = false, unique = true, length = 40)
	private String username;

	@Column(name = "password", nullable = false, length = 32)
	private String password;

	@Column(name = "email", nullable = false, unique = true, length = 256)
	private String email;

	@Column(name = "image")
	private String avatarPath;

	public UserBean() {
	}

	public UserBean(String username, String email) {
		this.username = username;
		this.email = email;
	}

	public UserBean(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

}
