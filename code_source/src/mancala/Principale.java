package mancala;

import java.util.Random;
import java.util.Scanner;

public class Principale {

	public static void main(String[] args) {

//		//Partie console
//		GameManagerAwale ArbitreAwale = new GameManagerAwale();
//		ArbitreAwale.lancerUneNouvellePartie();
//		ArbitreAwale.getGagnant();

		//Partie graphique
		GameManagerAwale ArbitreAwale = new GameManagerAwale();
		DrawingManagerAwale window = new DrawingManagerAwale(ArbitreAwale);
		window.getFrmAwale().setVisible(true);
		ArbitreAwale.lancerUneNouvellePartieGraphique(window);
		ArbitreAwale.getGagnant();
	}

}
