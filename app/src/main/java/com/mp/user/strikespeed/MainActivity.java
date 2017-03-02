package com.mp.user.strikespeed;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mp.user.strikespeed.database.StrikeBaseHelper;
import com.mp.user.strikespeed.entity.Strike;
import com.mp.user.strikespeed.logics.Calculator;

import com.mp.user.strikespeed.database.DbSchema;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String ACCELERATION = "acceleration";
    private static final String REACTION_TIME = "reaction_time";
    private static final String SPEED = "speed";

    private static final String STRIKE_TYPE = "strike_type";
    private static final String HAND = "hand";
    private static final String GLOVE = "glove";
    private static final String STEP = "step";

    public static long WAITING_TIME = 2500;
    private Calculator speedCalculator;

    private ImageButton strikeButton;
    private Button settingButton;
    private Button historyButton;

    private TextView strikeType, hand, hasGlove, hasStep;

    private SQLiteDatabase database;
    private Strike strike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new StrikeBaseHelper(this).getWritableDatabase();

        settingButton = (Button) findViewById(R.id.settings_button);
        historyButton = (Button) findViewById(R.id.history_button);
        strikeButton = (ImageButton) findViewById(R.id.strike_button);

        strikeType = (TextView) findViewById(R.id.strike_type);
        hand = (TextView) findViewById(R.id.hand);
        hasGlove = (TextView) findViewById(R.id.has_glove);
        hasStep = (TextView) findViewById(R.id.step);

        strikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speedCalculator = new Calculator(getApplicationContext());
                new CountDownTimer(WAITING_TIME, WAITING_TIME) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                        intent.putExtra(ACCELERATION,String.format("%.3f", speedCalculator.getMaxAcceleration()));
                        intent.putExtra(REACTION_TIME,String.format("%.3f",speedCalculator.getReactionTime()/1000));
                        intent.putExtra(SPEED,String.format("%.3f",speedCalculator.getSpeed()));
                        intent.putExtra(STRIKE_TYPE, strikeType.getText());
                        intent.putExtra(HAND, hand.getText());
                        intent.putExtra(GLOVE, hasGlove.getText());
                        intent.putExtra(STEP, hasStep.getText());
                        startActivity(intent);
                    }
                }.start();
            }
        });


        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                startActivity(intent);
            }
        });

        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prefs.registerOnSharedPreferenceChangeListener(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_show_license:
                Intent intent = new Intent(MainActivity.this, LicenseActivity.class);
                startActivity(intent);
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if(key.equals("hand_pref"))
        {
            hand.setText(sharedPreferences.getString("hand_pref","@string/right_hand"));
        }

        if(key.equals("glove_pref"))
        {
            boolean glove =sharedPreferences.getBoolean("glove_pref",false);
            if(glove == true){
                hasGlove.setText(R.string.has);
            } else {
                hasGlove.setText(R.string.no);
            }
        }

        if(key.equals("step_pref"))
        {
            boolean step =sharedPreferences.getBoolean("step_pref",false);
            if(step == true){
                hasStep.setText(R.string.has_step);
            } else {
                hasStep.setText(R.string.no_step);
            }
        }

        if(key.equals("strike_type_pref"))
        {
            strikeType.setText(sharedPreferences.getString("strike_type_pref","@string/uppercut"));
        }
    }

    private static ContentValues getContentValues(Strike strike){
        ContentValues values = new ContentValues();
        values.put(DbSchema.StrikeTable.Cols.ACCELERATION, strike.getAcceleration());
        values.put(DbSchema.StrikeTable.Cols.REACTION_SPEED, strike.getReactionSpeed());
        values.put(DbSchema.StrikeTable.Cols.STRIKE_SPEED, strike.getStrikeSpeed());
        values.put(DbSchema.StrikeTable.Cols.DATE, strike.getDate().toString());
        values.put(DbSchema.StrikeTable.Cols.TYPE, strike.getType());
        values.put(DbSchema.StrikeTable.Cols.IS_RIGHT, strike.isRight());
        values.put(DbSchema.StrikeTable.Cols.HAS_GLOVE, strike.isHasGlove());
        values.put(DbSchema.StrikeTable.Cols.HAS_STEP, strike.isHasStep());

        return values;
    }
}