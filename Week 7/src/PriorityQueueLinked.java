/**
 * A Priority Queue implementation
 *
 * @author Fang Kai Gan 21896665
 * @version 1.0
 * @since May 3 2017
 */

import CITS2200.*;


public class PriorityQueueLinked implements PriorityQueue {
    public Link<Object> front;


    /**
     * Constructor
     */
    public PriorityQueueLinked() {
        front = null;
    }

    /**
     * check if queue is empty
     *
     * @return true iff queue is empty
     */
    public boolean isEmpty() {
        return front == null;
    }


    /**
     * Puts object into queue
     *
     * @param o Object
     * @param i priority
     * @throws IllegalValue if value is less than 0
     */
    public void enqueue(Object o, int i) throws IllegalValue {
        if (i < 0) throw new IllegalValue("Enter value more than 0");

        if (isEmpty() || i > front.priority) {
            front = new Link<>(o, i, front);
        } else {
            Link<Object> l = front;
            while (l.next != null && l.next.priority >= i) {
                l = l.next;
            }
            l.next = new Link<>(o, i, l.next);
        }
    }

    /**
     * Return first element if queue if it is not empty
     *
     * @return first element if queue is not empty
     * @throws Underflow if queue is empty
     */
    public Object examine() throws Underflow {
        if (!isEmpty()) {
            return front.element;
        } else throw new Underflow("Empty Queue");
    }


    /**
     * Removes first element if queue is not empty
     *
     * @return first element if queue is not empty
     * @throws Underflow if queue is empty
     */
    public Object dequeue() throws Underflow {
        if (!isEmpty()) {
            Object temp = front.element;
            front = front.next;
            return temp;
        } else throw new Underflow("Empty Queue");
    }

    /**
     * Makes instance of iterator
     *
     * @return instance of iterator
     */
    public Iterator iterator() {
        return new Iterate();
    }

    public class Iterate implements Iterator {

        Link pointer;

        public Iterate() {
            pointer = PriorityQueueLinked.this.front;
        }

        /**
         * Returns next element in queue
         *
         * @return next element in queue
         * @throws OutOfBounds if queue is empty
         */
        public Object next() throws OutOfBounds {
            if (hasNext()) {
                Object temp = pointer.element;
                pointer = pointer.next;
                return temp;
            } else throw new OutOfBounds("No next element");
        }

        /**
         * Returns if queue has next element
         *
         * @return if queue has next element
         */
        public boolean hasNext() {
            return pointer != null;
        }
    }


    public class Link<Object> {
        Object element;
        int priority;
        Link<Object> next;

        public Link(Object e, int p, Link<Object> n) {
            this.element = e;
            this.priority = p;
            this.next = n;
        }
    }


}
