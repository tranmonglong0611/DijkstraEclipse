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
	
	
	public FibonacciHeapNode findANodeWithId(int id) {
		
		FibonacciHeapNode h = this;//thuc chat this o day phai chinh la root trong FibonacciHeap
		FibonacciHeapNode result = null;
		
		do {
			if ( h.id == id) {
				result = h;
				break;
			}
			if ( h.child == null) {
				h = h.right;
			}else {
				result = h.child.findANodeWithId(id);
				if (result == null) h = h.right;
				else break;
			}
		
		}while (h != this);
			
		return result;
		
	}


	@Override
	public int getId() {
		return id;
	}
}