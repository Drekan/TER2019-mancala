package mancala;

//les methodes de decision seront utiliser(MiniMax, Evalution...)
public class JoueurAwaleIA extends JoueurAwale{

	//constructeurs :
	public JoueurAwaleIA(String nomJoueur, int score, int numeroJoueur) {
		super(nomJoueur, score, numeroJoueur);
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
		
		int debut=(numeroJoueur==0?0:6);
		int fin=(numeroJoueur==1?6:12);
		
		for(int i=debut;i<fin;i++) {
			if(plateau[i]==1 || plateau[i]==2) {
				nbCasesVulnerables++;
			}
		}
		
		return 1-((double)(nbCasesVulnerables)*1/6);
		
	}
	
	private double H2(int numeroJoueur,int[] plateau) {
		double nombreGraine=0;
		double nombreGraineJoueur=0;
		//double nombreGraineAdversaire=0;
		int debut=(numeroJoueur==0?0:6);
		int fin=(numeroJoueur==1?6:12);
		
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
}
