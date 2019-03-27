package mancala;

import javax.swing.JFrame;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class DrawingManagerAwale {

	private JFrame frmAwale;
	private int coupActu = -1;
	private ArrayList<SButton> buttonList;
	private ArrayList<JLabel> nameList;
	private ArrayList<JLabel> scoreList;
	private GameManagerAwale arbitreAwale;

	public JFrame getFrmAwale() {
		return frmAwale;
	}

	public void setFrmAwale(JFrame frmAwale) {
		this.frmAwale = frmAwale;
	}

	public int getCoupActu() {
		return coupActu;
	}

	public void setCoupActu(int coupActu) {
		this.coupActu = coupActu;
	}

	public ArrayList<SButton> getButtonList() {
		return buttonList;
	}

	public ArrayList<JLabel> getNameList() {
		return nameList;
	}

	public ArrayList<JLabel> getScoreList() {
		return scoreList;
	}

	public GameManagerAwale getArbitreAwale() {
		return arbitreAwale;
	}

	public JButton getButtonIndice(int i) {
		return buttonList.get(i);
	}

	public DrawingManagerAwale(GameManagerAwale arbitreAwale) {
		this.arbitreAwale = arbitreAwale;
		initialize();
	}

	private void initialize() {
		frmAwale = new JFrame();
		frmAwale.setTitle("Awale");
		frmAwale.setSize(450, 300);
		frmAwale.setLocationRelativeTo(null);
		frmAwale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAwale.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel menu = new SPanel();
		frmAwale.getContentPane().add(menu, BorderLayout.NORTH);

		JLabel titre = new JLabel("Bienvenue au jeu d'Awale !");
		titre.setForeground(Color.WHITE);
		menu.add(titre);

		JPanel game = new SPanel();
		frmAwale.getContentPane().add(game);
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

		buttonList = new ArrayList<>();
		for(int i = 0; i < 12; i++)
		{
			SButton btn = new SButton("4");
			final int fi = i;
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setCoupActu(fi);
				}
			});
			buttonList.add(btn);
		}

		nameList = new ArrayList<>();
		for(int i = 0; i < 2; i++){
			JLabel nomJoueur = new JLabel("Joueur " + (i+1));
			nameList.add(nomJoueur);
		}

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
			j2_cases.add(buttonList.get(i));
		}

		// Ajouter cases J1
		for(int i = 0; i < 6; i++) {
			j1_cases.add(buttonList.get(i));
		}

		j2_info.add(nameList.get(1));

		//j2_info.add(scoreList.get(1));

		j1_info.add(nameList.get(0));

		//j1_info.add(scoreList.get(0));
	}
}
