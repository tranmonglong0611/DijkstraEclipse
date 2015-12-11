package source;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
	//private HeapInterface heap;
	private NodeInterface node;
	private HeapInterface heap;
	

	private void doDijkstra(Graph graph) {
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

//	private void heapUsing2(Graph graph) {
//		heap = new BinaryHeap();
//		heap.initializeData(graph.distance);
//		while (!heap.isEmpty()) {
//			
//			System.out.println(heap.getSize());
//			node = heap.extractMin();// dua cai Node co gia tri nho
//												// nhat trong heap ra
//			if (node == null)
//				return;
//			int minNode = node.getId();
//			
//			graph.foundMinPath[minNode] = true; // minNOde.info chinh la nut da tim
//											// duoc duong di ngan nhat den
//											// source
//
//			int a[] = graph.getNeighborNode(minNode);
//			if ( a == null ) continue;
//			for (int neighborNode : a) {
//
//				if (graph.foundMinPath[neighborNode])
//					continue;// neu da tim thay duong di ngan nhat thi bo qua
//								// nut nay
//				else {
//					if (graph.distance[neighborNode] > graph.distance[minNode] + graph.getWeight(minNode, neighborNode)) {
//						graph.distance[neighborNode] = graph.distance[minNode] + graph.getWeight(minNode, neighborNode);
//
//						graph.previousNode[neighborNode] = minNode;
//						heap.decreaseNodeInfo(neighborNode, graph.distance[neighborNode]);
//					}
//				}
//			}
//		}
//	}
	
	public Dijkstra(Graph graph, HeapInterface heap) throws InputMismatchException, FileNotFoundException {
		this.heap = heap;
		doDijkstra(graph);
	}

//	public Dijkstra(Graph graph) {
//		heapUsing2(graph);
//	}


	public String printDijkstra(Graph graph) {
		String a = new String();
		
		for(int i = 0; i < graph.numOfVertex; i++) {
			 a = a + minPath(graph, i);
			 a = a + "\n";
		}
		return a;
		
	}
	
	public void printDijkstra2(Graph graph) {
		String a = new String();
		for(int i = 0; i < graph.numOfVertex; i++) {
			 a = minPath(graph, i);
			 System.out.println(a);
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		try {
			Graph graph = new Graph("20.txt");
			graph.sourceNode = 1;
			graph.setInitial();
			graph.print();
			long a1 = System.currentTimeMillis();
			Dijkstra dijkstraTest = new Dijkstra(graph, new Array());
			long a2 = System.currentTimeMillis();
			System.out.println(a2 - a1);
			
//			try {
//			    Thread.sleep(5000);                 //1000 milliseconds is one second.
//			} catch(InterruptedException ex) {
//			    Thread.currentThread().interrupt();
//			}
			
			//dijkstraTest.printDijskstraToFile(graph, "hoho.txt");
			//System.out.println("===============================");
			dijkstraTest.printDijkstra2(graph);
		} catch (InputMismatchException ex) {
			System.out.println("Loi 1");
		}
		
//		Graph graph = new Graph();
//		graph.randomGraphToFile(1000, 9, "fileName.txt");
//		
//		graph = new Graph("fileName.txt");
//		graph.print();
		
	}

	private int minWeight(Graph graph, int destination) {
		return graph.distance[destination];
	}
	
	public String minPath(Graph graph, int destination) {// in ra duong di ngan nhat tu
		// sourceNode den destination
		String a = "" ;
		if (destination == graph.sourceNode) {
			a = a + graph.sourceNode;
			return a;
		}
		
		int index = destination;
		if (graph.distance[index] == MAX_INT) {
			a = a + index + " can't connect to " + graph.sourceNode + "\n";  
			return a; 
		}
		
		while (index != graph.sourceNode) {
			a = a + index + " <- ";
			index = graph.previousNode[index];
		}
		
		a = a + graph.sourceNode + "(" + graph.distance[destination] + ")";
		return a + "\n";
	}
	
	//name is the name of file
	public void printDijskstraToFile(Graph graph, String name) {
		
		
//		try {
//			PrintWriter out = new PrintWriter(name);
//			for (int i = 0; i < graph.numOfVertex; i++) {
//				out.println(minPath(graph, i));
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		BufferedWriter writer = null;
		try
		{
		    writer = new BufferedWriter( new FileWriter(name));
		    for (int i = 0; i < graph.numOfVertex; i++) {
		    	writer.write(minPath(graph, i));
		    }

		}
		catch ( IOException e){
			e.printStackTrace();
		}
		finally
		{
		    try
		    {
		        if ( writer != null)
		        writer.close( );
		    }
		    catch ( IOException e){
		    	e.printStackTrace();
		    }
		}
	}
}
