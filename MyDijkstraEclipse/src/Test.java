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

import com.heap.BinomialHeap;
import com.heap.Heap;

import javafx.scene.layout.BorderPane;

public class Test {
	
	private Graph graph;
	private Dijkstra dijkstra;
	private JFrame frame;
	private JPanel panel;
	private JMenuBar menu;
	private JTextArea textGraph;
	private JTextArea textDijkstra;
	public Test() {
		setInitial();
		initUI();
		
		frame.setJMenuBar(menu);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		
		frame.setTitle("GRAPH");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	private void setInitial() {
		frame = new JFrame();
		textGraph = new JTextArea(30, 60);
		textGraph.setLineWrap(true);
		textGraph.setEditable(false);
		JScrollPane scrollGraph = new JScrollPane(textGraph);
		
		textDijkstra = new JTextArea(30, 60);
		textDijkstra.setLineWrap(true);
		textDijkstra.setEditable(false);
		JScrollPane scrollDijkstra = new JScrollPane(textDijkstra);
		
		panel = new JPanel();
		panel.add(scrollGraph);
		panel.add(scrollDijkstra);
		
		menu = new JMenuBar();
		
	}
//	private void initUI() {
//		CardLayout cl = new CardLayout();
//		JPanel panelChange = new JPanel(cl);
//		
//		JPanel panelOne = new JPanel(new BorderLayout());
//		JPanel panelTwo = new JPanel();
//		
//		JButton button1 = new JButton("Make graph");
//		JButton button2 = new JButton("Quit");
//		panelOne.add(BorderLayout.PAGE_START, button1);
//		panelOne.add(BorderLayout.LINE_START, button2);
//		
//		JTextArea textArea = new JTextArea();
//		textArea.setEditable(false);
//		textArea.setSize(300, 300);	
//		panelTwo.add(textArea);
//
//		panelChange.add(panelOne, "1");
//		panelChange.add(panelTwo, "2");
//		
//		button1.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				try {
//					Graph graph = new Graph("haha.txt");
//					graph.print(textArea);
//					cl.show(panelChange, "2");
//			
//				} catch (InputMismatchException | FileNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
//			
//		});
//		
//		button2.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.exit(0);
//			}
//			
//		});
//	
//		
//		this.getContentPane().add(BorderLayout.CENTER, panelChange);
//	}
	
	
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
		doDijkstra.add(normalHeapUsing);
		doDijkstra.add(binoHeapUsing);
		doDijkstra.add(fiboHeapUsing);
		
		normalHeapUsing.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					heapDijkstra();
				} catch (InputMismatchException | FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
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
	
	private void heapDijkstra() throws InputMismatchException, FileNotFoundException {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Enter source node"));
		JTextField inputSource = new JTextField(3);
		panel.add(inputSource);
		
		int result = JOptionPane.showConfirmDialog(null, panel, 
	               "", JOptionPane.OK_CANCEL_OPTION);
		
		graph.sourceNode = Integer.parseInt(inputSource.getText());
		graph.setInitial();
		
		dijkstra = new Dijkstra(graph, new Heap());
		textDijkstra.append(dijkstra.printDijkstra(graph));
		
	}
	private void randomInput() {
		JTextField xField = new JTextField(3);
		JTextField yField = new JTextField(3);
		JPanel myPanel1 = new JPanel();
		myPanel1.add(new JLabel("Enter the Number of Vertexs"));
		myPanel1.add(xField);
		
		JPanel myPanel2 = new JPanel();
		myPanel2.add(new JLabel("Enter the Max Number of NeighborEdges"));
	    myPanel2.add(yField);
	    
	    JPanel myPanel = new JPanel();
	    myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
	    myPanel.add(myPanel1);
	    myPanel.add(myPanel2);
	    								
	    int result = JOptionPane.showConfirmDialog(null, myPanel, 
	               "", JOptionPane.OK_CANCEL_OPTION);
	    
	    int x = Integer.parseInt(xField.getText());
	    int y = Integer.parseInt(yField.getText());
	    
	    graph = new Graph(x ,y);
	    graph.print(textGraph);
	    
	    
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



