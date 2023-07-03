package Player;

import Manager.World;
import gameStates.gameScenes;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseListeners implements MouseListener, MouseMotionListener {
    private World w;
    public MouseListeners(World w){
        this.w = w;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        switch (gameScenes.GameScenes){
            case PLAYING:
                w.getPlaying().mouseDrag(e);
                break;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (gameScenes.GameScenes){
            case MENU:
                 w.getMenu().MouseClicked(e.getX(),e.getY());
                 break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (gameScenes.GameScenes){
            case PLAYING:
                w.getPlaying().mouseRelease(e);
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
