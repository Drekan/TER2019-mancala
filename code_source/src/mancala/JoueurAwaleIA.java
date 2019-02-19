package mancala;

//les methodes de decision seront utiliser(MiniMax, Evalution...)
public class JoueurAwaleIA extends JoueurAwale{

	//constructeurs :
	public JoueurAwaleIA(String nomJoueur, int score, int numeroJoueur,int min,int max) {
		super(nomJoueur, score, numeroJoueur,min,max);
	}
	
	//methods:
	@Override
	public int getJeu() {//faire appel a algo MiniMax;
		int caseJouer = 0 ;
		return caseJouer;
		
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
		
		return (double)(nombreGraineJoueur)/(double)(nombreGraine);
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
		
		int poidsActuel=0;
		for(int i=debut;i<fin;i++) {
			valeurH4+=poids[poidsActuel]*((double)(plateau[i])/(double)(nombreGrainesJoueur));
			poidsActuel++;
		}
		
		return valeurH4;
		
	}
	
	
	/* Heuristique 5:
	 * L'objectif de cet heuristique est de minimiser
	 * le score de l'adversaire
	 */
	private double H3(int scoreJoueur,int scoreAdversaire) {
		return (double)(scoreJoueur)/(double)(scoreJoueur+scoreAdversaire);
	}
}
