package mancala;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.GridLayout;

public class ChoixDifficulte {
    private JFrame frame;

    public static void main(String[] args) {
        ChoixDifficulte window = new ChoixDifficulte();
        window.frame.setVisible(true);
    }

    public ChoixDifficulte() {
        initialize();
    }

    private void initialize() {
        String[] diffIA = {"IA naive (random)", "IA minimax", "IA alphaBeta"};

        frame = new JFrame();
        frame.setTitle("Awale - Choix difficulté");
        frame.setSize(450, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(2, 1, 0, 0));

        JComboBox choixIAJ1 = new JComboBox(diffIA);
        frame.getContentPane().add(choixIAJ1);
        choixIAJ1.setSelectedIndex(-1);

        JComboBox choixIAJ2 = new JComboBox(diffIA);
        frame.getContentPane().add(choixIAJ2);
        choixIAJ2.setSelectedIndex(-1);
    }
}
