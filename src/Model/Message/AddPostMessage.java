package Model.Message;

import Model.ClientAndServerAccess.Post;
import Model.ClientAndServerAccess.ConnectionHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddPostMessage extends Message2{
    public static final long serialVersionUID = 2457541254L;
    private final Post post;

    public AddPostMessage(Post post) {
        this.post = post;
    }

    @Override
    public void handle(ConnectionHandler connectionHandler) {
        connectionHandler.getDataBase().AddPost_InClientHandler(post);
        connectionHandler.getDataBase().updateLikesAndReposts();
        System.out.println("action: publish post");
        System.out.println(post.getUsername() + " publish");
        System.out.println("message: " + post.getTitle() + " " + post.getUsername());
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));

        connectionHandler.SendMessage(new TextMessage2("New post added"));
    }
}
