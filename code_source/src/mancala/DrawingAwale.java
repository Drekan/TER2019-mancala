package mancala;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DrawingAwale {

	private JFrame frmAwale;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DrawingAwale window = new DrawingAwale();
					window.frmAwale.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DrawingAwale() {
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
		
		JLabel titre = new JLabel("Bienvenue");
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
		
		JButton j2_c6 = new JButton("4");
		j2_c6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		j2_cases.add(j2_c6);
		
		JButton j2_c5 = new JButton("4");
		j2_cases.add(j2_c5);
		
		JButton j2_c4 = new JButton("4");
		j2_cases.add(j2_c4);
		
		JButton j2_c3 = new JButton("4");
		j2_cases.add(j2_c3);
		
		JButton j2_c2 = new JButton("4");
		j2_cases.add(j2_c2);
		
		JButton j2_c1 = new JButton("4");
		j2_cases.add(j2_c1);
		
		JPanel j1 = new JPanel();
		game.add(j1);
		j1.setLayout(new GridLayout(0, 1, 0, 5));
		
		JPanel j1_cases = new JPanel();
		j1.add(j1_cases);
		j1_cases.setLayout(new GridLayout(1, 6, 10, 0));
		
		JButton j1_c1 = new JButton("4");
		j1_cases.add(j1_c1);
		
		JButton j1_c2 = new JButton("4");
		j1_cases.add(j1_c2);
		
		JButton j1_c3 = new JButton("4");
		j1_cases.add(j1_c3);
		
		JButton j1_c4 = new JButton("4");
		j1_cases.add(j1_c4);
		
		JButton j1_c5 = new JButton("4");
		j1_cases.add(j1_c5);
		
		JButton j1_c6 = new JButton("4");
		j1_cases.add(j1_c6);
		
		JPanel j1_score = new JPanel();
		j1.add(j1_score);
		j1_score.setLayout(new GridLayout(1, 6, 10, 0));
		
		JPanel j1_info = new JPanel();
		j1.add(j1_info);
		
	}

}
