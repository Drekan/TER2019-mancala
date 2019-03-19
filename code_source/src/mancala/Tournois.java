package mancala;

public class Tournois {

	static Awale partie;
	private int nbrVictoiresJ1=0;
	private int nbrVictoiresJ2=0;
	private JoueurAwaleIA j1;
	private JoueurAwaleIA j2;
	
	public Tournois(JoueurAwaleIA j1,JoueurAwaleIA j2) {
		
	}
	
	
	public JoueurAwaleIA getJ1() {
		return this.j1;
	}
	
	public void setJ1(JoueurAwaleIA j) {
		this.j1=j;
	}
	
	public JoueurAwaleIA getJ2() {
		return this.j2;
	}
	
	public void setJ2(JoueurAwaleIA j) {
		this.j2=j;
	}
	
	public int getNbrVictoireJ1() {
		return nbrVictoiresJ1;
	}
	public void setNbrVictoireJ1(int nbr) {
		this.nbrVictoiresJ1=nbr;
	}
	
	public int getNbrVictoireJ2() {
		return nbrVictoiresJ2;
	}
	public void setNbrVictoireJ2(int nbr) {
		this.nbrVictoiresJ2=nbr;
	}
	
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
