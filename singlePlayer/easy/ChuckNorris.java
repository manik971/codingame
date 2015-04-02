import java.util.Scanner;

/**
 * Chuck Norris
 **/
class Solution {

    /**
     * Main
     **/
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String message = in.nextLine();
        // translate String into binary
        String[] binaryTab = convertStringToBinary(message).split("");
        // translate binary into chuck norris code
        String chuckNorrisCode = convertBinariesToChuckNorris(binaryTab);
        // display the chuck norris code
        System.out.println(chuckNorrisCode);
    }

    /**
     * Convert String to binary ("CC" => "10000111000011")
     * 
     * @param asciiString_
     *            entry text
     * @return string binary translation
     */
    public static String convertStringToBinary(String asciiString_) {
        String binary = "";
        for (String letter : asciiString_.split("")) {
            byte[] bytes = letter.getBytes();
            binary += Integer.toBinaryString(0x100 + bytes[0]).substring(2);
        }
        return binary;
    }

    /**
     * Convert a sequence of same characters into Chuck Norris code 
     * ("0000" => "00 0000")
     * 
     * @param binarySequence_
     * @return chuck norris code
     */
    public static String convertSampleToChuckNorris(String binarySequence_) {
        // 1 -> 0
        String chuckNorrisCode = "0 ";
        // 0 -> 00
        if (binarySequence_.substring(0, 1).equals("0")) {
            chuckNorrisCode = "00 ";
        }
        // add "0" for each occurrence
        for (int i = 0; i < binarySequence_.length(); i++) {
            chuckNorrisCode += "0";
        }
        return chuckNorrisCode;
    }

    /**
     * Convert a binary table into Chuck Norris code
     * 
     * @param binaryTab
     * @return chuck norris code
     */
    public static String convertBinariesToChuckNorris(String[] binaryTab_) {
        String chuckNorrisCode = "";
        String previousChar = binaryTab_[0];
        String buffer = binaryTab_[0];
        for (int i = 1; i < binaryTab_.length; i++) {
            if (binaryTab_[i].equals(previousChar)) {
                buffer += binaryTab_[i];
            }
            else {
                chuckNorrisCode += convertSampleToChuckNorris(buffer);
                chuckNorrisCode += " ";
                buffer = binaryTab_[i];
            }
            previousChar = binaryTab_[i];
        }
        chuckNorrisCode += convertSampleToChuckNorris(buffer);
        return chuckNorrisCode;
    }
}
