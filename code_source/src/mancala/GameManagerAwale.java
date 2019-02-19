package mancala;

import java.util.ArrayList;
import java.util.Arrays;

//gere le jeu en fonction des regles de l'awale
//il enregistre aussi l'historique de la partie
public class GameManagerAwale extends GameManager{
	
	private int nbrJoueursHumain;
	private int historiquePlateau[][] = null;
	private boolean tour = true;
	private ArrayList<int[]> historique;
	
	private JoueurAwale joueur1;
	private JoueurAwale joueur2;
	private Awale partie;

	private int i ;
	
	//constructeurs :
	public GameManagerAwale(int nbrJoueursHumain) {
		super();
		this.nbrJoueursHumain = nbrJoueursHumain;
		this.i = 0 ;
		this.historique = new ArrayList<int[]>();
	}
	//getters & setters :
	public int getNbrJoueursHumain() {
		return this.nbrJoueursHumain;
	}
	
	public void setNbrJoueursHumain(int nbrJoueursHumain) {	
		this.nbrJoueursHumain = nbrJoueursHumain;
	}
	
	public int[][] get() {	
		return this.historiquePlateau;
	}
	
	public void setHistoriquePlateau(int[][] historiquePlateau) {
		this.historiquePlateau = historiquePlateau;
	}
	
	public ArrayList<int[]> getHistorique() {
		return this.historique;
	}
	public void setHistorique(ArrayList<int[]> historique) {
		this.historique = historique;
	}
	public boolean isTour() {
		return this.tour;
	}
	
	public void setTour(boolean tour) {
		this.tour = tour;
	}
	
	public JoueurAwale getJoueur1() {
		return this.joueur1;
	}
	
	public void setJoueur1(String nomJoueur) {
		if(getNbrJoueursHumain() == 0) {
			this.joueur1 = new JoueurAwaleIA(nomJoueur, 0, 1);
		}
		else if(getNbrJoueursHumain() == 1) {
			this.joueur1 = new JoueurAwaleHumain(nomJoueur, 0, 1);
		}
		else if(getNbrJoueursHumain() == 2) {
			this.joueur1 = new JoueurAwaleHumain(nomJoueur, 0, 1);
		}
	}
	
	//Pour sauvegarder les joueurs dans l'optique d'une sauvegarde de partie
	public void saveJoueur1(JoueurAwale joueur1) {
		this.joueur1 = joueur1;
	}
	
	public JoueurAwale getJoueur2() {
		return this.joueur2;
	}
	
	public void setJoueur2(String nomJoueur) {
		if(getNbrJoueursHumain() == 0) {
			this.joueur2 = new JoueurAwaleIA(nomJoueur, 0, 2);
		}
		else if(getNbrJoueursHumain() == 1) {
			this.joueur2 = new JoueurAwaleIA(nomJoueur, 0, 2);
		}
		else if(getNbrJoueursHumain() == 2) {
			this.joueur2 = new JoueurAwaleHumain(nomJoueur, 0, 2);
		}
	}
	
	//Pour sauvegarder les joueurs dans l'optique d'une sauvegarde de partie
	public void saveJoueur2(JoueurAwale joueur2) {
		this.joueur2 = joueur2;
	}
	
	public Awale getPartie() {
		return this.partie;
	}
	
	public void setPartie(String nomJeu, String regles, int difficulte) {
		this.partie  = new Awale(nomJeu,regles,difficulte);
	}
	
	public int getI() {	
		return i;
	}
	
	public void setI(int i) {
		this.i = i;
	}
	
	//methods :
	public void lancerUneNouvellePartie(int difficulte){
		setJoueur1("joueur1");
		setJoueur2("joueur2");
		
		setPartie("MonAwale","MesRegles",difficulte);
		this.getPartie().initialisationJeu();
		
	}
	
	public void stockerEtatMouvement(int[] etatActuel) {//Historique
		/*
		for(int j=0;j<12;j++) {
			this.historiquePlateau[this.getI()][j] = etatActuel[j];
		}
		*/
		//this.historique.addAll(Arrays.asList(etatActuel));
		this.historique.add(0,etatActuel.clone());
		this.setI(getI()+1);
	}
	
	public boolean verifierCoupValide(JoueurAwale joueur, int caseJouee) {//bonne case avec bonnes regles
		//case non vide :
		if(this.getPartie().getPlateau()[caseJouee] != 0) {
			if( joueur.getNumeroJoueur() == 1) {
				if( caseJouee >= 0 && caseJouee < 6 ) return true;
			}
			else {
				if( caseJouee >= 6 && caseJouee < 12 ) return true;
			}
		}
		return false;
	}
	
	@Override
	public JoueurAwale gestionTour() { //decide de qui va jouer
		if( this.getI()%2 == 0) return this.joueur2;
		return this.joueur1;
	}
	
	public int calculSommeGrainesEnJeu(JoueurAwale joueur) {
		int x = 0;
		if(joueur.getNumeroJoueur() == 1) {
			for(int i=0;i<6;i++) {
				x+= this.getPartie().getPlateau()[i];
			}
		}
		else {
			for(int i=6;i<12;i++) {
				x+= this.getPartie().getPlateau()[i];
			}
		}
		return x;
	}
	
	@Override
	public boolean finPartie() {//dire si c'est une fin de partie et arreter le jeu en fonction
		if( this.getPartie().getNbrGrainesEnJeu() <= 1 ) {
			this.ajoutGains();
			return true;
		}
		else if( gestionTour() == this.joueur1 && calculSommeGrainesEnJeu(this.joueur1) == 0 ) {
			this.ajoutGains();
			return true;
		}
		else if( gestionTour() == this.joueur2 && calculSommeGrainesEnJeu(this.joueur2) == 0 ) {
			this.ajoutGains();
			return true;
		}
		
		return false;
	}
	
	@Override
	public void gestionTemps() {//gere le temps alloue a chaque joueur tour a� tour
		
	}
	
	@Override
	public void getGagnant() {
		int score1 = getJoueur1().getScore();
		int score2 = getJoueur2().getScore();
		if( score1 == score2 )	
			System.out.println(" Score Egaux ! " + score1);
		else if( score1 > score2 )	
			System.out.println("  Gagnant : Joueur1 !!! " + score1  + " Score perdant : " + score2);
		else	
			System.out.println("  Gagnant : Joueur2 !!! " + score2 + " Score perdant : " + score1);
	}
	public void ajoutGains() {
		this.joueur2.setScore( this.joueur2.getScore() + calculSommeGrainesEnJeu(this.joueur2) );
		this.joueur1.setScore( this.joueur1.getScore() + calculSommeGrainesEnJeu(this.joueur1) );
	}
	
	public void afficheHistorique() {
		int taille=historique.size();
		int[] current;
		for(int i=0;i<taille;i++) {
			current=historique.get(i);
			for(int j=11;j>5;j--) {
				System.out.print(current[j]+"|");
			}
			System.out.println();
			for(int j=0;j<6;j++) {
				System.out.print(current[j]+"|");
			}
			System.out.println();System.out.println();
		}
	}
}

