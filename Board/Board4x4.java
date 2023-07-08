package Board;

import java.awt.*;

public class Board4x4 {
    private static Board4x4 instance;

    public Tile[] getTile() {
        return tile;
    }

    public static Board4x4 createInstance(){
        if(instance == null){
            instance = new Board4x4();
            return instance;
        }
        return null;
    }
    private Tile[] tile = new Tile[16];
    public void initTiles(){
        int line = 0;
        int row = 0;
        for(int i = 0;i<16;i++){
            if(i%4==0 && i!= 0){
                line++;
                row = 0;
            }
            tile[i] = new Tile(i);
            tile[i].setPosition(row*162,line*136+100);
            row++;
        }
    }
    public void render(Graphics g){
        for(int i = 0;i<16;i++){
            tile[i].render(g);
        }
    }
    public Board4x4(){
        initTiles();
    }
}
