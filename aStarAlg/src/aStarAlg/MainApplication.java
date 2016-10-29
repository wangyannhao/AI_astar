package aStarAlg;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JSeparator;

public class MainApplication {

	private JFrame frame;
	private JPanel mapPanel;
	private JPanel InfoPanel;
	public DrawPath_sstartwoqueues up;
	public DrawPath_astar ap;
	public DrawMap dmp;
	public DrawPath_astarweighted awp;
	public DrawPath_Sequential ass;

	private Boolean ShowProcess_pressed = false;
	private int mapAmount = 0;
	private int goalAmount = 0;
	public Map map = new Map();
	public  Point clickedPoint ;
	public List<javax.swing.JComponent> layer =  new ArrayList<javax.swing.JComponent>();
	public  Boolean ButtonPressed[] = new Boolean[3];
	private JTextField Map_Paths;
	private JTextField weightText;
	private JTextField txtW;
	//map.Read_map("test.txt");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApplication window = new MainApplication();
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
	public MainApplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(160*5+222,128*5);

		frame.setResizable(false);
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		FlowLayout a = new FlowLayout(2);
		a.setAlignment(FlowLayout.LEFT);
		a.setHgap(5);
		a.setVgap(0);
		frame.getContentPane().setLayout(a);

		//frame.getContentPane().setSize(new Dimension(160*4,120*4));

		mapPanel = new JPanel();

		//mapPanel.setBackground(Color.GREEN);
		mapPanel.setPreferredSize(new Dimension(160*5,128*5));
		mapPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		InfoPanel = new JPanel();
		//InfoPanel.setBackground(Color.YELLOW);

		InfoPanel.setPreferredSize(new Dimension(40*5,128*5));
		frame.getContentPane().add(mapPanel);
		frame.getContentPane().add(InfoPanel);
		InfoPanel.setLayout(new GridLayout(0,1, 0, 0));
		final JFrame CreateMaps = new JFrame();

		Map_Paths = new JTextField();
		Map_Paths.setText("1_1.txt");
		InfoPanel.add(Map_Paths);
		Map_Paths.setColumns(10);
		JButton btnReadMaps = new JButton("Read Map");
		InfoPanel.add(btnReadMaps);
		btnReadMaps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				map.Read_map(Map_Paths.getText());
				if(layer.size()!=0) {
					mapPanel.remove(layer.get(layer.size()-1));
				}
				dmp = new DrawMap (map);
				layer.add(dmp);
				mapPanel.add(layer.get(layer.size()-1));
				mapPanel.validate();
				mapPanel.repaint();
			}
		});
		JButton btnCreateMaps = new JButton("Create Maps");
		InfoPanel.add(btnCreateMaps);
		btnCreateMaps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String createOption = JOptionPane.showInputDialog(CreateMaps,
						"How many maps and start & goal positions (in m,n form)?", null);
				String[] ar = createOption.split(",");
				mapAmount = Integer.parseInt(ar[0]);
				goalAmount = Integer.parseInt(ar[1]);
				// read 8 centers of the hard to traverse regions and size of map
				//System.out.println(mapAmount);
				//List<Map> maplist = new ArrayList<Map>();
				for (int i = 0; i<mapAmount;i++){
					Map map_tmp = new Map();
					map_tmp.generateMap();
					for (int j =0;j<goalAmount;j++){
						map_tmp.generateStartandGoal();
						map_tmp.Produce_map(String.valueOf(i+1)+"_"+String.valueOf(j+1)+".txt");
					}
				}
				if(mapAmount!=0&&goalAmount!=0) {
					Frame popup = new Frame();
					JOptionPane.showMessageDialog(popup, "Maps Created!");
				}



			}});
		JSeparator separator = new JSeparator();
		separator.setToolTipText("Algorithms");
		separator.setForeground(Color.LIGHT_GRAY);
		InfoPanel.add(separator);

		JButton ShowProcess = new JButton("Show Search Process: Off");

		ShowProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( ShowProcess_pressed == false){
					ShowProcess.setText("Show Search Process: On");
					ShowProcess_pressed = true;
				}else{
					ShowProcess.setText("Show Search Process: Off");
					ShowProcess_pressed = false;
				}

			}
		});

		InfoPanel.add(ShowProcess);
		JButton btnAStarAlgorithm = new JButton("A* Sequential");
		btnAStarAlgorithm.setSize(200, 10);
		InfoPanel.add(btnAStarAlgorithm);

		btnAStarAlgorithm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonPressed[1]=false;
				ButtonPressed[2]=false;
				ButtonPressed[0]=true;
				ass =new DrawPath_Sequential(map,ShowProcess_pressed,Double.parseDouble(txtW.getText()),Double.parseDouble(weightText.getText()));
				
				mapPanel.remove(layer.get(layer.size()-1));
				mapPanel.validate();
				layer.add(ass);
				mapPanel.add(layer.get(layer.size()-1));
				mapPanel.validate();
				mapPanel.repaint();
			}
		});

		JButton btnUniformCostAlg = new JButton("A* Two Queues");
		btnUniformCostAlg.setSize(200, 10);
		InfoPanel.add(btnUniformCostAlg);
		btnUniformCostAlg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonPressed[1]=true;
				ButtonPressed[2]=false;
				ButtonPressed[0]=false;
				up = new DrawPath_sstartwoqueues(map,ShowProcess_pressed,Double.parseDouble(txtW.getText()),Double.parseDouble(weightText.getText()));
				mapPanel.remove(layer.get(layer.size()-1));
				mapPanel.validate();
				layer.add(up);
				mapPanel.add(layer.get(layer.size()-1));
				mapPanel.validate();
				mapPanel.repaint();
			}
		});
		
		txtW = new JTextField();
		InfoPanel.add(txtW);
		txtW.setColumns(10);

		weightText = new JTextField();
		InfoPanel.add(weightText);
		weightText.setColumns(10);


		JLabel label_7 = new JLabel("");
		InfoPanel.add(label_7);
		//InfoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

//
//		clickedPoint = new Point(0,0);
//		MouseListener mouse = new MyMouseListener() ;
//		mapPanel.addMouseListener(mouse);

	}

//	private class MyMouseListener implements MouseListener{
//
//		@Override
//
//		public void mouseClicked(MouseEvent e) {
//			// TODO Auto-generated method stub
//			clickedPoint.x = e.getX()/5;
//			clickedPoint.y = e.getY()/5-1;
//			if(ButtonPressed[0]){
//				for (int i = 0; i < ass.path.size();i++){
//					if (ass.path.get(i).getx()==clickedPoint.x&&ass.path.get(i).gety()==clickedPoint.y ){
//						DecimalFormat df  = new DecimalFormat("###.000");
//						show_gCost.setText("gCost: "+df.format(ass.path.get(i).getgCost(0)));
//						show_fCost.setText(" fCost: " +df.format(ass.path.get(i).getKey(0)));
//						show_hCost.setText("hCost: "+df.format(ass.path.get(i).gethCost(0)));
//					}
//				}
//			}
//			if(ButtonPressed[2]){
//				for (int i = 0; i < awp.path.size();i++){
//					if (awp.path.get(i).getx()==clickedPoint.x&&awp.path.get(i).gety()==clickedPoint.y ){
//						DecimalFormat df  = new DecimalFormat("###.000");
//						show_gCost.setText("gCost: "+df.format(awp.path.get(i).gCost));
//						show_fCost.setText(" fCost: " +df.format(awp.path.get(i).fCost));
//						show_hCost.setText("hCost: "+df.format(awp.path.get(i).hCost));
//					}
//				}
//			}
//			if(ButtonPressed[1]){
//				for (int i = 0; i < up.path.size();i++){
//					if (up.path.get(i).getx()==clickedPoint.x&&up.path.get(i).gety()==clickedPoint.y ){
//						DecimalFormat df  = new DecimalFormat("###.000");
//						show_gCost.setText("gCost: "+df.format(up.path.get(i).gCost));
//						show_fCost.setText(" fCost: " +df.format(up.path.get(i).fCost));
//						show_hCost.setText("No h Cost");
//					}
//				}
//			}
//		}
//
//		@Override
//		public void mouseEntered(MouseEvent e) {
//			// TODO Auto-generated method stub
//
//		}
//
//		@Override
//		public void mouseExited(MouseEvent e) {
//			// TODO Auto-generated method stub
//
//		}
//
//		@Override
//		public void mousePressed(MouseEvent e) {
//			// TODO Auto-generated method stub
//
//		}
//
//		@Override
//		public void mouseReleased(MouseEvent e) {
//			// TODO Auto-generated method stub
//
//		}

	//}
}
