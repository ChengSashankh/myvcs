package RepoObserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/***
 * Stores the change that was observed
 * TODO: Implement this class
 */
class Change {
    private static byte[] oldHash;
    private static byte[] newHash;
    private static LocalDateTime timeOfChange;
    private static File file;

    public Change(File file, byte[] oldHash) throws IOException, NoSuchAlgorithmException {
        // Set time of creation as the time of change
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        timeOfChange = LocalDateTime.now();
        dtf.format(timeOfChange);

        // Set the old hash as the file
        this.oldHash = oldHash;

        // Set the file
        this.file = file;

        // Set the new has
        this.newHash = PeriodicObserver.getHashForFile(file).digest();
    }

    public Change(
            File file,
            byte[] oldHash,
            byte[] newHash) throws IOException, FileNotFoundException, NoSuchAlgorithmException {
        // Set time of creation as the time of change
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        timeOfChange = LocalDateTime.now();
        dtf.format(timeOfChange);

        // Set the old hash as the file
        this.oldHash = oldHash;

        // Set the file
        this.file = file;

        // Set the new has
        this.newHash = newHash;
    }

    public String toString() {
        return new String (
                "Changes:\n" + timeOfChange.toString() + "\n" + oldHash.toString() + "\n" + newHash.toString());
    }
}
