import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

// Import any package as required

public class HuffmanSubmit implements Huffman {
	/* Global Variables */
	static int frequencyListSize;
	static Map<Character, Integer> frequencies = new HashMap<>();
	static Map<Character, String> encodedfreqs = new HashMap<>();
	static Map<String, String> binfreq = new HashMap<>();

	static String hEncodeInputFile;
	static String hDecodeFile;

	static PriorityQueue<Node> queue = new PriorityQueue<Node>();

	/* Node Class */
	static class Node {
		int data;
		char c;
		Node left;
		Node right;
	}

	/* Compare Nodes */
	static class MyComparator implements Comparator<Node> {
		public int compare(Node x, Node y) {

			return x.data - y.data;
		}
	}

	public static void printTree(Node root, String s) throws IOException {
		if (root.left == null && root.right == null) {
	//		System.out.println(root.c + ":" + s); // Prints freq chart
			encodedfreqs.put(root.c, s);

			return;
		}
		printTree(root.left, s + "0");
		printTree(root.right, s + "1");

	}

	public void encode(String inputFile, String outputFile, String freqFile) {
		hEncodeInputFile = inputFile;
		try {
			countFrequency();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			makeHeap();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {

		Huffman huffman = new HuffmanSubmit();

		huffman.encode("alice30.txt", "ur.enc", "freq.txt");
		huffman.decode("ur.enc", "ur_dec.jpg", "freq.txt");

		// After decoding, both ur.jpg and ur_dec.jpg should be the same.
		// On linux and mac, you can use `diff' command to check if they are the same.
	}

	public static void countFrequency() throws IOException {

		FileReader inputStream = null;
		FileWriter outputStream = null;

		try {
			inputStream = new FileReader(hEncodeInputFile);
			// outputStream = new FileWriter("freqFile.txt");

			int c;
			while ((c = inputStream.read()) != -1) {
				// System.out.print((char) c);
				frequencies.put(((char) c), frequencies.getOrDefault(((char) c), 0) + 1);

				// System.out.println(frequencies.get((char)c));
				// System.out.println(frequencies.((char)c));

				// Possible solution
				// System.out.println(encodedfreqs.get(c));
				// outputStream.write(c);
			}

		} finally {
			if (inputStream != null) {
				inputStream.close();

			}
			if (outputStream != null) {
				outputStream.close();
			}
		}
		// outputStream.write(c);

		// normal char

		// *********************** //
		frequencyListSize = frequencies.size();

		// THEN ENCODE

	}

	private static void counttoEncode() throws IOException {
		FileReader inputStream = null;
		FileReader inputStream2 = null;

		FileWriter outputStream = null;
		FileWriter outputStream2 = null;

		/* Writes encoded file */
		try {
			inputStream = new FileReader("alice30.txt");
			inputStream2 = new FileReader("ur.enc");

			outputStream = new FileWriter("ur.enc");
			outputStream2 = new FileWriter("freqFile.txt");
			int c;

			while ((c = inputStream.read()) != -1) {
				char cToChar = (char) c;

				if (encodedfreqs.containsKey(cToChar)) {

					outputStream.write(encodedfreqs.get(cToChar));

				}

			}

		} finally

		{

			if (inputStream != null) {

				inputStream.close();

			}
			if (outputStream != null) {
				outputStream.close();
				// }
			}

			int c;

			// COUNT 010101

			// binfreq.put(frequencies.get(key),encodedfreqs.get)

		}

		/*
		 * 
		 * for (Entry<Character, Integer> entry : frequencies.entrySet()) {
		 * binfreq.put(String.valueOf(entry.getValue()), 0);
		 * 
		 * 
		 * }
		 */

		PrintWriter writer = new PrintWriter("freqFile.txt", "UTF-8");

		/*
		 * for (Entry<Character, String> entry : encodedfreqs.entrySet()) {
		 * 
		 * // System.setOut(logout); writer.println(String.valueOf(entry.getKey()) + "="
		 * + String.valueOf(entry.getValue()));
		 * 
		 * }
		 */

		Iterator it = frequencies.entrySet().iterator();
		Iterator it2 = encodedfreqs.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Map.Entry pair2 = (Map.Entry) it2.next();

			writer.println(pair2.getValue() + " : " + pair.getValue());
		}
		writer.close();

	}

	// myNode.data = frequencies.get(i);

	public static void makeHeap() throws IOException {
		// System.out.println(frequencyListSize);

		queue = new PriorityQueue<Node>(frequencyListSize, new MyComparator());

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
		counttoEncode();

	}

	/* DECODE */
	public void decode(String inputFile, String outputFile, String freqFile) {
		System.out.println("DECODING");

		FileReader inputStream = null;
		FileWriter outputStream = null;
		try {
			PrintStream out = new PrintStream(new FileOutputStream(outputFile));
			System.setOut(out);

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		try {
			// Open File "input file" so we can decode it
			inputStream = new FileReader(inputFile);
			outputStream = new FileWriter(outputFile);
			int c;
			String huffCode = "";

			while ((c = inputStream.read()) != -1) { // Iterate through numbers
				char cToChar = (char) c;
				huffCode += cToChar;
				if (encodedfreqs.containsValue(huffCode)) { // If Match
					// outputStream.write(encodedFreq.); //Writes to file
					for (Object o : encodedfreqs.keySet()) {
						if (encodedfreqs.get(o).equals(huffCode)) {
							System.out.print(o.toString());
				// outputStream.write(o.toString()); //Writes to file

						}
						// Get key from value


					}
					
					huffCode = "";


					// System.out.println(cToChar);
					// System.out.println(huffCode);
					// System.out.println(encodedfreqs.get(key))
					// System.out.println(Collections.singletonList(encodedfreqs)); // method 2
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
	}

}
//
