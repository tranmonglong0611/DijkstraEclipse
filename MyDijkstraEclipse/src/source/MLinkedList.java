package source;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author long
 */
public class MLinkedList {
    
    public ListNode header;
    
    public MLinkedList() {
    }
    
    public boolean isEmpty() {
        return header == null;
    }
    
    public void makeEmpty() {
        while(header != null) {
            ListNode tempNode = header.next;
            header = null;
            header = tempNode;
        }
    }
    

    
    public void insert(Node x) {
	    ListNode a = new ListNode(x);
	    if(header == null) {
	        header = a;
	        return;
	    }else {
	    
	        ListNode tempNode = header;
	        ListNode prevNode = null;
	        while(tempNode != null) {
	        	if ((tempNode.info.getId() == a.info.getId()) && (tempNode.info.getWeight() > a.info.getWeight()) ) {
	        		tempNode.info.setWeight(a.info.getWeight());
	        		return;  // nếu như node a đã được thêm vào rồi thì ta sẽ không thêm vào nữa
	        	}
	        	prevNode = tempNode;
	            tempNode = tempNode.next;
	        }
	        
	        prevNode.next = a;
	    }
	}
    
    @Override
    public String toString() {
        ListNode tempNode = header;
        String a = "";
        while(tempNode.next != null) {
            if(tempNode.next == null) {
                a = a + tempNode.info.getId();
            }else {
                a = a + tempNode.info.getId() + "->";
            }
            
            tempNode = tempNode.next;
        }
        return a;
    }
    
    
}
    
 class ListNode {
        
    public Node info;
    public ListNode next;

    public ListNode(Node a ) {
        this(a, null);
    }

    public ListNode(Node a, ListNode next) {
        info = a;
        this.next = next;
    }

}

    

    