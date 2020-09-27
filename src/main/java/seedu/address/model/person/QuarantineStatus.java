package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's quarantine status in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidQuarantineStatus(String)}
 */
public class QuarantineStatus {

    public static final String MESSAGE_CONSTRAINTS =
            "Quarantine status should either be true or false, and it should not be blank";

    public final String value;

    /**
     * Constructs a {@code QuarantineStatus}.
     *
     * @param quarantineStatus A valid quarantine status.
     */
    public QuarantineStatus(String quarantineStatus) {
        requireNonNull(quarantineStatus);
        checkArgument(isValidQuarantineStatus(quarantineStatus), MESSAGE_CONSTRAINTS);
        value = quarantineStatus.toLowerCase();
    }

    /**
     * Returns if a given string is a valid quarantine status.
     */
    public static boolean isValidQuarantineStatus(String test) {
        return test.toLowerCase().equals("true") || test.toLowerCase().equals("false");
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof QuarantineStatus // instanceof handles nulls
                && value.equals(((QuarantineStatus) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
