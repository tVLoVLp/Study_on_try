package com.example.fragments;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    private final int TIMER_OF_PN = 3600000;
    private final int TI_VIBRATE = 1000;

    private Timer mTimer = new Timer();
    private Boolean isPause = false;

    Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomnav = findViewById(R.id.navigation);
        bottomnav.setOnNavigationItemSelectedListener(navListener);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Study on");
       // toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        else {
            handler.removeCallbacks(runnable);
        }
        //


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()) {
                        case R.id.action_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.action_delightful:
                            selectedFragment = new CameraFragment();
                            break;
                        case R.id.action_share:
                            selectedFragment = new ShareFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menumain, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        // Handle item selection
        if (id == R.id.itemSettings) {

            Intent intent = new Intent(MainActivity.this, MainSettingsActivity.class);
            startActivity(intent);
            return false;
        }

        if (id == R.id.itemInfo) {

            Intent intent = new Intent(MainActivity.this, MainInfoActivity.class);
            startActivity(intent);
            return false;
        }

        return super.onOptionsItemSelected(item);
    }
    private void addNotification() {

        Uri sound = Uri.parse("android.resource://" + getPackageName() + "/sounds");

        Intent resultIntent = new Intent(this, MainActivity.class);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);


        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher, options);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(getString(R.string.title_program))
                        .setContentText(getString(R.string.notification_text))
                        .setLargeIcon(bitmap)
                        .setAutoCancel(true)
                        .setWhen(System.currentTimeMillis())
                        .setDefaults(Notification.DEFAULT_SOUND |
                                Notification.DEFAULT_VIBRATE)
                        .setSound(sound)
                        .setVibrate(new long[]{TI_VIBRATE, TI_VIBRATE, TI_VIBRATE, TI_VIBRATE, TI_VIBRATE})
                        .setLights(Color.RED, 0, 1)
                        .setContentIntent(resultPendingIntent);


        Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isPause = true;

        handler.postDelayed(runnable, TIMER_OF_PN);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPause = true;
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onStart() {
        super.onStart();
        isPause = false;
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (isPause) {
                addNotification();
            }
        }
    };
}
