import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class P2 {

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

		public boolean isNeighbor(int u, int v) {

			if (adjListArray[u] == null)
				return false;
			return adjListArray[u].contains(v);

		}
	}

	public static void addEdges(Graph graph, int src, int dest) {
		graph.adjListArray[src].add(dest);

		// Since graph is undirected, add an edge from dest
		// to src also
		graph.adjListArray[dest].add(src);
	}

	public static void printGraph(Graph graph) {
		for (int v = 0; v < graph.V; v++) {
			System.out.println("Adjacency list of vertex " + v);
			System.out.print("head");
			for (Integer pCrawl : graph.adjListArray[v]) {
				System.out.print(" -> " + pCrawl);
			}
			System.out.println("\n");
		}
	}

	public static ArrayList<Integer> BFS(Graph graph, int src, int dest) {
		ArrayList<Integer> shortestPath = new ArrayList<Integer>();
		boolean[] visited = new boolean[graph.V];
		if (src == dest) {
			return null;

		}
		Queue<Integer> q = new LinkedList();
		Stack<Integer> paths = new Stack<Integer>();

		q.add(src);
		paths.add(src);
		visited[src] = true;

		while (!q.isEmpty()) {
			int u = q.poll();
			for (Integer pCrawl : graph.adjListArray[u]) {
				if (visited[pCrawl] == false) {
					q.add(pCrawl);
					visited[pCrawl] = true;
					paths.add(pCrawl);
					System.out.println(u + " " + dest);

					if (u == dest)
						break;
				}

			}
		}
		int node = dest;
		int currentSrc = dest;
		shortestPath.add(dest);

		while (!paths.isEmpty()) {
			System.out.println("got here2");

			node = paths.pop();
			if (graph.isNeighbor(currentSrc, node)) {
				shortestPath.add(node);
				currentSrc = node;
				System.out.println("got here2222");

				if (node == src)
					System.out.println("got here");

					System.out.println(node + " " + src);

					break;
			}
		}

		return shortestPath;
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

		// getShortestPath(graph, S, T);
		// Dont delete
		LinkedList<Integer> adjListArray[];
		adjListArray = new LinkedList[maxNum];
		//
		printGraph(graph);
		System.out.println();

		printShortestDistance(graph, 7, 4);

	}

	public static void printShortestDistance(Graph graph, int src, int dest) {

		ArrayList<Integer> path = BFS(graph, src, dest);

		for (int node : path) {
			System.out.print(node + " ");
		}

	}

}
