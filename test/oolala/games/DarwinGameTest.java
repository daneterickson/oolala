package oolala.games;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DarwinGameTest {
    private DarwinGame myGame;

    @BeforeEach
    public void setUp () {
        myGame = new DarwinGame();
        myGame.initialize(60, 60, 10);
        myGame.addCreature("C", 12, 12);
        myGame.addCreature("C", 12, 10);
    }

    @Test
    void compileBasic () {
        String paragraph = "# Flytrap species\n" +
                "ifenemy 4\n" +
                "left 90\n" +
                "go 1\n" +
                "infect\n" +
                "go 1";
        assertEquals(null, myGame.compile(paragraph));
        assertEquals(5, myGame.getInstructionsMap().size());
    }

    @Test
    void compileWithExtraLines () {
        String paragraph = "# Flytrap species\n" +
                "ifenemy 4\n" +
                "left 90\n" +
                "go 1\n\n\n" +
                "infect\n" +
                "go 1";
        assertEquals(null, myGame.compile(paragraph));
        assertEquals(5, myGame.getInstructionsMap().size());
    }

    @Test
    void compileWithExtraWhitespaces () {
        String paragraph = "# Flytrap species\n" +
                "ifenemy   4\n" +
                "  left 90\n" +
                "go 1\n\n  \n" +
                "infect\n" +
                "go 1";
        assertEquals(null, myGame.compile(paragraph));
        assertEquals(5, myGame.getInstructionsMap().size());
    }


}
