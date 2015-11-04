package com.example.administrator.battleship;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {


    //Global 2 dimensional array
    private Player player1;
    private Player player2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player1 = new Player();
        player2 = new Player();
        player1.initSquares();
        player2.initSquares();


    }





    public void createMap()
    {
        //how to declare a button, this can be used to create a hashmap of all 200 buttons
        String button = "left_"+ 8 + "_" + 1;
       // int resId= getResources().getIdentifier(button, "drawable", MainActivity.this.getPackageName());
        //Button one = (Button)this.findViewById(resId);


    }

    public void exitToStart(View view)
    {
        //********* THIS WILL MAKE A NEW MAIN MENU ON TOP OF EVERYTHIGN ALREADY ***************
        //Intent main = new Intent(this, Start_Page.class);
        //startActivity(main);

        finish(); //this will finish the current intent and go back to the other code where it left off
    }

    public void checkHit(View view)
    {
        Log.i("X POSITION: ", "" + view.getLeft() / 80);
        Log.i("Y POSITION: ", "" +  view.getTop()/80);
        int x = view.getLeft()/80;
        int y = view.getTop()/80;
        player1.squares[0][0]=2;
        if(player1.squares[x][y]==0)
        {
            player1.squares[x][y]++;
            view.setBackgroundResource(R.drawable.miss);
        }



        if(player1.squares[x][y]==2)
        {
            player1.squares[x][y]++;
            view.setBackgroundResource(R.drawable.hit);
        }


        Log.i("POSITION VAL: ", ""+player1.squares[x][y]);
    }

    public void checkHitPlayer2(View view)
    {
        Log.i("X POSITION: ", ""+view.getLeft()/80);
        Log.i("Y POSITION: ", ""+view.getTop()/80);

        int x = view.getLeft()/80;
        int y = view.getTop()/80;
        player2.squares[5][5]=2;
        if(player2.squares[x][y]==0)
        {
            view.setBackgroundResource(R.drawable.miss);
            player2.squares[x][y]++;
        }

        if(player2.squares[x][y]==2)
        {
            view.setBackgroundResource(R.drawable.hit);
            player2.squares[x][y]++;
        }



        Log.i("POSITION VAL: ", ""+player2.squares[x][y]);
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
