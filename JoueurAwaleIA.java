package mancala;
// les m�thodes de d�cision seront utiliser(MiniMax, Evalution...)
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
	
	
	
}
