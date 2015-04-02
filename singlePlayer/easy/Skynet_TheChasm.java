import java.util.Scanner;

/**
 * Skynet: The Chasm
 **/
class Player {

    /**
     * Main
     **/
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        // the length of the road before the gap
        int roadLength = in.nextInt();
        // the length of the gap
        int gapLength = in.nextInt();
        // the length of the landing platform
        int landingLength = in.nextInt();

        // game loop
        while (true) {
            // the motorbike's speed
            int motoSpeed = in.nextInt();
            // the position on the road of the motorbike
            int motoPos = in.nextInt();

            String action = "WAIT";

            // before gap
            if (motoPos < roadLength) {
                if (motoSpeed < gapLength + 1) {
                    action = "SPEED";
                }
                else if (motoSpeed > gapLength + 1) {
                    action = "SLOW";
                }
                if (motoPos + motoSpeed >= roadLength + gapLength) {
                    action = "JUMP";
                }
            }
            else if (motoPos >= roadLength + gapLength) {
                action = "SLOW";
            }
            // action to do: SPEED, SLOW, JUMP or WAIT
            System.out.println(action);
        }
    }
}
