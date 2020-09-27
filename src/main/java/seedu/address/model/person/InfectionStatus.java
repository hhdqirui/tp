package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.regex.Pattern;

/**
 * Represents a Person's infection status in the tracker.
 * Guarantees: is valid as declared in {@link #isValidInfectionStatus(String)}
 */
public class InfectionStatus {

    public static final String MESSAGE_CONSTRAINTS = "The infection status of a person can only be true or false.";

    /*
     * The infection status can only be specified as
     * true or false, case-insensitive.
     */
    public static final String VALIDATION_REGEX = "(?i)(true|false)";

    private boolean isInfected;

    /**
     * Constructs an {@code InfectionStatus}.
     *
     * @param infectionStatus A valid infection status.
     */
    public InfectionStatus(String infectionStatus) {
        requireNonNull(infectionStatus);
        checkArgument(isValidInfectionStatus(infectionStatus), MESSAGE_CONSTRAINTS);
        isInfected = Boolean.parseBoolean(infectionStatus);
    }

    /**
     * Returns true if a given string is a valid infection status.
     */
    public static boolean isValidInfectionStatus(String test) {
        return Pattern.matches(VALIDATION_REGEX, test);
    }

    /**
     * Returns the String format of the infection status
     *
     * @return A String either containing true or false.
     */
    public String getStatusAsString() {
        return String.valueOf(isInfected);
    }

    @Override
    public String toString() {
        return String.valueOf(isInfected);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InfectionStatus // instanceof handles nulls
                && isInfected == ((InfectionStatus) other).isInfected); // state check
    }

    @Override
    public int hashCode() {
        return Boolean.valueOf(isInfected).hashCode();
    }

}
