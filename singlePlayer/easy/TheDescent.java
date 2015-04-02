import java.util.Scanner;

/**
 * The Descent
 **/
class Player {

    /**
     * Main
     */
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // game loop
        while (true) {
            // starship X position
            int starshipX = in.nextInt();
            // starship Y position
            int starshipY = in.nextInt();
            // higher mountain position
            int mtnMaxPos = 0;
            // higher mountain height
            int mtnMaxHeight = 0;
            String action = "HOLD";
            for (int i = 0; i < 8; i++) {
                // represents the height of one mountain, from 9 to 0. Mountain
                // heights are provided from left to right.
                int currentMtnHeight = in.nextInt();
                if (currentMtnHeight > mtnMaxHeight) {
                    mtnMaxHeight = currentMtnHeight;
                    mtnMaxPos = i;
                }
            }
            if (starshipX == mtnMaxPos) {
                action = "FIRE";
            }
            // FIRE (ship is firing its phase cannons)
            // HOLD (ship is not firing)
            System.out.println(action);
        }
    }
}
