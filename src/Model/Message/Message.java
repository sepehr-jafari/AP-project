package Model.Message;

import java.io.Serializable;

public interface Message extends Serializable {
    void handle();
}
