package dataStructure;

import java.util.Collection;
import java.util.HashMap;

public class DGraph implements graph{

	int mc;
	int edgesCounter;
	HashMap<Integer,node_data> nodes;
	HashMap<Integer,HashMap<Integer,edge_data>> edges;

	public DGraph() {
		this.mc = 0;
		this.edgesCounter = 0;
		this.nodes = new HashMap<Integer,node_data>();
		this.edges = new HashMap<Integer,HashMap<Integer,edge_data>>();
	}

	public DGraph(DGraph g) {
		this.mc = g.getMC();
		this.edgesCounter = g.edgeSize();
		this.nodes=(HashMap<Integer, node_data>) g.getNodes();
		this.edges = (HashMap<Integer,HashMap<Integer,edge_data>>) g.getEdges();
	}

	public HashMap<Integer, node_data> getNodes() {
		return this.nodes;
	}

	public HashMap<Integer, HashMap<Integer, edge_data>> getEdges() {
		return this.edges;
	}

	@Override
	public node_data getNode(int key) {
		if(nodes.containsKey(key))
			return nodes.get(key);
		else
			return null;
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		if(edges.containsKey(src)&&edges.get(src).containsKey(dest))
			return edges.get(src).get(dest);
		else
			return null;
	}

	@Override
	public void addNode(node_data n) {
		nodes.put(n.getKey(),n);
		mc++;
	}

	@Override
	public void connect(int src, int dest, double w) {
		Edge e = new Edge(src, dest, w,null,0);//??????check????????
		if(nodes.containsKey(src)&&nodes.containsKey(dest))// the nodes are exist in the graph
		{
			if(!edges.containsKey(src)) {//if the src node is already exist
				HashMap<Integer,edge_data> newEdge= new HashMap<Integer,edge_data>();//empty hashmap
				edges.put(src, newEdge);
			}
			edges.get(src).put(dest,e);
			mc++;
			edgesCounter++;
		}

	}
	@Override
	public Collection<node_data> getV() {
		return nodes.values();
		//return new ArrayList<node_data>(idToNode.values());
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		if(edges.containsKey(node_id))// the node is exist
			return this.edges.get(node_id).values();
		return null;
	}

	@Override
	public node_data removeNode(int key) {///////////////////////////to do//////////////////////////////////////////
		if(nodes.containsKey(key)) {// the node is exist
			if(nodes.containsKey(key))// if there is an edge with this node as a src
				for (Integer node : nodes.keySet())// runs on all the nodes
				{
					if(node!=key&& edges.containsKey(node)&&edges.get(node).containsKey(key)) {// the key is the dest node
						edges.get(node).remove(key);
						edgesCounter--;
						mc++;
					}
					if(node==key&&edges.containsKey(key)) {// the key is the src node
						mc+=edges.get(key).size();// how many edges has been removed
						edgesCounter-=edges.get(key).size();
						edges.remove(key);
					}

				}
			mc++;
			node_data removed=nodes.remove(key);// remove from the node hashmap.
			return removed;
		}
		return null;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		if(edges.containsKey(src)&&edges.get(src).containsKey(dest)) {// the edge is exist
			mc++;
			edgesCounter--;
			return edges.get(src).remove(dest);
		}
		return null;
	}

	@Override
	public int nodeSize() {
		return nodes.size();
	}

	@Override
	public int edgeSize() {
		return edgesCounter;
	}

	@Override
	public int getMC() {
		return mc;
	}

}
