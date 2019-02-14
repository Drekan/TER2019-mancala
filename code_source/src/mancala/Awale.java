package mancala;

//cette classe reconnait l'état du jeu : nbr de graines, leur position dans le tableau
public class Awale extends Jeu{
	private int nbrGrainesEnJeu;
	private int plateau[] ;
	
	GameManagerAwale gameManagerAwale;

	
	//constructeur :
	public Awale(String nomJeu, String regles, int difficulte){
		super(nomJeu,regles,difficulte);
	}
	//setters & getters :
	public int getNbrGrainesEnJeu(){ 
		return this.nbrGrainesEnJeu;
	}
	
	public void setNbrGrainesEnJeu(int nbrGrainesEnJeu){ 
		this.nbrGrainesEnJeu = nbrGrainesEnJeu;
	}
	
	public int[] getPlateau(){ 
		return this.plateau;
	}
	
	public void setPlateau(int nouvelleValeur,int caseModifiee){ 
		this.plateau[caseModifiee] = nouvelleValeur;
	}
	
	//methods :
	public void stockerJoueur(JoueurAwale joueur1,JoueurAwale joueur2) {// pouvoir reprendre une partie plus tard
		gameManagerAwale.saveJoueur1(joueur1);
		gameManagerAwale.saveJoueur2(joueur2);
	}
	public int[] etatActuel() {//permet de savoir la disposition du plateau actuel
		return this.getPlateau();
	}
	@Override
	public void initialisationJeu() {
		this.plateau = new int[] {4,4,4,4,4,4,4,4,4,4,4,4};
		setNbrGrainesEnJeu(48);
		
	}	
}