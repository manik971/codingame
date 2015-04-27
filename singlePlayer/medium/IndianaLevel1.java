import java.util.Scanner;

/**
 * Indiana - Level 1
 **/
class Player {

    /**
     * Main
     */
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // number of columns
        int nbColumns = in.nextInt();
        // number of rows
        int nbRows = in.nextInt();
        in.nextLine();
        // initialize game grid
        int[][] grid = new int[nbRows][nbColumns];
        for (int i = 0; i < nbRows; i++) {
            // represents a line in the grid and contains W integers.
            // Each integer represents one room of a given type
            String[] line = in.nextLine().split(" ");
            for (int j = 0; j < line.length; j++) {
                grid[i][j] = Integer.parseInt(line[j]);
            }
        }
        // the coordinate along the X axis of the exit
        int exitX = in.nextInt();
        in.nextLine();

        // game loop
        while (true) {
            // indiana position
            int indianaX = in.nextInt();
            int indianaY = in.nextInt();
            // indiana's entrance point into the current room
            String entryPos = in.next();
            in.nextLine();

            // define the room in which indiana will be on next turn
            switch (grid[indianaY][indianaX]) {
            case 1:
            case 3:
            case 7:
            case 8:
            case 9:
            case 12:
            case 13:
                indianaY++;
                break;
            case 2:
            case 6:
                if (entryPos.equals("LEFT")) {
                    indianaX++;
                }
                else {
                    indianaX--;
                }
                break;
            case 4:
            case 10:
                if (entryPos.equals("TOP")) {
                    indianaX--;
                }
                else {
                    indianaY++;
                }
                break;
            case 5:
            case 11:
                if (entryPos.equals("TOP")) {
                    indianaX++;
                }
                else {
                    indianaY++;
                }
                break;
            default:
                System.err.println("******** ERROR ********");
                break;
            }
            System.out.println(indianaX + " " + indianaY);
        }
    }
}