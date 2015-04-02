import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Temperatures
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int result = 5526;
        int N = in.nextInt(); // the number of temperatures to analyse
        if(N > 0){
            in.nextLine();
            // the N temperatures expressed as integers ranging from -273 to 5526
            String [] temperatures = in.nextLine().split(" "); 
            for(int i=0;i<N;i++){
                int currentTemp = Integer.parseInt(temperatures[i]);
                // save the temperature closest to 0
                if(Math.abs(currentTemp) < Math.abs(result)){
                    result = currentTemp;
                }
                // if two numbers are equally close to zero, keep the positive integer
                else if(Math.abs(currentTemp) == Math.abs(result)){
                    if(currentTemp > 0){
                        result = currentTemp;
                    }
                }
            }
        }
        else {
            result = 0;
        }
        // Display the temperature closest to 0
        System.out.println(result);
    }
}
