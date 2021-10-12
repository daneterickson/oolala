package oolala.creatures;

import oolala.games.DarwinGame;
import oolala.games.Game;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class CreatureTest {

    private final double THRESHOLD = 0.01;

    private Creature myCreature;
    private DarwinGame myGame;



    @BeforeEach
    public void setUp () {
        myGame = new DarwinGame();
        myGame.initialize(100, 100, 20);
        myCreature = new Creature(0, 0);
    }

    @Test
    void moveTwice() {
        myCreature.move(3, null);
        myCreature.move(-2, null);
        assertEquals(0, myCreature.getNewX());
        assertEquals(-1, myCreature.getNewY());
    }

    @Test
    void rotateTwice() {
        myCreature.changeOrientation(30);
        assertEquals(30, myCreature.getAngle());
        myCreature.changeOrientation(-20);
        assertEquals(10, myCreature.getAngle());
    }

    @Test
    void rotateThenMove() {
        myCreature.changeOrientation(30);
        myCreature.move(20, null);
        assertTrue(Math.abs(myCreature.getNewY() + 10*Math.sqrt(3)) < THRESHOLD);
        assertTrue(Math.abs(myCreature.getNewX() - 10) < THRESHOLD);
    }

    @Test
    void twoCreaturesNearByAhead() {
        Creature c1 = new Creature(10, 10);
        Creature c2 = new Creature(10, 20);
        assertTrue(c2.isNearbyAhead(c1, myGame));
        assertFalse(c1.isNearbyAhead(c2, myGame));
    }

    @Test
    void infect () {
        Creature c1 = new Creature(10, 10);
        c1.setType("c");
        Creature c2 = new Creature(10, 20);
        c2.setType("d");
        c2.infect(c1, myGame);
        assertEquals("d", c1.getType());
    }


}

