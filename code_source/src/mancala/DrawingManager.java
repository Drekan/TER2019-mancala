package mancala;

import javax.swing.*;

public class DrawingManager {
	private GameManagerAwale arbitre;

	public DrawingManager(GameManagerAwale arbitre) {
		this.arbitre = arbitre;
	}

	public void go() {
		new ChoixPartie(arbitre);
	}

	static public void showDialog(String message, String titre) {
		JOptionPane.showMessageDialog(null, message, titre, JOptionPane.WARNING_MESSAGE);
	}
}
