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
        String LON = in.next();
        in.nextLine();
        String LAT = in.next();
        in.nextLine();
        int N = in.nextInt();
        in.nextLine();
        double min_distance = 99999.99;
        String nom = "UNKNOWN";
        
        if(N > 1){
            for (int i = 0; i < N; i++) {
                //données defibrilateur
                String [] DEFIB = in.nextLine().split(";");
                double distance = calculDistance(stringToDouble(LON), stringToDouble(LAT), stringToDouble(DEFIB[4]), stringToDouble(DEFIB[5]));
                if(distance < min_distance){
                    nom = DEFIB[1];
                    min_distance = distance;
                }
            }   
        }
        else {
            nom = in.nextLine().split(";")[1];
        }
        
        System.out.println(nom);
    }
    
    public static double calculDistance(double longitudeA_, double latitudeA_, double longitudeB_, double latitudeB_){
        double x = (longitudeB_ - longitudeA_) * Math.cos((latitudeA_ + latitudeB_) / 2);
        double y = latitudeB_ - latitudeA_;
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) * 6371;
    }
    
    public static double stringToDouble(String text_){
        return Double.parseDouble(text_.replaceAll(",", "."));
    }
}
