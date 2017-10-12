package com.rramos.mypersonalapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rramos.mypersonalapp.R;
import com.rramos.mypersonalapp.models.User;
import com.rramos.mypersonalapp.repositories.UserRepository;

import org.polaric.colorful.Colorful;

public class DashboardActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private static final String TAG = DashboardActivity.class.getSimpleName();
    // SharedPreferences
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Colorful.init(this);
        // Setear Toolbar como action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Set DrawerLayout
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, android.R.string.ok, android.R.string.cancel);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Set NavigationItemSelectedListener
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_inicio:
                        Toast.makeText(DashboardActivity.this, "Go home...", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_mis_datos:
                        Toast.makeText(DashboardActivity.this, "Go calendar...", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_configuracion:
                        Intent intent=new Intent(DashboardActivity.this,MyPreferencesActivity.class);
                        startActivity(intent);

                        //Toast.makeText(DashboardActivity.this, "Go gallery...", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_logout:
                        Toast.makeText(DashboardActivity.this, "Cerrando sesión", Toast.LENGTH_SHORT).show();
                        // remove from SharedPreferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        boolean success = editor.putBoolean("islogged", false).commit();
                        //        boolean success = editor.clear().commit(); // not recommended
                        finish();
                        break;

                }

                // Close drawer
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;

            }
        });



        // init SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // get username from SharedPreferences
        String username = sharedPreferences.getString("username", null);
        String fullname = sharedPreferences.getString("fullname", null);
        Log.d(TAG, "username: " + username);

//        User user = UserRepository.getUser(username);



        // Change navigation header information
        ImageView photoImage = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.menu_photo);
        photoImage.setBackgroundResource(R.drawable.ic_profile);

        TextView fullnameText = (TextView) navigationView.getHeaderView(0).findViewById(R.id.menu_fullname);
        fullnameText.setText(fullname);

        TextView emailText = (TextView) navigationView.getHeaderView(0).findViewById(R.id.menu_email);
        emailText.setText(username);




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: // Option open drawer
                if(!drawerLayout.isDrawerOpen(GravityCompat.START))
                    drawerLayout.openDrawer(GravityCompat.START);   // Open drawer
                else
                    drawerLayout.closeDrawer(GravityCompat.START);    // Close drawer
                return true;
        }
        return super.onOptionsItemSelected(item);

    }



}
