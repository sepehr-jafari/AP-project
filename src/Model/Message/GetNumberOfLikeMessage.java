package Model.Message;

import Model.Server.ConnectionHandler;

public class GetNumberOfLikeMessage extends Message2{
    public static final long serialVersionUID = 54683466987L;
    private String UID;

    public GetNumberOfLikeMessage(String UID) {
        this.UID = UID;
    }

    @Override
    public void handle(ConnectionHandler connectionHandler) {
        Long numberOfLike = connectionHandler.getDataBase().getNumberOfLike(UID);
        connectionHandler.SendMessage(new ObjectMessage(numberOfLike));
    }
}
