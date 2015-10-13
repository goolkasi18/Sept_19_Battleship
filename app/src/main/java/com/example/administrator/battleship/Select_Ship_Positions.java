package com.example.administrator.battleship;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


public class Select_Ship_Positions extends ActionBarActivity implements View.OnTouchListener {

    ImageView ship5, ship4, ship3, ship2, ship1, movingShip;

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

    float x,y=0.0f;
    boolean moving=false;
    @Override
    public boolean onTouch(View arg0, MotionEvent arg1) {
        movingShip = (ImageView) arg0;
        switch (arg1.getAction()) {
            case MotionEvent.ACTION_DOWN:
                moving = true;
                break;
            case MotionEvent.ACTION_MOVE:
                if (moving) {
                    x = arg1.getRawX() - movingShip.getWidth() / 2;
                    y = arg1.getRawY() - movingShip.getHeight() * 3 / 2;
                    movingShip.setX(x);
                    movingShip.setY(y);
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
