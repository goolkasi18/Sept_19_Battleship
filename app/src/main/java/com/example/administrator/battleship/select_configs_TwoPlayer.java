package com.example.administrator.battleship;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class select_configs_TwoPlayer extends ActionBarActivity {

    public boolean readyPlayer1 = false;
    public boolean readyPlayer2 = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_configs__two_player);
        Button startB = (Button) this.findViewById(R.id.select_ships);
        startB.setAlpha(0);
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
        if(readyPlayer1&&readyPlayer2) {
            finish();
            Intent switchToSelect = new Intent(this, Select_Ship_Positions.class);
            startActivity(switchToSelect);
        }

    }

    public void readyPlayer1(View view){
        //If the other player is ready, and player 2 has shown they are ready, make the start game button visible
        Button startB = (Button) this.findViewById(R.id.select_ships);
        Button readyB = (Button) this.findViewById(R.id.ready_1);
        if(!readyPlayer1) {
            //change button to say ready
            readyPlayer1 = true;
            readyB.setText("Ready");
        }
        else
        {
            //change button to say not ready
            readyPlayer1 = false;
            readyB.setText("Not Ready");
        }


        if(readyPlayer1&&readyPlayer2){
            startB.setAlpha(1);


        }
        else
            startB.setAlpha(0);
    }

    public void readyPlayer2(View view){
        Button startB = (Button) this.findViewById(R.id.select_ships);
        Button readyB = (Button) this.findViewById(R.id.ready_2);
        if(!readyPlayer2) {
            //change button to say ready
            readyPlayer2 = true;
            readyB.setText("Ready");
        }
        else
        {
            //change button to say not ready
            readyPlayer2 = false;
            readyB.setText("Not Ready");
        }


        if(readyPlayer1&&readyPlayer2){
            startB.setAlpha(1);


        }
        else
            startB.setAlpha(0);



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
