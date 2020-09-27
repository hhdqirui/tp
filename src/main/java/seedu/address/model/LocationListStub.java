package seedu.address.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LocationListStub {
    private ObservableList<Location> internalList = FXCollections.observableArrayList();
    private final ObservableList<Location> list;

    /**
     * Constructor
     */
    public LocationListStub() {
        internalList.add(new Location("Clementi"));
        internalList.add(new Location("Bedok"));
        internalList.add(new Location("Orchard"));
        this.list = FXCollections.unmodifiableObservableList(internalList);
    }

    public ObservableList<Location> getList() {
        return list;
    }
}
