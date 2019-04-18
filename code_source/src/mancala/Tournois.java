package mancala;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Tournois {

	//----Informations sur j1 et j2----
	private JoueurAwaleIA j1;
	private int nbrVictoiresJ1;
	
	private JoueurAwaleIA j2;
	private int nbrVictoiresJ2;

	private int nbrPartiesEffectuees=0;
	
	//----Informations mises à jour tout au long du tournois----
	private ArrayList<String> gagnants;
	private ArrayList<Integer> scores;

	
	
	public Tournois(JoueurAwaleIA j1,JoueurAwaleIA j2) {
		gagnants=new ArrayList<String>();
		scores=new ArrayList<Integer>();
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
	
	/*Donne le nombre de victoire du joueur, identifié par son numéro
	 * @param numeroJoueur numéro du joueur, valeurs dans {1,2}
	 */
	public int getNbrVictoire(int numeroJoueur) {
		int nbVictoire=-1;
		if(numeroJoueur==1 || numeroJoueur==2)
			nbVictoire=(numeroJoueur==1?this.nbrVictoiresJ1:this.nbrVictoiresJ2);
		return nbVictoire;
	}
	public void incrementNbrVictoire(int nombreJoueur) {
		if(nombreJoueur==1) {
			this.nbrVictoiresJ1++;
		}else {
			this.nbrVictoiresJ2++;
		}
	}

	public int saisirNbrParties() {
		int nbrParties=0;
		Scanner sc=new Scanner(System.in);
		System.out.println("----Preparation du Tournois----");
		do {
			System.out.print("Nombre de parties à faire >>");
			nbrParties = sc.nextInt();
		}while(nbrParties<1);
		return nbrParties;
	}
	
	public void printResultats(long time) {
		long tempsTournois=System.currentTimeMillis()-time;
		System.out.println("|");
		System.out.println("--Le tournois est terminé--");
		System.out.println("Parties gagnees par <"+j1.getNom()+"> :"+ 100*(double)nbrVictoiresJ1/this.nbrPartiesEffectuees+"%");
		System.out.println("Parties gagnees par <"+j2.getNom()+"> :"+ 100*(double)nbrVictoiresJ2/this.nbrPartiesEffectuees+"%");
		System.out.println("Score gagnant : "+this.scores.get(0));
		System.out.println("(temps : "+tempsTournois/1000+"s)");		
	}
	
	
	
	public int saisirValeurLimite(String modeTournois) {
		int valeurLimite=0;
		Scanner sc=new Scanner(System.in);
		if(modeTournois=="nombre-parties") {
			System.out.println("Entrez un nombre de parties");
		}else {
			System.out.println("Entrez une durée limite (en s)");
		}
		
		do {
			System.out.print(">>");
			valeurLimite=sc.nextInt();
		}while(valeurLimite<1);
		
		return valeurLimite;
	}
	
	public String choisirModeTournois() {
		Scanner sc=new Scanner(System.in);
		int choix;
		System.out.println("0.Saisir un nombre de parties\n1.Saisir une durée limite (en secondes)");
		do {
			System.out.print("Votre choix>>");
			choix=sc.nextInt();
		}while(choix<0 || choix>1);
		return (choix==0?"nombre-parties":"duree");
	}
	
	
	public void initialiserJoueurs() {
		Scanner sc=new Scanner(System.in);
		char ouiNon;
		j1.setNomParDefaut();
		j1.demanderHeuristique();
		j1.demanderProfondeur();
		if(j1.getDifficulte()>0) {
			do {
				System.out.println("Saisir les poids de "+j1.getNom()+" ? (o/n)");
				ouiNon=sc.nextLine().charAt(0);
			}while(ouiNon!='o' && ouiNon!='n');
			if(ouiNon=='o') {
				j1.demanderPoids();
				System.out.println();
			}
		}
		j2.setNomParDefaut();
		j2.demanderHeuristique();
		j2.demanderProfondeur();
		if(j2.getDifficulte()>0) {
			do {
				System.out.println("Saisir les poids de "+j2.getNom()+" ? (o/n)");
				ouiNon=sc.nextLine().charAt(0);
			}while(ouiNon!='o' && ouiNon!='n');
			if(ouiNon=='o') {
				j2.demanderPoids();
				System.out.println();
			}
		}
		
	}
	
	public void updateTrace(JoueurAwale gagnant) {
		if(gagnant!=null) {
			gagnants.add(0,gagnant.getNom());
			scores.add(0,gagnant.getScore());
			incrementNbrVictoire(gagnant.getNumeroJoueur());
		}else {
			gagnants.add(0,"NULL");
			scores.add(0,this.j1.getScore());
		}
	}
	
	/* Méthode qui lance un tournois, cad :
	 *  -initialisation de la partie et chargement des joueurs dans GMA
	 *  -affichage dynamique de la progression
	 *  -suivi des parties jouées grâce à scores[] et gagnants[]
	 *  -affichage des résultats du tournois
	 */
	public void lancer() {
		
		//initialisation des joueurs et de l'arbitre
		initialiserJoueurs();
		
		//saisie du mode de Tournois
		String limite=choisirModeTournois();
		int valeurLimite=saisirValeurLimite(limite);
		
		
		
		GameManagerAwale arbitre= new GameManagerAwale(0,0);
		arbitre.loadJoueur1(j1);
		arbitre.loadJoueur2(j2);	

		int valeurEffective=0;
		int nbrParties=0;
		long time=System.currentTimeMillis();//calculer la durée du tournois
		
		System.out.println("|----Progression----|");
		System.out.println("|0%-- --50%-- --100%|");
		System.out.print(  "|");
		int progressionAffichee=0;//pourcentage de progression qui a été affiché
		
		while(valeurEffective<valeurLimite) {
			arbitre.commencerPartie(false);
			updateTrace(arbitre.getGagnant());
			nbrParties++;
			
			//on augmente le nombre de parties ou le temps écoulé
			switch(limite) {
			case "nombre-parties":
				valeurEffective++;
				break;
			case "duree":
				valeurEffective+=(System.currentTimeMillis()-time)/1000;
				break;
			}
			
			while(progressionAffichee<((valeurEffective*100)/valeurLimite)) {
				progressionAffichee+=10;
				System.out.print("-"+(progressionAffichee==100?"":"-"));
			}
			arbitre.resetPartie();
		}
		this.nbrPartiesEffectuees=nbrParties;
		printResultats(time);	
	}
	
	boolean saveCSV(String nomFichier) {
		boolean ok=true;
		String nomF=(nomFichier==null)?"resultats_tournois.csv":nomFichier;
		File fichier=new File(nomF);
		FileWriter bufferEcriture=null;
		
		try {
			bufferEcriture=new FileWriter(fichier);
			bufferEcriture.write("Nom IA,heuristiques,poids,profondeur,% reussite\n");
			bufferEcriture.write(j1.getNom()+",");
			
			if(this.j1.getDifficulte()>0){
				bufferEcriture.write(j1.getMasque()+","+j1.getPoids()+","+j1.getProfondeurMax());
			}else {
				bufferEcriture.write("N/A,N/A,N/A");
			}
			
			bufferEcriture.write(","+String.format("%.0f",100*(double)nbrVictoiresJ1/this.nbrPartiesEffectuees)+"\n");
			
			bufferEcriture.write(j2.getNom()+",");
			
			if(this.j2.getDifficulte()>0){
				bufferEcriture.write(j2.getMasque()+","+j2.getPoids()+","+j2.getProfondeurMax());
			}else {
				bufferEcriture.write("N/A,N/A,N/A");
			}
			
			bufferEcriture.write(","+String.format("%.0f",100*(double)nbrVictoiresJ2/this.nbrPartiesEffectuees)+"\n");
			
			bufferEcriture.write("match,gagnant,score <"+j1.getNom()+">,score <"+j2.getNom()+">\n");
			for(int i=0;i<this.gagnants.size();i++) {
				bufferEcriture.write(i+","+this.gagnants.get(i));
				if(this.gagnants.get(i)==j1.getNom()) {
					bufferEcriture.write(","+this.scores.get(i)+","+(48-this.scores.get(i))+"\n");
				}else {
					bufferEcriture.write(","+(48-this.scores.get(i))+","+this.scores.get(i)+"\n");
				}
			}
			
			bufferEcriture.write("score moyen <"+j1.getNom()+">,"+this.getScoreMoyenJ1()/this.nbrPartiesEffectuees+"\n");
			bufferEcriture.write("score moyen <"+j2.getNom()+">,"+this.getScoreMoyenJ2()/this.nbrPartiesEffectuees+"\n");
		}
		catch (IOException e) {
			e.printStackTrace();
			ok=false;
		}
		finally {
			try {
				bufferEcriture.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		return ok;
	}
	
	public double getScoreMoyenJ1() {
		double scoreMoyen=0;
		for(int i=0;i<this.gagnants.size();i++) {
			if(this.gagnants.get(i)==j1.getNom()) {
				scoreMoyen+=this.scores.get(i);
			}
			else {
				scoreMoyen+=48-this.scores.get(i);
			}
		}
		
		return scoreMoyen;
	}
	
	public double getScoreMoyenJ2() {
		double scoreMoyen=0;
		for(int i=0;i<this.gagnants.size();i++) {
			if(this.gagnants.get(i)==j2.getNom()) {
				scoreMoyen+=this.scores.get(i);
			}
			else {
				scoreMoyen+=48-this.scores.get(i);
			}
		}
		
		return scoreMoyen;
	}
	
	
}
