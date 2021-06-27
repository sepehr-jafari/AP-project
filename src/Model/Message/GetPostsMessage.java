package Model.Message;

import Model.ClientAndServerAccess.Person;
import Model.ClientAndServerAccess.Post;
import Model.ClientAndServerAccess.ConnectionHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

public class GetPostsMessage extends Message2{
    public static final long serialVersionUID = 25762586523L;
    private final Person person;

    public GetPostsMessage(Person person) {
        this.person = person;
    }
    @Override
    public void handle(ConnectionHandler connectionHandler) {
        String username = person.getUsername();
        ArrayList<String> followers = person.getFollowing();
        ConcurrentLinkedQueue<Person> users = connectionHandler.getDataBase().getListOfUsers();
        ArrayList<ArrayList<Post>> posts = new ArrayList<>();
        for (Person p:users) {
            for (int i = 0; i < followers.size(); i++) {
                if (followers.get(i).equals(p.getUsername())){
                    posts.add(p.getPosts());
                    break;
                }
            }
        }
        posts.add(person.getPosts());
        System.out.println("action: get posts");
        System.out.println(person.getUsername() + " get post list");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        connectionHandler.SendMessage(new ObjectMessage(posts));
    }
}
