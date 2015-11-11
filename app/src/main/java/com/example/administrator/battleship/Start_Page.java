package com.example.administrator.battleship;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class Start_Page extends ActionBarActivity {

    int muteToggle, vibroToggle = 0;
    Button helpBtn, settingsBtn, vsAIBtn, vsPlayerBtn, muteBtn, vibroBtn;
    LinearLayout vsLayout, helperBtns;
    FrameLayout helpFrame, settingsFrame;
    Vibrator vibrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start__page);
        helpBtn = (Button) findViewById(R.id.helpBtn);
        settingsBtn = (Button) findViewById(R.id.settingsButton);
        vsAIBtn = (Button) findViewById(R.id.vsAIButton);
        vsPlayerBtn = (Button) findViewById(R.id.vsPlayerButton);
        muteBtn = (Button) findViewById(R.id.muteButton);
        vibroBtn = (Button) findViewById(R.id.vibroButton);
        vsLayout = (LinearLayout) findViewById(R.id.vsLayout);
        helperBtns = (LinearLayout) findViewById(R.id.helperButtons);
        helpFrame = (FrameLayout) findViewById(R.id.helpFrame);
        settingsFrame = (FrameLayout) findViewById(R.id.settingsFrame);
        vibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        ImageView background = (ImageView)findViewById(R.id.Background);
        background.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.title2, 100, 100));
    }

    public void switchToSelectShips(View view) {
        Intent switchToCustom = new Intent(this, select_configs_TwoPlayer.class);
        startActivity(switchToCustom);
    }

    public void switchToAIConfig(View view){
        Intent switchToAIConfig = new Intent(this, AI_Player_configs.class);
        startActivity(switchToAIConfig);
    }

    public void showHelp(View view) {
        vibrate.vibrate(2000);
        helpBtn.setEnabled(false);
        vsAIBtn.setEnabled(false);
        vsPlayerBtn.setEnabled(false);
        settingsBtn.setEnabled(false);
        helpFrame.setVisibility(View.VISIBLE);
    }

    public void closeHelp(View view) {
        helpBtn.setEnabled(true);
        vsAIBtn.setEnabled(true);
        vsPlayerBtn.setEnabled(true);
        settingsBtn.setEnabled(true);
        helpFrame.setVisibility(View.GONE);
    }

    public void showSettings(View view) {
        helpBtn.setEnabled(false);
        vsAIBtn.setEnabled(false);
        vsPlayerBtn.setEnabled(false);
        settingsBtn.setEnabled(false);
        settingsFrame.setVisibility(View.VISIBLE);
    }

    public void closeSettings(View view) {
        helpBtn.setEnabled(true);
        vsAIBtn.setEnabled(true);
        vsPlayerBtn.setEnabled(true);
        settingsBtn.setEnabled(true);
        settingsFrame.setVisibility(View.GONE);
    }

    public void toggleVolume(View view)
    {
        if(muteToggle == 0)
        {
            muteBtn.setBackgroundResource(R.drawable.sound_offbg);
            muteToggle = 1;
            //mute sounds
        }
        else
        {
            muteBtn.setBackgroundResource(R.drawable.sound_onbg);
            muteToggle = 0;
            //turn sounds back on
        }
    }

    public void toggleVibro(View view)
    {
        if(vibroToggle == 0)
        {
            vibroBtn.setBackgroundResource(R.drawable.vibro_offbg);
            vibroToggle = 1;
            //mute vibration
        }
        else
        {
            vibroBtn.setBackgroundResource(R.drawable.vibro_onbg);
            vibroToggle = 0;
            //turn vibration back on
        }
    }

    public void changeTheme(View view)
    {

        /*
        if (i == 0)
        {
            i = 1;
            RelativeLayout background = (RelativeLayout)findViewById(R.id.mainBackground);
            background.setBackgroundResource(R.drawable.title);
            Button button = (Button)findViewById(R.id.settingsBtn);
            button.setBackgroundResource(R.drawable.blank_binder);
        }
        else
        {
            i=0;
            //Button tiny = (Button)findViewById(R.id.mainBackground); THIS WORKS FOR BUTTONS
            RelativeLayout tiny = (RelativeLayout)findViewById(R.id.mainBackground);
            tiny.setBackgroundResource(R.drawable.title2);
            Button button = (Button)findViewById(R.id.settingsBtn);
            button.setBackgroundResource(R.drawable.blank_metal);
        }
        */
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
