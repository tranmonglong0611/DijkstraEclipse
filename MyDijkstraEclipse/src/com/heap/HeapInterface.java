package com.heap;

public interface HeapInterface<T extends NodeInterface> {
	public void insert(int id, int info);
	public T extractMin();
	public void decreaseNodeInfo(int id, int info);
	public void initializeData(int[] distance);
	public boolean isEmpty();
}

