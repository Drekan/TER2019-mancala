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

		goBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ChoixJoueur(arbitre);
			}
		});

		loadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//GameManagerAwale.getInstance().chargerPartieGraphique();
			}
		});

		DrawingManagerAwale.getInstance().setContentPane(all);
		DrawingManagerAwale.getInstance().setVisible(true);
	}
}
