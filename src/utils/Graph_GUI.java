package utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileSystemView;

import algorithms.Graph_Algo;
import algorithms.graph_algorithms;
import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;


public class Graph_GUI extends JFrame implements ActionListener, MouseListener, KeyListener{

	graph g_GUI ;
	
	public Graph_GUI() {
		
		initGUI();
	}
	
	public Graph_GUI(graph g){
		
		this.g_GUI = g;
		initGUI();
;
	}
	
	public void initGUI(){
		
		this.setBounds(100, 200, 1000, 1000);
		this.setLayout(new FlowLayout()); // simtri
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setTitle("Standard Draw");
		
		MenuBar menuBar = new MenuBar();
		this.setMenuBar(menuBar);
		Menu file = new Menu("File");
		menuBar.add(file);
		MenuItem item1f = new MenuItem("Save_Image");
		file.add(item1f);
		
		//KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK);
		//KeyStroke.getKeyStroke('a',java.awt.event.InputEvent.CTRL_MASK);
		item1f.addActionListener(this);
		MenuItem item2f = new MenuItem("Load_Graph");
		file.add(item2f);
		item2f.addActionListener(this);
		MenuItem item3f = new MenuItem("Sava Graph_file");
		file.add(item3f);		      
		item3f.addActionListener(this);
		

		Menu algo = new Menu("Algo");
		menuBar.add(algo);
		MenuItem item1a = new MenuItem("Is_Connected");
		algo.add(item1a);
		item1a.addActionListener(this);
		MenuItem item2a = new MenuItem("shortestPathDist");
		algo.add(item2a);
		item2a.addActionListener(this);
		MenuItem item3a = new MenuItem("TSP");
		algo.add(item3a);
		item3a.addActionListener(this);
		
		this.addMouseListener(this);

	}
	
		public void paint(Graphics g) {
			super.paint(g);
			Collection<node_data> n = g_GUI.getV();
			for (node_data node : n) {
				Point3D src = node.getLocation();
				g.setColor(Color.BLUE);
				g.fillOval(src.ix(), src.iy(), 10, 10);
				g.drawString(""+node.getKey(), (src.ix()+1), (src.iy()+1));
				Collection<edge_data> e = g_GUI.getE(node.getKey());
				for (edge_data edge : e) {
					g.setColor(Color.RED);
					Point3D dest = g_GUI.getNode(edge.getDest()).getLocation();
					g.drawLine(src.ix() , src.iy() , dest.ix() , dest.iy());
					g.drawString(""+edge.getWeight(), (int)((src.x()+dest.x())/2), (int)((src.y()+dest.y())/2));
					g.setColor(Color.YELLOW);
					g.fillOval((src.ix()+10), (src.iy()+10), 10, 10);
				}
			}
			
		}
	
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String str = e.getActionCommand();  ///////link to function
//			if(str.startsWith("Save_Image")) {
//				FileDialog chooser = new FileDialog(StdDraw.frame, "Use a .png or .jpg extension", FileDialog.SAVE);
//				chooser.setVisible(true);
//				String filename = chooser.getFile();
//				if (filename != null) {
//					StdDraw.save(chooser.getDirectory() + File.separator + chooser.getFile());
//				}
//			}
//			if(str.startsWith("Load")) {
//				FileDialog chooser = new FileDialog(StdDraw.frame, "Loading graph file", FileDialog.LOAD);
//				chooser.setVisible(true);
//				String filename = chooser.getFile();
//				System.out.println(filename);
//			}
			if(str.startsWith("Save_Image")) {
				graph_algorithms ga = new Graph_Algo();
				ga.init(this.g_GUI);
				JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					try {
						ga.save(fileChooser.getSelectedFile() + ".txt");
					} catch (Exception ee) {
						ee.getMessage();
					}
				}
			}
			if(str.startsWith("Load")) {
				graph_algorithms ga = new Graph_Algo();
				ga.init(this.g_GUI);
				JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					try {
						File file = fileChooser.getSelectedFile();
					//	ga.init(null);           //////////write - SelectedFile.getAbsolutePath()//////////
						this.g_GUI = ga.copy();
					} catch (Exception ee) {
						ee.getMessage();
					}
				}
			}
			if(str.startsWith("Is_Connected")) {
				graph_algorithms ga = new Graph_Algo();
				ga.init(this.g_GUI);
				boolean result = ga.isConnected();
				if(result) {
					JOptionPane.showMessageDialog(null, "it's connected");
				}else {
					JOptionPane.showMessageDialog(null, "it's not connected");
				}
				
//				Point3D	p1=new Point3D(-1,-1,1);
//				Point3D	p2=new Point3D(-1,1,4);
//				Point3D	p3=new Point3D(0,0,0);
//				Point3D	p4=new Point3D(8,1,-1);
//				Point3D	p5=new Point3D(-1,1,-1);
//				Point3D	p6=new Point3D(10,5,1);
//				Point3D	p7=new Point3D(9,0,1);
			
//				points.add(p1);
//				points.add(p2);
//				points.add(p3);
//				points.add(p4);
//				points.add(p5);
//				points.add(p6);
//				points.add(p7);
				repaint();
			}else if(str.equals("shortestPathDist")) {
				graph_algorithms ga = new Graph_Algo();
				ga.init(this.g_GUI);
				String src = JOptionPane.showInputDialog("Please input a src point");
				String dest = JOptionPane.showInputDialog("Please input a dest point");
				double result = ga.shortestPathDist(Integer.parseInt(src), Integer.parseInt(dest));
				if(result > 0){
					JOptionPane.showMessageDialog(null, "The shortest path dist is:",""+result,JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,  "The point's is'nt exist","null",JOptionPane.INFORMATION_MESSAGE);
				}
			}else if(str.equals("TSP")){


			}
			
			
			
		}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
//		double x = e.getX();
//		double y = e.getY();                  ////I draw
//		Point3D p = new Point3D(x,y);
//		points.add(p);
//		repaint();		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		graph g=new DGraph();
//		Point3D p1 = new Point3D(-100,100);
//		Point3D p2 = new Point3D(50,300);
//		Point3D p3 = new Point3D(400,150);
//	node_data	n1=new Node(1,p1,0,null,0);
//	node_data n2=new Node(2,p2,0,null,0);
//	node_data	n3=new Node(3,p3,0,null,0);
//		
//		g.addNode(n1);
//		g.addNode(n2);
//		g.addNode(n3);
		int j=2;
		for (int i=100;i<1000;i=i+50,j=j+50)
		{
			Point3D Location = new Point3D(i,j);
			node_data node=new Node(i,Location);
			g.addNode(node);
		}
		Collection<node_data> s = g.getV();
		for (node_data node1 : s) 
		{
			for (node_data node2 : s) 
			{
				if(node1.getKey()!=node2.getKey())
					g.connect(node1.getKey(), node2.getKey(), Double.MAX_VALUE);
			}
		}
		Graph_GUI app = new Graph_GUI();
		app.setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
