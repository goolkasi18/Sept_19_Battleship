package com.example.administrator.battleship;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

/*
* @authors: Jared, Daniel, Will
* @version: November 10th, 2015
*
* This class represents the Local Game and Main Activity of our Game.
*
* Class handles the following:
*
* --Checking hits in game
*
* --Resizing images to lower resolutions
*
* --Changes the turn upon one player attacking
*
*
 */

public class MainActivity extends ActionBarActivity {


    //the Players to play the game, passed up from configs activity
    private Player p1;
    private Player p2;

    //to be implemented
    private AI AIPlayer;

    //an array to determine the active player
    private Player[] players = new Player[2];
    private int activePlayer = 1;
    private boolean isAI;

    /*
    * method: onCreate
    *
    * purpose: starts the activity
    *
    * @param: savedInstanceState - a Bundle of info about the activity that will be started
    *
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p1 = (Player)getIntent().getSerializableExtra("Player1");
        isAI = (Boolean)getIntent().getExtras().getBoolean("isAI");
        //there might not be a difference with what is below
        if(isAI)
            p2 = (AI)getIntent().getSerializableExtra("Player2");
        else
            p2 = (Player)getIntent().getSerializableExtra("Player2");

        players[0] = p1;
        players[1] = p2;

        //ImageView background = (ImageView)findViewById(R.id.Background);
       // background.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.board2, 1000, 600));
    }

    /*
    *method: exitToStart
    *
    * Purpose: switches intents to the main activity
    *
    * @param: view - the Button that was pressed
     */
    public void exitToStart(View view)
    {
        Intent main = new Intent(this, Start_Page.class);
        startActivity(main);
        finish();
    }

    /*
    * Method: checkHit
    *
    * Purpose: Checks the hit upon selection of a square on the grid
    *
    * @param: view - the button that was pressed
     */
    public void checkHit(View view)
    {
        if((view.getParent() == findViewById(R.id.left_button_grid) && activePlayer == 1) || (view.getParent() == findViewById(R.id.right_button_grid) && activePlayer == 0)) {
            view.setEnabled(false);
            int col = view.getLeft() / 80;
            int row = view.getTop() / 80;
            Log.i("Jello0", "Row: " + row + "Col: " + col);

            if(players[activePlayer].attack(row,col)) {
                view.setBackgroundResource(R.drawable.hit);
                if (players[activePlayer].checkSink(players[activePlayer].ships[players[activePlayer].squares[row][col]-1]))
                {
                    Log.i("Sunk:", "ships[" + (players[activePlayer].squares[row][col]-1));
                    //draw the new ship on the screen as sunk
                }
                if (players[activePlayer].checkWin())
                {
                    Log.i("Win:", "Player " + activePlayer);
                    //do whatever we want to end game and show win screen
                }
            }
            else
            {
                view.setBackgroundResource(R.drawable.miss);
            }
            endTurn();
            if(isAI)
                AITurn();
        }
    }

    /*
    *
    * method: endTurn
    *
    * purpose: switches the turn of the player
    *
    * @return: void
     */

    public void endTurn(){
        if(activePlayer == 0)
            activePlayer = 1;
        else
            activePlayer = 0;
    }

    public void AITurn(){

    }


    /*
    *Method: calculateInSampleSize
    *
    *purpose: changes the size of an image so our game doesnt run out of memory,
    *            essentially lowers the resolution of images
    *
    * @return: int
     */
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

    /*
    *   see above comment, helps to change the resolution of images
    *
    *   @param: res
    *   @param: resID
    *   @param: reqWidth
    *   @param: reqHeight
     */
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
