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
    Ship s1,s2,s3,s4,s5,s6,s7,s8,s9,s10;
    Ship[] shipsToBeAdded = new Ship[5];

    public AI(String playerName){
        super(playerName);
        s1 = new Ship(0, 0, 0, 2, 1, null, 0);
        shots = new int[10][10];
        initShots();

        s1 = new Ship(0, 0, 0, 2, 1, null, 0);
        s2 = new Ship(0, 0, 0, 3, 2, null, 0);
        s3 = new Ship(0, 0, 0, 3, 3, null, 0);
        s4 = new Ship(0, 0, 0, 4, 4, null, 0);
        s5 = new Ship(0, 0, 0, 5, 5, null, 0);
        Ship[] vShips = {s1, s2, s3, s4, s5};
        s6 = new Ship(0, 0, 2, 0, 1, null, 0);
        s7 = new Ship(0, 0, 3, 0, 2, null, 0);
        s8 = new Ship(0, 0, 3, 0, 3, null, 0);
        s9 = new Ship(0, 0, 4, 0, 4, null, 0);
        s10 = new Ship(0, 0, 5, 0, 5, null, 0);
        Ship[] hShips = {s6, s7, s8, s9, s10};


    }
    /*
    * Method: takeTurn
    * Parameters: None
    * Return Value: A Random point within the grid
     */
    public Point takeTurn(){

        Point p = new Point();      //Declare a new point
        Random rn = new Random();   //Declare a random generator

        int x = rn.nextInt(10) + 1; //Set the x value of the point
        int y = rn.nextInt(10) + 1; //Set the y value of the point

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


    public void aiAddShipsToGrid(){
        Ship ship = new Ship();

        Ship[] vShips = {s1, s2, s3, s4, s5};
        Ship[] hShips = {s6, s7, s8, s9, s10};


        if(Math.random()<.5)
            shipsToBeAdded[0] = s1;
        else
            shipsToBeAdded[0] = s6;

        if(Math.random()<.5)
            shipsToBeAdded[1] = s2;
        else
            shipsToBeAdded[1] = s7;

        if(Math.random()<.5)
            shipsToBeAdded[2] = s3;
        else
            shipsToBeAdded[2] = s8;

        if(Math.random()<.5)
            shipsToBeAdded[3] = s4;
        else
            shipsToBeAdded[3] = s9;

        if(Math.random()<.5)
            shipsToBeAdded[4] = s5;
        else
            shipsToBeAdded[4] = s10;



        int shipLength;
        int xCoord;
        int yCoord;
        boolean isH;

        // Iterates through each ship
        for(int i = 0; i < 5; i++)
        {
            do
            {
                xCoord = (int)Math.random()*10;
                yCoord = (int)Math.random()*10;

            }       // Calls addShipToGrid until the ship can be added
            while(!(addShipToGrid(xCoord, yCoord, shipsToBeAdded[i])));
        }

        for(int i = 0; i<10; i++) {

                Log.i("", this.squares[i][0] + " " + this.squares[i][1]+ " " + this.squares[i][2] + " " + this.squares[i][3] + " " + this.squares[i][4] + " " + this.squares[i][5] + " " + this.squares[i][6] + " " + this.squares[i][7] + " " + this.squares[i][8]+ " " + this.squares[i][9]);



        }

    }
}
