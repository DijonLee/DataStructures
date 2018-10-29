import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;

// Import any package as required

public class HuffmanSubmit implements Huffman {

	static int frequencyListSize;
	static Map<Character, Integer> frequencies = new HashMap<>();

	static class Node {
		int data;
		char c;
		Node left;
		Node right;
	}

	static class MyComparator implements Comparator<Node> {
		public int compare(Node x, Node y) {

			return x.data - y.data;
		}
	}

	public static void printTree(Node root, String s) {
		if (root.left == null && root.right == null ) {

			// c is the character in the node
			System.out.println(root.c + ":" + s);

			return;
		}
		printTree(root.left, s + "0");
		printTree(root.right, s + "1");

	}

	// Feel free to add more methods and variables as required.

	public void encode(String inputFile, String outputFile, String freqFile) {
		// TODO: Your code here
		// get frequency

		try {
			countFrequency();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		makeHeap();

	}

	public void decode(String inputFile, String outputFile, String freqFile) {
		// TODO: Your code here
	}

	public static void main(String[] args) {

		Huffman huffman = new HuffmanSubmit();

		huffman.encode("ur.jpg", "ur.enc", "freq.txt");
		huffman.decode("ur.enc", "ur_dec.jpg", "freq.txt");

		// After decoding, both ur.jpg and ur_dec.jpg should be the same.
		// On linux and mac, you can use `diff' command to check if they are the same.
	}

	public static void countFrequency() throws IOException {

		FileReader inputStream = null;
		FileWriter outputStream = null;

		try {
			inputStream = new FileReader("urBin.txt");
			// outputStream = new FileWriter("characteroutput.txt");

			int c;
			while ((c = inputStream.read()) != -1) {
				// System.out.print((char) c);
				frequencies.put(((char) c), frequencies.getOrDefault(((char) c), 0) + 1);

				// outputStream.write(c);
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			// if (outputStream != null) {
			// outputStream.close();
			// }
		}
		 System.out.println(Collections.singletonList(frequencies)); // Print Map of
		// Freqs

		// *********************** //
		frequencyListSize = frequencies.size();
	}

	// myNode.data = frequencies.get(i);

	public static void makeHeap() {
		//System.out.println(frequencyListSize);
		
		PriorityQueue<Node> queue = new PriorityQueue<Node>(frequencyListSize, new MyComparator());


		for (Entry<Character, Integer> entry : frequencies.entrySet()) {
			Node myNode = new Node();
			Character key = entry.getKey();
			Object value = entry.getValue();

			myNode.c = key;
			myNode.data = (int) value;

			myNode.left = null;
			myNode.right = null;
			queue.add(myNode);

		}
		Node root = null;

		while (queue.size() > 1) {
			Node x = queue.peek();
			queue.poll();

			// second min extarct.
			Node y = queue.peek();
			queue.poll();

			// new node f which is equal
			Node f = new Node();

			// to the sum of the frequency of the two nodes
			// assigning values to the f node.
			f.data = x.data + y.data;
			f.c = '-';

			// first extracted node as left child.
			f.left = x;

			// second extracted node as the right child.
			f.right = y;

			// marking the f node as the root node.
			root = f;

			// add this node to the priority-queue.
			queue.add(f);
		}

		// print the codes by traversing the tree
		printTree(root, "");
	}

}
