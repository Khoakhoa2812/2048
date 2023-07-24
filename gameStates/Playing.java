package gameStates;

import Audio.Music;
import Board.Board4x4;
import Calculation.TempoValue;
import Calculation.calculation;
import Calculation.tileCombination;
import Feature.Reset;
import Feature.Score;
import Feature.Undo;
import Win_Lose.Lose;
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
    private Reset reset;
    private Score score;
    private Lose lose;
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
        Music.backgroundMusic();
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
        reset = Reset.createInstance(this);
        score = Score.createInstance(this);
        lose = Lose.createInstance(this);
    }

    public Score getScore() {
        return score;
    }

    public Lose getLose() {
        return lose;
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
        reset.resetMatch(x,y);
    }
    public void mouseDrag(MouseEvent e){
        isDragCompleted = false;
        if(!isDragged){
            initX = e.getX();
            initY = e.getY();
            isDragged = true;
        }
        undo.storeCurrentEntities();
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
            entitiesManager.ageTile();
            if(tempoValue.getCurrDirection() != Calculation.calculateDirection()){
                entitiesManager.setLimitPerCreation(false);
            }
        }
    }
    public void update(){
        entitiesManager.update();
        undo.update();
        lose.update();
    }
    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img,0,100,640,540,null);
        entitiesManager.EntitiesRender(g);
        undo.render(g);
        reset.render(g);
        score.render(g);
        lose.render(g);
//        board4x4.render(g);
    }
}
