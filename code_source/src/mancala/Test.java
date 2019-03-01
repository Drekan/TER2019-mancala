package mancala;

import java.awt.*;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        DrawingManagerAwale window;
        Scanner sc = new Scanner(System.in);
        System.out.println("Choisissez, un ou deux joueurs? ");
        int choix = sc.nextInt();
        GameManagerAwale ArbitreAwale = new GameManagerAwale(choix);

        System.out.println("Choisissez la difficulte ");
        int difficulte = sc.nextInt();
        ArbitreAwale.lancerUneNouvellePartie(difficulte);

        System.out.println("nbrgraine init : " + ArbitreAwale.getPartie().getNbrGrainesEnJeu() );

        sc.close();

        window = new DrawingManagerAwale(ArbitreAwale);
        window.getFrmAwale().setVisible(true);
        //window.startGame();

//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        while( !ArbitreAwale.finPartie() ) {
            int coupJoue = -1;

            // gestion tour:
            if( ArbitreAwale.gestionTour() == ArbitreAwale.getJoueur1()){
                do {
                    coupJoue = window.getCoupActu();
                    window.setCoupActu(-1);
                    try {
                        Thread.sleep(1000/60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //System.out.println(coupJoue);
                }while( (coupJoue == -1) || (!ArbitreAwale.verifierCoupValide(ArbitreAwale.getJoueur1(),coupJoue,ArbitreAwale.getPartie().getPlateau())) );
                ArbitreAwale.getJoueur1().jouerUnCoup(coupJoue,ArbitreAwale);
            }
            else {
                ArbitreAwale.getJoueur2().choisirUnCoup(ArbitreAwale);
            }

            ArbitreAwale.stockerEtatMouvement(ArbitreAwale.getPartie().etatActuel());

            //Actualiser les cases graphiques
            window.getJ1_c1().setLabel("" + ArbitreAwale.getPartie().etatActuel()[0]);
            window.getJ1_c2().setLabel("" + ArbitreAwale.getPartie().etatActuel()[1]);
            window.getJ1_c3().setLabel("" + ArbitreAwale.getPartie().etatActuel()[2]);
            window.getJ1_c4().setLabel("" + ArbitreAwale.getPartie().etatActuel()[3]);
            window.getJ1_c5().setLabel("" + ArbitreAwale.getPartie().etatActuel()[4]);
            window.getJ1_c6().setLabel("" + ArbitreAwale.getPartie().etatActuel()[5]);
            window.getJ2_c1().setLabel("" + ArbitreAwale.getPartie().etatActuel()[6]);
            window.getJ2_c2().setLabel("" + ArbitreAwale.getPartie().etatActuel()[7]);
            window.getJ2_c3().setLabel("" + ArbitreAwale.getPartie().etatActuel()[8]);
            window.getJ2_c4().setLabel("" + ArbitreAwale.getPartie().etatActuel()[9]);
            window.getJ2_c5().setLabel("" + ArbitreAwale.getPartie().etatActuel()[10]);
            window.getJ2_c6().setLabel("" + ArbitreAwale.getPartie().etatActuel()[11]);

        }
        ArbitreAwale.getGagnant();
    }
}
