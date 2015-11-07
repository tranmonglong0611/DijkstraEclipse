package com.heap;

public class BinomialHeapNode implements NodeInterface{
	public int info, id, degree;
	public BinomialHeapNode parent;
	public BinomialHeapNode sibling;
	public BinomialHeapNode child;
	
	
	public BinomialHeapNode(int id, int info) {
		this.id = id;
		this.info = info;
		degree = 0;
		parent = null;
		sibling = null;
		child = null;
	}
	
	public BinomialHeapNode reverse(BinomialHeapNode sibl) {//dao chieu cua heap.Duoc su dung trong ham extract min
		BinomialHeapNode ret;
		if (sibling != null) {
			ret = sibling.reverse(this);
		}
		else {
			ret = this;
		}
		
		sibling = sibl;
		return ret;
	}
	
	public BinomialHeapNode findMinNode() { // chỉ tìm kiếm với các nút là nút gốc ( là các nút sibling với nhau
		BinomialHeapNode x = this, y = this;
		int min = x.info;
		
		while (x != null) {
			if(x.info < min) {
				y = x;
				min = x.info;
			}
			x = x.sibling;
		}
		
		return y;
	}
	
	public BinomialHeapNode findANodeWithId(int value) {
		BinomialHeapNode temp = this, node = null;
		
		while (temp != null) {
			if (temp.id == value) {
				node = temp;
				break;
			}
			
			if (temp.child == null) {
				temp = temp.sibling;
			}
			else {
				node = temp.child.findANodeWithId(value);
				if (node == null) 
					temp = temp.sibling;
				else break;
			}
		}
		return node;
	}
	public BinomialHeapNode findANodeWithKey(int value) {
		BinomialHeapNode temp = this, node = null;
		
		while( temp != null) {
			if(temp.info == value) {
				node = temp;
				break;
			}
			
			if(temp.child == null) {
				temp = temp.sibling;
			}
			else {
				node = temp.child.findANodeWithKey(value);
				if (node == null) 
					temp = temp.sibling;
				else break;
				
			}
		}
		return node;
	}
			
	
	public int getSize()  {
       return (1 + ((child == null) ? 0 : child.getSize()) + ((sibling == null) ? 0 : sibling.getSize()));
    }

	@Override
	public int getId() {
		return id;
	}
		
	
}