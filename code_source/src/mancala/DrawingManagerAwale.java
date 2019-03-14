package mancala;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;

public class DrawingManagerAwale {

	private JFrame frmAwale;
	private int coupActu = -1;
	private JButton j2_c6;
	private JButton j2_c5;
	private JButton j2_c4;
	private JButton j2_c3;
	private JButton j2_c2;
	private JButton j2_c1;
	private JButton j1_c6;
	private JButton j1_c5;
	private JButton j1_c4;
	private JButton j1_c3;
	private JButton j1_c2;
	private JButton j1_c1;
	private GameManagerAwale arbitreAwale;

	public JButton getJ2_c6() {
		return j2_c6;
	}

	public void setJ2_c6(JButton j2_c6) {
		this.j2_c6 = j2_c6;
	}

	public JButton getJ2_c5() {
		return j2_c5;
	}

	public void setJ2_c5(JButton j2_c5) {
		this.j2_c5 = j2_c5;
	}

	public JButton getJ2_c4() {
		return j2_c4;
	}

	public void setJ2_c4(JButton j2_c4) {
		this.j2_c4 = j2_c4;
	}

	public JButton getJ2_c3() {
		return j2_c3;
	}

	public void setJ2_c3(JButton j2_c3) {
		this.j2_c3 = j2_c3;
	}

	public JButton getJ2_c2() {
		return j2_c2;
	}

	public void setJ2_c2(JButton j2_c2) {
		this.j2_c2 = j2_c2;
	}

	public JButton getJ2_c1() {
		return j2_c1;
	}

	public void setJ2_c1(JButton j2_c1) {
		this.j2_c1 = j2_c1;
	}

	public JButton getJ1_c6() {
		return j1_c6;
	}

	public void setJ1_c6(JButton j1_c6) {
		this.j1_c6 = j1_c6;
	}

	public JButton getJ1_c5() {
		return j1_c5;
	}

	public void setJ1_c5(JButton j1_c5) {
		this.j1_c5 = j1_c5;
	}

	public JButton getJ1_c4() {
		return j1_c4;
	}

	public void setJ1_c4(JButton j1_c4) {
		this.j1_c4 = j1_c4;
	}

	public JButton getJ1_c3() {
		return j1_c3;
	}

	public void setJ1_c3(JButton j1_c3) {
		this.j1_c3 = j1_c3;
	}

	public JButton getJ1_c2() {
		return j1_c2;
	}

	public void setJ1_c2(JButton j1_c2) {
		this.j1_c2 = j1_c2;
	}

	public JButton getJ1_c1() {
		return j1_c1;
	}

	public void setJ1_c1(JButton j1_c1) {
		this.j1_c1 = j1_c1;
	}

	public int getCoupActu() {
		return coupActu;
	}

	public void setCoupActu(int coupActu) {
		this.coupActu = coupActu;
	}

	public JFrame getFrmAwale() {
		return frmAwale;
	}

	public void setFrmAwale(JFrame frmAwale) {
		this.frmAwale = frmAwale;
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
		
		JPanel menu = new JPanel();
		frmAwale.getContentPane().add(menu, BorderLayout.NORTH);
		
		JLabel titre = new JLabel("Bienvenue au jeu d'Awale !");
		menu.add(titre);
		
		JPanel game = new JPanel();
		frmAwale.getContentPane().add(game);
		game.setLayout(new GridLayout(2, 1, 0, 5));
		
		JPanel j2 = new JPanel();
		game.add(j2);
		j2.setLayout(new GridLayout(0, 1, 0, 5));
		
		JPanel j2_info = new JPanel();
		j2.add(j2_info);
		
		JPanel j2_score = new JPanel();
		j2.add(j2_score);
		j2_score.setLayout(new GridLayout(1, 6, 10, 0));
		
		JPanel j2_cases = new JPanel();
		j2.add(j2_cases);
		j2_cases.setLayout(new GridLayout(1, 6, 10, 0));

		j2_c6 = new JButton("4");
		j2_c6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCoupActu(11);
			}
		});
		j2_cases.add(j2_c6);

		j2_c5 = new JButton("4");
		j2_c5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCoupActu(10);
			}
		});
		j2_cases.add(j2_c5);

		j2_c4 = new JButton("4");
		j2_c4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCoupActu(9);
			}
		});
		j2_cases.add(j2_c4);

		j2_c3 = new JButton("4");
		j2_c3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCoupActu(8);
			}
		});
		j2_cases.add(j2_c3);

		j2_c2 = new JButton("4");
		j2_c2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCoupActu(7);
			}
		});
		j2_cases.add(j2_c2);

		j2_c1 = new JButton("4");
		j2_c1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCoupActu(6);
			}
		});
		j2_cases.add(j2_c1);
		
		JPanel j1 = new JPanel();
		game.add(j1);
		j1.setLayout(new GridLayout(0, 1, 0, 5));
		
		JPanel j1_cases = new JPanel();
		j1.add(j1_cases);
		j1_cases.setLayout(new GridLayout(1, 6, 10, 0));

		j1_c1 = new JButton("4");
		j1_c1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCoupActu(0);
			}
		});
		j1_cases.add(j1_c1);

		j1_c2 = new JButton("4");
		j1_c2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCoupActu(1);
			}
		});
		j1_cases.add(j1_c2);

		j1_c3 = new JButton("4");
		j1_c3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCoupActu(2);
			}
		});
		j1_cases.add(j1_c3);

		j1_c4 = new JButton("4");
		j1_c4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCoupActu(3);
			}
		});
		j1_cases.add(j1_c4);

		j1_c5 = new JButton("4");
		j1_c5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCoupActu(4);
			}
		});
		j1_cases.add(j1_c5);

		j1_c6 = new JButton("4");
		j1_c6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCoupActu(5);
			}
		});
		j1_cases.add(j1_c6);
		
		JPanel j1_score = new JPanel();
		j1.add(j1_score);
		j1_score.setLayout(new GridLayout(1, 6, 10, 0));
		
		JPanel j1_info = new JPanel();
		j1.add(j1_info);
	}
}
