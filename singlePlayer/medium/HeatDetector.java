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
        
        int XLeft = 0;
        // width of the building
        int XRight = in.nextInt();
        int YTop = 0;
        // height of the building
        int YBot = in.nextInt();
        // maximum number of turns before game over
        int N = in.nextInt();
        int X0 = in.nextInt();
        int Y0 = in.nextInt();
        
        // game loop
        while (true) {
            String bombDir = in.next(); // the direction of the bombs from batman"s current location (U, UR, R, DR, D, DL, L or UL)
            //update area with direction
            if(bombDir.contains("U")){
                YBot = Y0;
            }
            if(bombDir.contains("R")){
                XLeft = X0 + 1;
            }
            if(bombDir.contains("D")){
                YTop = Y0 + 1;
            }
            if(bombDir.contains("L")){
                XRight = X0;
            }
            
            X0 = (XLeft + XRight) / 2;
            Y0 = (YBot + YTop) / 2;
            
            System.out.println(X0 + " " + Y0); // the location of the next window Batman should jump to.
        }
    }
}
