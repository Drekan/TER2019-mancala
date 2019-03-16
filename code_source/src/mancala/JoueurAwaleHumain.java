package mancala;

import java.util.Scanner;

//On demande a l'utilisateur de faire un coup
public class JoueurAwaleHumain extends JoueurAwale{

	public JoueurAwaleHumain(String nomJoueur, int score, int numeroJoueur, int min, int max, int nbrGrainesEnJeu) {
		super(nomJoueur, score, numeroJoueur, min, max, nbrGrainesEnJeu);
	}
	
	public int choisirUnCoup(GameManagerAwale arbitre) {
		int coupJoue=-1;
		Scanner sc=new Scanner(System.in);
		do {
			System.out.println(this.getNom() +  ", choisissez un coup ("+this.getMin()+"-"+this.getMax()+")");
			coupJoue = sc.nextInt();
		}while( !arbitre.verifierCoupValide(this,coupJoue,arbitre.getPartie().getPlateau() ));
		System.out.println("coup joue par joueur 1 : " +  coupJoue);
		System.out.println();
		return coupJoue;
	}
}
