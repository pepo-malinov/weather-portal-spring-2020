package uni.fmi.masters.vaadin.ui.comment;

import java.util.Collection;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uni.fmi.masters.models.CommentBean;
import uni.fmi.masters.services.CommentService;
import uni.fmi.masters.vaadin.ui.MainView;

@Route(value = "comments", layout = MainView.class)
@PageTitle("Коментари")
public class CommentsView extends Div {

	private static final long serialVersionUID = 1L;
	private Grid<CommentBean> grid = new Grid<>(CommentBean.class, false);
	private CommentService commentService;

	public CommentsView(CommentService commentService) {
		setSizeFull();
		this.commentService = commentService;
		initContent();
	}

	private void initContent() {
		configureGrid();

	}

	private void configureGrid() {
		grid.setSizeFull();
		grid.addColumn("city").setHeader("Населено място");
		grid.addColumn("temp").setHeader("Температура");
		grid.addColumn("comment").setHeader("Коментар");
		grid.addColumn("user").setHeader("Потребител");
		//grid.addColumn(comment->comment.getUser().getUsername()).setHeader("Потребител");
		// add custom column for User
		add(grid);
		updateGrid(commentService.findAll());

	}

	private void updateGrid(Collection<CommentBean> comments) {
		grid.select(null);
		grid.setItems(comments);
	}

}
