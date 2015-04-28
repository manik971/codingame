import java.util.Arrays;
import java.util.Scanner;

/**
 * Network Cabling
 **/
class Solution {

    /**
     * Main
     */
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        // number of buildings
        int nbBuildings = in.nextInt();
        // initialize min & max building coordinates
        long minX = in.nextInt();
        long maxX = minX;
        long[] coordY = new long[nbBuildings];
        coordY[0] = in.nextInt();
        // initialize all buildings coordinates
        for (int i = 1; i < nbBuildings; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            coordY[i] = y;
            // update min & max coord
            if (x < minX) {
                minX = x;
            }
            else if (x > maxX) {
                maxX = x;
            }
        }
        // find the best main cable Y position
        long moyY = getMedian(coordY);
        // add main cable length
        long cableLength = maxX - minX;
        // add secondary cable length
        for (int i = 0; i < coordY.length; i++) {
            cableLength += Math.abs(moyY - coordY[i]);
        }
        System.out.println(cableLength);
    }

    /**
     * Calculate the median of a list of values
     * 
     * @param coordY_
     * @return the median value
     */
    public static long getMedian(long[] coordY_) {
        long moyY;
        Arrays.sort(coordY_);
        // even
        if (coordY_.length % 2 == 0) {
            moyY = (long) Math.floor((coordY_[coordY_.length / 2] + coordY_[coordY_.length / 2 - 1]) / 2);
        }
        // odd
        else {
            moyY = coordY_[(int) Math.floor(coordY_.length / 2)];
        }
        return moyY;
    }
}