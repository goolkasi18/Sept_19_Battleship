package com.example.administrator.battleship;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * this will impliment a moving background maybe
 * Created by goolkasi18 on 9/15/2015.
 */
public class Background {

    private Bitmap image;
    private int x, y;

    public Background(Bitmap res) {
        image = res;
    }
    public void update()
    {

    }
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(image, x, y, null);
    }
}
