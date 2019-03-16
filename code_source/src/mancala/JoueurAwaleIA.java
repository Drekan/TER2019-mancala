package mancala;

import java.util.ArrayList;
import java.util.Random;

//les methodes de decision seront utilisees(MiniMax, Evalution...)
public class JoueurAwaleIA extends JoueurAwale{
	//Pour calculer le nombre d'appels récursifs de minimax
	private int compteur = 0;
	
	//Pour calculer le temps d'execution de minimax
	private long time = 0;
	
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
	
	//constructeurs :
	public JoueurAwaleIA(String nomJoueur, int score, int numeroJoueur,int min,int max, int nbrGrainesEnJeu) {
		super(nomJoueur, score, numeroJoueur, min, max, nbrGrainesEnJeu);
	}
	
	//methods:
	public int[] simulerUnCoup(int caseJouee, GameManagerAwale gameManagerAwale)
	{
		int plateauSimule[] = new int[12];
		
		for(int i = 0; i < 12; i++)
		{
			plateauSimule[i] = gameManagerAwale.getPartie().getPlateau()[i];
		}
		miseAJourPlateauSimuler(plateauSimule, caseJouee);
		
		return plateauSimule;
	}
	
	//Permet de savoir qui gagne
	public int vainqueur(int scoreJoueur1, int scoreJoueur2)
	{
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
	
	//Supprimer la redondance de code a l'occasion si possible
	public int nbRedondanceHistorique(int profondeur, ArrayList<int []> historique, GameManagerAwale arbitreAwale) 
	{
		int redondance = 0;
		int profondeurEffective = Math.min(historique.size()-1, profondeur);
		for(int i = 0; i < profondeurEffective; i++) 
		{
			if(arbitreAwale.plateauxEgaux(historique.get(0), historique.get(i))) 
			{
				redondance++;
			}
		}
		
		return redondance;
	}
	
	public int simulerFinPartie(Awale partieSimulee, ArrayList<int []> historique, GameManagerAwale arbitreAwale) 
	{
		int joueurGagnant = -1;
		
		if( partieSimulee.getNbrGrainesEnJeu() <= 1 ) 
		{
			joueurGagnant = vainqueur(arbitreAwale.getJoueur1().getScore(), arbitreAwale.getJoueur2().getScore());
		}

		else if(nbRedondanceHistorique(36, historique, arbitreAwale) >= 3) 
		{
			joueurGagnant = vainqueur(arbitreAwale.getJoueur1().getScore(), arbitreAwale.getJoueur2().getScore());
		}
		
		else if(arbitreAwale.joueurActuel().getNbrGrainesEnJeu() == 0 ) 
		{
			joueurGagnant = vainqueur(arbitreAwale.getJoueur1().getScore(), arbitreAwale.getJoueur2().getScore());
		}
		
		boolean affamerPartout = true;
		
		for(int i = arbitreAwale.joueurActuel().getMin(); i <= arbitreAwale.joueurActuel().getMax(); i++) 
		{
			if(arbitreAwale.InterdictionAffamer(i, partieSimulee.getPlateau())) 
			{
				affamerPartout = false;
			}
		}
		
		if(affamerPartout)
		{
			joueurGagnant = vainqueur(arbitreAwale.getJoueur1().getScore(), arbitreAwale.getJoueur2().getScore());
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
		return ((scoreJoueur+scoreAdversaire!=0)?
					(double)(scoreJoueur)/(double)(scoreJoueur+scoreAdversaire)
					:0);
	}
	
	
	/* evaluation d'un etat du jeu en fonction
	 * de la ponderation de chaque heuristique
	 */
	public double evaluation(int numeroJoueur,int[] plateau,int scoreJoueur,int scoreAdversaire) {
		double[] poids= {0.3,0.19,0.37,0.41,0.56};
		double[] heuristiques= {
				H1(numeroJoueur,plateau),
				H2(numeroJoueur,plateau),
				H3(numeroJoueur,plateau),
				H4(numeroJoueur,plateau),
				H5(scoreJoueur,scoreAdversaire)			
		};
		
		double valeurEvaluation=0;
		for(int i=0;i<5;i++) {
			valeurEvaluation+=heuristiques[i]*poids[i];
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
	
	public double minimax(int caseJouee, GameManagerAwale arbitreAwale, int profondeurMax, boolean joueurMax)
    	{
		long time = System.currentTimeMillis();
		ArrayList coupPossible = new ArrayList<>();
		ArrayList <int[]> historique = new ArrayList<>();
		double valeur = -1;
		int retourSimulerFinPartie, score, scoreJoueur, scoreAdversaire, numeroJoueur;
		int difficulte = arbitreAwale.getPartie().getDifficulteChoisie();
		
		//Compte le nombre d'appels recursifs
		setCompteur(getCompteur() + 1);

		Awale partieSimulee = new Awale("MonAwale", "MesRegles", difficulte);
		//On met a jour le nombre de graines en jeu
		partieSimulee.setNbrGrainesEnJeu(arbitreAwale.getPartie().getNbrGrainesEnJeu());
		//On simule un plateau
		partieSimulee.modifierPlateau(this.simulerUnCoup(caseJouee, arbitreAwale));
		historique.add(partieSimulee.getPlateau());

		coupPossible = arbitreAwale.determinerCoupPossible(arbitreAwale.joueurActuel(), partieSimulee.getPlateau());
		//System.out.println("Minimax !! coupPossible = " + coupPossible);

		retourSimulerFinPartie = simulerFinPartie(partieSimulee, historique, arbitreAwale);
	    
        	if(retourSimulerFinPartie != -1)
        	{        	
        		valeur = noeudTerminal(retourSimulerFinPartie, arbitreAwale);        	
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
		    if(arbitreAwale.joueurActuel() == arbitreAwale.getJoueur1())
		    {
			numeroJoueur = 1;
			scoreJoueur = arbitreAwale.getJoueur1().getScore();
			scoreAdversaire = arbitreAwale.getJoueur2().getScore();
		    }
		    else
		    {
			numeroJoueur = 2;
			scoreJoueur = arbitreAwale.getJoueur1().getScore();
			scoreAdversaire = arbitreAwale.getJoueur2().getScore();
		    }

		    valeur = evaluation(numeroJoueur, partieSimulee.getPlateau(), scoreJoueur, scoreAdversaire);

		    return valeur;
		}
          
		//Si le joueur est l'IA
		if(joueurMax)
		{
		    valeur = -10000;
		    for(int i = 0; i < coupPossible.size() ; i++)
		    {
			valeur = Math.max(valeur, minimax((int)coupPossible.get(i), arbitreAwale, profondeurMax-1, false));
		    }
		}
		else
		{
		    valeur = 10000;
		    for(int i = 0; i < coupPossible.size(); i++)
		    {
			valeur = Math.min(valeur, minimax((int)coupPossible.get(i), arbitreAwale, profondeurMax-1, true));
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

		ArrayList coupPossible = new ArrayList<>();
		coupPossible = arbitreAwale.determinerCoupPossible(arbitreAwale.joueurActuel(),arbitreAwale.getPartie().getPlateau());

		int coup_optimise = -1;
  
		for(int i = 0; i < coupPossible.size(); i++) //Pour chaque coup possible a partir de l'etat courant
		{
		    valeur = minimax((int)coupPossible.get(i), arbitreAwale, profondeurMax, true); 
		    nombre_appel++;
		    if(valeur > valeur_optimisee)
		    {
			valeur_optimisee = valeur;
			coup_optimise = (int)coupPossible.get(i);

		    }
        	}
	    
		//Une fois tous les appels recursifs pour le choix d'une case effectues, on affiche le nombre d'appels recursif de minimax
		System.out.println("Nombre d'appels recursif de minimax : " + getCompteur());

		//Une fois tous les appels recursifs pour le choix d'une case effectues, on affiche le temps d'execution de minimax puis on remet le compteur à 0
		System.out.println("Temps d'execution de minimax : " + getTime() + "ms.");
		setTime(0);

		System.out.println("Nombre d'appels recursifs de jouerMinimax : " + nombre_appel);
		System.out.println("Nombre d'appels recursifs total : " + (nombre_appel + getCompteur()));

		time = System.currentTimeMillis() - time;
		System.out.println("Temps d'execution de jouerMinimax : " + time + "ms.");
		
		setCompteur(0);

		return coup_optimise;
    	}
     
    	public int choisirUnCoup(GameManagerAwale arbitreAwale) 
    	{
		int caseJouee = -1;
		int ia=arbitreAwale.getPartie().getDifficulteChoisie();
		if(ia == 1) 
		{
			do {
				caseJouee = jouerMinimax(arbitreAwale,4);
			}while( !arbitreAwale.verifierCoupValide(arbitreAwale.joueurActuel(), caseJouee, arbitreAwale.getPartie().getPlateau()) );

			System.out.println("case jouee : " + caseJouee);
		}
		else if(ia == 0) {
			Random rand = new Random();
			do {
				caseJouee = rand.nextInt(6)+this.getMin();
		    }while( !arbitreAwale.verifierCoupValide(this,caseJouee,arbitreAwale.getPartie().getPlateau()) );
		}
       		return caseJouee;
    	}
}
