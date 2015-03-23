package com.jahirfiquitiva.dashboardsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class Home extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(Home.this, com.jahirfiquitiva.paperboard.activities.Main.class);
        startActivity(intent);

    }

}
