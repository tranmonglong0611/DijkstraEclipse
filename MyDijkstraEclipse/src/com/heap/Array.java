package com.heap;

public class Array implements HeapInterface<HeapNode> {

	HeapNode[] node;
	private int numOfNode = 0;
	private static final int MAX_INT = 99999;

	@Override
	public void insert(int id, int info) {

	}

	@Override
	public HeapNode extractMin() {
		HeapNode temp = null;
		for (int i = 0; i < numOfNode; i++) {
			if (node[i].info < MAX_INT) {
				temp = node[i];
			}
		}

		if (temp == null) return null;
		delete(temp.id);
		return temp;
	}

	@Override
	public void decreaseNodeInfo(int id, int info) {
		HeapNode temp = findNodeWithId(id);
		temp.info = info;
	}

	@Override
	public void initializeData(int[] distance) {
		numOfNode = distance.length;
		node = new HeapNode[numOfNode];
		
		for (int i = 0; i < numOfNode; i++) {
			node[i] = new HeapNode(i , distance[i]);
		}
		
	}

	@Override
	public boolean isEmpty() {
		return (numOfNode == 0);
	}

	
	private HeapNode findNodeWithId(int id) {
		for (int i = 0; i < numOfNode; i++) {
			if (node[i].id == id) {
				return node[i];
			}
		}
		return null;
	}
	
	//tim vi tri cua ( node.id = id) trong mang
	private int findPosition(int id) {
		for (int i = 0; i < numOfNode; i++) {
			if (node[i].id == id) {
				return i;
			}
		}
		return -1;
	}
	
	public void delete(int id) { // xoa node co id = id

		int save = findPosition(id);
		if (save == numOfNode - 1) {
			node [save] = null;
			numOfNode--;
			return;
		}
		
		// dich dan cac node vao
		for (int i = save; i < numOfNode - 1; i++) {
			node[i] = node[i + 1];
		}
		
		node[numOfNode - 1] = null;
		numOfNode--;
	}

	@Override
	public int getSize() {
		return numOfNode;
	}

}
