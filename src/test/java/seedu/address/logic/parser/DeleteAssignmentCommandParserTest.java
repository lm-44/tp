package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ASSIGNMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteAssignmentCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteAssignmentCommand code.
 */
public class DeleteAssignmentCommandParserTest {
    private DeleteAssignmentCommandParser parser = new DeleteAssignmentCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteAssignmentCommand() {
        String input = " " + PREFIX_STUDENT_INDEX + INDEX_FIRST_STUDENT.getOneBased() + " "
                + PREFIX_ASSIGNMENT_INDEX + INDEX_FIRST_ASSIGNMENT.getOneBased();
        assertParseSuccess(parser, input, new DeleteAssignmentCommand(INDEX_FIRST_STUDENT, INDEX_FIRST_ASSIGNMENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "test", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteAssignmentCommand.MESSAGE_USAGE));
    }
}
