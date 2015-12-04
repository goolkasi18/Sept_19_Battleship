package com.example.administrator.battleship;

import android.graphics.Color;
import android.util.Log;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by Jared, Daniel, Will
 * Class: Player, represents the game state, and the human player,
 * Functionality:
 * --Adds ships to grid
 * --Deletes ships from grid
 * --checks winning, and sink conditions
 */
public class Player implements Serializable{

    public int[][] squares;    //Represents the game board for a player where they can place ships
    public boolean turn;       //Represents whether it is this players turn
    public String playerName;  //Represents the name of the player
    public int profilePicID;   //the profile picture for a player (address of resource)
    public int colorChoiceID;  //the color of the player, (to be implemented) (address of resource)
    public Ship[] ships;       //the array of possible ships to place on squares[][]

    /*
    *   Constructor for a Player object
    *   Parameter is the name of the player
    *   @return: creates a Player
    *   @param: initPlayerName, the initial name for the player
     */
    public Player(String initPlayerName)
    {
        //Initialize all instace variables associated with a player object
        squares = new int[10][10];
        initSquares();
        turn = false;
        playerName=initPlayerName;
        profilePicID = R.drawable.hit_left;
        colorChoiceID = Color.BLUE;
        ships = new Ship[10];
    }

    /*
    *  Empty Constructor if you want to programatically set instance variables
     */
    public Player(){
        squares = new int[10][10];
        initSquares();
        turn = false;
        playerName = "default";
        profilePicID = R.drawable.hit_left;
        colorChoiceID = Color.BLUE;
        ships = new Ship[10];
    }

    /*
    * Sets the players board to its initial state
    * (all squares are 0 = all squares empty, no misses or hits or ships)
     */
    public void initSquares()
    {
        int x, y;
        for(x=0; x<10; x++)
            for(y=0; y<10; y++)
                squares[x][y]= 0;
    }

    /*
        Takes care of turn boolean logic for turn
     */

    //******************************************

    public void endTurn() {turn = false;}
    public void startTurn() {turn = true;}
    public boolean getTurn() {return turn;}

    //*******************************************

    //Setters and getters for the instance variables
    public void setPlayerName(String name) {playerName = name;}
    public String getPlayerName(){return  playerName;}

    public void setProfilePicID(int initID){profilePicID = initID;}
    public int getProfilePicID(){return profilePicID;}

    public void setColorChoiceID(int initID){colorChoiceID = initID;}
    public int getColorChoiceID(){return  colorChoiceID;}

    /*
    * Method: deleteShip
    * Purpose: deletes a ship with the associated shipID from the grid, is used when the
    *          player is choosing where there ships will be
    * @param: ship, the ship to be deleted from the grid
     */
    public void deleteShip(Ship ship)
    {
        for(int i = 0; i<10; i++)
            for(int j = 0; j<10; j++)
                if(squares[i][j]==ship.shipID)
                    squares[i][j] = 0;
        //if they are tapping a ship on a grid that needs to be removed
        if(ship.placed == true)
            ship.togglePlaced();
    }

    /**
     * Method will check the locations where a ship will be added, and add the corresponding integer ID to those spots if
     * the locations are clear
     * @param col        The X Location in the 10X10 grid where the player dropped the boat
     * @param row        The Y Location in the grid
     * @return
     */
    public boolean addShipToGrid(int row, int col, Ship ship)
    {
        if(row == -1 || col == -1) {
            return false;
        }
        //check bounds
        if(row+ship.height < 11 && col+ship.length < 11)
        {
           if(testShip(row,col,ship)) { //if it doesnt overlap other ships (need another method for selectship dynamic class
               //no ships are in the way, so set the ship
               for (int i = col; i < col + ship.length; i++) {
                   squares[row][i] = ship.shipID;
               }
               for (int i = row; i < row + ship.height; i++) {
                   squares[i][col] = ship.shipID;
               }
               //if you got this far, then you added the ships correctly and there were no conflicts.
               return true;
           }
        }
        return false;
    }

    /*
    * Method: testShip
    * @param: row is the row on the board where the user placed the ship
    * @param: col is the col on the board where the user placed the ship\
    * @param: Ship is the ship that the user wants to place
    * Purpose: Checks whether the ship is placed in a valid position on the board
    * @return: A Boolean for whether the method found an invalid placement
     */
    public boolean testShip(int row, int col, Ship ship)
    {
        if(row == -1 || col == -1) {
            return true; //its out of bounds so technically.. it isnt overlapping any ships
        }
        //check the height for other ships
        for(int i = row; i<row+ship.height; i++)
        {
            if(squares[i][col] != 0)
                return false;
        }
        //check the length for other ships
        for(int i = col; i<col+ship.length; i++)
        {
            if(squares[row][i] != 0)
                return false;
        }

        //check the grid for the same ship or the same ship in the other orientation
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                if (squares[i][j] == ship.shipID)
                    return false;

        return true;
    }

    /*
    *   method: attack
    *   purpose: attacks a certain row and col on the board
    *   @param: row, the row to be attacked
    *   @return: boolean for whether that attack was successfull
     */
    public boolean attack(int row, int col)
    {
        // If the row and col are valid.
        if(col >= 0 && col <= 9 && row >= 0 && row <= 9)
        {
            if(squares[row][col] > 0) {
                ships[squares[row][col]-1].upHits();
                return true;
            }
        }
        return false;
    }

    /*
    * method: checkWin
    * purpose: checks if a player has won by iterating through the ships array and checking if all of them are sunk
    * @return: boolean for whether the player has Lost
     */
    public boolean checkWin()
    {
        int count = 0;
        for(int i = 0; i<ships.length; i++)
            if(ships[i].getSunk())
                count++;
        if(count == 5)
            return true;
        return false;
    }

    /*
    * method: checkSink
    * @param: ship, the ship that was hit
    * @return: boolean for whether that ship is destroyed
     */
    public boolean checkSink(Ship ship)
    {
        if ((ship.hits == ship.length || ship.hits == ship.height) && !ship.getSunk()) {
            ship.sink();
            return true;
        }
        return false;
    }


}
