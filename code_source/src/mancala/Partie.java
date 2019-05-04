package mancala;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Partie {
	private int coupActu = -1;
	private ArrayList<SButton> buttonListGame;
	private ArrayList<SButton> buttonListMenu;
	private ArrayList<JLabel> nameList;
	private ArrayList<JLabel> scoreList;

	public JFrame getFrmAwale() {
		return DrawingManagerAwale.getInstance();
	}

	public int getCoupActu() {
		return coupActu;
	}

	public void setCoupActu(int coupActu) {
		this.coupActu = coupActu;
	}

	public ArrayList<SButton> getButtonListGame() {
		return buttonListGame;
	}

	public ArrayList<JLabel> getNameList() {
		return nameList;
	}

	public ArrayList<JLabel> getScoreList() {
		return scoreList;
	}

	public SButton getButtonGame(int i) {
		return buttonListGame.get(i);
	}

	public SButton getButtonMenu(int i) {
		return buttonListMenu.get(i);
	}

	public Partie(String nomJ1, String nomJ2) {
		initialize(nomJ1, nomJ2);
	}

	private void initialize(String nomJ1, String nomJ2) {
		//Partie panel
		JPanel all = new JPanel(new BorderLayout(0, 0));
		DrawingManagerAwale.getInstance().setContentPane(all);

		//Menu panel
		JPanel menu = new SPanel();
		menu.setLayout(new GridLayout(1, 3, 55, 0));
		all.add(menu, BorderLayout.NORTH);

		buttonListMenu = new ArrayList<>();
		for(int i = 0; i < 3; i++)
		{
			SButton btn = new SButton("0");
			buttonListMenu.add(btn);
		}

		getButtonMenu(0).setText("New");
		getButtonMenu(1).setText("Save");
		getButtonMenu(2).setText("Quit");

		getButtonMenu(0).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane save = new JOptionPane();
				String nom = save.showInputDialog(null, "Save?", "new", JOptionPane.QUESTION_MESSAGE);
				if(nom != null)
				{
					GameManagerAwale.getInstance().sauvegarder(nom);
				}
				GameManagerAwale.getInstance().killPartie();
				GameManagerAwale.getInstance().resetPartie();
				new ChoixJoueur(GameManagerAwale.getInstance());
			}
		});

		getButtonMenu(1).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane save = new JOptionPane();
				save.setOptions(new Object[] {
						new JButton("Oui"), new JButton("Non")
				});
				String nom = save.showInputDialog(null, "Save?", "save", JOptionPane.YES_NO_OPTION);
				if(nom != null)
				{
					GameManagerAwale.getInstance().sauvegarder(nom);
				}
			}
		});

		getButtonMenu(2).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane save = new JOptionPane();
				String nom = save.showInputDialog(null, "Save?", "Quit", JOptionPane.QUESTION_MESSAGE);
				if(nom != null)
				{
					GameManagerAwale.getInstance().sauvegarder(nom);
				}
				GameManagerAwale.getInstance().resetPartie();
				System.exit(0);
			}
		});

		for(int i = 0; i < 3; i++) {
			menu.add(getButtonMenu(i));
		}

		//JLabel titre = new JLabel("Bienvenue au jeu d'Awale !");
		//titre.setForeground(Color.BLACK);
		//menu.add(titre);

		//Partie panel
		SPanel game = new SPanel();
		all.add(game);
		game.setLayout(new GridLayout(2, 1, 0, 5));

		JPanel j2 = new SPanel();
		game.add(j2);
		j2.setLayout(new GridLayout(0, 1, 0, 5));

		JPanel j2_info = new SPanel();
		j2.add(j2_info);
		j2_info.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel j2_score = new SPanel();
		j2.add(j2_score);
		j2_score.setLayout(new GridLayout(1, 6, 10, 0));

		JPanel j2_cases = new SPanel();
		j2.add(j2_cases);
		j2_cases.setLayout(new GridLayout(1, 6, 10, 0));

		buttonListGame = new ArrayList<>();
		for(int i = 0; i < 12; i++)
		{
			SButton btn = new SButton("4");
			final int fi = i;
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setCoupActu(fi);
				}
			});
			buttonListGame.add(btn);
		}

		nameList = new ArrayList<>();
		for(int i = 0; i < 2; i++){
			JLabel nomJoueur = new JLabel("Joueur " + (i+1));
			nameList.add(nomJoueur);
		}
		nameList.get(0).setText(nomJ1);
		nameList.get(1).setText(nomJ2);

		scoreList = new ArrayList<>();
		for(int i = 0; i < 2; i++){
			JLabel scoreJoueur = new JLabel("0");
			scoreList.add(scoreJoueur);
		}

		JPanel j1 = new SPanel();
		game.add(j1);
		j1.setLayout(new GridLayout(0, 1, 0, 5));

		JPanel j1_cases = new SPanel();
		j1.add(j1_cases);
		j1_cases.setLayout(new GridLayout(1, 6, 10, 0));

		JPanel j1_score = new SPanel();
		j1.add(j1_score);
		j1_score.setLayout(new GridLayout(1, 6, 10, 0));

		JPanel j1_info = new SPanel();
		j1.add(j1_info);
		j1_info.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// Ajouter cases J2
		for(int i = 11; i > 5; i--) {
			j2_cases.add(getButtonGame(i));
		}

		// Ajouter cases J1
		for(int i = 0; i < 6; i++) {
			j1_cases.add(getButtonGame(i));
		}

		j2_info.add(nameList.get(1));

		j2_info.add(scoreList.get(1));

		j1_info.add(nameList.get(0));
		j1_info.add(scoreList.get(0));
		DrawingManagerAwale.getInstance().setVisible(true);
	}
}
