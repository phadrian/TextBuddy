import java.util.*;
import java.io.*;

public class Parser {

    /**
     * This method determines which operation the user has entered into the program
     *
     * @param userCommand
     *            String which contains the entire line input by the user
     */
    protected Command getCommandType(String userCommand) {

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
}
