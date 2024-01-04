package Board;

import entities.Entities;
import gameStates.Playing;

import java.awt.*;

public class Board4x4 {
    private static Board4x4 instance;
    private Tile[][] tiles = new Tile[4][4];
    private Playing playing;
    public Tile[][] getTile() {
        return tiles;
    }
    public Board4x4(Playing playing){
        this.playing = playing;
        initTiles();
    }

    public static Board4x4 createInstance(Playing playing) {
        if (instance == null) {
            instance = new Board4x4(playing);
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
    public boolean isBoardFull(){
        for(int i = 0;i<tiles.length;i++){
            for(int j = 0;j<tiles[i].length;j++){
                for(Entities entities:playing.getEntitiesManager().getEntitiesList()){
                    if(!tiles[i][j].isOccupied(entities)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
