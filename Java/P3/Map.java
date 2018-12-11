/*
 * Jack Fredericksen
 * CSC 172
 * Fall 2016
 * Lab TA Chris Zhang
 * Project 4
 */


import javax.swing.*;
import java.awt.*;

public class Map extends JFrame{

	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	
	public Map(){
		setTitle("Map");
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container pane = getContentPane();
		pane.setLayout(new GridLayout (4, 2));

	}
	
	  public static void main(String[] args){

		  Map map = new Map();
		  
	  }
}
