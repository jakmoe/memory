package gameLogicOBSOLETE;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Card extends JButton {
    private int id;
    private boolean matched = false;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getMatched() {
        return this.matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
        ImageIcon image = new ImageIcon("C:\\Users\\D067928\\workspace\\Memory\\src\\Media\\1.jpg");
        image.setImage(image.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        this.setIcon(image);
    }
}
