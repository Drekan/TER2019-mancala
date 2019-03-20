package mancala;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DrawingManagerAwale {

	private JFrame frmAwale;
	private int coupActu = -1;
	private JButton j11;
	private JButton j10;
	private JButton j9;
	private JButton j8;
	private JButton j7;
	private JButton j6;
	private JButton j5;
	private JButton j4;
	private JButton j3;
	private JButton j2;
	private JButton j1;
	private JButton j0;
	private GameManagerAwale arbitreAwale;

	public JButton getJ11() {
		return j11;
	}

	public void setJ11(JButton j11) {
		this.j11 = j11;
	}

	public JButton getJ10() {
		return j10;
	}

	public void setJ10(JButton j10) {
		this.j10 = j10;
	}

	public JButton getJ9() {
		return j9;
	}

	public void setJ9(JButton j9) {
		this.j9 = j9;
	}

	public JButton getJ8() {
		return j8;
	}

	public void setJ8(JButton j8) {
		this.j8 = j8;
	}

	public JButton getJ7() {
		return j7;
	}

	public void setJ7(JButton j7) {
		this.j7 = j7;
	}

	public JButton getJ6() {
		return j6;
	}

	public void setJ6(JButton j6) {
		this.j6 = j6;
	}

	public JButton getJ5() {
		return j5;
	}

	public void setJ5(JButton j5) {
		this.j5 = j5;
	}

	public JButton getJ4() {
		return j4;
	}

	public void setJ4(JButton j4) {
		this.j4 = j4;
	}

	public JButton getJ3() {
		return j3;
	}

	public void setJ3(JButton j3) {
		this.j3 = j3;
	}

	public JButton getJ2() {
		return j2;
	}

	public void setJ2(JButton j2) {
		this.j2 = j2;
	}

	public JButton getJ1() {
		return j1;
	}

	public void setJ1(JButton j1) {
		this.j1 = j1;
	}

	public JButton getJ0() {
		return j0;
	}

	public void setJ0(JButton j0) {
		this.j0 = j0;
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

		j11 = new JButton("4");
		j11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCoupActu(11);
			}
		});
		j2_cases.add(j11);

		j10 = new JButton("4");
		j10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCoupActu(10);
			}
		});
		j2_cases.add(j10);

		j9 = new JButton("4");
		j9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCoupActu(9);
			}
		});
		j2_cases.add(j9);

		j8 = new JButton("4");
		j8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCoupActu(8);
			}
		});
		j2_cases.add(j8);

		j7 = new JButton("4");
		j7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCoupActu(7);
			}
		});
		j2_cases.add(j7);

		j6 = new JButton("4");
		j6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCoupActu(6);
			}
		});
		j2_cases.add(j6);
		
		JPanel j1 = new JPanel();
		game.add(j1);
		j1.setLayout(new GridLayout(0, 1, 0, 5));
		
		JPanel j1_cases = new JPanel();
		j1.add(j1_cases);
		j1_cases.setLayout(new GridLayout(1, 6, 10, 0));

		j0 = new JButton("4");
		j0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCoupActu(0);
			}
		});
		j1_cases.add(j0);

		this.j1 = new JButton("4");
		this.j1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCoupActu(1);
			}
		});
		j1_cases.add(this.j1);

		this.j2 = new JButton("4");
		this.j2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCoupActu(2);
			}
		});
		j1_cases.add(this.j2);

		j3 = new JButton("4");
		j3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCoupActu(3);
			}
		});
		j1_cases.add(j3);

		j4 = new JButton("4");
		j4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCoupActu(4);
			}
		});
		j1_cases.add(j4);

		j5 = new JButton("4");
		j5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCoupActu(5);
			}
		});
		j1_cases.add(j5);
		
		JPanel j1_score = new JPanel();
		j1.add(j1_score);
		j1_score.setLayout(new GridLayout(1, 6, 10, 0));
		
		JPanel j1_info = new JPanel();
		j1.add(j1_info);
	}
}
