package gameStates;

import Board.Board4x4;
import Calculation.TempoValue;
import Calculation.calculation;
import Calculation.tileCombination;
import Feature.Undo;
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
    private TempoValue tempoValue;
    private Undo undo;
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
        tempoValue = TempoValue.createInstance(this);
        undo = Undo.createInstance(this);
    }

    public TempoValue getTempoValue() {
        return tempoValue;
    }

    public void initAction(){
        entitiesManager.createEntities();
//        entitiesManager.initTile();
    }
    public void mousePressed(MouseEvent e){
        Rectangle rectangle = new Rectangle(0,0,640,640);
        if(rectangle.contains(e.getX(),e.getY())){
            isDragCompleted = false;
        }
    }
    public void mouseClicked(int x, int y){
        undo.UndoMove(x,y);
    }
    public void mouseDrag(MouseEvent e){
        undo.storeCurrentEntities();
        isDragCompleted = false;
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

    public Undo getUndo() {
        return undo;
    }

    public void mouseRelease(MouseEvent e){
        if(isDragged){
            finalX = e.getX();
            finalY = e.getY();
            isDragged = false;
            isDragCompleted = true;
            undo.setUndoUsed(false);
            entitiesManager.releaseNewEntities();
            entitiesManager.ageTile();
            if(tempoValue.getCurrDirection() != Calculation.calculateDirection()){
                entitiesManager.setLimitPerCreation(false);
            }
        }
    }
    public void update(){
        entitiesManager.update();
    }
    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img,0,100,640,540,null);
        entitiesManager.EntitiesRender(g);
        undo.render(g);
//        board4x4.render(g);
    }
}
