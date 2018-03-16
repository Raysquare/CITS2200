/**
 * A basic Stack
 *
 * @author Fang Kai Gan 21896665
 * @version 1.0
 * @since March 21 2017
 */

import CITS2200.Overflow;
import CITS2200.Stack;
import CITS2200.Underflow;

public class StackBlock implements Stack {

    private java.util.Stack<Object> block = new java.util.Stack<>();
    private int size;
    private int stackCount;

    /**
     * The constructor creates a stack with size s
     *
     * @param s size of stack
     */
    public StackBlock(int s) {
        size = s;
    }

    /**
     * Check if stack is empty
     *
     * @return true if stack is empty
     */
    public boolean isEmpty() {
        return (stackCount == 0);
    }

    /**
     * Check if stack is full
     *
     * @return true if stack is full
     */
    public boolean isFull() {
        return (stackCount == size);
    }

    /**
     * Writes Object o to the top of the stack
     *
     * @param o Object o
     * @throws Overflow if stack is full
     */
    public void push(Object o) throws Overflow {
        if (!isFull()) {
            block.push(o);
            stackCount++;
        } else throw new Overflow("Stack is Full");
    }

    /**
     * Return Object at the current top of stack
     *
     * @return Object
     * @throws Underflow if stack is empty
     */
    public Object examine() throws Underflow {
        if (!isEmpty()) {
            return block.peek();
        } else throw new Underflow("Stack is Empty");

    }

    /**
     * Remove Object from top of stack
     *
     * @return Object
     * @throws Underflow if stack is empty
     */
    public Object pop() throws Underflow {
        if (!isEmpty()) {
            Object copy = block.peek();
            block.pop();
            stackCount--;
            return copy;
        } else throw new Underflow("Stack is empty");
    }

}
