package singlyLinkedList;

/*
 * Test and tun a singly linked list
 */

/**
 * @author CPSC
 */
public class ListTest 
{
    public static void main (String [] args)
    {
        SList l = new SList();
        
        Node newnode = new Node("A", null);
        
        Node newnode2 = new Node("B", null);
        
        Node newnode3 = new Node("C", null);
       
        Node newnode4 = new Node("D", null);
        
        Node newnode5 = new Node("E", null);
        
        l.addFirst(newnode5);
        l.addFirst(newnode4);
        l.addFirst(newnode3);
        l.addFirst(newnode2);
        l.addFirst(newnode);

        System.out.println(l.toString());
        System.out.println(l.getFirst());
        
    }
}
