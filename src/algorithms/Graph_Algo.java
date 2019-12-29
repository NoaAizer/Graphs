package algorithms;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import dataStructure.*;
import gui.Graph_GUI;
import utils.Point3D;

/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{
	private graph g;

	/**
	 * Init this set of algorithms on the parameter - graph.
	 * @param g is the given graph
	 */
	@Override
	public void init(graph g) {
		this.g=g;
	}
	/**
	 * Init a graph from file
	 * @param file_name represents the graph file.
	 */
	@Override
	public void init(String file_name) 
	{
		try
		{    
			FileInputStream file = new FileInputStream(file_name); 
			ObjectInputStream in = new ObjectInputStream(file); 
			g = (graph)in.readObject(); 
			in.close(); 
			file.close(); 
		} 
		catch(IOException ex) 
		{ 
			System.out.println("IOException is caught"); 
		} 
		catch(ClassNotFoundException ex) 
		{ 
			System.out.println("ClassNotFoundException is caught"); 
		} 
	}
	/** Saves the graph to a file.
	 * 
	 * @param file_name represents the name of the new file.
	 */
	@Override
	public void save(String file_name) 
	{
		try
		{    
			FileOutputStream file = new FileOutputStream(file_name); 
			ObjectOutputStream out = new ObjectOutputStream(file); 
			out.writeObject(g); 
			out.close(); 
			file.close(); 
		}   
		catch(IOException ex) 
		{ 
			System.out.println("IOException is caught"); 
		} 
	}
	/**
	 * Returns true if and only if (iff) there is a valid path from EVREY node to each
	 * other node. NOTE: assume directional graph - a valid path (a-->b) does NOT imply a valid path (b-->a).
	 * @return TRUE- if there a valid path from Every node to each, otherwise return FALSE.
	 */
	@Override
	public boolean isConnected() {
		if(g.getV()==null)return false;//there are no nodes
		boolean result=runDFS(g);
		if(!result) return false;//DFS traversal doesn't visit all nodes.
		graph tran_g=getTranspose(g);//Create a reversed graph 
		result=runDFS(tran_g);//Do DFS for reversed graph starting from the same node as before.
		return result;
	}
	/**
	 * returns the length of the shortest path between src to dest
	 * @param src - start node
	 * @param dest - end (target) node
	 * @return
	 */
	@Override
	public double shortestPathDist(int src, int dest) {
		try {
			for(Iterator<node_data> it=g.getV().iterator();it.hasNext();) {
				node_data n=it.next();
				n.setWeight(Double.POSITIVE_INFINITY);
				n.setInfo("");
				n.setTag(0);
			}
			g.getNode(src).setWeight(0);
			Queue<node_data> Q = new LinkedList<node_data>();
			Q.add(g.getNode(src));
			for(Iterator<node_data> it=g.getV().iterator();it.hasNext();)
			{ 
				node_data v=it.next();
				if(v.getKey()!=src)
					Q.add(v);
			}
			while(Q.size()!=0)
			{
				int u=findMinNode(Q);
				g.getNode(u).setTag(1);
				for(Iterator<edge_data> edgeIt=g.getE(u).iterator();edgeIt.hasNext();)
				{
					edge_data e=edgeIt.next();
					node_data e_dest=g.getNode(e.getDest());
					if(e_dest.getTag()==0&&e_dest.getWeight()>g.getNode(u).getWeight()+e.getWeight())
					{
						e_dest.setWeight(g.getNode(u).getWeight()+e.getWeight());
						e_dest.setInfo(""+u);
					}
				}	
			}
			for(Iterator<node_data> nodeIt=g.getV().iterator();nodeIt.hasNext();) {
				nodeIt.next().setTag(0);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return g.getNode(dest).getWeight();
	}
	

	/**
	 * returns the the shortest path between src to dest - as an ordered List of nodes:
	 * src--> n1-->n2-->...dest
	 * see: https://en.wikipedia.org/wiki/Shortest_path_problem
	 * @param src - start node
	 * @param dest - end (target) node
	 * @return
	 */
	@Override
	public List<node_data> shortestPath(int src, int dest) {
		List<node_data> path=new ArrayList <node_data>();
		double dist=shortestPathDist(src, dest);
		if(dist<Double.POSITIVE_INFINITY)// there is a path
		{
			node_data v= g.getNode(dest);
			path.add(v);
			while(!v.getInfo().isEmpty())
			{
				int prev_node=Integer.parseInt(v.getInfo());
				path.add(g.getNode(prev_node));
				v.setInfo("");
				v=g.getNode(prev_node);
			}
			List<node_data> reversed_path=new ArrayList <node_data>();
			while(path.size()!=0)
				reversed_path.add(path.remove(path.size()-1));
			return reversed_path;
		}
		return null;
	}
	/**
	 * computes a relatively short path which visit each node in the targets List.
	 * Note: this is NOT the classical traveling salesman problem, 
	 * as you can visit a node more than once, and there is no need to return to source node - 
	 * just a simple path going over all nodes in the list. 
	 * @param targets
	 * @return
	 */
	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}
	/** 
	 * Compute a deep copy of this graph.
	 * @return the new copied graph.
	 */
	@Override
	public graph copy() {
		graph g_copy= new DGraph();
		for (node_data n : g.getV()) {
			g_copy.addNode(n);
		}
		for (node_data n : g.getV()) {
			g_copy.addNode(n);
			for (edge_data e : g.getE(n.getKey())) 
				g_copy.connect(e.getSrc(),e.getDest(),e.getWeight());
		}
		return g_copy;
	}
	/**
	 * Graph getter.
	 * @return the graph in this class.
	 */
	public graph getG() {
		return g;
	}

	/*****************Private Methods********************/
	/**
	 * Runs DFS algorithm on the given graph.
	 * @param g represents the graph to be traveled.
	 * @return true- if all nodes are connected , otherwise false.
	 */
	private boolean runDFS(graph gr) {
		boolean flag_first=true;
		//Marks all the nodes as not visited 
		for(Iterator<node_data> it=gr.getV().iterator();it.hasNext();) {
			it.next().setTag(0);
		}
		//Do DFS traversal starting from first node. 
		for(Iterator<node_data> it=gr.getV().iterator();it.hasNext()&&flag_first;) {
			node_data v=it.next();
			flag_first=false;
			DFSUtil(v.getKey(),gr);
		}
		// If DFS traversal doesn't visit all nodes, then return false.
		for(Iterator<node_data> it=gr.getV().iterator();it.hasNext();) {
			node_data v=it.next();
			if(g.getNode(v.getKey()).getTag()==0)
				return false;
		}
		return true;
	}

	/**
	 * Runs DFS starting from the first node.
	 * @param key represents the first node.
	 */
	private void DFSUtil(int key,graph gr) 
	{ 

		// Mark the current node as visited
		gr.getNode(key).setTag(1); 

		if(gr.getE(key)==null)return;//there are no edges
		// Recur for all the nodes adjacent to this node
		for(Iterator<edge_data> edgeIt=gr.getE(key).iterator();edgeIt.hasNext();)
		{
			edge_data e=edgeIt.next();
			node_data e_dest=gr.getNode(e.getDest());
			if(e_dest.getTag()==0)
				DFSUtil(e_dest.getKey(),gr);	
		}
	}
	/**
	 * Transposes the original graph.
	 * @return transpose of this graph 
	 */
	private graph getTranspose(graph gr) 
	{ 
		graph tran_g = new DGraph(); 

		//Add all the nodes.
		for(Iterator<node_data> it=gr.getV().iterator();it.hasNext();) {
			node_data v=it.next();
			tran_g.addNode(v);
		}
		//Add all the edges (but in opposite direction).
		for(Iterator<node_data> it=gr.getV().iterator();it.hasNext();) {
			node_data v=it.next();
			for(Iterator<edge_data> edgeIt=gr.getE(v.getKey()).iterator();edgeIt.hasNext();) {
				edge_data e=edgeIt.next();
				node_data e_dest=gr.getNode(e.getDest());
				tran_g.connect(e_dest.getKey(), v.getKey(), e.getWeight());
			}
		}
		return tran_g; 
	} 
	/**
	 * Finds the node with the minimum weight and removes it from the queue.
	 * @param q represents the queue with the unvisited nodes.
	 * @return the node with the minimum weight
	 */
	private int findMinNode(Queue<node_data> q) {
		double weight=Double.POSITIVE_INFINITY;
		int minNode=0;
		node_data temp=null;
		for (Iterator<node_data> it = q.iterator(); it.hasNext();) {
			node_data node=it.next();
			if(node.getWeight()<weight)
			{
				weight=node.getWeight();
				minNode=node.getKey();
				temp=node;
			}
		}
		q.remove(temp);
		return minNode;
	}

}
