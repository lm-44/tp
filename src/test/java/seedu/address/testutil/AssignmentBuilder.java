package seedu.address.testutil;

import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentName;
import seedu.address.model.student.Student;

/**
 * A utility class to help with building Assignment objects.
 */
public class AssignmentBuilder {
    public static final String DEFAULT_ASSIGNMENTNAME = "Test Assignment";
    public static final int DEFAULT_MAXSCORE = 100;

    private AssignmentName assignmentName;
    private int maxScore;
    private int score;
    private boolean hasSubmitted;
    private Student student;

    /**
     * Creates a {@code AssignmentBuilder} with the default details.
     */
    public AssignmentBuilder(Student student) {
        this.student = student;
        this.assignmentName = new AssignmentName(DEFAULT_ASSIGNMENTNAME);
        this.maxScore = DEFAULT_MAXSCORE;
    }

    /**
     * Initializes the AssignmentBuilder with the data of {@code assignmentToCopy}.
     */
    public AssignmentBuilder(Assignment assignmentToCopy) {
        this.student = assignmentToCopy.getStudent();
        this.assignmentName = new AssignmentName(assignmentToCopy.getName());
        this.maxScore = assignmentToCopy.getMaxScore();
    }

    /**
     * Sets the {@code assignmentName} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withAssignmentName(String assignmentName) {
        this.assignmentName = new AssignmentName(assignmentName);
        return this;
    }

    /**
     * Sets the {@code maxScore} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withMaxScore(int maxScore) {
        this.maxScore = maxScore;
        return this;
    }

    /**
     * Sets the {@code score} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withScore(int score) {
        this.score = score;
        return this;
    }

    /**
     * Sets the {@code hasSubmitted} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withHasSubmitted(boolean hasSubmitted) {
        this.hasSubmitted = hasSubmitted;
        return this;
    }

    /**
     * Sets the {@code student} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withStudent(Student student) {
        this.student = student;
        return this;
    }

    public Assignment build() {
        return new Assignment(this.student, this.assignmentName, this.maxScore);
    }

}
