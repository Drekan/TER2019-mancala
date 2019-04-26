package mancala;

import javax.swing.*;
import java.awt.*;

public class DrawingManagerAwale extends JFrame {
    public static DrawingManagerAwale instance = null;

    public static DrawingManagerAwale getInstance()
    {
        if(instance == null)
            instance = new DrawingManagerAwale();
        return instance;
    }

    private DrawingManagerAwale(){
        this.setTitle("Awale");
        this.setSize(450, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout(0, 0));
    }
}
