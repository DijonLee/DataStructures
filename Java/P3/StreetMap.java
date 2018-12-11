/*
 * Jack Fredericksen
 * CSC 172
 * Fall 2016
 * Lab TA Chris Zhang
 * Project 4
 */


import java.util.ArrayList;

public class StreetMap {
	static int show;
    static int direction;
    static String[] commands = new String[100];

	public static void main(String[] args){
			commands[0] = "--show";
	        commands[1] = "--directions";
	        commands[2] = "--directions";
		String filename = args[0];
		boolean mmap = false;
		//boolean direction = false;
		//boolean show = false;
		String start ="", end = "";
		
	try{
      
        if(args[1].equals((commands[0]))){  //commands from regular
			show = 1;
			
		if (args[2].equals("-meridianmap")){
          mmap = true;
          
        }else if(args[2].equals((commands[1]))){  //commands from regular
        
        	direction = 1;
        	start = args[3];
        	end = args[4];
        }
      }else{
    	  
    	  if (args[1].equals("-meridianmap")){
    		  mmap = true;
          }else if(args[1].equals((commands[1]))){ //commands from regular
    		  
    		  direction = 1;
    		  start = args[2];
    		  end = args[3];
    	  }
      }	

		Graph t = Graph.createFromFile(filename);
		if (mmap == true){
			ArrayList<Edge> ans = t.Prim("i15");
			if(show == 1){
				t.mspShow();
			}else{
        
				//for (Edge e : ans){ EDITED
				for (int i = 0; i < ans.size(); i++) {

					System.out.print(ans.get(i).name + ", ");
				}
				System.out.println();
			}
		}else if (direction == 1){  //commands from regular
			if(show == 1){  //commands from regular
				t.shortPath(start, end, false);
				t.directionShow();
			}else{
        	
          t.shortPath(start, end, true);
			}
      }else{
    	  
        throw new Exception();
      }


		}catch(Exception l){
			System.out.println("That was not a valid input. Please use the following format:");
			System.out.println("java Mapping map.txt [-show] [-directions startingIntersection endingIntersection] [-meridianmap]");
		}
	}
}
