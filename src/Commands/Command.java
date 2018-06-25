package Commands;

/***
 * Command class that is extended by all implemented commands
 */
public class Command {
    private int expectedNumberOfArguments;

    public int getExpectedNumberOfArguments() {
        return expectedNumberOfArguments;
    }

    public void showUsageInstructions() {
        // This implementation should be overridden.
    }

    public void execute() {
        // This implementation should be overridden.
    }
}
