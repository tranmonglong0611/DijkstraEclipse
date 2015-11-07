package com.heap;

import java.util.ArrayList;
import java.util.List;

public class FibonacciHeap implements HeapInterface<FibonacciHeapNode>{
	private FibonacciHeapNode root;// cũng chính là minNode
	private int size;


	public FibonacciHeap() {
		root = null;
		size = 0;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void clear() {
		root = null;
		size = 0;
	}

	public void insert(int id, int info) {
		FibonacciHeapNode node = new FibonacciHeapNode(id, info);

		if (root != null) {
			node.left = root;
			node.right = root.right;
			root.right = node;
			node.right.left = node;

			if (info < root.info) {
				root = node;
			}
		}

		else {
			root = node;
		}
		size++;
	}

	public void display() {
		System.out.print("\nHeap = ");
		FibonacciHeapNode ptr = root;
		if (ptr == null) {
			System.out.print("\nEmpty\n");
		}
	}
	
	public FibonacciHeapNode extractMin() {
		FibonacciHeapNode z = root;

		if (z != null) {
			int numKids = z.degree;
			FibonacciHeapNode x = z.child;
			FibonacciHeapNode tempRight;

			// for each child of z do
			while (numKids > 0) {
				tempRight = x.right;

				// remove x from child list
				x.left.right = x.right;
				x.right.left = x.left;

				// add x to root list of heap
				x.left = root;
				x.right = root.right;
				root.right = x;
				x.right.left = x;

				// set parent[x] to null
				x.parent = null;
				x = tempRight;
				numKids--;
			}

			// remove z from root list of heap
			z.left.right = z.right;
			z.right.left = z.left;

			if (z == z.right) {
				root = null;
			} else {
				root = z.right;
				consolidate();
			}

			// decrement size of heap
			size--;
		}

		return z;
	}

	public void decreaseNodeInfo(int id, int newValue) {
		FibonacciHeapNode h = root.findANodeWithId(id);
	
		if (newValue > h.info) {
			System.out.println("New Value > Value Hien tai");;
		}
		h.info = newValue;
			
		FibonacciHeapNode y = h.parent;
		if ((y != null) && (h.info < y.info)) {
			cut (h, y);
			cascadingCut(y);
		}
		
		if (h.info < root.info) {
			root = h;
		}
			
	}
	
	
	
	
	
	
	
	
	
	
	
	protected void cascadingCut(FibonacciHeapNode y) {
		FibonacciHeapNode z = y.parent;
		
		//if there's a parent...
		if (z != null) {
			//if y is unmarked, set it marked
			if ( !y.mark) {
				y.mark = true;
			}else {
				//it's marked, cut it from parent
				cut(y, z);
				
				//cut its parent as well
				cascadingCut(z);
			}
		}
	}
	
	private void cut (FibonacciHeapNode x, FibonacciHeapNode y) {
		//remove x from childlist of y and decrement y.degree
		
		x.left.right = x.right;
		x.right.left = x.left;
		y.degree--;
		
		// reset y.child if necessary
		if (y.child == x) {
			y.child = x.right;
		}
		
		if (y.degree == 0) {
			y.child = null;
		}
		
		//add x to root list of heap
		x.left = root;
		x.right = root.right;
		root.right = x;
		x.right.left = x;
		
		//set x.parent = null
		x.parent = null;
		
		//set x.mark to false
		x.mark = false;
	}
	private void consolidate() {
		int arraySize = size;

		List<FibonacciHeapNode> array = new ArrayList<FibonacciHeapNode>(arraySize);

		for (int i = 0; i < arraySize; i++) {
			array.add(null);
		}

		// Find the number of root Nodes
		int numRoots = 0;
		FibonacciHeapNode x = root;

		if (x != null) {
			numRoots++;
			x = x.right;

			while (x != root) {
				numRoots++;
				x = x.right;
			}
		}

		// For each node in root list do...
		while (numRoots > 0) {
			// Access this node's degree..
			int d = x.degree;
			FibonacciHeapNode next = x.right;

			// .. and see if there's another of same degree
			for (;;) {
				FibonacciHeapNode y = array.get(d);
				if (y == null) {
					// Nope..
					break;
				}

				// There is, make one of the nodes a child of the other
				// Do this based on the key value
				if (x.info > y.info) {
					FibonacciHeapNode temp = y;
					y = x;
					x = temp;
				}

				// FibonacciHeapNode y dissapears from list
				link(y, x);

				// We're handle this degree, go to the next one.
				array.set(d, null);
				d++;
			}
				
			array.set(d, x);
				// Move forward through the list
			x = next;
			numRoots--;
			
		}
		
		// Set min to null (effectively losing the root list) and
        // reconstruct the root list from the array entries in array[].
        root = null;

        for (int i = 0; i < arraySize; i++) {
            FibonacciHeapNode y = array.get(i);
            if (y == null) {
                continue;
            }

            // We've got a live one, add it to root list.
            if (root != null) {
            	if(y.info < root.info) {
            		 root = y;
                }
            } else {
                root = y;
            }
        }
	}

	/*
	 * Make node y a child of node x
	 * 
	 */

	private void link(FibonacciHeapNode y, FibonacciHeapNode x) {
		
		//remove y from list of heap
		y.left.right = y.right;
		y.right.left = y.left;
		
		//make y a child of x
		y.parent = x;
		
		if (x.child == null) {
			x.child = y;
			y.right = y;
			y.left = y;
		}else {
			y.left = x.child;
			y.right = x.child.right;
			x.child.right = y;
			y.right.left = y;
		}
		
		//increase degree[x]
		x.degree++;
		
		y.mark = false;
	}

	private FibonacciHeap mergeHeap(FibonacciHeap mergedHeap) {

		FibonacciHeap h1 = this;
		FibonacciHeap h2 = mergedHeap;
		FibonacciHeap h = new FibonacciHeap();
		if ((h1 != null) && (h2 != null)) {
			h.root = h1.root;

			if (h.root != null) {
				if (h2.root != null) {
					h.root.right.left = h2.root.left;
					h2.root.left.right = h.root.right;
					h.root.right = h2.root;
					h2.root.left = h.root;

					if (h2.root.info < h1.root.info) {
						h.root = h2.root;
					}
				}
			} else {
				h.root = h2.root;
			}

			h.size = h1.size + h2.size;
		}

		return h;
	}

	@Override
	public void initializeData(int[] distance) {
		root = null;
		size = 0;
		for (int i=0; i<distance.length; i++) {
			this.insert(i, distance[i]);
		}
		
	}
}
