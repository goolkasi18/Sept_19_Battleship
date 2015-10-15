package com.example.administrator.battleship;

import android.graphics.Color;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by rothschi18 on 9/23/2015.
 */
public class Player {
    protected int[][] squares;
    protected int playerIdentifier;
    protected String playerName;
    protected LocationInfo[][] shipGrid;

    public Player(int playerIdentifier, String playerName)
    {
        this.playerName=playerName;
        this.playerIdentifier=playerIdentifier;
        squares = new int[10][10];
        shipGrid = new LocationInfo[10][10];

    }

    /*
    *  Empty Constructor if you want to programatically set instance variables
     */
    public Player(){squares = new int[10][10];}

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
    * Getter Methods for current instance variables
     */
    public int getPlayerIdentifier(){return playerIdentifier;}
    public String getPlayerName(){return  playerName;}


    /*
    * Setter methods fo current instance variables
     */
    public void setPlayerIdentifier(int identifier)
    {
        playerIdentifier=identifier;
    }

    public void setPlayerName(String name)
    {
        playerName = name;
    }

    public void deleteShip(int shipID)
    {
        for(int i = 0; i<10; i++)
            for(int j = 0; j<10; j++)
                if(squares[i][j]==shipID)
                    squares[i][j] = 0;
    }

    /**
     * Method will check the locations where a ship will be added, and add the corresponding integer ID to those spots if
     * the locations are clear
     * @param col          The Location in the 10X10 grid where the player dropped the boat
     * @param row        The Y Location in the grid
     * @param shipID     The ID of the ship to add
     * @param shipLength The Length of the ship
     * @param isH        Boolean to determine whether the ship is horizontal or not
     * @return
     */
    public boolean addShipToGrid(int col, int row, int shipID, int shipLength, Boolean isH, ImageView ship)
    {
        if(row == -1 || col == -1 || row>9 || col>9) {
            Log.i("HERE", "");
            ship.setBackgroundColor(Color.RED);
            return false;
        }

        if(isH){

            for(int i = col; i<col+shipLength; i++){
                //check the spots where you shouldnt add a boat
                if(i>9) {
                    ship.setBackgroundColor(Color.RED);
                    return false;
                }
                if(squares[row][i]>0){
                    ship.setBackgroundColor(Color.RED);
                    return false;
                }


            }

            //check the above conditions before adding the boat, otherwise, a partially added boat could get messy...
            for(int i = col; i<col+shipLength; i++){
               squares[row][i]=shipID;
            }

        }

        if(!isH){
            for(int i = row; i<row+shipLength; i++){
                //check the spots where you shouldnt add a boat
                if(i>9) {
                    ship.setBackgroundColor(Color.RED);
                    return false;
                }
                if(squares[i][col]>0){
                    ship.setBackgroundColor(Color.RED);
                    return false;
                }

            }

            //check the above conditions before adding the boat, otherwise, a partially added boat could get messy...
            for(int i = row; i<row+shipLength; i++){

                squares[i][col]=shipID;
            }

        }
        for(int m = 0; m<10; m++)
            Log.i("", squares[m][0]+" "+squares[m][1]+" "+squares[m][2]+" "+squares[m][3]+" "+squares[m][4]+" "+squares[m][5]+" "+squares[m][6]+" "+squares[m][7]+" "+squares[m][8]+" "+squares[m][9]);

        return true;
    }


}
