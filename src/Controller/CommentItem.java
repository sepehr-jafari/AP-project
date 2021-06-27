package Controller;

import Model.ClientAndServerAccess.Comment;
import javafx.scene.control.ListCell;

public class CommentItem extends ListCell<Comment> {
    @Override
    public void updateItem(Comment comment, boolean empty) {
        super.updateItem(comment, empty);
        if (comment != null) {
            setGraphic(new CommentItemController(comment).init());
        }
    }
}
