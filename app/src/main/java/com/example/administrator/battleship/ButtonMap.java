package com.example.administrator.battleship;

import android.app.Activity;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.HashMap;

/**
 * Created by rothschi18 on 9/22/2015.
 */
public class ButtonMap {
    private HashMap<ImageButton, LocationInfo> Map;

    public ButtonMap()
    {
        Map = new HashMap<ImageButton, LocationInfo>();
    }

    public void addButton(ImageButton spot, LocationInfo loc)
    {
        if(Map.containsKey(spot))
            return;

        else
            Map.put(spot, loc);
    }


}
