package com.mp.user.strikespeed;

import android.preference.PreferenceActivity;
import android.os.Bundle;

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingFragment())
                .commit();
    }
}
