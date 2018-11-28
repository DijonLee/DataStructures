import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class P4 {
	static int c1;
	static int c2;
	static int c3;

	public static void main(String[] args) {
		int vertices = 0;
		int edges = 0;
		int time = 0;

		BufferedReader reader;

		try {
			boolean getFirst = true;
			String[] words;
			reader = new BufferedReader(new FileReader("p4_in2"));
			String line = reader.readLine();
			int col = 0;

			while (line != null) {
				// System.out.println(line);
				words = line.split(" ");
				if (getFirst == true) {
					vertices = Integer.parseInt(words[0]);
					edges = Integer.parseInt(words[1]);
					time = Integer.parseInt(words[2]);
					getFirst = false;
					System.out.println("Default values " + vertices + " " + edges + "  " + time);
					c3 = vertices;
					// System.out.println("falsified" + vertices);
				}
				/*
				 * 
				 * else { System.out.println(Arrays.toString(words));
				 * graph.addEgde(Integer.parseInt(words[0]), Integer.parseInt(words[1]),
				 * Integer.parseInt(words[2]));
				 * 
				 * }
				 */

				// System.out.println(words.length);

				line = reader.readLine();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		Graph graph = new Graph(vertices);
		

		try {
			boolean getFirst = true;
			String[] words;
			reader = new BufferedReader(new FileReader("p4_in2"));
			String line = reader.readLine();
			int col = 0;
			while (line != null) {
				// System.out.println(line);
				words = line.split(" ");
				if (getFirst == true) {
					// vertices = Integer.parseInt(words[0]);
					// edges = Integer.parseInt(words[1]);
					// time = Integer.parseInt(words[2]);
					getFirst = false;

					// System.out.println("falsified" + vertices);
				}

				else {
					System.out.println(Arrays.toString(words));
					graph.addEgde(Integer.parseInt(words[0]), Integer.parseInt(words[1]), Integer.parseInt(words[2]));

				}

				// System.out.println(words.length);

				line = reader.readLine();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		/* TRUE MAIN */
		graph.printGraph();
		int sourceVertex = 0;

		graph.dijkstra_GetMinDistances(sourceVertex);
	}

	static class HeapNode{ 
		int vertex;
        int distance;	
	}
	static class Edge {
		int source;
		int destination;
		int weight;

		public Edge(int source, int destination, int weight) {
			this.source = source;
			this.destination = destination;
			this.weight = weight;
		}
	}

	static class Graph {
		int vertices;
		LinkedList<Edge>[] adjacencylist;

		Graph(int vertices) {
			this.vertices = vertices;
			adjacencylist = new LinkedList[vertices];
			// initialize adjacency lists for all the vertices
			for (int i = 0; i < vertices; i++) {
				adjacencylist[i] = new LinkedList<>();
			}
		}

		public void addEgde(int source, int destination, int weight) {
			Edge edge = new Edge(source, destination, weight);
			adjacencylist[source].addFirst(edge); // for directed graph
		}

		public void printGraph() {
			for (int i = 0; i < vertices; i++) {
				LinkedList<Edge> list = adjacencylist[i];
				for (int j = 0; j < list.size(); j++) {
					System.out.println("vertex-" + i + " is connected to " + list.get(j).destination + " with weight "
							+ list.get(j).weight);
				}
			}
		}

		public void dijkstra_GetMinDistances(int sourceVertex) {
			int INFINITY = Integer.MAX_VALUE;
			boolean[] SPT = new boolean[vertices];

			// //create heapNode for all the vertices
			HeapNode[] heapNodes = new HeapNode[vertices];
			for (int i = 0; i < vertices; i++) {
				heapNodes[i] = new HeapNode();
				heapNodes[i].vertex = i;
				heapNodes[i].distance = INFINITY;
			}

			// decrease the distance for the first index
			heapNodes[sourceVertex].distance = 0;

			// add all the vertices to the MinHeap
			MinHeap minHeap = new MinHeap(vertices);
			for (int i = 0; i < vertices; i++) {
				minHeap.insert(heapNodes[i]);
			}
			// while minHeap is not empty
			while (!minHeap.isEmpty()) {
				// extract the min
				HeapNode extractedNode = minHeap.extractMin();

				// extracted vertex
				int extractedVertex = extractedNode.vertex;
				SPT[extractedVertex] = true;

				// iterate through all the adjacent vertices
				LinkedList<Edge> list = adjacencylist[extractedVertex];
				for (int i = 0; i < list.size(); i++) {
					Edge edge = list.get(i);
					int destination = edge.destination;
					// only if destination vertex is not present in SPT
					if (SPT[destination] == false) {
						/// check if distance needs an update or not
						// means check total weight from source to vertex_V is less than
						// the current distance value, if yes then update the distance
						int newKey = heapNodes[extractedVertex].distance + edge.weight;
						int currentKey = heapNodes[destination].distance;
						if (currentKey > newKey) {
							decreaseKey(minHeap, newKey, destination);
							heapNodes[destination].distance = newKey;
						}
					}
				}
			}
			// print SPT
			printDijkstra(heapNodes, sourceVertex);
		}

		public void decreaseKey(MinHeap minHeap, int newKey, int vertex) {

			// get the index which distance's needs a decrease;
			int index = minHeap.indexes[vertex];

			// get the node and update its value
			HeapNode node = minHeap.mH[index];
			node.distance = newKey;
			minHeap.bubbleUp(index);
		}

		public void printDijkstra(HeapNode[] resultSet, int sourceVertex) {
			System.out.println("Dijkstra Algorithm: (Adjacency List + Min Heap)");
			for (int i = 0; i < vertices; i++) {
				String PF = "";
				if( resultSet[i].distance > c3)
				{
					PF = "Failed";
				
				}
				else { 
				
				}
				System.out.println(
						"Source Vertex: " + sourceVertex + " to vertex " + +i + " distance: " + resultSet[i].distance);
			}
		}
	}

	static class MinHeap {
		int capacity;
		int currentSize;
		HeapNode[] mH;
		int[] indexes; // will be used to decrease the distance

		public MinHeap(int capacity) {
			this.capacity = capacity;
			mH = new HeapNode[capacity + 1];
			indexes = new int[capacity];
			mH[0] = new HeapNode();
			mH[0].distance = Integer.MIN_VALUE;
			mH[0].vertex = -1;
			currentSize = 0;
		}

		public void display() {
			for (int i = 0; i <= currentSize; i++) {
				System.out.println(" " + mH[i].vertex + "   distance   " + mH[i].distance);
			}
			System.out.println("________________________");
		}

		public void insert(HeapNode x) {
			currentSize++;
			int idx = currentSize;
			mH[idx] = x;
			indexes[x.vertex] = idx;
			bubbleUp(idx);
		}

		public void bubbleUp(int pos) {
			int parentIdx = pos / 2;
			int currentIdx = pos;
			while (currentIdx > 0 && mH[parentIdx].distance > mH[currentIdx].distance) {
				HeapNode currentNode = mH[currentIdx];
				HeapNode parentNode = mH[parentIdx];

				// swap the positions
				indexes[currentNode.vertex] = parentIdx;
				indexes[parentNode.vertex] = currentIdx;
				swap(currentIdx, parentIdx);
				currentIdx = parentIdx;
				parentIdx = parentIdx / 2;
			}
		}

		public HeapNode extractMin() {
			HeapNode min = mH[1];
			HeapNode lastNode = mH[currentSize];
			// update the indexes[] and move the last node to the top
			indexes[lastNode.vertex] = 1;
			mH[1] = lastNode;
			mH[currentSize] = null;
			sinkDown(1);
			currentSize--;
			return min;
		}

		public void sinkDown(int k) {
			int smallest = k;
			int leftChildIdx = 2 * k;
			int rightChildIdx = 2 * k + 1;
			if (leftChildIdx < heapSize() && mH[smallest].distance > mH[leftChildIdx].distance) {
				smallest = leftChildIdx;
			}
			if (rightChildIdx < heapSize() && mH[smallest].distance > mH[rightChildIdx].distance) {
				smallest = rightChildIdx;
			}
			if (smallest != k) {

				HeapNode smallestNode = mH[smallest];
				HeapNode kNode = mH[k];

				// swap the positions
				indexes[smallestNode.vertex] = k;
				indexes[kNode.vertex] = smallest;
				swap(k, smallest);
				sinkDown(smallest);
			}
		}

		public void swap(int a, int b) {
			HeapNode temp = mH[a];
			mH[a] = mH[b];
			mH[b] = temp;
		}

		public boolean isEmpty() {
			return currentSize == 0;
		}

		public int heapSize() {
			return currentSize;
		}
	}
	
	//https://algorithms.tutorialhorizon.com/dijkstras-shortest-path-algorithm-spt-adjacency-list-and-min-heap-java-implementation/

}
