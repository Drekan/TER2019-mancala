package mancala;

public class DrawingManager {
    private GameManagerAwale arbitre;

    public DrawingManager(GameManagerAwale arbitre){
        this.arbitre = arbitre;
    }

    public void go(){
        new ChoixJoueur(arbitre);
    }
}
