import java.util.*;
import java.io.*;
import java.math.*;
import java.util.Arrays;

/**
 * Horce-racing Duals
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int nbHorses = in.nextInt();
        // strength table creation
        int[] strengthTab = new int[nbHorses];
        for (int i = 0; i < nbHorses; i++) {
            strengthTab[i] = in.nextInt();
        }
        // strength table sorting
        Arrays.sort(strengthTab);
        // get the minimal difference strength
        int minDiff = strengthTab[1] - strengthTab[0];
        for (int i = 1; i < strengthTab.length - 1; i++) {
            int diff = strengthTab[i + 1] - strengthTab[i];
            minDiff = Math.min(minDiff, diff);
            if (minDiff == 0) {
                break;
            }
        }
        System.out.println(minDiff);
    }
}