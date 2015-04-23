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
        int N = in.nextInt();
        int [] puissanceTab = new int[N];
        for (int i = 0; i < N; i++) {
            puissanceTab[i] = in.nextInt();
        }
        
        //tri
        Arrays.sort(puissanceTab);
        
        int min_ecart = puissanceTab[1] - puissanceTab[0];
        for (int i = 1; i < puissanceTab.length - 1; i++) {
            int ecart = puissanceTab[i + 1] - puissanceTab[i];
            min_ecart = Math.min(min_ecart, ecart);
        }
        System.out.println(min_ecart);
    }
}
