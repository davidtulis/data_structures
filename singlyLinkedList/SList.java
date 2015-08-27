package singlyLinkedList;

/**
 * 
 * @author david tulis
 *
 */

public class SList
{
  // instance variables of the SinglyLinkedList
  private Node head = null;	 // head node of the list (or null if empty)
  private int size = 0;	 // number of nodes in the list
  
  // constructs an initially empty list
  public SList() 
  { 
  }	 
  
  /**
   * this method swaps the two nodes
   * @param a the first node
   * @param b the second node
   * @precondition a comes before b in the list
   */
  public void swap(Node a, Node b) {
	  Node aNext=a.getNext();
	  Node bNext=b.getNext();
	  Node v=head;
	  
	  if(a==v) //if a is the first element
	  {
		  head=b; //
		  b.setNext(aNext);
	  }
	  else { //we search for a
		  while(v.getNext()!=a) {
			  v=v.getNext(); 
		  }
		  v.setNext(b);
		  v=v.getNext();
		  v.setNext(aNext);
	  }
	  
	  try {
		  while(v.getNext()!=b) {
			  v=v.getNext(); 
		  }

	  } catch (NullPointerException e) { //b is at the end of the list
		  v.setNext(a);
		  v=v.getNext();
		  v.setNext(null);
	  }
		  
		  v.setNext(a);
		  v=v.getNext();
		  v.setNext(bNext);
	  
	  }
  
  /**
   * Gets the size of the list
   * @return size
   */
  public int getSize() 
  {
      return size; 
  }
   
  /**
   * Check if the list is empty
   * @return true/false
   */
  public boolean isEmpty() 
  {
      return size == 0; 
  }
   
  /**
   * Gets the first node in the list
   * @return the first node in the list
   */
  public Node getFirst() 
  {	 // returns (but does not remove) the ﬁrst element
      if (isEmpty()) 
          return null;
      return head.getNext();
  }
  
  /**
   * Adds a newNode at the beginning of the list
   * @param newNode the new node to be added to the list
   */  
  public void addFirst(Node newNode) 
  {	
      newNode.setNext(head);
      head = newNode;	 // create and link a new node
      size++;
  }
  
   /**
    * Removes the first element in the list
    * @return the removed node 
    */
   public Node removeFirst() 
   {	 // removes and returns the ﬁrst element
       if (isEmpty()) 
             return null;	 // nothing to remove
       Node removedNode = head;
       head = head.getNext();	 // will become null if list had only one node
       size --;
       return removedNode;
   }
   
   
   /** 
    * Returns a string representation of the list 
    */
    public String toString()
    {
        String s =  "[";
        Node v = head;
        while (v != null)
        {
            s += v.getElement();
            v = v.getNext();
            if (v != null)
                s +=  ", ";
        }
        s +=  "]";
        return s;
    }
}