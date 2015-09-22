package com.example.administrator.battleship;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Select_Ship_Positions extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__ship__positions);
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
