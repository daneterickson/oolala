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
    void test () {
        
    }
}
