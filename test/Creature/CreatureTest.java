package Creature;

import Creatures.Creature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class CreatureTest {

    private final double THRESHOLD = 0.01;

    private Creature myCreature;


    @BeforeEach
    public void setUp () {
        myCreature = new Creature();
    }

    @Test
    void moveTwice() {
        myCreature.move(3);
        myCreature.move(-2);
        assertEquals(1, myCreature.getShape().getX());
        assertEquals(0, myCreature.getShape().getY());
    }

    @Test
    void rotateTwice() {
        myCreature.changeOrientation(30);
        assertEquals(30, myCreature.getShape().getRotate());
        myCreature.changeOrientation(-20);
        assertEquals(10, myCreature.getShape().getRotate());
    }

    @Test
    void rotateThenMove() {
        myCreature.changeOrientation(30);
        myCreature.move(20);
        assertTrue(Math.abs(myCreature.getShape().getX() - 10*Math.sqrt(3)) < THRESHOLD);
        assertTrue(Math.abs(myCreature.getShape().getY() - 10) < THRESHOLD);
    }


}

