import java.io.*;
import java.util.Collections;
import java.util.Vector;

public class FileModifier {

    private static final String MESSAGE_EMPTY = "%1$s is empty";

    /*
	 * Methods that write data to the file
	 * ======================================
	 * writeToFile
	 * writeToBlankFile
	 */

    /**
     * Writes to the text file
     *
     * @param fileName
     *            The name of the text file to be modified
     * @param commandArgs
     *            The text which the user wants to enter into the file
     */
    protected static void writeToFile(String fileName, String commandArgs) {

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
    protected static void writeToNewFile(String fileName, Vector<String> commandArgsVector) {

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
    protected static void writeToBlankFile(String fileName) {

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
    protected static Vector<String> readFromFile(String fileName) {
        String inputLine;
        BufferedReader bfReader = null;
        Vector<String> commandArgsVector = new Vector<String>();
        // Dummy to synchronize line numbers with Vector index
        commandArgsVector.add("");
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
    protected static void displayFileContents(String fileName) {

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
    protected static String deleteLineFromFile(String fileName, int lineNumber) {

        Vector<String> commandArgsVector = readFromFile(fileName);

        String deletedLine = commandArgsVector.get(lineNumber);
        commandArgsVector.remove(lineNumber);

        writeToNewFile(fileName, commandArgsVector);
        return deletedLine;
    }

    /**
     * Searches the file for a certain specified word
     *
     * @param fileName
     *            The name of the text file to search
     * @param searchTerm
     *            The term/word to be searched for in the file
     * @return A Vector<String> structure which contains all lines which contain
     *         that word
     */
    protected static Vector<String> searchFile(String fileName, String searchTerm) {

        Vector<String> matchingArgsVector = new Vector<String>();
        Vector<String> commandArgsVector = readFromFile(fileName);

        for (int i = 1; i < commandArgsVector.size(); i++) {
            String inputLine = commandArgsVector.get(i);
            // Use lowercase to ensure case insensitive search
            if (isSubstring(searchTerm.toLowerCase(), inputLine.toLowerCase())) {
                matchingArgsVector.add(i + ". " + inputLine);
            }
        }
        return matchingArgsVector;
    }

    /**
     * Sorts the file by alphabetical order
     * Numbers are taken to be Strings and not Integers, so 111 < 2
     *
     * @param fileName
     *            The name of the file to be sorted
     */
    protected static void sortFile(String fileName) {

        Vector<String> commandArgsVector = readFromFile(fileName);
        Collections.sort(commandArgsVector, String.CASE_INSENSITIVE_ORDER);
        writeToNewFile(fileName, commandArgsVector);
    }

    /**
     * This method checks whether str1 is a substring of another String str2
     *
     * @param str1
     *            The matching String
     * @param str2
     *            The String to be compared with
     * @return Boolean value of whether str1 is a substring of str2
     */
    private static boolean isSubstring(String str1, String str2) {
        if (str1.length() > str2.length()) {
            return false;
        } else if (str1.length() == str2.length()) {
            return str1.equals(str2);
        } else if (str1.equals(str2.substring(0, str1.length()))) {
            return true;
        } else {
            return isSubstring(str1, str2.substring(1));
        }
    }
}
