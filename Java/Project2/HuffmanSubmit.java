import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class HuffmanSubmit implements Huffman {

 static HashMap < Character, Integer > numOfChar = new HashMap < Character, Integer > ();
 static HashMap < Character, String > encoding = new HashMap < Character, String > ();
 static PriorityQueue < Node > pq2 = new PriorityQueue < Node > ();
 static PriorityQueue < Node > pq = new PriorityQueue < Node > ();
 HashMap < Character, Integer > decode = new HashMap < Character, Integer > ();

 public void encode(String inputFile, String outputFile, String freqFile) {
  BinaryIn bin = new BinaryIn(inputFile);

  // filling hashmap with quantity of each char

  while (bin.isEmpty() == !true) {
   char c2 = bin.readChar();

   if (numOfChar.containsKey(c2)) {
    numOfChar.put(c2, numOfChar.get(c2) + 1);
   } else {
    numOfChar.put(c2, 1);
   }

  }

  // System.out.println(numOfChar);

  // creating tree using priority queue
  // Fixed
  for (Entry < Character, Integer > item: numOfChar.entrySet()) {
   Character key = item.getKey();
   Integer value = item.getValue();

   char c = item.getKey();
   Node newLeaf = new Node(numOfChar.get(c), c);
   pq.add(newLeaf);
  }

  while ((pq.size() <= 1) == false) {
   Node smallest = pq.remove();
   Node secondSmallest = pq.remove();
   Node newNode = new Node(smallest, secondSmallest);
   pq.add(newNode);
  }

  Node root = pq.remove();

  traverseTree(root, encoding, "");

  // System.out.println(encoding);

  // writing to output file
  BinaryOut bout = new BinaryOut(outputFile);
  BinaryIn bin2 = new BinaryIn(inputFile);
  while (!bin2.isEmpty()) {
   char c = bin2.readChar();
   String str = encoding.get(c);
   for (int i = 0; i < str.length(); i++) {

    char binary = str.charAt(i);
    if (binary != '0') {
     bout.write(true);
     // bout.flush();
    } else {
     bout.write(false);
     // bout.flush();
    }
   }
   // bout.flush();
  }
  bout.close();
  // writing to freq file
  try {
   PrintWriter writer = new PrintWriter(freqFile);

   for (Character c: numOfChar.keySet()) {
    String str = Integer.toBinaryString(c);
    int freq = numOfChar.get(c);
    writer.println(str + ":" + freq);
   }
   writer.close();
  } catch (FileNotFoundException e) {
   e.printStackTrace();
  }

 }

 public static void traverseTree(Node root, HashMap < Character, String > encoding, String binary)
 throws NullPointerException {
  try {

   if (!(root.leftChild == (null))) {
    traverseTree(root.leftChild, encoding, binary + "0");
   }
   if (!(root.rightChild == null)) {
    traverseTree(root.rightChild, encoding, binary + "1");
   }
   if (root.leftChild == null && root.rightChild == null) {
    encoding.put(root.character, binary);
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
    char c = (char) Integer.parseInt(binary[0], 2); // parsing string as a binary number
    decode.put(c, Integer.parseInt(binary[1]));
   }

   // creating tree using priority queue

   Iterator < Character > it = decode.keySet().iterator();
   while (it.hasNext()) {
    char c = it.next();
    Node newLeaf = new Node(decode.get(c), c);
    pq2.add(newLeaf);
   }

   while (!(pq2.size() <= 1) == true) {
    Node smallest = pq2.remove();
    Node secondSmallest = pq2.remove();
    Node newNode = new Node(smallest, secondSmallest);
    pq2.add(newNode);
   }

   Node root = pq2.remove();

   // writing to output file
   BinaryOut bout = new BinaryOut(outputFile);
   BinaryIn bin = new BinaryIn(inputFile);

   Node node = root;

   while (bin.isEmpty() == false) {

    boolean move = bin.readBoolean();
    if (node.leftChild == null && node.rightChild == null) {
     bout.write(node.character);
     node = root;
    }
    if (move) {
     node = node.rightChild;
    } else {
     node = node.leftChild;
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
 char character;
 Node leftChild;
 Node rightChild;

 public Node(int val, char character) {
  this.freq = val;
  this.character = character;
  leftChild = null;
  rightChild = null;
 }

 public Node(Node x, Node y) {
  this.freq = x.freq + y.freq;
  this.character = '-';

  if (x.freq < y.freq) {
   leftChild = x;

  } else {
   leftChild = y;

  }
  if (x.freq >= y.freq) {
   rightChild = x;
  } else {
   rightChild = y;

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
