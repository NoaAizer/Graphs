package dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


public class DGraph implements graph{

	int mc;
	HashMap<Integer,node_data> idToNode;
	HashMap<ArrayList<Integer>,edge_data> nodesToEdge;
	HashMap<Integer,ArrayList<edge_data>> idToEdges;


	public DGraph(int mc, HashMap<Integer, node_data> idToNode, HashMap<ArrayList<Integer>, edge_data> nodesToEdge,
			HashMap<Integer, ArrayList<edge_data>> idToEdges) {
		this.mc = mc;
		this.idToNode = idToNode;
		this.nodesToEdge = nodesToEdge;
		this.idToEdges = idToEdges;
	}
	
	public DGraph() {
		this.mc = 0;
		this.idToNode = new HashMap <Integer,node_data>();
		this.nodesToEdge = new HashMap<ArrayList<Integer>,edge_data>();
		this.idToEdges = new HashMap<Integer,ArrayList<edge_data>>();
	}
	
	@Override
	public node_data getNode(int key) {
		if(idToNode.get(key)==null) return null;
		return idToNode.get(key);
	}
	private ArrayList<Integer> buildArrayList(int src, int dest) {
		ArrayList<Integer> nodes= new ArrayList<Integer>();
		nodes.add(src);
		nodes.add(dest);
		return nodes;
	}
	@Override
	public edge_data getEdge(int src, int dest) {
		edge_data ed=nodesToEdge.get(buildArrayList(src, dest));
		if(ed==null)return null;
		return ed;
	}

	@Override
	public void addNode(node_data n) {
		idToNode.put(n.getKey(),n);
		idToEdges.put(n.getKey(), new ArrayList<edge_data>());
		mc++;
	}

	@Override
	public void connect(int src, int dest, double w) {
		Edges e = new Edges(src, dest, w,null,0);//??????check????????
		nodesToEdge.put(buildArrayList(src, dest), e);
		idToEdges.get(src).add(e);
		idToEdges.get(dest).add(e);


	}
	@Override
	public Collection<node_data> getV() {
		return idToNode.values();
		//return new ArrayList<node_data>(idToNode.values());
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		return nodesToEdge.values();
	}

	@Override
	public node_data removeNode(int key) {///////////////////////////to do//////////////////////////////////////////
		node_data nd=idToNode.remove(key);
		idToEdges.remove(key);
		mc++;
		return nd;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		edge_data ed=nodesToEdge.remove(buildArrayList(src, dest));
		idToEdges.get(src).remove(ed);
		idToEdges.get(dest).remove(ed);
		mc++;
		return ed;
	}

	@Override
	public int nodeSize() {
		return idToNode.size();
	}

	@Override
	public int edgeSize() {
		return nodesToEdge.size();
	}

	@Override
	public int getMC() {
		return mc;
	}

}
