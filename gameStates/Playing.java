package gameStates;

import Board.Board4x4;
import Calculation.calculation;
import Calculation.tileCombination;
import entities.EntitiesManager;
import entities.EntitiesStorage;

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
    private calculation Calculation;
    private tileCombination TileCombination;
    private EntitiesStorage entitiesStorage;
    public EntitiesManager getEntitiesManager() {
        return entitiesManager;
    }

    public calculation getCalculation() {
        return Calculation;
    }

    private static Playing instance;

    public Board4x4 getBoard4x4() {
        return board4x4;
    }

    public tileCombination getTileCombination() {
        return TileCombination;
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

    public EntitiesStorage getEntitiesStorage() {
        return entitiesStorage;
    }

    public void initComponent(){
        board4x4 = Board4x4.createInstance();
        entitiesManager = EntitiesManager.createInstance(this);
        Calculation = calculation.createInstance(this);
        TileCombination = tileCombination.createInstance(this);
        entitiesStorage = EntitiesStorage.createInstance(this);
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

    public double getInitX() {
        return initX;
    }

    public double getInitY() {
        return initY;
    }

    public double getFinalX() {
        return finalX;
    }

    public double getFinalY() {
        return finalY;
    }

    public boolean isDragCompleted() {
        return isDragCompleted;
    }

    public void setDragCompleted(boolean dragCompleted) {
        isDragCompleted = dragCompleted;
    }

    public void mouseRelease(MouseEvent e){
        if(isDragged){
            finalX = e.getX();
            finalY = e.getY();
            isDragged = false;
            isDragCompleted = true;
        }
    }
    public void update(){
        entitiesManager.update();
    }
    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img,0,100,640,540,null);
        entitiesManager.EntitiesRender(g);
//        board4x4.render(g);
    }
}
