
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JTextArea;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author long
 */
public class Graph {
    //can xay dung LinkedList
    public int numOfVertex;
    public MLinkedList[] vertexes;
    
    private final int MAX_INT = 9999;
    public int sourceNode;                          // nut nguon trong thuat toan dijkstra
    public boolean[] foundMinPath;                   // bien kiem tra xem nut i da tim duoc duong di ngan nhat chua
	public int[] distance;                           // khoang cach da biet tu nut i den sourceNode
	public int[] previousNode;						 //đã tạo xong mảng gồm numOfVertex Vertex(numOfVertex nút Vertex)
                                                       
    
    public Graph(String file) throws InputMismatchException, FileNotFoundException{
    	setAdjacency(file);                          //doc du lieu tu file de cai dat danh sach ke
    }
    
    public Graph(int vertex, int maxNeighborEdge) {
    	randomGraph(vertex, maxNeighborEdge);
    }
    
    private void randomGraph(int vertex, int maxNeighborEdge) {
    	numOfVertex = vertex;
    	vertexes = new MLinkedList[numOfVertex];
 
    	for (int i=0; i< numOfVertex; i++) {
    		vertexes[i] = new MLinkedList();
    		vertexes[i].insert(new Node(i, 0));
    	}
    	
    	
    	for (int i=0; i<numOfVertex; i++) {
    		int numOfNeighborEdge = (int) (Math.random()*maxNeighborEdge);
    		
    		for (int j=0; j<numOfNeighborEdge; j++) {
    			int a = (int) (Math.random() * numOfVertex);
    			int weight = (int) (Math.random() * 1000);
    			
    			vertexes[i].insert(new Node(a, weight));
    		}
    	}
    }
    
    public void setInitial() {
    	distance = new int[numOfVertex];
    	for (int i=0; i<numOfVertex; i++) {
    		if (i == sourceNode) {
    			distance[i] = 0;
    		}else {
    			distance[i] = MAX_INT;
    		}
    	}
    	
    	foundMinPath = new boolean[numOfVertex];
		for (int i = 0; i < numOfVertex; i++) {// tat ca deu chua tim thay duong di ngan nhat
			foundMinPath[i] = false;
		}
		
		previousNode = new int[numOfVertex];
		previousNode[sourceNode] = -1;
    }
    
    private void setAdjacency(String file) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(file));
        
        numOfVertex = scan.nextInt();
        
        vertexes = new MLinkedList[numOfVertex];
      
        for (int i=0; i< numOfVertex; i++) {
    		vertexes[i] = new MLinkedList();
    	}
        
        for (int i=0; i<numOfVertex; i++) {
            Node a = new Node(scan.nextInt(), 0);//do day la nut goc nen khoang cach cua no den chinh no la 0
            vertexes[i].insert(a);
        }
  
        while(scan.hasNext()) {
            int v1 = scan.nextInt();
            int v2 = scan.nextInt();
            int  weight = scan.nextInt();
            
            for(int i=0; i<numOfVertex; i++) {
                if(vertexes[i].header.info.getId() == (v1)) {
                    vertexes[i].insert(new Node(v2, weight));
                }
            }
        }
    }
    
    //ham duyet linkedlist cua vertex vi tri id de tim ra cac nut hang xom
    public int[] getNeighborNode(int id) {
       
       int[] a;
 	   int index = 0;
 	   
 	   int numOfNeighborNode = 0;
 	   
 	   ListNode graphNode = this.vertexes[id].header;
 	   
 	   while(graphNode.next != null) {
 		   numOfNeighborNode++;
 		   graphNode = graphNode.next;
 	   }
 	   
 	   a = new int[numOfNeighborNode];
 	 
 	   graphNode = this.vertexes[id].header;
 	 
 	   if ( numOfNeighborNode == 0) { return null; } //khong co nut lien ke
 	   
 	   while(graphNode.next != null) {
 		   a[index] = graphNode.next.info.getId();
 		   graphNode = graphNode.next;
 		   index++;
 	   }
 	   
 	   return a;
 	   
    }
    
    public int getWeight(int source, int Node) {// tim khoang cach tu nut Node den nut Source
    	ListNode tempNode = this.vertexes[source].header;
    	while(tempNode.next.info.getId() != Node) {
    		tempNode = tempNode.next;
    	}
    	
    	return tempNode.next.info.getWeight();
    }
    
    public void print() {
    	for (int i=0; i<numOfVertex; i++) {
            ListNode a = vertexes[i].header;
            while(a!=null) {
                if (a.next != null) {
                    System.out.print(a.info.getId() + "(" + a.info.getWeight() + ")" + " -> " );  //in ra theo kiểu Long(0) -> My(2) -> Thai (5)...
                }else {
                    System.out.print(a.info.getId() + "(" + a.info.getWeight() + ")" );
                }
                a = a.next;
            }
            
            System.out.println();
        }
    }
    
    
    
    
    public void print(JTextArea textArea) {
        for (int i=0; i<numOfVertex; i++) {
            ListNode a = vertexes[i].header;
            while(a!=null) {
                if (a.next != null) {
                    textArea.append(a.info.getId() + "(" + a.info.getWeight() + ")" + " -> " );  //in ra theo kiểu Long(0) -> My(2) -> Thai (5)...
                }else {
                    textArea.append(a.info.getId() + "(" + a.info.getWeight() + ")" );
                }
                a = a.next;
            }
            
            textArea.append("\n");
        }
    }
    
    public MLinkedList[] getVertexes() {
        return vertexes;
    }

}
