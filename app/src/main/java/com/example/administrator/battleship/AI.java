package com.example.administrator.battleship;

import android.graphics.Point;
import android.util.Log;
import android.widget.ImageView;

import java.util.Random;

/**
 * Created by Jared on 11/4/2015.
 */
public class AI extends Player{

    private Player player;
    private int[][] shots;

    public AI(String initPlayerName){
        shots = new int[10][10];
        initShots();
        squares = new int[10][10];
        initSquares();
        turn = false;
        playerName=initPlayerName;
        profilePicID = -1;
        colorChoiceID = -1;
        ships = new Ship[10];
    }

    public AI(){
        playerName = "Default AI";
        shots = new int[10][10];
        initShots();
        squares = new int[10][10];
        initSquares();
        turn = false;
        profilePicID = -1;
        colorChoiceID = -1;
        ships = new Ship[10];
    }

    public boolean addShipToGrid()
    {
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

    public Point takeTurn(){

        Point p = new Point();      //Declare a new point
        Random rn = new Random();   //Declare a random generator

        int x = rn.nextInt(10); //Set the x value of the point
        int y = rn.nextInt(10); //Set the y value of the point

        p.x = x;
        p.y = y;

        return p;                   //Return the random point made

    }

    public void initShots()
    {
        int x, y;
        for(x=0; x<10; x++)
            for(y=0; y<10; y++)
                shots[x][y]= 0;
    }

    public void addShot(){

    }

    public boolean setUpAi(){
        if(addShipToGrid())
            return true;
        return false;
    }

}
