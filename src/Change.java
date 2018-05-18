/* Stores the change that was observed */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.security.*;
import java.io.*;

class Change {
    static byte[] oldHash, newHash;
    static LocalDateTime timeOfChange;
    static File file;

    public Change(File file, byte[] oldHash) throws IOException, FileNotFoundException, NoSuchAlgorithmException {
        // Set time of creation as the time of change
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        timeOfChange = LocalDateTime.now();
        dtf.format(timeOfChange);

        // Set the old hash as the file
        this.oldHash = oldHash;

        // Set the file
        this.file = file;

        // Set the new has
        this.newHash = getHashForFile(file).digest();
    }

    public Change(File file, byte[] oldHash, byte[] newHash) throws IOException, FileNotFoundException, NoSuchAlgorithmException {
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

    // Duplicate method - should just use Observer.getHashForFile() after linking files with package.
    public static MessageDigest getHashForFile(File file)  throws IOException, FileNotFoundException, NoSuchAlgorithmException {
        int bytesRead;
        byte[] byteBuffer = new byte[2048];

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        bytesRead = bis.read(byteBuffer);
        while(bytesRead > 0) {
            //System.out.println(bytesRead);
            messageDigest.update(byteBuffer, 0, bytesRead);
            bytesRead = bis.read(byteBuffer);
        }
        //System.out.println("loop over");
        bis.close();
        return messageDigest;
    }

    public String toString() {
        return new String (
                "Changes:\n" + timeOfChange.toString() + "\n" + oldHash.toString() + "\n" + newHash.toString());
    }
}
