package com.example.administrator.battleship;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class select_configs_TwoPlayer extends ActionBarActivity {

    //Both players are human, need to declare two Player objects upon start of this class
    public Player p1,p2;

    //Both players are defaulted to not ready, because they both need to choose configurations
    public boolean readyPlayer1 = false;
    public boolean readyPlayer2 = false;

    //Text fields that hold the players name
    public EditText p1Name;
    public EditText p2Name;
    /*
    *start the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_configs__two_player);
        Button startB = (Button) this.findViewById(R.id.select_ships);
        startB.setVisibility(View.GONE);
        p1 = new Player();
        p2 = new AI();

        p1Name = (EditText) this.findViewById(R.id.Player1_Name);
        p2Name = (EditText) this.findViewById(R.id.Player2_Name);

        //ImageView background = (ImageView)findViewById(R.id.Background);
        //background.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.title2, 1000, 600));
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


    public void readyPlayer2(View view){
        Button startB = (Button) this.findViewById(R.id.select_ships);
        Button readyB = (Button) this.findViewById(R.id.ready_2);
        if(!readyPlayer2) {//change button to say ready
            readyPlayer2 = true;
            readyB.setText("Ready");
        }
        else //change button to say not ready
        {
            readyPlayer2 = false;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_configs__two_player, menu);
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

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}
