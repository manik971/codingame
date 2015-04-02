import java.util.*;
import java.io.*;
import java.math.*;

/**
 * ASCII Art
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt(); // the width of letters represented in ASCII art
        in.nextLine();
        int H = in.nextInt(); // the height of letters represented in ASCII art
        in.nextLine();
        String text = in.nextLine(); // the entry text
        //change letter to index number in asciiLetters tab
        List<Integer> ascii_letters_list = getIndexAscii(text.toUpperCase());
        //print ASCII letters
        for (int i = 0; i < H; i++) {
            String ROW = in.nextLine();
            String print = "";
            for(Integer ascii_letter : ascii_letters_list){
                print += ROW.substring(ascii_letter * L, ascii_letter * L + L);
            }
            System.out.println(print);
        }
    }
    
    /**
     * 
     **/
    public static List<Integer> getIndexAscii(String text_){
        List<Integer> ascii_letters = new ArrayList<Integer>(text_.length());
        //index A in ascii table
        int index_A = (int) 'A';
        for(char letter : text_.toCharArray()){
            int index_letter = (int) letter;
            // index of symbol '?'
            int index_asciiArt = 26;
            // if in the intervals [A-Z]
            if(index_letter >= 65 && index_letter <= 90){
                index_asciiArt = index_letter - index_A;
            }
            ascii_letters.add(index_asciiArt);
        }
        return ascii_letters;
    }
}
