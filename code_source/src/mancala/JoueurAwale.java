package mancala;

//peut jouer un coup en fonction du plateau.
//le comportement change q'il s'agit d'un humain ou d'une IA
public abstract class JoueurAwale extends Joueur{
	
	GameManagerAwale gameManagerAwale;

	public JoueurAwale(String nomJoueur, int score, int numeroJoueur, int min, int max) {
		super(nomJoueur, score, numeroJoueur,min,max);
	}

	@Override
	public void jouerUnCoup(int caseJouee, GameManagerAwale gameManagerAwale) {//mise a jour des valeurs du plateau
		int CaseActuelle = caseJouee;
		int nbrGrainesADeplacer = gameManagerAwale.getPartie().getPlateau()[caseJouee];
		gameManagerAwale.getPartie().setPlateau( 0, caseJouee );
		
		while(nbrGrainesADeplacer>0) {
			System.out.println("nbr graine a deplacer : " + nbrGrainesADeplacer );
			System.out.println(" gameManagerAwale.Partie.getPlateau()["+ CaseActuelle +"] = " + gameManagerAwale.getPartie().getPlateau()[CaseActuelle] );
			
			if(caseJouee==0 && CaseActuelle==11) {
				CaseActuelle = 1;
			}
			else if(CaseActuelle == caseJouee-1)
			{
				if(CaseActuelle == 10) {
					CaseActuelle = 0;
				}
				else {
					CaseActuelle += 2;
				}
			}
			else if (CaseActuelle == 11) {
				CaseActuelle = 0;
			}
			else {
				CaseActuelle++;
			}
			
			gameManagerAwale.getPartie().setPlateau( gameManagerAwale.getPartie().getPlateau()[CaseActuelle] + 1 , CaseActuelle);
			
			nbrGrainesADeplacer--;			
		}
		//enlever les graines = 2 ou =3
		// diminuer le nbr de graines du plateau
		prendreGraines(CaseActuelle, gameManagerAwale);
		
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

	@Override
	public abstract int getJeu();//a redefinir dans classe fille
		

}
