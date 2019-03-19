package mancala;

import java.util.Random;
import java.util.Scanner;

public class Principale {

	public static void main(String[] args) {
		GameManagerAwale arbitreAwale = new GameManagerAwale();
		Scanner sc = new Scanner(System.in);
		int gameMode;

		System.out.println("\n----Choisissez le mode d'affichage----");
		System.out.println("0. Console");
		System.out.println("1. Graphique");

		do {
			System.out.print("\nVotre choix >> ");
			gameMode = sc.nextInt();
		}while(gameMode < 0 || gameMode > 1);

		switch (gameMode){
			//Partie sur console
			case 0:	arbitreAwale.lancerUneNouvellePartie();
					break;
			//Partie graphique
			case 1:	DrawingManagerAwale window = new DrawingManagerAwale(arbitreAwale);
					window.getFrmAwale().setVisible(true);
					arbitreAwale.lancerUneNouvellePartieGraphique(window);
					break;
		}
		arbitreAwale.getGagnant();
		sc.close();
	}

}
