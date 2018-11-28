import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class P2 {
	
	static int S;
	static int T;
	
	public static class Graph {
		int V;
		LinkedList<Integer> adjListArray[];

		Graph(int V) {
			this.V = V;
			adjListArray = new LinkedList[V];
			for (int i = 0; i < V; i++) {
				adjListArray[i] = new LinkedList<>();
			}
		}
	}

	static void addEdges(Graph graph, int src, int dest) {
		graph.adjListArray[src].add(dest);

		// Since graph is undirected, add an edge from dest
		// to src also
		graph.adjListArray[dest].add(src);
	}

	static void printGraph(Graph graph) {
		for (int v = 0; v < graph.V; v++) {
			System.out.println("Adjacency list of vertex " + v);
			System.out.print("head");
			for (Integer pCrawl : graph.adjListArray[v]) {
				System.out.print(" -> " + pCrawl);
			}
			System.out.println("\n");
		}
	}

	public static void main(String[] args) {

		// HashMap <Integer,Integer><Intger> map = new HashMap();

		int maxNum = 0;
		String matrix[] = null;
		int[][] realMatrix = null;
		BufferedReader reader;

		// Construct Initial Matrix O(n)
		try {
			boolean getFirst = true;
			String[] words;
			reader = new BufferedReader(new FileReader("p2_in1"));
			String line = reader.readLine();
			int col = 0;

			while (line != null) {
				// System.out.println(line);
				matrix = line.split(" ");
				if (Integer.parseInt(matrix[0]) > maxNum) {
					maxNum = Integer.parseInt(matrix[0]);
				}
				if (Integer.parseInt(matrix[1]) > maxNum) {
					maxNum = Integer.parseInt(matrix[1]);
				}
				line = reader.readLine();
				System.out.println(Arrays.toString(matrix));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		realMatrix = new int[maxNum + 1][maxNum + 1];
		int V = maxNum + 1;
		Graph graph = new Graph(V);

		System.out.println("------------------------");
		BufferedReader reader2;

		// Construct Initial Matrix O
		try {
			boolean getFirst = true;
			String[] words;
			reader2 = new BufferedReader(new FileReader("p2_in1"));
			String line = reader2.readLine();
			int col = 0;

			while (line != null) {
				matrix = line.split(" ");
				System.out.println(matrix[0] + " " + matrix[1]);
				int mySrc = Integer.parseInt(matrix[0]);
				int myDest = Integer.parseInt(matrix[1]);

				addEdges(graph, mySrc, myDest);
				// realMatrix[Integer.parseInt(matrix[0])][Integer.parseInt(matrix[1])] = 1;
				line = reader2.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("--");
		System.out.println(realMatrix.length);
		for (int i = 0; i < realMatrix.length; i++) {
			for (int j = 0; j < realMatrix[i].length; j++) {
				System.out.print(realMatrix[i][j]);

			}
			System.out.println();

		}
		//getShortestPath(graph, S, T);

		LinkedList<Integer> adjListArray[];
		adjListArray = new LinkedList[maxNum];
		printGraph(graph);

	}

	private static int getShortestPath() {
		return 0;
		// TODO Auto-generated method stub

	}

}
