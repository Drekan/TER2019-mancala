package mancala;

import java.util.ArrayList;

//les methodes de decision seront utiliser(MiniMax, Evalution...)
public class JoueurAwaleIA extends JoueurAwale{

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
	 * de la pondération de chaque heuristique
	 */
	public double evaluation(int numeroJoueur,int[] plateau,int scoreJoueur,int scoreAdversaire) {
		double[] poids= {1/2,1/2,1/2,1/2,1/2};
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

	public double maximum(double premiereValeur, double secondeValeur){
        double resultat;
          
        if(premiereValeur > secondeValeur){
            resultat = premiereValeur;
        }
        else{
            resultat = secondeValeur;
        }
          
        return resultat;
    }
      
    public double minimum(double premiereValeur, double secondeValeur){
        double resultat;
          
        if(premiereValeur <= secondeValeur){
            resultat = premiereValeur;
        }
        else{
            resultat = secondeValeur;
        }
          
        return resultat;
    }
      
    public double minimax(int caseJouee, GameManagerAwale arbitreAwale, int profondeurMax, boolean joueurMax){
        ArrayList coupPossible = new ArrayList<>();
        int plateauSimule[] = new int[12];
        plateauSimule = this.simulerUnCoup(caseJouee, arbitreAwale);
         
        coupPossible = arbitreAwale.determinerCoupPossible(arbitreAwale.gestionTour(),plateauSimule);
        double valeur = -1;
         
        //Gerer le cas où le noeud est terminal
        if(profondeurMax == 0){//&& le noeud n'est pas terminal
            if(arbitreAwale.gestionTour() == arbitreAwale.getJoueur1()){
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
                  
                valeur = maximum(valeur, minimax((int)coupPossible.get(i), arbitreAwale, profondeurMax-1, false));
            }
        }
        else{
            valeur = 10000;
            for(int i = 0; i < coupPossible.size(); i++){
                valeur = minimum(valeur, minimax((int)coupPossible.get(i), arbitreAwale, profondeurMax-1, true));
            }
        }
          
        return valeur;
    }
  
    public int jouerMinimax(GameManagerAwale arbitreAwale, int profondeurMax){
        double valeur_optimisee = -10000;
        double valeur;
         
        ArrayList coupPossible = new ArrayList<>();
        coupPossible = arbitreAwale.determinerCoupPossible(arbitreAwale.gestionTour(),arbitreAwale.getPartie().getPlateau());
         
        int coup_optimise = -1;
  
        for(int i = 0; i < coupPossible.size(); i++) {//Pour chaque coup possible a partir de l'etat courant
            valeur = minimax((int)coupPossible.get(i), arbitreAwale, profondeurMax, true);
            if(valeur > valeur_optimisee){
                valeur_optimisee = valeur;
                coup_optimise = (int)coupPossible.get(i);
            }
        }
  
        return coup_optimise;
    }
     
    public void choisirUnCoup(GameManagerAwale arbitreAwale) {
        int caseJouee = jouerMinimax(arbitreAwale,4);
        System.out.println("case jouée : " + caseJouee);
         
        jouerUnCoup(caseJouee,arbitreAwale);        
    }
}
