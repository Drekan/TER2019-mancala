package mancala;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

//gere le jeu en fonction des regles de l'awale
//il enregistre aussi l'historique de la partie
public class GameManagerAwale extends GameManager{
	
	private int nbrJoueursHumain;
	private ArrayList<int[]> historique;
	
	private JoueurAwale joueur1;
	private JoueurAwale joueur2;
	private Awale partie;

	private int tourActuel ;
	
	//constructeurs :
	public GameManagerAwale() {
		this.nbrJoueursHumain=choisirModeJeu();
		this.tourActuel = 0 ;
		this.historique = new ArrayList<int[]>();
	}
	public GameManagerAwale(int modeJeu) {
		this.nbrJoueursHumain=(modeDeJeuValide(modeJeu)?modeJeu:0);
		this.tourActuel = 0 ;
		this.historique = new ArrayList<int[]>();
	}
	
	/* Cette méthode teste si un mode de jeu donné
	 *  en paramètre est valide ou non. On peut donc gérer
	 *  toutes les valeurs que l'on accepte, facilement
	 */
	public boolean modeDeJeuValide(int modeJeu) {
		return !(modeJeu<0 || modeJeu>2);
	}
	
	/* Cette méthode affiche les différents modes de jeux jouables,
	 *  et demande à l'utilisateur d'en choisir un
	 */
	public int choisirModeJeu() {
		int modeDeJeu;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("----Choisissez le mode de jeu----");
		System.out.println("0. IA       VS   IA");
		System.out.println("1. IA       VS   Joueur");
		System.out.println("2. Joueur   VS   Joueur");
		
		do {
			System.out.print("\nVotre choix >>");
			modeDeJeu = sc.nextInt();
		}while(!modeDeJeuValide(modeDeJeu));
		
		return modeDeJeu;
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
	
	public JoueurAwale getJoueur1() {
		return this.joueur1;
	}
	
	public void setJoueur1(String nomJoueur) {
		if(getNbrJoueursHumain() == 0) {
			this.joueur1 = new JoueurAwaleIA(nomJoueur, 0, 1,0,5);
		}
		else if(getNbrJoueursHumain() >= 1) {
			this.joueur1 = new JoueurAwaleHumain(nomJoueur, 0, 1,0,5);
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
		if(getNbrJoueursHumain() <= 1) {
			this.joueur2 = new JoueurAwaleIA(nomJoueur, 0, 2,6,11);
		}
		else if(getNbrJoueursHumain() == 2) {
			this.joueur2 = new JoueurAwaleHumain(nomJoueur, 0, 2,6,11);
		}
	}
	
	//Pour sauvegarder les joueurs dans l'optique d'une sauvegarde de partie
	public void saveJoueur2(JoueurAwale joueur2) {
		this.joueur2 = joueur2;
	}
	
	public Awale getPartie() {
		return this.partie;
	}
	
	public int getTourActuel() {	
		return this.tourActuel;
	}
	
	public void setTourActuel(int tourActuel) {
		this.tourActuel = tourActuel;
	}
	
	public void initJoueurs(String J1,String J2) {
		setJoueur1(J1);
		setJoueur2(J2);
	}
	
	/* Cette méthode gère la mise en place de la difficulté
	 * 	NB: On n'a pas besoin d'assigner de difficulté lorsque deux
	 * 		joueurs humains s'affrontent
	 */
	private int choisirDifficulte() {
		int difficulte=0;
		if(this.nbrJoueursHumain!=2) {
			Scanner sc=new Scanner(System.in);
			System.out.println("----Choisissez la difficulté de l'IA----");
			System.out.println("0. IA naïve (random)");
			System.out.println("1. IA minimax");
			do {
				System.out.print("\nVotre choix >>");
				difficulte=sc.nextInt();
			}while(difficulte<0 || difficulte>1);
		}
		return difficulte;
	}
	
	
	/* Méthode qui gère le déroulement d'une partie,
	 * les coups joués et par qui. Elle ne s'arrête que 
	 * lorsqu'une(au moins) des conditions de fins de partie est remplie 
	 */
	public void commencerPartie() {
		Scanner sc=new Scanner(System.in);
		while( !this.finPartie() ) {
			System.out.println("JOUEUR ACTUEL : "+joueurActuel().getNom());
			
			int coupJoue = this.joueurActuel().choisirUnCoup(this);
			if(this.verifierCoupValide(this.joueurActuel(),coupJoue,this.getPartie().getPlateau())){
				this.joueurActuel().jouerUnCoup(coupJoue, this);
			}
			
			
			//se pencher sur ce bout de code plus tard -Bastien (note à moi même)--------------
			System.out.println();
			this.stockerEtatMouvement(this.getPartie().etatActuel());
			for(int i = 11;i>5;i--) {
				System.out.print(this.getPartie().etatActuel()[i] + " | ");
			}
			System.out.println();
			for(int i = 0;i<6;i++) {
				System.out.print(this.getPartie().etatActuel()[i] + " | ");
			}
			
			System.out.println();
			
			System.out.println("Nbr graines en jeu : " + this.getPartie().getNbrGrainesEnJeu());
			//---------------------------------------------------------------------------------
			
		}
	}
	
	//methods :
	
	/*Méthode qui instancie le plateau de jeu et
	 * démarre la partie avec la difficulté adéquate !
	 */
	public void lancerUneNouvellePartie(){
		initJoueurs("joueur1","joueur2");
		this.partie=new Awale("MonAwale","MesRegles",choisirDifficulte());
		this.getPartie().initialisationJeu();
		this.commencerPartie();
		
	}
	
	public void stockerEtatMouvement(int[] etatActuel) {//Historique
		this.historique.add(0,etatActuel.clone());
		this.setTourActuel(getTourActuel()+1);
	}
	
	public boolean verifierCoupValide(JoueurAwale joueur, int caseJouee, int[] plateau) {//bonne case avec bonnes regles
		//case non vide :
		if( plateau[caseJouee] != 0 ) { //this.getPartie().getPlateau()
			if( caseJouee >= joueur.getMin() && caseJouee <= joueur.getMax() && InterdictionAffamer(caseJouee) ) return true;
		}
		return false;
	}
	
	@Override
	public JoueurAwale joueurActuel() { //decide de qui va jouer
		if( this.getTourActuel()%2 == 0) return this.joueur2;
		return this.joueur1;
	}
	
	public int calculSommeGrainesEnJeu(JoueurAwale joueur) {
		int x = 0;
		for(int i=joueur.getMin();i<=joueur.getMax();i++) {
			x+= this.getPartie().getPlateau()[i];
		}
		return x;
	}
	
	@Override
	public boolean finPartie() {//dire si c'est une fin de partie et arreter le jeu en fonction
		boolean finDePartie=false;
		String messageFinDePartie="";
		
		if( this.getPartie().getNbrGrainesEnJeu() <= 1 ) {
			messageFinDePartie=" !! plus qu'une graine !! ";
			finDePartie=true;
		}
		else if(NbRedondanceHistorique(18)>1) {
			messageFinDePartie="Redondances dans les coups joués. La partie s'arrête.";
			finDePartie=true;
		}
		else if(calculSommeGrainesEnJeu(joueurActuel()) == 0 ) {
			messageFinDePartie=" !! plus de graines a jouer pour "+ joueurActuel().getNom() +" !! ";
			finDePartie=true;
		}
		
		boolean affamerPartout=true;
		for(int i=this.joueurActuel().getMin();i<=joueurActuel().getMax();i++) {
			if(InterdictionAffamer(i)) {
				affamerPartout=false;
			}
		}
		finDePartie=finDePartie||affamerPartout;
		
		if(finDePartie){
			ajoutGains();
			System.out.println(messageFinDePartie);
		}
		
		return finDePartie;
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
		for(int i = joueur.getMin(); i <=joueur.getMax(); i++) {
			if( verifierCoupValide(joueur,i,plateau)) {
				coupPossible.add(i);
			}
		}
		
		return coupPossible;
	}
	
	public boolean InterdictionAffamer(int caseJouee) {//renvoie vrai si on n'affame pas l'adversaire ou faux sinon
		if ( (this.joueurActuel() == this.getJoueur1() && calculSommeGrainesEnJeu(this.getJoueur2()) == 0) || ( this.joueurActuel() == this.getJoueur2() && calculSommeGrainesEnJeu(this.getJoueur1()) == 0 ) ) {
			int nbrGrainesJouee = this.partie.getPlateau()[caseJouee];
			int resteADeposer = nbrGrainesJouee-(this.joueurActuel().getMax() - caseJouee);
			if( resteADeposer <= 0 )
				return false ;
		}
		return true;
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

