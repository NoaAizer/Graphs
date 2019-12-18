package dataStructure;

public class Edges implements edge_data{

	private int src;
	private int dest;
	private double weight;
	private String info;
	private int tag;


	public Edges(int src, int dest, double weight, String info, int tag) {
		this.src = src;
		this.dest = dest;
		this.weight = weight;
		this.info = info;
		this.tag = tag;
	}

	public Edges(edge_data ed) {
		this.src=ed.getSrc();
		this.dest=ed.getDest();
		this.weight=ed.getWeight();
		this.info=ed.getInfo();
		this.tag=ed.getTag();
	}

	@Override
	public int getSrc() {
		return src;

	}

	@Override
	public int getDest() {
		return dest;

	}

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public String getInfo() {
		return info;
	}

	@Override
	public void setInfo(String s) {
		this.info=s;
	}

	@Override
	public int getTag() {
		return tag;
	}

	@Override
	public void setTag(int t) {
		this.tag=t;
	}

}
