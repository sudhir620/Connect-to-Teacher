package com.skcoder.ctcenter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.skcoder.ctcenter.ui.ebooks.Ebooks;
import com.skcoder.ctcenter.ui.notification.Notification;
import com.skcoder.ctcenter.ui.youtubeLecture.YoutubeLacture;

import static com.skcoder.ctcenter.R.id.notification;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.setTitle("C2T");

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(this, R.id.frame_layout);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        navigationView = (NavigationView)findViewById(R.id.navigation_view);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.start, R.string.close);

        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notification_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case notification:
                Intent intent = new Intent(getApplicationContext(), Notification.class);
                startActivity(intent);
                break;
            default:
        }

        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navigation_video:
                //Toast.makeText(getApplicationContext(),"YouTube Video News", Toast.LENGTH_SHORT).show();
                Uri uri = Uri.parse("https://www.youtube.com/c/CTNewsJalandhar/videos");
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
                break;
            case R.id.ebooks:
                startActivity(new Intent(getApplicationContext(), Ebooks.class));
                break;
            case R.id.youtubeLecture:
                startActivity(new Intent(getApplicationContext(), YoutubeLacture.class));
                break;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
                break;
            default:
        }
        return true;
    }

}