package com.example.administrator.battleship;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Start_Page extends ActionBarActivity {

    int i = 0; //used to switch background for a test

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start__page);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start__page, menu);
        return true;
    }

    public void switchToSelectShips(View view)
    {

        Intent switchToSelect = new Intent(this, Select_Ship_Positions.class);
        startActivity(switchToSelect);
        finish();

    }

    public void changeTheme(View view)
    {

        if (i == 0)
        {
            i = 1;
            Button tiny = (Button)findViewById(R.id.settingsBtn);
            tiny.setBackgroundResource(R.drawable.transparent);
        }
        else
        {
            i=0;
            Button tiny = (Button)findViewById(R.id.settingsBtn);
            tiny.setBackgroundResource(R.drawable.vibro_on);
        }


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
