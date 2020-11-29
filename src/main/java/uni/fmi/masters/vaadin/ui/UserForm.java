package uni.fmi.masters.vaadin.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.data.validator.StringLengthValidator;

import uni.fmi.masters.models.UserBean;

public class UserForm extends FormLayout {
	private static final long serialVersionUID = 1L;

	private TextField useranemeFiled = new TextField("Потребителско име");
	private PasswordField passwordFiled = new PasswordField("Парола");
	private PasswordField secondPasswordFiled = new PasswordField("Повтори парола");
	private EmailField emailFiled = new EmailField("Електронна поща");
	private Button okButton = new Button("OK");
	private Button closeButton = new Button("Отказ");
	private UserBean selectedUser;
	private Binder<UserBean> binder = new Binder<>();

	public UserForm() {
		this(new UserBean());
	}

	public UserForm(final UserBean selectedUser) {
		this.selectedUser = selectedUser;
		HorizontalLayout actions = new HorizontalLayout(okButton, closeButton);
		actions.setDefaultVerticalComponentAlignment(Alignment.CENTER);
		add(useranemeFiled, passwordFiled, secondPasswordFiled, emailFiled, actions);
		binder.forField(useranemeFiled).asRequired()
				.withValidator(new StringLengthValidator("Въведете име с дължина между 3 и 20 синвола", 3, 20))
				.bind(UserBean::getUsername, UserBean::setUsername);
		binder.forField(passwordFiled).asRequired()
				.withValidator(new StringLengthValidator("Въведете парола с дължина между 3 и 20 синвола", 3, 20))
				.bind(UserBean::getPassword, UserBean::setPassword);
		binder.forField(emailFiled).asRequired()
		.withValidator(new EmailValidator("Въведете валидна поща!"))
		.bind(UserBean::getEmail, UserBean::setEmail);
		binder.readBean(selectedUser);
	}

}
