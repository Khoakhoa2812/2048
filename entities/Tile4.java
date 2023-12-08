package entities;

import Board.Tile;

public class Tile4 extends Entities{
    public Tile4(Tile tile, int No) {
        super(tile,No);
        setValue(4);
        initImage();
    }
    public void initImage(){
        setImageEntity(getT().getImage(getClass().getResource("/resources/4.png")));
    }
}
