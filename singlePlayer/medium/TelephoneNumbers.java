import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Telephone Numbers
 **/
class Solution {

    /**
     * Digit node used for numbers storage
     */
    public class Digit {

        private final int _digit;
        // possible number childs
        private final Map<Integer, Digit> _childs;

        public Digit(int digit_) {
            this._digit = digit_;
            this._childs = new HashMap<Integer, Digit>();
        }

        /**
         * Add a digit as next possible entry which reference a known number
         * 
         * @param childDigit_
         *            next possible digit
         */
        public void addChild(Digit childDigit_) {
            this._childs.put(childDigit_.getDigit(), childDigit_);
        }

        /**
         * Get the child digit
         * 
         * @param digit_
         *            a digit
         * @return a digit
         */
        public Digit getChild(int digit_) {
            return this._childs.get(digit_);
        }

        /**
         * Check if the digit can be a next entry which reference a known number
         * 
         * @param digit_
         *            a digit
         * @return
         */
        public boolean containsChild(int digit_) {
            return this._childs.containsKey(digit_);
        }

        public int getDigit() {
            return this._digit;
        }
    }

    /**
     * Main
     */
    public static void main(String args[]) {
        Solution solution = new Solution();
        Scanner in = new Scanner(System.in);
        // initialize
        int nbNumbers = in.nextInt();
        // numbers storage
        List<Digit> numbers = new ArrayList<Digit>();
        // first possible digit (tree root access)
        Map<Integer, Digit> firstDigit = new HashMap<Integer, Digit>();

        for (int i = 0; i < nbNumbers; i++) {
            int[] currNumber = convertStringtoIntArray(in.next());
            Digit prevDigit = null;
            // first digit
            if (firstDigit.containsKey(currNumber[0])) {
                prevDigit = firstDigit.get(currNumber[0]);
            }
            else {
                prevDigit = solution.new Digit(currNumber[0]);
                numbers.add(prevDigit);
                firstDigit.put(currNumber[0], prevDigit);
            }
            // foreach others digits
            for (int j = 1; j < currNumber.length; j++) {
                if (prevDigit.containsChild(currNumber[j])) {
                    prevDigit = prevDigit.getChild(currNumber[j]);
                }
                else {
                    Digit node = solution.new Digit(currNumber[j]);
                    numbers.add(node);
                    prevDigit.addChild(node);
                    prevDigit = node;
                }
            }
        }
        System.out.println(numbers.size());
    }

    /**
     * Convert a String to an int array
     * 
     * @param numberString_
     *            telephone number as String
     * @return telephone number as int array
     */
    public static int[] convertStringtoIntArray(String numberString_) {
        int[] numberInt = new int[numberString_.length()];
        for (int i = 0; i < numberString_.length(); i++) {
            numberInt[i] = Character.getNumericValue(numberString_.charAt(i));
        }
        return numberInt;
    }
}