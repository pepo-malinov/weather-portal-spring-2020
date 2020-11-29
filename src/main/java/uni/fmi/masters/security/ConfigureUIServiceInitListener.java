package uni.fmi.masters.security;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;

import uni.fmi.masters.vaadin.ui.MainView;
import uni.fmi.masters.vaadin.ui.comment.CommentsView;
import uni.fmi.masters.vaadin.ui.login.LoginView;

@Component
public class ConfigureUIServiceInitListener implements VaadinServiceInitListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void serviceInit(ServiceInitEvent event) {
		event.getSource().addUIInitListener(uiEvent -> {
			final UI ui = uiEvent.getUI();
			ui.addBeforeEnterListener(this::authenticateNavigation);
		});

	}

	private void authenticateNavigation(BeforeEnterEvent event) {
		if (!LoginView.class.equals(event.getNavigationTarget()) && !SecurityUtils.isUserLoggedIn()) {
			event.rerouteTo(LoginView.class);
		}
	}

}
