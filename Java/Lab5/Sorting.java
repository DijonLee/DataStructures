/******************************************************************************
 *  Compilation:  javac Sorting.java
 *  Execution:    java Sorting input.txt AlgorithmUsed
 *  Dependencies: StdOut.java In.java Stopwatch.java
 *  Data files:   http://algs4.cs.princeton.edu/14analysis/1Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/2Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/4Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/8Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/16Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/32Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/1Mints.txt
 *
 *  A program to play with various sorting algorithms. 
 *
 *
 *  Example run:
 *  % java Sorting 2Kints.txt  2
 *
 ******************************************************************************/
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;


public class Sorting {

	/**
	 * 
	 * Sorts the numbers present in the file based on the algorithm provided. 0 =
	 * Arrays.sort() (Java Default) 1 = Bubble Sort 2 = Selection Sort 3 = Insertion
	 * Sort 4 = Mergesort 5 = Quicksort
	 *
	 * @param args
	 *            the command-line arguments
	 */
	public static void main(String[] args) {
		In in = new In(args[0]);
		In sortType = new In(args[1]);
		// Storing file input in an array
		int[] a = in.readAllInts();
		
		int[] b = in.readAllInts();

		Arrays.sort(b); // b contains sorted integers from a (You can use Java Arrays.sort() method)
		
		int[] c = in.readAllInts(); // c contains all integers stored in reverse order
		for(int i=0; i<c.length/2; i++){ int temp = c[i]; c[i] = c[c.length -i -1]; c[c.length -i -1] = temp; }

		// d contains almost sorted array
int[] d = in.readAllInts();
Arrays.sort(d);

  // You need to perform (0.1 * length of the array) number of swaps to perform this)
int numswap = (int) (.1*d.length);

for(int i =1;i< numswap;i++) {
	int temp  =0;
	temp = d[i+1];
	d[]
	// swap beginning with end 
	
	// swap end with beginning
	
}


		// TODO: Generate 3 other arrays, b, c, d where
		// (you can have your own O(n) solution to get c from b
		// (You can copy b to a and then perform (0.1 * d.length) many swaps to acheive
		// this.

		// TODO:
		// Read the second argument and based on input select the sorting algorithm
		// * 0 = Arrays.sort() (Java Default)
		// * 1 = Bubble Sort
		// * 2 = Selection Sort
		// * 3 = Insertion Sort
		// * 4 = Mergesort
		// * 5 = Quicksort
		// Perform sorting on a,b,c,d. Pring runtime for each case along with timestamp
		// and record those.
		// For runtime and priting, you can use the same code from Lab 4 as follows:

		// TODO: For each array, a, b, c, d:
		Stopwatch timer = new Stopwatch();
		// TODO: Perform Sorting and store the result in an array

		double time = timer.elapsedTimeMillis();

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		// TODO: Replace with your own netid
		String netID = "tbiswas2";
		// TODO: Replace with the algorithm used
		String algorithmUsed = "insertion sort";
		// TODO: Replace with the array used
		String arrayUsed = "a";
		StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time, timeStamp, netID, args[0]);
		// Write the resultant array to a file (Each time you run a program 4 output
		// files should be generated. (one for each a,b,c, and d)

	}
}
