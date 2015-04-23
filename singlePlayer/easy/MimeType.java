import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // Number of elements which make up the association table.
        in.nextLine();
        int Q = in.nextInt(); // Number Q of file names to be analyzed.
        in.nextLine();
        
        Map<String, String> tableExt = new HashMap<String, String>();
        for (int i = 0; i < N; i++) {
            String EXT = in.next(); // file extension
            String MT = in.next(); // MIME type.
            tableExt.put(EXT.toLowerCase(), MT);
            in.nextLine();
        }
        for (int i = 0; i < Q; i++) {
            String FNAME = in.nextLine(); // One file name per line.
            String ext = getFileExtension(FNAME.toLowerCase());
            String sortie = "UNKNOWN";
            if(ext != null && tableExt.containsKey(ext)){
                sortie = tableExt.get(ext);
            }
            System.out.println(sortie);
        }
    }
    
    public static String getFileExtension(String fileName_){
        String ext = null;
        if(fileName_.lastIndexOf(".") >= 0 && fileName_.lastIndexOf(".") < fileName_.length() - 1){
            ext = fileName_.substring(fileName_.lastIndexOf(".") + 1);
        }
        return ext;
    }
}
