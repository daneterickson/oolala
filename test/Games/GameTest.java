package Games;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {
    private Game myGame;

    @BeforeEach
    public void setUp () {
        myGame = new Game();
    }

    @Test
    void stepCommandParsing () {
        myGame.step("fd 50");
        assertEquals(50, myGame.getListCreatures().get(1).getNewX());
        myGame.step("pd");
        assertTrue(myGame.getListCreatures().get(1).getPenActivity());
    }

    @Test
    void tellCreateOneNewCreature () {
        myGame.step("tell 2");
        assertEquals(2, myGame.getActiveIndices().get(0));
        assertEquals(2, myGame.getListCreatures().size());
    }

    @Test
    void tellCreateTwoNewCreatures () {
        myGame.step("tell 3 2");
        assertEquals(3, myGame.getActiveIndices().get(0));
        assertEquals(2, myGame.getActiveIndices().get(1));
        assertEquals(2, myGame.getActiveIndices().size());
        assertEquals(3, myGame.getListCreatures().size());
    }

    @Test
    void tellThreeCreatures () {
        myGame.step("tell 1 2 3");
        assertEquals(1, myGame.getActiveIndices().get(0));
        assertEquals(2, myGame.getActiveIndices().get(1));
        assertEquals(3, myGame.getActiveIndices().get(2));
        assertEquals(3, myGame.getActiveIndices().size());
        assertEquals(3, myGame.getListCreatures().size());
    }


}
