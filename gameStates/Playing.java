package gameStates;

import Board.Board4x4;
import entities.EntitiesManager;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Playing implements sceneMethods{
    private boolean isDragged = false;
    private boolean isDragCompleted = false;
    private double initX;
    private double initY;
    private double finalX;
    private double finalY;
    private Board4x4 board4x4;
    private EntitiesManager entitiesManager;
    private static Playing instance;

    public Board4x4 getBoard4x4() {
        return board4x4;
    }

    public static Playing createInstance(){
        if(instance == null){
            instance = new Playing();
            return instance;
        } else {
            System.out.println("There is a Playing existed");
            return null;
        }
    }
    public Playing(){
        initComponent();
        initAction();
    }
    public void initComponent(){
        board4x4 = Board4x4.createInstance();
        entitiesManager = EntitiesManager.createInstance(this);
    }
    public void initAction(){
        entitiesManager.createEntities();
    }
    public void mouseDrag(MouseEvent e){
        if(!isDragged){
            initX = e.getX();
            initY = e.getY();
            isDragged = true;
        }
    }
    public void mouseRelease(MouseEvent e){
        if(isDragged){
            finalX = e.getX();
            finalY = e.getY();
            isDragged = false;
            isDragCompleted = true;
        }
    }
    public void Action(){
        if(isDragCompleted){
            entitiesManager.createEntities();
            isDragCompleted = false;
        }
    }
    public void update(){
        Action();
    }
    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img,0,100,640,540,null);
        entitiesManager.EntitiesRender(g);
//        board4x4.render(g);
    }
}
