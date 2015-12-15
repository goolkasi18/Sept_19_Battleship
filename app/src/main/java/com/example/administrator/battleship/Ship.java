package com.example.administrator.battleship;

import android.view.View;
import android.widget.ImageView;

import java.io.Serializable;

/*
* @authors: Jared, Daniel, Will
* @version: November 8th, 2015
* This class represents the Ship
* Class handles the following:
* --Ability to make ships with set dimensions and image
 */
public class Ship implements Serializable{
    boolean placed;
    int length,height,shipID,imageID;
    float originx,originy;
    int hits;
    boolean sunk;
    int viewID;

    // constructor
    public Ship(float initx, float inity, int initLength, int initHeight, int initShipID, int initImageID, int initViewID){
        originx = initx;
        originy = inity;
        length = initLength;
        height = initHeight;
        placed = false;
        shipID = initShipID;
        imageID = initImageID;
        hits = 0;
        sunk = false;
        viewID = initViewID;
    }

    public Ship() //empty for testing
    {
        originx = 0;
        originy = 0;
        length = 5;
        height = 0;
        placed = false;
        shipID = 5;
        imageID = 0;
        hits = 0;
        sunk = false;
        viewID = 0;
    }

    /**
     * Setters and Getters
     *
     */
    public boolean getPlaced() {return placed;}

    public void togglePlaced()
    {
        placed = !placed;
    }

    public int getID(){return shipID;}

    public void upHits(){hits++;}

    public void sink(){sunk = true;}

    public boolean getSunk(){return sunk;}
}
