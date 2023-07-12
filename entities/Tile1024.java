package entities;

import Board.Tile;

public class Tile1024 extends Entities{
    public Tile1024(Tile tile) {
        super(tile);
        setValue(1024);
        initImage();
    }
    public void initImage(){
        setImageEntity(getT().getImage(getClass().getResource("/resources/1024.png")));
    }
}
