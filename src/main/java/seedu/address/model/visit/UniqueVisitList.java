package seedu.address.model.visit;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.location.exceptions.DuplicateLocationException;
import seedu.address.model.visit.exceptions.DuplicateVisitException;
import seedu.address.model.visit.exceptions.VisitNotFoundException;

/**
 * A list of visits that enforces uniqueness between its elements and does not allow nulls.
 * A visit is considered unique by comparing using {@code visit #equals(Visit)}. As such, adding and
 * updating and deleting of visits uses Visit#equal(Visit) for equality so as to ensure that the visit being
 * added or updated is unique in terms of identity in the UniqueVisitList.
 *
 * Supports a minimal set of list operations.
 *
 * @see Visit #equals(Visit)
 */
public class UniqueVisitList implements Iterable<Visit> {

    private final ObservableList<Visit> internalList = FXCollections.observableArrayList();
    private final ObservableList<Visit> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent visit as the given argument.
     */
    public boolean contains(Visit toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a visit to the list.
     * The visit must not already exist in the list.
     */
    public void add(Visit toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateLocationException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent visit from the list.
     * The visit must exist in the list.
     */
    public void remove(Visit toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new VisitNotFoundException();
        }
    }

    /**
     * Replaces the Visit {@code target} in the list with {@code editedVisit}.
     * {@code target} must exist in the list.
     * The identities of {@code editedVisit} must not be the same as another existing visit in the list.
     */
    public void setVisit(Visit target, Visit editedVisit) {
        requireAllNonNull(target, editedVisit);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new VisitNotFoundException();
        }

        if (target.equals(editedVisit) || contains(editedVisit)) {
            throw new DuplicateLocationException();
        }

        internalList.set(index, editedVisit);
    }
    public void setVisits(UniqueVisitList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code visits}.
     * {@code visits} must not contain duplicate visits.
     */
    public void setVisits(List<Visit> visits) {
        requireAllNonNull(visits);
        if (!visitsAreUnique(visits)) {
            throw new DuplicateVisitException();
        }

        internalList.setAll(visits);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Visit> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Visit> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueVisitList // instanceof handles nulls
                && internalList.equals(((UniqueVisitList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code visits} contains only unique visits.
     */
    private boolean visitsAreUnique(List<Visit> visits) {
        for (int i = 0; i < visits.size() - 1; i++) {
            for (int j = i + 1; j < visits.size(); j++) {
                if (visits.get(i).equals(visits.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
