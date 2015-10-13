package com.example.administrator.battleship;

import android.content.Intent;
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
    Player p1 = new Player(1, "Will");
    Player p2 = new Player(2, "Dan");

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

    float x,y, diffX, diffY =0.0f;
    boolean moving=false;
    @Override
    public boolean onTouch(View arg0, MotionEvent arg1) {
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

                    if(x<250){
                        arg0.setX(149.47f);

                        if(y<250) arg0.setY(152.266f);

                        if(y<359.699 && y > 250) arg0.setY(253.233f);

                        if(y<463.165 && y >359.7) arg0.setY(359.699f);

                        if(y<569.13 && y > 463.165) arg0.setY(463.165f);

                        if(y<667.6 && y > 569.13) arg0.setY(569.13f);

                        if(y<773.565 && y > 667.6) arg0.setY(667.6f);

                        if(y<878.12 && y > 773.565) arg0.setY(773.565f);

                        if(y<980.09 && y > 878.12) arg0.setY(878.12f);

                        if(y<1081.06 && y > 980.09) arg0.setY(980.09f);

                        if(y > 1190) arg0.setY(1081.06f);


                    }

                    if(x<350 && x >250) arg0.setX(254.21f);

                    if(x<460 && x >350) arg0.setX(355.61f);

                    if(x<560 && x >460) arg0.setX(461.45f);

                    if(x<669 && x >560) arg0.setX(564.4f);

                    if(x<772 && x >669) arg0.setX(669.24f);

                    if(x<877 && x >772) arg0.setX(772.31f);

                    if(x<980 && x >877) arg0.setX(877.16f);

                    if(x<1080 && x>980) arg0.setX(981.11f);

                    if(x<1165 && x >1080) arg0.setX(1085.07f);






                }
                break;
            case MotionEvent.ACTION_UP:
                moving = false;
                break;
        }
        return true;
    }











    /*
    *  Method: switchToMain
    *  Purpose: finishes the current activity and switches back to the start Page
     */
    public void switchToMain(View view)
    {

        Intent switchToMain = new Intent(this, Start_Page.class);
        startActivity(switchToMain);
        finish();

    }

    /*
    *  Method: startGame
    *  Purpose: finishes the current activity and switches to the game
     */

    public void startGame(View view)
    {

        Intent startGame = new Intent(this, MainActivity.class);
        startActivity(startGame);
        finish();

    }

    public void rotateShips(View view)
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
