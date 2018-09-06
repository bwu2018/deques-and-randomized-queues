/******************************************************************************
 *  Name:    Brandon Wu
 *  Login:   bwu2018
 *  Precept: P02
 *
 *  Partner Name:    N/A
 *  Partner Login:   N/A
 *  Partner Precept: N/A
 * 
 *  Compilation:  javac-algs4 Deque.java
 *  Execution:    java-algs4 Deque
 *  Dependencies: Item.java Iterator.java
 *
 *  Description:  Implementing a Deque using linked structures
 ******************************************************************************/
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int size;

    private static class Node<Item> {

        private Node<Item> next;
        private Node<Item> prev;
        private Item data;

        public Node(Node<Item> next, Node<Item> prev, Item data) {
            this.next = next;
            this.prev = prev;
            this.data = data;
        }
        public Node<Item> getNext() {
            return next;
        }
        public void setNext(Node<Item> node) {
            next = node; 
        }
        public Node<Item> getPrev() {
            return prev;
        }
        public void setPrev(Node<Item> node) {
            prev = node;
        }
        public Item getData() {
            return data;
        }
        public void setElement(Item elem) {
            data = elem;
        }
    }

    /**
     * construct an empty deque
     */
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * is the deque empty?
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * return the number of items on the deque
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * add the item to the front
     * @param item
     */
    public void addFirst(Item item) {
        if (item == null) { 
            throw new IllegalArgumentException();
        }
        if (isEmpty()) { 
            Node<Item> newFirst = new Node<Item>(null, null, item);
            first = newFirst;
            last = newFirst;
            size++;
            return;
        }
        Node<Item> oldFirst = first;
        first = new Node<Item>(oldFirst, null, item);
        oldFirst.setPrev(first);
        size++;
    }

    /**
     * add the item to the end
     * @param item
     */
    public void addLast(Item item) {
        if (item == null) { 
            throw new IllegalArgumentException();
        }
        if (isEmpty()) { 
            Node<Item> newLast = new Node<Item>(null, null, item);
            first = newLast;
            last = newLast;
            size++;
            return;
        }
        Node<Item> oldLast = last;
        last = new Node<Item>(null, oldLast, item);
        oldLast.setNext(last);
        size++;
    }

    /**
     * remove and return the item from the front
     * @return
     */
    public Item removeFirst() {
        if (isEmpty()) { 
            throw new NoSuchElementException();
        }
        Item oldFirstData = first.data;
        if (size > 1) {
            first = first.next;
            first.prev = null;
        } else {
            first = null;
            last = null;
        }
        size--;
        return oldFirstData;
    }

    /**
     * remove and return the item from the end
     * @return
     */
    public Item removeLast() {
        if (isEmpty()) { 
            throw new NoSuchElementException();
        }
        Item oldLastData = last.data;
        if (size > 1) {
            last = last.prev;
            last.next = null;
        } else {
            first = null;
            last = null;
        }
        size--;
        return oldLastData;
    }

    /**
     * return an iterator over items in order from front to end
     */
    public Iterator<Item> iterator() {
        return new WuIterator();
    }

    private class WuIterator implements Iterator<Item> {
        private Node<Item> current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next()
        {
            if (current == null) {
                throw new NoSuchElementException();
            }
            Item data = current.data;
            current = current.next;
            return data;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        // unit testing (optional)
    }
}