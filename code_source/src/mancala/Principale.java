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
			System.out.println("\n----Choisissez le mode d'affichage----");
			System.out.println("0. Console");
			System.out.println("1. Graphique");
	
			do {
				System.out.print("\nVotre choix >> ");
				choix = sc.nextInt();
			}while(choix < 0 || choix > 1);

			GameManagerAwale arbitreAwale = new GameManagerAwale(choix);
	
			switch (choix){
				//Partie sur console
				case 0:
					arbitreAwale.lancerUneNouvellePartie(true);
					arbitreAwale.getGagnant();
					break;
				//Partie graphique
				case 1:
					//Partie window = new Partie();
					//window.getFrmAwale().setVisible(true);
					DrawingManager game = new DrawingManager(arbitreAwale);
					game.go();
					//new ChoixJoueur(arbitreAwale);
					//DrawingManager drawer = new DrawingManager();
					//arbitreAwale.lancerUneNouvellePartieGraphique(drawer.lancerWindow());
					break;
			}
		}
		else {
			System.out.println("\n----Choisissez le mode de tournois----");
			System.out.println("0. Tester un algorithme précis (choix des heuristiques, poids, etc...)");
			System.out.println("1. Trouver les meilleures heuristiques");
			do {
				System.out.print("\nVotre choix >> ");
				choix = sc.nextInt();
			}while(choix < 0 || choix > 1);
			
			JoueurAwaleIA j1=new JoueurAwaleIA("IA_TOURNOIS_1",0,1,0,5,24);
			JoueurAwaleIA j2=new JoueurAwaleIA("IA_TOURNOIS_2",0,2,6,11,24);
			Tournois tournois=new Tournois(j1,j2);
			
			if(choix==0) {
				tournois.lancer();
				tournois.saveCSV(null);
			}else {
				System.out.println("Tester combien d'heuristique ?");
				do {
					System.out.print("\nVotre choix >> ");
					choix = sc.nextInt();
				}while(choix < 0 || choix > 512);
				
				System.out.println("Gagnant : "+tournois.testerHeuristiques(choix));
			}
				
		}
		sc.close();
	}
}
