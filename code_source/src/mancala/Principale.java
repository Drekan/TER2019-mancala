package mancala;

import java.util.Random;
import java.util.Scanner;

public class Principale {

	public static void main(String[] args) {
		GameManagerAwale ArbitreAwale = new GameManagerAwale();
		
		ArbitreAwale.lancerUneNouvellePartie();
		
		ArbitreAwale.getGagnant();
		
	}

}
