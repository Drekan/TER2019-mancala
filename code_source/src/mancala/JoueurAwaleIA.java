package mancala;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

//les methodes de decision seront utilisees(MiniMax, Evalution...)
public class JoueurAwaleIA extends JoueurAwale implements Cloneable{
	//Pour calculer le nombre d'appels récursifs de minimax
	private int compteur = 0;
	
	//difficulte de l'IA
	private int difficulte=-1;
	
	//heuristiques actives
	private boolean[] heuristique= {false,false,false,false,true,false,false,false,false};
	//leur poids
	double[] poids= {0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5};
	
	//Pour calculer le temps d'execution de minimax
	private long time = 0;
	
	//profondeur maximum
	private int profondeurMax=-1;
	
	public int getProfondeurMax(){
		return this.profondeurMax;
	}
	
	public int getCompteur() 
	{
		return compteur;
	}

	public void setCompteur(int compteur) 
	{
		this.compteur = compteur;
	}
	
	public long getTime() 
	{
		return time;
	}

	public void setTime(long time) 
	{
		this.time = time;
	}
	
	public int getDifficulte() {
		return difficulte;
	}
	
	public void setDifficulte(int dif) {
		this.difficulte=(difficulteValide(dif)?dif:0);
	}
	
	public void setNomParDefaut() {
		switch(this.difficulte) {
		case 0:
			this.setNomJoueur("IA_RANDOM_J"+this.getNumeroJoueur());
			break;
			
		case 1:
			this.setNomJoueur("IA_MINMAX_J"+this.getNumeroJoueur());
			break;
			
		case 2:
			this.setNomJoueur("IA_ALPHABETA_J"+this.getNumeroJoueur());
			break;
			
		default:
			this.setNomJoueur("IA_J"+this.getNumeroJoueur());
			break;
		}
	}
	
	//constructeurs :
	public JoueurAwaleIA(String nomJoueur, int score, int numeroJoueur,int min,int max, int nbrGraineJoueur) {
		super(nomJoueur, score, numeroJoueur, min, max, nbrGraineJoueur);
		choisirDifficulte();
	}
	public JoueurAwaleIA(String nomJoueur, int score, int numeroJoueur,int min,int max, int nbrGraineJoueur,int difficulte) {
		super(nomJoueur, score, numeroJoueur, min, max, nbrGraineJoueur);
		setDifficulte(difficulte);
	}
	public JoueurAwaleIA() {
		
	}
	
	public JoueurAwaleIA clone() {
		JoueurAwaleIA clone=new JoueurAwaleIA(this.getNom(),
													this.getScore(),
													this.getNumeroJoueur(),
													this.getMin(),
													this.getMax(),
													this.getNbrGraineJoueur(),
													this.difficulte);
		clone.compteur=this.compteur;
		clone.time=this.time;
		return clone;
	}
	
	private void choisirDifficulte() {
		int difficulte=0;
		Scanner sc=new Scanner(System.in);
		System.out.println("\n----Choisissez la difficulte de l'IA <"+this.getNom()+">----");
		System.out.println("0. IA naive (random)");
		System.out.println("1. IA minimax");
		System.out.println("2. IA alphaBeta");
		do {
			System.out.print("\nVotre choix >> ");
			difficulte=sc.nextInt();
		}while(difficulte<0 || difficulte>2);
		this.setDifficulte(difficulte);
	}
	
	
	/* Cette methode teste si une difficulte donnee
	 *  en parametre est valide ou non. On peut donc gerer
	 *  toutes les valeurs que l'on accepte, facilement
	 */
	public boolean difficulteValide(int difficulte) {
		return (difficulte==0 || difficulte==1 || difficulte==2);
	}
	
	
	//methods:
	public int[] simulerUnCoup(int caseJouee, GameManagerAwale arbitreAwale)
	{
		int plateauSimule[] = new int[12];
		
		for(int i = 0; i < 12; i++)
		{
			plateauSimule[i] = arbitreAwale.getPartie().getPlateau()[i];
		}
		miseAJourPlateauSimuler(plateauSimule, caseJouee);
		
		arbitreAwale.setTourActuel(arbitreAwale.getTourActuel() + 1);
		
		return plateauSimule;
	}
	
	//Permet de savoir qui gagne
	public int vainqueur(GameManagerAwale arbitreAwaleSimule)
	{
		arbitreAwaleSimule.ajoutGains();
		
		int scoreJoueur1 = arbitreAwaleSimule.getJoueur1().getScore(), 
			scoreJoueur2 = arbitreAwaleSimule.getJoueur2().getScore();		
		int joueurGagnant = -1;
		
		if(scoreJoueur1 > scoreJoueur2)
		{
			joueurGagnant = 1;
		}
		else if(scoreJoueur1 < scoreJoueur2)
		{
			joueurGagnant = 2;
		}
		else
		{
			joueurGagnant = 0;
		}
		
		return joueurGagnant;
	}
	
	public int differenceScore(int scoreJoueurA, int scoreJoueurB)
	{
		int difference = scoreJoueurA - scoreJoueurB;
		
		return difference;
	}
	
	public int simulerFinPartie(ArrayList<int []> historique, GameManagerAwale arbitreAwaleSimule) 
	{
		int joueurGagnant = -1;
		
		GameManagerAwale arbitreSimule = arbitreAwaleSimule.clone();
		
		if(arbitreSimule.getPartie().getNbrGraines() <= 1) 
		{
			joueurGagnant = vainqueur(arbitreSimule);
		}
		
		else if(arbitreSimule.nbRedondanceHistorique(36) >= 3)
		{
			joueurGagnant = vainqueur(arbitreSimule);
		}
		
		else if(arbitreSimule.joueurActuel().getNbrGraineJoueur() == 0 ) 
		{
			joueurGagnant = vainqueur(arbitreSimule);
		}
		
		boolean affamerPartout = true;
		
		for(int i = arbitreSimule.joueurActuel().getMin(); i <= arbitreSimule.joueurActuel().getMax(); i++) 
		{
			if(arbitreSimule.interdictionAffamer(i)) 
			{
				affamerPartout = false;
			}
		}
		
		if(affamerPartout)
		{
			joueurGagnant = vainqueur(arbitreSimule);
		}
		
		return joueurGagnant;
	}
	
	/* Heuristique 1:
	 * L'objectif de cet heuristique est de minimiser
	 * le nombre de cases vulnerables
	 */
	private double H1(int numeroJoueur,int[] plateau) {
		int nbCasesVulnerables=0;
		
		int debut=(numeroJoueur==1?0:6);
		int fin=(numeroJoueur==2?6:12);
		
		for(int i=debut;i<fin;i++) {
			if(plateau[i]==1 || plateau[i]==2) {
				nbCasesVulnerables++;
			}
		}
		
		return 1-((double)(nbCasesVulnerables)*1/6);
		
	}
	
	/* Heuristique 2:
	 * L'objectif de cet heuristique est de maximiser
	 * notre nombre de graines
	 */
	private double H2(int numeroJoueur,int[] plateau) {
		double nombreGraine=0;
		double nombreGraineJoueur=0;
		
		int debut=(numeroJoueur==1?0:6);
		int fin=(numeroJoueur==2?6:12);
		
		for(int i=0;i<12;i++) {
			nombreGraine+=plateau[i];
			
			if(i>=debut && i<fin) {
				nombreGraineJoueur+=plateau[i];
			}
		}
		
		return (nombreGraine==0?0:(double)(nombreGraineJoueur)/(double)(nombreGraine));
	}
	
	/* Heuristique 3:
	 * L'objectif de cet heuristique est de minimiser
	 * le nombre de cases vides
	 */
	private double H3(int numeroJoueur,int[] plateau) {
		int nbCasesVides=0;
		
		int debut=(numeroJoueur==1?0:6);
		int fin=(numeroJoueur==2?6:12);
		
		for(int i=debut;i<fin;i++) {
			if(plateau[i]==0) {
				nbCasesVides++;
			}
		}
		
		return 1-((double)(nbCasesVides)*1/6);
		
	}
	
	/* Heuristique 4:
	 * L'objectif de cet heuristique est de valoriser
	 * les etats du jeu ou les graines sont a� droite
	 */
	private double H4(int numeroJoueur,int[] plateau) {
		int nombreGrainesJoueur=0;
		double valeurH4=0;
		
		double[] poids= {0,1/5,2/5,3/5,4/5,1};
		
		int debut=(numeroJoueur==1?0:6);
		int fin=(numeroJoueur==1?6:12);
		
		for(int i=debut;i<fin;i++) {
			nombreGrainesJoueur++;
		}
		
		if(nombreGrainesJoueur!=0) {
			int poidsActuel=0;
			for(int i=debut;i<fin;i++) {
				
				valeurH4+=poids[poidsActuel]*((double)(plateau[i])/(double)(nombreGrainesJoueur));
				poidsActuel++;
			}
		}
		
		return valeurH4;
		
	}
	
	
	/* Heuristique 5:
	 * L'objectif de cet heuristique est de minimiser
	 * le score de l'adversaire
	 */
	private double H5(int scoreJoueur,int scoreAdversaire) {
		return 1-((double)scoreAdversaire*((double)1/48));
	}
	
	/* Heuristique 6:
	 * On essaie d'avoir le plus de graines possible dans la case la plus à gauche 
	 */
	private double H6(int numeroJoueur,int[] plateau) {
		int nombreGrainesJoueur=0;
		int debut=(numeroJoueur==1?0:6);
		int fin=(numeroJoueur==1?6:12);
		
		for(int i=debut;i<fin;i++) {
			nombreGrainesJoueur++;
		}
		return (double)plateau[debut]/(double)nombreGrainesJoueur;
	}
	
	/* Heuristique 7:
	 * On essaie d'avoir le plus de cases jouables possibles 
	 */
	private double H7(int numeroJoueur,int[] plateau) {
		int nombreCasesJouables=0;
		int debut=(numeroJoueur==1?0:6);
		int fin=(numeroJoueur==1?6:12);
		
		for(int i=debut;i<fin;i++) {
			if(plateau[i]>0) {
				nombreCasesJouables++;
			}
		}
		return (1/6)*(double)nombreCasesJouables;
	}
	
	/* Heuristique 8:
	 * L'objectif de cet heuristique est de valoriser
	 * les etats du jeu où les cases de droites sont jouées
	 */
	private double H8(int numeroJoueur,int[] plateau) {
		int nombreGrainesJoueur=0;
		double valeurH8=0;
		
		double[] poids= {1,4/5,3/5,2/5,1/5,0};
		
		int debut=(numeroJoueur==1?0:6);
		int fin=(numeroJoueur==1?6:12);
		
		for(int i=debut;i<fin;i++) {
			nombreGrainesJoueur++;
		}
		
		if(nombreGrainesJoueur!=0) {
			int poidsActuel=0;
			for(int i=debut;i<fin;i++) {
				
				valeurH8+=poids[poidsActuel]*((double)(plateau[i])/(double)(nombreGrainesJoueur));
				poidsActuel++;
			}
		}
		
		return valeurH8;
		
	}
	
	/* Heuristique 9:
	 * L'objectif de cet heuristique est de maximiser
	 * le score du joueur
	 */
	private double H9(int scoreJoueur,int scoreAdversaire) {
		return ((double)scoreJoueur*((double)1/48));
	}
	
	public void setHeuristique(String string) {
		if(string.length()==this.heuristique.length) {
			for(int i=0;i<this.heuristique.length;i++) {
				if(string.charAt(i)=='1') {
					this.heuristique[i]=true;
				}else {
					this.heuristique[i]=false;
				}
			}
		}
	}
	
	public void setProfondeurMax(int p) {
		this.profondeurMax=p;
	}
	
	public void printHeuristique() {
		for(int i=0;i<this.heuristique.length;i++) {
			System.out.print((this.heuristique[i]?"1":"0"));
		}
	}
	
	public void printPoids() {
		for(int i=0;i<this.heuristique.length;i++) {
			if(this.heuristique[i]) {
				System.out.print(this.poids[i]+":");
			}
			else {
				System.out.print("--:");
			}
		}
	}
	
	public String getMasque() {
		String retour="/";
		for(int i=0;i<this.heuristique.length;i++) {
			retour+=(heuristique[i]?"1":"0");
		}
		return retour;
	}
	
	public String getPoids() {
		NumberFormat formatter = NumberFormat.getInstance(Locale.US);
		String retour="{";
		for(int i=0;i<this.poids.length;i++) {
			retour+=(heuristique[i]?String.format(Locale.US,"%s",this.poids[i]):"--")+";";
		}
		retour+="}";
		return retour;
	}
	
	public void demanderHeuristique() {
		if(this.difficulte!=0) {
			Scanner sc=new Scanner(System.in);
			String saisie;
			do {
				System.out.println("Heuristiques à activer pour <"+this.getNom()+"> (exemple: 111101100)\n >>");
				saisie=sc.nextLine();
			}while(saisie.length()!=this.heuristique.length);
			setHeuristique(saisie);
			System.out.print("Voici les nouvelles heuristiques : ");
			printHeuristique();
			System.out.println();
		}
	}
	
	public void demanderProfondeur() {
		if(this.difficulte==2 || this.difficulte==1) {
			Scanner sc=new Scanner(System.in);
			int saisie;
			do {
				System.out.print("Entrez une profondeur max <"+this.getNom()+">>");
				saisie=sc.nextInt();
			}while(saisie<1);
			setProfondeurMax(saisie);
		}
	}
	
	public void demanderPoids() {
		if(this.difficulte==2 || this.difficulte==1) {
			Scanner sc=new Scanner(System.in);
			double saisie;
			for(int i=0;i<this.poids.length;i++) {
				if(this.heuristique[i]) {
					do {
						System.out.print("Entrez un poid [0;1] pour h"+(i+1)+" <"+this.getNom()+">>");
						saisie=sc.nextDouble();
					}while(saisie>1 || saisie<0);
					this.poids[i]=saisie;
				}
			}
			System.out.print("Voici les nouveaux poids : ");
			printPoids();
		}
	}
	
	/* evaluation d'un etat du jeu en fonction
	 * de la ponderation de chaque heuristique
	 */
	public double evaluation(int numeroJoueur,int[] plateau,int scoreJoueur,int scoreAdversaire) {
		
		double[] heuristiques= {
				(this.heuristique[0]?H1(numeroJoueur,plateau):0),
				(this.heuristique[1]?H2(numeroJoueur,plateau):0),
				(this.heuristique[2]?H3(numeroJoueur,plateau):0),
				(this.heuristique[3]?H4(numeroJoueur,plateau):0),
				(this.heuristique[4]?H5(scoreJoueur,scoreAdversaire):0),
				(this.heuristique[5]?H6(numeroJoueur,plateau):0),
				(this.heuristique[6]?H7(numeroJoueur,plateau):0),
				(this.heuristique[7]?H8(numeroJoueur,plateau):0),
				(this.heuristique[8]?H9(scoreJoueur,scoreAdversaire):0)
		};
		
		double valeurEvaluation=0;
		for(int i=0;i<this.heuristique.length;i++) {
			valeurEvaluation+=heuristiques[i]*this.poids[i];
		}
		
		return valeurEvaluation;
	}
    
	public double noeudTerminal(int retourSimulerFinPartie, GameManagerAwale arbitreAwale)
	{
		double valeur;
		int score, scoreJoueur, scoreAdversaire;
		
		if(arbitreAwale.joueurActuel().getNumeroJoueur() == 1)
		{
			scoreJoueur =  arbitreAwale.getJoueur1().getScore();
			scoreAdversaire =  arbitreAwale.getJoueur2().getScore();
		}
		else
		{
			scoreJoueur =  arbitreAwale.getJoueur2().getScore();
			scoreAdversaire =  arbitreAwale.getJoueur1().getScore();
		}
		
		if(retourSimulerFinPartie == 0)
		{
			score = 0; //Neutre si ex aequo
		}
		else
		{
			score = differenceScore(scoreJoueur, scoreAdversaire); //Positif si le joueur actuel est gagnant, negatif sinon
		}
    	
		valeur = score * 1000;
    	
		return valeur;
	}
	
	double profondeurMaxAtteinte(GameManagerAwale arbitreSimule)
	{
		int scoreJoueur, scoreAdversaire, numeroJoueur;
		double valeur = -1;
		
		if(arbitreSimule.joueurActuel() == arbitreSimule.getJoueur1())
	    {
			numeroJoueur = 1;
			scoreJoueur = arbitreSimule.getJoueur1().getScore();
			scoreAdversaire = arbitreSimule.getJoueur2().getScore();
	    }
	    else
	    {
			numeroJoueur = 2;
			scoreJoueur = arbitreSimule.getJoueur1().getScore();
			scoreAdversaire = arbitreSimule.getJoueur2().getScore();
	    }
		
		valeur = evaluation(numeroJoueur, arbitreSimule.getPartie().getPlateau(), scoreJoueur, scoreAdversaire);
		
		return valeur;
	}
	
	public double minimax(int caseJouee, GameManagerAwale arbitreAwaleSimule, int profondeurMax, boolean joueurMax)
	{
		long time = System.currentTimeMillis();
		ArrayList coupPossible = new ArrayList<>();
		ArrayList <int[]> historique = new ArrayList<>();
		double valeur = -1;
		int retourSimulerFinPartie, score, scoreJoueur, scoreAdversaire, numeroJoueur;
		
		//On simule un nouveau un GMA, c'est obligatoire, autrement c'est toujours l'arbitreAwaleSimule (le paramètre que l'on donne) qui est modifié et donc la simulation n'est pas correcte
		GameManagerAwale arbitreSimuleMinimax = arbitreAwaleSimule.clone();
		
		//Compte le nombre d'appels recursifs
		setCompteur(getCompteur() + 1);
		
		//On modifie le plateau
		arbitreSimuleMinimax.getPartie().modifierPlateau(this.simulerUnCoup(caseJouee, arbitreAwaleSimule));
		
		//On rempli l'historique
		arbitreSimuleMinimax.stockerEtatMouvement(arbitreSimuleMinimax.getPartie().getPlateau());
		
		//On determine la liste des coups possible a partir de la caseJouee donnee en parametre
		coupPossible = arbitreSimuleMinimax.determinerCoupPossible(arbitreSimuleMinimax.joueurActuel(), arbitreSimuleMinimax.getPartie().getPlateau());
		
		retourSimulerFinPartie = simulerFinPartie(historique, arbitreSimuleMinimax);
	    
		if(retourSimulerFinPartie != -1)
		{
			valeur = noeudTerminal(retourSimulerFinPartie, arbitreSimuleMinimax);
		}
        
		/*On verifie que la liste des coupPossible est vide
		Si oui, on met une valeur positive quelconque dans la variable valeur et on la renvoie
		Permet de jouer l'unique coup possible dans cette situation (sinon, la valeur renvoyee est -1 et du coup la variable coupOptimise de Jouer Minimax vaut -1 aussi => erreur)*/
		if(coupPossible.isEmpty())
		{
			valeur = 1000;
			return valeur;
		}
        
		if(profondeurMax == 0)
		{
		    valeur = profondeurMaxAtteinte(arbitreSimuleMinimax);

		    return valeur;
		}
          
		//Si le joueur est l'IA
		if(joueurMax)
		{
		    valeur = -10000;
		    for(int i = 0; i < coupPossible.size() ; i++)
		    {
				valeur = Math.max(valeur, minimax((int)coupPossible.get(i), arbitreSimuleMinimax, profondeurMax-1, false));
		    }
		}
		else
		{
		    valeur = 10000;
		    for(int i = 0; i < coupPossible.size(); i++)
		    {
				valeur = Math.min(valeur, minimax((int)coupPossible.get(i), arbitreSimuleMinimax, profondeurMax-1, true));
		    }
		}
	
		time = System.currentTimeMillis() - time;
		setTime(getTime() + time);

		return valeur;
	}
  
	public int jouerMinimax(GameManagerAwale arbitreAwale, int profondeurMax)
	{
		long time = System.currentTimeMillis();
		double valeur_optimisee = -10000;
		double valeur;
		int nombre_appel = 0;
		
		//On simule un GMA pour ne pas modifier le GMA actuel du jeu
		GameManagerAwale arbitreAwaleSimule;
		
		nombre_appel++;

		ArrayList coupPossible = new ArrayList<>();
		coupPossible = arbitreAwale.determinerCoupPossible(arbitreAwale.joueurActuel(),arbitreAwale.getPartie().getPlateau());
		
		arbitreAwaleSimule = arbitreAwale.clone();
		
		int coup_optimise = -1;
  
		for(int i = 0; i < coupPossible.size(); i++) //Pour chaque coup possible a partir de l'etat courant
		{
		    valeur = minimax((int)coupPossible.get(i), arbitreAwaleSimule, profondeurMax, true); 
		    if(valeur > valeur_optimisee)
		    {
				valeur_optimisee = valeur;
				coup_optimise = (int)coupPossible.get(i);
		    }
		}
	    
		if(arbitreAwale.getVocal()) 
		{
			System.out.println();
			
			//Une fois tous les appels recursifs pour le choix d'une case effectues, on affiche le nombre d'appels recursif de minimax
			System.out.println("Nombre d'appels recursif de minimax : " + getCompteur());
	
			//Une fois tous les appels recursifs pour le choix d'une case effectues, on affiche le temps d'execution de minimax puis on remet le compteur à 0
			System.out.println("Temps d'execution de minimax : " + getTime() + "ms.");
			setTime(0);
	
			System.out.println("Nombre d'appels recursifs de jouerMinimax : " + nombre_appel);
			System.out.println("Nombre d'appels recursifs total : " + (nombre_appel + getCompteur()));
	
			time = System.currentTimeMillis() - time;
			System.out.println("Temps d'execution de jouerMinimax : " + time + "ms.");
			
			System.out.println();
			
			setCompteur(0);
		}

		return coup_optimise;
	}
	
	public double alphaBeta(int caseJouee, GameManagerAwale arbitreAwaleSimule, int profondeurMax, boolean joueurMax, double alpha, double beta)
	{
		long time = System.currentTimeMillis();
		ArrayList coupPossible = new ArrayList<>();
		ArrayList <int[]> historique = new ArrayList<>();
		double alphaBeta = -1;
		int retourSimulerFinPartie, score;
		
		//On simule un nouveau un GMA, c'est obligatoire, autrement c'est toujours l'arbitreAwaleSimule (le parametre que l'on donne) qui est modifie et donc la simulation n'est pas correcte
		GameManagerAwale arbitreSimuleMinimax = arbitreAwaleSimule.clone();
		
		//Compte le nombre d'appels recursifs
		setCompteur(getCompteur() + 1);
		
		//On modifie le plateau
		arbitreSimuleMinimax.getPartie().modifierPlateau(this.simulerUnCoup(caseJouee, arbitreAwaleSimule));
		
		//On rempli l'historique
		arbitreSimuleMinimax.stockerEtatMouvement(arbitreSimuleMinimax.getPartie().getPlateau());
		
		//On determine la liste des coups possible a partir de la caseJouee donnee en parametre
		coupPossible = arbitreSimuleMinimax.determinerCoupPossible(arbitreSimuleMinimax.joueurActuel(), arbitreSimuleMinimax.getPartie().getPlateau());
		
		retourSimulerFinPartie = simulerFinPartie(historique, arbitreSimuleMinimax);
	    
		if(retourSimulerFinPartie != -1)
		{
			alphaBeta = noeudTerminal(retourSimulerFinPartie, arbitreSimuleMinimax);
		}
        
		/*On verifie que la liste des coupPossible est vide
		Si oui, on met une valeur positive quelconque dans la variable valeur et on la renvoie
		Permet de jouer l'unique coup possible dans cette situation (sinon, la valeur renvoyee est -1 et du coup la variable coupOptimise de Jouer Minimax vaut -1 aussi => erreur)*/
		if(coupPossible.isEmpty())
		{
			alphaBeta = 1000;
			return alphaBeta;
		}
        
		if(profondeurMax == 0)
		{
			alphaBeta = profondeurMaxAtteinte(arbitreSimuleMinimax);

		    return alphaBeta;
		}
          
		//Si le joueur est l'IA
		if(joueurMax)
		{
		    for(int i = 0; i < coupPossible.size() ; i++)
		    {
				alpha = Math.max(alpha, alphaBeta((int)coupPossible.get(i), arbitreSimuleMinimax, profondeurMax-1, false, alpha, beta));
				if(alpha >= beta)
				{
					return beta;
				}
		    }
		}
		else
		{
		    for(int i = 0; i < coupPossible.size(); i++)
		    {
				beta = Math.min(beta, alphaBeta((int)coupPossible.get(i), arbitreSimuleMinimax, profondeurMax-1, true, alpha, beta));
				if(alpha >= beta)
				{
					return alpha;
				}
		    }
		}
	
		time = System.currentTimeMillis() - time;
		setTime(getTime() + time);

		return beta;
	}
	
	public int jouerAlphaBeta(GameManagerAwale arbitreAwale, int profondeurMax)
	{
		long time = System.currentTimeMillis();
		double valeur_optimisee = -10000;
		double valeur;
		int nombre_appel = 0;
		
		//On simule un GMA pour ne pas modifier le GMA actuel du jeu
		GameManagerAwale arbitreAwaleSimule;
		
		nombre_appel++;

		ArrayList coupPossible = new ArrayList<>();
		coupPossible = arbitreAwale.determinerCoupPossible(arbitreAwale.joueurActuel(),arbitreAwale.getPartie().getPlateau());
		
		arbitreAwaleSimule = arbitreAwale.clone();
		
		int coup_optimise = -1;
  
		for(int i = 0; i < coupPossible.size(); i++) //Pour chaque coup possible a partir de l'etat courant
		{
		    valeur = alphaBeta((int)coupPossible.get(i), arbitreAwaleSimule, profondeurMax, true, -10000, 10000); 
		    if(valeur > valeur_optimisee)
		    {
				valeur_optimisee = valeur;
				coup_optimise = (int)coupPossible.get(i);
		    }
		}
	    
		if(arbitreAwale.getVocal()) 
		{
			System.out.println();
			
			//Une fois tous les appels recursifs pour le choix d'une case effectues, on affiche le nombre d'appels recursif d'alphaBeta
			System.out.println("Nombre d'appels recursif d'alphaBeta : " + getCompteur());
	
			//Une fois tous les appels recursifs pour le choix d'une case effectues, on affiche le temps d'execution d'alphaBeta puis on remet le compteur à 0
			System.out.println("Temps d'execution d'alphaBeta : " + getTime() + "ms.");
			setTime(0);
	
			System.out.println("Nombre d'appels recursifs de jouerAlphaBeta : " + nombre_appel);
			System.out.println("Nombre d'appels recursifs total : " + (nombre_appel + getCompteur()));
	
			time = System.currentTimeMillis() - time;
			System.out.println("Temps d'execution de jouerAlphaBeta : " + time + "ms.");
			
			System.out.println();
			
			setCompteur(0);
		}

		return coup_optimise;
	}
     
	public int choisirUnCoup(GameManagerAwale arbitreAwale)
	{
		int caseJouee = -1;
		
		if(difficulte==2) 
		{
			do {
				caseJouee = jouerAlphaBeta(arbitreAwale,this.profondeurMax==-1?4:this.profondeurMax);
			}while( !arbitreAwale.verifierCoupValide(arbitreAwale.joueurActuel(), caseJouee, arbitreAwale.getPartie().getPlateau()) );
			if(arbitreAwale.getVocal()) {
				System.out.println("case jouee : " + caseJouee);
			}
		}
		else if(difficulte==1) 
		{
			do {
				caseJouee = jouerMinimax(arbitreAwale,this.profondeurMax==-1?4:this.profondeurMax);
			}while( !arbitreAwale.verifierCoupValide(arbitreAwale.joueurActuel(), caseJouee, arbitreAwale.getPartie().getPlateau()) );
			if(arbitreAwale.getVocal()) {
				System.out.println("case jouee : " + caseJouee);
			}
		}
		else if(difficulte==0)
		{
			Random rand = new Random();
			do {
				caseJouee = rand.nextInt(6)+this.getMin();
		    }while( !arbitreAwale.verifierCoupValide(this,caseJouee,arbitreAwale.getPartie().getPlateau()) );
		}
		return caseJouee;
	}
}
