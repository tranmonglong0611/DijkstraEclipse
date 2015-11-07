package com.heap;

public class HeapNode implements NodeInterface{
    public int info;
    public int id;

    public HeapNode(int id, int info) {//distance cua nut id se co gia tri la info
    	this.id = id;
        this.info = info;
    }

	@Override
	public int getId() {
		return id;
	}
}