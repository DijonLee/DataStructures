import java.util.Arrays;

public class sequenceObj {

	enum type {
		DNA, RNA, NONE;
	}

	type myType;
	int position;
	Node<String> mySequence = new Node<String>();

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
			mySequence.append(sequenceLink[i]);

		}

	}

	/* PRINT ALL SEQUENCES */
	public String mytoString() {
		return ("\n " + myType + " " + position + mySequence.toString());

	}

	public String myGetString() {
//		String getasString = mySequence.getasString();
		String getasString = mySequence.toString();
		return getasString;

	}

}


//
