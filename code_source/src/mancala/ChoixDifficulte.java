package mancala;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoixDifficulte {
    public ChoixDifficulte(String nomJ1, String nomJ2, int nbrIA) {
        initialize(nomJ1, nomJ2, nbrIA);
    }

    private void initialize(String nomJ1, String nomJ2, int nbrIA) {
        String[] diffIA = {"IA naive (random)", "IA minimax", "IA alphaBeta"};
        String[] diffIA1 = {"Facile", "Moyen", "Difficile"};

        JPanel all = new JPanel(new BorderLayout(0, 0));
        MainWindow.getInstance().setContentPane(all);

        JPanel panel = new JPanel();
        all.add(panel);
        panel.setLayout(new GridLayout(2, 1, 0, 0));

        JComboBox choixIAJ1 = new JComboBox(diffIA1);
        panel.add(choixIAJ1);
        choixIAJ1.setSelectedIndex(0);

        if(nbrIA == 2)
        {
            JComboBox choixIAJ2 = new JComboBox(diffIA1);
            panel.add(choixIAJ2);
            choixIAJ2.setSelectedIndex(0);
        }

        JPanel panel_1 = new JPanel();
        all.add(panel_1, BorderLayout.SOUTH);

        JButton btnNewButton = new JButton("Suivant");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new DrawingManagerAwale(nomJ1, nomJ2);
            }
        });
        panel_1.add(btnNewButton);
        MainWindow.getInstance().setVisible(true);
    }
}
