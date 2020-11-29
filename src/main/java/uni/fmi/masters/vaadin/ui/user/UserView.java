package uni.fmi.masters.vaadin.ui.user;

import java.util.Collection;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.selection.SingleSelect;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import uni.fmi.masters.models.UserBean;
import uni.fmi.masters.services.UserService;
import uni.fmi.masters.vaadin.ui.MainView;

@Route(value = "users", layout = MainView.class)
@PageTitle("Users")
public class UserView extends Div {
	private static final long serialVersionUID = 1L;
	private Grid<UserBean> grid = new Grid<>(UserBean.class, false);
	private UserService userService;
	private UserBean selectedUser;
	final Button editUserButton = new Button("Помени");
	final Button removeUserButton = new Button("Изтрий");
	private Button newUserButton;

	public UserView(UserService userService) {
		this.userService = userService;
		setSizeFull();
		configureGrid();
		add(createActionButtons());
		add(grid);

		Collection<UserBean> users = userService.findAll();
		updateGrid(users);
	}

	private HorizontalLayout createActionButtons() {
		newUserButton = new Button("Нов Потребител");
		newUserButton.addClickListener(l -> {
			Dialog dialog = new Dialog();
			UserForm userForm = new UserForm(userService);
			userForm.addOKClickListener(l1 -> {
				if (userForm.isValid()) {
					dialog.close();
					Collection<UserBean> users = userService.findAll();
					updateGrid(users);
				}
			});
			userForm.addCloseClickListener(l1 -> dialog.close());
			dialog.add(userForm);
			dialog.open();
		});

		editUserButton.setEnabled(false);
		editUserButton.addClickListener(listener -> {
			Dialog dialog = new Dialog();
			UserForm userForm = new UserForm(userService, selectedUser);
			userForm.addOKClickListener(l1 -> {
				if (userForm.isValid()) {
					dialog.close();
					Collection<UserBean> users = userService.findAll();
					updateGrid(users);
				}
			});
			userForm.addCloseClickListener(l1 -> dialog.close());
			dialog.add(userForm);
			dialog.open();
		});
		removeUserButton.setEnabled(false);
		removeUserButton.addClickListener(listener -> {
			final Dialog dialog = new Dialog();
			dialog.add(new Label("Да изтрия ли потребителя?"));
			final Button yesButton = new Button("ДА", l -> {
				userService.remove(selectedUser);
				dialog.close();
				Collection<UserBean> users = userService.findAll();
				updateGrid(users);
			});

			Button notButton = new Button("НЕ", l -> dialog.close());
			dialog.add(new HorizontalLayout(yesButton, notButton));
			dialog.open();
		});
		HorizontalLayout actions = new HorizontalLayout(newUserButton, editUserButton, removeUserButton);
		return actions;
	}

	private void updateGrid(Collection<UserBean> users) {
		grid.setItems(users);
	}

	private void configureGrid() {
		grid.setSizeFull();
		final Column<UserBean> nameColumn = grid.addColumn("username").setHeader("Потребителско име").setSortable(true);
		grid.addColumn("email").setHeader("Поща").setSortable(true);
		grid.addColumn("id").setHeader("ID");
		grid.setSelectionMode(SelectionMode.SINGLE);
		SingleSelect<Grid<UserBean>, UserBean> selectedObject = grid.asSingleSelect();
		selectedObject.addValueChangeListener(listener -> {
			selectedUser = listener.getValue();
			editUserButton.setEnabled(selectedUser != null);
			removeUserButton.setEnabled(selectedUser != null);
		});
		final HeaderRow prependHeaderRow = grid.prependHeaderRow();
		final TextField nameFilterField = new TextField();
		nameFilterField.setPlaceholder("Филтрирайте по име...");
		nameFilterField.addValueChangeListener(l -> {
			String value = l.getValue();
			Collection<UserBean> users = userService.findByUsernameContaining(value);
			updateGrid(users);
		});
		prependHeaderRow.getCell(nameColumn).setComponent(nameFilterField);

	}
}
