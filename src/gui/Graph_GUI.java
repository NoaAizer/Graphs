package gui;

import dataStructure.*;
import utils.*;

import java.awt.Color;
import java.awt.Font;

import java.util.Collection;
import java.util.Iterator;


public class Graph_GUI {
	graph g;

	public void init (graph _g) {
		this.g=_g;
		StdDraw.initGraph(g);
	}
	public void initSize() {
		StdDraw.setCanvasSize(500,500);
		Range rx= rangeX(g.getV());
		Range ry= rangeY(g.getV());
		double limx=(rx.get_max()-rx.get_min())*0.1;
		double limy=(ry.get_max()-ry.get_min())*0.1;
		StdDraw.setXscale(rx.get_min()-limx, rx.get_max()+limx);
		StdDraw.setYscale(ry.get_min()-limy, ry.get_max()+limy);
	}

	public void drawGraph() {
		initSize();
		StdDraw.setFont(new Font("Calibri", Font.CENTER_BASELINE, 16));
		Range ry= rangeY(g.getV());
		for (node_data n : g.getV() ){
			Point3D src=n.getLocation();
			StdDraw.setPenRadius(0.02);
			StdDraw.setPenColor(Color.BLUE);
			StdDraw.point(src.x(), src.y());
			StdDraw.text(src.x(), src.y()+0.03*ry.get_length(), "" + n.getKey());
			if (g.getE(n.getKey()) != null) {
				for(Iterator<edge_data> edgeIt=g.getE(n.getKey()).iterator();edgeIt.hasNext();) {
					edge_data e=edgeIt.next();
					Point3D dest=g.getNode(e.getDest()).getLocation();

					//						StdDraw.filledCircle(dest.x(), dest.y(), 0.06);
					//						StdDraw.text(dest.x(), dest.y() + 0.1, "" + g.getNode(e.getDest()).getKey());
					StdDraw.setPenColor(Color.RED);
					StdDraw.setPenRadius(0.006);
					StdDraw.text((src.x()*0.2 +dest.x()*0.8), (src.y()*0.2+dest.y()*0.8)+0.1, "" + e.getWeight());
					StdDraw.setPenColor(Color.BLACK);
					StdDraw.setPenRadius();
					StdDraw.line(src.x(), src.y(), dest.x(), dest.y());
					StdDraw.setPenColor(Color.GREEN);
					StdDraw.setPenRadius(0.02);
					StdDraw.point((src.x()*0.1 +dest.x()*0.9),(src.y()*0.1 +dest.y()*0.9));
					//						StdDraw.filledCircle((src.x()*0.1 +dest.x()*0.9), (src.y()*0.1 +dest.y()*0.9),0.06);

				}

			}
		}
	}
	public void update() {
		StdDraw.clear();
		this.drawGraph();

	}
	private Range rangeX (Collection<node_data> nodes) {
		double min= Double.POSITIVE_INFINITY , max=Double.NEGATIVE_INFINITY;
		for (node_data n: nodes) {
			if(n.getLocation().x()<min) min=n.getLocation().x();
			else if(n.getLocation().x()>max) max=n.getLocation().x();
		}
		return new Range (min,max);
	}
	private Range rangeY (Collection<node_data> nodes) {
		double min= Double.POSITIVE_INFINITY , max=Double.NEGATIVE_INFINITY;
		for (node_data n: nodes)  {
			if(n.getLocation().y()<min) min=n.getLocation().y();
			else if(n.getLocation().y()>max) max=n.getLocation().y();
		}
		return new Range (min,max);
	}
	public static void main(String[] args) {

		graph g= new DGraph();
		Point3D p1,p2,p3,p4,p5,p6,p7;
		node_data n1,n2,n3,n4,n5,n6,n7;


		p1=new Point3D(-10,-10,0);
		p2=new Point3D(-10,10,0);
		p3=new Point3D(40,0,0);
		p4=new Point3D(80,10,0);
		p5=new Point3D(80,-10,0);
		p6=new Point3D(90,30,0);
		p7=new Point3D(100,0,0);
		//		p1=new Point3D(-1,2,0);
		//		p2=new Point3D(-1,3,0);
		//		p3=new Point3D(4,2.5,0);
		//		p4=new Point3D(8,3,0);
		//		p5=new Point3D(8,2,0);
		//		p6=new Point3D(10,4,0);
		//		p7=new Point3D(9,1,0);


		n1=new Node(1,p1,0,null,0);
		n2=new Node(2,p2,0,null,0);
		n3=new Node(3,p3,0,null,0);
		n4=new Node(4,p4,0,null,0);
		n5=new Node(5,p5,0,null,0);
		n6=new Node(6,p6,0,null,0);
		n7=new Node(7,p7,0,null,0);

		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.addNode(n4);
		g.addNode(n5);
		g.addNode(n6);
		g.addNode(n7);

		g.connect(n1.getKey(), n2.getKey(), 4);
		g.connect(n2.getKey(), n1.getKey(), 4);
		g.connect(n1.getKey(), n3.getKey(), 3);
		g.connect(n2.getKey(), n4.getKey(), 5);
		g.connect(n3.getKey(), n2.getKey(), 1);
		g.connect(n3.getKey(), n5.getKey(), 8);
		g.connect(n4.getKey(), n3.getKey(), 11);
		g.connect(n4.getKey(), n6.getKey(), 2);
		g.connect(n5.getKey(), n4.getKey(), 2);
		g.connect(n5.getKey(), n1.getKey(), 7);
		g.connect(n5.getKey(), n7.getKey(), 5);
		g.connect(n6.getKey(), n7.getKey(), 3);
		g.connect(n7.getKey(), n4.getKey(), 10);
		Graph_GUI app = new Graph_GUI();
		app.init(g);
		app.drawGraph();
	}


}