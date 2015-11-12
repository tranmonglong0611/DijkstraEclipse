
package com.heap;

import java.util.Scanner;

public class BinomialHeap implements HeapInterface<BinomialHeapNode> {
	private BinomialHeapNode Nodes;
	private int size;

	public BinomialHeap() {
		Nodes = null;
		size = 0;
	}

	public boolean isEmpty() {
		return Nodes == null;
	}

	public int getsize() {
		return size;
	}

	public void makeEmpty() {
		Nodes = null;
		size = 0;
	}

	/************ Them mot node moi vao ***************/
	public void insert(int id, int value) {
		if (id >=0 ) {
			BinomialHeapNode temp = new BinomialHeapNode(id, value);
			if (Nodes == null) {
				Nodes = temp;
				size = 1;
			}

			else {
				unionNodes(temp);
				size++;
			}
		}
	}

	/**************************
	 * Ham giup dua ra cai Node nho nhat
	 **********************/
	public BinomialHeapNode extractMin() {
		if (Nodes == null) {
			return null;
		}

		BinomialHeapNode temp = Nodes, prevTemp = null;
		BinomialHeapNode minNode = Nodes.findMinNode();

		while (temp.info != minNode.info) {
			prevTemp = temp;
			temp = temp.sibling;
		}

		if (prevTemp == null) {
			Nodes = temp.sibling;
		}

		else {
			prevTemp.sibling = temp.sibling;
		}

		temp = temp.child;
		BinomialHeapNode fakeNode = temp;

		while (temp != null) {
			temp.parent = null;
			temp = temp.sibling;
		}

		if ((Nodes == null) && (fakeNode == null)) {
			size = 0;
		} else {
			if ((Nodes == null) && (fakeNode != null)) {
				Nodes = fakeNode.reverse(null);
				size = Nodes.getSize();
			} else {
				if ((Nodes != null) && (fakeNode == null)) {
					size = Nodes.getSize();
				} else {
					BinomialHeapNode a = fakeNode.reverse(null);
					unionNodes(a);
					size = Nodes.getSize();
				}
			}
		}

		return minNode;
	}

	/************ Ham giam gia tri cua Node id xuong con value **********/
	public void decreaseNodeInfo(int id, int new_value) {

		BinomialHeapNode temp = Nodes.findANodeWithId(id);
		if (temp == null) return;
		if (temp.info < new_value) {
			System.out.println("Error!!!Node moi co gia tri lon hon Node cu");
			return;
		}

		temp.info = new_value;
		BinomialHeapNode tempParent = temp.parent;

		while ((tempParent != null) && (temp.info < tempParent.info)) {
			int z = temp.info;
			int z1 = temp.id;// doi ca id va info cua 2 nut con va nut cha
			temp.info = tempParent.info;
			temp.id = tempParent.id;
			tempParent.info = z;
			tempParent.id = z1;

			temp = tempParent;
			tempParent = tempParent.parent;
		}
	}

	/*private void merge(BinomialHeapNode binHeap) {
		BinomialHeapNode temp1 = Nodes, temp2 = binHeap;

		while ((temp1 != null) && (temp2 != null)) {// sau khi xong se thang
													// temp1->temp2->temp1.sibling
													// || temp2.sibling
			if (temp1.degree == temp2.degree) {
				BinomialHeapNode tmp = temp2;
				temp2 = temp2.sibling; // lenh gan nay de tiep tuc vong lap voi
										// temp2.sibling
				tmp.sibling = temp1.sibling;
				temp1.sibling = tmp;
				temp1 = tmp.sibling; // lenh gan nay de tiep tuc vong lap voi
										// tmp1.sibling
			} else {
				if (temp1.degree < temp2.degree) {
					if ((temp1.sibling == null) || (temp1.sibling.degree > temp2.degree)) {
						BinomialHeapNode tmp = temp2;
						temp2 = temp2.sibling;
						tmp.sibling = temp1.sibling;
						temp1.sibling = tmp;
						temp1 = tmp.sibling;
					} else {
						temp1 = temp1.sibling;
					}
				} else {
					BinomialHeapNode tmp = temp1;
					temp1 = temp2;
					temp2 = temp2.sibling;
					temp1.sibling = tmp;
					if (tmp == Nodes) {
						Nodes = temp1;
					} else {
					}
				}
			}
		}

		if (temp1 == null) {
			temp1 = Nodes;
			while (temp1.sibling != null) {
				temp1 = temp1.sibling;
			}
			temp1.sibling = temp2;
		} else {
		}
	}*/

	
	private void merge(BinomialHeapNode binHeap) {
		{
	         BinomialHeapNode a = Nodes;
	         BinomialHeapNode b = binHeap;

	        if (a == null) {
	        	Nodes = b;
	            return;
	        } else if (b == null) {
	        	Nodes = a;
	        	return;
	        }

	        BinomialHeapNode rootListHead;
	        BinomialHeapNode rootListTail;

	        // Initialize rootListHead and rootListTail.
	        if (a.degree < b.degree) {
	            rootListHead = a;
	            rootListTail = a;
	            a = a.sibling;
	        } else {
	            rootListHead = b;
	            rootListTail = b;
	            b = b.sibling;
	        }

	        while (a != null && b != null) {
	            if (a.degree < b.degree) {
	                rootListTail.sibling = a;
	                rootListTail = a;
	                a = a.sibling;
	            } else {
	                rootListTail.sibling = b;
	                rootListTail = b;
	                b = b.sibling;
	            }
	        }

	        if (a != null) {
	            // Just append the rest.
	            rootListTail.sibling = a;
	        } else {
	            // Just append the rest.
	            rootListTail.sibling = b;
	        }

	        Nodes =  rootListHead;
	    }
	}
	// ham gan z lam con cua y.(y va z co degree = nhau nen gan chung vao voi
	// nhau
	private void linkNodes(BinomialHeapNode y, BinomialHeapNode z) {
		z.parent = y;
		z.sibling = y.child;
		y.child = z;
		y.degree++;
	}

	private void unionNodes(BinomialHeapNode binHeap) {
		merge(binHeap);

		BinomialHeapNode prevTemp = null, temp = Nodes, nextTemp = Nodes.sibling;

		/*
		 * chia ra tat ca 4 truong hop 
		 * 1.temp va nextTemp co degree khac nhau
		 * 2.temp va nextTemp va nextTemp.sibling(3 nut canh nhau) co degree
		 * bang nhau 
		 * 3.temp va nextTemp co degree = nhau va khac voi nextTemp.sibling(temp.info < nextTemp.info 
		 * 4.Giong het truong hop 3 chi khac temp.info > nextTemp.info
		 */

		while (nextTemp != null) {
			// xu ly truong hop 1 va 2
			if ((temp.degree != nextTemp.degree)
					|| ((nextTemp.sibling != null) && (nextTemp.sibling.degree == temp.degree))) {
				prevTemp = temp;
				temp = nextTemp;
			} else {
				// xu ly truong hop 3
				if (temp.info <= nextTemp.info) {
					temp.sibling = nextTemp.sibling;
					linkNodes(temp, nextTemp);
				} else {
					if (prevTemp == null) {
						Nodes = nextTemp;
					} else {
						prevTemp.sibling = nextTemp;
					}
					linkNodes(nextTemp, temp);
					temp = nextTemp;
				}
			}
			nextTemp = temp.sibling;
		}
	}

	public int findMinimum() {
		return Nodes.findMinNode().info;
	}

	public void delete(int value) {
		if ((Nodes != null) && (Nodes.findANodeWithKey(value) != null)) {
			decreaseNodeInfo(value, findMinimum() - 1);
			extractMin();
		}
	}

	public void displayHeap() {
		System.out.print("\nHeap : ");
		displayHeap(Nodes);
		System.out.println("\n");
	}

	private void displayHeap(BinomialHeapNode r) {
		if (r != null) {
			displayHeap(r.child);
			System.out.print(r.info + " ");
			displayHeap(r.sibling);
		}
	}

	@Override
	public void initializeData(int[] distance) {
		Nodes = null;
		size = 0;
		for (int i = 0; i < distance.length; i++) {
			this.insert(i, distance[i]);
		}
	}

	@Override
	public int getSize() {
		return  size;
	}

}
