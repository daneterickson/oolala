package oolala.creatures;

import oolala.games.DarwinGame;
import oolala.games.Game;

/**
 * Creature class describes the static and dynamic features of the game objects
 */
public class Creature {

    private boolean isPenActive, isCreatureVisible, isStamp;
    private double myOldPosX, myOldPoxY, myNewPosX, myNewPosY;
    private int myAngle;
    private String myType;
    private boolean isInfected;

    /**
     * Constructor that initializes a Creature with initial position
     *
     * @param x is the initial x-location of the Creature
     * @param y is the initial y-location of the Creature
     */
    public Creature (int x, int y) {
        isPenActive = true;
        isStamp = false;
        isCreatureVisible = true;
        isInfected = false;
        setInitialPosition(x, y,0);
    }

    private void setInitialPosition (double x, double y, int angle) {
        myOldPosX = x;
        myNewPosX = x;
        myOldPoxY = y;
        myNewPosY = y;
        myAngle = angle;
    }

    /**
     * Setter method that sets the Creature's type
     * @param type
     */
    public void setType (String type) { myType = type; }

    /**
     * Getter method that returns the Creature's type
     * @return myType
     */
    public String getType () { return myType; }

    /**
     * Setter method that infect or deinfect the Creature
     * @param status is whether the Creature is infected or not
     */
    public void setStatusInfection (boolean status) { isInfected = status; }

    /**
     * Getter method that tells us whether the Creature is infected or not
     * @return isInfected
     */
    public boolean getStatusInfection () { return isInfected; }

    /**
     * Getter method that tells us whether the pen is up (not active) or down (active)
     * @return isPenActive
     */
    public boolean getPenActivity () { return isPenActive; }

    /**
     * Setter method that make pen up (not active) or down (active)
     * @param status
     */
    public void setPenActivity (boolean status) {
        isPenActive = status;
        updateOldPos();
    }

    /**
     * Getter method that tells us whether the Creature is visible or not
     * @return isCreatureVisible
     */
    public boolean getCreatureVisibility () { return isCreatureVisible; }

    /**
     * Setter method that make the Creature visible or invisible
     * @param status
     */
    public void setCreatureVisibility (boolean status) {
        isCreatureVisible = status;
        updateOldPos();
    }

    /**
     * Getter method that tells us whether the user wants to stamp the Creature
     * @return isStamp
     */
    public boolean getStatusStamp () { return isStamp; }

    /**
     * Setter method that make the stamp or not to make the stamp
     * @param status
     */
    public void setStatusStamp (boolean status) {
        isStamp = status;
        updateOldPos();
    }

    /**
     * Getter method that tells us the old x-location
     * @return myOldPosX
     */
    public double getOldX () { return myOldPosX; }

    /**
     * Getter method that tells us the old y-location
     * @return myOldPosY
     */
    public double getOldY () { return myOldPoxY; }

    /**
     * Getter method that tells us the new/current x-location
     * @return myNewPosX
     */
    public double getNewX () { return myNewPosX; }

    /**
     * Getter method that tells us the new/current y-location
     * @return myNewPosY
     */
    public double getNewY () { return myNewPosY; }

    /**
     * Getter method that tells us the new/current angle
     * @return myAngle
     */
    public int getAngle () { return myAngle; }

    /**
     * This method resets the Creature to its home location and make the angle zero
     * @param game tells us the current home location
     */
    public void reset (Game game) {
        updateOldPos();
        myNewPosX = game.getHomeX();
        myNewPosY = game.getHomeY();
        myAngle = 0;
    }

    private void updateOldPos () {
        myOldPosX = myNewPosX;
        myOldPoxY = myNewPosY;
    }

    /**
     * This method moves the Creature
     *
     * @param distance is the distance to move in the current direction which is specified by myAngle
     * @param game will enable the Creature to only move when it will not hit the wall or hit other Creatures
     */
    public void move (double distance, Game game) {
        double newX = myNewPosX + distance * Math.sin(Math.toRadians(myAngle));
        double newY = myNewPosY - distance * Math.cos(Math.toRadians(myAngle));
        if (game instanceof DarwinGame) {
            DarwinGame DGame = (DarwinGame) game;
            if (newX < 0 || newX > DGame.getMaxX()
                    || newY < 0 || newY > DGame.getMaxY()){
                return;
            }
            else {
                for (Creature c: game.getCreaturesMap().values()) {
                    if ((!c.equals(this)) && newX == c.getNewX() && newY == c.getNewY()) {
                        return;
                    }
                }
            }
        }
        updateOldPos();
        myNewPosX = newX;
        myNewPosY = newY;
    }

    /**
     * This method changes the direction of the Creature
     *
     * @param angle is the change in myAngle
     */
    public void changeOrientation (double angle) {
        myAngle += angle;
    }

    /**
     * This method tells us whether another Creature is nearby ahead (half circle) this Creature
     *
     * @param c is another Creature to be evaluated
     * @param game tells us the radius for how nearby the user wants
     * @return whether another Creature c is nearby ahead of this Creature
     */
    public boolean isNearbyAhead (Creature c, Game game) {
        if (game instanceof DarwinGame) {
            DarwinGame DGame = (DarwinGame) game;
            if (Math.pow(c.getNewX() - myNewPosX, 2) + Math.pow(c.getNewY() - myNewPosY, 2)
                    < Math.pow(DGame.getRadius(), 2)) {
                double directX = Math.sin(Math.toRadians(myAngle));
                double directY = -Math.cos(Math.toRadians(myAngle));
                double creatureX = c.getNewX() - myNewPosX;
                double creatureY = c.getNewY() - myNewPosY;
                return (directX * creatureX + directY * creatureY > 0);
            }
        }
        return false;
    }

    /**
     * This method will infect another Creature if it's nearby ahead, make the type of another Creature the same with myType
     *
     * @param c is another Creature to be evaluated
     * @param game tells us the radius for how nearby the user wants
     */
    public void infect (Creature c, Game game) {
        if (isNearbyAhead(c, game)) {
            c.setStatusInfection(true);
            c.setType(myType);
        }
    }

}