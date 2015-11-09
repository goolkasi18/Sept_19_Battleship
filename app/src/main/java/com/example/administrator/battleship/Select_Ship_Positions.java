package com.example.administrator.battleship;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


public class Select_Ship_Positions extends ActionBarActivity implements View.OnTouchListener {

    ImageView Vship5,Vship4,Vship3,Vship2,Vship1,Hship5,Hship4,Hship3,Hship2,Hship1;
    Player p1 = new Player("Will");
    Player p2 = new Player("Dan");
    //static int playerSelecting = 0;
   // Boolean isH = false;
    //public static Point[] initPoints = {new Point(1844, 583), new Point(1708, 486), new Point(1572, 486), new Point(1436, 392), new Point(1300, 298)};
    //These are the orginal positions to draw the ships at
    //public static Point[] p1nitPoints = {new Point(1844, 583), new Point(1708, 486), new Point(1572, 486), new Point(1436, 392), new Point(1300, 298)};
    //public Point[] p2initPoints = {new Point(1844, 583), new Point(1708, 486), new Point(1572, 486), new Point(1436, 392), new Point(1300, 298)};
    Ship s1,s2,s3,s4,s5,s6,s7,s8,s9,s10;
    Ship[] ships = new Ship[10];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__ship__positions);

        //set on touch listeners for the ships visible and invisible on startup
        Vship5 = (ImageView) findViewById(R.id.Ship5);
        Vship4 = (ImageView) findViewById(R.id.Ship4);
        Vship3 = (ImageView) findViewById(R.id.Ship3);
        Vship2 = (ImageView) findViewById(R.id.Ship2);
        Vship1 = (ImageView) findViewById(R.id.Ship1);

        Hship5 = (ImageView) findViewById(R.id.HShip5);
        Hship4 = (ImageView) findViewById(R.id.HShip4);
        Hship3 = (ImageView) findViewById(R.id.HShip3);
        Hship2 = (ImageView) findViewById(R.id.HShip2);
        Hship1 = (ImageView) findViewById(R.id.HShip1);


        Vship5.setOnTouchListener(this);
        Vship4.setOnTouchListener(this);
        Vship3.setOnTouchListener(this);
        Vship2.setOnTouchListener(this);
        Vship1.setOnTouchListener(this);

        Hship5.setOnTouchListener(this);
        Hship4.setOnTouchListener(this);
        Hship3.setOnTouchListener(this);
        Hship2.setOnTouchListener(this);
        Hship1.setOnTouchListener(this);
        //set on touch listeners for the five ships **NOT** visible on startup


       //Ship(float initx, float inity, int initLength, int initHeight, int initShipID, ImageView initImage)
        s1 = new Ship(Vship1.getX(), Vship1.getY(), 0, 2, 1, Vship1);
        s2 = new Ship(Vship1.getX(), Vship1.getY(), 0, 3, 2, Vship2);
        s3 = new Ship(Vship1.getX(), Vship1.getY(), 0, 3, 3, Vship3);
        s4 = new Ship(Vship1.getX(), Vship1.getY(), 0, 4, 4, Vship4);
        s5 = new Ship(Vship1.getX(), Vship1.getY(), 0, 5, 5, Vship5);

        s6 = new Ship(Hship1.getX(), Hship1.getY(), 2, 0, 1, Hship1);
        s7 = new Ship(Hship2.getX(), Hship2.getY(), 3, 0, 2, Hship2);
        s8 = new Ship(Hship3.getX(), Hship3.getY(), 3, 0, 3, Hship3);
        s9 = new Ship(Hship4.getX(), Hship4.getY(), 4, 0, 4, Hship4);
        s10 = new Ship(Hship5.getX(), Hship5.getY(), 5, 0, 5, Hship5);
        ships[0] = s1; ships[1] = s2; ships[2] = s3; ships[3] = s4; ships[4] = s5; ships[5] = s6; ships[6] = s7; ships[7] = s8; ships[8] = s9; ships[9] = s10;
    }

    /*public void setp2(View view){
            ship5.setX((float)p2initPoints[4].x);
            ship5.setY((float)p2initPoints[4].y);

            ship4.setX((float)p2initPoints[3].x);
            ship4.setY((float)p2initPoints[3].y);

            ship3.setX((float)p2initPoints[2].x);
            ship3.setY((float)p2initPoints[2].y);

            ship2.setX((float)p2initPoints[1].x);
            ship2.setY((float)p2initPoints[1].y);

            ship1.setX((float)p2initPoints[0].x);
            ship1.setY((float)p2initPoints[0].y);

    } */

    float x,y, diffX, diffY = 0.0f;
    boolean moving=false;
    int column,row,index;
    @Override
    public boolean onTouch(View arg0, MotionEvent arg1) {

        for(index = 0; index<=ships.length; index++)
            if(arg0 == ships[index].image)
                break;

        Log.i("SHIP: "+ ships[index].shipID + "", arg0.getX()+ " " + arg0.getY());
        p1.deleteShip(ships[index]);
        switch (arg1.getAction()) {
            case MotionEvent.ACTION_DOWN:
                diffX = arg0.getX()-arg1.getRawX();
                diffY = arg0.getY() - arg1.getRawY();
                moving = true;
                break;
            case MotionEvent.ACTION_MOVE:
                if (moving) {
                    x = arg1.getRawX() + diffX;
                    y = arg1.getRawY() + diffY;
                    arg0.setX(x);
                    arg0.setY(y);
                    Log.i("X Position : Y Position", x + "  " + y);

                    if(x<250)arg0.setX(149.47f);
                    else if(x<350 && x >250) arg0.setX(254.21f);
                    else if(x<460 && x >350) arg0.setX(355.61f);
                    else if(x<560 && x >460) arg0.setX(461.45f);
                    else if(x<669 && x >560) arg0.setX(564.4f);
                    else if(x<772 && x >669) arg0.setX(669.24f);
                    else if(x<877 && x >772) arg0.setX(772.31f);
                    else if(x<980 && x >877) arg0.setX(877.16f);
                    else if(x<1080 && x>980) arg0.setX(981.11f);
                    else if(x<1165 && x >1080) arg0.setX(1085.07f);

                    if(x<1165) {
                        if (y < 250) arg0.setY(152.266f);
                        else if (y < 359.699 && y > 250) arg0.setY(253.233f);
                        else if (y < 463.165 && y > 359.7) arg0.setY(359.699f);
                        else if (y < 569.13 && y > 463.165) arg0.setY(463.165f);
                        else if (y < 667.6 && y > 569.13) arg0.setY(569.13f);
                        else if (y < 773.565 && y > 667.6) arg0.setY(667.6f);
                        else if (y < 878.12 && y > 773.565) arg0.setY(773.565f);
                        else if (y < 980.09 && y > 878.12) arg0.setY(878.12f);
                        else if (y < 1081.06 && y > 980.09) arg0.setY(980.09f);
                        else if (y < 1190 && y > 1081.1) arg0.setY(1081.06f);
                    }

                    column = setColumn(x);
                    row = setRow(y);
                    if(column+ships[index].length > 10 || row+ships[index].height > 10) ships[index].image.setBackgroundColor(Color.RED);
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.i("X : Y", column + " " + row);
                Boolean worked = p1.addShipToGrid(column, row, ships[index]);
                if(worked)
                {
                    ships[index].togglePlaced();
                }
                else if(!worked)
                {
                    reset(ships[index]);
                }
                moving = false;
                break;
        }
        return true;
    }

    public int setColumn(float x)
    {
        int c = -1;
        if(x<250)    c=1;
        else if(x<350 && x >250) c=2;
        else if(x<460 && x >350) c=3;
        else if(x<560 && x >460) c=4;
        else if(x<669 && x >560) c=5;
        else if(x<772 && x >669) c=6;
        else if(x<877 && x >772) c=7;
        else if(x<980 && x >877) c=8;
        else if(x<1080 && x>980) c=9;
        else if(x<1165 && x >1080) c=10;

        return c;
    }

    public int setRow(float y){
        int r = -1;
        if(y<250) r = 1;
        else if(y<359.699 && y > 250) r = 2;
        else if(y<463.165 && y >359.7) r = 3;
        else if(y<569.13 && y > 463.165) r = 4;
        else if(y<667.6 && y > 569.13) r = 5;
        else if(y<773.565 && y > 667.6) r = 6;
        else if(y<878.12 && y > 773.565)r = 7;
        else if(y<980.09 && y > 878.12) r = 8;
        else if(y<1081.06 && y > 980.09) r = 9;
        else if(y< 1190 && y>1081.1) r = 10;

        return r;
    }

    public void reset(Ship ship)
    {
        ship.image.setX(ship.originx);
        ship.image.setY(ship.originy);
    }

    public void rotateShips(View view)
    {
        for(int i=0; i<=ships.length; i++) { //for each ship in ships array
            if(ships[i].placed == false) //if that ship is not placed
            {
                ships[i].poof(); //make it disappear
                if(i < 5)
                    ships[i+5].tada(); //make the opposite of it appear
                else if(i >= 5)
                    ships[i-5].tada(); //make the opposite of it appear
            }
        }
    }

    /*
    *  Method: switchToMain
    *  Purpose: finishes the current activity and switches back to the start Page
     */
    public void switchToMain(View view)
    {
        Intent main = new Intent(this, Start_Page.class);
        startActivity(main);
        finish();
    }


    /*
    *  Method: startGame
    *  Purpose: finishes the current activity and switches to the game
     */
    public void selectShips(View view)
    {
        Intent selectShip = new Intent(this, MainActivity.class);
        startActivity(selectShip);
        finish();
    }

    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select__ship__positions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
