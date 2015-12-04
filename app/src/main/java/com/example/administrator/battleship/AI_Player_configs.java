package com.example.administrator.battleship;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;


public class AI_Player_configs extends ActionBarActivity {

    //Both players are human, need to declare two Player objects upon start of this class
    public Player p1;
    public AI p2;

    //Player 2 is a robot, and therefore is always ready
    public boolean readyPlayer1 = false;
    private int[] defaultPics = new int[3]; //change size to how many pics there are
    private int[] chosenPics = new int[3]; //change size to how many pics there are
    private int[] p1ProfilePics = new int[3]; //change size to how many pics there are
    private int[] p2ProfilePics = new int[3]; //change size to how many pics there are

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
        p2 = new AI();

        p1Name = (EditText) this.findViewById(R.id.Player1_Name);
        p2Name = (EditText) this.findViewById(R.id.Player2_Name);


        defaultPics[0] = R.drawable.sailor1;
        defaultPics[1] = R.drawable.sailor2;
        defaultPics[2] = R.drawable.sailor3;

        p1ProfilePics[0] = R.drawable.r_sailor1;
        p1ProfilePics[1] = R.drawable.r_sailor2;
        p1ProfilePics[2] = R.drawable.r_sailor3;

        p2ProfilePics[0] = R.drawable.l_sailor1;
        p2ProfilePics[1] = R.drawable.l_sailor2;
        p2ProfilePics[2] = R.drawable.l_sailor3;


        chosenPics[0] = R.drawable.sailor1_yes;
        chosenPics[1] = R.drawable.sailor2_yes;
        chosenPics[2] = R.drawable.sailor3_yes;
        //ImageView background = (ImageView)findViewById(R.id.Background);
        //background.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.title2, 1000, 600));

        Spinner colorSpinnerP1 = (Spinner) findViewById(R.id.ColorSpinner);
        Spinner colorSpinnerP2 = (Spinner) findViewById(R.id.Color2_spinner);

        ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource(this,R.array.colors_array , R.layout.support_simple_spinner_dropdown_item);
        // Specify the layout to use when the list of choices appears
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        colorSpinnerP1.setAdapter(colorAdapter);
        colorSpinnerP2.setAdapter(colorAdapter);

        colorSpinnerP1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).toString().equals("Blue")) {
                    p1.setColorChoiceID(Color.BLUE);
                }
                if (parent.getItemAtPosition(position).toString().equals("Red")) {
                    p1.setColorChoiceID(Color.RED);
                }
                if (parent.getItemAtPosition(position).toString().equals("Green")) {
                    p1.setColorChoiceID(Color.GREEN);
                }
                if (parent.getItemAtPosition(position).toString().equals("White")) {
                    p1.setColorChoiceID(Color.WHITE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        colorSpinnerP2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).toString().equals("Blue")) {
                    p2.setColorChoiceID(Color.BLUE);
                }
                if (parent.getItemAtPosition(position).toString().equals("Red")) {
                    p2.setColorChoiceID(Color.RED);
                }
                if (parent.getItemAtPosition(position).toString().equals("Green")) {
                    p2.setColorChoiceID(Color.GREEN);
                }
                if (parent.getItemAtPosition(position).toString().equals("White")) {
                    p2.setColorChoiceID(Color.WHITE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setProfile1Pic(View view){
        LinearLayout p1Pics = (LinearLayout) findViewById(R.id.Player1_Images);
        int p1Size = p1Pics.getChildCount();

        for(int i = 0; i<p1Size; i++)
            if(view != p1Pics.getChildAt(i))
                p1Pics.getChildAt(i).setBackgroundResource(defaultPics[i]);
            else {
                p1Pics.getChildAt(i).setBackgroundResource(chosenPics[i]);
                p1.setProfilePicID(p1ProfilePics[i]);
            }
    }

    public void setProfile2Pic(View view){
        LinearLayout p2Pics = (LinearLayout) findViewById(R.id.Player2_Images);
        int p2Size = p2Pics.getChildCount();

        for(int i = 0; i<p2Size; i++)
            if(view != p2Pics.getChildAt(i))
                p2Pics.getChildAt(i).setBackgroundResource(defaultPics[i]);
            else {
                p2Pics.getChildAt(i).setBackgroundResource(chosenPics[i]);
                p2.setProfilePicID(p2ProfilePics[i]);
            }
    }

    /**
     * Once the players are done selecting configurations they will be able to move onto selecting ships
     *
     * @param view
     */
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

        if(readyPlayer1)
            startB.setVisibility(View.VISIBLE);
        else
            startB.setVisibility(View.GONE);
    }

    public void startGame(View view){
        p1.setPlayerName(p1Name.getText().toString());
        p2.setPlayerName(p2Name.getText().toString());
        Intent switchToSelect = new Intent(this, select_ship_positions_ai.class);
        switchToSelect.putExtra("Player1", p1);
        switchToSelect.putExtra("AI2", p2);
        startActivity(switchToSelect);
        finish();
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
        getMenuInflater().inflate(R.menu.menu_select_ship_positions_ai, menu);
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
