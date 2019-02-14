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
		
		System.out.println("Choisissez la difficulte ");
		int difficulte = sc.nextInt();
		ArbitreAwale.lancerUneNouvellePartie(difficulte);
		
		System.out.println("nbrgraine init : " + ArbitreAwale.getPartie().getNbrGrainesEnJeu() );
		
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
			int coupJoue;
					
			// gestion tour:
			Random rand = new Random();
			if( ArbitreAwale.gestionTour() == ArbitreAwale.getJoueur1()){
				coupJoue = rand.nextInt(5 - 0 + 1) + 0;
				System.out.println("coup jouee par joueur 1 : " +  coupJoue);
				System.out.println();
				if( ArbitreAwale.verifierCoupValide(ArbitreAwale.getJoueur1(),coupJoue) )
					ArbitreAwale.getJoueur1().jouerUnCoup(coupJoue,ArbitreAwale);
			}
			else {
				coupJoue = rand.nextInt(11 - 6 + 1) + 6;
				System.out.println("coup jouee par joueur 2 : " + coupJoue);
				System.out.println();
				if( ArbitreAwale.verifierCoupValide(ArbitreAwale.getJoueur2(),coupJoue) )
					ArbitreAwale.getJoueur2().jouerUnCoup(coupJoue,ArbitreAwale);
			}
			
			ArbitreAwale.stockerEtatMouvement(ArbitreAwale.getPartie().etatActuel());
			
			System.out.println("Nbr graines en jeu : " + ArbitreAwale.getPartie().getNbrGrainesEnJeu());
			
		}
		ArbitreAwale.getGagnant();
		
	}

}
