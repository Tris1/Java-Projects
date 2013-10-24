/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package binarytree;

public class BinarySearchTree<E extends Comparable<E>> {

    private Node<E> root;   // reference to tree root
    private boolean added;  // flag to indicate insertion result
    private E deleteReturn;  // the data deleted

    private static class Node<E> {
        private E data;
        private Node<E> left;
        private Node<E> right;

        public Node() {
            data = null;
            left = null;
            right = null;
        }

        public Node(E item) {
            this(item, null, null);
        }

        public Node(E item, Node<E> l, Node<E> r) {
            data =item;
            left = l;
            right = r;
        }
    }

    /** Creates a new instance of BinarySearchTree */
    public BinarySearchTree() {
        root = null;
    }

    public BinarySearchTree(E item) {
        root = new Node<E> (item);
    }
    public BinarySearchTree(Node<E> node, BinarySearchTree leftNode, BinarySearchTree rightNode){
        root = node;
        BinarySearchTree<E> left = leftNode;
        BinarySearchTree<E> right = rightNode;
    }


    public BinarySearchTree(E data, BinarySearchTree<E> leftTree, BinarySearchTree<E> rightTree){
        root = new Node<E>(data);
        if(leftTree != null){
            root.left = leftTree.root;
        } else {
            root.left = null;
        }
        if(rightTree != null){
            root.right = rightTree.root;
        } else {
            root.right = null;
        }
    }
    public boolean search(E target) {
        return search(root, target);
    }

    private boolean search(Node<E> r, E target) {
        if(r == null)
        return false;
        
        if(target == r.data)
            return true;
        
        else if(target.compareTo(r.data) < 0){
            return search(r.left, target);

        }
        else //(target.compareTo(r.data) > 0){
            return search(r.right, target);

        }


    /** Insert E where it belongs in the tree. */
    public void insert(E item) {
         root = insert(root, item);

    }

    /** Insert E where it belongs in the tree and set added to
        true if the insertion takes place.
    */
    private Node<E> insert(Node<E> r, E item) {
        if(r == null){
           BinarySearchTree<E> test = new BinarySearchTree(item);
            added = true;
            return test.root;
        }
        else if(r.data == item){
            added = false;
            return null;
        }
        else if(item.compareTo(r.data) < 0){
                    r.left = insert(r.left, item);
                    return r;
        }
        else{
            r.right = insert(r.right, item);
            return r;
       //return null;
    }
    }
    /** Perform an inorder traversal */
    public String inOrder() {
        return inOrder(root);
    }

    private String inOrder(Node<E> r) {
        //StringBuilder test = new StringBuilder();
        if(r != null)
           return inOrder(r.left) +
                    r.data +
                 inOrder(r.right);
        else
           return "";

    }

    /** Return a string representation of the tree in preorder form
        as shown on p. 413
    */
    @Override
    public String toString() {
       StringBuilder test = new StringBuilder();
       preOrderTraverse(root, 1, test);
       return test.toString();

    }

    private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb){
      for(int i = 1; i < depth; i++){
          sb.append("  ");
      }
      if(node == null){
          sb.append("null\n");
      } else {
          sb.append(node.data);
          sb.append("\n");
          preOrderTraverse(node.left, depth + 1, sb);
          preOrderTraverse(node.right, depth + 1, sb);

      }
    }



    /** Delete the argument from the tree and return the node data.
     * Set deleted to true if successful.
     */
    public E delete(E item) {
       root = delete(root, item);
       return deleteReturn;
    }

    private Node<E> delete(Node<E> r, E item) {
        if(r == null){
            deleteReturn = null;
            return r;
        }

        int compResult = item.compareTo(r.data);
        if(compResult < 0){
            r.left = delete(r.left, item);
            return r;
        }else if(compResult > 0){
            r.right = delete(r.right, item);
            return r;
        }else {
            deleteReturn = r.data;
            if(r.left == null){
                return r.right;
            }else if(r.right == null){
                return r.left;
            }else {
                if(r.left.right == null){
                    r.data = r.left.data;
                    r.left = r.left.left;
                    return r;
                }else {
                    r.data = findLargestChild(r.left);
                    return r;
                }
            }
        }

    }

    //Finds the largest child of a node
    private E findLargestChild(Node<E> parent){
        if(parent.right.right == null){
            E returnValue = parent.right.data;
            parent.right = parent.right.left;
            return returnValue;
        }else{
            return findLargestChild(parent.right);
        }
    }
    /** Compare this binary tree to another binary tree */
    public boolean equals(BinarySearchTree other) {
        BinarySearchTree<E> newTree = other;
        if(newTree == null && this != null){
      return false;
      }

      else if(this == null && newTree != null){
          return false;
      }
        else if (this.getClass() != newTree.getClass())
            return false;
        else{
            return equals(this.root, newTree.root);
        }
    }

    private boolean equals(Node<E> thisList, Node<E> listCompared){
        if(thisList == null && listCompared != null ||thisList != null && listCompared == null ){
           return false;
        }

            else if(thisList == null && listCompared == null){
                return true;
            }
            else if(thisList.data != listCompared.data){
                return false;
            }else if(thisList.data.compareTo(listCompared.data)==0){
            return equals(thisList.left, listCompared.left) && equals(thisList.right, listCompared.right);
            }else{
            
        return false;
    }
    }
    /** get the level of a binary tree */
    public int level() {
       return level(root);
    }

    private int level(Node<E> node){
        if(node == null){
            return 0;
        }
        else {
    int lDepth = level(node.left);
    int rDepth = level(node.right);

    // use the larger + 1
    return(Math.max(lDepth, rDepth) + 1);
  }
    }

    /** get the number of nodes in the tree */
    public int countNodes() {
        return countNodes(root);
    }

    private int countNodes(Node<E> node){
        if(node == null)
            return 0;

        else{
    int count = 1;

    count += countNodes(node.left);
    count += countNodes(node.right);

    return count;
        }
    }

    /** return the mirror image of this tree.
     *  the left and right subtrees of every node should be
     *  transposed.
     */
    public BinarySearchTree<E> mirrorImage() {
        return mirrorImage(root);
    }

    private BinarySearchTree<E> mirrorImage(Node<E> node){
        
        if(node == null){      
        return null;
        }

        else{
            BinarySearchTree<E> l = mirrorImage(node.left);
            BinarySearchTree<E> r = mirrorImage(node.right);
            return new BinarySearchTree(node.data, r, l);
        }
        
    }
}
