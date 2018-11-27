import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

//row l-r
//col u-d
public class P3 {
	public static void main(String[] args) {
		BufferedReader reader;
		int A = 0; // row
		int B = 0; // col
		String[] graph = null;
		String[][] myGraph = null;

		/* Text file to variables */
		try {
			boolean getFirst = true;
			String[] words;

			reader = new BufferedReader(new FileReader("p3_in1"));
			String line = reader.readLine();
			int col = 0;

			while (line != null) {

				if (getFirst) {
					words = line.split(" ");
					// System.out.println(Arrays.toString(words));
					getFirst = false;
					A = Integer.parseInt(words[0]);
					B = Integer.parseInt(words[1]);
					myGraph = new String[A][B];
				} else {
					// System.out.println(line);
					graph = line.split("");
					for (int i = 0; i < graph.length; i++) {
					//	System.out.println(graph[i]);
						myGraph[col][i] = graph[i];
					}

					System.out.println(Arrays.toString(graph));
					col++;
				}

				line = reader.readLine();

			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Solution(A, B, graph, myGraph);

	}

	public static int Solution(int a, int b, String[] graph, String[][] myGraph) {

		// Determine exits

		// System.out.println("sep");
	//	System.out.println(Arrays.toString(graph));
		System.out.println("--");

		System.out.println(Arrays.toString(myGraph[0]));
		System.out.println(Arrays.toString(myGraph[1]));
		System.out.println(Arrays.toString(myGraph[2]));

		for (int i = 0; i <= a; i++) {
			for (int j = 0; j < graph.length; j++) {
				// System.out.println(String.valueOf(graph[i].charAt(0)));
				// System.out.println(j);

				// System.out.println(graph[i]);

			}

		}

		return 0;

	}
}
