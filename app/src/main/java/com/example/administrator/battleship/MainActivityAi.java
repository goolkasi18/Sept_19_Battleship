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


public class MainActivityAi extends ActionBarActivity {


    //Global 2 dimensional array
    private Player p1;
    private AI p2;
    Player[] players = new Player[2];
    int activePlayer = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_ai);

        p1 = (Player)getIntent().getSerializableExtra("Player1");
        p2 = (AI)getIntent().getSerializableExtra("AI2");
        players[0] = p1;
        players[1] = p2;

       // ImageView background = (ImageView)findViewById(R.id.Background);
        //background.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.board2, 1000, 600));
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
        Point attackPoint = p2.randPoint();

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
