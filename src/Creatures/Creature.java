package Creatures;

public class Creature {

    private int myPosX, myPosY, myDirectX, myDirectY;
    private boolean isPenActive, isCreatureVisible, isCreatureActive;

    public Creature () {
        reset();
    }

    public void reset () {
        myPosX = 0;
        myPosY = 0;
        myDirectX = 0;
        myDirectY = 0;
    }

    public void move (int distance) {
        myPosX += distance * myDirectX;
        myPosY += distance * myDirectY;
    }

    public void changeOrientation (int angle) {
        // positive angle means clockwise
    }

}