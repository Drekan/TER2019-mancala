package mancala;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

//gere le jeu en fonction des regles de l'awale
//il enregistre aussi l'historique de la partie
public class GameManagerAwale extends GameManager implements Cloneable{
	
	private int nbrJoueursHumain;
	private ArrayList<int[]> historique;
	
	private JoueurAwale joueur1;
	private JoueurAwale joueur2;
	private Awale partie;

	private int tourActuel ;
	
	private boolean vocal;//true pour parler, sinon chuuut
	
	//constructeurs :
	public GameManagerAwale() {
		this.nbrJoueursHumain=choisirModeJeu();
		this.tourActuel = 0 ;
		this.historique = new ArrayList<int[]>();
	}
	public GameManagerAwale(int nbrJoueursHumain, int tourActuel) {
		this.nbrJoueursHumain = nbrJoueursHumain;
		this.tourActuel = tourActuel ;
		this.historique = new ArrayList<int[]>();
		this.partie=new Awale("MonAwale","MesRegles");
		this.getPartie().initialisationJeu();
	}
	public GameManagerAwale(int modeJeu) {
		this.nbrJoueursHumain=(modeDeJeuValide(modeJeu)?modeJeu:0);
		this.tourActuel = 0 ;
		this.historique = new ArrayList<int[]>();
	}
	public GameManagerAwale(int modeJeu,int difficulte,int tourActuel){
		this.nbrJoueursHumain=(modeDeJeuValide(modeJeu)?modeJeu:0);
		this.tourActuel = tourActuel;
		this.historique = new ArrayList<int[]>();
		this.initJoueurs("joueur1","joueur2");
		this.partie=new Awale("MonAwale","MesRegles");
		this.getPartie().initialisationJeu();
	}
	public GameManagerAwale(int modeJeu,int difficulte,int tourActuel,String j1,String j2){
		this.nbrJoueursHumain=(modeDeJeuValide(modeJeu)?modeJeu:0);
		this.tourActuel = tourActuel;
		this.historique = new ArrayList<int[]>();
		this.initJoueurs(j1,j2);
		this.partie=new Awale("MonAwale","MesRegles");
		this.getPartie().initialisationJeu();
	}
	
	/* Cette methode teste si un mode de jeu donne
	 *  en parametre est valide ou non. On peut donc gerer
	 *  toutes les valeurs que l'on accepte, facilement
	 */
	public boolean modeDeJeuValide(int modeJeu) {
		return !(modeJeu<0 || modeJeu>2);
	}
	

	
	/* Cette methode affiche les differents modes de jeux jouables,
	 *  et demande a l'utilisateur d'en choisir un
	 */
	public int choisirModeJeu() {
		int modeDeJeu;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n----Choisissez le mode de jeu----");
		System.out.println("0. IA       VS   IA");
		System.out.println("1. IA       VS   Joueur");
		System.out.println("2. Joueur   VS   Joueur");
		
		do {
			System.out.print("\nVotre choix >> ");
			modeDeJeu = sc.nextInt();
		}while(!modeDeJeuValide(modeDeJeu));
		
		return modeDeJeu;
	}
	//getters & setters :
	public boolean getVocal() {
		return this.vocal;
	}
	
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
			this.joueur1 = new JoueurAwaleIA(nomJoueur, 0, 1,0,5,24);
		}
		else if(getNbrJoueursHumain() >= 1) {
			this.joueur1 = new JoueurAwaleHumain(nomJoueur, 0, 1,0,5,24);
		}
	}
	public void setJoueur1(String nomJoueur,int difficulte) {
		if(getNbrJoueursHumain() == 0) {
			this.joueur1 = new JoueurAwaleIA(nomJoueur, 0, 1,0,5,24,difficulte);
		}
		else if(getNbrJoueursHumain() >= 1) {
			this.joueur1 = new JoueurAwaleHumain(nomJoueur, 0, 1,0,5,24);
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
			this.joueur2 = new JoueurAwaleIA(nomJoueur, 0, 2,6,11,24);
		}
		else if(getNbrJoueursHumain() == 2) {
			this.joueur2 = new JoueurAwaleHumain(nomJoueur, 0, 2,6,11,24);
		}
	}
	public void setJoueur2(String nomJoueur,int difficulte) {
		if(getNbrJoueursHumain() <= 1) {
			this.joueur2 = new JoueurAwaleIA(nomJoueur, 0, 2,6,11,24,difficulte);
		}
		else if(getNbrJoueursHumain() == 2) {
			this.joueur2 = new JoueurAwaleHumain(nomJoueur, 0, 2,6,11,24);
		}
	}
	
	//Pour sauvegarder les joueurs dans l'optique d'une sauvegarde de partie
	public void saveJoueur2(JoueurAwale joueur2) {
		this.joueur2 = joueur2;
	}
	
	public Awale getPartie() {
		return this.partie;
	}
	
	public void setPartie(Awale partie) {
		this.partie = partie;
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
	public void initJoueurs(String J1,int difficulte1,String J2,int difficulte2) {
		setJoueur1(J1,difficulte1);
		setJoueur2(J2,difficulte2);
	}
	//GameManagerAwale(int modeJeu,int difficulte,int tourActuel,String j1,String j2)
	
	
	/* Methode qui produit une replique de la partie en cours
	 * chaque methode clone() des attributs non primitifs est appelee
	 * @see java.lang.Object#clone()
	 */
	public GameManagerAwale clone() {
		GameManagerAwale clone=new GameManagerAwale(this.nbrJoueursHumain,this.tourActuel);
		clone.historique=(ArrayList<int[]>)this.historique.clone();
		clone.partie=this.partie.clone();
		
		//On doit connaitre le type des joueurs 1 et 2 avant de les cloner proprement
		
		//Joueur 1
		if(this.joueur1.getClass()==new JoueurAwaleHumain().getClass()) {
			clone.joueur1=(JoueurAwaleHumain)this.joueur1.clone();
		}
		else {
			clone.joueur1=(JoueurAwaleIA)this.joueur1.clone();
		}
		
		//Joueur2
		if(this.joueur2.getClass()==new JoueurAwaleHumain().getClass()) {
			clone.joueur2=(JoueurAwaleHumain)this.joueur2.clone();
		}
		else {
			clone.joueur2=(JoueurAwaleIA)this.joueur2.clone();
		}
		
		
		return clone;
	}


	public void affichePlateau(){
		//se pencher sur ce bout de code plus tard -Bastien (note a moi meme)--------------

		this.stockerEtatMouvement(this.getPartie().etatActuel());
		
		if(vocal) {
			System.out.println();
			for(int i = 11;i>5;i--) {
				System.out.print(this.getPartie().etatActuel()[i] + " | ");
			}
			System.out.println();
			for(int i = 0;i<6;i++) {
				System.out.print(this.getPartie().etatActuel()[i] + " | ");
			}
	
			System.out.println();
	
			System.out.println("Nbr graines en jeu : " + this.getPartie().getNbrGraines());
		}
		//---------------------------------------------------------------------------------
	}
	
	
	/* Methode qui gere le deroulement d'une partie,
	 * les coups joues et par qui. Elle ne s'arrete que 
	 * lorsqu'une (au moins) des conditions de fins de partie est remplie 
	 */
	public void commencerPartie(boolean vocal) {
		this.vocal=vocal;
		Scanner sc=new Scanner(System.in);
		while( !this.finPartie() ) {
			this.affichePlateau();
			
			if(vocal)
				System.out.println("JOUEUR ACTUEL : "+joueurActuel().getNom());

			int coupJoue = this.joueurActuel().choisirUnCoup(this);

			if(this.verifierCoupValide(this.joueurActuel(),coupJoue,this.getPartie().getPlateau())){
				this.joueurActuel().jouerUnCoup(coupJoue, this,vocal);
			}

			this.setTourActuel(getTourActuel()+1);

			//Pour attendre un peu
			this.delay(60);
		}
		this.affichePlateau();
	}

	//methods :

	/*Methode qui instancie le plateau de jeu et
	 * demarre la partie avec la difficulte adequate !
	 */
	public void lancerUneNouvellePartie(boolean silence){
		this.initJoueurs("joueur1","joueur2");
		this.partie=new Awale("MonAwale","MesRegles");
		this.getPartie().initialisationJeu();
		this.commencerPartie(silence);

	}

	public void lancerUneNouvellePartieGraphique(DrawingManagerAwale window){
		initJoueurs("Estelle","Chahinez");
		this.partie=new Awale("MonAwale","MesRegles");
		this.getPartie().initialisationJeu();
		this.commencerPartieGraphique(window);

	}

	public void commencerPartieGraphique(DrawingManagerAwale window) {
        window.getNameList().get(0).setText(this.joueur1.getNom());
        window.getNameList().get(1).setText(this.joueur2.getNom());

		while( !this.finPartie() ) {
		    if(this.joueurActuel() == this.joueur1){
                window.getNameList().get(0).setForeground(Color.RED);
                window.getNameList().get(1).setForeground(Color.BLACK);
            }
		    else{
                window.getNameList().get(0).setForeground(Color.BLACK);
                window.getNameList().get(1).setForeground(Color.RED);
            }

            window.getScoreList().get(0).setText(String.valueOf(this.joueur1.getScore()));
            window.getScoreList().get(1).setText(String.valueOf(this.joueur2.getScore()));

			this.enableAll(window);
			this.disableCaseNonValide(window);

			int coupJoue;

			if(this.getNbrJoueursHumain() == 0){
				coupJoue = this.joueurActuel().choisirUnCoup(this);
			}
			else if(this.getNbrJoueursHumain() == 1){
				if(this.getTourActuel()%2 == 0){
					coupJoue = this.joueurActuel().choisirUnCoup(this);
				}
				else{
					do {
						coupJoue = window.getCoupActu();
						window.setCoupActu(-1);
						this.delay(1000/60);
					}while( (coupJoue == -1) || (!this.verifierCoupValide(this.joueurActuel(),coupJoue,this.getPartie().getPlateau())) );
				}
			}
			else{
				do {
					coupJoue = window.getCoupActu();
					window.setCoupActu(-1);
					this.delay(1000/60);
				}while( (coupJoue == -1) || (!this.verifierCoupValide(this.joueurActuel(),coupJoue,this.getPartie().getPlateau())) );
			}

            int graineRestante = this.getPartie().etatActuel()[coupJoue];

			this.joueurActuel().jouerUnCoup(coupJoue,this,true);

			for (int i = 0 ; i < 12 ; i++){
				window.getButtonList().get(i).setForeground(Color.WHITE);
			}

			window.getButtonList().get(coupJoue).setForeground(Color.RED);

			for (int i = coupJoue ; i < 12 ; i++) {
                if(graineRestante >= 0 && i != coupJoue) {
                    window.getButtonList().get(i).setForeground(Color.BLUE);
                }
				if(this.getPartie().etatActuel()[i] != 0) {
					window.getButtonList().get(i).setEnabled(true);
				}
				window.getButtonList().get(i).setLabel("" + this.getPartie().etatActuel()[i]);
				this.delay(500);
				graineRestante--;
			}
			for (int i = 0 ; i < coupJoue ; i++) {
                if(graineRestante >= 0 && i != coupJoue) {
                    window.getButtonList().get(i).setForeground(Color.BLUE);
                }
				if (this.getPartie().etatActuel()[i] != 0) {
					window.getButtonList().get(i).setEnabled(true);
				}
				window.getButtonList().get(i).setLabel("" + this.getPartie().etatActuel()[i]);
				this.delay(500);
                graineRestante--;
			}

			this.setTourActuel(getTourActuel()+1);
		}
	}

	public void stockerEtatMouvement(int[] etatActuel) {//Historique
		this.historique.add(0,etatActuel.clone());
	}
	
	public boolean verifierCoupValide(JoueurAwale joueur, int caseJouee, int[] plateau) {//bonne case avec bonnes regles
		//case non vide :
		if( plateau[caseJouee] != 0 ) {
			if( caseJouee >= joueur.getMin() && caseJouee <= joueur.getMax() && interdictionAffamer(caseJouee) ) return true;
		}
		return false;
	}
	
	@Override
	public JoueurAwale joueurActuel() { //decide de qui va jouer
		if( this.getTourActuel()%2 == 0) return this.joueur2;
		return this.joueur1;
	}

	@Override
	public boolean finPartie() {//dire si c'est une fin de partie et arreter le jeu en fonction
		boolean finDePartie=false;
		String messageFinDePartie="";
		
		if( this.getPartie().getNbrGraines() <= 1 ) {
			messageFinDePartie=" !! plus qu'une graine !! ";
			finDePartie=true;
		}
		//2*6*3 = 36, 2 pour les 2 joueurs, 6 pour le nombre de cases d'un cote, 3 pour le nombre de tour de plateau
		//36 >= 3 fois le tour du plateau redondant 
		else if(nbRedondanceHistorique(36)>=3) {
			messageFinDePartie="Redondances dans les coups joues. La partie s'arrete.";
			finDePartie=true;
		}
		else if( this.joueurActuel().getNbrGraineJoueur() == 0 ) {
			messageFinDePartie=" !! plus de graines a jouer pour "+ joueurActuel().getNom() +" !! ";
			finDePartie=true;
		}
		
		boolean affamerPartout=true;
		for(int i=this.joueurActuel().getMin();i<=joueurActuel().getMax();i++) {
			if(interdictionAffamer(i)) {
				affamerPartout=false;
			}
		}
		
		if(affamerPartout) {
			messageFinDePartie="Adversaire affame.";
		}
		
		finDePartie=finDePartie||affamerPartout;
		
		if(finDePartie){
			ajoutGains();
			if(vocal) {
				System.out.println();
				System.out.println(messageFinDePartie);
				System.out.println();
			}
		}
		
		return finDePartie;
	}
	
	@Override
	public void gestionTemps() {//gere le temps alloue a chaque joueur tour a  tour
		
	}
	
	@Override
	public JoueurAwale getGagnant() {
		int gagnant = 0;
		int score1 = getJoueur1().getScore();
		int score2 = getJoueur2().getScore();
		if( score1 == score2 ) {
			if(vocal)
				System.out.println(" Score Egaux ! " + score1);
		}
		else if( score1 > score2 ) {
			if(vocal)
				System.out.println("Joueur 1 a gagne");
			gagnant = 1;
		}
		else {
			if(vocal)
				System.out.println("Joueur 2 a gagne");
			gagnant = 2;
		}
		if(vocal)
			System.out.println("Score joueur 1: "+ score1 + "\nScore joueur 2: " + score2);
		if(gagnant == 1) {
			return this.joueur1;
		}
		else if(gagnant == 2) {
			return this.joueur2;
		}
		return null;
	}
	
	
	public void ajoutGains() {
		this.joueur2.setScore( this.joueur2.getScore() + this.getJoueur2().getNbrGraineJoueur() );
		this.joueur1.setScore( this.joueur1.getScore() + this.getJoueur1().getNbrGraineJoueur() );
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
	
	public boolean interdictionAffamer(int caseJouee) {//renvoie vrai si on n'affame pas l'adversaire ou faux sinon
		if ( (this.joueurActuel() == this.getJoueur1() && this.getJoueur2().getNbrGraineJoueur() == 0) || ( this.joueurActuel() == this.getJoueur2() && this.getJoueur1().getNbrGraineJoueur() == 0 ) ) {
			int nbrGrainesJouee = this.getPartie().getPlateau()[caseJouee];
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
	
	public int nbRedondanceHistorique(int profondeur) {
		int redondances=0;
		int profondeurEffective=Math.min(this.historique.size()-1,profondeur);
		for(int i=0;i<profondeurEffective;i++) {
			if(plateauxEgaux(this.historique.get(0),this.historique.get(i))) {
				redondances++;
			}
		}
		return redondances;
	}

	public void delay(int temps){
		try {
			Thread.sleep(temps);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void resetPartie() {
		this.historique=new ArrayList<int[]>();
		this.joueur1.reset();
		this.joueur2.reset();
		this.tourActuel=0;
		this.partie.initialisationJeu();
	}

	public void enableAll(DrawingManagerAwale window){
		for (int i = 0 ; i < 12 ; i++){
			window.getButtonList().get(i).setEnabled(true);
		}
	}

	public void disableCaseNonValide(DrawingManagerAwale window){
		for (int i = 0 ; i < 12 ; i++){
			if (Integer.parseInt(window.getButtonList().get(i).getText()) == 0){
				window.getButtonList().get(i).setEnabled(false);
			}
		}
	}
}
