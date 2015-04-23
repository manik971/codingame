import java.util.*;
import java.io.*;
import java.math.*;

/**
 * MIME Type
 **/
class Solution {

    /**
     * Main
     **/
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        // Number of elements which make up the association table
        int nbAssociation = in.nextInt();
        in.nextLine();
        // Number of file names to be analyzed
        int nbFiles = in.nextInt();
        in.nextLine();

        Map<String, String> tableExt = new HashMap<String, String>();
        for (int i = 0; i < nbAssociation; i++) {
            // file extension
            String fileExt = in.next();
            // MIME type
            String mimeType = in.next();
            tableExt.put(fileExt.toLowerCase(), mimeType);
            in.nextLine();
        }
        for (int i = 0; i < nbFiles; i++) {
            // One file name per line
            String fileName = in.nextLine();
            String ext = getFileExtension(fileName.toLowerCase());
            String result = "UNKNOWN";
            if (ext != null && tableExt.containsKey(ext)) {
                result = tableExt.get(ext);
            }
            System.out.println(result);
        }
    }

    /**
     * Get the file extension
     * 
     * @param fileName_
     *            Name of the file
     * @return the extension
     **/
    public static String getFileExtension(String fileName_) {
        String ext = null;
        if (fileName_.lastIndexOf(".") >= 0 && fileName_.lastIndexOf(".") < fileName_.length() - 1) {
            ext = fileName_.substring(fileName_.lastIndexOf(".") + 1);
        }
        return ext;
    }
}