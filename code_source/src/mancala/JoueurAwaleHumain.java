package mancala;

import java.util.Scanner;

//On demande a l'utilisateur de faire un coup
public class JoueurAwaleHumain extends JoueurAwale{

	public JoueurAwaleHumain(String nomJoueur, int score, int numeroJoueur, int min, int max) {
		super(nomJoueur, score, numeroJoueur,min,max);
	}
	
	public int choisirUnCoup(GameManagerAwale arbitre) {
		int coupJoue=-1;
		Scanner sc=new Scanner(System.in);
		do {
			System.out.println(this.getNom() +  ", choisissez un coup ("+this.getMin()+"-"+this.getMax()+")");
			coupJoue = sc.nextInt();
			//coupJoue = rand.nextInt(5 - 0 + 1) + 0;
		}while( !arbitre.verifierCoupValide(this,coupJoue,arbitre.getPartie().getPlateau() ));
		System.out.println("coup joue par joueur 1 : " +  coupJoue);
		System.out.println();
		//this.getJoueur1().jouerUnCoup(coupJoue,this);
		return coupJoue;
	}
}
