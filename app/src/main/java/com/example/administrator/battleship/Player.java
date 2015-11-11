package com.example.administrator.battleship;

import android.graphics.Color;
import android.util.Log;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by goolkasi18 on 9/23/2015.
 */
public class Player implements Serializable{
    int[][] squares;
    boolean turn;
    String playerName;
    int profilePicID;
    int colorChoiceID;
    Ship[] ships;


    public Player(String initPlayerName)
    {
        squares = new int[10][10];
        initSquares();
        turn = false;
        playerName=initPlayerName;
        profilePicID = -1;
        colorChoiceID = -1;
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
        profilePicID = -1;
        colorChoiceID = -1;
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

    public void endTurn() {turn = false;}
    public void startTurn() {turn = true;}
    public boolean getTurn() {return turn;}

    public void setPlayerName(String name) {playerName = name;}
    public String getPlayerName(){return  playerName;}

    public void setProfilePicID(int initID){profilePicID = initID;}
    public int getProfilePicID(){return profilePicID;}

    public void setColorChoiceID(int initID){colorChoiceID = initID;}
    public int getColorChoiceID(){return  colorChoiceID;}

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
     *
     *
     * (Jared) Removing ImageView parameter. Have caller move ship to initial position if method returns false.
     * Will rewrote
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

    public boolean checkSink(Ship ship)
    {
        if ((ship.hits == ship.length || ship.hits == ship.height) && !ship.getSunk()) {
            ship.sink();
            return true;
        }
        return false;
    }


}
