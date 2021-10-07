package oolala.games;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FractalGameTest {

    private FractalGame myGameOne, myGameTwo;

    @BeforeEach
    public void setUp () {
        myGameOne = new FractalGame();
        myGameTwo = new FractalGame();
        myGameOne.initialize(1, 30, 30, 0, 0, 20);
        myGameTwo.initialize(2, 30, 30, 0, 0, 20);
    }

    @Test
    void testCompileRule () {
        String paragraph = "start F\n" +
                "rule F F-F++F-F";
        String expectedOne = "tell 1\npd\nfd 30.0\n";
        assertTrue(myGameOne.compile(paragraph).equals(expectedOne));
    }

    @Test
    void testCompileExpand () {
        String paragraph = "start F\n" +
                "rule F F-F++F-F";
        String expectedTwo = "tell 1\npd\nfd 30.0\ntell 2\npd\nfd 15.0\nlt 30\npd\nfd 15.0\nrt 30\nrt 30\npd\nfd 15.0\nlt 30\npd\nfd 15.0\n";
        assertTrue(myGameTwo.compile(paragraph).equals(expectedTwo));
    }


}
