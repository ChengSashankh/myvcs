package Commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/***
 * Implements command to start a repo
 */
public class CreateCommand extends Command {
    public static final String COMMAND_WORD = "go";
    public static final String MESSAGE_USAGE_INSTRUCTIONS =
            "Format: " + COMMAND_WORD + " " + "[absolute/path/to/project]";

    @Override
    public void execute() {
        System.out.println (
                "Saying "
                        + COMMAND_WORD
                        + " tells myvcs to start looking at this directory as a project");

        // TODO: Implement the create command

        /*
            1. Should create a folder to store the vcs material
            2. Should add an initial state to the folder
        */
    }

    /***
     * Create a new directory to store the vcs files
     */
    public void createDirectory(String pathToProject) {
        String directoryToCreate = pathToProject.concat("/myvcs");

        if (Files.isDirectory(Paths.get(directoryToCreate))) {
            System.out.println("There are existing myvcs files in this directory. Press y to overwrite them?");

            Scanner sc = new Scanner(System.in);

            if (sc.next().compareTo("y") != 0) {
                System.out.println("Commmand execution aborted.");
                return;
            }
        } else if (new File (directoryToCreate).mkdirs()) {
            System.out.println("Directory created");
        } else {
            System.out.println("Error: Unable to create directory " + directoryToCreate);
        }
    }

    /***
     *
     * @param pathToProject
     */
    public static void saveState (String pathToProject) {
        File projectDirectory = new File(pathToProject);
        File[] listOfFiles = projectDirectory.listFiles();

        if (listOfFiles == null) {
            System.out.println("Directory list returned null");
            return;
        }

        for (int i = 0; i < listOfFiles.length; i++) {
            try {
                Files.copy(
                        listOfFiles[i].toPath(),
                        new File(pathToProject + "/myvcs/" + listOfFiles[i].getName()).toPath());
            } catch (IOException ioe) {
                System.out.println("Error in file copy");
            }
        }
    }


    @Override
    public void showUsageInstructions() {
        System.out.println(MESSAGE_USAGE_INSTRUCTIONS);
    }

    /***
     * Returns file list in
     * TODO: Implement this
     * @return
     */
    public List<String> getListOfFiles () {
        return new ArrayList<String>();
    }
}
