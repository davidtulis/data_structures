package doublyLinkedList;

/*
 * This class will test the DNode and DList code by creating a doubly linked list and run its methods.
 */

/**
 *
 * @author CPSC
 */
public class DListTest 
{
    public static void main (String [] args)
    {
        //Create a doubly linked list
        DList list = new DList();
        
        // Create a node to be added to the list
        DNode node = new DNode("A", null, null);
        
        // Add the node using addFirst mehod
        list.addFirst(node);
        
        // Print the elements in the list
        
        
        // Create a node to be added to the list
        DNode node2 = new DNode("B", null, null);
   
        // Add the node using addFirst mehod
        list.addLast(node2);
        
       
        DNode node3 = new DNode("C", null, null);
        list.addLast(node3);
        
        
         // Create a node to be added to the list
        DNode node4 = new DNode("D", null, null);
        list.addLast(node4);
        
        DNode node5 = new DNode("E", null, null);
        list.addLast(node5);
        
        // Print the elements in the list
        System.out.println(list.toString());

        
        System.out.println("Swapping...");

        list.swap(node2, node3);
        System.out.println(list.toString());

    }
    
}
