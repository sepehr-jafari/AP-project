package Controller;

import Model.ClientAndServerAccess.Person;
import javafx.scene.control.ListCell;

public class ProfileItem  extends ListCell<Person> {
    @Override
    public void updateItem(Person person, boolean empty) {
        super.updateItem(person, empty);
        if (person != null) {
            setGraphic(new ProfileItemController(person).init());
        }
    }
}
