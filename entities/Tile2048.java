package entities;

import Board.Tile;

public class Tile2048 extends Entities{

    public Tile2048(Tile tile, int No) {
        super(tile, No);
        setValue(2048);
        initImage();
    }
    public void initImage(){
        setImageEntity(getT().getImage(getClass().getResource("/resources/2048.png")));
    }
}
