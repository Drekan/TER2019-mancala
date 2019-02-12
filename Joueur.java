package mancala;

//Contient le nom du joueur, son score.
//c'est ici que se feront les méthodes pour jouer un coup
public abstract class Joueur {
	private String nomJoueur;
	private int score;
	private int numeroJoueur;
	
	//constructeurs :
	public Joueur(String nomJoueur, int score, int numeroJoueur){
		this.nomJoueur = nomJoueur;
		this.score = score;
		this.numeroJoueur = numeroJoueur;
	}
	
	//getters & setters :
	public String getNomJoueur(){ 
		return nomJoueur;
	}
	
	public void setNomJoueur(String nomJoueur){ 
		this.nomJoueur = nomJoueur;
	}
	
	public int getScore(){ 
		return score;
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
	
	//methods :
	public abstract void jouerUnCoup(int caseJouee,GameManagerAwale gameManagerAwale);//mise à jour des valeurs du plateau
	public abstract int getJeu();// avoir le coup que le joueur a choisi
		
	
}
