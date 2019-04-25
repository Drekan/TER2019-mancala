package mancala;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChoixDifficulte {
    private ArrayList<JCheckBox> boxListJ1;
    private ArrayList<JCheckBox> boxListJ2;

    public ArrayList<JCheckBox> getBoxListJ1() {
        return boxListJ1;
    }

    public void setBoxListJ1(ArrayList<JCheckBox> boxListJ1) {
        this.boxListJ1 = boxListJ1;
    }

    public JCheckBox getBoxJ1(int i) {
        return boxListJ1.get(i);
    }

    public JCheckBox getBoxJ2(int i) {
        return boxListJ2.get(i);
    }

    public ChoixDifficulte(String nomJ1, String nomJ2, int nbrIA, GameManagerAwale arbitre) {
        initialize(nomJ1, nomJ2, nbrIA, arbitre);
    }

    private void initialize(String nomJ1, String nomJ2, int nbrIA, GameManagerAwale arbitre) {
        String[] diffIA = {"IA naive (random)", "IA minimax", "IA alphaBeta"};
        String[] diffIA1 = {"Facile", "Moyen", "Difficile"};

        JPanel all = new JPanel(new BorderLayout(0, 0));
        JPanel panel = new JPanel(new GridLayout(1, 2, 0, 0));
        JPanel panelJ1 = new JPanel(new GridLayout(3, 1, 0, 0));
        JPanel panelJ2 = new JPanel(new GridLayout(3, 1, 0, 0));
        JPanel btnPanel = new JPanel();
        JPanel heuristiqueJ1 = new JPanel();
        JPanel heuristiqueJ2 = new JPanel();

        all.add(panel);
        all.add(btnPanel, BorderLayout.SOUTH);
        panel.add(panelJ1);
        panel.add(panelJ2);

        SpinnerModel spinnerModelJ1 = new SpinnerNumberModel(0, 0, 8, 1);
        SpinnerModel spinnerModelJ2 = new SpinnerNumberModel(0, 0, 8, 1);

        JComboBox<String> choixIAJ1 = new JComboBox<>(diffIA1);
        JComboBox<String> choixIAJ2 = new JComboBox<>(diffIA1);
        JButton btnNewButton = new JButton("Suivant");
        JSpinner profondeurJ1 = new JSpinner(spinnerModelJ1);
        JSpinner profondeurJ2 = new JSpinner(spinnerModelJ2);

        panelJ1.add(choixIAJ1);
        btnPanel.add(btnNewButton);

        choixIAJ1.setSelectedIndex(0);

        if(nbrIA == 2) {
            //J1
            panelJ1.add(profondeurJ1);
            panelJ1.add(heuristiqueJ1);

            boxListJ1 = new ArrayList<>();
            for(int i = 0 ; i < 9 ; i++){
                JCheckBox box = new JCheckBox();
                boxListJ1.add(box);
            }

            for(int i = 0 ; i < 9 ; i++) {
                getBoxJ1(i).setText("H" + (i+1));
                heuristiqueJ1.add(getBoxJ1(i));
            }

            //J2
            DefaultComboBoxModel modelJ1 = new DefaultComboBoxModel(diffIA);
            DefaultComboBoxModel modelJ2 = new DefaultComboBoxModel(diffIA);

            choixIAJ1.setModel(modelJ1);
            choixIAJ2.setModel(modelJ2);

            panelJ2.add(choixIAJ2);
            panelJ2.add(profondeurJ2);

            choixIAJ2.setSelectedIndex(0);
            panelJ2.add(heuristiqueJ2);

            boxListJ2 = new ArrayList<>();
            for(int i = 0 ; i < 9 ; i++){
                JCheckBox box = new JCheckBox();
                boxListJ2.add(box);
            }

            for(int i = 0 ; i < 9 ; i++) {
                getBoxJ2(i).setText("H" + (i+1));
                heuristiqueJ2.add(getBoxJ2(i));
            }
        }

        //ActionListener
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int diff1, diff2, profondeur1, profondeur2;
                diff1 = choixIAJ1.getSelectedIndex();
                diff2 = choixIAJ2.getSelectedIndex();
                profondeur1 = (int) profondeurJ1.getValue();
                profondeur2 = (int) profondeurJ2.getValue();
                arbitre.initJoueurs(nomJ1, diff1, nomJ2, diff2, profondeur1, profondeur2);
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

        //Ajout du panel 'all" et affichage de l'instance
        MainWindow.getInstance().setContentPane(all);
        MainWindow.getInstance().setVisible(true);
    }
}
