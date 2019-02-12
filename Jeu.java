package mancala;
//contient les règles du jeu, le mode de difficulté et le nom du Jeu
public abstract class Jeu {
	
	private String nomJeu;
	private String regles;
	private int difficulteChoisis;
	
	int Difficulte[]= {1,2,3};
	
	//constructeurs :
	public Jeu(String nomJeu, String regles, int difficultechoisis){
		this.nomJeu = nomJeu;
		this.regles = regles;
		this.difficulteChoisis = difficultechoisis;
	}
	
	//setters & getters :
	public String getNomJeu(){ return nomJeu;}
	public void setNomJeu(String nomJeu){ this.nomJeu = nomJeu;}
	public String getRegles(){ return regles;}
	public void setRegles(String regles){ this.regles = regles;}
	public int getDifficulteChoisis(){ return difficulteChoisis;}
	public void setDifficulteChoisis(int difficulteChoisis){	this.difficulteChoisis = difficulteChoisis;}

	//methods :
	public abstract void initialisationJeu();//permet de tout remettre a 0
		
	
}
