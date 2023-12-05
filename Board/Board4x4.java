package Board;

import java.awt.*;

public class Board4x4 {
    private static Board4x4 instance;
    private Tile[][] tiles = new Tile[4][4];

    public Tile[][] getTile() {
        return tiles;
    }

    public static Board4x4 createInstance() {
        if (instance == null) {
            instance = new Board4x4();
            return instance;
        }
        return null;
    }

    public void initTiles() {
        int tileNumber = 0;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                tiles[row][col] = new Tile(tileNumber);
                tiles[row][col].setPosition(col * 162, row * 136 + 100);
                tileNumber++;
            }
        }
    }

    public void render(Graphics g) {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                tiles[row][col].render(g);
            }
        }
    }

    private Board4x4() {
        initTiles();
    }
}
