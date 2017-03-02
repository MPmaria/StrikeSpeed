package com.mp.user.strikespeed;

import android.content.Intent;
import android.hardware.SensorEventListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private static final String ACCELERATION = "acceleration";
    private static final String REACTION_TIME = "reaction_time";
    private static final String SPEED = "speed";
    private static final String STRIKE_TYPE = "strike_type";
    private static final String HAND = "hand";
    private static final String GLOVE = "glove";
    private static final String STEP = "step";

    private TextView strikeSpeedResult;
    private TextView reactionResult;
    private TextView accelerationResult;

    private TextView strikeTypeResult, handResult, gloveResult, stepResult;

    private Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        strikeSpeedResult = (TextView)findViewById(R.id.strike_speed_result);
        reactionResult = (TextView)findViewById(R.id.reaction_result);
        accelerationResult = (TextView)findViewById(R.id.acceleration_result);

        strikeTypeResult = (TextView) findViewById(R.id.strike_type_result);
        handResult = (TextView) findViewById(R.id.hand_result);
        gloveResult = (TextView) findViewById(R.id.glove_result);
        stepResult = (TextView) findViewById(R.id.step_result);

        accelerationResult.setText(getIntent().getStringExtra(ACCELERATION));
        reactionResult.setText(getIntent().getStringExtra(REACTION_TIME));
        strikeSpeedResult.setText(getIntent().getStringExtra(SPEED));

        strikeTypeResult.setText(getIntent().getStringExtra(STRIKE_TYPE));
        handResult.setText(getIntent().getStringExtra(HAND));
        gloveResult.setText(getIntent().getStringExtra(GLOVE));
        stepResult.setText(getIntent().getStringExtra(STEP));

        okButton = (Button)findViewById(R.id.ok_button);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}

