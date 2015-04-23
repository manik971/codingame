import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Defibrillators
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String longitude = in.next();
        in.nextLine();
        String latitude = in.next();
        in.nextLine();
        int nbDefib = in.nextInt();
        in.nextLine();
        double minDistance = Double.MAX_VALUE;
        String nom = "UNKNOWN";

        // many defibrallors
        if (nbDefib > 1) {
            for (int i = 0; i < nbDefib; i++) {
                // get defibrillator data
                String[] defib = in.nextLine().split(";");
                // calcul the distance between the user position and the current
                // defibrillator
                double distance = calculDistance(stringToDouble(longitude), stringToDouble(latitude), stringToDouble(defib[4]),
                        stringToDouble(defib[5]));
                // keep the closest defibrillator
                if (distance < minDistance) {
                    nom = defib[1];
                    minDistance = distance;
                }
            }
        }
        else if (nbDefib > 0) {
            nom = in.nextLine().split(";")[1];
        }

        System.out.println(nom);
    }

    /**
     * Calcul the distance between two position
     * 
     * @param longitudeA_
     *            longitude position A
     * @param latitudeA_
     *            latitude position A
     * @param longitudeB_
     *            longitude position B
     * @param latitudeB_
     *            latitude position B
     * @return the distance
     **/
    public static double calculDistance(double longitudeA_, double latitudeA_, double longitudeB_, double latitudeB_) {
        double x = (longitudeB_ - longitudeA_) * Math.cos((latitudeA_ + latitudeB_) / 2);
        double y = latitudeB_ - latitudeA_;
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) * 6371;
    }

    /**
     * Convert a String into a double
     * 
     * @param text_
     *            String entry
     * @return the double
     */
    public static double stringToDouble(String text_) {
        return Double.parseDouble(text_.replaceAll(",", "."));
    }
}