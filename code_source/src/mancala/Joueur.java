package mancala;

//Contient le nom du joueur, son score.
//c'est ici que se feront les methodes pour jouer un coup
public abstract class Joueur {
	private String nomJoueur;
	private int score;
	private int numeroJoueur;
	private int min;
	private int max;
	
	//constructeurs :
	public Joueur(String nomJoueur, int score, int numeroJoueur, int min, int max){
		this.nomJoueur = nomJoueur;
		this.score = score;
		this.numeroJoueur = numeroJoueur;
		this.min = min;
		this.max = max;
	}
	
	//getters & setters :
	public String getNomJoueur(){ 
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

	//methods :
	public abstract void jouerUnCoup(int caseJouee,GameManagerAwale gameManagerAwale);//mise a  jour des valeurs du plateau
	public abstract int[] simulerUnCoup(int caseJouee,GameManagerAwale gameManagerAwale);
	public abstract void choisirUnCoup(GameManagerAwale gameManagerAwale);
}