/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package binarytree;

/**
 *
 * @author Gloria
 */
public class BinarySearchTreeTester {
public static void main(String[] args){
        BinarySearchTree test = new BinarySearchTree("jack");
        BinarySearchTree test2 = new BinarySearchTree("jack");
        test.insert("jill");
        test.insert("cat");
        test.insert("dog");

        test2.insert("jill");
        test2.insert("cat");
        test2.insert("dog");

        System.out.println(test.inOrder());
       System.out.println(test.toString());
        System.out.println("Total # of nodes: " + test.countNodes());
        System.out.println("This tree has " + test.level() + " levels");
        System.out.println(test.delete("jack"));
        System.out.println(test.equals(test2));
        BinarySearchTree newTree = test.mirrorImage();
        System.out.println(newTree.toString());
        System.out.println(newTree.inOrder());

    }
}
