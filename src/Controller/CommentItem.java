package Controller;

import Model.Comment;
import Model.Post;
import javafx.scene.control.ListCell;

import java.awt.*;
import java.io.IOException;

public class CommentItem extends ListCell<Comment> {
    @Override
    public void updateItem(Comment comment, boolean empty) {
        super.updateItem(comment, empty);
        if (comment != null) {
            setGraphic(new CommentItemController(comment).init());
        }
    }
}
