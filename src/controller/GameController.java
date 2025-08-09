package controller;

import model.MoveDirection;
import model.TileFactory;

import java.util.Arrays;

public class GameController {
    private final int [][] board;
    private boolean gameOver;

    public GameController() {
        board = new int[4][4];
        reset();
    }

    public void reset() {
        gameOver = false;
        for (int[] row : board) {
            Arrays.fill(row, 0);
        }
        TileFactory.placeTile(board);
        TileFactory.placeTile(board);
    }

    public int[][] getBoard() {
        return board;
    }

    public void move(MoveDirection moveDirection) {

        if (gameOver) {
            System.out.println("Game Over!");
            return;
        }

        boolean moved = false;
        for (int i = 0; i < board.length; i++) {
            int[] line = getLine(i, moveDirection);
            int[] merged = mergeLine(line);

            if (!arraysEqual(line, merged)) {
                moved = true;
            }

            setLine(i, merged, moveDirection);
        }

        if (moved) {
            TileFactory.placeTile(board);
            checkVictory();
            if (!gameOver)
                checkGameOver();
        } else {
            checkGameOver();
        }
    }

    private void checkVictory() {
        for (int[] row : board) {
            for (int val : row) {
                if (val == 2048) {
                    System.out.println("You won!");
                    gameOver = true;
                }
            }
        }
    }

    private void checkGameOver() {
        // 1. If there is at least one empty cell, game is not over
        for (int[] row : board) {
            for (int val : row) {
                if (val == 0) {
                    return; // Still space left
                }
            }
        }

        int size = board.length;

        // 2. Check for possible merges horizontally and vertically
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                // Right neighbor
                if (c < size - 1 && board[r][c] == board[r][c + 1]) {
                    return; // Can merge horizontally
                }
                // Down neighbor
                if (r < size - 1 && board[r][c] == board[r + 1][c]) {
                    return; // Can merge vertically
                }
            }
        }

        // No moves left
        gameOver = true;
        System.out.println("Game Over!");
    }

    private int[] getLine(int index, MoveDirection dir) {
        int size = board.length;
        int[] result = new int[size];

        for (int i = 0; i < size; i++) {
            switch (dir) {
                case LEFT:  result[i] = board[index][i]; break;
                case RIGHT: result[i] = board[index][size - 1 - i]; break;
                case UP:    result[i] = board[i][index]; break;
                case DOWN:  result[i] = board[size - 1 - i][index]; break;
            }
        }
        return result;
    }

    private void setLine(int index, int[] line, MoveDirection dir) {
        int size = board.length;
        for (int i = 0; i < size; i++) {
            switch (dir) {
                case LEFT:  board[index][i] = line[i]; break;
                case RIGHT: board[index][size - 1 - i] = line[i]; break;
                case UP:    board[i][index] = line[i]; break;
                case DOWN:  board[size - 1 - i][index] = line[i]; break;
            }
        }
    }

    private int[] mergeLine(int[] oldLine) {
        // Step 1: compress
        int[] newLine = compressLine(oldLine);

        // Step 2: merge
        for (int i = 0; i < newLine.length - 1; i++) {
            if (newLine[i] != 0 && newLine[i] == newLine[i + 1]) {
                newLine[i] *= 2;
                newLine[i + 1] = 0;
                i++; // skip next tile to avoid double merge
            }
        }

        // Step 3: compress again
        return compressLine(newLine);
    }

    private int[] compressLine(int[] line) {
        int[] newLine = new int[line.length];
        int insertPos = 0;
        for (int num : line) {
            if (num != 0) {
                newLine[insertPos++] = num;
            }
        }
        return newLine;
    }

    private boolean arraysEqual(int[] a, int[] b) {
        return Arrays.equals(a, b);
    }
}
