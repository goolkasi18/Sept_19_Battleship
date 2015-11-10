package com.example.administrator.battleship;

import android.graphics.Point;
import android.widget.ImageView;

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

    public Point takeTurn(){
        Point p = new Point();
        p.x = 1;
        return p;
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
        int shipLength;
        int xCoord;
        int yCoord;
        boolean isH;

        // Iterates through each ship
        for(int shipId = 1; shipId < 6; shipId++)
        {
            xCoord = 0;
            yCoord = 0;
            shipLength = 2;
            isH = false;

            if(shipId==1)
            {
                shipLength = 2;
            }
            if(shipId==2)
            {
                shipLength = 3;
            }
            if(shipId==3)
            {
                shipLength = 3;
            }
            if(shipId==4)
            {
                shipLength = 4;
            }
            if(shipId==5)
            {
                shipLength = 5;
            }

            do
            {
                if(Math.random() <= 0.5)
                {
                    isH = true;
                }
                else
                {
                    isH = false;
                }
                xCoord = (int)Math.random()*10;
                yCoord = (int)Math.random()*10;

            }       // Calls addShipToGrid until the ship can be added
            while(!(addShipToGrid(xCoord, yCoord, shipId, shipLength, isH)));
        }

    }
}
