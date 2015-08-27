package binary_tree;

public interface BinaryTree <E> extends Tree<E>
{
    public BTNode<E> getThisLeft (BTNode <E> v) throws InvalidPositionException, BoundaryViolationException;

    public BTNode<E> getThisRight (BTNode <E> v) throws InvalidPositionException, BoundaryViolationException;

    public boolean hasLeft(BTNode <E> v) throws InvalidPositionException;

    public boolean hasRight(BTNode <E> v) throws InvalidPositionException;

    
}
