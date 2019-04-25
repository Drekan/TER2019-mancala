package mancala;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoixDifficulte {
    public ChoixDifficulte(String nomJ1, String nomJ2, int nbrIA, GameManagerAwale arbitre) {
        initialize(nomJ1, nomJ2, nbrIA, arbitre);
    }

    private void initialize(String nomJ1, String nomJ2, int nbrIA, GameManagerAwale arbitre) {
        String[] diffIA = {"IA naive (random)", "IA minimax", "IA alphaBeta"};
        String[] diffIA1 = {"Facile", "Moyen", "Difficile"};

        JPanel all = new JPanel(new BorderLayout(0, 0));
        MainWindow.getInstance().setContentPane(all);

        JPanel panel = new JPanel();
        all.add(panel);
        panel.setLayout(new GridLayout(2, 1, 0, 0));

        JComboBox<String> choixIAJ1 = new JComboBox<>(diffIA1);
        panel.add(choixIAJ1);
        choixIAJ1.setSelectedIndex(0);

        JComboBox<String> choixIAJ2 = new JComboBox<>(diffIA1);
        if(nbrIA == 2)
        {
            DefaultComboBoxModel model = new DefaultComboBoxModel(diffIA);
            choixIAJ1.setModel(model);
            choixIAJ2.setModel(model);
            panel.add(choixIAJ2);
            choixIAJ2.setSelectedIndex(0);
        }

        JPanel panel_1 = new JPanel();
        all.add(panel_1, BorderLayout.SOUTH);

        JButton btnNewButton = new JButton("Suivant");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int diff1, diff2;
                diff1 = choixIAJ1.getSelectedIndex();
                diff2 = choixIAJ2.getSelectedIndex();
                arbitre.initJoueurs(nomJ1, diff1, nomJ2, diff2);
                DrawingManagerAwale test = new DrawingManagerAwale(nomJ1, nomJ2);
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        arbitre.lancerUneNouvellePartieGraphique(test);
                        arbitre.getGagnant();
                    }
                });
                t.start();
            }
        });
        panel_1.add(btnNewButton);
        MainWindow.getInstance().setVisible(true);
    }
}
