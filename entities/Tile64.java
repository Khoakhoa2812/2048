package entities;

import Board.Tile;

public class Tile64 extends Entities{
    public Tile64(Tile tile) {
        super(tile);
        setValue(64);
        initImage();
    }
    public void initImage(){
        setImageEntity(getT().getImage(getClass().getResource("/resources/64.png")));
    }
}
