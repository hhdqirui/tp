package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.attribute.Address;
import seedu.address.model.attribute.Email;
import seedu.address.model.attribute.Id;
import seedu.address.model.attribute.InfectionStatus;
import seedu.address.model.attribute.Name;
import seedu.address.model.attribute.Phone;
import seedu.address.model.attribute.QuarantineStatus;
import seedu.address.model.attribute.Tag;

/**
 * Represents a Person in the person book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Id id;

    // Data fields
    private final Address address;
    private final QuarantineStatus quarantineStatus;
    private final InfectionStatus infectionStatus;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Id id, Name name, Phone phone, Email email, Address address, QuarantineStatus quarantineStatus,
                  InfectionStatus infectionStatus, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, quarantineStatus, infectionStatus, id, tags);

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.quarantineStatus = quarantineStatus;
        this.infectionStatus = infectionStatus;
        this.tags.addAll(tags);
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public QuarantineStatus getQuarantineStatus() {
        return quarantineStatus;
    }

    public InfectionStatus getInfectionStatus() {
        return infectionStatus;
    }

    public Id getId() {
        return id;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both person have the same id.
     */
    public boolean isSameId(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }
        return otherPerson != null
                && otherPerson.getId().equals(getId());

    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName())
                && (otherPerson.getPhone().equals(getPhone()) || otherPerson.getEmail().equals(getEmail()))
                && otherPerson.getId().equals(getId());
    }

    /**
     * Returns true if both persons are of the same identity expect id
     */
    public boolean isSameIdentityExceptIdPerson(Person otherPerson) {
        return otherPerson != null
                && otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getQuarantineStatus().equals(getQuarantineStatus())
                && otherPerson.getInfectionStatus().equals(getInfectionStatus())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getId().equals(getId());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, quarantineStatus, infectionStatus, tags, id);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Address: ")
                .append(getAddress())
                .append(" Quarantine Status: ")
                .append(getQuarantineStatus())
                .append(" Infected: ")
                .append(getInfectionStatus())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
