import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/***
 * TODO: Implement this class
 */
public class FileDelta {
    private static byte[] byteDifferences;
    private static BufferedInputStream oldCopyStream;
    private static BufferedInputStream fileStream;

    public FileDelta(File oldCopy, File file) throws IOException {

        try {
            oldCopyStream = new BufferedInputStream(new FileInputStream(oldCopy));
            fileStream = new BufferedInputStream(new FileInputStream(file));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Files were not found");
            System.out.println(fnfe.getLocalizedMessage());
        }

        computeDeltas();
    }

    /***
     * TODO: Implement
     * @throws IOException
     */
    private static void computeDeltas() throws IOException {
//        int bytesReadOldCopy;
//        int bytesRead;
//        byte[] byteBufferOldCopy = new byte[2048];
//        byte[] byteBuffer = new byte[2048];
//        byte[] differences;
//
//        bytesReadOldCopy = oldCopyStream.read(byteBufferOldCopy);
//        bytesRead = fileStream.read(byteBuffer);
//
//        while (bytesRead > 0 && bytesReadOldCopy > 0) {
//
//      for (int i = 0; i < bytesRead && i < bytesReadOldCopy; i++) {
//        if (someCondition) {
//            dest[i] = (byte) (data1[i] - data2[i]);
//        } else {
//            dest[i] = 0;
//        }
//
//        }
//
//            bytesReadOldCopy = oldCopyStream.read(byteBufferOldCopy);
//            bytesRead = fileStream.read(byteBuffer);
//        }
    }

}
