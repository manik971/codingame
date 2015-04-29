import java.util.Scanner;

/**
 * APU: Init Phase
 **/
class Player {

    /**
     * Main
     */
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        // the number of cells on the X axis
        int width = in.nextInt();
        in.nextLine();
        // the number of cells on the Y axis
        int height = in.nextInt();
        in.nextLine();

        // initialize grid
        char[][] grid = new char[height][width];
        for (int y = 0; y < height; y++) {
            String line = in.nextLine();
            for (int x = 0; x < width; x++) {
                grid[y][x] = line.charAt(x);
            }
        }
        // game loop
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (grid[y][x] == '0') {
                    String output = x + " " + y;
                    output += " " + getNextRightNode(grid, x, y);
                    output += " " + getNextBottomNode(grid, x, y);
                    System.out.println(output);
                }
            }
        }
    }

    /**
     * Get the next coordinates on the right
     * 
     * @param grid_
     *            the grid
     * @param x_
     *            coordinate X
     * @param y_
     *            coordinate Y
     * @return coordinates "X Y"
     */
    public static String getNextRightNode(char[][] grid_, int x_, int y_) {
        String nextRightNode = "-1 -1";
        for (int i = x_ + 1; i < grid_[y_].length; i++) {
            if (grid_[y_][i] == '0') {
                nextRightNode = i + " " + y_;
                break;
            }
        }
        return nextRightNode;
    }

    /**
     * Get the next coordinates on the bottom
     * 
     * @param grid_
     *            the grid
     * @param x_
     *            coordinate X
     * @param y_
     *            coordinate Y
     * @return coordinates "X Y"
     */
    public static String getNextBottomNode(char[][] grid_, int x_, int y_) {
        String nextBottomNode = "-1 -1";
        for (int i = y_ + 1; i < grid_.length; i++) {
            if (grid_[i][x_] == '0') {
                nextBottomNode = x_ + " " + i;
                break;
            }
        }
        return nextBottomNode;
    }
}