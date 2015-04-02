import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ASCII Art
 **/
class Solution {

    /**
     * Main
     **/
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        // the width of letters represented in ASCII art
        int width = in.nextInt();
        in.nextLine();
        // the height of letters represented in ASCII art
        int height = in.nextInt();
        in.nextLine();
        // the entry text
        String text = in.nextLine();
        // change letter to index number in asciiLetters tab
        List<Integer> asciiLettersList = getIndexAscii(text.toUpperCase());
        // print ASCII letters
        for (int i = 0; i < height; i++) {
            String row = in.nextLine();
            String print = "";
            for (Integer asciiLetter : asciiLettersList) {
                print += row.substring(asciiLetter * width, asciiLetter * width + width);
            }
            System.out.println(print);
        }
    }

    /**
     * Get index letters in ASCII table
     * 
     * @param text_
     *            entry text
     * @return list of letters index
     */
    public static List<Integer> getIndexAscii(String text_) {
        List<Integer> asciiLetters = new ArrayList<Integer>(text_.length());
        // index A in ascii table
        int indexA = 'A';
        for (char letter : text_.toCharArray()) {
            int indexLetter = letter;
            // index of symbol '?'
            int indexAsciiArt = 26;
            // if in the intervals [A-Z]
            if (indexLetter >= 65 && indexLetter <= 90) {
                indexAsciiArt = indexLetter - indexA;
            }
            asciiLetters.add(indexAsciiArt);
        }
        return asciiLetters;
    }
}
