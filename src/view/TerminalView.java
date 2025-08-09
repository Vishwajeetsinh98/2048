package view;

public class TerminalView {

    private static final String RESET = "\u001B[0m";
    private static final String CLEAR_SCREEN = "\u001B[H\u001B[2J";

    private static String getColor(int value) {
        return switch (value) {
            case 2 -> "\u001B[37m";  // White
            case 4 -> "\u001B[36m";  // Cyan
            case 8 -> "\u001B[34m";  // Blue
            case 16 -> "\u001B[32m"; // Green
            case 32 -> "\u001B[33m"; // Yellow
            case 64 -> "\u001B[35m"; // Magenta
            case 128 -> "\u001B[31m"; // Red
            case 256 -> "\u001B[91m"; // Bright Red
            case 512 -> "\u001B[95m"; // Bright Magenta
            case 1024 -> "\u001B[93m"; // Bright Yellow
            case 2048 -> "\u001B[92m"; // Bright Green
            default -> "\u001B[90m"; // Grey
        };
    }

    public static void viewBoard(int[][] board) {
        final int CELL_WIDTH = 6; // Width of each cell
        final String HORIZONTAL = "-";

        // Clear screen before drawing
        System.out.print(CLEAR_SCREEN);
        System.out.flush();

        int rows = board.length;
        int cols = board[0].length;

        String separator = HORIZONTAL.repeat((CELL_WIDTH + 3) * cols + 1);

        for (int r = 0; r < rows; r++) {
            System.out.println(separator);
            System.out.print("|");
            for (int c = 0; c < cols; c++) {
                int value = board[r][c];
                String display = value == 0 ? "" : String.valueOf(value);
                String color = getColor(value);
                System.out.printf(" %s%" + CELL_WIDTH + "s%s |", color, display, RESET);
            }
            System.out.println();
        }
        System.out.println(separator);
    }
}
