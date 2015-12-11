package com.heap;

public class FibonacciHeapNode implements NodeInterface {
	FibonacciHeapNode child, left, right, parent;
	public int info;
	public int id;
	public int degree; //so con 
	boolean mark;
	
	public FibonacciHeapNode(int id, int info) {
		this.right = this;
		this.left = this;
		this.info = info;
		this.id = id;
		degree = 0;
		mark = false;
	}
	


	@Override
	public int getId() {
		return id;
	}


	@Override
	public int getInfo() {
		return info;
	}
}