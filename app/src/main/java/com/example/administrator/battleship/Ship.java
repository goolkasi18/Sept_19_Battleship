package com.example.administrator.battleship;

import android.view.View;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by goolkasi18 on 11/8/2015.
 */
public class Ship implements Serializable{
    boolean placed;
    int length,height,shipID,imageID;
    float originx,originy;
    int hits;
    boolean sunk;
    int viewID;

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

    public void togglePlaced()
    {
        placed = !placed;
    }

    public int getID(){return shipID;}

    public void upHits(){hits++;}

    public void sink(){sunk = true;}

    public boolean getSunk(){return sunk;}
}
