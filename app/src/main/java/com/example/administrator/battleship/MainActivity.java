package com.example.administrator.battleship;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends ActionBarActivity {


    //Global 2 dimensional array
    private Player player1;
    private Player player2;
    private AI AIPlayer;

    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Jello0", "Jello1");
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
        Intent main = new Intent(this, Start_Page.class);
        startActivity(main);
        finish();
    }

    public void callAIPlayerTakeTurn(){
        Point attackPoint = AIPlayer.takeTurn();

        boolean hit = player1.attack(attackPoint.x, attackPoint.y);
        //if(!hit)
           //view.setBackgroundResource(R.drawable.miss);
        //if(hit)
            //view.setBackgroundResource(R.drawable.hit);
    }

    public void checkHit(View view)
    {

        //this only works for player1 right now so we need an "active player" variable so the two players must be an array with an active index
        view.setEnabled(false);
        int x = view.getLeft()/80;
        int y = view.getTop()/80;
        boolean hit = player1.attack(x, y);
        if(!hit) {
            view.setBackgroundResource(R.drawable.miss);
            view.setEnabled(false);
        }
        if(hit) {
            view.setBackgroundResource(R.drawable.hit);
            view.setEnabled(false);
            if(player1.ships[player1.squares[x][y]-1].hits == player1.ships[player1.squares[x][y]-1].length || player1.ships[player1.squares[x][y]-1].hits == player1.ships[player1.squares[x][y]-1].height)
            {
                //then you sunk a ship.
                player1.ships[player1.squares[x][y]-1].sink();
                //redwar whatever image is on screen for that ship
            }
        }
    }

    public void checkHitPlayer2(View view)
    {
        view.setEnabled(false);
        int x = view.getLeft()/80;
        int y = view.getTop()/80;
        boolean hit = player2.attack(x, y);
        if(!hit)
            view.setBackgroundResource(R.drawable.miss);
        if(hit)
            view.setBackgroundResource(R.drawable.hit);
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
