/*
 * Jack Fredericksen
 * CSC 172
 * Fall 2016
 * Lab TA Chris Zhang
 * Project 4
 */

	public class GraphNodes implements Comparable<GraphNodes>{
		
		String id;
		GraphNodes next;
		double weight;
		int cons;
		String parent;
		String road;

		public GraphNodes(String i){
			
			id = i;
			cons = 0;
		}

		public void insert(GraphNodes n, String a, Double lb, String par, String r){
			
			if(n.next == null){
				n.next = new GraphNodes(a);
			      n.next.weight = lb;
			      n.next.parent = par;
			      n.next.road = r;
			      cons++;
			}else{
				insert(n.next, a, lb, par, r);
			}
		}
		
		public boolean lookup(GraphNodes n, String a){
		  
			if(!(n !=null)){
				return false;
			}else if ((!(n.id.equals(a))) == false){  // Edited
				return true;
			}else{
				return lookup(n.next, a);
			}
		}

		public double lookupWeight(GraphNodes n, String a){
			if(n == null){
				return Integer.MAX_VALUE; 				// EDITED TO MAXVALUE

			}else if ((!(n.id.equals(a))) == false){  				// EDITED

				return n.weight;
			}else{
				return lookupWeight(n.next, a);
			}
		}

		public void printConnections(){
			if (next == null){
				return;
			}else {
				System.out.print(next.id +", ");
				next.printConnections();
			}
		}
	 
		public GraphNodes[] getConnections(){
	    
			GraphNodes[] ans = new GraphNodes[cons];
			conHelper(this,ans, 0);
			return ans;
		}

		public GraphNodes[] conHelper(GraphNodes n, GraphNodes[] a, int p){
			
			//if
			if (n.next == null){
				return a;
			}else{
				a[p] = n.next;
				return conHelper(n.next, a, p+1);
			}
		}

	  public boolean equals (String x){
		  
		  // Function modified
		  boolean isEq = false;
		  if(!(x.equals(id) == false)) {
			  isEq = true;
		  }
		  else  if(!(x.equals(id) == true)){ 
			  isEq = false;
		  }
		  return isEq;
	  }

	  public int compareTo(GraphNodes that){
		  if ((!(this.weight == that.weight)) == false) { // modified
			  return 0;
		  }else if (!(this.weight <= that.weight)){ // modified
			  		// !(this.weight = that.weight) || this.weight < that.weight)
			  
			  return 1;
		  }else{
			  
	      return -1;
	    }
	  }
}

