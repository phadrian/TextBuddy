import org.junit.Test;

import static org.junit.Assert.*;
import java.util.Vector;
import java.io.*;

public class TextBuddyTest {

    /**
     * testCase1 - testCase6
     * Test cases for TextBuddy.searchFile
     */
    @Test
    public void testCase1() {
        Vector<String> expectedOutput = new Vector<String>();
        expectedOutput.add("4. added search");
        expectedOutput.add("5. and sort");
        expectedOutput.add("7. to the program");
        assertEquals(expectedOutput, TextBuddy.searchFile("mytestfile.txt", "a"));
    }

    @Test
    public void testCase2() {
        Vector<String> expectedOutput = new Vector<String>();
        expectedOutput.add("1. this is");
        expectedOutput.add("2. the test file");
        expectedOutput.add("5. and sort");
        expectedOutput.add("6. functions");
        expectedOutput.add("7. to the program");
        assertEquals(expectedOutput, TextBuddy.searchFile("mytestfile.txt", "t"));
    }

    @Test
    public void testCase3() {
        Vector<String> expectedOutput = new Vector<String>();
        expectedOutput.add("1. this is");
        expectedOutput.add("2. the test file");
        expectedOutput.add("7. to the program");
        assertEquals(expectedOutput, TextBuddy.searchFile("mytestfile.txt", "th"));
    }

    @Test
    public void testCase4() {
        Vector<String> expectedOutput = new Vector<String>();
        assertEquals(expectedOutput, TextBuddy.searchFile("mytestfile.txt", "zzz"));
    }

    @Test
    public void testCase5() {
        Vector<String> expectedOutput = new Vector<String>();
        expectedOutput.add("2. the test file");
        expectedOutput.add("3. for CE2");
        expectedOutput.add("4. added search");
        expectedOutput.add("7. to the program");
        assertEquals(expectedOutput, TextBuddy.searchFile("mytestfile.txt", "e"));
    }

    @Test
    public void testCase6() {
        Vector<String> expectedOutput = new Vector<String>();
        expectedOutput.add("3. for CE2");
        assertEquals(expectedOutput, TextBuddy.searchFile("mytestfile.txt", "2"));
    }

    /**
     * testCase7 - testCase10
     * Test cases for TextBuddy.sortFile
     */
    @Test
    public void testCase7() {
        Vector<String> expectedOutput = new Vector<String>();
        expectedOutput.add("added search");
        expectedOutput.add("and sort");
        expectedOutput.add("for CE2");
        expectedOutput.add("functions");
        expectedOutput.add("the test file");
        expectedOutput.add("this is");
        expectedOutput.add("to the program");

        TextBuddy.sortFile("sortTest1.txt");
        Vector<String> actualOutput = TextBuddy.readFromFile("sortTest1.txt");
        // To account for the dummy empty String in index 0
        actualOutput.remove(0);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testCase8() {
        Vector<String> expectedOutput = new Vector<String>();
        expectedOutput.add("TEXTbuddy");
        expectedOutput.add("textBUDDY");
        expectedOutput.add("teXTbuddy");
        expectedOutput.add("TExtbuddy");
        expectedOutput.add("textBUDdy");
        expectedOutput.add("textbuDDY");
        expectedOutput.add("tExTbUdDy");
        expectedOutput.add("TeXtBuDdY");
        expectedOutput.add("teXtBUDDy");
        expectedOutput.add("tExtbuDdY");

        TextBuddy.sortFile("sortTest2.txt");
        Vector<String> actualOutput = TextBuddy.readFromFile("sortTest2.txt");
        // To account for the dummy empty String in index 0
        actualOutput.remove(0);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testCase9() {
        Vector<String> expectedOutput = new Vector<String>();
        expectedOutput.add("a");
        expectedOutput.add("b");
        expectedOutput.add("c");
        expectedOutput.add("d");
        expectedOutput.add("e");
        expectedOutput.add("f");
        expectedOutput.add("g");
        expectedOutput.add("h");
        expectedOutput.add("i");
        expectedOutput.add("j");
        expectedOutput.add("k");
        expectedOutput.add("l");
        expectedOutput.add("m");
        expectedOutput.add("n");
        expectedOutput.add("o");
        expectedOutput.add("p");
        expectedOutput.add("q");
        expectedOutput.add("r");
        expectedOutput.add("s");
        expectedOutput.add("t");
        expectedOutput.add("u");
        expectedOutput.add("v");
        expectedOutput.add("w");
        expectedOutput.add("x");
        expectedOutput.add("y");
        expectedOutput.add("z");

        TextBuddy.sortFile("sortTest3.txt");
        Vector<String> actualOutput = TextBuddy.readFromFile("sortTest3.txt");
        // To account for the dummy empty String in index 0
        actualOutput.remove(0);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testCase10() {
        Vector<String> expectedOutput = new Vector<String>();
        expectedOutput.add("1");
        expectedOutput.add("10");
        expectedOutput.add("11");
        expectedOutput.add("12");
        expectedOutput.add("13");
        expectedOutput.add("14");
        expectedOutput.add("15");
        expectedOutput.add("16");
        expectedOutput.add("17");
        expectedOutput.add("18");
        expectedOutput.add("19");
        expectedOutput.add("2");
        expectedOutput.add("20");
        expectedOutput.add("3");
        expectedOutput.add("4");
        expectedOutput.add("5");
        expectedOutput.add("6");
        expectedOutput.add("7");
        expectedOutput.add("8");
        expectedOutput.add("9");

        TextBuddy.sortFile("sortTest4.txt");
        Vector<String> actualOutput = TextBuddy.readFromFile("sortTest4.txt");
        // To account for the dummy empty String in index 0
        actualOutput.remove(0);

        assertEquals(expectedOutput, actualOutput);
    }
}
