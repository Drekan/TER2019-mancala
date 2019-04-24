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
				case 1:	//DrawingManagerAwale window = new DrawingManagerAwale();
						//window.getFrmAwale().setVisible(true);
						DrawingManager drawer = new DrawingManager();
						arbitreAwale.lancerUneNouvellePartieGraphique(drawer.lancerWindow());
						break;
			}
			arbitreAwale.getGagnant();
			
		}
		else {
			System.out.println("\n----Choisissez le mode de tournois----");
			System.out.println("0. Tester un algo précis (choix des heuristiques, poids, etc...)");
			System.out.println("1. Ligue Heuristique (c'est comme la Ligue Pokémon, mais avec des heuristiques..bref..)");
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
				System.out.println("---Les parties sont en train d'être générées---\n");
				System.out.println("Pour l'instant, cette fonctionnalité est encore expérimentale. Seules les 10 premières heuristiques sont testés.\n");
				System.out.println("à venir :");
				System.out.println("-suivi des matchs en temps réel");
				System.out.println("-sauvegarde propre dans un csv pour exploiter les résultats");
				System.out.println("-amélioration de la structure de l'algo\n");
				System.out.print("Heuristique gagnante : ");
				System.out.println(tournois.meilleureHeuristique(8));
			}
				
		}
		sc.close();
	}

}
