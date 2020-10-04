package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.visit.Visit;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class VisitCard extends UiPart<Region> {

    private static final String FXML = "VisitListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Visit visit;

    @FXML
    private HBox cardPane;
    @FXML
    private Label date;
    @FXML
    private Label id;
    @FXML
    private Label personName;
    @FXML
    private Label locationName;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public VisitCard(Visit visit, int displayedIndex) {
        super(FXML);
        this.visit = visit;
        id.setText(displayedIndex + ". ");
        date.setText("Date: " + visit.getDate().toString());
        personName.setText("Visit by: " + visit.getPersonName(visit.getPersonId()));
        locationName.setText("Location: " + visit.getLocationName(visit.getLocationId()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        VisitCard card = (VisitCard) other;
        return id.getText().equals(card.id.getText())
                && visit.equals(card.visit);
    }
}