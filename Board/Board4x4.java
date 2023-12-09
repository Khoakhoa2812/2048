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
                tiles[row][col].setPosition(col * 160, row * 160 + 100);
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
    public void boardTest(){
        for(int i = 0;i< tiles.length;i++){
            for(int j = 0;j<tiles[i].length;j++){
                System.out.println(tiles[i][j].getTileNum());
            }
        }
    }
    public void update(){
//        boardTest();
    }
    private Board4x4() {
        initTiles();
    }
}
