package UserInterface;

import Commands.Command;
import Commands.CreateCommand;
import Commands.ExitCommand;
import Commands.HelpCommand;
import Commands.StatusCommand;
import Exceptions.UnknownCommandException;

/***
 * Accepts and parses new commands entered received by myvcs
 */
public class CommandParser {
    public static final String UNKNOWN_COMMAND_MESSAGE = "Command unknown. Please try "
            + HelpCommand.MESSAGE_USAGE_INSTRUCTIONS;

    /***
     * Removes escape characters and whitespace characters at the ends of {@code commandToParse}.
     * @param commandToParse
     * @return {@code cleanedCommand } containing the cleaned String.
     */
    public String cleanCommand (String commandToParse) {
        String cleanedCommand = commandToParse.replaceAll("\n" , "").replaceAll("\r", "");
        cleanedCommand = cleanedCommand.trim();
        return cleanedCommand;
    }

    /***
     * Checks if the correct number of arguments are entered for the command.
     * @param command is the command that is parsed.
     * @param commandToParse is the command word and arguments entered by the user.
     */
    public Boolean hasCorrectArguments(Command command, String commandToParse) {
        return (command.getExpectedNumberOfArguments() == commandToParse.split(" ").length - 1);
    }

    /***
     * Extracts the command word from the user's command input.
     * @param commandToParse is the user's input.
     * @return the command word.
     */
    public String extractCommandWord (String commandToParse) {
        return commandToParse.split(" ")[0];
    }

    public Command getCommandObject (String commandWord, String commandToParse)
            throws NullPointerException, UnknownCommandException {
        if (commandWord == null) {
            throw new NullPointerException();
        }

        switch (commandWord) {

        case StatusCommand.COMMAND_WORD:
            return new StatusCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case CreateCommand.COMMAND_WORD:
            return new HelpCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        default:
            System.out.println(UNKNOWN_COMMAND_MESSAGE);
            throw new UnknownCommandException(UNKNOWN_COMMAND_MESSAGE);
        }

    }

    /***
     * Initiates the execution of the command.
     * @param commandToParse represents the user's input
     */
    public void startCommandExecution (String commandToParse) {
        String cleanedCommand = cleanCommand(commandToParse);
        String commandWord = extractCommandWord(cleanedCommand);

        Command chosenCommand = new Command();

        try {
            chosenCommand = getCommandObject(commandWord, cleanedCommand);
        } catch (UnknownCommandException uce) {
            System.out.println("Command was not recognized. Please try again.");
        }

        if (!hasCorrectArguments(chosenCommand, cleanedCommand)) {
            chosenCommand.showUsageInstructions();
            return;
        }

        chosenCommand.execute();
    }

}
