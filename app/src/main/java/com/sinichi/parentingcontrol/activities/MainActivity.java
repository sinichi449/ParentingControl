package com.sinichi.parentingcontrol.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sinichi.parentingcontrol.R;
import com.sinichi.parentingcontrol.adapters.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private static final String PREFS = "sharedprefs";
    private FirebaseUser mFirebaseUser;
    private SharedPreferences mSharedPrefs;
    private SharedPreferences.Editor mEditor;

    private void initComponents() {
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_assessment_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_near_me_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_chat_black_24dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_people_black_24dp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mSharedPrefs = MainActivity.this.getSharedPreferences(PREFS, MODE_PRIVATE);

        initComponents();

        // TODO: Menambahkan dan mencari username sementara yang tersedia
        final String username = mSharedPrefs.getString("username", null);
        if (username == null) {
            View dialogView = getLayoutInflater().inflate(R.layout.custom_alert_username, null);
            final EditText edtUsername = dialogView.findViewById(R.id.edt_makeUsername);
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setView(dialogView)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // TODO: Get usernames
                            if (edtUsername.getText().toString().equals("")) {
                                return;
                            }
                            String username = edtUsername.getText().toString();
                            mEditor = mSharedPrefs.edit();
                            mEditor.putString("username", username);
                            mEditor.apply();
                        }
                    })
                    .setCancelable(false);
            builder.show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mFirebaseUser == null) {
            // TODO: To login page
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }


    }
}