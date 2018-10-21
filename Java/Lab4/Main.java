
import java.io.File;

import java.io.FileNotFoundException;

import java.util.Arrays;

import java.util.NoSuchElementException;

import java.util.Scanner;

public class DNAList {

	static String[] instructionArray = new String[1];
	static sequenceObj[] sequenceArr;
	static int arraySize;

	public static void main(String[] args) throws FileNotFoundException {

		arraySize = Integer.parseInt(args[0]);
		// Argument 1 = Array Size, Argument 2 = File
		sequenceArr = new sequenceObj[arraySize]; // Create Sequence Array
		Node llist = new Node(); // Create linkedlist

		readText(args[0], args[1]);
		// System.out.println("CURRENT SEQUENCE ARRAY");

	}

	/* READ THE COMMAND FILE */
	public static void readText(String arraySize, String filename)
			throws NoSuchElementException, FileNotFoundException {
		Scanner scan = new Scanner(new File(filename)); // scans all the tesxt
		// GET INSTRUCTION LINE BY LINE //
		while (scan.hasNext()) {
			String line = scan.nextLine();
			instructionArray = line.split(" ");
			// Delete Unncessary whitespace
			String[] removedNull = Arrays.stream(instructionArray).filter(value -> value != null && value.length() > 0)
					.toArray(size -> new String[size]);

			if (removedNull.length == 0) {
			} else {
				// Delete whitespace again
				System.out.println(Arrays.toString(removedNull)); // PRINT COMMANDS
				commandAction(removedNull); // DETERMINE COMMAND

			}
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
		/* COPY INSERT */
		else if (commandArr.length == 3) {
			if (commandArr[0].equals("copy")) {
				copyCommand(commandArr);

			} else if (commandArr[0].equals("insert")) {
				insertNull(commandArr);

			}

			else {
			}

		}

		/* PRINTPOS REMOVE TRANSCRIBE */
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

		}

	}

	/* Print All Sequences */
	public static void printCommand(sequenceObj[] sequenceArr2) {
		// TODO Auto-generated method stub

		for (int i = 0; i < arraySize; i++) {
			if (sequenceArr[i] != null) {
				sequenceArr[i].mySequence.print();
				System.out.print(" " + sequenceArr[i].myType + " ");
				System.out.print(sequenceArr[i].position + "\n");

			}
		}

	}

	/* Insert Sequence */
	public static void insertCommand(String[] commandArr) {
		// [insert, 2, DNA, AGG]
		// Take in Position, Type, Sequence

		String SequenceCheck = commandArr[3];
		String TypeCheck = commandArr[2];

		// IF DNA TYPE BUT RNA SEQUENCE

		sequenceObj myObj = new sequenceObj(commandArr[1], commandArr[2], commandArr[3]);

		int position = (Integer.parseInt(commandArr[1]));
		if (position < 0 || position > arraySize) {

			System.out.println("ERR: SIZE");
		} else {
			// INSERT INTO ARRAY POSITION
			sequenceArr[position] = myObj;
		}
		// CHEKC CORRECT TYPE
		if (TypeCheck.equals("DNA") && (

		(SequenceCheck.contains("U"))))

		{
			System.out.println("DNA TYPE BUT RNA SEQUENCE");
			sequenceArr[position] = null;

		}

		if (TypeCheck.equals("RNA") && ((SequenceCheck.contains("T"))))

		{

			System.out.println("RNA TYPE BUT DNA SEQUENCE");
			sequenceArr[position] = null;

		}

		else {

		}
	}

	/* Insert a null sequence */
	public static void insertNull(String[] commandArr) {
		// [insert, 2, DNA, AGG]
		// Take in Position, Type, Sequence
		String nullSeq = "";
		sequenceObj myObj = new sequenceObj(commandArr[1], commandArr[2], nullSeq);
		int position = (Integer.parseInt(commandArr[1]));
		if (position < 0 || position > arraySize) {

			System.out.println("ERR: SIZE");
		} else {
			// INSERT INTO ARRAY POSITION
			sequenceArr[position] = myObj;
		}
	}

	/* Normal Transcribe */
	public static void transcribeCommand(String[] commandArr) {
		int position = Integer.parseInt(commandArr[1]);
		if (position < arraySize || position > 0) {

		} else {
			System.out.println(sequenceArr[position].myType);
			/* NULL ERR */
			if (sequenceArr[position] == null) {
				System.out.println("CANNOT TRANSCRIBE RNA ON NULL");

			}
			/* TRAMSCRIBE DNA TO RNA */

			else if (sequenceArr[position].myType == sequenceObj.type.DNA) {
				System.out.print(sequenceArr[position].position + "\n");
				sequenceArr[position].mySequence.print();

				System.out.println();
				sequenceArr[position].mySequence.transcribe();
				sequenceArr[position].myType = sequenceArr[position].myType.RNA;
				sequenceArr[position].mySequence.print();
				System.out.println("");

			}
			/* RNA TO RNA ERR */

			else if (sequenceArr[position].myType == sequenceObj.type.RNA) {

				System.out.println("CANNOT TRANSCRIBE RNA ON RNA");

			}

		}
	}

	public static void printPosCommand(String[] commandArr) {
		try {
			int position = Integer.parseInt(commandArr[1]);
			if (position < 0 || position > arraySize || sequenceArr[position].mySequence == null
					|| sequenceArr[position] == null || sequenceArr[position].equals(null)
					|| sequenceArr[position].mySequence.equals(null)) {
				System.out.print("Invalid Position");
			} else {

				if (sequenceArr[position].mySequence != null || sequenceArr[position] != null) {
					sequenceArr[position].mySequence.print();
					System.out.print(" " + sequenceArr[position].myType + " ");
					System.out.print(sequenceArr[position].position + "\n");
				} else {

				}

			}
		} catch (java.lang.NullPointerException exception) {
			System.out.println("Invalid Position");
		}

	}

	/* Remove at Position */
	public static void removeCommand(String[] commandArr) {
		int position = Integer.parseInt(commandArr[1]);
		if (position < 0 || position > arraySize) {
			System.out.print("Invalid Position");
		} else {

			if (sequenceArr[position].mySequence == null || sequenceArr[position].mySequence.isSqNull()) {
				System.out.println("CANT REMOVE NOTHING");
			} else {
				System.out.println("Removed " + position);
				sequenceArr[position].mySequence.clear();
				sequenceArr[position].mySequence.append("");

				sequenceArr[position].myType = sequenceObj.type.NONE;
			}
		}
	}

	/* Copy */
	public static void copyCommand(String[] commandArr) {
		/* Copy Seq in Pos1 to Pos 2 */
		int position1 = Integer.parseInt(commandArr[1]);
		int position2 = Integer.parseInt(commandArr[2]);

		if (position1 < 0 || position1 > arraySize || position2 < 0 || position2 > arraySize) {
			System.out.print("Invalid Position");
		} else {
			if (sequenceArr[position1] != null) {
				sequenceArr[position2] = sequenceArr[position1];

				sequenceArr[position2].position = position2;
			} else {
				System.out.println("UNABLE TO COPY ON NULL");
			}
		}
	}

	/* Clip Command */
	public static void clipCommand(String[] commandArr) {
		int position = Integer.parseInt(commandArr[1]);
		int start = Integer.parseInt(commandArr[2]);
		int end = Integer.parseInt(commandArr[3]);
		if (position < 0 || position > arraySize || start < 0 || end > arraySize || start > arraySize) {
			System.out.print("Invalid Position");
		} else if (sequenceArr[position].mySequence.isSqNull()) {
			System.out.println("NO SEQUENCE");
		} else {

			if (end < start) {

				System.out.println("ERR END< START");
				sequenceArr[position].mySequence.clear();
				sequenceArr[position].mySequence.insert("");

				sequenceArr[position].mySequence.print();

				System.out.print(" " + sequenceArr[position].myType + " ");
				System.out.print(sequenceArr[position].position + "\n");

			} else {

				sequenceArr[position].mySequence.print();
				System.out.println("Converted to");
				sequenceArr[position].mySequence.clip(start, end);
				sequenceArr[position].mySequence.print();
				System.out.print(" " + sequenceArr[position].myType + " ");
				System.out.print(sequenceArr[position].position + "\n");

			}
		}
	}
}
