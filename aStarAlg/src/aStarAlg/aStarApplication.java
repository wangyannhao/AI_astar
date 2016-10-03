package aStarAlg;

import java.awt.EventQueue;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.CardLayout;

public class aStarApplication {

	private JFrame frame;
	public Screen s;
	public Screen p;
	public DrawMap d;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					aStarApplication window = new aStarApplication();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public aStarApplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(160*5,120*5);
		frame.setResizable(false);
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		CardLayout a = new CardLayout(0, 0);
		frame.getContentPane().setLayout(a);
		
		JButton btnCreateMaps = new JButton("Create Maps");
		btnCreateMaps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel();
				s = new Screen();
				frame.getContentPane().add(s);
				frame.getContentPane().validate();
				frame.getContentPane().repaint();
				
				System.out.println(frame.getContentPane().size());
			}
		});
		menuBar.add(btnCreateMaps);
		JButton btnReadMaps = new JButton("Read Maps");
		btnReadMaps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d = new DrawMap();
				//((LayoutManager) frame.getContentPane()).removeLayoutComponent(s);
				frame.getContentPane().add(d);
				frame.getContentPane().validate();
				frame.getContentPane().repaint();
			}
		});

		menuBar.add(btnReadMaps);
		
		JButton btnUniformCostAlg = new JButton("Uniform Cost Alg");
		menuBar.add(btnUniformCostAlg);
		
		JButton btnAStarAlgorithm = new JButton("A Star Algorithm");
		menuBar.add(btnAStarAlgorithm);
		
		JButton btnAStarWeighted = new JButton("A Star Weighted");
		menuBar.add(btnAStarWeighted);


		btnAStarWeighted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAStarAlgorithm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
	}
	

}
