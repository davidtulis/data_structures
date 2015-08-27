package doublyLinkedList;

/**
 * 
 * @author david tulis
 *
 */

public  class DList
{
	protected int size;	 // number of elements
	protected DNode header, trailer;  // sentinels

	/** Constructor that creates an empty list */
	public DList()
	{
		size = 0;
		header =  new DNode(null, null,  null);  // create header
		trailer =  new DNode(null, header,  null);  // create trailer
		header.setNext(trailer);	 // make header and trailer point to each other
	}

	/**
	 * method to swap two nodes
	 * @param a the first node
	 * @param b the second node
	 * @precondition a comes before b in the list
	 */
	public void swap(DNode a, DNode b) {
		DNode aBefore=a.getPrev();
		DNode aAfter=a.getNext();
		DNode bBefore=b.getPrev();
		DNode bAfter=b.getNext();
		
		DNode v=header;
		while (v.getNext()!=a) {
			v=v.getNext();
		}
		//head should be set to point to the node before a
		v.setNext(b); //update node before a to point to b
		v=v.getNext(); //update v to point to b
		v.setPrev(aBefore);//update node b to point to the correct nodes
		v.setNext(aAfter);
		v=v.getNext(); //v is now pointing to the node following a in the original list
		v.setPrev(b);
		
		try {
			while (v.getNext()!=b) {
				v=v.getNext();
			}
		} catch (NullPointerException e) { //b is at the end of the list
			v.setNext(a); 
			v=v.getNext(); 
			v.setPrev(bBefore);
			v.setNext(null);
		}
		
		v.setNext(a); 
		v=v.getNext(); 
		v.setPrev(bBefore);
		v.setNext(bAfter);
		v=v.getNext(); 
		v.setPrev(a);
		
	}
	
	/** Returns the number of elements in the list */
	public int getSize()
	{
		return size;
	}

	/** Returns whether the list is empty */
	public boolean isEmpty()
	{
		return (size == 0);
	}

	/** Returns the first node of the list */
	public DNode getFirst()  throws IllegalStateException
	{
		if (isEmpty())  throw  new IllegalStateException("List is  empty");
		return header.getNext();
	}

	/** Returns the last node of the list */
	public DNode getLast()  throws IllegalStateException
	{
		if (isEmpty())  throw  new IllegalStateException("List is  empty");
			return trailer.getPrev();
	}

	/** Returns the node before the given node v. An error occurs if v
	* is the header */
	public DNode getPrev(DNode v)  throws IllegalArgumentException
	{
		if (v == header)  throw  new IllegalArgumentException ("Cannot move  back  past  the  header of  the  list");
			return v.getPrev();
	}

	/** Returns the node after the given node v. An error occurs if v
	* is the trailer */
	public DNode getNext(DNode v)  throws IllegalArgumentException
	{
		if (v == trailer)  throw  new IllegalArgumentException ("Cannot move  forward past  the  trailer of  the  list");
			return v.getNext();
	}

	/** Inserts the given node z before the given node v. An error
	* occurs if v is the header */
	public void addBefore(DNode v, DNode z)  throws IllegalArgumentException
	{
		DNode u = getPrev(v);  // may throw an IllegalArgumentException
		z.setPrev(u);
		z.setNext(v);
		v.setPrev(z);
		u.setNext(z);
		size++;
	}

	/** Inserts the given node z after the given node v. An error occurs
	* if v is the trailer */
	public void addAfter(DNode v, DNode z)
	{
		DNode w = getNext(v);  // may throw an IllegalArgumentException
		z.setPrev(v);
		z.setNext(w);
		w.setPrev(z);
		v.setNext(z);
		size++;
	}

	/** Inserts the given node at the head of the list */
	public void addFirst(DNode v)
	{
		addAfter(header, v);
	}

	/** Inserts the given node at the tail of the list */
	public void addLast(DNode v)
	{
		addBefore(trailer, v);
	}

	/** Removes the given node v from the list. An error occurs if v is
	* the header or trailer */
	public void remove(DNode v)
	{
		DNode u = getPrev(v);  // may throw an IllegalArgumentException
		DNode w = getNext(v);  // may throw an IllegalArgumentException

		// unlink the node from the list
		w.setPrev(u);
		u.setNext(w);
		v.setPrev(null);
		v.setNext(null);
		size--;
	}

	/** Returns whether a given node has a previous node */
	public boolean hasPrev(DNode v)
	{
		return v != header;
	}

	/** Returns whether a given node has a next node */
	public boolean hasNext(DNode v)
	{
		return v != trailer;
	}

	/** Returns a string representation of the list */
	public String toString()
	{
		String s =  "[";
		DNode v = header.getNext();
		while (v != trailer)
		{
			s += v.getElement();
			v = v.getNext();
			if (v != trailer)
				s +=  ",";
		}
		s +=  "]";
		return s;
	}

	/** Insertion-sort for a doubly linked list of class DList.  */
	public static void sort(DList L)
	{
		if (L.getSize() <= 1)
			return; // L is already sorted in this case

		DNode pivot;	 // pivot node
		DNode ins;	 // insertion point
		DNode end = L.getFirst(); // end of run

		while (end != L.getLast())
		{
			pivot = end.getNext();  // get the next pivot node
			L.remove(pivot);	 // remove it
			ins = end;	 // start searching from the end of the sorted run
			while (L.hasPrev(ins) && ins.getElement().compareTo(pivot.getElement()) > 0)
				ins = ins.getPrev();  // move left
				L.addAfter(ins,pivot);  // add the pivot back, after insertion point
				if (ins == end)	 // we just added pivot after end in this case
					end = end.getNext();  // so increment the end marker
		}
	}

}