package mancala;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChoixDifficulte {
    private ArrayList<JCheckBox> boxListJ1;
    private ArrayList<JCheckBox> boxListJ2;

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
        SPanel panel = new SPanel();
        SPanel panelJ1 = new SPanel();
        SPanel panelJ2 = new SPanel();
        SPanel btnPanel = new SPanel();
        SPanel heuristiqueJ1 = new SPanel();
        SPanel heuristiqueJ2 = new SPanel();

        panel.setLayout(new GridLayout(1, 2, 0, 0));
        panelJ1.setLayout(new GridLayout(3, 1, 0, 0));
        panelJ2.setLayout(new GridLayout(3, 1, 0, 0));

        all.add(panel);
        all.add(btnPanel, BorderLayout.SOUTH);
        panel.add(panelJ1);
        panel.add(panelJ2);

        SpinnerModel spinnerModelMinimaxJ1 = new SpinnerNumberModel(0, 0, 12, 1);
        SpinnerModel spinnerModelAlphaBetaJ1 = new SpinnerNumberModel(0, 0, 8, 1);
        SpinnerModel spinnerModelMinimaxJ2 = new SpinnerNumberModel(0, 0, 12, 1);
        SpinnerModel spinnerModelAlphaBetaJ2 = new SpinnerNumberModel(0, 0, 8, 1);

        JComboBox<String> choixIAJ1 = new JComboBox<>(diffIA1);
        JComboBox<String> choixIAJ2 = new JComboBox<>(diffIA1);
        JButton btnNewButton = new SButton("Suivant");
        JSpinner profondeurJ1 = new JSpinner(spinnerModelAlphaBetaJ1);
        JSpinner profondeurJ2 = new JSpinner(spinnerModelAlphaBetaJ2);

        profondeurJ1.setEditor(new JSpinner.DefaultEditor(profondeurJ1));
        profondeurJ2.setEditor(new JSpinner.DefaultEditor(profondeurJ2));

        panelJ1.add(choixIAJ1);
        btnPanel.add(btnNewButton);

        if(nbrIA == 2) {
            //J1
            profondeurJ1.setValue(4);
            DefaultComboBoxModel modelJ1 = new DefaultComboBoxModel(diffIA);
            choixIAJ1.setModel(modelJ1);
            choixIAJ1.setSelectedIndex(-1);

            panelJ1.add(profondeurJ1);
            panelJ1.add(heuristiqueJ1);

            boxListJ1 = new ArrayList<>();
            for(int i = 0 ; i < 9 ; i++){
                JCheckBox box = new JCheckBox();
                box.setBackground(new Color(255, 237, 183));
                boxListJ1.add(box);
            }

            for(int i = 0 ; i < 9 ; i++) {
                getBoxJ1(i).setText("H" + (i+1));
                heuristiqueJ1.add(getBoxJ1(i));
            }

            //J2
            profondeurJ2.setValue(4);
            DefaultComboBoxModel modelJ2 = new DefaultComboBoxModel(diffIA);
            choixIAJ2.setModel(modelJ2);
            choixIAJ2.setSelectedIndex(-1);

            panelJ2.add(choixIAJ2);
            panelJ2.add(profondeurJ2);
            panelJ2.add(heuristiqueJ2);

            boxListJ2 = new ArrayList<>();
            for(int i = 0 ; i < 9 ; i++){
                JCheckBox box = new JCheckBox();
                box.setBackground(new Color(255, 237, 183));
                boxListJ2.add(box);
            }

            for(int i = 0 ; i < 9 ; i++) {
                getBoxJ2(i).setText("H" + (i+1));
                heuristiqueJ2.add(getBoxJ2(i));
            }
        }

        //ActionListener ComboBox
        choixIAJ1.addActionListener(new ActionListener() {//J1
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (choixIAJ1.getSelectedIndex())
                {
                    case 0:
                        profondeurJ1.setVisible(false);
                        heuristiqueJ1.setVisible(false);
                        break;
                    case 1:
                        profondeurJ1.setVisible(true);
                        profondeurJ1.setModel(spinnerModelMinimaxJ1);
                        profondeurJ1.setValue(4);
                        heuristiqueJ1.setVisible(true);
                        break;
                    case 2:
                        profondeurJ1.setVisible(true);
                        profondeurJ1.setModel(spinnerModelAlphaBetaJ1);
                        profondeurJ1.setValue(4);
                        heuristiqueJ1.setVisible(true);
                        break;
                }
            }
        });

        choixIAJ2.addActionListener(new ActionListener() {//J2
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (choixIAJ2.getSelectedIndex())
                {
                    case 0:
                        profondeurJ2.setVisible(false);
                        heuristiqueJ2.setVisible(false);
                        break;
                    case 1:
                        profondeurJ2.setVisible(true);
                        profondeurJ2.setModel(spinnerModelMinimaxJ2);
                        profondeurJ2.setValue(4);
                        heuristiqueJ2.setVisible(true);
                        break;
                    case 2:
                        profondeurJ2.setVisible(true);
                        profondeurJ2.setModel(spinnerModelAlphaBetaJ2);
                        profondeurJ2.setValue(4);
                        heuristiqueJ2.setVisible(true);
                        break;
                }
            }
        });

        //ActionListener Button
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(choixIAJ1.getSelectedIndex() == -1 || choixIAJ2.getSelectedIndex() == -1)
                {
                    JOptionPane typeIAErreur = new JOptionPane();
                    typeIAErreur.showMessageDialog(null, "Type IA", "Wait!", JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    int diff1, diff2, profondeur1, profondeur2;
                    StringBuilder heuristique1 = new StringBuilder();
                    StringBuilder heuristique2 = new StringBuilder();

                    diff1 = choixIAJ1.getSelectedIndex();
                    diff2 = choixIAJ2.getSelectedIndex();

                    profondeur1 = (int) profondeurJ1.getValue();
                    profondeur2 = (int) profondeurJ2.getValue();

                    for(JCheckBox box : boxListJ1)
                    {
                        if(box.isSelected())
                            heuristique1.append("1");
                        else
                            heuristique1.append("0");
                    }
                    for(JCheckBox box : boxListJ2)
                    {
                        if(box.isSelected())
                            heuristique2.append("1");
                        else
                            heuristique2.append("0");
                    }
                    System.out.println(heuristique1.toString() + " | " + heuristique2.toString());

                    if(         (heuristique1.toString().equals("000000000") && choixIAJ1.getSelectedIndex() != 0)
                            ||  (heuristique2.toString().equals("000000000") && choixIAJ2.getSelectedIndex() != 0) )
                    {
                        JOptionPane heuristiqueErreur = new JOptionPane();
                        heuristiqueErreur.showMessageDialog(null, "Aucune heuristique selectionée", "Wait!", JOptionPane.WARNING_MESSAGE);
                    }
                    else
                    {
                        arbitre.initJoueurs(nomJ1, diff1, nomJ2, diff2, profondeur1, profondeur2);

                        Partie partie = new Partie(nomJ1, nomJ2);

                        arbitre.lancerThread(partie);
                    }
                }
            }
        });

        //Ajout du panel 'all" et affichage de l'instance
        DrawingManagerAwale.getInstance().setContentPane(all);
        DrawingManagerAwale.getInstance().setVisible(true);
    }
}
