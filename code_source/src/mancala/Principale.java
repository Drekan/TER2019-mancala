package mancala;

import java.util.Random;
import java.util.Scanner;

public class Principale {

	public static void main(String[] args) {
		
		/*
		JoueurAwaleIA j1=new JoueurAwaleIA("IA_TOURNOIS_1",0,1,0,5,24);
		JoueurAwaleIA j2=new JoueurAwaleIA("IA_TOURNOIS_2",0,2,6,11,24);
		Tournois tournois=new Tournois(j1,j2);
		tournois.lancer(); 
		*/
		
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
			case 0:	arbitreAwale.lancerUneNouvellePartie(true);
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
