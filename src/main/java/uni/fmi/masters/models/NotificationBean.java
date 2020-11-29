package uni.fmi.masters.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "notification")
@Data
public class NotificationBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private UserBean fromUser;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private UserBean toUser;

	@Column(name = "status", length = 20)
	private String status;

	@Column(name = "date")
	private Date date;

}
