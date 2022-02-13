package com.codepath.debuggingchallenges.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.TextView;


import com.codepath.debuggingchallenges.R;

public class ToolbarActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        // Find the toolbar view inside the activity layout
        toolbar = findViewById(R.id.toolbar);

        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

        tvDescription = findViewById(R.id.tvDescription);
        tvDescription.setText("Toolbar");
    }
}
