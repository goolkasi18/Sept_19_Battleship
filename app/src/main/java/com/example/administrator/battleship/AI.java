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

    public AI(String playerName){
        super(playerName);
        shots = new int[10][10];
        initShots();
    }

    public void addShipToGrid()
    {
        double i = Math.random()*10;
        int done = 0;
        while (done < 5){
            int x = (int)Math.random()*10-1;
            int y = (int)Math.random()*10-1;
            if(addShipToGrid(x, y,ships[(int)i-1]))
                done++;
        }
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

}
