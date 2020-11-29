package uni.fmi.masters.security;

import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.flow.server.ServletHelper;
import com.vaadin.flow.shared.ApplicationConstants;

import uni.fmi.masters.models.UserBean;

public final class SecurityUtils {

	private SecurityUtils() {
	}

	static boolean isFrameworkInternalRequest(HttpServletRequest request) {

		final String parameterValue = request.getParameter(ApplicationConstants.REQUEST_TYPE_PARAMETER);
		return parameterValue != null && Stream.of(ServletHelper.RequestType.values())
				.anyMatch(r -> r.getIdentifier().equals(parameterValue));
	}

	static boolean isUserLoggedIn() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null && !(authentication instanceof AnonymousAuthenticationToken)
				&& authentication.isAuthenticated();
	}

	public static UserBean getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrinciple principal = (UserPrinciple) authentication.getPrincipal();

		return principal != null ? principal.getUser() : null;
	}

}
