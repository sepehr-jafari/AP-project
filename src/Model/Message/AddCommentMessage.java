package Model.Message;

import Model.ClientAndServerAccess.Comment;
import Model.ClientAndServerAccess.Post;
import Model.ClientAndServerAccess.ConnectionHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCommentMessage extends Message2{
    public static final long serialVersionUID = 2500826502L;
    private String UID;
    private Comment comment;
    private String username;
    private Post post;

    public AddCommentMessage(String UID, Comment comment, String username,Post post) {
        this.UID = UID;
        this.comment = comment;
        this.username = username;
        this.post=post;
    }

    @Override
    public void handle(ConnectionHandler connectionHandler) {
        connectionHandler.getDataBase().addComment(comment,UID,username);
        connectionHandler.getDataBase().updateListOfUser();
        System.out.println("action: comment");
        System.out.println(username + " comment");
        System.out.println("message: " + post.getTitle());
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        connectionHandler.SendMessage(new TextMessage2("Comment set"));
    }
}
