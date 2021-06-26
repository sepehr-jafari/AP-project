package Model.Server;

import Model.Comment;
import Model.Person;
import Model.Post;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public class DataBase {
    private boolean firstTime = true;
    private static DataBase dataBase;
    private static File file_ClientsAccount;
    private static File file_ListOfUsers;
    private  static File file_LikesAndReposts;
    private final static String fileAddress_ClientAccounts ="F:\\AP-project\\src\\Model\\Server\\Data\\clientsAccount.txt";
    private final static String fileAddress_ListOfUsers="F:\\AP-project\\src\\Model\\Server\\Data\\listOfUsers.txt";
    private final static String fileAddress_LikesAndReposts = "F:\\AP-project\\src\\Model\\Server\\Data\\likesAndReposts.txt";
    private ConcurrentHashMap<Person, String> clientsAccount;
    private ConcurrentHashMap<String,ArrayList<Long>> postsLikeAndRepost;
    private ConcurrentLinkedQueue<Person> Users;
    private final Semaphore LockForInitializeDatabase = new Semaphore(1);
    private final Semaphore LockForAddingClient_ClientHandler = new Semaphore(1);
    private final Semaphore LockForInitializeDatabase_ListOfUsers = new Semaphore(1);
    private final Semaphore LockForAddingUserIntoList = new Semaphore(1);
    private final Semaphore LockForUpDatingListOfUsers = new Semaphore(1);
    private final Semaphore LockForSetFollowerFollowing = new Semaphore(1);
    private final Semaphore LockForEditingUser = new Semaphore(1);
    private final Semaphore LockForUpdatingLikesAndReposts = new Semaphore(1);
    private final Semaphore LockForInitializeLikesAndPosts = new Semaphore(1);
    private final Semaphore LockForAddingLike = new Semaphore(1);
    private final Semaphore LockForAddingRepost = new Semaphore(1);
    private final Semaphore LockForAddingThePostToMyPost = new Semaphore(1);
    private final Semaphore LockForAddingComment = new Semaphore(1);
    private DataBase() {
        clientsAccount = new ConcurrentHashMap<>();

        Users = new ConcurrentLinkedQueue<>();
        postsLikeAndRepost = new ConcurrentHashMap<>();
    }

    public static DataBase getDataBase() {
        if (dataBase == null) {
            dataBase = new DataBase();
            file_ClientsAccount = new File(fileAddress_ClientAccounts);
            file_ListOfUsers = new File(fileAddress_ListOfUsers);
            file_LikesAndReposts = new File(fileAddress_LikesAndReposts);
            if (!file_ClientsAccount.exists()){
                try {
                    file_ClientsAccount.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (!file_ListOfUsers.exists()){
                try {
                    file_ListOfUsers.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (!file_LikesAndReposts.exists()){
                try {
                    file_LikesAndReposts.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return dataBase;
    }

    public ConcurrentHashMap<Person, String> getData() {
        return clientsAccount;
    }

    public void AddClient_InClientHandler(Person person) {
        try {
            LockForAddingClient_ClientHandler.acquire();
            clientsAccount.put(person, person.getPassword());
            FileOutputStream os = new FileOutputStream(file_ClientsAccount);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(clientsAccount);
            oos.flush();
            oos.reset();
            oos.close();
            os.close();
            LockForAddingClient_ClientHandler.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void InitializeClientsAccount(){
        if (clientsAccount.isEmpty()&&file_ClientsAccount.length()!=0) {
            try {
                LockForInitializeDatabase.acquire();
                FileInputStream fis = new FileInputStream(file_ClientsAccount);
                ObjectInputStream ois = new ObjectInputStream(fis);
                this.clientsAccount = (ConcurrentHashMap<Person, String>) ois.readObject();
                ois.close();
                fis.close();
                LockForInitializeDatabase.release();
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    public void InitializeListOfUsers(){
        if (Users.isEmpty()&&file_ListOfUsers.length()!=0) {
            try {
                LockForInitializeDatabase_ListOfUsers.acquire();
                FileInputStream is = new FileInputStream(file_ListOfUsers);
                ObjectInputStream ois = new ObjectInputStream(is);
                this.Users = (ConcurrentLinkedQueue<Person>) ois.readObject();
                ois.close();
                is.close();

                LockForInitializeDatabase_ListOfUsers.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public void InitializeLikesAndReposts(){
        try {
            LockForInitializeLikesAndPosts.acquire();
            if (postsLikeAndRepost.isEmpty()&&file_LikesAndReposts.length()!=0){
                FileInputStream is = new FileInputStream(file_LikesAndReposts);
                ObjectInputStream ois = new ObjectInputStream(is);
                this.postsLikeAndRepost = (ConcurrentHashMap<String, ArrayList<Long>>) ois.readObject();
                ois.close();
                is.close();
            }
            LockForInitializeLikesAndPosts.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ConcurrentLinkedQueue<Person> getListOfUsers(){
        return this.Users;
    }
    public void AddPost_InClientHandler(Post post){
        String Username = post.getUsername();
        ArrayList<Long> arrayList = new ArrayList<>();
        arrayList.add(0L);
        arrayList.add(0L);
        postsLikeAndRepost.put(post.getUID(),arrayList);
        for (Person p: Users) {
            if (p.getUsername().equals(Username)){
                p.setPost(post);
                break;
            }

        }
        try {
            LockForUpDatingListOfUsers.acquire();
            FileOutputStream os = new FileOutputStream(file_ListOfUsers);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(Users);
            oos.flush();
            oos.reset();
            oos.close();
            os.close();
            LockForUpDatingListOfUsers.release();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    public void SaveUser(Person person){
        try{
            LockForAddingUserIntoList.acquire();
            Users.add(person);
            FileOutputStream os = new FileOutputStream(file_ListOfUsers);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(Users);
            oos.flush();
            oos.reset();
            oos.close();
            os.close();

            LockForAddingUserIntoList.release();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
    public void updateListOfUser(){
        try {
            LockForUpDatingListOfUsers.acquire();
            FileOutputStream os = new FileOutputStream(file_ListOfUsers);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(Users);
            oos.flush();
            oos.reset();
            oos.close();
            os.close();
            LockForUpDatingListOfUsers.release();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void setFollowerFollowing(String follower, String following,boolean unfollow){
        try{
            LockForSetFollowerFollowing.acquire();
            Person followerPerson = null;
            Person followingPerson = null;
            int counter =0;
            for (Person p: Users) {
                if (counter==2){
                    break;
                }
                if (p.getUsername().equals(follower)){
                    followerPerson = p;
                    ++counter;
                }
                if (p.getUsername().equals(following)){
                    followingPerson = p;
                    ++counter;
                }
            }
            if (unfollow==false) {
                followerPerson.setFollowing(following);
                followingPerson.setFollower(follower);
            }else if (unfollow==true){
                followerPerson.removeFollowing(following);
                followingPerson.removeFollowers(follower);
            }
            LockForSetFollowerFollowing.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void EditUser(byte[] image, String name, String surname, String email, String password, String Username){
        try {
            LockForEditingUser.acquire();
            for (Person p:Users) {
                if (p.getUsername().equals(Username)){
                    if (image!=null){
                        p.setImage(image);
                    }
                    if (name!=null){
                        p.setName(name);
                    }
                    if (surname!=null){
                        p.setSurName(surname);
                    }
                    if (email!=null){
                        p.setEmail(email);
                    }if (password!=null){
                        p.setPassword(password);
                    }
                    break;
                }
            }


            LockForEditingUser.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public Long getNumberOfLike(String UID){
        if (postsLikeAndRepost.isEmpty()){
            return 0L;
        }else {
            ArrayList<Long> arrayList = postsLikeAndRepost.get(UID);
            return arrayList.get(0);
        }
    }
    public Long getNumberOfRepost(String UID){
        if (postsLikeAndRepost.isEmpty()){
            return 0L;
        }
        ArrayList<Long> arrayList = postsLikeAndRepost.get(UID);
        return arrayList.get(1);
    }
    public void addLike(Post post, String username){
        ArrayList<Long> arrayList = postsLikeAndRepost.get(post.getUID());
        ArrayList<String> usernamesThatLike = post.getUsernamesThatLike();
        for (int i = 0; i < usernamesThatLike.size(); i++) {
            if (usernamesThatLike.get(i).equals(username)){
                return;
            }
        }
        try {
            LockForAddingLike.acquire();
            for (Person p : Users) {
                if (p.getUsername().equals(post.getUsername())) {
                    ArrayList<Post> postArrayList = p.getPosts();
                    for (int j = 0; j < postArrayList.size(); j++) {
                        if (postArrayList.get(j).getUID().equals(post.getUID())) {
                            postArrayList.get(j).setUsernamesThatLike(username);
                            break;
                        }
                    }
                }
            }
            Long newNumberOfLike = 1 + arrayList.get(0);
            arrayList.set(0, newNumberOfLike);

            postsLikeAndRepost.put(post.getUID(), arrayList);
            LockForAddingLike.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void addRepost(Post post, String username){
        ArrayList<Long> arrayList = postsLikeAndRepost.get(post.getUID());
        ArrayList<String> usernamesThatRepost = post.getUsernamesThatRepost();
        for (int i = 0; i < usernamesThatRepost.size(); i++) {
            if (usernamesThatRepost.get(i).equals(username)){
                return;
            }
        }
        try {
            LockForAddingRepost.acquire();
            for (Person p : Users) {
                if (p.getUsername().equals(post.getUsername())) {
                    ArrayList<Post> postArrayList = p.getPosts();
                    for (int i = 0; i < postArrayList.size(); i++) {
                        if (postArrayList.get(i).getUID().equals(post.getUID())) {
                            postArrayList.get(i).setUsernamesThatRepost(username);
                            break;
                        }
                    }
                }
            }
            Long newNumberOfRepost = 1 + arrayList.get(1);
            arrayList.set(1, newNumberOfRepost);
            postsLikeAndRepost.put(post.getUID(), arrayList);
            LockForAddingRepost.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateLikesAndReposts(){
        try {
            LockForUpdatingLikesAndReposts.acquire();
            FileOutputStream os = new FileOutputStream(file_LikesAndReposts);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(postsLikeAndRepost);
            oos.flush();
            oos.reset();
            oos.close();
            os.close();
            LockForUpdatingLikesAndReposts.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void AddThisPostToMyPosts(Post post, String username){
        try {
            LockForAddingThePostToMyPost.acquire();
            for (Person p : Users) {
                if (p.getUsername().equals(username)) {
                    p.setPost(post);
                    break;
                }

            }
            LockForAddingThePostToMyPost.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void addComment(Comment comment, String UID, String username){
        try {
            LockForAddingComment.acquire();
            for (Person p : Users) {
                if (p.getUsername().equals(username)) {
                    ArrayList<Post> comments = p.getPosts();
                    for (Post post : comments) {
                        if (post.getUID().equals(UID)) {
                            post.setComment(comment);
                            break;
                        }
                    }
                }

            }
            LockForAddingComment.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
