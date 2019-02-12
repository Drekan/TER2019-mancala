package mancala;

import java.util.ArrayList;

//gere le jeu en fonction des regles de l'awale
//il enregistre aussi l'historique de la partie
public class GameManagerAwale extends GameManager{
	
	private int NbrJoueursHumain;
	private int historiquePlateau[][] = null;
	private boolean Tour = true;
	private ArrayList<int[]> Historique;
	
	JoueurAwale joueur1;
	JoueurAwale joueur2;
	Awale Partie;
	
	private int i ;
	
	//constructeurs :
	public GameManagerAwale(int nbrJoueursHumain) {
		super();
		this.NbrJoueursHumain = nbrJoueursHumain;
		this.i = 0 ;
	}
	//getters & setters :
	public int getNbrJoueursHumain() {return NbrJoueursHumain;}
	public void setNbrJoueursHumain(int nbrJoueursHumain) {	NbrJoueursHumain = nbrJoueursHumain;}
	public int[][] getHistoriquePlateau() {	return historiquePlateau;}
	public void setHistoriquePlateau(int[][] historiquePlateau) {this.historiquePlateau = historiquePlateau;}
	public boolean isTour() {return Tour;}
	public void setTour(boolean tour) {Tour = tour;}
	public int getI() {	return i;}
	public void setI(int i) {this.i = i;}
	
	//methods :
	public void lancerUneNouvellePartie(int difficulte){
		if(this.NbrJoueursHumain == 1) {
			this.joueur1 = new JoueurAwaleHumain("joueur1", 0, 1);
			this.joueur2 = new JoueurAwaleIA("joueur2", 0, 2);
		}
		else {
			this.joueur1 = new JoueurAwaleHumain("joueur1", 0, 1);
			this.joueur2 = new JoueurAwaleHumain("joueur2", 0, 2);
		}
		
		this.Partie  = new Awale("MonAwale","MesRègles",difficulte);
		this.Partie.initialisationJeu();
		
	}
	public void stockerEtatMouvement(int[] etatActuel) {//Historique
		/*
		for(int j=0;j<12;j++) {
			this.historiquePlateau[this.getI()][j] = etatActuel[j];
		}
		*/
		this.setI(getI()+1);
	}
	public boolean verifierCoupValide(JoueurAwale joueur, int caseJouee) {//bonne case avec bonnes regles
		//case non vide :
		if(this.Partie.getPlateau()[caseJouee] != 0) {
			if(joueur.getNumeroJoueur() == 1) {
				if( caseJouee >= 0 && caseJouee < 6 ) return true;
			}
			else {
				if( caseJouee >= 6 && caseJouee < 12 ) return true;
			}
		}
		return false;
	}
	@Override
	public JoueurAwale gestionTour() { //décide de qui va jouer
		if( this.getI()%2 == 0) return this.joueur2;
		return this.joueur1;
	}
	@Override
	public boolean finPartie() {//dire si c'est une fin de partie et arreter le jeu en fonction
		if(this.Partie.getNbrGrainesEnJeu() <= 1) {
			return true;
		}
		return false;
	}
	@Override
	public void gestionTemps() {//gere le temps allouer a chaque joueur tour a tour
		
	}
	@Override
	public void getGagnant() {
		int score1 = this.joueur1.getScore();
		int score2 = this.joueur2.getScore();
		if(score1 == score2)	System.out.println(" Score égaux ! ");
		else if(score1 > score2)	System.out.println("  Gagnant : Joueur1 !!!");
		else	System.out.println("  Gagnant : Joueur2 !!!");
	}
}
