package Model.Message;

import Model.ClientAndServerAccess.ConnectionHandler;

public class GetNumberOfRepostMessage extends Message2{
    public static final long serialVersionUID = 287263109L;
    private String UID;

    public GetNumberOfRepostMessage(String UID) {
        this.UID = UID;
    }

    @Override
    public void handle(ConnectionHandler connectionHandler) {
        Long numberOfRepost = connectionHandler.getDataBase().getNumberOfRepost(UID);
        connectionHandler.SendMessage(new ObjectMessage(numberOfRepost));
    }
}
