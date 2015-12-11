package com.heap;

public class Array implements HeapInterface<ArrayNode> {

	ArrayNode[] node;
	private int numOfNode = 0;
	private static final int MAX_INT = 99999;
	private ArrayNode[] position;
	
	@Override
	public void insert(int id, int info) {
		node[numOfNode] = new ArrayNode(id, info);
		position[id] = node[numOfNode];
		numOfNode++;
	}

	@Override
	public ArrayNode extractMin() {
		ArrayNode temp = null;
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
		ArrayNode temp = position[id];
		temp.info = info;
	}

	@Override
	public void initializeData(int[] distance) {
		numOfNode = distance.length;
		node = new ArrayNode[numOfNode];
		position = new ArrayNode[200000];
		
		for (int i = 0; i < numOfNode; i++) {
			node[i] = new ArrayNode(i , distance[i]);
			position[i] = node[i];
		}
		
	}

	@Override
	public int getSize() {
		return numOfNode;
	}
	
	@Override
	public boolean isEmpty() {
		return (numOfNode == 0);
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
	
	// xoa node co id = id
	public void delete(int id) { 
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



}
