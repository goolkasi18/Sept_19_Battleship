package com.example.administrator.battleship;

import android.graphics.Point;
import android.util.Log;
import android.widget.ImageView;

import java.util.Random;

/**
 * Created by Jared, Daniel, Will
 *
 * Class AI is a descendant of Player. However it is distinct in the fact that it will choose random positions for the ships,
 * and will attack on its own
 */
public class AI extends Player{



    private int difficultyLevel; //Represents the difficulty level of the AI. higher the difficulty, lower the chance of missing
    private Point[] attackPoints = new Point[4];
    private boolean hasHit;
    private boolean[][] spotsChecked = new boolean[10][10];
    /*
    * Method: Constructor for AI class
    *
    * Purpose: Instantiates an AI object with the givin name
    *
    * Returns: creates an AI
    *
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
    *
    *   Purpose: will add 5 ships to random locations on the grid till one of each size (two of the three size according to the rules of battleship)
    *   is placed on the grid
    *
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
            if(addShipToGrid(x, y,ships[i]))
                done++;
        }
        return true;
    }
    /*
    *   Method: setDifficultyLevel
    *
    *   Parameters: diffLevel, represents the difficulty level the user chooses
    *
    *   @return: void
    *
    *   @param: diffLevel, the difficulty level of the AI
     */
    public void setDifficultyLevel(int diffLevel)
    {
        difficultyLevel = diffLevel;
    }
    /*
    *   Method: takeTurn
    *
    *   @return: Point, returns a random point to be chosen by the Ai for attacking
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

    public boolean AIAttack(){

        if(hasHit){




        }

        else{
            //Find a spot that has not been attacked
            Point bombPoint = randPoint();
            int x = bombPoint.x;
            int y = bombPoint.y;
            //while it sees that that spot has been attacked, find a new spot
            while (spotsChecked[x][y]) {
                bombPoint = randPoint();
                x = bombPoint.x;
                y = bombPoint.y;
            }


            //attack that point

            if (attack(bombPoint.x, bombPoint.y)) {
                hasHit = true;

            }
            //Mark that the point has been attacked
            spotsChecked[bombPoint.x][bombPoint.y] = true;
        }

        return true;
    }

    //Return the index to attack
    public int getIndex(){
        Random rn = new Random();   //Declare a random generator
        int index = rn.nextInt(4);
        return index;
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
                above.x = row+1;
                above.y = col;
            }

        }

        if(col-1>=0){
            if(!spotsChecked[row][col-1]) {
                above.y = col-1;
                above.x = row;
            }


        }

        if(col+1<=9){
            if(!spotsChecked[row][col+1]) {
                above.y = col+1;
                above.x = row;
            }

        }

        pointsToTry[0] = above;
        pointsToTry[1] = below;
        pointsToTry[2] = right;
        pointsToTry[3] = left;

        return pointsToTry;

    }


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
