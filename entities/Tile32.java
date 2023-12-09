package entities;

import Board.Tile;

public class Tile32 extends Entities{
    public Tile32(Tile tile, int No) {
        super(tile, No);
        setValue(32);
        initImage();
    }
    public void initImage(){
        setImageEntity(getT().getImage(getClass().getResource("/resources/32.png")));
    }
}
