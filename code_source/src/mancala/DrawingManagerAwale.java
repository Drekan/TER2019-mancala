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

		// Ajouter cases J2
		for(int i = 11; i > 5; i--) {
			j2_cases.add(buttonList.get(i));
		}

		// Ajouter cases J1
		for(int i = 0; i < 6; i++) {
			j1_cases.add(buttonList.get(i));
		}
	}
}
