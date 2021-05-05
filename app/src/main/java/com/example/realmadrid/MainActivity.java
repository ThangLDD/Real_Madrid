package com.example.realmadrid;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.realmadrid.fragment.FragmentAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    private FragmentAdapter fragmentAdapter;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Home");

        viewPager = findViewById(R.id.viewPager);
        bottomNavigationView = findViewById(R.id.btnNavigation);

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), FragmentAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(fragmentAdapter);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menuHome:
                    viewPager.setCurrentItem(0);
                    actionBar.setTitle("Home");
                    break;
                case R.id.menuAdd:
                    viewPager.setCurrentItem(1);
                    actionBar.setTitle("Add player");
                    break;
                case R.id.menuSearch:
                    viewPager.setCurrentItem(2);
                    actionBar.setTitle("Search player");
                    break;
                case R.id.menuNotifications:
                    viewPager.setCurrentItem(3);
                    actionBar.setTitle("Get notifications");
                    break;
            }
            return true;
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.menuHome).setChecked(true);
                        actionBar.setTitle("Home");
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.menuAdd).setChecked(true);
                        actionBar.setTitle("Add player");
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.menuSearch).setChecked(true);
                        actionBar.setTitle("Search player");
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.menuNotifications).setChecked(true);
                        actionBar.setTitle("Get notifications");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}