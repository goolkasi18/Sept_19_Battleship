package com.example.administrator.battleship;

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
    * Setter methods for current instance variables
     */
    public void setPlayerIdentifier(int identifier)
    {
        playerIdentifier=identifier;
    }

    public void setPlayerName(String name)
    {
        playerName = name;
    }


}
