import java.util.*;

public class TextBuddy {
	
	// These are the notification messages displayed to the user when the command has been completed
	private static final String MESSAGE_WELCOME = "Welcome to TextBuddy. %1$s is ready for use";
	private static final String MESSAGE_COMMAND = "Command: ";
	private static final String MESSAGE_ADDED = "Added to %1$s: \"%2$s\"";
	private static final String MESSAGE_DELETED = "Deleted from %1$s: \"%2$s\"";
	private static final String MESSAGE_CLEARED = "All content cleared from %1$s";
	private static final String MESSAGE_SEARCH = "Search results for \"%1$s\":";
	private static final String MESSAGE_NOTFOUND = "No results";
	private static final String MESSAGE_SORTED = "%1$s has been sorted in alphabetical order";
	private static final String MESSAGE_INVALID = "Invalid command: %1$s";
	protected static final String MESSAGE_EMPTY = "%1$s is empty";

	public static void main(String[] args) {
		// Displays the welcome message
		displayMessage(String.format(MESSAGE_WELCOME, args[0]));
		// Runs the program till "exit" is entered
		runProgramLoop(args[0]);
	}
	
	public static void runProgramLoop(String fileName) {
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			// Prints instruction and gets input
			System.out.print(MESSAGE_COMMAND);
			
			// Gets the user command
			String userCommand = sc.nextLine();
			
			// Determines the command type from the Parser
			Parser parser = new Parser();
			Command command = parser.getCommandType(userCommand);

			// Performs the action according to the command from Parser
			mapCommandToAction(parser, command, fileName, userCommand);
		}
	}

	/**
	 * This method maps the command entered by the user to the correct function to 
	 * perform the operation
	 * 
	 * @param command
	 *            Enum type that contains the command type entered by the user
	 * @param fileName
	 *            The name of the text file to be modified
	 * @param userCommand
	 *            String which contains the entire line input by the user
	 * @throws Error
	 */
	protected static void mapCommandToAction(Parser parser, Command command,
			String fileName, String userCommand) throws Error {

		FileModifier file = new FileModifier();

		switch (command) {
		case ADD:
			file.writeToFile(fileName, parser.getCommandArgs(userCommand));

			displayMessage(String.format(
					MESSAGE_ADDED, fileName, parser.getCommandArgs(userCommand)));
			break;
		case DELETE:
			// Gets the line that is deleted to display in the console
			String deletedLine = file.deleteLineFromFile(
					fileName, parser.getLineNumber(userCommand));

			displayMessage(String.format(
					MESSAGE_DELETED, fileName, deletedLine));
			break;
		case DISPLAY:
			file.displayFileContents(fileName);
			break;
		case CLEAR:
			file.writeToBlankFile(fileName);

			displayMessage(String.format(MESSAGE_CLEARED, fileName));
			break;
		case SEARCH:
			displayMessage(String.format(
					MESSAGE_SEARCH, parser.getCommandArgs(userCommand)));
			displayVector(file.searchFile(
					fileName, parser.getCommandArgs(userCommand)));
			break;
		case SORT:
			file.sortFile(fileName);

			displayMessage(String.format(MESSAGE_SORTED, fileName));
			break;
		case EXIT:
			System.exit(0);
		case INVALID:
			displayMessage(String.format(
					MESSAGE_INVALID, parser.getCommandTypeString(userCommand)));
			break;
		default: 
			throw new Error("Unrecognized command type");	
		}
	}
	
	// Display method that prints out notifications after the operation	
	public static void displayMessage(String message) {
		System.out.println(message);
	}
	
	/**
	 * Displays all elements of an input Vector
	 * 
	 * @param stringVector
	 *            The Vector whose contents will be displayed
	 */
	private static void displayVector(Vector<String> stringVector) {
		if (!stringVector.isEmpty()) {
			for (int i = 0; i < stringVector.size(); i++) {
				System.out.println(stringVector.get(i));
			}
		} else {
			System.out.println(MESSAGE_NOTFOUND);
		}
	}
}