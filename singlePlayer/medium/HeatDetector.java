import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt(); // width of the building.
        int H = in.nextInt(); // height of the building.
        int N = in.nextInt(); // maximum number of turns before game over.
        int X0 = in.nextInt();
        int Y0 = in.nextInt();
        
        //bomb area to watch
        int [] area = new int [] {0, W - 1, 0, H - 1};
        
        // game loop
        while (true) {
            String BOMB_DIR = in.next(); // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)
            area = calculAreaRemaining(BOMB_DIR, new int [] {area[0], area[1], area[2], area[3]}, X0, Y0);
            int [] direction = findBestJump(area[0], area[1], area[2], area[3]);
            
            X0 = direction[0];
            Y0 = direction[1];
            
            System.out.println(X0 + " " + Y0); // the location of the next window Batman should jump to.
        }
    }
    
    public static int[] calculAreaRemaining(String bomb_dir_, int [] area, int X, int Y){
        char [] direction = bomb_dir_.toCharArray();
        // U UL UR
        if(direction[0] == 'U' || direction[0] == 'D'){
            // U D
            if(direction.length == 1){
                area[0] = area[1] = X;
            }
            // UL DL
            else if(direction[1] == 'L'){
                area[1] = X - 1;
            }
            // UR DR
            else {
                area[0] = X + 1;
            }
            // U UL UR
            if(direction[0] == 'U') {
                area[3] = Y - 1;
            }
            // D DL DR
            else {
                area[2] = Y + 1;
            }
        }
        // L or R
        else {
            area[3] = area[2] = Y;
            if(direction[0] == 'L'){
                area[1] = X - 1;
            }
            // R
            else {
                area[0] = X + 1;
            }
        }
        return area;
    }
    
    public static int[] findBestJump(int W_left, int W_right, int H_top, int H_bot){
        int X, Y;
        X = Y = 0;
        // only 1 columns
        if(W_right - W_left == 0){
            X = W_right;
        }
        // more than 1 column
        else {
            X = W_left + Math.abs((W_right - W_left) / 2);
        }
        // only 1 columns
        if(H_bot - H_top == 0){
            Y = H_bot;
        }
        // more than 1 column
        else {
            Y = H_top + Math.abs((H_bot - H_top) / 2);
        }
        return new int [] {X, Y};
    }
}
