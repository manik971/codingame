import java.util.Scanner;

/**
 * Stock Exchange Losses
 **/
class Solution {

    /**
     * Main
     */
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        // number of available stock values
        int nbValues = in.nextInt();
        in.nextLine();
        // the stock values
        String[] stockValues = in.nextLine().split(" ");

        // initialize
        int minDiff = 0;
        int maxVal = Integer.parseInt(stockValues[0]);

        for (int i = 1; i < nbValues; i++) {
            int currentVal = Integer.parseInt(stockValues[i]);
            int diff = maxVal - currentVal;
            if (diff > minDiff) {
                minDiff = diff;
            }
            if (currentVal > maxVal) {
                maxVal = currentVal;
            }
        }

        System.out.println(Math.max(0, minDiff) * -1);
    }
}