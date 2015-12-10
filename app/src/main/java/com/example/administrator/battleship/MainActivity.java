package com.example.administrator.battleship;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/*
* @authors: Jared, Daniel, Will
* @version: November 10th, 2015
* This class represents the Local Game and Main Activity of our Game.
* Class handles the following:
* --Checking hits in game
* --Resizing images to lower resolutions
* --Changes the turn upon one player attacking
 */
public class MainActivity extends ActionBarActivity {

    //the Players to play the game, passed up from configs activity
    private Player p1;
    private Player p2;
    private AI a1;
    public SoundPool soundPool;
    public int explosion, sunk, miss;
    //to be implemented
    private Point guessAI;
    private GridLayout aiBoard;
    private ImageView[] player1Remaining = new ImageView[5];
    private ImageView[] player2Remaining = new ImageView[5];
    private int[] player1Down = new int[5];
    private int[] player2Down = new int[5];
    private ImageView turnGui;

    //an array to determine the active player
    private Player[] players = new Player[2];
    private int activePlayer = 1;
    private boolean isAI;

    Vibrator vibrate;

    private Thread hit;
    private Thread sink;
    private Runnable endTurn;
    private Runnable endGame;
    private Handler wait;
    private Boolean locked;

    private RelativeLayout p1Layout;
    private RelativeLayout p2Layout;

    private Button p1_pre;
    private Button p2_pre;

    /*
    * method: onCreate
    * purpose: starts the activity
    * @param: savedInstanceState - a Bundle of info about the activity that will be started
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        p1 = (Player)getIntent().getSerializableExtra("Player1");
        isAI = (Boolean)getIntent().getExtras().getBoolean("isAI");
        vibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        explosion = soundPool.load(this, R.raw.explosion, 1);
        sunk = soundPool.load(this, R.raw.sunk, 1);
        miss = soundPool.load(this, R.raw.splashsoundeffect, 1);

        players[0] = p1;
        //there might not be a difference with what is below
        if(isAI) {
            a1 = (AI) getIntent().getSerializableExtra("Player2");
            a1.copyBoard(p1.squares);
            aiBoard = (GridLayout) findViewById(R.id.right_button_grid);
            players[1] = a1;
        }
        else {
            p2 = (Player) getIntent().getSerializableExtra("Player2");
            players[1] = p2;
        }

        p1Layout = (RelativeLayout) this.findViewById(R.id.P1_Pre);
        p2Layout = (RelativeLayout) this.findViewById(R.id.P2_Pre);

        p1_pre = (Button) this.findViewById(R.id.p1);
        p2_pre = (Button) this.findViewById(R.id.p2);
        p1_pre.setText(p1.getPlayerName() + " Ready?");
        if(!isAI) {
            Log.i("Message", "Message");
            p2_pre.setText(p2.getPlayerName() + " Ready?");
            p2Layout.setBackgroundColor(p2.colorChoiceID);
        }

        p1Layout.setBackgroundColor(p1.colorChoiceID);


        if(isAI)
            p2Layout.setVisibility(View.GONE);
        player1Remaining[0] = (ImageView)findViewById(R.id.RShip1);
        player1Remaining[1] = (ImageView)findViewById(R.id.RShip2);
        player1Remaining[2] = (ImageView)findViewById(R.id.RShip3);
        player1Remaining[3] = (ImageView)findViewById(R.id.RShip4);
        player1Remaining[4] = (ImageView)findViewById(R.id.RShip5);
        player1Down[0] = R.drawable.r_vertical2_down;
        player1Down[1] = R.drawable.r_vertical3_down;
        player1Down[2] = R.drawable.r_vertical3_down;
        player1Down[3] = R.drawable.r_vertical4_down;
        player1Down[4] = R.drawable.r_vertical5_down;

        player2Remaining[0] = (ImageView)findViewById(R.id.LShip1);
        player2Remaining[1] = (ImageView)findViewById(R.id.LShip2);
        player2Remaining[2] = (ImageView)findViewById(R.id.LShip3);
        player2Remaining[3] = (ImageView)findViewById(R.id.LShip4);
        player2Remaining[4] = (ImageView)findViewById(R.id.LShip5);
        player2Down[0] = R.drawable.l_vertical2_down;
        player2Down[1] = R.drawable.l_vertical3_down;
        player2Down[2] = R.drawable.l_vertical3_down;
        player2Down[3] = R.drawable.l_vertical4_down;
        player2Down[4] = R.drawable.l_vertical5_down;

        ((ImageView)findViewById(R.id.Player1ProfilePic)).setBackgroundResource(players[0].getProfilePicID());
        ((ImageView)findViewById(R.id.Player2ProfilePic)).setBackgroundResource(players[1].getProfilePicID());

        turnGui = (ImageView)findViewById(R.id.PlayerTurn);

        //ImageView background = (ImageView)findViewById(R.id.Background);
        // background.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.board2, 1000, 600));

        hit = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if(Start_Page.muteToggle == 0)
                        soundPool.play(explosion, 1f, 1f, 1, 0, 2.0f);
                    if(Start_Page.vibroToggle == 0)
                        vibrate.vibrate(250);

                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
            }
        });

        sink = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (Start_Page.muteToggle == 0)
                        soundPool.play(sunk, 1f, 1f, 1, 0, 2.0f);

                    if (Start_Page.vibroToggle == 0) {
                        vibrate.vibrate(250);
                        sink.sleep(500);
                        vibrate.vibrate(250);
                    }

                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
            }
        });

        endTurn = new Runnable() {
            @Override
            public void run(){
                endTurn();
                if(isAI && activePlayer == 0)
                    AITurn();
            }
        };
        endGame = new Runnable() {
            @Override
            public void run(){
                goToWin();
            }
        };
        wait = new Handler();
        locked = false;

        ((TextView)findViewById(R.id.RightPlayerName)).setText("Your Enemy: " + players[0].getPlayerName());
        ((TextView)findViewById(R.id.LeftPlayerName)).setText("Your Enemy: " + players[1].getPlayerName());
    }

    public void startGame(View view)
    {
        if(view instanceof RelativeLayout)
        {
            view.setVisibility(View.GONE);
        }

        else {
            RelativeLayout lay = (RelativeLayout) view.getParent();
            lay.setVisibility(View.GONE);
        }

    }

    /*
    *method: exitToStart
    * Purpose: switches intents to the main activity
    * @param: view - the Button that was pressed
     */
    public void exitToStart(View view)
    {
        final Intent main = new Intent(this, Start_Page.class);
        AlertDialog.Builder deletePrompt = new AlertDialog.Builder(this);
        deletePrompt.setMessage("Are you sure you want to exit the game?");
        deletePrompt.setCancelable(false);
        deletePrompt.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
                startActivity(main);
                finish();

            }
        });
        deletePrompt.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                dialog.cancel();
            }
        });
        AlertDialog alert = deletePrompt.create();
        alert.show();

    }

    /*
    * Method: checkHit
    * Purpose: Checks the hit upon selection of a square on the grid
    * @param: view - the button that was pressed
     */
    public void checkHit(View view)
    {


        if(((view.getParent() == findViewById(R.id.right_button_grid) && activePlayer == 0)||(view.getParent() == findViewById(R.id.left_button_grid) && activePlayer == 1))&&(locked == false)) {
            locked = true;
            view.setEnabled(false);
            int Rcol = view.getLeft() / 80;
            int Rrow = view.getTop() / 80;
            int row,col;
            if(activePlayer == 0)
            {
                 col = 9-Rrow;
                 row = Rcol;
            }
            else
            {
                 col = Rrow;
                 row = 9-Rcol;
            }

            if(players[activePlayer].attack(row,col)) {

                hit.run();

                if(activePlayer == 0)
                {
                    view.setBackgroundResource(R.drawable.right_hit_animation);
                    ((AnimationDrawable)view.getBackground()).start();
                }

                else
                {
                    view.setBackgroundResource(R.drawable.left_hit_animation);
                    ((AnimationDrawable)view.getBackground()).start();
                }

                if (players[activePlayer].checkSink(players[activePlayer].ships[players[activePlayer].squares[row][col]-1])) {
                    sink.run();
                    int index = players[activePlayer].squares[row][col]-1;
                    if(index > 4)
                        index = index-5;
                    if(activePlayer == 0)
                        player1Remaining[index].setBackgroundResource(player1Down[index]);
                    else
                        player2Remaining[index].setBackgroundResource(player2Down[index]);
                    //draw the new ship on the screen as sunk
                }
                if (players[activePlayer].checkWin())
                {
                    if(activePlayer == 0)
                        activePlayer = 1;
                    else
                        activePlayer = 0;
                    wait.postDelayed(endGame, 1000);
                }
            }
            else
            {
                if(activePlayer == 0) {
                    if(Start_Page.muteToggle == 0)
                        soundPool.play(miss, 1f, 1f, 1, 0, 1.5f);

                    view.setBackgroundResource(R.drawable.right_miss_animation);
                    ((AnimationDrawable)view.getBackground()).start();
                }
                else {
                    if(Start_Page.muteToggle == 0)
                        soundPool.play(miss, 1f, 1f, 1, 0, 1.5f);
                    view.setBackgroundResource(R.drawable.left_miss_animation);
                    ((AnimationDrawable)view.getBackground()).start();
                }
            }
            wait.postDelayed(endTurn, 1000);
        }
    }

    /*
    * method: endTurn
    * purpose: switches the turn of the player
    * @return: void
     */
    public void endTurn(){
        if(activePlayer == 0) {
            activePlayer = 1;
            turnGui.setBackgroundResource(R.drawable.fade2);
        }
        else {
            activePlayer = 0;
            turnGui.setBackgroundResource(R.drawable.fade1);
        }
        locked = false;
    }

    public void AITurn(){
        locked = true;
        guessAI = a1.AIAttack();

        int row = guessAI.x;
        int col = guessAI.y;
        int Rcol = row;
        int Rrow = 9-col;

        //this might also work with the children going from 0 to 99 so we use math to find the spot
        ImageButton testing2 = (ImageButton) aiBoard.getChildAt(Rrow*10 + Rcol);

        //needs to impliment below
        if(players[activePlayer].attack(row,col)) {
            hit.run();

            testing2.setBackgroundResource(R.drawable.right_hit_animation);
            ((AnimationDrawable)testing2.getBackground()).start();

            if (players[activePlayer].checkSink(players[activePlayer].ships[players[activePlayer].squares[row][col]-1])) {

                sink.run();
                a1.forget();
                int index = players[activePlayer].squares[row][col]-1;
                if(index > 4)
                    index = index-5;
                player1Remaining[index].setBackgroundResource(player1Down[index]);
                //draw the new ship on the screen as sunk
            }
            if (players[activePlayer].checkWin())
            {
                //do whatever we want to end game and show win screen
                if (activePlayer == 0)
                    activePlayer = 1;
                else
                    activePlayer = 0;
                wait.postDelayed(endGame, 1000);
                 //this is a pace holder to just pop to main screen upon winning to show that it recognizes it
            }
        }
        else
        {
            if(Start_Page.muteToggle == 0)
                soundPool.play(miss, 1f, 1f, 1, 0, 1.5f);
            testing2.setBackgroundResource(R.drawable.right_miss_animation);
            ((AnimationDrawable)testing2.getBackground()).start();
        }
        wait.postDelayed(endTurn, 1000);
    }

    public void goToWin(){

        Intent win = new Intent(this, WinScreen.class);
        win.putExtra("Winner", players[activePlayer].getPlayerName());
        win.putExtra("Player1", players[0]);
        if(isAI)
        {
            a1.unInitialize();
            win.putExtra("Player2", a1);
        }
        else
            win.putExtra("Player2", p2);
        win.putExtra("isAI", isAI);
        startActivity(win);
        finish();
    }


    /*
    *Method: calculateInSampleSize
    *purpose: changes the size of an image so our game doesnt run out of memory,
    *         essentially lowers the resolution of images
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
