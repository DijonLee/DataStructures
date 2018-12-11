/*
 * Jack Fredericksen
 * CSC 172
 * Fall 2016
 * Lab TA Chris Zhang
 * Project 4
 */


import java.awt.*; //importing Color
import java.awt.event.*; //importing KeyEvent
import javax.swing.JPanel; //importing JPanel
import java.util.*;

//Canvas clase to display the UI 

public class CanvasUI extends JPanel {

	  HashMap<String, Node> nodeMap;
	  HashMap<String, Edge> edgeMap;
	  ArrayList<Edge> most;
	  ArrayList<Node> sp;
	
	  private double minLati;
	  private double minLong;
	  private double maxLat;
	  private double maxLong;
	
	  private double y;
	  private double x;
	  private int selection;

	  public CanvasUI(HashMap<String, Node> nM, HashMap<String, Edge> eM, ArrayList<Edge> m, ArrayList<Node> s, int selection){
		  
		  setFocusable(true);
		  nodeMap = nM;
		  edgeMap = eM;
		  double[] extreme = extrema();
		  minLati = extreme[0];
		  maxLat = extreme[1];
		  minLong = extreme[2];
		  maxLong = extreme[3];
		  most = m;
		  sp = s;
		  this.selection = selection;
		  
		  }


	  protected void paintComponent(Graphics g){
		  
		  super.paintComponent(g);
		  Graphics2D g2d = (Graphics2D) g.create();
		  g2d.setStroke(new BasicStroke(1));
		  x = getWidth()/(maxLong - minLong);
		  y = getHeight()/(maxLat - minLati);
		  edgePainter(g2d);

		  if(selection < 0){
			  shortPath(g2d);
		  }else{
			  
			  minWeight(g2d);
		  }
	  }

	  public void edgePainter(Graphics2D g2d){
		  
		  for (Edge e : edgeMap.values()){ 
			  
			  Node a = nodeMap.get(e.w);
			  Node b = nodeMap.get(e.v);
			  
			 //  int x1 = (int) ((getHeight() - Math.abs(a.lat - Math.abs(minLati)) * y));

				int x1 = (int) ((getHeight()));
				x1 += -1 * (Math.abs(a.lat - Math.abs(minLati)) * y);
				
				
				
			 // int y1 = (int) (((a.lon * x)) - minLong * x);
			  
			  
			  int y1 = (int) (((a.lon * x)));
			  y1 += (-1*( minLong * x));
			  
			  
			//  int x2 = (int) ((getHeight() - Math.abs(b.lat - Math.abs(minLati)) * y));
				int x2 = (int) ((getHeight()));
				x2 += (-1 * (Math.abs(b.lat - Math.abs(minLati)) * y));
			  
			  
			  
			//  int y2 = (int) (((b.lon * x)) - minLong * x);
			  
			  int y2 = (int) (((b.lon * x)));
			  y2 += (-1*( minLong * x));
			  
			  g2d.drawLine(y1,x1, y2,x2);
			  
		  }
	  }

	  public void minWeight(Graphics2D g2d){
		  g2d.setStroke(new BasicStroke(2));
		  g2d.setColor(Color.RED);
		  for (Edge e : most){
			  Node a = nodeMap.get(e.w);
			  Node b = nodeMap.get(e.v);
			  
				 //  int x1 = (int) ((getHeight() - Math.abs(a.lat - Math.abs(minLati)) * y));

					int x1 = (int) ((getHeight()));
					x1 += -1 * (Math.abs(a.lat - Math.abs(minLati)) * y);
					
					
					
				 // int y1 = (int) (((a.lon * x)) - minLong * x);
				  
				  
				  int y1 = (int) (((a.lon * x)));
				  y1 += (-1*( minLong * x));
				  
				  
				//  int x2 = (int) ((getHeight() - Math.abs(b.lat - Math.abs(minLati)) * y));
					int x2 = (int) ((getHeight()));
					x2 += (-1 * (Math.abs(b.lat - Math.abs(minLati)) * y));
				  
				  
				  
				//  int y2 = (int) (((b.lon * x)) - minLong * x);
				  
				  int y2 = (int) (((b.lon * x)));
				  y2 += (-1*( minLong * x));
				  
			  g2d.drawLine(y1,x1, y2,x2);
		  }
	  }

	  public double[] extrema(){
		  
		  double[] ex = {Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY};
		  
		  for (Node n : nodeMap.values()){
			  
			  if (n.lat < ex[0]){
				  ex[0]=n.lat;
			  }
			  if (n.lat > ex[1]){
				  ex[1]=n.lat;
			  }
			  if (n.lon < ex[2]){
				  ex[2]=n.lon;
			  }
			  if (n.lon > ex[3]){
				  ex[3]=n.lon;
			  }
			  
		  }
		  
		  return ex;
	  }
  
	  public void shortPath(Graphics2D g2d){
	  
		  g2d.setStroke(new BasicStroke(2));
		  g2d.setColor(Color.BLUE);
		  Node a = sp.get(0);
		  
		 // for (int i = 1; i < sp.size(); i++){
			//int i = 0;
			for (Node i : sp) {
    		Node b = i;
    		 
			 //  int x1 = (int) ((getHeight() - Math.abs(a.lat - Math.abs(minLati)) * y));

				int x1 = (int) ((getHeight()));
				x1 += -1 * (Math.abs(a.lat - Math.abs(minLati)) * y);
				
				
				
			 // int y1 = (int) (((a.lon * x)) - minLong * x);
			  
			  
			  int y1 = (int) (((a.lon * x)));
			  y1 += (-1*( minLong * x));
			  
			  
			//  int x2 = (int) ((getHeight() - Math.abs(b.lat - Math.abs(minLati)) * y));
				int x2 = (int) ((getHeight()));
				x2 += (-1 * (Math.abs(b.lat - Math.abs(minLati)) * y));
			  
			  
			  
			//  int y2 = (int) (((b.lon * x)) - minLong * x);
			  
			  int y2 = (int) (((b.lon * x)));
			  y2 += (-1*( minLong * x));
			  
    		a = b;
    	}
	  }
	}