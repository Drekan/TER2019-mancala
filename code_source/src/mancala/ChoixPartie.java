package mancala;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoixPartie {
	private GameManagerAwale arbitre;

	public ChoixPartie(GameManagerAwale arbitre) {
		this.arbitre = arbitre;
		initialize();
	}

	private void initialize() {
		JPanel all = new JPanel(new BorderLayout(0, 0));

		SPanel btnPanel = new SPanel();
		all.add(btnPanel, BorderLayout.CENTER);
		SButton loadBtn = new SButton("Charger une partie");
		SButton goBtn = new SButton("Lancer une nouvelle partie");
		btnPanel.add(loadBtn);
		btnPanel.add(goBtn);

		SPanel comboPanel = new SPanel();
		all.add(comboPanel, BorderLayout.SOUTH);
		JComboBox test = new JComboBox();
		int nbSaves = arbitre.bla1(test);
		comboPanel.add(test);
		test.setSelectedIndex(-1);
		test.setVisible(false);

		SButton btn = new SButton("Suivant");
		comboPanel.add(btn);
		btn.setVisible(false);

		goBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ChoixJoueur(arbitre);
			}
		});

		loadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (nbSaves != 0) {
					test.setVisible(true);
					btn.setVisible(true);
				} else {
					DrawingManager.showDialog("Aucune sauvegarde disponible", "Attention");
				}
			}
		});

		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameManagerAwale.getInstance().chargerPartieGraphique(test.getSelectedIndex());
				GameManagerAwale.getInstance().ehem(GameManagerAwale.getInstance());
			}
		});

		DrawingManagerAwale.getInstance().setContentPane(all);
		DrawingManagerAwale.getInstance().setVisible(true);
	}
}
