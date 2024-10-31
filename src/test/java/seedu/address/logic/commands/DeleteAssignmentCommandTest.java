package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalAssignments.getTypicalAssignments;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ASSIGNMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBookWithAssignments;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteAssignmentCommand}.
 */

public class DeleteAssignmentCommandTest {
    private Model model = new ModelManager(getTypicalAddressBookWithAssignments(), new UserPrefs());

    @Test
    public void execute_validIndexesUnfilteredList_success() {
        DeleteAssignmentCommand deleteAssignmentCommand = new DeleteAssignmentCommand(INDEX_FIRST_STUDENT,
                INDEX_FIRST_ASSIGNMENT);

        Student studentToDeleteAssignmentFrom = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student studentWithDeletedAssignment = new StudentBuilder(studentToDeleteAssignmentFrom).buildWithAssignment();
        studentWithDeletedAssignment.deleteAssignment(INDEX_FIRST_ASSIGNMENT.getOneBased());

        String expectedMessage = String.format(DeleteAssignmentCommand.MESSAGE_DELETE_SUCCESS,
                Messages.format(studentToDeleteAssignmentFrom.getAssignmentList().get(INDEX_FIRST_ASSIGNMENT
                        .getZeroBased())) + " " + Messages.format(studentWithDeletedAssignment));

        ModelManager expectedModel = new ModelManager(getTypicalAddressBookWithAssignments(), new UserPrefs());
        expectedModel.setStudent(studentToDeleteAssignmentFrom, studentWithDeletedAssignment);

        assertCommandSuccess(deleteAssignmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidAssignmentIndexUnfilteredList_throwsCommandException() {
        // Index is one above the number of assignments
        Index outOfBoundIndex = Index.fromOneBased(getTypicalAssignments().size() + 1);
        DeleteAssignmentCommand deleteAssignmentCommand = new DeleteAssignmentCommand(INDEX_FIRST_STUDENT,
                outOfBoundIndex);

        assertCommandFailure(deleteAssignmentCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        DeleteAssignmentCommand deleteAssignmentCommand = new DeleteAssignmentCommand(INDEX_FIRST_STUDENT,
                INDEX_FIRST_ASSIGNMENT);

        Student studentToDeleteAssignmentFrom = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student studentWithDeletedAssignment = new StudentBuilder(studentToDeleteAssignmentFrom).buildWithAssignment();
        studentWithDeletedAssignment.deleteAssignment(INDEX_FIRST_ASSIGNMENT.getOneBased());

        String expectedMessage = String.format(DeleteAssignmentCommand.MESSAGE_DELETE_SUCCESS,
                Messages.format(studentToDeleteAssignmentFrom.getAssignmentList().get(INDEX_FIRST_ASSIGNMENT
                        .getZeroBased())) + " " + Messages.format(studentWithDeletedAssignment));

        ModelManager expectedModel = new ModelManager(getTypicalAddressBookWithAssignments(), new UserPrefs());
        expectedModel.setStudent(studentToDeleteAssignmentFrom, studentWithDeletedAssignment);

        assertCommandSuccess(deleteAssignmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeleteAssignmentCommand deleteAssignmentFirstCommand = new DeleteAssignmentCommand(INDEX_FIRST_STUDENT,
                INDEX_FIRST_ASSIGNMENT);
        DeleteAssignmentCommand deleteAssignmentSecondCommand = new DeleteAssignmentCommand(INDEX_SECOND_STUDENT,
                INDEX_FIRST_ASSIGNMENT);

        // same object -> returns true
        assertTrue(deleteAssignmentFirstCommand.equals(deleteAssignmentFirstCommand));

        // same values -> returns true
        DeleteAssignmentCommand deleteAssignmentFirstCommandCopy = new DeleteAssignmentCommand(INDEX_FIRST_STUDENT,
                INDEX_FIRST_ASSIGNMENT);
        assertTrue(deleteAssignmentFirstCommand.equals(deleteAssignmentFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteAssignmentFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteAssignmentFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(deleteAssignmentFirstCommand.equals(deleteAssignmentSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index studentTargetIndex = INDEX_FIRST_STUDENT;
        Index assignmentTargetIndex = INDEX_FIRST_ASSIGNMENT;
        DeleteAssignmentCommand deleteAssignmentCommand = new DeleteAssignmentCommand(studentTargetIndex,
                assignmentTargetIndex);
        String expected = DeleteAssignmentCommand.class.getCanonicalName()
                + "{studentIndex=" + studentTargetIndex + ", assignmentIndex=" + assignmentTargetIndex + "}";
        assertEquals(expected, deleteAssignmentCommand.toString());
    }

}
