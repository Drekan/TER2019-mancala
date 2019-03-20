package mancala;

//cette classe reconnait l'etat du jeu : nbr de graines, leur position dans le tableau
public class Awale extends Jeu implements Cloneable{
	private int nbrGraines;
	private int plateau[] ;
	
	GameManagerAwale gameManagerAwale;

	
	//constructeur :
	public Awale(String nomJeu, String regles){
		super(nomJeu,regles);
	}
	//setters & getters :
	public int getNbrGraines(){ 
		return this.nbrGraines;
	}
	
	public void setNbrGraines(int nbrGrainesEnJeu){ 
		this.nbrGraines = nbrGrainesEnJeu;
	}
	
	public int[] getPlateau(){ 
		return this.plateau;
	}
	
	public void setPlateau(int nouvelleValeur,int caseModifiee){ 
		this.plateau[caseModifiee] = nouvelleValeur;
	}
	
	public void setPlateau(int[] p) {
		if(p.length==6) {
			this.plateau=p;
		}
	}
	
	public void modifierPlateau(int[] plateau) {
		this.plateau = plateau;
	}
	
	public Awale clone() {
		Awale clone=new Awale(this.getNomJeu(),this.getRegles());
		clone.plateau=this.plateau.clone();
		clone.nbrGraines = this.nbrGraines;
		return clone;
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
		setNbrGraines(48);
		
	}	
}
