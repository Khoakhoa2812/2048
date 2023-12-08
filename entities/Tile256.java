package entities;

import Board.Tile;

public class Tile256 extends Entities{
    public Tile256(Tile tile, int No) {
        super(tile,No);
        setValue(256);
        initImage();
    }
    public void initImage(){
        setImageEntity(getT().getImage(getClass().getResource("/resources/256.png")));
    }
}
