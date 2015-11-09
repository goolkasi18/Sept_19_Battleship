package com.example.administrator.battleship;

import android.widget.ImageView;

/**
 * Created by Jared on 11/4/2015.
 */
public class AI extends Player{

    private Player player;

    public void aiAddShipsToGrid(){
        int shipLength;
        int xCoord;
        int yCoord;
        int randOrientation;
        boolean isH;

        // Iterates through each ship
        for(int shipId = 1; shipId < 6; shipId++)
        {
            xCoord = 0;
            yCoord = 0;
            shipLength = 2;
            isH = false;

           // do
            {
                randOrientation = (int)Math.random()*2;
                if(randOrientation <= 0)
                {
                    isH = true;
                }
                else
                {
                    isH = false;
                }
                xCoord = (int)Math.random()*9;
                yCoord = (int)Math.random()*9;
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
            }       // Calls addShipToGrid until the ship can be added
         //   while(!(addShipToGrid(xCoord, yCoord, shipId, shipLength, isH, ship)));


         //   addShipToGrid(xCoord, yCoord, shipId, shipLength, isH, ship);
        }

    }

    public boolean addShipToGrid(int col, int row, int shipID, int shipLength, Boolean isH, ImageView ship){


        return true;
    }
}
