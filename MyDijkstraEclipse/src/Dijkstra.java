import java.io.FileNotFoundException;
import com.heap.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author long
 */
public class Dijkstra {
	private final int MAX_INT = 99999;
	private HeapInterface heap;
	private NodeInterface node;
	

	private void heapUsing(Graph graph) {
		heap.initializeData(graph.distance);
		while (!heap.isEmpty()) {
			
			System.out.println(heap.getSize());
			node = heap.extractMin();// dua cai Node co gia tri nho
												// nhat trong heap ra
			if (node == null)
				return;
			int minNode = node.getId();
			graph.foundMinPath[minNode] = true; // minNOde.info chinh la nut da tim
											// duoc duong di ngan nhat den
											// source

			int a[] = graph.getNeighborNode(minNode);
			if ( a == null ) continue;
			for (int neighborNode : a) {

				if (graph.foundMinPath[neighborNode])
					continue;// neu da tim thay duong di ngan nhat thi bo qua
								// nut nay
				else {
					if (graph.distance[neighborNode] > graph.distance[minNode] + graph.getWeight(minNode, neighborNode)) {
						graph.distance[neighborNode] = graph.distance[minNode] + graph.getWeight(minNode, neighborNode);

						graph.previousNode[neighborNode] = minNode;
						heap.decreaseNodeInfo(neighborNode, graph.distance[neighborNode]);
					}
				}
			}
		}
	}

	public Dijkstra(Graph graph, HeapInterface heap) throws InputMismatchException, FileNotFoundException {
		this.heap = heap;
		heapUsing(graph);
	}

	


	public String printDijkstra(Graph graph) {
		String a = new String();
		
		for(int i = 0; i < graph.numOfVertex; i++) {
			 a = a + printMinPath(graph, i);
			 a = a + "\n";
		}
		return a;
		
	}
	
	
	public static void main(String[] args) {
		try {
			Graph graph = new Graph(100000, 8);
			graph.sourceNode = 1;
			graph.setInitial();
			graph.print();
			long a1 = System.currentTimeMillis();
			Dijkstra dijkstraTest = new Dijkstra(graph, new BinomialHeap());
			long a2 = System.currentTimeMillis();

			System.out.println(dijkstraTest.printDijkstra(graph));
			System.out.println(a2 - a1);
		} catch (InputMismatchException ex) {
			System.out.println("Loi 1");
		} catch (FileNotFoundException ex) {
			System.out.println("Loi 2");
		}

	}

	private String printMinPath(Graph graph, int destination) {// in ra duong di ngan nhat tu
		// sourceNode den destination
		String a = "" ;
		if (destination == graph.sourceNode) {
			a = a + graph.sourceNode;
			return a;
		}
		
		int index = destination;
		if (graph.distance[index] == MAX_INT) {
			a = a + index + " can't connect to " + graph.sourceNode;  
			return a; 
		}
		
		while (index != graph.sourceNode) {
			a = a + index + " <- ";
			index = graph.previousNode[index];
		}
		
		a = a + graph.sourceNode;
		return a;
	}
}
