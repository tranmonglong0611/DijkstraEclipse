
package com.heap;

public class Heap implements HeapInterface<HeapNode> {//heap với nút gốc là nút nhỏ nhất(nút cha mẹ phải bé hơn nút con
    public HeapNode[] node;
    public int numOfNode = 0;
    private final int MAX_INT = 9999;
   
    public Heap() {
    	
    }
    
    public boolean isEmpty() {
    	return (numOfNode == 0);
    }
   
    public void insert(int id, int info) {
        numOfNode++ ;
        node[numOfNode-1] = new HeapNode(id, MAX_INT);//gan cho no 1 gia tri info cuc lon
        
        decreaseNodeInfo(id, info);//sau do thuc hien cau lenh thay doi gia tri info de dua heapNode do ve dung vi tri
        
    }
    
    
    private void exchangeNode(int a, int b) {
        HeapNode tempNode = node[a];
        node[a] = node[b];
        node[b] = tempNode;
    }
    
    private void minHeapify(int i) {//vun đống từ vị trí i
        
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
    public HeapNode extractMin() {
        HeapNode a = node[0];
        exchangeNode(0, numOfNode - 1);
        numOfNode--;
        minHeapify(0);
        return a;
    }
    
    //thay đổi giá trị info của phần tử id (thay doi distance[id] ) trong mảng
    public void decreaseNodeInfo(int idChange, int info) {
        //tăng giá trị info lên
    	int i = 0;
    	for(i=0; i<numOfNode; i++) {
    		if (node[i].id == idChange)
    			break;
    	}
        if (info < node[i].info) {//giam gia tri node idChange => idChange co the be hon nut cha cua no => phai xu ly tu nut idChange len tren
            node[i].info = info;
            while((i > 0) && (node[i].info < node[(i-1)/2].info)) {
                exchangeNode(i, (i-1)/2);
                i = (i-1)/2;
            }
            return;
            
        }
        if (info > node[i].info) { //tang gia tri node idChange => idChange co the lon hon nut con cua no => Minheapify(xu ly tu nut idChange xuong duoi)
            System.out.println("Error!!! Gia tri nut moi lon hon gia tri nut cu");
        }       
               
    }
    
    public void print() {
    	for(int i=0; i<numOfNode; i++) {
    		System.out.print(node[i].id +  " : " + node[i].info + "<><><>");
    	}
    	System.out.println();
    }

	@Override
	public void initializeData(int[] a) {
		numOfNode = a.length;
    	node = new HeapNode[a.length];
    	
    	for (int i=0; i<a.length; i++) {
    		node[i] = new HeapNode(i, a[i]);
    	}
    	
    	buildMinHeap();
	}
}

