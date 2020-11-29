package uni.fmi.masters.vaadin.ui.login;

import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("login")
@PageTitle("Вход в системата")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

	private static final long serialVersionUID = 1L;

	private LoginForm loginForm = new LoginForm();

	public LoginView() {
		setSizeFull();
		setAlignItems(Alignment.CENTER);
		setJustifyContentMode(JustifyContentMode.CENTER);
		loginForm.setAction("login");
		loginForm.setI18n(createPortugueseI18n());
		add(loginForm);

	}

	private LoginI18n createPortugueseI18n() {
	    final LoginI18n i18n = LoginI18n.createDefault();

	    i18n.setHeader(new LoginI18n.Header());
	    i18n.getHeader().setTitle("Вход в системата");
	    i18n.getHeader().setDescription("Форма за оторизация на достъп до приложението");
	    i18n.getForm().setUsername("Потребителско име");
	    i18n.getForm().setTitle("Въведете вашите потребителски данни");
	    i18n.getForm().setSubmit("Вход");
	    i18n.getForm().setPassword("Парола");
	    i18n.getForm().setForgotPassword("Забравена парола");
	    i18n.getErrorMessage().setTitle("Грешка при влизане в системата.");
	    i18n.getErrorMessage()
	        .setMessage("Въвели сте грешни потребителски данни.");
	    i18n.setAdditionalInformation(
	        "Въвели сте грешни потребителски данни.");
	    return i18n;
	}
	
	
	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		if (event.getLocation().getQueryParameters().getParameters().containsKey("error")) {
			loginForm.setError(true);
		}

	}

}
