package Controller;

import Model.ClientAndServerAccess.Comment;
import Model.Main;
import Model.Message.AddCommentMessage;
import Model.PageLoader;
import Model.ClientAndServerAccess.Post;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.util.ArrayList;

public class CommentsController {
    private static ArrayList<Comment> commentArrayList;
    public ListView<Comment> comments;
    public TextArea comment;
    private static Post post;

    @FXML
    public void initialize(){
        if (!commentArrayList.isEmpty()) {
            comments.setItems(FXCollections.observableArrayList(commentArrayList));
            comments.setCellFactory(comments -> new CommentItem());
        }
    }
    public static void setComments(ArrayList<Comment> comments){
        commentArrayList = comments;
    }

    public void Exit(ActionEvent actionEvent) {
        try {
            new PageLoader().load("TimeLine");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendComment(ActionEvent actionEvent) {
        String commentToSend = comment.getText();
        String commenterUsername = LoginController.getUsername();
        Comment comment = new Comment(commenterUsername,commentToSend,ProfileController.profile.getImage());
        Main.client.getResponse(new AddCommentMessage(post.getUID(), comment, post.getUsername(), post));
    }

    public static void setPost(Post Post) {
        post = Post;
    }
}
