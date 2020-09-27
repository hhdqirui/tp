package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.parser.ListType;
import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists information based on a given type.\n"
            + "Parameters: LIST_TYPE (must be either people, locations or visits)\n"
            + "Example: " + COMMAND_WORD + " people";

    public static final String MESSAGE_SUCCESS_ALL_PEOPLE = "Listed all people";
    public static final String MESSAGE_SUCCESS_ALL_LOCATIONS = "Listed all locations";
    public static final String MESSAGE_SUCCESS_ALL_VISITS = "Listed all visits";

    private final ListType listType;

    public ListCommand(ListType listType) {
        this.listType = listType;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (listType.equals(ListType.ALL_PEOPLE)) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        }
        return new CommandResult(MESSAGE_SUCCESS_ALL_PEOPLE);
    }
}
