package binary_tree;


public interface Tree <E>
{
    /**
     * return the size of the tree
     * @return 
     */
    public int getSize();
    
    /**
     * return weather the tree is empty
     * @return 
     */
    public boolean isEmpty();
    
    
    /**
     * return the root of the tree
     * @return the root node
     * @throws EmptyTreeException 
     */
    public BTNode<E> getRoot() throws EmptyTreeException;
    
    /**
     * return the parent of a given node
     * @return the parent of a given node
     * @throws InvalidPositionException
     * @throws BoundaryViolationException 
     */
    public BTNode<E> getThisParent(BTNode<E> v) throws InvalidPositionException, BoundaryViolationException;
    
   
    /**
     * check if the node is an internal node
     * @param v node to be checked 
     * @return a boolean value 
     * @throws InvalidPositionException 
     */
    public boolean isInternal (BTNode<E> v) throws InvalidPositionException;
    
    /**
     * check if the node is an external node
     * @param v node to be checked
     * @return a boolean value
     * @throws InvalidPositionException 
     */
    public boolean isExternal (BTNode<E> v) throws InvalidPositionException;
    
    /**
     * check if the node is the root
     * @param v node to be checked
     * @return a boolean value
     * @throws InvalidPositionException 
     */
    public boolean isRoot (BTNode<E> v) throws InvalidPositionException;
}
