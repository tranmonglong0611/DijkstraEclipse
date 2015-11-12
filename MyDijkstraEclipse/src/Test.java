import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.heap.Array;
import com.heap.BinomialHeap;
import com.heap.FibonacciHeap;
import com.heap.Heap;
import com.heap.HeapInterface;

import javafx.scene.layout.BorderPane;

public class Test {
	
	private Graph graph;
	private Dijkstra dijkstra;
	private JFrame frame;
	private JPanel panel1;// chua 2 cai textArea
	private JPanel panel2;// chua 2 cai time
	private JMenuBar menu;
	private JTextArea textGraph;
	private JTextArea textDijkstra;
	private JTextArea timeGraph;
	private JTextArea timeDijkstra;
	private double timeToMakeGraph;
	private double timeToDijkstra;
	
	public Test() {
		setInitial();
		initUI();
		
		frame.setJMenuBar(menu);
		frame.getContentPane().add(BorderLayout.SOUTH, panel2);
		frame.getContentPane().add(BorderLayout.CENTER, panel1);
		
		frame.setTitle("GRAPH");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	private void setInitial() {
		frame = new JFrame();
	
		textGraph = new JTextArea(40, 50);
		textGraph.setLineWrap(true);
		textGraph.setEditable(false);
		JScrollPane scrollGraph = new JScrollPane(textGraph);
		
		textDijkstra = new JTextArea(40, 50);
		textDijkstra.setLineWrap(true);
		textDijkstra.setEditable(false);
		JScrollPane scrollDijkstra = new JScrollPane(textDijkstra);
		
		panel1 = new JPanel();
		panel1.add(scrollGraph);
		panel1.add(scrollDijkstra);
		
		
		
		
		JLabel t1 = new JLabel("Time to make graph: ");
		timeGraph = new JTextArea("haha");
		JLabel t2 = new JLabel("Time to do Dijkstra: ");
		
		timeDijkstra = new JTextArea("hehe");
		
		panel2 = new JPanel();
		GroupLayout layout = new GroupLayout(panel2);
	
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addComponent(t1)
				.addComponent(timeGraph)
				.addComponent(t2)
				.addComponent(timeDijkstra)
				);
		
	
		
		menu = new JMenuBar();
		
	}

	
	
	private void initUI() {
		
		JMenu makeGraph = new JMenu("MakeGraph");
		JMenuItem makeByRandom = new JMenuItem("By Random");
		JMenuItem makeByFile = new JMenuItem("By File");
		makeGraph.add(makeByRandom);
		makeGraph.add(makeByFile);
		
		JMenu doDijkstra = new JMenu("DoDijstra");
		JMenuItem normalHeapUsing = new JMenuItem("Normal Heap");
		JMenuItem binoHeapUsing = new JMenuItem("Binomial Heap");
		JMenuItem fiboHeapUsing = new JMenuItem("Fibonnaci Heap");
		JMenuItem arrayUsing = new JMenuItem("Array");
		doDijkstra.add(normalHeapUsing);
		doDijkstra.add(binoHeapUsing);
		doDijkstra.add(fiboHeapUsing);
		doDijkstra.add(arrayUsing);
		
		//doan nay them vao de su dung cac nut
		/***********************/
		normalHeapUsing.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					heapDijkstra(new Heap());
				} catch (InputMismatchException | FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		binoHeapUsing.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					heapDijkstra(new BinomialHeap());
				} catch (InputMismatchException | FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		fiboHeapUsing.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					heapDijkstra(new FibonacciHeap());
				} catch (InputMismatchException | FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		arrayUsing.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					heapDijkstra(new Array());
				} catch (InputMismatchException | FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		/****************/
		
		makeByRandom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				randomInput();
			}
			
		});
		
		makeByFile.addActionListener(new ActionListener()  {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fileInput();
			}
		});
		menu.add(makeGraph);
		menu.add(doDijkstra);
		
	}
	
	private void heapDijkstra(HeapInterface heap) throws InputMismatchException, FileNotFoundException {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Enter source node"));
		JTextField inputSource = new JTextField(3);
		panel.add(inputSource);
		
		int result = JOptionPane.showConfirmDialog(null, panel, 
	               "", JOptionPane.OK_CANCEL_OPTION);
		
		graph.sourceNode = Integer.parseInt(inputSource.getText());
		System.out.println(System.currentTimeMillis());
		graph.setInitial();
		System.out.println(System.currentTimeMillis());
		//Time to do Dijkstra
		double begin = System.currentTimeMillis();
		System.out.println(begin);
		dijkstra = new Dijkstra(graph, heap);
		double end = System.currentTimeMillis();
		System.out.println(end + "\n" + (end - begin));
		timeToDijkstra = end - begin;
		
		String time = String.valueOf(timeToDijkstra);
		timeDijkstra.setText(time);

		textDijkstra.append(dijkstra.printDijkstra(graph));
		
		
	}
	
	private void randomInput() {
		JTextField xField = new JTextField(3);
		JTextField yField = new JTextField(3);
		JPanel myPanel1 = new JPanel();
		myPanel1.add(new JLabel("Enter the Number of Vertexs"));
		myPanel1.add(xField);
		
		JPanel myPanel2 = new JPanel();
		myPanel2.add(new JLabel("Enter the Max Number of NeighborVertexs"));
	    myPanel2.add(yField);
	    
	    JPanel myPanel = new JPanel();
	    myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
	    myPanel.add(myPanel1);
	    myPanel.add(myPanel2);
	    								
	    int result = JOptionPane.showConfirmDialog(null, myPanel, 
	               "", JOptionPane.OK_CANCEL_OPTION);
	    
	    int x = Integer.parseInt(xField.getText());
	    int y = Integer.parseInt(yField.getText());
	    
	    //Time to make graph
	    double begin = System.currentTimeMillis();
	    System.out.println(begin);
	    graph = new Graph(x ,y);
	    double end = System.currentTimeMillis();
	    System.out.println(end);
	    System.out.println(end - begin);
	    timeToMakeGraph = end - begin;
	    
	    String time = String.valueOf(timeToMakeGraph);
	    timeGraph.setText(time);
	    
	    long time1 = System.currentTimeMillis();
	    System.out.println(time1);
	    graph.print(textGraph);
	    long time2 = System.currentTimeMillis();
	    
	    System.out.println(time2 + "\n" + (time1 - time2));
	}
	
	private void fileInput() {
		JTextField field = new JTextField(10);
		JPanel panel = new JPanel();
		panel.add(new JLabel("Enter file name: "));
		panel.add(field);
		
		int result = JOptionPane.showConfirmDialog(null, panel, 
	               "", JOptionPane.OK_CANCEL_OPTION);
	    if (result == JOptionPane.OK_OPTION) {
		    try {
				graph = new Graph(field.getText());
				graph.print(textGraph);
			} catch (InputMismatchException | FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				Test test = new Test();
			}
			
		});
	}
}



