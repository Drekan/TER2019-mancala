package mancala;

import java.util.Random;
import java.util.Scanner;

public class Principale {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Choisissez, un ou deux joueurs? ");
		int choix = sc.nextInt();
		GameManagerAwale ArbitreAwale = new GameManagerAwale(choix);
		
		System.out.println("Choisissez la difficulte ");
		int difficulte = sc.nextInt();
		ArbitreAwale.lancerUneNouvellePartie(difficulte);
		
		System.out.println("nbrgraine init : " + ArbitreAwale.getPartie().getNbrGrainesEnJeu() );
		
		while( !ArbitreAwale.finPartie() ) {
			System.out.println("Nombre de redondances dans les 10 derniers coups :"+ArbitreAwale.NbRedondanceHistorique(10));
			int coupJoue = 0;
			
			// Entree les coups manuellement a travers la console :
					
			// gestion tour:
			Random rand = new Random();
			if( ArbitreAwale.gestionTour() == ArbitreAwale.getJoueur1()){
				do {
					System.out.println(ArbitreAwale.gestionTour().getNomJoueur() +  " donner coup a jouer : ");
					coupJoue = sc.nextInt();
					//coupJoue = rand.nextInt(5 - 0 + 1) + 0;
				}while( !ArbitreAwale.verifierCoupValide(ArbitreAwale.getJoueur1(),coupJoue,ArbitreAwale.getPartie().getPlateau() ));
				System.out.println("coup jouee par joueur 1 : " +  coupJoue);
				System.out.println();
				ArbitreAwale.getJoueur1().jouerUnCoup(coupJoue,ArbitreAwale);
			}
			else {
				if(choix == 2) {
                    do {
                        coupJoue = rand.nextInt(11 - 6 + 1) + 6;
                    }while( !ArbitreAwale.verifierCoupValide(ArbitreAwale.getJoueur2(),coupJoue,ArbitreAwale.getPartie().getPlateau()) );
                    System.out.println("coup joue par joueur 2 : " + coupJoue);
                    System.out.println();
                    ArbitreAwale.getJoueur2().jouerUnCoup(coupJoue,ArbitreAwale);
                }
                else if(choix == 1) {
                    System.out.println("coup joue par joueur 2 : ");
                    System.out.println();
                    ArbitreAwale.getJoueur2().choisirUnCoup(ArbitreAwale);
                }
			}
			
			System.out.println();
			ArbitreAwale.stockerEtatMouvement(ArbitreAwale.getPartie().etatActuel());
			for(int i = 11;i>5;i--) {
				System.out.print(ArbitreAwale.getPartie().etatActuel()[i] + " | ");
			}
			System.out.println();
			for(int i = 0;i<6;i++) {
				System.out.print(ArbitreAwale.getPartie().etatActuel()[i] + " | ");
			}
			
			System.out.println();
			
			System.out.println("Nbr graines en jeu : " + ArbitreAwale.getPartie().getNbrGrainesEnJeu());
			
		}
		ArbitreAwale.getGagnant();
		
		//Afficher Historique :
		//ArbitreAwale.afficheHistorique();
		sc.close();
	}

}
