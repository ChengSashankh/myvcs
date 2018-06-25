package UserInterface;

import java.util.Scanner;

/***
 * Represents the shell with which user interacts on command line.
 */
public class Shell {
    public static final String VERSION = "0.7 Pre-release";
    public static final String SOFTWARE_NAME = "myvcs";
    public static final String WELCOME_MESSAGE = "Hello this is " + SOFTWARE_NAME + " v" + VERSION;
    public static final String SPECIAL_MESSAGE = "If you're seeing this, you're an early adopter so thanks!";

    private static CommandParser commandParser;

    /***
     * Prints messages at the start of session.
     */
    public static void printInitMessages () {
        System.out.println(WELCOME_MESSAGE);
        System.out.println(SPECIAL_MESSAGE);
    }

    /***
     * Runs the actual shell session
     */
    public static void runShellSession() {
        String nextCommand;

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print(">");
            nextCommand = sc.nextLine();
            commandParser.startCommandExecution(nextCommand);
        }
    }

    /***
     * Runs the shell.
     * @param args
     */
    public static void main(String[] args) {
        commandParser = new CommandParser();

        // TODO: Run init processes here

        printInitMessages();
        runShellSession();
    }
}
