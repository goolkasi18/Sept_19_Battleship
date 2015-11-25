package com.example.administrator.battleship;

import android.graphics.Point;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jared, Daniel, Will
 *
 * Class AI is a descendant of Player. However it is distinct in the fact that it will choose random positions for the ships,
 * and will attack on its own
 */
public class AI extends Player{


    private Point[] attackPoints = new Point[4];
    private boolean[][] spotsChecked = new boolean[10][10]; //instead of this, we should copy player 1's board. we need it in order to have a percentile hit rate
    private int attackIndex = 0;


    private int interval;
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
        profilePicID = -1;
        colorChoiceID = -1;
        ships = new Ship[10];

        for(int i = 0; i<10; i++)
            for(int j = 0; j<10; j++) {
                spotsChecked[i][j] = false;
            }

        hasHit = false;
        dirKnown = false;
    }

    /*
    *   Empty Constructor
     */
    public AI(){
        playerName = "Default AI";

        squares = new int[10][10];
        initSquares();
        turn = false;
        profilePicID = -1;
        colorChoiceID = -1;
        ships = new Ship[10];
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


    /*
    *   Method: takeTurn
    *   @return: Point, returns a random point to be chosen by the Ai for attacking
    *
    *   again, this needs to have a bit done on it to incorporate difficulty. itll be a random miss or a random hit
     */
    public Point randPoint(){

        Point p = new Point();      //Declare a new point
        Random rn = new Random();   //Declare a random generator

        int x = rn.nextInt(10); //Set the x value of the point
        int y = rn.nextInt(10); //Set the y value of the point

        p.x = x;
        p.y = y;

        return p;                   //Return the random point made
    }

    //after having found the direction from the ArrayList, do it until it finds a sink.
    public Point guessToKill(){
        Point guessPoint = new Point(-1,-1);

        if (focusPoint.x == lastHit.x) //means the ship is horizontal (same row)
        {
            if(!(lastHit.y+(1*interval) < 10 || lastHit.y+(1*interval) > -1)) //if the spot in the direction is not in bounds
            {
                lastHit = focusPoint; //go back to the first spot and
                interval = -1 * interval; //go the opposite direction
            }
            guessPoint.x = lastHit.x;
            guessPoint.y = lastHit.y + 1*interval;



            //Add on for the up and down case, x interval, y interval
            if(cheater[guessPoint.x][guessPoint.y] > 0) //if the new spot is a hit
            {
                //check sink? might have to be done in main method so assume the sink case is handled already
                //do nothing special.. should just keep going next time till a miss (important)


            }
            else if(cheater[guessPoint.x][guessPoint.y] == 0) //if the new spot missed (should be able to be changed to just else by final release)
            {
                lastHit = focusPoint; //go back to the first spot and
                interval = -1 * interval; //go the opposite direction
                if(cheater[focusPoint.x][focusPoint.y + 1*interval] == -1) //if after missing direction and the other is also a miss or guessed.....
                { //then we either screwed up calling the AI to "forget" or it thought it was a horizontal ship when really was two verticals side by side.
                    for(int i = 0; i<surroundingSpots.size(); i++)
                    {
                        if(guessPoint == surroundingSpots.get(i))
                        {
                            surroundingSpots.remove(i);
                            break;
                        }

                    }

                    dirKnown = false; //will go through the other directions to re-do this process the opposite direction(assuming later case)
                }
            }
        }
        cheater[guessPoint.x][guessPoint.y] = -1;
        lastHit = guessPoint;
        return guessPoint;
    }

    //go through the Arraylist until it finds a direction that is a hit
    public Point findDir(){
        Point guessPoint = new Point(-1,-1);
        int guessIndex = (int)(Math.random()*surroundingSpots.size()-1);
        guessPoint = surroundingSpots.get(guessIndex); //choose a random one of the directional surrounding spots (from 0 to size-1)
        surroundingSpots.remove(guessIndex); //remove that point saying we have now guessed it

        if(cheater[guessPoint.x][guessPoint.y] > 0) //if that spot is a hit
        {
            //check sink? might have to be done in main method so assume the sink case is handled already
            interval = guessPoint.x-focusPoint.x + guessPoint.y-focusPoint.y; // 2-2 + 2-3 for example(-1) or 5-4 + 7-7 (1) will always be 1 or -1(saves direction)
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
            } while (cheater[guessPoint.x][guessPoint.y] != 0); //keep going until you find a spot that is a zero (a miss)
        }
        else {
            do {
                guessPoint.x = (int) (Math.random() * 10);
                guessPoint.y = (int) (Math.random() * 10);
            }
            while (cheater[guessPoint.x][guessPoint.y] > 0); //keep going until you find a spot that is greater than zero (a hit)
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

        if(focusPoint.x-1 >= 0 && cheater[focusPoint.x][focusPoint.y] != -1)
            surroundingSpots.add(new Point(focusPoint.x-1 , focusPoint.y));

        if(focusPoint.x+1 <= 9 && cheater[focusPoint.x][focusPoint.y] != -1)
            surroundingSpots.add(new Point(focusPoint.x+1 , focusPoint.y));

        if(focusPoint.y-1 >= 0 && cheater[focusPoint.x][focusPoint.y] != -1)
            surroundingSpots.add(new Point(focusPoint.x , focusPoint.y-1));

        if(focusPoint.y+1 <= 9 && cheater[focusPoint.x][focusPoint.y] != -1)
            surroundingSpots.add(new Point(focusPoint.x , focusPoint.y+1));
    }

    public void forget()
    {
        hasHit = false;
        dirKnown = false;
        focusPoint.x = -1;
        focusPoint.y = -1;
        surroundingSpots.clear();
        interval = 0;
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
        //Logic to run when the AI has hit a ship on the last turn
        if(hasHit){
            //Set it to only try to find a spot around it 4 times, there are only 4 possible spots


            //Find an index within the array of points around the spot hit

            //While it finds an invalid point, attempt to find a valid point by increasing the index that they attack in the array
            while(attackPoints[attackIndex].x == -1 && attackIndex<4){
                attackIndex++;
            }
            //If there is no valid spot in the array, try and find a new spot to attack, then return.
            // This should never be called and should be able to be removed by final release
            if(attackIndex>=4)
            {
                hasHit = false;
                AIAttack();
                attackIndex =  0;
                return true;
            }

            if(attack(attackPoints[attackIndex].x, attackPoints[attackIndex].y)){
                //If the point just attacked sunk a ship, then return to randomly trying to hit points
                if (this.checkSink(this.ships[this.squares[attackPoints[attackIndex].x][attackPoints[attackIndex].y]-1]))
                {
                    hasHit = false;
                }
                //add that that spot was checked on the boolean board
                focusPoint = focusPoints(attackPoints[attackIndex].x, attackPoints[attackIndex].y);
            }

            //Mark the spot in the array as invalid
            attackPoints[attackIndex].x = -1;
            attackPoints[attackIndex].y = -1;


        }

        else{
            Point bombPoint;
            int x,y;
            //while it sees that that spot has been attacked, find a new spot
            do {
                bombPoint = randPoint();
                x = bombPoint.x;
                y = bombPoint.y;
            } while (spotsChecked[x][y]); //itll keep going till it is false(hasnt guessed it)


            //attack that point

            if (attack(bombPoint.x, bombPoint.y)) {
                //To remember the last row and col hit
                lastColHit = bombPoint.y;
                lastRowHit = bombPoint.x;


                //find all spots around the hit that could be used
                attackPoints = findSpotsAroundHit(bombPoint.x, bombPoint.y);

            }
            //Mark that the point has been attacked
            spotsChecked[bombPoint.x][bombPoint.y] = true;
        }

        return true; //needs to know when false (it missed)
    }

    public Point focusPoints(int row, int col)
    {
        Point nextPoint;

        //If the spot is in the same column, then try to find a new row to hit
        if(lastColHit == col) {
            //If the row above the spot is out of bounds, then find the spot "Below" the spot that was attacked
            if (row - 1 < 0 || spotsChecked[row-1][col])
                nextPoint.x = lastRowHit + 1;
            else
                nextPoint.x = row - 1;
            nextPoint.y = col;
        }

        if(lastRowHit == row){
            if(col - 1 < 0)
                nextPoint.y = lastRowHit + 1;
            else
                nextPoint.y = col -1;
            nextPoint.x = row;
        }


       return nextPoint;
    }

    public Point[] findSpotsAroundHit(int row, int col)
    {
        //Declare an array of points for all 4 spots around the point if there was a hit
        Point[] pointsToTry = new Point[4];

        Point above = new Point(-1, -1);
        Point below = new Point(-1, -1);
        Point right = new Point(-1, -1);
        Point left = new Point(-1, -1);

        //check the bounds of all points that are directly above below.. etc to make sure we should try them

        //And check to see if AI has already attacked that spot

        if(row-1>=0){
            if(!spotsChecked[row-1][col]) {
                above.x = row - 1;
                above.y = col;
            }
        }

        if(row+1<=9){
            if(!spotsChecked[row+1][col]) {
                below.x = row+1;
                below.y = col;
            }

        }

        if(col-1>=0){
            if(!spotsChecked[row][col-1]) {
                left.y = col-1;
                left.x = row;
            }


        }

        if(col+1<=9){
            if(!spotsChecked[row][col+1]) {
                right.y = col+1;
                right.x = row;
            }

        }

        pointsToTry[0] = above;
        pointsToTry[1] = below;
        pointsToTry[2] = right;
        pointsToTry[3] = left;
        //If all the points are already hit or checked then dont use this array
        if(above.x == -1 && below.x == -1 && right.x == -1 && left.x == -1)
            hasHit = false;
        return pointsToTry;
    }
*/

    /*
    *   Method: setUPAi
    *
    *   @return: boolean, whether adding ships to the grid was successfull
     */
    public boolean setUpAi(){

        if(addShipToGrid())
            return true;

        return false;
    }

}
