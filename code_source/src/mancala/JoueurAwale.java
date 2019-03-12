package mancala;

//peut jouer un coup en fonction du plateau.
//le comportement change s'il s'agit d'un humain ou d'une IA
public abstract class JoueurAwale extends Joueur{
	
	GameManagerAwale gameManagerAwale;

	public JoueurAwale(String nomJoueur, int score, int numeroJoueur, int min, int max) {
		super(nomJoueur, score, numeroJoueur,min,max);
	}
	
	public int miseAJourPlateau(int[] plateau,int caseInitiale) {
		int grainesRestantes=plateau[caseInitiale];
		plateau[caseInitiale]=0;
		
		int caseActuelle=caseInitiale;
		while(grainesRestantes>0) {
			//System.out.println("nbr graine a deplacer : " + grainesRestantes );
			//System.out.println(" gameManagerAwale.Partie.getPlateau()["+ caseActuelle +"] = " + plateau[caseActuelle] );
			if(((caseActuelle+1)%12) == caseInitiale){
				caseActuelle=(caseActuelle+2)%12;
			}
			else {
				caseActuelle=(caseActuelle+1)%12;
			}
			
			plateau[caseActuelle]++;
			grainesRestantes--;
		}
		return caseActuelle;
	}
	
	@Override
	public void jouerUnCoup(int caseJouee, GameManagerAwale gameManagerAwale) {//mise a jour des valeurs du plateau
		int derniereCaseJouee=miseAJourPlateau(gameManagerAwale.getPartie().getPlateau(),caseJouee);
		//enlever les graines = 2 ou =3
		// diminuer le nbr de graines du plateau
		prendreGraines(derniereCaseJouee, gameManagerAwale);
		
	}
	
	public void prendreGraines(int CaseActuelle, GameManagerAwale gameManagerAwale) {
		//enlever les graines = 2 ou =3 et augmenter le score du joueur
		int min,max;
		if( getNumeroJoueur() == 2 ) {
			min= 0;
			max= 5;
		}
		else {
			min = 6;
			max= 11; 
		}
		while( CaseActuelle <= max && CaseActuelle >= min && ( gameManagerAwale.getPartie().getPlateau()[CaseActuelle] == 2 || gameManagerAwale.getPartie().getPlateau()[CaseActuelle] == 3 ) ) {
			
			setScore(getScore() + gameManagerAwale.getPartie().getPlateau()[CaseActuelle]);
			System.out.println("score joueur " + getNumeroJoueur() +": " + getScore());
			
			gameManagerAwale.getPartie().setNbrGrainesEnJeu(gameManagerAwale.getPartie().getNbrGrainesEnJeu() - gameManagerAwale.getPartie().getPlateau()[CaseActuelle] );
			System.out.println("nbr graines en jeu : " + gameManagerAwale.getPartie().getNbrGrainesEnJeu());
			
			gameManagerAwale.getPartie().setPlateau(0, CaseActuelle );
			System.out.println("gameManagerAwale.Partie.getPlateau()[" + CaseActuelle + "] : " + gameManagerAwale.getPartie().getPlateau()[CaseActuelle]);
			
			CaseActuelle--;
		}
		
	}

	
}
