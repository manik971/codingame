import java.util.Scanner;

/**
 * Heat Detector
 **/
class Player {

    /**
     * Main
     */
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // area of the building
        int xLeft = 0;
        int xRight = in.nextInt();
        int yTop = 0;
        int yBot = in.nextInt();
        // maximum number of turns before game over
        int nbMoves = in.nextInt();
        // batman position
        int batmanX = in.nextInt();
        int batmanY = in.nextInt();

        // game loop
        while (true) {
            // the direction of the bombs
            // (U, UR, R, DR, D, DL, L or UL)
            String bombDir = in.next();
            // update area with direction
            if (bombDir.contains("U")) {
                yBot = batmanY;
            }
            if (bombDir.contains("R")) {
                xLeft = batmanX + 1;
            }
            if (bombDir.contains("D")) {
                yTop = batmanY + 1;
            }
            if (bombDir.contains("L")) {
                xRight = batmanX;
            }
            // jump on the middle of the area
            batmanX = (xLeft + xRight) / 2;
            batmanY = (yBot + yTop) / 2;
            // window jump
            System.out.println(batmanX + " " + batmanY);
        }
    }
}
