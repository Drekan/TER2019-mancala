package mancala;

import java.util.Random;
import java.util.Scanner;

public class Principale {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/* */
		System.out.println("Choisissez, un ou deux joueurs? ");
		int choix = sc.nextInt();
		GameManagerAwale ArbitreAwale = new GameManagerAwale(choix);
		
		System.out.println("Choisissez la difficulté ");
		int difficulte = sc.nextInt();
		ArbitreAwale.lancerUneNouvellePartie(difficulte);
		
		System.out.println("nbrgraine init : " + ArbitreAwale.Partie.getNbrGrainesEnJeu() );
		
		/*
		Awale awale = new Awale("","",2);
		awale.initialisationJeu();
		 */
		//int tabInt[] = {3,12,3,3,3,3,3,3,3,4,4,4};
		/*
		JoueurAwale joueur1 = new JoueurAwaleHumain("joueur1", 0, 1);
		JoueurAwale joueur2 = new JoueurAwaleHumain("joueur2", 0, 2);
		*/
		
		while( !ArbitreAwale.finPartie() ) {
			
			//System.out.println("Donner coup a jouer : ");
			//int coupJouee = sc.nextInt();
			int coupJouee;
					
			// gestion tour:
			Random rand = new Random();
			if( ArbitreAwale.gestionTour() == ArbitreAwale.joueur1){
				coupJouee = rand.nextInt(5 - 0 + 1) + 0;
				System.out.println("coupJouee par joueur 1 : " +  coupJouee);
				if( ArbitreAwale.verifierCoupValide(ArbitreAwale.joueur1,coupJouee) )
					ArbitreAwale.joueur1.jouerUnCoup(coupJouee,ArbitreAwale);
			}
			else {
				coupJouee = rand.nextInt(11 - 6 + 1) + 6;
				System.out.println("coupJouee par joueur 2 : " + coupJouee);
				if( ArbitreAwale.verifierCoupValide(ArbitreAwale.joueur2,coupJouee) )
					ArbitreAwale.joueur2.jouerUnCoup(coupJouee,ArbitreAwale);
			}
			
			ArbitreAwale.stockerEtatMouvement(ArbitreAwale.Partie.etatActuel());
			
			System.out.println("Nbr graines en jeu : " + ArbitreAwale.Partie.getNbrGrainesEnJeu());
			
		}
		ArbitreAwale.getGagnant();
		
	}

}
