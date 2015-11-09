package com.example.administrator.battleship;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by goolkasi18 on 11/8/2015.
 */
public class Ship {
    boolean placed;
    int length,height,shipID,imageID;
    float originx,originy;
    ImageView image;
    public Ship(float initx, float inity, int initLength, int initHeight, int initShipID, ImageView initImage, int initImageID){
        originx = initx;
        originy = inity;
        length = initLength;
        height = initHeight;
        placed = false;
        shipID = initShipID;
        image = initImage;
        imageID = initImageID;
    }

    public Ship() //empty for testing
    {
        originx = 0;
        originy = 0;
        length = 5;
        height = 0;
        placed = false;
        shipID = 5;
        image = null;
        imageID = R.id.HShip5;
    }

    public void togglePlaced()
    {
        placed = !placed;
    }

    public void poof(){image.setVisibility(View.INVISIBLE);}

    public void tada(){image.setVisibility(View.VISIBLE);}

    public int getID(){return shipID;}
}
