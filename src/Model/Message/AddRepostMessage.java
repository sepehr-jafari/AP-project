package Model.Message;

import Model.Post;
import Model.Server.ConnectionHandler;

public class AddRepostMessage extends Message2{
    public static final long serialVersionUID = 9726435L;
    private String UID;
    private String username;
    private Post post;

    public AddRepostMessage(String UID,Post post, String username) {
        this.UID = UID;
        this.username = username;
        this.post = post;

    }

    @Override
    public void handle(ConnectionHandler connectionHandler) {
        connectionHandler.getDataBase().addRepost(post, username);
        connectionHandler.getDataBase().updateLikesAndReposts();
        connectionHandler.getDataBase().updateListOfUser();
        connectionHandler.SendMessage(new TextMessage2("Repost added"));
    }
}
