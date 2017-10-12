package com.rramos.mypersonalapp.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ftinc.scoop.Scoop;
import com.rramos.mypersonalapp.R;

import org.polaric.colorful.Colorful;

public class MyPreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
    }

    public static class MyPreferenceFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener
    {
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {


            Log.d("settings", "preference changed: " + s);
            if("fullname".equals(s)){
                Log.d("settings", "new value for username: " + sharedPreferences.getString(s, null));
            }else if("applicationUpdates".equals(s)){
                Log.d("settings", "new value for applicationUpdates: " + sharedPreferences.getBoolean(s, false));
            }else if("downloadType".equals(s)){
                Log.d("settings", "new value for downloadType: " + sharedPreferences.getString(s, null));
                if(sharedPreferences.getString(s, null).equals("1")){
                    Colorful.config(getActivity())
                            .primaryColor(Colorful.ThemeColor.RED)
                            .accentColor(Colorful.ThemeColor.AMBER)
                            .translucent(false)
                            .dark(true)
                            .apply();

                }else if (sharedPreferences.getString(s, null).equals("2")){
                    Colorful.config(getActivity())
                            .primaryColor(Colorful.ThemeColor.GREEN)
                            .accentColor(Colorful.ThemeColor.DEEP_ORANGE)
                            .translucent(false)
                            .dark(true)
                            .apply();
                }else if (sharedPreferences.getString(s, null).equals("3")){
                    Colorful.config(getActivity())
                            .primaryColor(Colorful.ThemeColor.RED)
                            .accentColor(Colorful.ThemeColor.BLUE)
                            .translucent(false)
                            .dark(false)
                            .apply();
                }
            }
        }

        @Override
        public void onResume() {
            super.onResume();
            getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
            super.onPause();
        }

    }
}
