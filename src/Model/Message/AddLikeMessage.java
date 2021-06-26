package Model.Message;

import Model.Post;
import Model.Server.ConnectionHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddLikeMessage extends Message2{
    public static final long serialVersionUID = 897987234L;
    private String UID;
    private String username;
    private Post post;

    public AddLikeMessage(String UID, String username,Post post) {
        this.UID = UID;
        this.username = username;
        this.post = post;
    }

    @Override
    public void handle(ConnectionHandler connectionHandler) {
        connectionHandler.getDataBase().addLike(post, username);
        connectionHandler.getDataBase().updateLikesAndReposts();
        connectionHandler.getDataBase().updateListOfUser();
        System.out.println("action: like");
        System.out.println(username + " like");
        System.out.println("message: " + post.getUsername() + " " + post.getTitle());
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));

        connectionHandler.SendMessage(new TextMessage2("Like added"));
    }
}
