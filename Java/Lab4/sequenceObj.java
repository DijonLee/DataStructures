

public class sequenceObj {
	
	

enum type {
	DNA, RNA;
}


	type myType;
	int position;
	LList<String> mySequence = new LList<String>();

	public sequenceObj(String takeInPosition, String takeType, String takeSequence) {
		// SET DNA OR RNA
		if (takeType.equals("DNA")) {
			myType = type.DNA;

		} else {
			myType = type.RNA;

		}
		// SET POSITION
		position = Integer.parseInt(takeInPosition);

		// CREATE A LINKEDLIST SEQUENCE
		String[] sequenceLink = takeSequence.split("");
		for (int i = 0; i < sequenceLink.length; i++) {
			// System.out.println(sequenceLink[i]);
			mySequence.insert(sequenceLink[i]);
			//System.out.println(sequenceLink[i]);

		}
	//	System.out.println("INNER SEQUENCE");
	//	mySequence.print();

	}

	@Override
	public String toString() {
		// mySequence.print();
		return ("\n  type:" + myType + "    position: " + position  + " List:" +mySequence+ " \n");

	}

	public void printSequence() {
		//System.out.println("PRINTING MY SEQUENCE");
	//	mySequence.print();

		// TODO Auto-generated method stub

	}
	
	public void PrintPos() {
		
	}

}
