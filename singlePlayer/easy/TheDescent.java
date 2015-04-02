import java.util.*;
import java.io.*;

/**
 * The Descent
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // game loop
        while (true) {
            int SX = in.nextInt();
            int SY = in.nextInt();
            int MH_max_coord = 0;
            int MH_max = 0;
            String action = "HOLD";
            for (int i = 0; i < 8; i++) {
                // represents the height of one mountain, from 9 to 0. Mountain heights are provided from left to right.
                int MH = in.nextInt();
                if(MH > MH_max){
                    MH_max = MH;
                    MH_max_coord = i;
                }
            }
            if(SX == MH_max_coord){
                action = "FIRE";
            }
            // FIRE (ship is firing its phase cannons) or HOLD (ship is not firing)
            System.out.println(action); 
        }
    }
}
