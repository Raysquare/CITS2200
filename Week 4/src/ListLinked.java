/**
 * A basic LinkedList
 *
 * @author Fang Kai Gan 21896665
 * @version 1.0
 * @since April 4 2017
 */

import CITS2200.*;

public class ListLinked implements List {

    private Link before;
    private Link after;

    public ListLinked() {
        after = new Link(null, null);
        before = new Link(null, after);
    }

    /**
     * Check if array is empty
     *
     * @return true if the list is empty
     */
    public boolean isEmpty() {
        return before.successor == after;
    }

    /**
     * Check if window is before first position
     *
     * @param windowLinked
     * @return true if windowLinked is over the before first position.
     */
    public boolean isBeforeFirst(WindowLinked windowLinked) {
        return windowLinked.link == before;
    }

    /**
     * Check if window is after last position.
     *
     * @param windowLinked
     * @return True if windowLinked is over the after last position
     */
    public boolean isAfterLast(WindowLinked windowLinked) {
        return windowLinked.link == after;
    }

    /**
     * Initialises windowLinked to the before first position
     *
     * @param windowLinked
     */
    public void beforeFirst(WindowLinked windowLinked) {
        windowLinked.link = before;
    }

    /**
     * Initialises windowLinked to the after last position.
     *
     * @param windowLinked
     */
    public void afterLast(WindowLinked windowLinked) {
        windowLinked.link = after;
    }

    /**
     * Moves windowLinked to the next window position.
     *
     * @param windowLinked
     * @throws OutOfBounds if windowLinked is over the after last position
     */
    public void next(WindowLinked windowLinked) throws OutOfBounds {
        if (!isAfterLast(windowLinked)) windowLinked.link = windowLinked.link.successor;
        else throw new OutOfBounds("Calling next after list end.");
    }

    /**
     * Moves windowLinked to the previous window position.
     *
     * @param windowLinked
     * @throws OutOfBounds if windowLinked is before first position.
     */
    public void previous(WindowLinked windowLinked) throws OutOfBounds {
        if (!isBeforeFirst(windowLinked)) {
            Link current = before.successor;
            Link previous = before;
            while (current != windowLinked.link) {
                previous = current;
                current = current.successor;
            }
            windowLinked.link = previous;
        } else throw new OutOfBounds("Calling previous before start of list.");
    }

    /**
     * Element o is added to the list before windowsLinked.
     *
     * @param o            Object o.
     * @param windowLinked
     * @throws OutOfBounds if windowLinked is before the first position.
     */
    public void insertBefore(Object o, WindowLinked windowLinked) throws OutOfBounds {
        if (!isBeforeFirst(windowLinked)) {
            windowLinked.link.successor = new Link(windowLinked.link.item, windowLinked.link.successor);
            if (isAfterLast(windowLinked)) after = windowLinked.link.successor;
            windowLinked.link.item = o;
            windowLinked.link = windowLinked.link.successor;
        } else throw new OutOfBounds("Inserting before beginning of list");
    }

    /**
     * Element o is added to the list after windowsLinked.
     *
     * @param o            Object o.
     * @param windowLinked
     * @throws OutOfBounds if windowLinked is after the last position.
     */
    public void insertAfter(Object o, WindowLinked windowLinked) throws OutOfBounds {
        if (!isAfterLast(windowLinked)) {
            windowLinked.link.successor = new Link(o, windowLinked.link.successor);
        } else throw new OutOfBounds("Inserting end of list");

    }

    /**
     * Returns the element under windowLinked.
     *
     * @param windowLinked
     * @return element in windowLinked
     * @throws OutOfBounds if windowLinked is before first or after last position.
     */
    public Object examine(WindowLinked windowLinked) throws OutOfBounds {
        if (!isAfterLast(windowLinked) && !isBeforeFirst(windowLinked)) {
            return windowLinked.link.item;
        } else throw new OutOfBounds("Examining before first or after last position");
    }


    /**
     * Replaces the element under windowLinked with o and returns the old element
     *
     * @param o            Object o.
     * @param windowLinked
     * @return original element at windowLinked.
     * @throws OutOfBounds if windowLinked is before first or after last position.
     */
    public Object replace(Object o, WindowLinked windowLinked) throws OutOfBounds {
        if (!isAfterLast(windowLinked) && !isBeforeFirst(windowLinked)) {
            Object temp = windowLinked.link.item;
            windowLinked.link.item = o;
            return temp;
        } else throw new OutOfBounds("Replacing before first or after last position");
    }

    /**
     * Delete element at windowLinked and returns the original element. WindowLinked is then moved to the next link.
     *
     * @param windowLinked
     * @return element that was deleted.
     * @throws OutOfBounds if windowLinked is before first or after last position.
     */
    public Object delete(WindowLinked windowLinked) throws OutOfBounds {
        if (!isAfterLast(windowLinked) && !isBeforeFirst(windowLinked)) {
            Object temp = windowLinked.link.item;
            if (windowLinked.link.successor == after) after = windowLinked.link;
            windowLinked.link.item = windowLinked.link.successor.item;
            windowLinked.link.successor = windowLinked.link.successor.successor;

            return temp;
        } else throw new OutOfBounds("Deleting before first or after last position");
    }
}

