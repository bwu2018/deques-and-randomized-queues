/******************************************************************************
 *  Name:    Brandon Wu
 *  Login:   bwu2018
 *  Precept: P02
 *
 *  Partner Name:    N/A
 *  Partner Login:   N/A
 *  Partner Precept: N/A
 * 
 *  Compilation:  javac-algs4 RandomizedQueue.java
 *  Execution:    java-algs4 RandomizedQueue
 *  Dependencies: Item.java Iterator.java
 *
 *  Description:  Implementing a Queue that chooses elements at random using an array
 ******************************************************************************/
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int size;
    
    /**
     * construct an empty randomized queue
     */
    public RandomizedQueue() {
        items = (Item[]) new Object[2];
    }
    
    /**
     * is the randomized queue empty?
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * return the number of items on the randomized queue
     * @return
     */
    public int size() {
        return size;
    }
    
    private void shrink() {
        if (size > 0 && size == items.length/4) resize(items.length/2);
    }
    
    private void expand() {
        if (size == items.length) resize(2*items.length);
    }
    
    private void resize(int length) {
        Item[] holder = (Item[]) new Object[length];
        for (int i = 0; i < size; i++) {
            holder[i] = items[i];
        }
        items = holder;
    }
    
    /**
     * add the item
     * @param item
     */
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        expand();
        items[size++] = item;
    }

    /**
     * remove and return a random item
     * @return
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int random = StdRandom.uniform(0, size);
        Item item = items[random];
        items[random] = items[size-1];
        items[size-1] = null;
        size--;
        shrink();
        return item;
    }

    /**
     * return a random item (but do not remove it)
     * @return
     */
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        return items[StdRandom.uniform(0, size)];
    }
    
    /**
     * return an independent iterator over items in random order
     */
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }
    
    private class RandomIterator implements Iterator<Item> {
        
        private final Item[] holder;
        private int index;
        
        public RandomIterator() {
            holder = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                holder[i] = items[i];
            }
            StdRandom.shuffle(holder);
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return holder[index++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    public static void main(String[] args) {
        // unit testing (optional)
    }
}