package mancala;

import java.util.Scanner;

public class Tournois {

	private Awale partie;
	private int nbrVictoiresJ1;
	private int nbrVictoiresJ2;
	private JoueurAwaleIA j1;
	private JoueurAwaleIA j2;
	private int nbrPartie;
	private JoueurAwale gagnant;
	
	public Tournois(JoueurAwaleIA j1,JoueurAwaleIA j2) {
		this.nbrVictoiresJ1=0;
		this.nbrVictoiresJ2=0;
		this.setJ1(j1);
		this.setJ2(j2);
	}
	
	
	public JoueurAwaleIA getJ1() {
		return this.j1;
	}
	
	public void setJ1(JoueurAwaleIA j) {
		j1=j;
	}
	
	public JoueurAwaleIA getJ2() {
		return this.j2;
	}
	
	public void setJ2(JoueurAwaleIA j) {
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
	
	
	
	
	
	
	public void lancer() {
		Scanner sc=new Scanner(System.in);
		System.out.println("----Preparation du Tournois----");
		do {
			System.out.println("Nombre de parties à faire >>");
			nbrPartie = sc.nextInt();
		}while(nbrPartie<1);
		
		GameManagerAwale arbitre= new GameManagerAwale(0,0);
		arbitre.saveJoueur1(j1);
		arbitre.saveJoueur2(j2);
		
		System.out.println("Progression");
		System.out.println("|0%-- --50%-- --100%|");
		System.out.print("|");
		int progression=0;
		for(int i=0; i<nbrPartie; i++) {
			
			arbitre.commencerPartie(false);	
			
			//gagnant = ArbitreAwale.getGagnant();
			if(arbitre.getGagnant() == arbitre.getJoueur1()) {
				nbrVictoiresJ1++;
			}
			else if(arbitre.getGagnant() == arbitre.getJoueur2()) {
				nbrVictoiresJ2++;
			}
			while(((double)(i+1)/this.nbrPartie*100)>progression) {
				progression+=10;
				System.out.print("-"+(progression==100?"|":"-"));
			}

			arbitre.resetPartie();
			
		}
		System.out.println("");
		System.out.println("Nombre de parties gagnees par joueur 1 : " + nbrVictoiresJ1);
		System.out.println("Nombre de parties gagnees par joueur 2 : " + nbrVictoiresJ2);
		
		
	}
	
	
}
