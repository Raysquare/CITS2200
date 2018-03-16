/**
 * A basic cyclic deque
 *
 * @author Fang Kai Gan 21896665
 * @version 1.0
 * @since April 4 2017
 */

import CITS2200.*;

public class DequeCyclic implements Deque {
    public Object[] deque;
    private int first;
    private int last = 1;
    private int currentSize;

    /**
     * The constructor creates an empty deque of size s
     *
     * @param s size of stack
     */
    public DequeCyclic(int s) {
        deque = new Object[s];
    }

    /**
     * Add object c as the left-most object in the deque, or throw an Overflow exception if the deque is full
     *
     * @param c Object to be pushed
     * @throws Overflow if deque is full
     */
    @Override
    public void pushLeft(Object c) throws Overflow {
        if (!isFull()) {
            deque[first] = c;
            first = modFunction(first - 1);
            currentSize++;
        } else throw new Overflow("Full Deque");
    }


    /**
     * Add object c as the right-most object in the deque, or throw an Overflow exception if the deque is full.
     *
     * @param c Object to be pushed
     * @throws Overflow if deque is empty
     */
    @Override
    public void pushRight(Object c) throws Overflow {
        if (!isFull()) {
            deque[last] = c;
            last = modFunction(last + 1);
            currentSize++;
        } else throw new Overflow("Full Deque");
    }


    /**
     * Remove and return the left-most object in the deque, or throw an Underflow exception if the deque is empty.
     *
     * @return Left-most object in deque
     * @throws Underflow if deque is empty
     */
    @Override
    public Object popLeft() throws Underflow {
        if (!isEmpty()) {
            first = modFunction(first + 1);
            Object temp = deque[first];
            deque[first] = null;
            currentSize--;
            return temp;
        } else throw new Underflow("Empty Deque");
    }


    /**
     * Remove and return the right-most object in the deque, or throw an Underflow exception if the deque is empty.
     *
     * @return Right-most object in deque
     * @throws Underflow if deque is empty
     */
    @Override
    public Object popRight() throws Underflow {
        if (!isEmpty()) {
            last = modFunction(last - 1);
            Object temp = deque[last];
            deque[last] = null;
            currentSize--;
            return temp;
        } else throw new Underflow("Empty Deque");
    }


    /**
     * Return the right-most object in the deque, or throw an Underflow exception if the deque is empty
     *
     * @return Right-most object in deque
     * @throws Underflow if deque is empty
     */
    @Override
    public Object peekRight() throws Underflow {
        if (!isEmpty()) {
            return deque[modFunction(last - 1)];
        } else throw new Underflow("Empty Deque");
    }


    /**
     * Return the left-most object in the deque, or throw an Underflow exception if the deque is empty
     *
     * @return Left-most object in deque
     * @throws Underflow if deque is empty
     */
    @Override
    public Object peekLeft() throws Underflow {
        if (!isEmpty()) {
            return deque[modFunction(first + 1)];
        } else throw new Underflow("Empty Deque");
    }


    /**
     * Return true iff the deque is empty, false otherwise.
     *
     * @return true iff deque is empty
     */
    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }


    /**
     * Return true iff the deque is full, false otherwise.
     *
     * @return true iff deque is full
     */
    @Override
    public boolean isFull() {
        return deque.length == currentSize;
    }


    /**
     * This function adjusts the pointer values preventing it from going out of bounds.
     * If the x to be adjusted is within array, then it is not adjusted,
     * Otherwise, it will be wrapped to the front/back of the array.
     *
     * @param x to be adjusted.
     * @return correct pointer location
     */
    private int modFunction(int x) {
        if (x % deque.length == -1) return deque.length - 1;
        else if (x >= deque.length) return 0;
        else return x;
    }
}
