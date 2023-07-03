import Manager.World;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        JFrame jFrame = new JFrame();
        jFrame.add(new World());
        jFrame.setTitle("2048");
        jFrame.pack();
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
