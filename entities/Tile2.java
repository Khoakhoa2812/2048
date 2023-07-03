package entities;

import Board.Tile;

public class Tile2 extends Entities{
    public Tile2(Tile tile) {
        super(tile);
        setValue(2);
        initImage();
    }
    public void initImage(){
        setImageEntity(getT().getImage(getClass().getResource("/resources/2.png")));
    }
}
