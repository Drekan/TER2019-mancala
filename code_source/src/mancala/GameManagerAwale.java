package mancala;

import java.util.ArrayList;
import java.util.Arrays;

//gere le jeu en fonction des regles de l'awale
//il enregistre aussi l'historique de la partie
public class GameManagerAwale extends GameManager{
	
	private int nbrJoueursHumain;
	private boolean tour = true;
	private ArrayList<int[]> historique;
	
	private JoueurAwale joueur1;
	private JoueurAwale joueur2;
	private Awale partie;

	private int tourActuel ;
	
	//constructeurs :
	public GameManagerAwale(int nbrJoueursHumain) {
		if(nbrJoueursHumain<=2 && nbrJoueursHumain>=0) {
			this.nbrJoueursHumain = nbrJoueursHumain;
			this.tourActuel = 0 ;
			this.historique = new ArrayList<int[]>();
		}
	}
	//getters & setters :
	public int getNbrJoueursHumain() {
		return this.nbrJoueursHumain;
	}
	
	public void setNbrJoueursHumain(int nbrJoueursHumain) {	
		this.nbrJoueursHumain = nbrJoueursHumain;
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
			this.joueur1 = new JoueurAwaleIA(nomJoueur, 0, 1,0,6);
		}
		else if(getNbrJoueursHumain() == 1) {
			this.joueur1 = new JoueurAwaleHumain(nomJoueur, 0, 1,0,6);
		}
		else if(getNbrJoueursHumain() == 2) {
			this.joueur1 = new JoueurAwaleHumain(nomJoueur, 0, 1,0,6);
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
			this.joueur2 = new JoueurAwaleIA(nomJoueur, 0, 2,6,12);
		}
		else if(getNbrJoueursHumain() == 1) {
			this.joueur2 = new JoueurAwaleIA(nomJoueur, 0, 2,6,12);
		}
		else if(getNbrJoueursHumain() == 2) {
			this.joueur2 = new JoueurAwaleHumain(nomJoueur, 0, 2,6,12);
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
	
	public int getTourActuel() {	
		return this.tourActuel;
	}
	
	public void setTourActuel(int tourActuel) {
		this.tourActuel = tourActuel;
	}
	
	//methods :
	public void lancerUneNouvellePartie(int difficulte){
		setJoueur1("joueur1");
		setJoueur2("joueur2");
		
		setPartie("MonAwale","MesRegles",difficulte);
		this.getPartie().initialisationJeu();
		
	}
	
	public void stockerEtatMouvement(int[] etatActuel) {//Historique
		this.historique.add(0,etatActuel.clone());
		this.setTourActuel(getTourActuel()+1);
	}
	
	public boolean verifierCoupValide(JoueurAwale joueur, int caseJouee, int[] plateau) {//bonne case avec bonnes regles
		//case non vide :
		if( plateau[caseJouee] != 0 ) { //this.getPartie().getPlateau()
			if( caseJouee >= joueur.getMin() && caseJouee < joueur.getMax() && InterdictionAffamer(caseJouee) ) return true;
		}
		return false;
	}
	
	@Override
	public JoueurAwale gestionTour() { //decide de qui va jouer
		if( this.getTourActuel()%2 == 0) return this.joueur2;
		return this.joueur1;
	}
	
	public int calculSommeGrainesEnJeu(JoueurAwale joueur) {
		int x = 0;
		for(int i=joueur.getMin();i<joueur.getMax();i++) {
			x+= this.getPartie().getPlateau()[i];
		}
		return x;
	}
	
	@Override
	public boolean finPartie() {//dire si c'est une fin de partie et arreter le jeu en fonction
		if( this.getPartie().getNbrGrainesEnJeu() <= 1 ) {
			this.ajoutGains();
			System.out.println(" !! plus qu'une graine !! ");
			return true;
		}
		else if( gestionTour() == this.joueur1 && calculSommeGrainesEnJeu(this.joueur1) == 0 ) {
			this.ajoutGains();
			System.out.println(" !! plus de graines a jouer pour joueur 1 !! ");
			return true;
		}
		else if( gestionTour() == this.joueur2 && calculSommeGrainesEnJeu(this.joueur2) == 0 ) {
			this.ajoutGains();
			System.out.println(" !! plus de graines a jouer pour joueur 2 !! ");
			return true;
		}
		else if(NbRedondanceHistorique(18)>1) {
			this.ajoutGains();
			System.out.println("Redondances dans les coups joués. La partie s'arrête.");
			return true;
		}
		return false;
	}
	
	@Override
	public void gestionTemps() {//gere le temps alloue a chaque joueur tour a  tour
		
	}
	
	@Override
	public void getGagnant() {
		int score1 = getJoueur1().getScore();
		int score2 = getJoueur2().getScore();
		if( score1 == score2 )	
			System.out.println(" Score Egaux ! " + score1);
		else if( score1 > score2 )	
			System.out.println("Joueur 1 a gagn�");
		else	
			System.out.println("Joueur 2 a gagn�");
		System.out.println("Score joueur 1: "+ score1 + "\nScore joueur 2: " + score2);
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
	public ArrayList determinerCoupPossible(JoueurAwale joueur, int[] plateau) {
		ArrayList coupPossible = new ArrayList<>();
		for(int i = joueur.getMin(); i < joueur.getMax(); i++) {
			if( verifierCoupValide(joueur,i,plateau)) {
				coupPossible.add(i);
			}
		}
		
		return coupPossible;
	}
	
	public boolean InterdictionAffamer(int caseJouee) {//renvoie vrai si on n'affame pas l'adversaire ou faux sinon
		if ( (this.gestionTour() == this.getJoueur1() && calculSommeGrainesEnJeu(this.getJoueur2()) == 0) || ( this.gestionTour() == this.getJoueur2() && calculSommeGrainesEnJeu(this.getJoueur1()) == 0 ) ) {
			int nbrGrainesJouee = this.partie.getPlateau()[caseJouee];
			int resteADeposer = nbrGrainesJouee-(this.gestionTour().getMax() - caseJouee);
			//System.out.println("InterdictionAffamer/getMax() : " + this.gestionTour().getMax());
			if( resteADeposer <= 0 )
				return false ;
		}
		return true;
	}
	
	public boolean InterdictionAffamer() {
		if ( gestionTour() == this.joueur1 && calculSommeGrainesEnJeu(this.joueur2) == 0 ) {
			int max = this.joueur1.getMax();
			//int min = this.joueur2.getMin();
			int compteur = 0;
			for( int i=this.joueur1.getMax() ; i> this.joueur2.getMin() ; i--) {
				if( this.getPartie().getPlateau()[max] <= compteur ) {
					return true;
				}
				compteur++;
				max--;
			}
		}
		return false;
	}
	
	public boolean plateauxEgaux(int[] plateau1,int[] plateau2) {
		boolean equal=(plateau1.length==plateau2.length);
		int i=plateau1.length-1;
		while(equal && i>=0) {
			if(plateau1[i]!=plateau2[i]) {
				equal=false;
			}
			i--;
		}
		return equal;
	}
	
	public int NbRedondanceHistorique(int profondeur) {
		int redondances=0;
		int profondeurEffective=Math.min(this.historique.size()-1,profondeur);
		for(int i=0;i<profondeurEffective;i++) {
			if(plateauxEgaux(this.historique.get(this.historique.size()-1),this.historique.get(i))) {
				redondances++;
			}
		}
		return redondances;
	}
}

