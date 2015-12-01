package com.example.administrator.battleship;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


public class select_ship_positions_ai  extends ActionBarActivity implements View.OnTouchListener {

    ImageView Vship5,Vship4,Vship3,Vship2,Vship1,Hship5,Hship4,Hship3,Hship2,Hship1;
    Player p1;
    AI p2;
    Ship s1,s2,s3,s4,s5,s6,s7,s8,s9,s10;
    Ship[] ships = new Ship[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_ship_positions_ai);

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

        p1 = (Player)getIntent().getSerializableExtra("Player1");
        p2 = (AI)getIntent().getSerializableExtra("AI2");

        //ImageView background = (ImageView)findViewById(R.id.Background);
        //background.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.player1positionspage, 1000, 600));
    }

    public void ready(View view){
        s1 = new Ship(Vship1.getX(), Vship1.getY(), 0, 2, 1, R.drawable.r_vertical2, R.id.Ship1);
        s2 = new Ship(Vship2.getX(), Vship2.getY(), 0, 3, 2, R.drawable.r_vertical3, R.id.Ship2);
        s3 = new Ship(Vship3.getX(), Vship3.getY(), 0, 3, 3, R.drawable.r_vertical3, R.id.Ship3);
        s4 = new Ship(Vship4.getX(), Vship4.getY(), 0, 4, 4, R.drawable.r_vertical4, R.id.Ship4);
        s5 = new Ship(Vship5.getX(), Vship5.getY(), 0, 5, 5, R.drawable.r_vertical5, R.id.Ship5);

        s6 = new Ship(Hship1.getX(), Hship1.getY(), 2, 0, 1, R.drawable.horizontal2, R.id.HShip1);
        s7 = new Ship(Hship2.getX(), Hship2.getY(), 3, 0, 2, R.drawable.horizontal3, R.id.HShip2);
        s8 = new Ship(Hship3.getX(), Hship3.getY(), 3, 0, 3, R.drawable.horizontal3, R.id.HShip3);
        s9 = new Ship(Hship4.getX(), Hship4.getY(), 4, 0, 4, R.drawable.horizontal4, R.id.HShip4);
        s10 = new Ship(Hship5.getX(), Hship5.getY(), 5, 0, 5, R.drawable.horizontal5, R.id.HShip5);
        ships[0] = s1; ships[1] = s2; ships[2] = s3; ships[3] = s4; ships[4] = s5; ships[5] = s6; ships[6] = s7; ships[7] = s8; ships[8] = s9; ships[9] = s10;
        p1.ships = ships;
        p2.ships = ships;
        findViewById(R.id.privacy).setVisibility(View.GONE);
    }

    float x,y, diffX, diffY = 0.0f;
    boolean moving=false;
    int column,row,index;
    @Override
    public boolean onTouch(View arg0, MotionEvent arg1) {

        for(index = 0; index<ships.length; index++)
            if(arg0 == findViewById(ships[index].viewID))
                break;

        Log.i("SHIP: " + ships[index].shipID + "", arg0.getX() + " " + arg0.getY());
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
                    if(column+ships[index].length > 10 || row+ships[index].height > 10) findViewById(ships[index].viewID).setBackgroundColor(Color.RED);
                    else if(p1.testShip(row,column,ships[index]) == false) findViewById(ships[index].viewID).setBackgroundColor(Color.RED);
                    else findViewById(ships[index].viewID).setBackgroundResource(ships[index].imageID);
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.i("X : Y", column + " " + row);
                Boolean worked = p1.addShipToGrid(row,column, ships[index]);
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
        if(x<250)    c=0;
        else if(x<350 && x >250) c=1;
        else if(x<460 && x >350) c=2;
        else if(x<560 && x >460) c=3;
        else if(x<669 && x >560) c=4;
        else if(x<772 && x >669) c=5;
        else if(x<877 && x >772) c=6;
        else if(x<980 && x >877) c=7;
        else if(x<1080 && x>980) c=8;
        else if(x<1165 && x >1080) c=9;

        return c;
    }

    public int setRow(float y){
        int r = -1;
        if(y<250) r = 0;
        else if(y<359.699 && y > 250) r = 1;
        else if(y<463.165 && y >359.7) r = 2;
        else if(y<569.13 && y > 463.165) r = 3;
        else if(y<667.6 && y > 569.13) r = 4;
        else if(y<773.565 && y > 667.6) r = 5;
        else if(y<878.12 && y > 773.565)r = 6;
        else if(y<980.09 && y > 878.12) r = 7;
        else if(y<1081.06 && y > 980.09) r = 8;
        else if(y< 1190 && y>1081.1) r = 9;

        return r;
    }

    public void reset(Ship ship)
    {
        for(index = 0; index<=ships.length; index++)
            if(ship == ships[index])
                break;
        //run an if statement to test if the user is in horizontal mode and make sure the image that is pulled off is in horizontal mode.
        if(index<5 && horizontal) {
            findViewById(ship.viewID).setBackgroundResource(ship.imageID);
            findViewById(ship.viewID).setX(ship.originx);
            findViewById(ship.viewID).setY(ship.originy);
            findViewById(ship.viewID).setVisibility(View.INVISIBLE);
            findViewById(ships[index+5].viewID).setVisibility(View.VISIBLE);
        }
        else if(index > 4 && !horizontal) {
            findViewById(ship.viewID).setBackgroundResource(ship.imageID);
            findViewById(ship.viewID).setX(ship.originx);
            findViewById(ship.viewID).setY(ship.originy);
            findViewById(ship.viewID).setVisibility(View.INVISIBLE);
            findViewById(ships[index-5].viewID).setVisibility(View.VISIBLE);
        }
        else
        {
            findViewById(ship.viewID).setBackgroundResource(ship.imageID);
            findViewById(ship.viewID).setX(ship.originx);
            findViewById(ship.viewID).setY(ship.originy);
        }
    }

    boolean horizontal = false;
    public void rotateShips(View view)
    {
        if(!horizontal) {
            horizontal = !horizontal;
            for (int i = 0; i < 5; i++) { //for each ship in ships array
                if (ships[i].placed == false) //if that ship is not placed
                {
                    findViewById(ships[i].viewID).setVisibility(View.INVISIBLE); //make it disappear
                    findViewById(ships[i+5].viewID).setVisibility(View.VISIBLE); //make the opposite of it appear
                }
            }
        }
        else
        {
            horizontal = !horizontal;
            for (int i = 5; i < ships.length; i++) { //for each ship in ships array
                if (ships[i].placed == false) //if that ship is not placed
                {
                    findViewById(ships[i].viewID).setVisibility(View.INVISIBLE); //make it disappear
                    findViewById(ships[i-5].viewID).setVisibility(View.VISIBLE); //make the opposite of it appear
                }
            }
        }
    }

    /*
    *  Method: switchToMain
    *  Purpose: finishes the current activity and switches back to the start Page
     */
    public void switchToMain(View view)
    {
        finish();
    }


    /*
   *  Method: startGame
   *  Purpose: finishes the current activity and switches to the game
    */
    public void startGame(View view)
    {
        if(p2.setUpAi()) {
            Intent startGame = new Intent(this, MainActivity.class); //needs to be a new AI class
            startGame.putExtra("Player1", p1);
            startGame.putExtra("Player2", p2);
            startGame.putExtra("isAI", true);
            startActivity(startGame);
            finish();
        }
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

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}