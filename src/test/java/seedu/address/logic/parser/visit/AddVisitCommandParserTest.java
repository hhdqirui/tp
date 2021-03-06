package seedu.address.logic.parser.visit;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.VisitBuilder.DEFAULT_DATE;
import static seedu.address.testutil.VisitBuilder.DEFAULT_LOCATION_INDEX;
import static seedu.address.testutil.VisitBuilder.DEFAULT_PERSON_INDEX;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.visit.AddVisitCommand;

public class AddVisitCommandParserTest {
    private AddVisitCommandParser parser = new AddVisitCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + " 2" + " 3" + " d/ 2020-09-12",
                new AddVisitCommand(DEFAULT_PERSON_INDEX, DEFAULT_LOCATION_INDEX, DEFAULT_DATE));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddVisitCommand.MESSAGE_USAGE);

        // missing location id and date
        assertParseFailure(parser, " 1 ", expectedMessage);

        // missing date
        assertParseFailure(parser, " 1 " + " 2 ", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid date
        assertParseFailure(parser, "2 " + " 0 " + " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddVisitCommand.MESSAGE_USAGE));

        // invalid locationId and date
        assertParseFailure(parser, "2 " + " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddVisitCommand.MESSAGE_USAGE));

        //To be further implemented
    }
}
