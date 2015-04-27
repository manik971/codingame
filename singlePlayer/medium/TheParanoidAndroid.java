import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The Paranoid Android
 **/
class Player {

    /**
     * Main
     */
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // number of floors
        int nbFloors = in.nextInt();
        // width of the area
        int width = in.nextInt();
        // maximum number of rounds
        int nbRounds = in.nextInt();
        // floor on which the exit is found
        int exitFloor = in.nextInt();
        // position of the exit on its floor
        int exitPos = in.nextInt();
        // number of generated clones
        int nbClones = in.nextInt();
        // ignore (always zero)
        int nbAdditionalElevators = in.nextInt();
        // number of elevators
        int nbElevators = in.nextInt();

        // initialize elevators
        Map<Integer, Integer> elevators = new HashMap<Integer, Integer>(nbElevators);
        for (int i = 0; i < nbElevators; i++) {
            // floor on which this elevator is found
            int elevatorFloor = in.nextInt();
            // position of the elevator on its floor
            int elevatorPos = in.nextInt();
            elevators.put(elevatorFloor, elevatorPos);
        }

        // game loop
        while (true) {
            // floor of the leading clone
            int cloneFloor = in.nextInt();
            // position of the leading clone on its floor
            int clonePos = in.nextInt();
            // direction of the leading clone (LEFT or RIGHT)
            String direction = in.next();
            // position to reached
            int targetPos = exitPos;
            // action to do to reach the target
            String action = "WAIT";

            // update target position if the leading clone is not in the exit floor
            if (cloneFloor >= 0 && cloneFloor != exitFloor) {
                targetPos = elevators.get(cloneFloor);
            }
            // block the leading clone if he's going in the wrong direction
            if (targetPos < clonePos && direction.equals("RIGHT") || targetPos > clonePos && direction.equals("LEFT")) {
                action = "BLOCK";
            }

            System.out.println(action);
        }
    }
}
