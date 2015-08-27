package binary_tree;
/**
 *
 * @author CPSC 3200
 */
public class BTNode<E> 
{
    private E element;
    private BTNode<E> left, right, parent;
    
    /**
     * Overloaded constructor
     * @param element the value
     * @param parent a reference to the parent
     * @param left a reference to the left child
     * @param right a reference to the right child
     */
    public BTNode(E element, BTNode <E> parent, BTNode<E> left, BTNode<E> right)
    {
        setElement(element);
        setParent(parent);
        setLeft(left);
        setRight(right);
    }
    
    /**
     * get the value stored
     * @return element
     */
    public E getElement()
    {
        return element;
    }
    
    /**
     * set the value stored in the node
     * @param elem value to be stored 
     */
    public void setElement(E elem)
    {
        element = elem;
    }
    
    /**
     * get the node at the left
     * @return left
     */
    public BTNode<E> getLeft() 
    {
        return left;
    }
    
    /**
     * set the node at the left
     * @param v node to be set as a left child
     */
    public void setLeft(BTNode<E> v)
    {
        left = v;
    }
    
    /**
     * get the right child
     * @return right
     */
    public BTNode<E> getRight()
    {
        return right;
    }
    
    /**
     * set the right child
     * @param v node to be set as right child
     */
    public void setRight(BTNode<E> v)
    {
        right = v;
        
    }
    
    /**
     * get the parent 
     * @return parent
     */
    public BTNode<E> getParent()
    {
        return parent;
    }
    
    /**
     * set the parent of a node
     * @param v node to be set as parent
     */
    public void setParent(BTNode<E> v)
    {
        parent = v;
    }
    
}
