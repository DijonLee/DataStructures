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
			reader = new BufferedReader(new FileReader("p4_in1"));
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
			reader = new BufferedReader(new FileReader("p4_in1"));
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
	}

}
