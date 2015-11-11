package com.example.administrator.battleship;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AI_Player_configs extends ActionBarActivity {

    //Both players are human, need to declare two Player objects upon start of this class
    public Player p1,p2;

    //Player 2 is a robot, and therefore is always ready
    public boolean readyPlayer1 = false;
    public boolean readyPlayer2 = true;

    //Text fields that hold the players name
    public EditText p1Name;
    public EditText p2Name;
    /*
    *start the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai__player_configs);
        Button startB = (Button) this.findViewById(R.id.select_ships);
        startB.setVisibility(View.GONE);
        p1 = new Player();
        p2 = new Player();

        p1Name = (EditText) this.findViewById(R.id.Player1_Name);
        p2Name = (EditText) this.findViewById(R.id.Player2_Name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_configs__two_player, menu);
        return true;
    }

    /**
     * Once the players are done selecting configurations they will be able to move onto selecting ships
     *
     * @param view
     */
    public void switchToSelectShips(View view) {

        /*
        * Upon switching views, find the two player names, and set the respective players
         */


        p1.setPlayerName(p1Name.getText().toString());
        p2.setPlayerName(p2Name.getText().toString());

        if(readyPlayer1&&readyPlayer2) {
            Intent switchToSelect = new Intent(this, Select_Ship_Positions.class);
            switchToSelect.putExtra("Player1", p1);
            switchToSelect.putExtra("Player2", p2);
            startActivity(switchToSelect);
            finish();
        }

    }


    public void readyPlayer1(View view){
        //If the other player is ready, and player 2 has shown they are ready, make the start game button visible
        Button startB = (Button) this.findViewById(R.id.select_ships);
        Button readyB = (Button) this.findViewById(R.id.ready_1);
        if(!readyPlayer1) {//change button to say ready
            readyPlayer1 = true;
            readyB.setText("Ready");
        }
        else//change button to say not ready
        {
            readyPlayer1 = false;
            readyB.setText("Not Ready");
        }

        if(readyPlayer1&&readyPlayer2)
            startB.setVisibility(View.VISIBLE);
        else
            startB.setVisibility(View.GONE);
    }




    /**
     * If the players want to go back to the main menu
     * @param view
     */
    public void back(View view) {
        finish();
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
