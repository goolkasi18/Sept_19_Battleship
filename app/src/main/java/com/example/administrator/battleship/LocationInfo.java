package com.example.administrator.battleship;

import android.graphics.Color;

/**
 * Created by rothschi18 on 9/22/2015.
 * goolkasi18 9/23/2015
 */
public class LocationInfo {
    //nothing is 0, if it has a ship that is 2: 1 is white, 3 is hit ship
    private int status;
    private boolean hasBoat;
    private Color locColor;
    private int x;
    private int y;


    public LocationInfo(int x, int y)
    {
        status = 0;
        hasBoat = false;
    }

    public void setBoat(Boolean status){ this.hasBoat = status;}
    public boolean containsBoat() { return hasBoat; }

}
