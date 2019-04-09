package mancala;

public class DrawingManager {
    private ChoixJoueur windowJoueur;
    private ChoixDifficulte windowDifficulte;
    private DrawingManagerAwale windowAwale;

    public DrawingManagerAwale lancerWindow(){
        windowAwale = new DrawingManagerAwale("", "");
        windowAwale.getFrmAwale().setVisible(true);
        return windowAwale;
    }
	
}
