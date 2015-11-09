package com.example.administrator.battleship;

import android.graphics.Color;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by goolkasi18 on 9/23/2015.
 */
public class Player {
    int[][] squares;
    boolean turn;
    String playerName;

    public Player(String playerName)
    {
        squares = new int[10][10];
        initSquares();
        turn = false;
        this.playerName=playerName;
    }

    /*
    *  Empty Constructor if you want to programatically set instance variables
     */
    public Player(){
        squares = new int[10][10];
        initSquares();
        turn = false;
        playerName = "default";

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
     */
    public boolean addShipToGrid(int col, int row, Ship ship)
    {
        if(row == -1 || col == -1) {
            Log.i("HERE", "");
            return false;
        }
        //check bounds
        if(col+ship.length <= 10 || row+ship.height <= 10)
        {
            //check the length for other ships
            for(int i = col; i<col+ship.length; i++)
            {
                if(squares[row][i] != 0)
                    return false;
            }
            //check the height for other ships
            for(int i = row; i<row+ship.height; i++)
            {
                if(squares[i][col] != 0)
                    return false;
            }
            //no ships are in the way, so set the ship
            for(int i = col; i<col+ship.length; i++)
            {
                if(squares[row][i] != 0)
                    squares[row][i]=ship.shipID;
            }
            for(int i = row; i<row+ship.height; i++)
            {
                if(squares[i][col] != 0)
                    squares[i][col]=ship.shipID;
            }
            //if you got this far, then you added the ships correctly and there were no conflicts.
            return true;
        }
        return false;
    }



    public boolean attack(int x, int y)
    {
        squares[x][y]++;
        if (squares[x][y] == 1)
            return false;
        if (squares[x][y] == 2)
            return true;

        return false;
    }

}
