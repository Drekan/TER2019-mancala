package mancala;

public class Tournois {

	static Awale partie;
	
	public static void main(String[] args) {
		GameManagerAwale ArbitreAwale = new GameManagerAwale(0,0);
		
		ArbitreAwale.initJoueurs("joueur1","joueur2");

		partie = new Awale("MonAwale","MesRegles",1 );
		ArbitreAwale.setPartie(partie);
		
		
		ArbitreAwale.getPartie().initialisationJeu();
		
		
		ArbitreAwale.commencerPartie();
		
		ArbitreAwale.getGagnant();
		
	}
	
	
}
