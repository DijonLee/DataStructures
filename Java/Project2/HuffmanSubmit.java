import java.io.BufferedReader;
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

	static String hEncodeInputFile;
	static String hDecodeFile;

	Properties prop = new Properties();

	static Map<String, String> map = new HashMap();

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
			// System.out.println(root.c + ":" + s); // Prints freq chart

			if ((root.c == '\n')) {
				encodedfreqs.put('\n', s);
			} else {
				encodedfreqs.put(root.c, s);
			}
			return;
		}
		printTree(root.left, s + "0");
		printTree(root.right, s + "1");
		System.out.println(Collections.singletonList(encodedfreqs));

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
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {

		Huffman huffman = new HuffmanSubmit();

		huffman.encode("alice30.txt", "ur.enc", "freq.txt");
		huffman.decode("ur.enc", "ur_dec.jpg", "freq.txt");
	}

	public static void countFrequency() throws IOException {

		FileReader inputStream = null;
		FileWriter outputStream = null;

		try {
			inputStream = new FileReader(hEncodeInputFile);
			outputStream = new FileWriter("tester.txt");

			int c;
			while ((c = inputStream.read()) != -1) {
				frequencies.put(((char) c), frequencies.getOrDefault(((char) c), 0) + 1);
				outputStream.write(c); // 146kb here (kept whitespace)
			}

		} finally {
			if (inputStream != null) {
				inputStream.close();

			}
			if (outputStream != null) {
				outputStream.close();
			}
		}
		frequencyListSize = frequencies.size();
	}

	private static void counttoEncode() throws IOException {
		FileReader inputStream = null;
		FileReader inputStream2 = null;

		FileWriter outputStream = null;
		FileWriter outputStream2 = null;

		/* Writes encoded file */
		try {
			inputStream = new FileReader(hEncodeInputFile);
			outputStream2 = new FileWriter("mytest.txt"); // 146kb here (kept whitespace)

			outputStream = new FileWriter("ur.enc");
			inputStream2 = new FileReader("ur.enc");

			int c;

			while ((c = inputStream.read()) != -1) {
				char cToChar = (char) c;

				if (encodedfreqs.containsKey(cToChar)) {
					outputStream.write(encodedfreqs.get(cToChar));

				}

				outputStream2.write(c);

			}
			outputStream2.close();
			outputStream.close();

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

		}

		PrintWriter writer = new PrintWriter("freq.txt", "UTF-8");

		Iterator it = frequencies.entrySet().iterator();
		Iterator it2 = encodedfreqs.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Map.Entry pair2 = (Map.Entry) it2.next();

			writer.println(pair2.getKey() + "=" + pair2.getValue());
		}
		writer.close();

	}

	public static void makeHeap() throws IOException {

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
		// huffman.decode("ur.enc", "ur_dec.jpg", "freq.txt");

		FileReader inputStream = null;
		FileReader inputFreq = null;

		FileWriter outputStream = null;

		try {
			prop.load(new FileReader(freqFile));
			for (Map.Entry entry : prop.entrySet()) { // map.put((String) entry.getKey(), (String) entry.getValue());

			}
			BufferedReader in = new BufferedReader(new FileReader(freqFile));

			String line;
			while ((line = in.readLine()) != null) {
				if (line.contains("=")) {
					String[] strings = line.split("=");
					map.put(strings[0], (strings[1]));
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		hDecodeFile = inputFile;
		try {
			DecodeNext();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void DecodeNext() throws IOException {

		FileReader inputStream = null;
		inputStream = new FileReader(hDecodeFile);
		FileWriter outputStream = null;
		outputStream = new FileWriter("ur_dec.txt");
		int c;
		String decoder = "";

		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) { // System.out.println(Collections.singletonList(map));

			Map.Entry pair = (Map.Entry) it.next();
		}
		String key = "";

		while ((c = inputStream.read()) != -1) {
			char cToChar = (char) c;
			decoder += cToChar;
			if (map.containsValue(decoder)) {
				for (Map.Entry entry : map.entrySet()) {
					if (decoder.equals(entry.getValue())) {
						key = (String) entry.getKey();
						// System.out.println("found and changing to" + key);
						decoder = "";
						outputStream.write(key);
						break; // breaking because its one to one map }
					}
				}

			}

		}
		outputStream.close();

	}
}
