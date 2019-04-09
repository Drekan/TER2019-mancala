package mancala;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public static MainWindow instance = null;

    public static MainWindow getInstance()
    {
        if(instance == null)
            instance = new MainWindow();
        return instance;
    }

    private MainWindow(){
        this.setTitle("Awale");
        this.setSize(450, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout(0, 0));
    }
}
