import java.util.*;
import java.io.*;
import java.security.*;

/* Checks for any changes in the directory periodically*/
class Observer {
    public static List<File> filesInDirectory;
    public static List<byte[]> digestForFiles;
    public static List<byte[]> newDigestForFiles;
    public static List<File> changedFiles;

    public static int PORT = 8000;

    public static String path;

    public Observer(String path) {
        this.path = path;
    }

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

    public static MessageDigest getHashForFile(File file)  throws IOException, FileNotFoundException, NoSuchAlgorithmException {
        int bytesRead;
        byte[] byteBuffer = new byte[2048];

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        bytesRead = bis.read(byteBuffer);
        while(bytesRead > 0) {
            messageDigest.update(byteBuffer, 0, bytesRead);
            bytesRead = bis.read(byteBuffer);
        }
        bis.close();
        return messageDigest;
    }

    public static boolean checkForChanges() throws IOException, NoSuchAlgorithmException {
        newDigestForFiles = getHashForAllFiles();
        boolean flag = false;
        //System.out.println(digestForFiles.size());

        for (int i = 0; i < newDigestForFiles.size(); i++) {
            if(! Arrays.equals(newDigestForFiles.get(i), digestForFiles.get(i)) ) {
                flag = true;
                Change change = new Change(filesInDirectory.get(i), digestForFiles.get(i), newDigestForFiles.get(i));
                System.out.println(change.toString());
            }
        }

        return flag;
    }

    public static void sendNotification() {
        System.out.println("Changes detected\n");
        digestForFiles = newDigestForFiles;
    }

    public static void main(String[] args) throws IOException {
        filesInDirectory = new ArrayList<File>();
        changedFiles = new ArrayList<File>();

        System.out.println("Updating directory");
        updateFilesInDirectory();
        System.out.println("Computing initial hash");
        digestForFiles = getHashForAllFiles();
        System.out.println(filesInDirectory.toString());

        System.out.println("Starting observer..");
        while(true) {
            try {
                Thread.sleep(2000);
                if (checkForChanges()) {
                    System.out.println("Checked for changes");
                    sendNotification();
                }
            } catch (Exception e) {
                System.out.println("Found an exception: " + e.toString());
            }
        }
    }

}