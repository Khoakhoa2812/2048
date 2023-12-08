package entities;

import Board.Tile;

public class Tile512 extends Entities{
    public Tile512(Tile tile, int No) {
        super(tile, No);
        setValue(512);
        initImage();
    }
    public void initImage(){
        setImageEntity(getT().getImage(getClass().getResource("/resources/512.png")));
    }
}
