package Model.Message;

import Model.Server.ConnectionHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UnfollowMessage extends Message2{
    public static final long serialVersionUID = 5864284L;
    private String following_me;
    private String follower_him;

    public UnfollowMessage(String following_me, String follower_him) {
        this.following_me = following_me;
        this.follower_him = follower_him;
    }

    @Override
    public void handle(ConnectionHandler connectionHandler) {
        connectionHandler.getDataBase().setFollowerFollowing(following_me,follower_him,true);
        connectionHandler.getDataBase().updateListOfUser();
        System.out.println("action: unfollow");
        System.out.println(following_me + " unfollow");
        System.out.println("message: " + follower_him);
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        connectionHandler.SendMessage(new TextMessage2("Unfollow"));

    }
}
