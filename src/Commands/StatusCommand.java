package Commands;

import java.util.ArrayList;
import java.util.List;

/***
 * Implements command to show repo status
 */
public class StatusCommand extends Command {
    public static final String COMMAND_WORD = "sup";
    public static final String MESSAGE_USAGE_INSTRUCTIONS = "Format: " + COMMAND_WORD;
    public static final String PLACEHOLDER_MESSAGE = "Placeholder message";

    @Override
    public void execute() {
        System.out.println (
                "Saying "
                + COMMAND_WORD
                + " shows the currrent status of the project in vcs");

        // TODO: Implement the status command.
        System.out.println(getCommitStatistics());
        System.out.println(getLastCommitSummary());
        System.out.println(getListOfFiles().toString());
    }

    @Override
    public void showUsageInstructions() {
        System.out.println(MESSAGE_USAGE_INSTRUCTIONS);
    }

    /***
     * Returns formatted String with number of commmits and size
     * TODO: Implement this
     * @return
     */
    public String getCommitStatistics () {
        return PLACEHOLDER_MESSAGE;
    }

    /***
     * Returns summary of the most recent commit
     * TODO: Implement this
     * @return
     */
    public String getLastCommitSummary () {
        return PLACEHOLDER_MESSAGE;
    }

    /***
     * Returns file list in order of edit time
     * TODO: Implement this
     * @return
     */
    public List<String> getListOfFiles () {
        return new ArrayList<String>();
    }
}
