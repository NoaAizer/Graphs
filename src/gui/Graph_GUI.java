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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import dataStructure.node_data;


public class Graph_GUI extends JFrame implements ActionListener, MouseListener{

	LinkedList<Point3D> points = new LinkedList<Point3D>();

	public Graph_GUI() {
//		Dimension d= new Dimension(900,900);
//		this.setSize(d);
		
		this.setBounds(100, 200, 900, 900);
		this.setLayout(new FlowLayout()); // simtri
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setTitle("Standard Draw");
		
		MenuBar menuBar = new MenuBar();
		this.setMenuBar(menuBar);
		Menu file = new Menu("File");
		menuBar.add(file);
		MenuItem item1f = new MenuItem("Save_Image");
		MenuItem item2f = new MenuItem("Load_Graph");
		MenuItem item3f = new MenuItem("Sava Graph_file");
		file.add(item1f);
		file.add(item2f);
		file.add(item3f);		      
		item1f.addActionListener(this);
		item2f.addActionListener(this);
		item3f.addActionListener(this);
		

		Menu algo = new Menu("Algo");
		menuBar.add(algo);
		MenuItem item1a = new MenuItem("Is_Connected");
		MenuItem item2a = new MenuItem("shortestPathDist");
		MenuItem item3a = new MenuItem("TSP");
		algo.add(item1a);
		algo.add(item2a);
		algo.add(item3a);
		item1a.addActionListener(this);
		item2a.addActionListener(this);
		item3a.addActionListener(this);
		this.addMouseListener(this);
		
//		JMenuBar menuBar = new JMenuBar();
//		JMenu menu = new JMenu("File");
//		menuBar.add(menu);
//		JMenuItem menuItem1 = new JMenuItem(" Save...   ");
//		menuItem1.addActionListener(this);
//		menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
//				Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
//		menu.add(menuItem1);
	}
	
		public void paint(Graphics g) {
			super.paint(g);
			Point3D prev = null;
			for (Point3D p : points) {
				g.setColor(Color.BLUE);
				g.fillOval((int)p.x(), (int)p.y(), 10, 10);

				if(prev != null) { //draw between points
					g.setColor(Color.RED);
					g.drawLine((int)p.x(), (int)p.y(), (int)prev.x(), (int)prev.y());
					///str=w //location print
					g.drawString("5", (int)((p.x()+prev.x())/2), (int)((p.y()+prev.y())/2)); 
					//g.drawString(, (int)((p.x()+prev.x())/2), (int)((p.y()+prev.y())/2));
				}
				prev = p;
			}
		}
		
		private Range rangeX (LinkedList<Point3D> points) {
			double min= Double.POSITIVE_INFINITY , max=Double.NEGATIVE_INFINITY;
			for (Point3D p : points) {
				if(p.x()<min) min=p.x();
				else if(p.x()>max) max=p.x();
			}
			return new Range (min+10,max+10);
		}
		private Range rangeY (LinkedList<Point3D> points) {
			double min= Double.POSITIVE_INFINITY , max=Double.NEGATIVE_INFINITY;
			for (Point3D p : points) {
				if(p.y()<min) min=p.y();
				else if(p.y()>max) max=p.y();
			}
			return new Range (min+10,max+10);
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
			if(str.startsWith("Is_Connected")) {
//				Point3D	p1=new Point3D(-1,-1,1);
//				Point3D	p2=new Point3D(-1,1,4);
//				Point3D	p3=new Point3D(0,0,0);
//				Point3D	p4=new Point3D(8,1,-1);
//				Point3D	p5=new Point3D(-1,1,-1);
//				Point3D	p6=new Point3D(10,5,1);
//				Point3D	p7=new Point3D(9,0,1);
				Point3D p1 = new Point3D(-100,100);
				Point3D p2 = new Point3D(50,300);
				Point3D p3 = new Point3D(400,150);
				points.add(p1);
				points.add(p2);
				points.add(p3);
//				points.add(p4);
//				points.add(p5);
//				points.add(p6);
//				points.add(p7);
				repaint();
			}else if(str.equals("shortestPathDist")) {
				Range rx= rangeX(points);
				Range ry= rangeY(points);
				Point3D p1 = new Point3D(rx.get_min(),rx.get_max());
				Point3D p2 = new Point3D(rx.get_min(),rx.get_max());
				points.add(p1);
				points.add(p2);
				StdDraw.setXscale(rx.get_min(), rx.get_max());
				StdDraw.setXscale(ry.get_min(), ry.get_max());
				
			}else if(str.equals("TSP")){
				Point3D p3 = new Point3D(400,150);
				points.add(p3);
				repaint();

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
		double x = e.getX();
		double y = e.getY();
		Point3D p = new Point3D(x,y);
		points.add(p);
		repaint();		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		Graph_GUI app = new Graph_GUI();
		app.setVisible(true);
	}

}
