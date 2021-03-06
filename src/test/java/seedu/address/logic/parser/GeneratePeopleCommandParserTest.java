package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.GeneratePeopleCommand;

public class GeneratePeopleCommandParserTest {

    private GeneratePeopleCommandParser parser = new GeneratePeopleCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                GeneratePeopleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_indexOutOfBounds_throwsParseException() {
        assertParseFailure(parser, "0", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                GeneratePeopleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsGeneratePeopleCommand() {
        // no leading and trailing whitespaces
        GeneratePeopleCommand expectedGeneratePeopleCommand =
                new GeneratePeopleCommand(Index.fromOneBased(1));
        assertParseSuccess(parser, "1", expectedGeneratePeopleCommand);
    }

}
