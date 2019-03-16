package mancala;

import java.util.Random;
import java.util.Scanner;

public class Principale {

	public static void main(String[] args) {

		GameManagerAwale ArbitreAwale = new GameManagerAwale();
		Scanner sc = new Scanner(System.in);
		int gameMode;

		System.out.println("\n----Choisissez le mode d'affichage----");
		System.out.println("1. Console");
		System.out.println("2. Graphique");

		do {
			System.out.print("\nVotre choix >> ");
			gameMode = sc.nextInt();
		}while(gameMode < 1 || gameMode > 2);

		switch (gameMode){
			case 1:	ArbitreAwale.lancerUneNouvellePartie();
					break;
			case 2:	DrawingManagerAwale window = new DrawingManagerAwale(ArbitreAwale);
					window.getFrmAwale().setVisible(true);
					ArbitreAwale.lancerUneNouvellePartieGraphique(window);
					break;
		}
		ArbitreAwale.getGagnant();
		sc.close();
	}

}
