/**
 * A basic Binary Tree Implementation
 *
 * @author Fang Kai Gan 21896665
 * @version 1.0
 * @since April 25 2017
 */

import CITS2200.BinaryTree;
import CITS2200.Iterator;
import CITS2200.OutOfBounds;

import java.util.LinkedList;

public class BinTree<E> extends BinaryTree<E> {
    private LinkedList<E> array;

    public BinTree() {
        super();
    }

    public BinTree(E item, BinaryTree<E> b1, BinaryTree<E> b2) {
        super(item, b1, b2);
    }

    /**
     * Creates new instance of iterator
     *
     * @return Iterator
     */

    public Iterator<E> iterator() {
        array = new LinkedList<>();
        iterateTree(this);
        return new binTreeIterator();
    }


    /**
     * Check if object O is the same as BinaryTree
     *
     * @param o Object
     * @return True iff Object o is the same as Binary Tree
     */
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof BinaryTree)) return false;
        if (this.isEmpty() ^ ((BinaryTree) o).isEmpty()) return false;
        if (this.isEmpty() && ((BinaryTree) o).isEmpty()) return true;
        if (this.getItem().equals(((BinaryTree) o).getItem())) {
            if (this.getLeft().equals(((BinaryTree) o).getLeft()) && this.getRight().equals(((BinaryTree) o).getRight()))
                return true;
            else return false;
        }
        return false;
    }

    /**
     * A recursive call that iterates the child of a parent BinaryTree
     *
     * @param tree to be recursively callsed
     */
    private void iterateTree(BinaryTree<E> tree) {
        array.push(tree.getItem());
        if (!tree.getLeft().isEmpty()) iterateTree(tree.getLeft());
        if (!tree.getRight().isEmpty()) iterateTree(tree.getRight());
    }

    class binTreeIterator implements Iterator<E> {
        /**
         * Returns next object
         *
         * @return next object in list
         * @throws OutOfBounds if there is not more elements
         */
        public E next() throws OutOfBounds {
            if (!hasNext()) throw new OutOfBounds("End of list");
            return array.pop();
        }

        /**
         * Returns if there is any elements lest in list.
         *
         * @return iff there is more elements in list
         */
        public boolean hasNext() {
            return (array.peek() != null);
        }

    }

}

