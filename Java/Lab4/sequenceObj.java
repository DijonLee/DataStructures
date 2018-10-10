import java.util.Arrays;

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
			mySequence.append(sequenceLink[i]);
			// System.out.println(sequenceLink[i]);

		}
		// System.out.println("INNER SEQUENCE");
		// mySequence.print();

	}

	/* PRINT ALL SEQUENCES */
	public String mytoString() {
		// mySequence.print();
		// String seqString = mySequence.();
		// myse.toString();
		return ("\n " + myType + " " + position + mySequence.toString());

	}

	public String myGetString() {
//		String getasString = mySequence.getasString();
		String getasString = mySequence.toString();
		return getasString;

	}

}
