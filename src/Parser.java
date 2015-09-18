public class Parser {

    /**
     * This method determines which operation the user has entered into the program
     *
     * @param userCommand
     *            String which contains the entire line input by the user
     */
    public Command getCommandType(String userCommand) {

        String commandType = getCommandTypeString(userCommand).toUpperCase();

        if (commandType.equals("ADD")) {
            return Command.ADD;
        } else if (commandType.equals("DELETE")) {
            return Command.DELETE;
        } else if (commandType.equals("DISPLAY")) {
            return Command.DISPLAY;
        } else if (commandType.equals("CLEAR")) {
            return Command.CLEAR;
        } else if (commandType.equals("SEARCH")) {
            return Command.SEARCH;
        } else if (commandType.equals("SORT")) {
            return Command.SORT;
        } else if (commandType.equals("EXIT")) {
            return Command.EXIT;
        } else {
            return Command.INVALID;
        }
    }

    /**
     * This method extracts the String equivalent of the command type from the entire
     * command entered
     *
     * @param userCommand
     *            String which contains the entire line input by the user
     * @return Command type in a String format
     */
    protected String getCommandTypeString(String userCommand) {
        String[] wordArray = userCommand.split(" ");
        return wordArray[0];
    }

    /**
     * This method extracts the text to be written into the file by the user from
     * the entire input line
     *
     * @param userCommand
     *            String which contains the entire line input by the user
     * @return Text to be written into the file
     */
    protected String getCommandArgs(String userCommand) {
        String[] wordArray = userCommand.split(" ");
        String commandArgs = "";

        for (int i = 1; i < wordArray.length - 1; i++) {
            commandArgs += (wordArray[i] + " ");
        }
        // The last word is separated to prevent additional whitespace
        commandArgs += wordArray[wordArray.length - 1];

        return commandArgs;
    }

    /**
     * Extracts the line number to be deleted from the entire command input by
     * the user
     *
     * @param userCommand
     *            String which contains the entire line input by the user
     * @return The line number of the line to be deleted
     */
    protected int getLineNumber(String userCommand) {
        String[] wordArray = userCommand.split(" ");
        return Integer.valueOf(wordArray[1]);
    }
}
