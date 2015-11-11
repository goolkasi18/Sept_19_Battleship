package com.example.administrator.battleship;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {


    //Global 2 dimensional array
    private Player p1;
    private Player p2;
    private AI AIPlayer;
    Player[] players = new Player[2];
    int activePlayer = 0;

    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Jello0", "Jello1");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p1 = (Player)getIntent().getSerializableExtra("Player1");
        p2 = (Player)getIntent().getSerializableExtra("Player2");
        players[0] = p1;
        players[1] = p2;
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

        boolean hit = p1.attack(attackPoint.x, attackPoint.y);
        //if(!hit)
           //view.setBackgroundResource(R.drawable.miss);
        //if(hit)
            //view.setBackgroundResource(R.drawable.hit);
    }

    public void checkHit(View view)
    {
        if((view.getParent() == findViewById(R.id.left_button_grid) && activePlayer == 1) || (view.getParent() == findViewById(R.id.right_button_grid) && activePlayer == 0)) {
            view.setEnabled(false);
            int x = view.getLeft() / 80;
            int y = view.getTop() / 80;
            boolean hit = players[activePlayer].attack(x, y);
            if (!hit) {
                view.setBackgroundResource(R.drawable.miss);
                view.setEnabled(false);
            }
            if (hit) {
                view.setBackgroundResource(R.drawable.hit);
                view.setEnabled(false);
                if (players[activePlayer].ships[(players[activePlayer].squares[x][y]-1)].hits == players[activePlayer].ships[(p1.squares[x][y]-1)].length || players[activePlayer].ships[(p1.squares[x][y]-1)].hits == players[activePlayer].ships[(p1.squares[x][y]-1)].height) {
                    //then you sunk a ship.
                    players[activePlayer].ships[players[activePlayer].squares[x][y] - 1].sink();
                    //redwar whatever image is on screen for that ship
                }
            }
            endTurn();
        }
    }

    /*public void checkHitPlayer2(View view)
    {
        view.setEnabled(false);
        int x = view.getLeft()/80;
        int y = view.getTop()/80;
        boolean hit = p2.attack(x, y);
        if(!hit)
            view.setBackgroundResource(R.drawable.miss);
        if(hit)
            view.setBackgroundResource(R.drawable.hit);
    }*/

    public void endTurn(){
        if(activePlayer == 0)
            activePlayer = 1;
        else
            activePlayer = 0;
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
