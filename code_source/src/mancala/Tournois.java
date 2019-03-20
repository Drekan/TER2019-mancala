package mancala;

import java.util.Scanner;

public class Tournois {

	static Awale partie;
	private static int nbrVictoiresJ1;
	private static int nbrVictoiresJ2;
	private static JoueurAwaleIA j1;
	private static JoueurAwaleIA j2;
	private static int nbrPartie;
	static JoueurAwale gagnant;
	
	public Tournois(JoueurAwaleIA j1,JoueurAwaleIA j2) {
		this.nbrVictoiresJ1=0;
		this.nbrVictoiresJ2=0;
		this.setJ1(j1);
		this.setJ2(j2);
	}
	
	
	public JoueurAwaleIA getJ1() {
		return this.j1;
	}
	
	public static void setJ1(JoueurAwaleIA j) {
		j1=j;
	}
	
	public JoueurAwaleIA getJ2() {
		return this.j2;
	}
	
	public static void setJ2(JoueurAwaleIA j) {
		j2=j;
	}
	
	public int getNbrVictoireJ1() {
		return this.nbrVictoiresJ1;
	}
	public void setNbrVictoireJ1(int nbr) {
		this.nbrVictoiresJ1=nbr;
	}
	
	public int getNbrVictoireJ2() {
		return this.nbrVictoiresJ2;
	}
	public void setNbrVictoireJ2(int nbr) {
		this.nbrVictoiresJ2=nbr;
	}
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Entrer le nombre de tournois que vous souhaitez faire >> ");
		nbrPartie = sc.nextInt();
		
		GameManagerAwale ArbitreAwale = new GameManagerAwale(0,0);
		
		ArbitreAwale.initJoueurs("joueur1","joueur2");
		setJ1((JoueurAwaleIA) ArbitreAwale.getJoueur1());
		setJ2((JoueurAwaleIA) ArbitreAwale.getJoueur2());

		partie = new Awale("MonAwale","MesRegles");
		ArbitreAwale.setPartie(partie);
		
		ArbitreAwale.getPartie().initialisationJeu();
		
		for(int i=0; i<nbrPartie; i++) {
			
			ArbitreAwale.commencerPartie();
			
			
			//gagnant = ArbitreAwale.getGagnant();
			if(ArbitreAwale.getGagnant() == j1) {
				nbrVictoiresJ1++;
			}
			else if(ArbitreAwale.getGagnant() == j2) {
				nbrVictoiresJ2++;
			}
			
		}
		
		System.out.println("Nombre de partie gagne par joueur 1 : " + nbrVictoiresJ1);
		System.out.println("Nombre de partie gagne par joueur 2 : " + nbrVictoiresJ2);
		
		
	}
	
	
}
