package RepoObserver;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * Checks for any changes in the directory periodically
 *
 * TODO: Implement this class
 */
class PeriodicObserver {
    private static String path;

    private static List<File> filesInDirectory;
    private static List<byte[]> digestForFiles;
    private static List<byte[]> newDigestForFiles;
    private static List<Change> changesInDirectory;
    private static int PORT = 8000;

    public PeriodicObserver(String path) {
        this.path = path;
    }

    /***
     * TODO: Implement this command.
     */
    public static void updateFilesInDirectory() {
        File chosenDirectory = new File("/home/sashankh/myvcs/test/");
        for (File file: chosenDirectory.listFiles()) {

            if (file.isFile()) {
                filesInDirectory.add(file);
            }
        }
    }

    public static List<byte[]> getHashForAllFiles() throws IOException, FileNotFoundException {
        List<byte[]> newDigestForFiles = new ArrayList<byte[]>();

        for (File file: filesInDirectory) {
            MessageDigest messageDigest = null;
            try {
                messageDigest = getHashForFile(file);
            } catch (Exception e) {
                System.out.println("Found an exception: " + e.toString());
                System.out.println(file.toString());
            }

            newDigestForFiles.add(messageDigest.digest());
        }

        return newDigestForFiles;
    }

    public static MessageDigest getHashForFile(File file) throws IOException, NoSuchAlgorithmException {
        int bytesRead;
        byte[] byteBuffer = new byte[2048];

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        bytesRead = bis.read(byteBuffer);

        while (bytesRead > 0) {
            messageDigest.update(byteBuffer, 0, bytesRead);
            bytesRead = bis.read(byteBuffer);
        }

        bis.close();
        return messageDigest;
    }

    /***
     * Checks for any changes in the directory.
     *
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static boolean checkForChanges() throws IOException, NoSuchAlgorithmException {
        newDigestForFiles = getHashForAllFiles();
        boolean flag = false;
        //System.out.println(digestForFiles.size());

        for (int i = 0; i < newDigestForFiles.size(); i++) {
            if (!Arrays.equals(newDigestForFiles.get(i), digestForFiles.get(i))) {
                flag = true;
                recordChange(i);
            }
        }

        return flag;
    }

    public static void recordChange(int i)  throws IOException, NoSuchAlgorithmException {
        Change change = new Change(filesInDirectory.get(i), digestForFiles.get(i), newDigestForFiles.get(i));
        changesInDirectory.add(change);
        sendNotification(change);
    }

    public static void sendNotification(Change change) {
        System.out.println("Changes detected\n" + change.toString());
        digestForFiles = newDigestForFiles;
    }

    /***
     * Runs a periodic observer that logs changes every 2 seconds.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        filesInDirectory = new ArrayList<File>();
        changesInDirectory = new ArrayList<Change>();

        System.out.println("Updating directory");
        updateFilesInDirectory();
        System.out.println("Computing initial hash");
        digestForFiles = getHashForAllFiles();
        System.out.println(filesInDirectory.toString());

        System.out.println("Starting observer..");
        while (true) {
            try {
                Thread.sleep(2000);
                if (checkForChanges()) {
                    System.out.println("Checked for changes");
                }
            } catch (Exception e) {
                System.out.println("Found an exception: " + e.toString());
            }
        }
    }

}
