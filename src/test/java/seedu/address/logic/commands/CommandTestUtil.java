package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFECTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUARANTINE_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_NINTH;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_TENTH;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.location.EditLocationCommand;
import seedu.address.logic.commands.person.EditPersonCommand;
import seedu.address.model.Model;
import seedu.address.model.location.Location;
import seedu.address.model.location.LocationNameContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonBook;
import seedu.address.testutil.EditLocationDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_NUS = "NUS";
    public static final String VALID_NAME_VIVOCITY = "Vivocity";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_ADDRESS_NUS = "21 Lower Kent Ridge Rd, Singapore 119077";
    public static final String VALID_ADDRESS_VIVOCITY = "1 HarbourFront Walk, Singapore 098585";
    public static final String VALID_QUARANTINE_STATUS_AMY = "true";
    public static final String VALID_QUARANTINE_STATUS_BOB = "false";
    public static final String VALID_INFECTION_STATUS_AMY = "false";
    public static final String VALID_INFECTION_STATUS_BOB = "true";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final Index VALID_ID_AMY_LOCATION = INDEX_SECOND;
    public static final Index VALID_ID_BOB_LOCATION = INDEX_THIRD;
    public static final Index VALID_ID_AMY = INDEX_NINTH;
    public static final Index VALID_ID_BOB = INDEX_TENTH;
    public static final Index VALID_ID_NUS = INDEX_FIRST;
    public static final Index VALID_ID_VIVOCITY = INDEX_SECOND;

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String NAME_DESC_NUS = " " + PREFIX_NAME + VALID_NAME_NUS;
    public static final String NAME_DESC_VIVOCITY = " " + PREFIX_NAME + VALID_NAME_VIVOCITY;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String ADDRESS_DESC_NUS = " " + PREFIX_ADDRESS + VALID_ADDRESS_NUS;
    public static final String ADDRESS_DESC_VIVOCITY = " " + PREFIX_ADDRESS + VALID_ADDRESS_VIVOCITY;
    public static final String QUARANTINE_STATUS_DESC_AMY = " " + PREFIX_QUARANTINE_STATUS
            + VALID_QUARANTINE_STATUS_AMY;
    public static final String QUARANTINE_STATUS_DESC_BOB = " " + PREFIX_QUARANTINE_STATUS
            + VALID_QUARANTINE_STATUS_BOB;
    public static final String INFECTION_DESC_AMY = " " + PREFIX_INFECTION + VALID_INFECTION_STATUS_AMY;
    public static final String INFECTION_DESC_BOB = " " + PREFIX_INFECTION + VALID_INFECTION_STATUS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_QUARANTINE_STATUS_DESC = " "
            + PREFIX_QUARANTINE_STATUS + "quarantined"; // only booleans allowed
    public static final String INVALID_INFECTION_DESC = " " + PREFIX_INFECTION + "nope"; // only true or false allowed
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_ID_LOCATION = "-1";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditPersonCommand.EditPersonDescriptor DESC_AMY;
    public static final EditPersonCommand.EditPersonDescriptor DESC_BOB;
    public static final EditLocationCommand.EditLocationDescriptor DESC_NUS;
    public static final EditLocationCommand.EditLocationDescriptor DESC_VIVOCITY;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withQuarantineStatus(VALID_QUARANTINE_STATUS_AMY).withInfectionStatus(VALID_INFECTION_STATUS_AMY)
                .withId(VALID_ID_AMY).withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withQuarantineStatus(VALID_QUARANTINE_STATUS_BOB).withInfectionStatus(VALID_INFECTION_STATUS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                .withId(VALID_ID_BOB).build();
    }

    static {
        DESC_NUS = new EditLocationDescriptorBuilder().withName(VALID_NAME_NUS)
                .withAddress(VALID_ADDRESS_NUS).withId(VALID_ID_NUS).build();
        DESC_VIVOCITY = new EditLocationDescriptorBuilder().withName(VALID_NAME_VIVOCITY)
                .withAddress(VALID_ADDRESS_VIVOCITY).withId(VALID_ID_VIVOCITY).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        PersonBook expectedPersonBook = new PersonBook(actualModel.getPersonBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedPersonBook, actualModel.getPersonBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the location at the given {@code targetIndex} in the
     * {@code model}'s location book.
     */
    public static void showLocationAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredLocationList().size());

        Location location = model.getFilteredLocationList().get(targetIndex.getZeroBased());
        final String[] splitName = location.getName().fullName.split("\\s+");
        model.updateFilteredLocationList(new LocationNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredLocationList().size());
    }
}
