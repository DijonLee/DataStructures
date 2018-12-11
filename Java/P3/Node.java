/*
 * Jack Fredericksen
 * CSC 172
 * Fall 2016
 * Lab TA Chris Zhang
 * Project 4
 */

	public class Node implements Comparable<Node>{
	
		String name;
		int num;
		double lon;
		double lat;
		Node connection;
	
		boolean known;
		double distance;
	  	Node parent;
	
	  	String setParent;
	  	
	  	public Node(String n, int spot, double lo, double la){

			num = spot;
			name = n;
			
			lon = lo;
			lat = la;
			
			known = false;
			distance = Double.POSITIVE_INFINITY;
			parent = null;
			
	  	}
	  	
	  	public Node(){
	  		known = false;
	  		distance = Double.POSITIVE_INFINITY;
	  		parent = null;
	  	}
	
	  	public Node(String n){
			name = n;
			known = false;
			distance = Double.POSITIVE_INFINITY;
			parent = null;
	  	}

	  	public boolean equals(Node that){
	  		if (!(this.name.equals(that.name))){ // demorgans law
	  			return false;
	  		}
	  		return true;
	  	}
	
	  	public int compareTo (Node that){
	  		if (!(this.distance <= that.distance)){
	  			return 1;
	  		}else if (!(this.distance >= that.distance)){ // < to >=
	    		return -1;
	    	}else{
	    		return 0;
	    	}
	  	}
}