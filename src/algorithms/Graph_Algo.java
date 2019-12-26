package algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import dataStructure.*;

/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{
	private graph g;

	@Override
	public void init(graph g) {
		this.g=g;
	}

	@Override
	public void init(String file_name) {
		// TODO Auto-generated method stub
		Graph_Algo g= new Graph_Algo();
		try
		{    
			FileInputStream file = new FileInputStream(file_name); 
			ObjectInputStream in = new ObjectInputStream(file); 

			g = (Graph_Algo) in.readObject(); 

			in.close(); 
			file.close(); 

			System.out.println(g);
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


	@Override
	public void save(String file_name) {  
		Graph_Algo g = new Graph_Algo();
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

	@Override
	public boolean isConnected() {
		boolean result=runDFS(g);
		if(!result) return false;//DFS traversal doesn't visit all nodes.
		graph tran_g=getTranspose();//Create a reversed graph 
		result=runDFS(tran_g);//Do DFS for reversed graph starting from the same node as before.
		return result;
	}
	/**
	 * Runs DFS algorithm on the given graph.
	 * @param g represents the graph to be traveled.
	 * @return true- if all nodes are connected , otherwise false.
	 */
	private boolean runDFS(graph g) {
		boolean flag_first=true;
		//Marks all the nodes as not visited 
		for(Iterator<node_data> it=g.getV().iterator();it.hasNext();) {
			it.next().setTag(0);
		}
		//Do DFS traversal starting from first node. 
		for(Iterator<node_data> it=g.getV().iterator();it.hasNext()&&flag_first;) {
			node_data v=it.next();
			flag_first=false;
			DFSUtil(v.getKey());
		}
		// If DFS traversal doesn't visit all nodes, then return false.
		for(Iterator<node_data> it=g.getV().iterator();it.hasNext();) {
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
	private void DFSUtil(int key) 
	{ 
		// Mark the current node as visited
		g.getNode(key).setTag(1); 

		// Recur for all the nodes adjacent to this node
		for(Iterator<edge_data> edgeIt=g.getE(key).iterator();edgeIt.hasNext();)
		{
			edge_data e=edgeIt.next();
			node_data e_dest=g.getNode(e.getDest());
			if(e_dest.getTag()==0)
				DFSUtil(e_dest.getKey());	
		}
	}
	/**
	 * Transposes the original graph.
	 * @return transpose of this graph 
	 */
	private graph getTranspose() 
	{ 
		graph tran_g = new DGraph(); 

		for(Iterator<node_data> it=g.getV().iterator();it.hasNext();) {
			node_data v=it.next();
			for(Iterator<edge_data> edgeIt=g.getE(v.getKey()).iterator();edgeIt.hasNext();) {
				edge_data e=edgeIt.next();
				node_data e_dest=g.getNode(e.getDest());
				tran_g.connect(e_dest.getKey(), v.getKey(), e.getWeight());
			}
		}
		return tran_g; 
	} 

	@Override
	public double shortestPathDist(int src, int dest) {
		for(Iterator<node_data> it=g.getV().iterator();it.hasNext();) {
			it.next().setWeight(Double.POSITIVE_INFINITY);
			it.next().setInfo("");
			it.next().setTag(0);
		}
		g.getNode(src).setWeight(0);
		PriorityQueue<node_data> Q = new PriorityQueue<node_data>();
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
		return g.getNode(dest).getWeight();
	}
	/**
	 * Finds the node with the minimum weight and removes it from the queue.
	 * @param q represents the queue with the unvisited nodes.
	 * @return the node with the minimum weight
	 */
	private int findMinNode(PriorityQueue<node_data> q) {
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

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {
		Graph_Algo g_copy=new Graph_Algo();
		this.save("graph");
		g_copy.init("graph");
		return g_copy.g;
	}

}
