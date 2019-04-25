package mancala;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoixJoueur {
    private JTextField nomJoueur1;
    private JTextField nomJoueur2;
    private GameManagerAwale arbitre;

    public ChoixJoueur(GameManagerAwale arbitre) {
        this.arbitre = arbitre;
        initialize();
    }

    private void initialize() {
        JPanel all = new JPanel(new BorderLayout(0, 0));
        MainWindow.getInstance().setContentPane(all);

        JPanel loadPanel = new JPanel();
        all.add(loadPanel, BorderLayout.NORTH);
        JButton loadBtn = new JButton("Charger une partie");
        loadPanel.add(loadBtn);

        JPanel menu1 = new JPanel();
        all.add(menu1);
        menu1.setLayout(new GridLayout(0, 2, 0, 0));

        JPanel j1 = new JPanel();
        menu1.add(j1);
        j1.setLayout(new GridLayout(4, 0, 0, 0));

        JLabel Joueur1 = new JLabel("J1");
        Joueur1.setHorizontalAlignment(SwingConstants.CENTER);
        j1.add(Joueur1);

        JRadioButton j1Humain = new JRadioButton("Humain", true);
        j1Humain.setHorizontalAlignment(SwingConstants.CENTER);
        j1.add(j1Humain);

        JRadioButton j1IA = new JRadioButton("IA");
        j1IA.setHorizontalAlignment(SwingConstants.CENTER);
        j1.add(j1IA);

        ButtonGroup j1Radio = new ButtonGroup();
        j1Radio.add(j1Humain);
        j1Radio.add(j1IA);

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

        JRadioButton j2Humain = new JRadioButton("Humain", true);
        j2Humain.setHorizontalAlignment(SwingConstants.CENTER);
        j2.add(j2Humain);

        JRadioButton j2IA = new JRadioButton("IA");
        j2IA.setHorizontalAlignment(SwingConstants.CENTER);
        j2.add(j2IA);

        ButtonGroup j2Radio = new ButtonGroup();
        j2Radio.add(j2Humain);
        j2Radio.add(j2IA);

        nomJoueur2 = new JTextField();
        nomJoueur2.setHorizontalAlignment(SwingConstants.CENTER);
        nomJoueur2.setText("Joueur 2");
        j2.add(nomJoueur2);
        nomJoueur2.setColumns(10);

        JPanel okPanel = new JPanel();
        all.add(okPanel, BorderLayout.SOUTH);

        JButton okSelectionJoueurs = new JButton("Suivant");
        okSelectionJoueurs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String nomJ1 = nomJoueur1.getText(), nomJ2 = nomJoueur2.getText();
                boolean j1IAKHRA = j1IA.isSelected(), j2IAKHRA = j2IA.isSelected();
                if(j1IAKHRA && j2IAKHRA){//2 IA
                    arbitre.setNbrJoueursHumain(0);
                    new ChoixDifficulte(nomJ1, nomJ2, 2, arbitre);
                }
                else if(j1IAKHRA || j2IAKHRA){//1 IA
                    arbitre.setNbrJoueursHumain(1);
                    new ChoixDifficulte(nomJ1, nomJ2, 1, arbitre);
                }
                else{//0 IA
                    arbitre.setNbrJoueursHumain(2);
                    arbitre.initJoueurs(nomJ1, nomJ2);
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
            }
        });
        okPanel.add(okSelectionJoueurs);
        MainWindow.getInstance().setVisible(true);

    }
}
