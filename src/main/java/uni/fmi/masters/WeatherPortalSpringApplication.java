package uni.fmi.masters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.vaadin.artur.helpers.LaunchUtil;

@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
public class WeatherPortalSpringApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(WeatherPortalSpringApplication.class, args));
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
