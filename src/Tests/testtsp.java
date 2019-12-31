package Tests;

import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;

public class testtsp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		graph g=new DGraph();
		Graph_Algo ga=new Graph_Algo();
		ga.init(g);

		Point3D p1,p2,p3,p4,p5,p6,p7;
		node_data n1,n2,n3,n4,n5,n6,n7;


		p1=new Point3D(-10,-10,0);
		p2=new Point3D(-10,10,0);
		p3=new Point3D(40,0,0);
		p4=new Point3D(80,10,0);
		p5=new Point3D(80,-10,0);
		p6=new Point3D(90,30,0);
		p7=new Point3D(100,0,0);



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
		List<Integer> checkList=new ArrayList <Integer>();
		checkList.add(1);
		checkList.add(4);
		checkList.add(7);
		List<node_data> path=new ArrayList  <node_data>();
		List<node_data> path2=new ArrayList  <node_data>();
		List<Integer> pathKeys=new ArrayList <Integer>();
		path=ga.TSP(checkList);
		for(int i=0;i<path.size();i++)
		{
			pathKeys.add(path.get(i).getKey());
		}
System.out.println("path keys:"+pathKeys);
		ga.getG().removeEdge(2,1);
		ga.getG().removeEdge(5,1);
		checkList.add(1);
		checkList.add(4);
		checkList.add(7);
		path2=ga.TSP(checkList);
		System.out.println(path2);
		System.out.println(pathKeys);
	}

}
