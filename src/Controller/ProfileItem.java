package Controller;

import Model.Person;
import Model.Post;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class ProfileItem  extends ListCell<Person> {
    @Override
    public void updateItem(Person person, boolean empty) {
        super.updateItem(person, empty);
        if (person != null) {
            setGraphic(new ProfileItemController(person).init());
        }
    }
}
