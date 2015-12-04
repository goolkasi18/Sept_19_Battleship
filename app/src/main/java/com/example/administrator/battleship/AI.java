package com.example.administrator.battleship;

import android.graphics.Point;
import android.util.Log;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jared, Daniel, Will
 *
 * Class AI is a descendant of Player. However it is distinct in the fact that it will choose random positions for the ships,
 * and will attack on its own
 */
public class AI extends Player implements Serializable{

    private int xInterval, yInterval;
    private int difficultyLevel; //Represents the difficulty level of the AI. higher the difficulty, lower the chance of missing
    private boolean hasHit, dirKnown;
    private Point focusPoint, lastHit; //where x=row and y=column i.e. board[x][y]
    private ArrayList<Point> surroundingSpots = new ArrayList<>();
    private int[][] cheater = new int[10][10];

    /*
    * Method: Constructor for AI class
    * Purpose: Instantiates an AI object with the givin name
    * Returns: creates an AI
    * @param: initPlayerName, the initial player name
     */
    public AI(String initPlayerName){

        squares = new int[10][10];
        initSquares();
        turn = false;
        playerName = initPlayerName;
        //Both of these are set in the configurations activity
        profilePicID = R.drawable.hit_left;
        colorChoiceID = -1;
        ships = new Ship[10];
        hasHit = false;
        dirKnown = false;
        difficultyLevel = 50;
    }

    /*
    *   Empty Constructor
     */
    public AI(){
        playerName = "Default AI";

        squares = new int[10][10];
        initSquares();
        turn = false;
        profilePicID = R.drawable.hit_left;
        colorChoiceID = -1;
        ships = new Ship[10];
        difficultyLevel = 50;
    }

    /*
    *   Method: addShipToGrid()
    *   Purpose: will add 5 ships to random locations on the grid till one of each size (two of the three size according to the rules of battleship)
    *            is placed on the grid
    *   Return: True, when the while loop is done adding ships (theoretically could cause infinite loop, infinitely low chance of this occuring)
     */
    public boolean addShipToGrid()
    {
        //until there are 5 ships added to the grid, continue to try and add ships
        int done = 0;
        while (done < 5){
            int i = (int)(Math.random()*10); //from 0 inclusive to 10 exclusive(9.9) so when cast to an int its 0-9
            int x = (int)(Math.random()*10); //from 0 inclusive to 10 exclusive(9.9) so when cast to an int its 0-9
            int y = (int)(Math.random()*10); //from 0 inclusive to 10 exclusive(9.9) so when cast to an int its 0-9
            if(addShipToGrid(x, y, ships[i]))
                done++;
        }
        return true;
    }

    /*
    *   Method: setDifficultyLevel
    *   Parameters: diffLevel, represents the difficulty level the user chooses
    *   @return: void
    *   @param: diffLevel, the difficulty level of the AI
     */
    public void setDifficultyLevel(int diffLevel)
    {
        difficultyLevel = diffLevel;
    }

    //after having found the direction from the ArrayList, do it until it finds a sink.
    public Point guessToKill(){
        Point guessPoint = new Point(-1,-1);

        if(!(lastHit.y+(1*yInterval) < 10 && lastHit.y+(1*yInterval) > -1 && lastHit.x+(1*xInterval) < 10 && lastHit.x+(1*xInterval) > -1)) //if the spot in the direction is not in bounds
        {
            lastHit = focusPoint; //go back to the first spot and
            xInterval = -1 * xInterval; //go the opposite direction
            yInterval = -1 * yInterval; //go the opposite direction
            return guessToKill(); //then guess that new point (yes, this is recursive... can only cause infinite loop with a ship that is 10 long(nonexistant)
        }
        guessPoint.x = lastHit.x + 1*xInterval;
        guessPoint.y = lastHit.y + 1*yInterval;



        //Add on for the up and down case, x interval, y interval
        if(cheater[guessPoint.x][guessPoint.y] > 0) //if the new spot is a hit
        {
            lastHit = guessPoint;
            //check sink? might have to be done in main method so assume the sink case is handled already
            // nothing special.. should just keep going next time till a miss (important)
        }
        else if(cheater[guessPoint.x][guessPoint.y] == -1)
        {
            lastHit = focusPoint; //go back to the first spot and
            xInterval = -1 * xInterval; //go the opposite direction
            yInterval = -1 * yInterval; //go the opposite direction
            if(cheater[focusPoint.x + 1*xInterval][focusPoint.y + 1*yInterval] == -1) {
                dirKnown = false; //so it knows it has to re-find a direction, but this attack is not a miss, so also still need a point to attack!
                return AIAttack();
            }
            else
                return guessToKill(); //then guess again going the new way that new point (yes, this is recursive... can only cause infinite loop with a ship that is 10 long(nonexistant)
        }
        else if(cheater[guessPoint.x][guessPoint.y] == 0) //if the new spot missed (should be able to be changed to just else by final release)
        {
            lastHit = focusPoint; //go back to the first spot and
            xInterval = -1 * xInterval; //go the opposite direction
            yInterval = -1 * yInterval; //go the opposite direction
            if(cheater[focusPoint.x + 1*xInterval][focusPoint.y + 1*yInterval] == -1) //if after missing direction and the other is also guessed.....
            { //then we either screwed up calling the AI to "forget" or it thought it was a horizontal ship when really was two verticals side by side.

                    /*for(int i = 0; i<surroundingSpots.size(); i++)
                    {
                        if(guessPoint == surroundingSpots.get(i))
                        {
                            surroundingSpots.remove(i);
                            break;
                        }

                    } */

                dirKnown = false; //will go through the other directions to re-do this process the opposite direction(assuming later case) next time the AI attacks(this attack is the miss)
            }
        }

        cheater[guessPoint.x][guessPoint.y] = -1;
        return guessPoint;
    }

    //go through the Arraylist until it finds a direction that is a hit
    public Point findDir(){
        Point guessPoint = new Point(-1,-1);
        int guessIndex = (int)(Math.random()*surroundingSpots.size()-1);
        if(guessIndex == -1)
        {
            return rollTheDice();
        }
        guessPoint = surroundingSpots.get(guessIndex); //choose a random one of the directional surrounding spots (from 0 to size-1)
        surroundingSpots.remove(guessIndex); //remove that point saying we have now guessed it

        if(cheater[guessPoint.x][guessPoint.y] > 0) //if that spot is a hit
        {
            //check sink? might have to be done in main method so assume the sink case is handled already
            xInterval = guessPoint.x-focusPoint.x; //direction for the x axis. Should be 1,-1,or 0.
            yInterval = guessPoint.y-focusPoint.y; //direction for the y axis. Should be 1,-1,or 0.
            dirKnown = true;
        }//else you missed and nothing special happens

        cheater[guessPoint.x][guessPoint.y] = -1; //set cheater board to -1 to say that we have guessed this spot now.
        lastHit = guessPoint;
        return guessPoint;
    }

    //uses the difficulty to decide weather to return a hit spot or a missed
    public Point rollTheDice(){
        Point guessPoint = new Point(-1,-1);
        if( (int)(Math.random()*100) > difficultyLevel ) {//if you beat the percentage (so if difficulty is low, this has a higher chance)
            do {
                guessPoint.x = (int) (Math.random() * 10);
                guessPoint.y = (int) (Math.random() * 10);
            } while (cheater[guessPoint.x][guessPoint.y] != 0); //keep going while the spot you find is not zero
        }
        else {
            do {
                guessPoint.x = (int) (Math.random() * 10);
                guessPoint.y = (int) (Math.random() * 10);
            }
            while (cheater[guessPoint.x][guessPoint.y] < 1); //keep going while the spot you keep finding is 0 or -1
            focusPoint = guessPoint; //save where the first hit spot is
            fillSurroundingSpots();
            hasHit = true;
        }

        cheater[guessPoint.x][guessPoint.y] = -1; //set cheater board to -1 to say that we have guessed this spot now.
        lastHit = guessPoint;
        return guessPoint;
    }

    public void copyBoard(int[][] p1Board){
        for(int r = 0; r<p1Board.length; r++)
            for(int c = 0; c<p1Board.length; c++)
                cheater[r][c] = p1Board[r][c];
    }

    public void fillSurroundingSpots(){
        //check the bounds of all points that are directly above below.. etc to make sure we should try them
        //And check to see if AI has already attacked that spot

        if(focusPoint.x-1 >= 0 && cheater[focusPoint.x-1][focusPoint.y] != -1)
            surroundingSpots.add(new Point(focusPoint.x-1 , focusPoint.y));

        if(focusPoint.x+1 <= 9 && cheater[focusPoint.x+1][focusPoint.y] != -1)
            surroundingSpots.add(new Point(focusPoint.x+1 , focusPoint.y));

        if(focusPoint.y-1 >= 0 && cheater[focusPoint.x][focusPoint.y-1] != -1)
            surroundingSpots.add(new Point(focusPoint.x , focusPoint.y-1));

        if(focusPoint.y+1 <= 9 && cheater[focusPoint.x][focusPoint.y+1] != -1)
            surroundingSpots.add(new Point(focusPoint.x , focusPoint.y+1));
    }

    public void forget()
    {
        hasHit = false;
        dirKnown = false;
        focusPoint.x = -1;
        focusPoint.y = -1;
        surroundingSpots.clear();
        xInterval = 0;
        yInterval = 0;
    }


    public Point AIAttack() {

        //***** Will ******

        if (hasHit)
            if (dirKnown)
                return guessToKill();
            else
                return findDir();
        else
            return rollTheDice();
        //**********
    }

    /*
    *   Method: setUPAi
    *   @return: boolean, whether adding ships to the grid was successfull
     */
    public boolean setUpAi(){

        if(addShipToGrid())
            return true;

        return false;
    }

}
