package com.example.administrator.battleship;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createMap();



    }
    public void createMap()
    {
        //how to declare a button, this can be used to create a hashmap of all 200 buttons
        String button = "left_"+ 8 + "_" + 1;
        int resId= getResources().getIdentifier(button, "drawable", MainActivity.this.getPackageName());
        Button one = (Button)this.findViewById(resId);


    }

    public void exitToStart(View view)
    {

        Intent exitGame = new Intent(this, Start_Page.class);
        startActivity(exitGame);
        finish();




    }

    public void checkHit(View view)
    {
        //check array spot according to button ID
        //if array is 0


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
