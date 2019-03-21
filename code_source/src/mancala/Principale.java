package mancala;

import java.util.Random;
import java.util.Scanner;

public class Principale {

	public static void main(String[] args) {
		int choix;
		Scanner sc = new Scanner(System.in);
		System.out.println("----MENU PRINCIPAL----");
		System.out.println("0. Jouer");
		System.out.println("1. Mode Tournois (IA vs IA)");
		do {
			System.out.print("\nVotre choix >> ");
			choix = sc.nextInt();
		}while(choix < 0 || choix > 1);
		
		if(choix==0) {
			GameManagerAwale arbitreAwale = new GameManagerAwale();
	
			System.out.println("\n----Choisissez le mode d'affichage----");
			System.out.println("0. Console");
			System.out.println("1. Graphique");
	
			do {
				System.out.print("\nVotre choix >> ");
				choix = sc.nextInt();
			}while(choix < 0 || choix > 1);
	
			switch (choix){
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
			
		}
		else {
			JoueurAwaleIA j1=new JoueurAwaleIA("IA_TOURNOIS_1",0,1,0,5,24);
			JoueurAwaleIA j2=new JoueurAwaleIA("IA_TOURNOIS_2",0,2,6,11,24);
			Tournois tournois=new Tournois(j1,j2);
			tournois.lancer(); 
		}
		sc.close();
	}

}
