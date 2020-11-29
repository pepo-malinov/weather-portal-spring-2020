package uni.fmi.masters.vaadin.ui;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route("test")
public class TestViwe extends Div {
	public TestViwe() {
		add(new UserForm());
	}
}
