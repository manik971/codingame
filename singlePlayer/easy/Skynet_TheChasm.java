import java.util.*;
import java.io.*;

/**
 * Skynet: The Chasm
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int R = in.nextInt(); // the length of the road before the gap.
        int G = in.nextInt(); // the length of the gap.
        int L = in.nextInt(); // the length of the landing platform.
        
        // game loop
        while (true) {
            int S = in.nextInt(); // the motorbike's speed.
            int X = in.nextInt(); // the position on the road of the motorbike.
            
            String action = "WAIT";
            
            // before gap
            if(X < R){
                if(S < G + 1){
                    action = "SPEED";
                }
                else if(S > G + 1){
                    action = "SLOW";
                }
                if(X + S >= R + G){
                    action = "JUMP";
                }
            }
            else if(X >= R + G){
                action = "SLOW";
            }
            // action to do: SPEED, SLOW, JUMP or WAIT
            System.out.println(action); 
        }
    }
}
