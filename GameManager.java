package mancala;
//C'est l'arbitre d'une partie, il connait le plateau de jeu et les joueurs,
//c'est lui qui demande aux joueurs de jouer quand leur tour arrive,
//et c'est lui qui soumet leurs coup au jeu qu'il arbitre (Expl : Awale)
public abstract class GameManager {

	//methods :
	public abstract JoueurAwale gestionTour();//d�cide de qui va jouer
	public abstract boolean finPartie();//dire si c'est une fin de partie et arreter le jeu en fonction
	public abstract void gestionTemps();//gere le temps allouer a chaque joueur tour a tour
	public abstract void getGagnant() ; // annoncer le gagnant
	
}
