package uni.fmi.masters.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import uni.fmi.masters.models.NotificationBean;

public interface NotificationRepo  extends JpaRepository<NotificationBean, Long>{

}
