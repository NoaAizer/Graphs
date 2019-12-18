package dataStructure;

import utils.Point3D;

public class Nodes implements node_data {

	private int key;
	private Point3D location;
	private double weight;
	private String info;
	private int tag;

	public Nodes(int key, Point3D location, double weight, String info, int tag) {
		this.key = key;
		this.location = location;
		this.weight = weight;
		this.info = info;
		this.tag = tag;
	}

	@Override
	public int getKey() {
		return key;
	}

	@Override
	public Point3D getLocation() {
		return location;
	}

	@Override
	public void setLocation(Point3D p) {
		this.location=p;
	}

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public void setWeight(double w) {
		this.weight=w;

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
