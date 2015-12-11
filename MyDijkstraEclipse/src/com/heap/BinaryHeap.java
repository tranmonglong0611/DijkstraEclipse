
package com.heap;

public class BinaryHeap implements HeapInterface<BinaryHeapNode> {//heap với nút gốc là nút nhỏ nhất(nút cha mẹ phải bé hơn nút con
    public BinaryHeapNode[] node;
    public int numOfNode = 0;
    private final int MAX_INT = 99999;
    private int[] position;
    
    public BinaryHeap() {
    	
    }
    
 
    public void insert(int id, int info) {
    	node[numOfNode] = new BinaryHeapNode(id, info);
    	numOfNode++;
    }
    
    public BinaryHeapNode extractMin() {
        BinaryHeapNode a = node[0];
        exchangeNode(0, numOfNode - 1);
        numOfNode--;
        minHeapify(0);
        return a;
    }
    
    //thay đổi giá trị info của phần tử id (thay doi distance[id] ) trong mảng
    public void decreaseNodeInfo(int idChange, int info) {
    	int i = position[idChange];
    	
        if (info < node[i].info) {
            node[i].info = info;
            while((i > 0) && (node[i].info < node[(i-1)/2].info)) {
                exchangeNode(i, (i-1)/2);
                i = (i-1)/2;
            }
            return;
            
        }
        if ( info > node[i].info) { 
            System.out.println("Error!!! Gia tri nut moi lon hon gia tri nut cu");
        }       
               
    }
    
    
    @Override
	public void initializeData(int[] a) {
		numOfNode = a.length;
    	node = new BinaryHeapNode[a.length];
    	position = new int[200000];
    	
    	for (int i=0; i<a.length; i++) {
    		node[i] = new BinaryHeapNode(i, a[i]);
    		position[i] = i;
    	}
    	
    	buildMinHeap();
	}

	@Override
	public int getSize() {
		return numOfNode;
	}
    
	public boolean isEmpty() {
    	return (numOfNode == 0);
    }
	   
    private void exchangeNode(int a, int b) {
    	position[node[a].id] = b;
    	position[node[b].id] = a;

    	BinaryHeapNode tempNode = node[a];
        node[a] = node[b];
        node[b] = tempNode;
    }
    
    //vun đống từ vị trí i
    private void minHeapify(int i) {
        
        if (i >= numOfNode) return;
        
        int left = 2*i + 1;
        int right = 2*i + 2;
        int biggest = i;
        
        if ( (left < numOfNode)&&(node[i].info > node[left].info)) {
            biggest = left;
        }
        
        if ( (right < numOfNode) && (node[right].info < node[biggest].info)) {
            biggest = right;
        }
        
        if (biggest != i) {
            exchangeNode(i, biggest);
            minHeapify(biggest);
        }
    }
    
    public void buildMinHeap() {
        for (int i = (int)(numOfNode/2); i >= 0; i--) {
            minHeapify(i);
        }
    }
	
}

