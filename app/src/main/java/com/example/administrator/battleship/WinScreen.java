package com.example.administrator.battleship;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/*
* @authors: Jared, Daniel, Will
* @version: December 10, 2015
* This class represents the game's win screen
* Class handles the following:
* --Displays which player won
* --Ability to restart game or go to main menu
 */
public class WinScreen extends ActionBarActivity {

    // variables for players and ai
    Player player1, player2;
    AI aiPlayer;
    boolean isAI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_screen);

        // gets and sets text to name of winner
        String p2 = (String)getIntent().getStringExtra("Winner");
        TextView win = (TextView) this.findViewById(R.id.PlayerWin);
        win.setText(p2 + "   WINS!!!");

        // gets players passed from last intent
        player1 = (Player)getIntent().getSerializableExtra("Player1");
        isAI = getIntent().getExtras().getBoolean("isAI");

        if(isAI) {
            aiPlayer = (AI) getIntent().getSerializableExtra("Player2");
        }
        else {
            player2 = (Player) getIntent().getSerializableExtra("Player2");
        }
    }

    /*
     *method: backToMainMenu
     * Purpose: switches to the main menu
     * @param: view - the Button that was pressed
      */
    public void backToMainMenu(View view) {
        Intent backToMainMenu = new Intent(this, Start_Page.class);
        startActivity(backToMainMenu);
        finish();
    }

   /*
    *method: restart
    * Purpose: switches to the ship selection page
    * @param: view - the Button that was pressed
     */

    public void restart(View view) {
        // If you are playing an ai
        if(isAI)
        {
            // Saves the player's and ai's configs from last game and
            Player newPlayer1 = new Player();
            newPlayer1.setPlayerName(player1.getPlayerName());
            newPlayer1.setColorChoiceID(player1.getColorChoiceID());
            newPlayer1.setProfilePicID(player1.getProfilePicID());

            AI newAIPlayer = new AI();
            newAIPlayer.setDifficultyLevel(aiPlayer.getDifficultyLevel());
            newAIPlayer.setPlayerName(aiPlayer.getPlayerName());
            newAIPlayer.setColorChoiceID(aiPlayer.getColorChoiceID());
            newAIPlayer.setProfilePicID(aiPlayer.getProfilePicID());

            // sends configs in the intent to new ship selection page
            Intent restart = new Intent(this, select_ship_positions_ai.class);
            restart.putExtra("AI2", newAIPlayer);
            restart.putExtra("Player1", newPlayer1);

            startActivity(restart);
        }
        else
        {
            // Same as above, saves both player's configs and sends
            // them for next game
            Player newPlayer1 = new Player();
            newPlayer1.setPlayerName(player1.getPlayerName());
            newPlayer1.setColorChoiceID(player1.getColorChoiceID());
            newPlayer1.setProfilePicID(player1.getProfilePicID());

            Player newPlayer2 = new Player();
            newPlayer2.setPlayerName(player2.getPlayerName());
            newPlayer2.setColorChoiceID(player2.getColorChoiceID());
            newPlayer2.setProfilePicID(player2.getProfilePicID());

            Intent restart = new Intent(this, Select_Ship_Positions.class);
            restart.putExtra("Player1", newPlayer1);
            restart.putExtra("Player2", newPlayer2);

            startActivity(restart);
        }
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_win_screen, menu);


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
