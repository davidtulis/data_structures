package binary_tree;

import java.util.Scanner;

/**
 *
 * @author CPSC 3200
 */
public class BinaryTreeTester 
{
    public static void main (String [] args)
    {
        LinkedBinaryTree<Integer> bTree = new LinkedBinaryTree();

        Scanner scan = new Scanner(System.in);
        
        System.out.println("Enter a value to be added to the binary search tree, -1 to stop");
        int num = scan.nextInt();

        while (num != -1)
        {
            bTree.insertBST(num);
            System.out.println("Size = " + bTree.getSize());
            System.out.println("Enter a value to be added to the binary search tree, -1 to stop");
            num = scan.nextInt();

        }
        
        System.out.println("Size: " + bTree.getSize());
        System.out.println("Height: " + bTree.getHeight(bTree.getRoot()));
        System.out.print("In order sequence: ");
        bTree.inOrder(bTree.getRoot());
        System.out.print("Pre order sequence: ");
        bTree.preOrder(bTree.getRoot());
        System.out.println("");
        
        System.out.println("Which node do you want deleted? Input element: ");
        int elemOfNode=scan.nextInt();
        BTNode<Integer> searchedNode=bTree.search(elemOfNode, bTree.getRoot());
        while (searchedNode==null) {
        	System.out.println("I cant find that node. try again");
        	elemOfNode=scan.nextInt();
        }
        bTree.deleteNode(searchedNode);
        System.out.println("Pre order tree after delete: ");
        bTree.preOrder(bTree.getRoot());
        System.out.println("There are " + bTree.internalCounter(bTree.getRoot()) + " internal nodes");
        
        
        
        
    }
    
}
