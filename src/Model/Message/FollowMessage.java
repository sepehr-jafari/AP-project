package Model.Message;

import Model.ClientAndServerAccess.ConnectionHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FollowMessage extends Message2{
    public static final long serialVersionUID = 9845442L;
    private String follower_me;
    private String following_him;

    public FollowMessage(String follower_me, String following_him) {
        this.follower_me = follower_me;
        this.following_him = following_him;
    }

    @Override
    public void handle(ConnectionHandler connectionHandler) {
        connectionHandler.getDataBase().setFollowerFollowing(follower_me,following_him,false);
        connectionHandler.getDataBase().updateListOfUser();
        System.out.println("action: follow");
        System.out.println(follower_me + " follow");
        System.out.println("message: " + following_him);
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        connectionHandler.SendMessage(new TextMessage2("Following and follower set"));

    }
}
