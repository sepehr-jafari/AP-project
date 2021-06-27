package Model.Message;

import Model.ClientAndServerAccess.Post;
import Model.ClientAndServerAccess.ConnectionHandler;

public class RepostMessage extends Message2{
    public static final long serialVersionUID =67500077L;
    private Post post;
    private String username;

    public RepostMessage(Post post, String username) {
        this.post = post;
        this.username = username;
    }

    @Override
    public void handle(ConnectionHandler connectionHandler) {
        connectionHandler.getDataBase().AddThisPostToMyPosts(post,username);
        connectionHandler.getDataBase().updateListOfUser();
        connectionHandler.SendMessage(new TextMessage2("The post add to your post"));
    }
}
