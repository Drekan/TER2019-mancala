package mancala;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class MenuInterface {
    private JFrame frame;
    private JTextField nomJoueur1;
    private JTextField nomJoueur2;

    public static void main(String[] args) {
        MenuInterface window = new MenuInterface();
        window.frame.setVisible(true);
    }

    public MenuInterface() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel menu1 = new JPanel();
        frame.getContentPane().add(menu1);
        menu1.setLayout(new GridLayout(0, 2, 0, 0));

        JPanel j1 = new JPanel();
        menu1.add(j1);
        j1.setLayout(new GridLayout(4, 0, 0, 0));

        JLabel Joueur1 = new JLabel("J1");
        Joueur1.setHorizontalAlignment(SwingConstants.CENTER);
        j1.add(Joueur1);

        JRadioButton j1Humain = new JRadioButton("Humain");
        j1Humain.setHorizontalAlignment(SwingConstants.CENTER);
        j1.add(j1Humain);

        JRadioButton j1IA = new JRadioButton("IA");
        j1IA.setHorizontalAlignment(SwingConstants.CENTER);
        j1.add(j1IA);

        nomJoueur1 = new JTextField();
        nomJoueur1.setHorizontalAlignment(SwingConstants.CENTER);
        nomJoueur1.setText("Joueur 1");
        j1.add(nomJoueur1);
        nomJoueur1.setColumns(10);

        JPanel j2 = new JPanel();
        menu1.add(j2);
        j2.setLayout(new GridLayout(4, 0, 0, 0));

        JLabel joueur2 = new JLabel("J2");
        joueur2.setHorizontalAlignment(SwingConstants.CENTER);
        j2.add(joueur2);

        JRadioButton j2Humain = new JRadioButton("Humain");
        j2Humain.setHorizontalAlignment(SwingConstants.CENTER);
        j2.add(j2Humain);

        JRadioButton j2IA = new JRadioButton("IA");
        j2IA.setHorizontalAlignment(SwingConstants.CENTER);
        j2.add(j2IA);

        nomJoueur2 = new JTextField();
        nomJoueur2.setHorizontalAlignment(SwingConstants.CENTER);
        nomJoueur2.setText("Joueur 2");
        j2.add(nomJoueur2);
        nomJoueur2.setColumns(10);

        JPanel okPanel = new JPanel();
        frame.getContentPane().add(okPanel, BorderLayout.SOUTH);

        JButton okSelectionJoueurs = new JButton("Suivant");
        okPanel.add(okSelectionJoueurs);
    }
}
