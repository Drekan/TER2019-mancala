package mancala;

//peut jouer un coup en fonction du plateau.
//le comportement change s'il s'agit d'un humain ou d'une IA
public abstract class JoueurAwale extends Joueur{
	
	//GameManagerAwale gameManagerAwale; /* Je crois que c'est un attribut dont on a pas besoin ici */

	public JoueurAwale(String nomJoueur, int score, int numeroJoueur, int min, int max, int nbrGrainesEnJeu) {
		super(nomJoueur, score, numeroJoueur, min, max, nbrGrainesEnJeu);
	}
	
	public int miseAJourPlateau(int[] plateau, int caseInitiale, GameManagerAwale gameManagerAwale) {//permet d'actualiser les cases du plateau
		int grainesRestantes=plateau[caseInitiale];
		gameManagerAwale.joueurActuel().setNbrGrainesEnJeu(gameManagerAwale.joueurActuel().getNbrGrainesEnJeu() - plateau[caseInitiale] );
		plateau[caseInitiale]=0;
		
		int caseActuelle=caseInitiale;
		while(grainesRestantes>0) {
			if( ( (caseActuelle+1)%12 ) == caseInitiale ){
				caseActuelle=(caseActuelle+2)%12;
			}
			else {
				caseActuelle=(caseActuelle+1)%12;
			}
			
			if( (caseActuelle%12) <6 ) {
				gameManagerAwale.getJoueur1().setNbrGrainesEnJeu( gameManagerAwale.getJoueur1().getNbrGrainesEnJeu()+1 );
			}
			else {
				gameManagerAwale.getJoueur2().setNbrGrainesEnJeu( gameManagerAwale.getJoueur2().getNbrGrainesEnJeu()+1 );
			}
			plateau[caseActuelle]++;
			grainesRestantes--;
		}
		return caseActuelle;
	}
	public int miseAJourPlateauSimuler(int[] plateauSimule, int caseInitiale) {//permet d'actualiser les cases du plateau
		int grainesRestantes=plateauSimule[caseInitiale];
		plateauSimule[caseInitiale]=0;
		
		int caseActuelle=caseInitiale;
		while(grainesRestantes>0) {
			if( ( (caseActuelle+1)%12 ) == caseInitiale ){
				caseActuelle=(caseActuelle+2)%12;
			}
			else {
				caseActuelle=(caseActuelle+1)%12;
			}
			plateauSimule[caseActuelle]++;
			grainesRestantes--;
		}
		return caseActuelle;
	}
	
	@Override
	public void jouerUnCoup(int caseJouee, GameManagerAwale gameManagerAwale) {//mise a jour des valeurs du plateau
		int derniereCaseJouee=miseAJourPlateau(gameManagerAwale.getPartie().getPlateau(), caseJouee, gameManagerAwale);
		//enlever les graines = 2 ou =3
		// diminuer le nbr de graines du plateau et le nbr de graines pour l'adversaire
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
			//Modifier le nombre de graines total de chaque joueur :
			if( getNumeroJoueur() == 2 ) {
				gameManagerAwale.getJoueur1().setNbrGrainesEnJeu( gameManagerAwale.getJoueur1().getNbrGrainesEnJeu() - gameManagerAwale.getPartie().getPlateau()[CaseActuelle] );
			}
			else {
				gameManagerAwale.getJoueur2().setNbrGrainesEnJeu( gameManagerAwale.getJoueur2().getNbrGrainesEnJeu() - gameManagerAwale.getPartie().getPlateau()[CaseActuelle] );
			}
			//Modifier le score du joueur :
			this.setScore(this.getScore() + gameManagerAwale.getPartie().getPlateau()[CaseActuelle]);
			System.out.println("score joueur " + getNumeroJoueur() +": " + getScore());
			//Modifier le nombre de graines disponnible sur le plateau
			gameManagerAwale.getPartie().setNbrGraines(gameManagerAwale.getPartie().getNbrGraines() - gameManagerAwale.getPartie().getPlateau()[CaseActuelle] );
			System.out.println("nbr graines en jeu : " + gameManagerAwale.getPartie().getNbrGraines());
			//prendre les graines 
			gameManagerAwale.getPartie().setPlateau(0, CaseActuelle );
			System.out.println("Les graines de la case " + CaseActuelle + " ont ete recuperees par le joueur " + gameManagerAwale.joueurActuel().getNumeroJoueur());
			
			CaseActuelle--;
		}
		
	}

	
}
