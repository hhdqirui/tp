package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelPredicate;
import seedu.address.model.visit.VisitBook;

/**
 * Finds and lists all persons in visit book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class GenerateLocationsCommand extends Command {

    public static final String COMMAND_WORD = "generateLocations";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all locations which a person of "
            + "the specified id (case-insensitive) visited and displays them as a list of locations.\n"
            + "Parameters: PERSONID\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_PERSON_HAS_NO_VISITS = "This person is not associated with any visits";
    public static final String MESSAGE_PERSON_IS_NOT_INFECTED = "This person is not infected";

    private final Index personId;

    public GenerateLocationsCommand(Index personId) {
        this.personId = personId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (personId.getZeroBased() >= model.getFilteredPersonList().size()) {
            throw new CommandException(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Index personIdFromBook = model.getFilteredPersonList().get(personId.getZeroBased()).getId();
        if (!model.getPersonBook().getPersonList()
                .get(personIdFromBook.getZeroBased()).getInfectionStatus().getStatusAsBoolean()) {
            throw new CommandException(MESSAGE_PERSON_IS_NOT_INFECTED);
        }
        VisitBook visitsByPerson = model.getInfoHandler().generateVisitsByPerson(personIdFromBook);
        if (visitsByPerson.getVisitList().isEmpty()) {
            throw new CommandException(MESSAGE_PERSON_HAS_NO_VISITS);
        }
        List<Index> locationIds = model.getInfoHandler().generateLocationIdsByVisitBook(visitsByPerson);
        model.updateFilteredLocationList(ModelPredicate.getPredicateShowLocationsById(locationIds));
        return new CommandResult(
                "Generated locations for: " + model.getPersonBook()
                        .getPersonList().get(personIdFromBook.getZeroBased()).getName(),
                false, false, CommandResult.SWITCH_TO_VIEW_LOCATIONS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GenerateLocationsCommand // instanceof handles nulls
                && personId.equals(((GenerateLocationsCommand) other).personId)); // state check
    }
}
