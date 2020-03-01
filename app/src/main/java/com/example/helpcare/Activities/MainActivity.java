package com.example.helpcare.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.helpcare.Fragments.HomeFragment;
import com.example.helpcare.Fragments.ProfileFragment;
import com.example.helpcare.Fragments.TeamFragment;
import com.example.helpcare.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView=findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.bt_home);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame,new HomeFragment())
                .addToBackStack(null)
                .commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment selected=null;

                switch (menuItem.getItemId())
                {
                    case R.id.bt_home:
                        selected=new HomeFragment();
                        break;
                    case R.id.bt_profile:
                        selected=new ProfileFragment();
                        break;
                    case R.id.bt_team:
                        selected=new TeamFragment();
                        break;
                }

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame,selected)
                        .addToBackStack(null)
                        .commit();

                return true;
            }
        });
    }
}
