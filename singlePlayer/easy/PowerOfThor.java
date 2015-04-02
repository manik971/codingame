import java.util.Scanner;

/**
 * Power Of Thor
 **/
class Player {

    /**
     * Main
     **/
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        // the X position of the light of power
        int destX = in.nextInt();
        // the Y position of the light of power
        int destY = in.nextInt();
        // Thor's starting X position
        int thorX = in.nextInt();
        // Thor's starting Y position
        int thorY = in.nextInt();

        // game loop
        while (true) {
            String move = "";
            if (destY > thorY) {
                move += "S";
                thorY++;
            }
            else if (destY < thorY) {
                move += "N";
                thorY--;
            }
            if (destX > thorX) {
                move += "E";
                thorX--;
            }
            else if (destX < thorX) {
                move += "W";
                thorX++;
            }
            // next move direction
            System.out.println(move);
        }
    }
}
