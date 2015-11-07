
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author long
 */
public class Node {
 
    private final int id; 
    private final int weight;// gia tri canh tu nut nay den nut nguon.vi du head->a->b->c->.....Đây là nút c thì weight la giá trị cạnh head->c
         
    public Node(int id, int weight) {
        this.id = id;
        this.weight = weight;
    }
    
    					
	public int getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return "Node " + id  ;
    }
    
    public int getWeight() {
        return this.weight;
    }
    
    public void setDistance (Node source, int distance) {
        
    }
}
