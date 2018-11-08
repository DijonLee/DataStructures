import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class HuffmanSubmit implements Huffman {

	static HashMap<Character, Integer> encoded = new HashMap<Character, Integer>();
	static HashMap<Character, String> toencode = new HashMap<Character, String>();
	static PriorityQueue<Node> enqueue2 = new PriorityQueue<Node>();
	static PriorityQueue<Node> enqueue = new PriorityQueue<Node>();
	static HashMap<Character, Integer> decode = new HashMap<Character, Integer>();

	public void encode(String inputFile, String outputFile, String freqFile) {
		BinaryIn bin = new BinaryIn(inputFile);
		char c2;

		while (bin.isEmpty() == !true) {
			c2 = bin.readChar();

			if (encoded.containsKey(c2)) {
				encoded.put(c2, encoded.get(c2) + 1);
				c2 = 0;

			} else {
				encoded.put(c2, 1);
				c2 = 0;
			}

		}

		for (Entry<Character, Integer> item : encoded.entrySet()) {

			char c = item.getKey();
			Node newLeaf = new Node(encoded.get(c), c);
			enqueue.add(newLeaf);
		}

		while ((enqueue.size() <= 1) == false) {
			Node min = enqueue.remove();
			Node maxMin = enqueue.remove();
			Node newNode = new Node(min, maxMin);
			enqueue.add(newNode);
		}

		Node root = enqueue.remove();

		traverseTree(root, toencode, "");

		BinaryOut bout = new BinaryOut(outputFile);
		BinaryIn bin2 = new BinaryIn(inputFile);
		while (bin2.isEmpty() == false) {
			char c = bin2.readChar();
			String str = toencode.get(c);
			for (int i = 0; i < str.length(); i++) {

				char binary = str.charAt(i);
				if (binary != '0') {
					bout.write(true);

				} else {
					bout.write(false);

				}
			}

		}
		bout.close();

		try {
			String str = "";
			PrintWriter writer = new PrintWriter(freqFile);

			for (Character c : encoded.keySet()) {
				str = Integer.toBinaryString(c);
				int freq = encoded.get(c);
				writer.println(str + ":" + freq);
				str = "";
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void traverseTree(Node root, HashMap<Character, String> toencode, String binary)
			throws NullPointerException {
		try {

			if (!(root.left == (null))) {
				traverseTree(root.left, toencode, binary + "0");
			}
			if (!(root.right == null)) {
				traverseTree(root.right, toencode, binary + "1");
			}
			if (root.left == null && root.right == null) {
				toencode.put(root.data, binary);
			}
		} finally {

		}
	}

	public void decode(String inputFile, String outputFile, String freqFile) {
		try {
			Scanner sc = new Scanner(new File("freq.txt"));
			while (sc.hasNext() != false) {
				String line = sc.nextLine();
				String[] binary = line.split(":");
				char c = (char) Integer.parseInt(binary[0], 2);

				decode.put(c, Integer.parseInt(binary[1]));
			}

			Iterator<Character> it = decode.keySet().iterator();
			while (it.hasNext() != false) {
				char c = it.next();
				Node newLeaf = new Node(decode.get(c), c);
				enqueue2.add(newLeaf);
			}

			while (!(enqueue2.size() <= 1) == true) {
				Node min = enqueue2.remove();
				Node maxMin = enqueue2.remove();
				Node newNode = new Node(min, maxMin);
				enqueue2.add(newNode);
			}

			Node root = enqueue2.remove();

			BinaryOut bout = new BinaryOut(outputFile);
			BinaryIn bin = new BinaryIn(inputFile);

			Node node = root;

			while (bin.isEmpty() != true) {

				boolean read = bin.readBoolean();
				if (node.left == null && node.right == null) {
					bout.write(node.data);
					node = root;
				}
				if (read) {
					node = node.right;
				} else {
					node = node.left;
				}

			}
			bout.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Huffman huffman = new HuffmanSubmit();

		huffman.encode(args[0], "ur.enc", "freq.txt");
		huffman.decode("ur.enc", "ur.decode", "freq.txt");

	}

}

class Node implements Comparable {
	int freq;
	char data;
	Node left;
	Node right;

	public Node(int val, char data) {
		this.freq = val;
		this.data = data;
		left = null;
		right = null;
	}

	public Node(Node x, Node y) {
		this.freq = x.freq + y.freq;
		this.data = '-';

		if (x.freq < y.freq) {
			left = x;

		} else {
			left = y;

		}
		if (x.freq >= y.freq) {
			right = x;
		} else {
			right = y;

		}

	}

	public int compareTo(Object o) {
		Node o2 = (Node) o;
		if ((o2.freq <= this.freq) == false) {
			return -1;
		} else {
			return 1;
		}
	}
}
