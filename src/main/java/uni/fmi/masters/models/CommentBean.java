package uni.fmi.masters.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "comment")
@Data
public class CommentBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "temp", length = 5, precision = 2, nullable = true)
	private double temp;

	@Column(name = "city", length = 250, nullable = false)
	private String city;

	@Column(name = "comment", length = 1000)
	private String comment;

	@Column(name = "icon", length = 256, nullable = true)
	private String icon;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private UserBean user;

}
