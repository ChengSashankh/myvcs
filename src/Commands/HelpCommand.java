package Commands;

/***
 * Implements the help command.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE_INSTRUCTIONS = "Format: " + COMMAND_WORD;

    @Override
    public void execute() {
        //TODO: Implement the help command
        System.out.println("Thing with early adopters is that theres no support. Jk tell me if you see this message");
    }

    @Override
    public void showUsageInstructions() {
        System.out.println(MESSAGE_USAGE_INSTRUCTIONS);
    }

}
