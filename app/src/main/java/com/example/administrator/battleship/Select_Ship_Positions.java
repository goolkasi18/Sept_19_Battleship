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

    ImageView ship5, ship4, ship3, ship2, ship1, movingShip;
    Player p1 = new Player("Will");
    Player p2 = new Player("Dan");
    static int playerSelecting = 0;
    Boolean isH = false;
    public static Point[] initPoints = {new Point(1844, 583), new Point(1708, 486), new Point(1572, 486), new Point(1436, 392), new Point(1300, 298)};
    //These are the orginal positions to draw the ships at
    public static Point[] p1nitPoints = {new Point(1844, 583), new Point(1708, 486), new Point(1572, 486), new Point(1436, 392), new Point(1300, 298)};
    public Point[] p2initPoints = {new Point(1844, 583), new Point(1708, 486), new Point(1572, 486), new Point(1436, 392), new Point(1300, 298)};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__ship__positions);




        /////////////////////////////////////////////////////////////////DRAGGING////////////////////////////////////////

        ship5 = (ImageView) findViewById(R.id.Ship5);

        ship5.setOnTouchListener(this);
        ship4 = (ImageView) findViewById(R.id.Ship4);

        ship4.setOnTouchListener(this);
        ship3 = (ImageView) findViewById(R.id.Ship3);

        ship3.setOnTouchListener(this);
        ship2 = (ImageView) findViewById(R.id.Ship2);

        ship2.setOnTouchListener(this);
        ship1 = (ImageView) findViewById(R.id.Ship1);

        ship1.setOnTouchListener(this);




        /////////////////////////////////////////////////////////////////DRAGGING////////////////////////////////////////

    }

    public void setp2(View view){


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

    }

    float x,y, diffX, diffY = 0.0f;
    boolean moving=false;
    @Override
    public boolean onTouch(View arg0, MotionEvent arg1) {




        //Declare the values needed to set the locations of the ships within the grid
        int xPos = 0, yPos = 0, shipID = 0, shipLength = 0;
        //Need Logic to determine whoes turn it is
        Player player;
        if(playerSelecting ==0) {
            player = p1;
        }
        else
            player = p2;
        //Need Logic to determine whether a ship is horizontal

        if(arg0 == ship1){
            shipLength = 2;
            shipID = 1;
        }
        if(arg0 == ship2){
            shipLength = 3;
            shipID = 2;
        }
        if(arg0 == ship3){
            shipLength = 3;
            shipID = 3;
        }
        if(arg0 == ship4){
            shipLength = 4;
            shipID = 4;
        }
        if(arg0 == ship5){
            shipLength = 5;
            shipID = 5;
        }
        //Log.i("ID :  Length", shipID + " " + shipLength);
        Log.i("SHIP: "+ shipID + "", arg0.getX()+ " " + arg0.getY());
        player.deleteShip(shipID);


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

                    if(x<250)           arg0.setX(149.47f);

                    if(x<350 && x >250) arg0.setX(254.21f);

                    if(x<460 && x >350) arg0.setX(355.61f);

                    if(x<560 && x >460) arg0.setX(461.45f);

                    if(x<669 && x >560) arg0.setX(564.4f);

                    if(x<772 && x >669) arg0.setX(669.24f);

                    if(x<877 && x >772) arg0.setX(772.31f);

                    if(x<980 && x >877) arg0.setX(877.16f);

                    if(x<1080 && x>980) arg0.setX(981.11f);

                    if(x<1165 && x >1080) arg0.setX(1085.07f);

                    if(x<1165) {
                        if (y < 250) arg0.setY(152.266f);

                        if (y < 359.699 && y > 250) arg0.setY(253.233f);

                        if (y < 463.165 && y > 359.7) arg0.setY(359.699f);

                        if (y < 569.13 && y > 463.165) arg0.setY(463.165f);

                        if (y < 667.6 && y > 569.13) arg0.setY(569.13f);

                        if (y < 773.565 && y > 667.6) arg0.setY(667.6f);

                        if (y < 878.12 && y > 773.565) arg0.setY(773.565f);

                        if (y < 980.09 && y > 878.12) arg0.setY(878.12f);

                        if (y < 1081.06 && y > 980.09) arg0.setY(980.09f);

                        if (y < 1190 && y > 1081.1) arg0.setY(1081.06f);

                    }

                }
                break;
            case MotionEvent.ACTION_UP:
                if(playerSelecting == 0)
                    p1nitPoints[shipID-1] = new Point(xPos, yPos);
                else
                    p2initPoints[shipID-1] = new Point(xPos, yPos);

                xPos = setColumn(x);
                yPos = setRow(y);
                Log.i("X : Y", xPos + " " + yPos);
                if(!(player.addShipToGrid(xPos, yPos, shipID, shipLength, isH)))
                    {
                        arg0.setX(Select_Ship_Positions.initPoints[shipID - 1].x);
                        arg0.setY(Select_Ship_Positions.initPoints[shipID - 1].y);
                    }



                for(int i = 0; i<9; i++) {
                    for (int j = 0; j < 9; j++) {
                        //Log.i(" Hello ", player.squares[i][j]+ "");
                    }
                   // Log.i("\n", "");
                }
                moving = false;
                break;
        }
        return true;
    }

    public int setColumn(float x)
    {
        int c = -1;
        if(x<250)    c=0;

        if(x<350 && x >250) c=1;

        if(x<460 && x >350) c=2;

        if(x<560 && x >460) c=3;

        if(x<669 && x >560) c=4;

        if(x<772 && x >669) c=5;

        if(x<877 && x >772) c=6;

        if(x<980 && x >877) c=7;

        if(x<1080 && x>980) c=8;

        if(x<1165 && x >1080) c=9;

        return c;
    }

    public int setRow(float y){
        int r = -1;
        if(y<250) r = 0;

        if(y<359.699 && y > 250) r = 1;

        if(y<463.165 && y >359.7) r = 2;

        if(y<569.13 && y > 463.165) r = 3;

        if(y<667.6 && y > 569.13) r = 4;

        if(y<773.565 && y > 667.6) r = 5;

        if(y<878.12 && y > 773.565)r = 6;

        if(y<980.09 && y > 878.12) r = 7;

        if(y<1081.06 && y > 980.09) r = 8;

        if(y< 1190 && y>1081.1) r = 9;

        return r;
    }










    /*
    *  Method: switchToMain
    *  Purpose: finishes the current activity and switches back to the start Page
     */
    public void switchToMain(View view)
    {

        Intent main = new Intent(this, Start_Page.class);
        startActivity(main);

        finish(); //this will finish the current intent and go back to the other code where it left off
    }

    /*
    *  Method: startGame
    *  Purpose: finishes the current activity and switches to the game
     */

    public void selectShips(View view)
    {

        if(playerSelecting == 0) {
            playerSelecting = 1;
            Intent selectShip = new Intent(this, Select_Ship_Positions.class);
            startActivity(selectShip);
        }

        else {
            //The game is started, and if they go back to select, it will restart the selection process
            playerSelecting = 0;
            Intent startGame = new Intent(this, MainActivity.class);
            startActivity(startGame);
        }
        finish();



    }

    public void rotateShips(View view)
    {
        isH = !isH;
    }

    public void convertShips()
    {

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
