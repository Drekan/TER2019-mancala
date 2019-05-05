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
        Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int hauteur = (int)tailleEcran.getHeight();
        int largeur = (int)tailleEcran.getWidth();

        this.setTitle("Awale");
        this.setSize(largeur/2, hauteur/2);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout(0, 0));
    }
}
