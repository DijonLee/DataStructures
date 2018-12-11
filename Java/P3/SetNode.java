/*
 * Jack Fredericksen
 * CSC 172
 * Fall 2016
 * Lab TA Chris Zhang
 * Project 4
 */


public class SetNode {
	String id;
	SetNode SetLeader;
	
	public SetNode(String i){
		id = i;
	}

	public void union (SetNode x){
		SetLeader = x;
	}

	public boolean equals(SetNode that){
		return (id.equals(that.id));
	}
}