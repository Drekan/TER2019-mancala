package mancala;

//On demande a  l'utilisateur de fairee un coup
public class JoueurAwaleHumain extends JoueurAwale{

	public JoueurAwaleHumain(String nomJoueur, int score, int numeroJoueur, int min, int max) {
		super(nomJoueur, score, numeroJoueur,min,max);
	}
	@Override
	public int getJeu() {
		int caseJouer = 0 ;
		return caseJouer;
	}

}
