import java.util.*;
import java.io.*;

/**
 * Power Of Thor
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int LX = in.nextInt(); // the X position of the light of power
        int LY = in.nextInt(); // the Y position of the light of power
        int TX = in.nextInt(); // Thor's starting X position
        int TY = in.nextInt(); // Thor's starting Y position

        // game loop
        while (true) {
            String move = "";
            if(LY > TY){
                move += "S";
                TY++;
            }
            else if(LY < TY){
                move += "N";
                TY--;
            }
            if(LX > TX){
                move += "E";
                TX--;
            }
            else if(LX < TX){
                move += "W";
                TX++;
            }
            // next move direction
            System.out.println(move);
        }
    }
}
