import edu.princeton.cs.algs4.StdIn;

/******************************************************************************
 *  Name:    Brandon Wu
 *  Login:   bwu2018
 *  Precept: P02
 *
 *  Partner Name:    N/A
 *  Partner Login:   N/A
 *  Partner Precept: N/A
 * 
 *  Compilation:  javac-algs4 Permutation.java
 *  Execution:    java-algs4 Permutation k < <filename>
 *  Dependencies: RandomizedQueue.java StdIn.java StdOut.java
 *
 *  Description:  Takes an integer k as a command-line argument; reads in a sequence of strings from standard input using StdIn.readString(); and prints exactly k of them, uniformly at random. 
 *  Prints each item from the sequence at most once. 
 ******************************************************************************/
public class Permutation {

    public static void main(String[] args) {
        
        RandomizedQueue<String> holder = new RandomizedQueue<String>();
        
        while (!StdIn.isEmpty()) {
            holder.enqueue(StdIn.readString());
        }
        int k = Integer.parseInt(args[0]);
        for (int i = 0; i < k; i++) {
            System.out.println(holder.dequeue());
        }
    }

}
