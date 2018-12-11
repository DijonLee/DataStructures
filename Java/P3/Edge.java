
/*
 * Jack Fredericksen
 * CSC 172
 * Fall 2016
 * Lab TA Chris Zhang
 * Project 4
 */

public class Edge implements Comparable<Edge>{
	
		String name;
		String v;
		String w;
		double wht;

	public Edge(String n, String x, String y, double we){
		
		name = n;
		v = x;
		w = y;
		wht = we;
	}

	public int compareTo(Edge that){
		
		if(wht == that.wht){
			return 0;
		}else if (wht > that.wht){
			return 1;
		}else{
			return -1;
		}
	}
}