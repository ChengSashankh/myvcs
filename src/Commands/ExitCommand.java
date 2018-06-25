package Commands;

import java.util.Scanner;

import static UserInterface.Shell.SOFTWARE_NAME;

/***
 * Implements the exit command.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_USAGE_INSTRUCTIONS = "Format: " + COMMAND_WORD;
    public static final String EXIT_MESSAGE = "Thanks for using " + SOFTWARE_NAME + ". Goodbye!";

    @Override
    public void execute() {
        System.out.println (
                "Saying "
                        + COMMAND_WORD
                        + " will cause " + SOFTWARE_NAME + " to exit");

        Boolean shouldExit = askForConfirmation();

        if (shouldExit) {
            System.out.println(EXIT_MESSAGE);
            System.exit(0);
        }
    }

    @Override
    public void showUsageInstructions() {
        System.out.println(MESSAGE_USAGE_INSTRUCTIONS);
    }

    /***
     * Asks user to confirm exit from program.
     * @return Boolean representing user's intent to exit
     */
    public static Boolean askForConfirmation () {
        System.out.println ("Are you sure you want to exit?(y/n)");

        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        return (input.equals("y"));
    }

}
