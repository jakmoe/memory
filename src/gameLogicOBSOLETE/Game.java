package gameLogicOBSOLETE;

import javax.swing.*;

import java.awt.*;


public class Game {
    public static void main(String[] args) {
        Board b = new Board();
        b.setPreferredSize(new Dimension(1000, 1000)); //need to use this instead of setSize
        b.setLocation(500, 250);
        b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b.pack();
        b.setVisible(true);
        b.setIconImage(new ImageIcon("C:\\Users\\D067928\\workspace\\Memory\\src\\Media\\1.jpg").getImage());
    }
}
