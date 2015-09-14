import java.util.*;
import java.io.*;

public class TextBuddy {
	
	// These are the notification messages displayed to the user when the command has been completed
	private static final String MESSAGE_WELCOME = "Welcome to TextBuddy. %1$s is ready for use";
	private static final String MESSAGE_COMMAND = "Command: ";
	private static final String MESSAGE_ADDED = "Added to %1$s: \"%2$s\"";
	private static final String MESSAGE_DELETED = "Deleted from %1$s: \"%2$s\"";
	private static final String MESSAGE_CLEARED = "All content cleared from %1$s";
	private static final String MESSAGE_EMPTY = "%1$s is empty";
	private static final String MESSAGE_INVALID = "Invalid command: %1$s";
	
	// The possible command types
	public enum Command {
		ADD, DELETE, DISPLAY, CLEAR, EXIT, INVALID;
	}
	
	public static void main(String[] args) {		
		displayWelcomeMessage(args[0]);		
		runProgramLoop(args[0]);
	}
	
	private static void displayWelcomeMessage(String fileName) {
		System.out.println(String.format(MESSAGE_WELCOME, fileName));
	}
	
	private static void runProgramLoop(String fileName) {
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			// Prints instruction and gets input
			displayCommandMessage();
			
			// Gets the user command
			String userCommand = sc.nextLine();
			
			// Determines the command type
			Command command = getCommandType(userCommand);			
			mapCommandToAction(command, fileName, userCommand);
		}
	}

	private static void displayCommandMessage() {
		System.out.print(MESSAGE_COMMAND);
	}
	
	/**
	 * This method determines which operation the user has entered into the program
	 * 
	 * @param userCommand
	 *            String which contains the entire line input by the user
	 */
	private static Command getCommandType(String userCommand) {
		
		String commandType = getCommandTypeString(userCommand).toUpperCase();
		
		if (commandType.equals("ADD")) {
			return Command.ADD;
		} else if (commandType.equals("DELETE")) {
			return Command.DELETE;
		} else if (commandType.equals("DISPLAY")) {
			return Command.DISPLAY;
		} else if (commandType.equals("CLEAR")) {
			return Command.CLEAR;
		} else if (commandType.equals("EXIT")) {
			return Command.EXIT;
		} else {
			return Command.INVALID;
		}
	}
	
	/**
	 * This method maps the command entered by the user to the correct function to 
	 * perform the operation
	 * 
	 * @param command
	 *            Enum type the contains the command type entered by the user
	 * @param fileName
	 *            The name of the text file to be modified
	 * @param userCommand
	 *            String which contains the entire line input by the user
	 * @throws Error
	 */
	private static void mapCommandToAction(Command command, 
			String fileName, String userCommand) throws Error {
		
		switch (command) {
		case ADD:			
			addCommand(fileName, userCommand);
			break;
		case DELETE:
			deleteCommand(fileName, userCommand);
			break;
		case DISPLAY:
			displayFileContents(fileName);
			break;
		case CLEAR:
			writeToNewFile(fileName);
			displayClearMessage(fileName);
			break;
		case EXIT:
			System.exit(0);
		case INVALID:
			displayInvalidMessage(userCommand);
			break;
		default: 
			throw new Error("Unrecognized command type");	
		}
	}

	private static void addCommand(String fileName, String userCommand) {
		String commandArgs = getCommandArgs(userCommand);			
		writeToFile(fileName, commandArgs);				
		displayAddMessage(fileName, commandArgs);
	}
	
	private static void deleteCommand(String fileName, String userCommand) {
		String deletedLine = deleteLineFromFile(fileName, 
				getLineNumber(userCommand));		
		displayDeleteMessage(fileName, deletedLine);
	}

	/**
	 * This method extracts the text to be written into the file by the user from
	 * the entire input line
	 * 
	 * @param userCommand
	 *            String which contains the entire line input by the user
	 * @return Text to be written into the file
	 */
	private static String getCommandArgs(String userCommand) {
		String[] wordArray = userCommand.split(" ");
		String commandArgs = "";
		
		for (int i = 1; i < wordArray.length; i++) {
			commandArgs += (wordArray[i] + " ");
		}
		return commandArgs;
	}

	/*
	 * Display methods that print out notifications after the operation has been completed
	 * =====================================================================================
	 * displayAddMessage
	 * displayInvalidMessage
	 * displayDeleteMessage
	 * displayClearMessage
	 */
	
	private static void displayAddMessage(String fileName, String commandArgs) {
		System.out.println(String.format(MESSAGE_ADDED, fileName, commandArgs));
	}
	
	private static void displayInvalidMessage(String userCommand) {
		System.out.println(String.format(MESSAGE_INVALID, getCommandTypeString(userCommand)));
	}
	
	private static void displayDeleteMessage(String fileName, String deletedLine) {
		System.out.println(String.format(MESSAGE_DELETED, fileName, deletedLine));
	}
	
	private static void displayClearMessage(String fileName) {
		System.out.println(String.format(MESSAGE_CLEARED, fileName));
	}
	
	/*
	 * Methods that write data to the file
	 * ======================================
	 * writeToFile
	 * writeToNewFile
	 */
	
	/**
	 * Writes to the text file
	 * 
	 * @param fileName
	 *            The name of the text file to be modified
	 * @param commandArgs
	 *            The text which the user wants to enter into the file
	 */
	private static void writeToFile(String fileName, String commandArgs) {
		
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(fileName, true), "UTF-8"));
			writer.write(commandArgs);
			writer.write("\n");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				// Ignore
			}
		}
	}
	
	/**
	 * Overwrites any existing file
	 * 
	 * @param fileName
	 *            The name of the text file to be modified
	 * @param commandArgsVector
	 *            A Vector<String> structure whose elements are the Strings of text to be input
	 *            into the text file by the user
	 */
	private static void writeToNewFile(String fileName, Vector<String> commandArgsVector) {
		
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(fileName), "UTF-8"));
			
			for (int i = 1; i < commandArgsVector.size(); i++) {
				writer.write(commandArgsVector.get(i));
				writer.write("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				// Ignore
			}
		}
	}
	
	/**
	 * Writes a new blank file
	 * 
	 * @param fileName
	 *            The name of the text file to be modified
	 */
	private static void writeToNewFile(String fileName) {
		
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(fileName), "UTF-8"));			
			writer.write("");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				// Ignore
			}
		}
	}

	/*
	 * Methods that read input from the file
	 * =======================================
	 * readFromFile
	 * displayFileContents
	 * deleteLineFromFile
	 */
	
	/**
	 * Reads from a text file and stores the content as Strings
	 * 
	 * @param fileName
	 *            The name of the text file to read from
	 * @return A Vector<String> structure whose elements are the contents of the file
	 *         in a String format
	 */
	private static Vector<String> readFromFile(String fileName) {
		String inputLine;
		BufferedReader bfReader = null;
		Vector<String> commandArgsVector = new Vector<String>();
		commandArgsVector.add(""); // Dummy to synchronize line numbers with Vector index
		try {
			bfReader = new BufferedReader(new InputStreamReader(
										new FileInputStream(fileName), "UTF-8"));
			while ((inputLine = bfReader.readLine()) != null) {
				commandArgsVector.add(inputLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bfReader.close();
			} catch (IOException e) {
				// Ignore
			}
		}
		return commandArgsVector;
	}
	
	/**
	 * Prints the contents of the file along with the line number, example:
	 * 1. Line One
	 * 2. Line Two
	 * 3. Line Three
	 *
	 * @param fileName
	 *            The name of the text file to read from
	 */
	private static void displayFileContents(String fileName) {
		
		String inputLine;
		int lineNumber = 1;
		BufferedReader bfReader = null;
		try {
			bfReader = new BufferedReader(new InputStreamReader(
										new FileInputStream(fileName), "UTF-8"));
			if ((inputLine = bfReader.readLine()) == null) {
				System.out.println(String.format(MESSAGE_EMPTY, fileName));
			} else {
				System.out.println(lineNumber + ". " + inputLine);
				lineNumber++;
				while ((inputLine = bfReader.readLine()) != null) {
					System.out.println(lineNumber + ". " + inputLine);
					lineNumber++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bfReader.close();
			} catch (IOException e) {
				// Ignore
			}
		}
	}
	
	/**
	 * Delete a certain line from the text file
	 * 
	 * @param fileName
	 *            The name of the text file to delete the line from
	 * @param lineNumber
	 *            The line number of the line to be deleted
	 * @return The line that was deleted in a String format
	 */
	private static String deleteLineFromFile(String fileName, int lineNumber) {
		
		Vector<String> commandArgsVector = readFromFile(fileName);
		String deletedLine = commandArgsVector.get(lineNumber);
		commandArgsVector.remove(lineNumber);
		writeToNewFile(fileName, commandArgsVector);
		return deletedLine;
	}

	/**
	 * Extracts the line number to be deleted from the entire command input by
	 * the user
	 * 
	 * @param userCommand
	 *            String which contains the entire line input by the user
	 * @return The line number of the line to be deleted
	 */
	private static int getLineNumber(String userCommand) {
		String[] wordArray = userCommand.split(" ");
		return Integer.valueOf(wordArray[1]);
	}

	/**
	 * This method extracts the String equivalent of the command type from the entire 
	 * command entered
	 * 
	 * @param userCommand
	 *            String which contains the entire line input by the user
	 * @return Command type in a String format
	 */
	private static String getCommandTypeString(String userCommand) {
		String[] wordArray = userCommand.split(" ");
		return wordArray[0];
	}
}