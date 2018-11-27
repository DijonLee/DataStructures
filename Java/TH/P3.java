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
/* Text file to variables */
		try {
			boolean getFirst = true;
			String[] words;

			reader = new BufferedReader(new FileReader("p3_in1"));
			String line = reader.readLine();
			while (line != null) {

				if (getFirst) {
					words = line.split(" ");
					// System.out.println(Arrays.toString(words));
					getFirst = false;
					A = Integer.parseInt(words[0]);
					B = Integer.parseInt(words[1]);

				} else {
					// System.out.println(line);
					graph = line.split("");
					System.out.println(Arrays.toString(graph));

				}

				line = reader.readLine();

			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Solution(A,B,graph);
		
	}
	
	public static int Solution(int a, int b, String[] graph) {
// Determine exits
		System.out.println(Arrays.toString(graph));

		for (int i=0;i<=a;i++) {
			System.out.println(graph[i]);
		}
			

		return 0 ;
	}
}
