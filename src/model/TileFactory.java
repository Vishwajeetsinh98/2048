package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TileFactory {

    private static final Random RANDOM = new Random();

    private static int createTile() {
        return RANDOM.nextInt(100) < 90 ? 2 : 4;
    }

    public static boolean placeTile(int[][] board) {
        List<int[]> emptyTiles = new ArrayList<>();
        for (int r = 0;r < board.length;r++) {
            for (int c = 0;c < board[0].length;c++) {
                if (board[r][c] == 0) {
                    emptyTiles.add(new int[] {r, c});
                }
            }
        }
        if (emptyTiles.isEmpty()) {
            return false;
        }
        int[] location = emptyTiles.get(RANDOM.nextInt(emptyTiles.size()));
        board[location[0]][location[1]] = createTile();
        return true;
    }
}
