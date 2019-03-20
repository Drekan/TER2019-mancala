package mancala;

//contient les regles du jeu, le mode de difficulte et le nom du Jeu
public abstract class Jeu {
	
	private String nomJeu;
	private String regles;
	
	//constructeurs :
	public Jeu(String nomJeu, String regles){
		this.nomJeu = nomJeu;
		this.regles = regles;
	}
	
	//setters & getters :
	public String getNomJeu(){ 
		return this.nomJeu;
	}
	
	public void setNomJeu(String nomJeu){ 
		this.nomJeu = nomJeu;
	}
	
	public String getRegles(){ 
		return this.regles;
	}
	
	public void setRegles(String regles){ 
		this.regles = regles;
	}
	
	//methods :
	public abstract void initialisationJeu();//permet de tout remettre a  0
		
	
}
