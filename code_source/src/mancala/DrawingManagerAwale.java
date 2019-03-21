package mancala;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class DrawingManagerAwale {

	private JFrame frmAwale;
	private int coupActu = -1;
	private ArrayList<JButton> buttonList;
	// private JButton j11;
	// private JButton j10;
	// private JButton j9;
	// private JButton j8;
	// private JButton j7;
	// private JButton j6;
	// private JButton j5;
	// private JButton j4;
	// private JButton j3;
	// private JButton j2;
	// private JButton j1;
	// private JButton c0;
	private GameManagerAwale arbitreAwale;

	public ArrayList<JButton> getButtonList() {
		return buttonList;
	}

	public GameManagerAwale getArbitreAwale() {
		return arbitreAwale;
	}

	public JButton getJ(int i) {
		return buttonList.get(i);
	}

	public JButton getJ11() {
		// return j11;
		return buttonList.get(11);
	}

// 	public void setJ11(JButton j11) {
// 		this.j11 = j11;
// 	}

	public JButton getJ10() {
		return buttonList.get(10);
	}

	// public void setJ10(JButton j10) {
	// 	this.j10 = j10;
	// }

	public JButton getJ9() {
		return buttonList.get(9);
	}

	// public void setJ9(JButton j9) {
	// 	this.j9 = j9;
	// }

	public JButton getJ8() {
		return buttonList.get(8);
	}

	// public void setJ8(JButton j8) {
	// 	this.j8 = j8;
	// }

	public JButton getJ7() {
		return buttonList.get(7);
	}

	// public void setJ7(JButton j7) {
	// 	this.j7 = j7;
	// }

	public JButton getJ6() {
		return buttonList.get(6);
	}

	// public void setJ6(JButton j6) {
	// 	this.j6 = j6;
	// }

	public JButton getJ5() {
		return buttonList.get(5);
	}

// 	public void setJ5(JButton j5) {
// 		this.j5 = j5;
// 	}

	public JButton getJ4() {
		return buttonList.get(4);
	}

	// public void setJ4(JButton j4) {
	// 	this.j4 = j4;
	// }

	public JButton getJ3() {
		return buttonList.get(3);
	}

	// public void setJ3(JButton j3) {
	// 	this.j3 = j3;
	// }

	public JButton getJ2() {
		return buttonList.get(2);
	}

	// public void setJ2(JButton j2) {
	// 	this.j2 = j2;
	// }

	public JButton getJ1() {
		return buttonList.get(1);
	}

	// public void setJ1(JButton j1) {
	// 	this.j1 = j1;
	// }

	// public JButton getC0() {
	// 	return c0;
	// }

	public JButton getC0() {
		return buttonList.get(0);
	}

	// public void setC0(JButton c0) {
	// 	this.c0 = c0;
	// }

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

		// j11
		buttonList = new ArrayList<>();
		for(int i = 0; i < 12; i++)
		{
			JButton btn = new JButton("4");
			final int fi = i;
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setCoupActu(fi);
				}
			});
			buttonList.add(btn);
		}


//       	// j11 = new JButton("4");
// 		j11.addActionListener(new ActionListener() {
// 			public void actionPerformed(ActionEvent arg0) {
// 				setCoupActu(11);
// 			}
// 		});

//       	// j10
// 		j10 = new JButton("4");
// 		j10.addActionListener(new ActionListener() {
// 			public void actionPerformed(ActionEvent arg0) {
// 				setCoupActu(10);
// 			}
// 		});

//       	// J9
// 		j9 = new JButton("4");
// 		j9.addActionListener(new ActionListener() {
// 			public void actionPerformed(ActionEvent arg0) {
// 				setCoupActu(9);
// 			}
// 		});

//       	// J8
// 		j8 = new JButton("4");
// 		j8.addActionListener(new ActionListener() {
// 			public void actionPerformed(ActionEvent arg0) {
// 				setCoupActu(8);
// 			}
// 		});

//       	// J7
// 		j7 = new JButton("4");
// 		j7.addActionListener(new ActionListener() {
// 			public void actionPerformed(ActionEvent arg0) {
// 				setCoupActu(7);
// 			}
// 		});

//       	// J6
// 		j6 = new JButton("4");
// 		j6.addActionListener(new ActionListener() {
// 			public void actionPerformed(ActionEvent arg0) {
// 				setCoupActu(6);
// 			}
// 		});

		JPanel j1 = new JPanel();
		game.add(j1);
		j1.setLayout(new GridLayout(0, 1, 0, 5));

		JPanel j1_cases = new JPanel();
		j1.add(j1_cases);
		j1_cases.setLayout(new GridLayout(1, 6, 10, 0));

		// // J0
		// c0 = new JButton("4");
		// c0.addActionListener(new ActionListener() {
		// 	public void actionPerformed(ActionEvent arg0) {
		// 		setCoupActu(0);
		// 	}
		// });

//       	// J1
// 		this.j1 = new JButton("4");
// 		this.j1.addActionListener(new ActionListener() {
// 			public void actionPerformed(ActionEvent arg0) {
// 				setCoupActu(1);
// 			}
// 		});

//       	// J2
// 		this.j2 = new JButton("4");
// 		this.j2.addActionListener(new ActionListener() {
// 			public void actionPerformed(ActionEvent arg0) {
// 				setCoupActu(2);
// 			}
// 		});

//       	// J3
// 		j3 = new JButton("4");
// 		j3.addActionListener(new ActionListener() {
// 			public void actionPerformed(ActionEvent arg0) {
// 				setCoupActu(3);
// 			}
// 		});

//       	// J4
// 		j4 = new JButton("4");
// 		j4.addActionListener(new ActionListener() {
// 			public void actionPerformed(ActionEvent arg0) {
// 				setCoupActu(4);
// 			}
// 		});

//       	// J5
// 		j5 = new JButton("4");
// 		j5.addActionListener(new ActionListener() {
// 			public void actionPerformed(ActionEvent arg0) {
// 				setCoupActu(5);
// 			}
// 		});


		// Ajouter cases J2
		for(int i = 11; i > 5; i--)
		{
			j2_cases.add(buttonList.get(i));
		}
		// j2_cases.add(j11);
		// j2_cases.add(j10);
		// j2_cases.add(j9);
		// j2_cases.add(j8);
		// j2_cases.add(j7);
		// j2_cases.add(j6);

		// Ajouter cases J1
		for(int i = 0; i < 6; i++)
		{
			j1_cases.add(buttonList.get(i));
		}
		// j1_cases.add(this.j1);
		// j1_cases.add(this.j2);
		// j1_cases.add(j3);
		// j1_cases.add(j4);
		// j1_cases.add(j5);


		JPanel j1_score = new JPanel();
		j1.add(j1_score);
		j1_score.setLayout(new GridLayout(1, 6, 10, 0));

		JPanel j1_info = new JPanel();
		j1.add(j1_info);

	}
}
