package uni.fmi.masters.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "role")
@Data
public class RoleBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "role_id")
	@GeneratedValue
	private long id;

	@Column(name = "code", unique = true, nullable = false)
	private String code;

	@Column(name = "description", nullable = true)
	private String description;

}
