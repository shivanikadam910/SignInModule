package com.android.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import Adapter.ViewPagerAdapter;

public class TabLayoutFragment extends AppCompatActivity {

    // creating object of ViewPager
    ViewPager mViewPager;

    // images array
    int[] images = {R.drawable.a1, R.drawable.a2, R.drawable.a3};

    // Creating Object of ViewPagerAdapter
    ViewPagerAdapter mViewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_fragment);

        // Initializing the ViewPager Object
        mViewPager = (ViewPager) findViewById(R.id.viewPagerMain);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        // Initializing the ViewPagerAdapter
        mViewPagerAdapter = new ViewPagerAdapter(this, images);
        // Adding the Adapter to the ViewPager
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
