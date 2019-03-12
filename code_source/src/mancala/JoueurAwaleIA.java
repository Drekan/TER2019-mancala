package mancala;

import java.util.ArrayList;
import java.util.Random;

//les methodes de decision seront utilisees(MiniMax, Evalution...)
public class JoueurAwaleIA extends JoueurAwale{
	//Pour calculer le nombre d'appels récursifs de minimax
	private int compteur = 0;
	
	//Pour calculer le temps d'exécution de minimax
	private long time = 0;
	
	public int getCompteur() {
		return compteur;
	}

	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}
	
	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	
	//constructeurs :
	public JoueurAwaleIA(String nomJoueur, int score, int numeroJoueur,int min,int max) {
		super(nomJoueur, score, numeroJoueur,min,max);
	}
	
	//methods:
	public int[] simulerUnCoup(int caseJouee, GameManagerAwale gameManagerAwale)
	{
		int plateauSimule[] = new int[12];
		
		for(int i = 0; i < 12; i++)
		{
			plateauSimule[i] = gameManagerAwale.getPartie().getPlateau()[i];
		}
		miseAJourPlateau(plateauSimule,caseJouee);
		
		return plateauSimule;
	}
	
	/* Heuristique 1:
	 * L'objectif de cet heuristique est de minimiser
	 * le nombre de cases vulnérables
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
		//double nombreGraineAdversaire=0;
		int debut=(numeroJoueur==1?0:6);
		int fin=(numeroJoueur==2?6:12);
		
		for(int i=0;i<12;i++) {
			nombreGraine+=plateau[i];
			
			if(i>=debut && i<fin) {
				nombreGraineJoueur+=plateau[i];
			}
			/*else {
				nombreGraineAdversaire+=plateau[i];
			}*/
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
	 * les états du jeu où les graines sont à droite
	 */
	private double H4(int numeroJoueur,int[] plateau) {
		int nombreGrainesJoueur=0;
		double valeurH4=0;
		//double[] poids= {1,4/5,3/5,2/5,1/5,0};
		double[] poids= {0,1/5,2/5,3/5,4/5,1};
		
		int debut=(numeroJoueur==1?0:6);
		int fin=(numeroJoueur==2?6:12);
		
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
	
	
	/* evaluation d'un état du jeu en fonction
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
      
    public double minimax(int caseJouee, GameManagerAwale arbitreAwale, int profondeurMax, boolean joueurMax){
	long time = System.currentTimeMillis();
        ArrayList coupPossible = new ArrayList<>();
        int plateauSimule[] = new int[12];
        plateauSimule = this.simulerUnCoup(caseJouee, arbitreAwale);
         
        coupPossible = arbitreAwale.determinerCoupPossible(arbitreAwale.joueurActuel(),plateauSimule);
        //System.out.println("Minimax !! coupPossible = " + coupPossible);
        
        double valeur = -1;
         
        //Gerer le cas ou le noeud est terminal
        
        //On verifie que la liste des coupPossible est vide
        //Si oui, on met une valeur positive quelconque dans la variable valeur et on la renvoie
        //Permet de jouer l'unique coup possible dans cette situation (sinon, la valeur renvoyee est -1 et du coup la variable coupOptimise de Jouer Minimax vaut -1 aussi => erreur
        if(coupPossible.isEmpty())
        {
        	//System.out.println("plateauSimule = null");
        	valeur = 1000;
        	return valeur;
        }
        
        if(profondeurMax == 0){//&& le noeud n'est pas terminal
            if(arbitreAwale.joueurActuel() == arbitreAwale.getJoueur1()){
                valeur = evaluation(1, plateauSimule, arbitreAwale.getJoueur1().getScore(), arbitreAwale.getJoueur2().getScore());
            }
            else{
                valeur = evaluation(2, plateauSimule, arbitreAwale.getJoueur2().getScore(), arbitreAwale.getJoueur1().getScore());
            }
            return valeur;
        }
          
        //Si le joueur est l'IA
        if(joueurMax){
            valeur = -10000;
            for(int i = 0; i < coupPossible.size() ; i++){
                valeur = Math.max(valeur, minimax((int)coupPossible.get(i), arbitreAwale, profondeurMax-1, false));
		setCompteur(getCompteur() + 1);
                //System.out.println(" !! valeur = " + valeur);
            }
        }
        else{
            valeur = 10000;
            for(int i = 0; i < coupPossible.size(); i++){
                valeur = Math.min(valeur, minimax((int)coupPossible.get(i), arbitreAwale, profondeurMax-1, true));
		setCompteur(getCompteur() + 1);
            }
        }
	
	time = System.currentTimeMillis() - time;
        setTime(getTime() + time);
	    
        return valeur;
    }
  
    public int jouerMinimax(GameManagerAwale arbitreAwale, int profondeurMax){
        long time = System.currentTimeMillis();
	double valeur_optimisee = -10000;
        double valeur;
	int nombre_appel = 0;
         
        ArrayList coupPossible = new ArrayList<>();
        coupPossible = arbitreAwale.determinerCoupPossible(arbitreAwale.joueurActuel(),arbitreAwale.getPartie().getPlateau());
        //System.out.println(" JouerMinimax !! coupPossible = " + coupPossible);
        
        int coup_optimise = -1;
  
        for(int i = 0; i < coupPossible.size(); i++) {//Pour chaque coup possible a partir de l'etat courant
            valeur = minimax((int)coupPossible.get(i), arbitreAwale, profondeurMax, true); 
	    nombre_appel++;
            //System.out.println(" !! valeur = " + valeur);
            if(valeur > valeur_optimisee){
                valeur_optimisee = valeur;
                coup_optimise = (int)coupPossible.get(i);
                
            }
        }
        //System.out.println(" !! coup_optimise = " + coup_optimise);
	    
	//Une fois tous les appels recursifs pour le choix d'une case effectues, on affiche le nombre d'appels recursif de minimax puis on remet le compteur à 0 
        System.out.println("Nombre d'appels recursif de minimax : " + getCompteur());
        setCompteur(0);
        
        //Une fois tous les appels recursifs pour le choix d'une case effectues, on affiche le temps d'execution de minimax puis on remet le compteur à 0
        System.out.println("Temps d'execution de minimax : " + getTime() + "ms.");
        setTime(0);
	
	System.out.println("Nombre d'appels recursifs de jouerMinimax : " + nombre_appel);
	    
        time = System.currentTimeMillis() - time;
        System.out.println("Temps d'execution de jouerMinimax : " + time + "ms.");
	    
	return coup_optimise;
    }
     
    public int choisirUnCoup(GameManagerAwale arbitreAwale) {
        int caseJouee = -1;
        int ia=arbitreAwale.getPartie().getDifficulteChoisie();
        if(ia==1) {
	        do {
	        	caseJouee = jouerMinimax(arbitreAwale,8);
	        }while( !arbitreAwale.verifierCoupValide(arbitreAwale.joueurActuel(), caseJouee, arbitreAwale.getPartie().getPlateau()) );
	        
	        System.out.println("case jouee : " + caseJouee);
	        //jouerUnCoup(caseJouee,arbitreAwale);
        }
        else if(ia==0) {
        	Random rand = new Random();
        	System.out.println("MIN : "+this.getMin()+"----");
        	do {
        		caseJouee = rand.nextInt(6)+this.getMin();
        		//System.out.println("JE CHERCHE");
            }while( !arbitreAwale.verifierCoupValide(this,caseJouee,arbitreAwale.getPartie().getPlateau()) );
        }
        return caseJouee;
    }
}
