package mancala;

//Contient le nom du joueur, son score.
//c'est ici que se feront les methodes pour jouer un coup
public abstract class Joueur {
	private String nomJoueur;
	private int score;
	private int numeroJoueur;
	private int min;
	private int max;
	private int nbrGraineJoueur;
	
	//constructeurs :
	public Joueur(String nomJoueur, int score, int numeroJoueur, int min, int max, int nbrGraineJoueur){
		this.nomJoueur = nomJoueur;
		this.score = score;
		this.numeroJoueur = numeroJoueur;
		this.min = min;
		this.max = max;
		this.nbrGraineJoueur = nbrGraineJoueur;
	}
	
	public Joueur() {
	}
	
	//getters & setters :
	public String getNom(){ 
		return this.nomJoueur;
	}
	
	public void setNomJoueur(String nomJoueur){ 
		this.nomJoueur = nomJoueur;
	}
	
	public int getScore(){ 
		return this.score;
	}
	
	public void setScore(int score){ 
		this.score = score;
	}
	
	public int getNumeroJoueur() {
		return this.numeroJoueur;
	}
	
	public void setNumeroJoueur(int numeroJoueur) {	
		this.numeroJoueur = numeroJoueur;
	}
	
	public int getMin() {
		return this.min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return this.max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getNbrGraineJoueur() {
		return this.nbrGraineJoueur;
	}

	public void setNbrGraineJoueur(int nbrGraineJoueur) {
		this.nbrGraineJoueur = nbrGraineJoueur;
	}
	
	public void reset() {
		this.score=0;
		this.nbrGraineJoueur=24;
	}
	//methods :
	public abstract void jouerUnCoup(int caseJouee,GameManagerAwale gameManagerAwale);//mise a� jour des valeurs du plateau
	public abstract int choisirUnCoup(GameManagerAwale gameManagerAwale);
}