import java.util.*;

// CPP code for printing shortest path between 
// two vertices of unweighted graph 
public class P2 {
	// utility function to form edge between two vertices
	// source and dest
	public static void add_edge(ArrayList<Integer> adj, int src, int dest) {
		adj.add(src,dest);
		adj.add(dest,src);
	}

	// a modified version of BFS that stores predecessor
	// of each vertex in array p
	// and its distance from source in array d
	public static boolean BFS(ArrayList<Integer> adj, int src, int dest, int v, int[] pred, int[] dist) {
		// a queue to maintain queue of vertices whose
		// adjacency list is to be scanned as per normal
		// DFS algorithm
		LinkedList<Integer> queue = new LinkedList<Integer>();

		// boolean array visited[] which stores the
		// information whether ith vertex is reached
		// at least once in the Breadth first search
		boolean[] visited = new boolean[v];

		// initially all vertices are unvisited
		// so v[i] for all i is false
		// and as no path is yet constructed
		// dist[i] for all i set to infinity
		for (int i = 0; i < v; i++) {
			visited[i] = false;
			dist[i] = Integer.MAX_VALUE;
			pred[i] = -1;
		}

		// now source is first to be visited and
		// distance from source to itself should be 0
		visited[src] = true;
		dist[src] = 0;
		queue.addLast(src);

		// standard BFS algorithm
		while (!queue.isEmpty()) {
			int u = queue.poll();
		//	queue.removeFirst();
			for (int i = 0; i < adj.size(); i++) {
				if (visited[adj.get(i)] == false) {
					visited[adj.get(i)] = true;
					dist[adj.get(u)] = dist[u] + 1;
					pred[adj.get(u)] = u;
					queue.addLast(adj.get(u));

					// We stop BFS when we find
					// destination.
					if (adj.get(u) == dest) {
						return true;
					}
				}
			}
		}

		return false;
	}

	// utility function to print the shortest distance
	// between source vertex and destination vertex
	public static void printShortestDistance(ArrayList<Integer> adj, int s, int dest, int v) {
		// predecessor[i] array stores predecessor of
		// i and distance array stores distance of i
		// from s
		int[] pred = new int[v];
		int[] dist = new int[v];

		if (BFS(adj, s, dest, v, pred, dist) == false) {
			System.out.print("Given source and destination");
			System.out.print(" are not connected");
			return;
		}

		// vector path stores the shortest path
		ArrayList<Integer> path = new ArrayList<Integer>();
		int crawl = dest;
		path.add(crawl);
		while (pred[crawl] != -1) {
			path.add(pred[crawl]);
			crawl = pred[crawl];
		}

		// distance from source is in distance array
		System.out.print("Shortest path length is : ");
		System.out.print(dist[dest]);

		// printing path from source to destination
		System.out.print("\nPath is::\n");
		for (int i = path.size() - 1; i >= 0; i--) {
			System.out.print(path.get(i));
			System.out.print(" ");
		}
	}

	public static void main(String[] args) {
		int v = 100;   // n -1
		int source = 1;
		int dest = 8;
		ArrayList<Integer> adj = new ArrayList<Integer>(v);

		// Creating graph given in the above diagram. 
		// add_edge function takes adjacency list, source  
		// and destination vertex as argument and forms 
		// an edge between them. 
		
		for (int i = 0; i < 10; i++) {
			  adj.add(0);
			}
		add_edge(adj, 8, 9);
		add_edge(adj, 1, 8);
		add_edge(adj, 1, 2);
		add_edge(adj, 1, 3);
		add_edge(adj, 2, 4);
		add_edge(adj, 4, 3);
		add_edge(adj, 5, 4);
		add_edge(adj, 5, 6);
		add_edge(adj, 6, 8);
		add_edge(adj, 8, 7);
		add_edge(adj, 7, 5);
				
		printShortestDistance(adj, source, dest, v);
	}
/*
 * 
 * 8 9
1 8
1 2
1 3
2 4
4 3
5 4
5 6
6 8
8 7
7 5

 */
	}

//


