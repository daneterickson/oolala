package oolala.games;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Class that tests FractalGame
 *
 * @author: Norah Tan
 */
public class FractalGameTest {

    private FractalGame myGameOne, myGameTwo, myGameThree;

    @BeforeEach
    public void setUp () {
        myGameOne = new FractalGame();
        myGameTwo = new FractalGame();
        myGameThree = new FractalGame();
        myGameOne.initialize(1, 30, 30, 0, 0, 20);
        myGameTwo.initialize(2, 30, 30, 0, 0, 20);
        myGameThree.initialize(3, 30, 30, 0, 0, 20);
    }

    /**
     * Tests that a Fractal rule compiles
     */
    @Test
    void testCompileRule () {
        String paragraph = "start F\n" +
                "rule F F-F++F-F";
        String expectedOne = "tell 1\npd\nfd 30.0\n";
        assertTrue(myGameOne.compile(paragraph).equals(expectedOne));
    }

    /**
     * Tests that a command expands to expected functionality from Logo
     */
    @Test
    void testCompileExpand () {
        String paragraph = "start F\n" +
                "rule F F-F++F-F";
        String expectedTwo = "tell 1\npd\nfd 30.0\ntell 2\npd\nfd 15.0\nlt 30\npd\nfd 15.0\nrt 30\nrt 30\npd\nfd 15.0\nlt 30\npd\nfd 15.0\n";
        assertTrue(myGameTwo.compile(paragraph).equals(expectedTwo));
    }

    /**
     * Tests that a command expands to 3 levels of expected functionality from Logo
     */
    @Test
    void testCompileThreeLevels () {
        String paragraph = "start F\n" +
                "rule F F++F";
        String expectedThree = "tell 1\npd\nfd 30.0\n" +
                "tell 2\npd\nfd 15.0\nrt 30\nrt 30\npd\nfd 15.0\n" +
                "tell 3\npd\nfd 10.0\nrt 30\nrt 30\npd\nfd 10.0\nrt 30\nrt 30\npd\nfd 10.0\nrt 30\nrt 30\npd\nfd 10.0\n";
        assertTrue(myGameThree.compile(paragraph).equals(expectedThree));
    }


}
