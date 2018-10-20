
import java.io.File;

import java.io.FileNotFoundException;

import java.util.Arrays;

import java.util.NoSuchElementException;

import java.util.Scanner;

public class Main {

	static String[] instructionArray = new String[1];
	static sequenceObj[] sequenceArr;
	static int arraySize;

	public static void main(String[] args) throws FileNotFoundException {
		arraySize = Integer.parseInt(args[0]);
		// Argument 1 = Array Size, Argument 2 = File
		sequenceArr = new sequenceObj[arraySize]; // Create Sequence Array
		LList llist = new LList(); // Create linkedlist

		readText(args[0], args[1]);
		System.out.println("CURRENT SEQUENCE ARRAY");

	}

	/* READ THE COMMAND FILE */

	public static void readText(String arraySize, String filename)

			throws /* FileNotFoundException, */NoSuchElementException, FileNotFoundException {

		Scanner scan = new Scanner(new File(filename)); // scans all the tesxt

		while (scan.hasNext()) { // GET INSTRUCTION LINE BY LINE */
			String line = scan.nextLine(); // go line by line
			instructionArray = line.split(" ");
			String[] removedNull = Arrays.stream(instructionArray).filter(value -> value != null && value.length() > 0)
					.toArray(size -> new String[size]);

			System.out.println(Arrays.toString(removedNull)); // PRINT COMMANDS
			commandAction(removedNull); // DETERMINE COMMAND

		}

	}

	/* DETERMINE COMMAND */

	public static void commandAction(String[] commandArr) {
		if (commandArr.length == 0) { // System.out.println("null entry");
		}
		/* INSERT OR CLIP */
		else if (commandArr.length == 4) {
			if (commandArr[0].equals("insert")) {
				insertCommand(commandArr);
			} else if (commandArr[0].equals("clip")) {
				clipCommand(commandArr);
			}

		}
		/* COPY TRANSCRIBE */
		else if (commandArr.length == 3) {
			if (commandArr[0].equals("copy")) {
				copyCommand(commandArr);

				/* TRANSCRIBE, REMOVE OR PRINT POS */

			} else {

			}

		}
		/* PRINT POS */
		else if (commandArr.length == 2) {

			if (commandArr[0].equals("print")) {
				printPosCommand(commandArr);
			} else if (commandArr[0].equals("remove")) {
				removeCommand(commandArr);
			} else if (commandArr.length == 2) {
				if (commandArr[0].equals("transcribe")) {
					transcribeCommand(commandArr);
				} else {
					System.out.print("");
				}
			}
		}
		/* PRINT ALL SEQUENCES */
		else if (commandArr.length == 1) {
			if (commandArr[0].equals("print")) {

				printCommand(sequenceArr);

			}
			/*
			 * for (int i = 0; i < arraySize; i++) { if (sequenceArr[i] != null) {
			 * 
			 * 
			 * System.out.print(sequenceArr[i].mytoString()); System.out.print(" " +
			 * sequenceArr[i].position); System.out.print(" " + sequenceArr[i].myType);
			 * 
			 * 
			 * System.out.println("");
			 * 
			 * }
			 * 
			 * // System.out.println(Arrays.toString(sequenceArr)); }
			 */
		}

	}

	public static void printCommand(sequenceObj[] sequenceArr2) {
		// TODO Auto-generated method stub

		for (int i = 0; i < arraySize; i++) {
			if (sequenceArr[i] != null) {
				// System.out.print(sequenceArr[i].mySequence.myGetString() + " ");
				sequenceArr[i].mySequence.print();
				System.out.print(" " + sequenceArr[i].myType + " ");
				System.out.print(sequenceArr[i].position + "\n");
				// System.out.print(sequenceArr[i].mySequence.toS + "\n");
				// System.out.println(sequenceArr[i].myGetString());

				// System.out.println(sequenceArr[i]);
				// System.out.println("Debug");

			}
		}

	}

	public static void insertCommand(String[] commandArr) {
		// [insert, 2, DNA, AGG]
		// Take in Position, Type, Sequence
		sequenceObj myObj = new sequenceObj(commandArr[1], commandArr[2], commandArr[3]);
		int position = (Integer.parseInt(commandArr[1]));
		// System.out.println("" + myObj.toString());
		// myObj.printSequence();

		// INSERT INTO ARRAY POSITION
		sequenceArr[position] = myObj;

	}

	public static void transcribeCommand(String[] commandArr) {
		int position = Integer.parseInt(commandArr[1]);

		System.out.println(sequenceArr[position].myType);
		/* NULL ERR */
		if (sequenceArr[position] == null) {
			System.out.println("CANNOT TRANSCRIBE RNA ON NULL");

		}
		/* TRAMSCRIBE DNA TO RNA */

		else if (sequenceArr[position].myType == sequenceObj.type.DNA) {
			System.out.println("Pre Transcribe");
			sequenceArr[position].mySequence.print();
			System.out.println();
			sequenceArr[position].mySequence.transcribe();
			sequenceArr[position].myType = sequenceArr[position].myType.RNA;
			sequenceArr[position].mySequence.print();
			System.out.println("Transcribe test");

		}
		/* RNA TO RNA ERR */

		else if (sequenceArr[position].myType == sequenceObj.type.RNA) {

			System.out.println("CANNOT TRANSCRIBE RNA ON RNA");

		}

	}

	public static void printPosCommand(String[] commandArr) {
		int position = Integer.parseInt(commandArr[1]);
		sequenceArr[position].mySequence.print();
		System.out.print(" " + sequenceArr[position].myType + " ");
		System.out.print(sequenceArr[position].position + "\n");

		// sequenceArr[position].mySequence.print();
		// System.out.println("Print Position" + commandArr[position]); // PRINT
		// COMMANDS

		/*
		 * // CREATE A LINKEDLIST SEQUENCE String[] sequenceLink =
		 * takeSequence.split(""); for (int i = 0; i < sequenceLink.length; i++) {
		 * mySequence.insert(sequenceLink[i]);
		 * 
		 * }
		 */

	}

	// REMOVE POS WORKING
	public static void removeCommand(String[] commandArr) {
		int position = Integer.parseInt(commandArr[1]);
		System.out.println("Removed " + position);
		sequenceArr[position] = null;

	}

	// COPY WORKING
	public static void copyCommand(String[] commandArr) {
		/* Copy Seq in Pos1 to Pos 2 */
		int position1 = Integer.parseInt(commandArr[1]);
		int position2 = Integer.parseInt(commandArr[2]);

		if (sequenceArr[position1] != null) {
			sequenceArr[position2] = sequenceArr[position1];

			sequenceArr[position2].position = position2;
		} else {
			System.out.println("UNABLE TO COPY ON NULL");
		}
	}

	// CLIP TBD
	public static void clipCommand(String[] commandArr) {
		int position = Integer.parseInt(commandArr[1]);
		int start = Integer.parseInt(commandArr[1]);
		int end = Integer.parseInt(commandArr[1]);
		System.out.println("Pre Clip");
		sequenceArr[position].mySequence.print();

		sequenceArr[position].mySequence.clip(start, end);
		System.out.println("Post Clip");
		sequenceArr[position].mySequence.print();
		System.out.print(" " + sequenceArr[position].myType + " ");
		System.out.print(sequenceArr[position].position + "\n");

	}

}

// Updated
