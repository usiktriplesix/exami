package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTabs();
        selectFragment(0);
    }

    private void initTabs() {
        tabLayout = findViewById(R.id.tabLayout);

        tabLayout.addTab(tabLayout.newTab().setIcon(getDrawable(R.drawable.first_tab)).setText("Анализы"));
        tabLayout.addTab(tabLayout.newTab().setIcon(getDrawable(R.drawable.second_tab)).setText("Результаты"));
        tabLayout.addTab(tabLayout.newTab().setIcon(getDrawable(R.drawable.third_tab)).setText("Поддержка"));
        tabLayout.addTab(tabLayout.newTab().setIcon(getDrawable(R.drawable.fourth_tab)).setText("Пользователь"));

        fragmentManager = getSupportFragmentManager();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

    }

    private void selectFragment(int i) {
        Fragment fragment = null;
        switch (i) {
            case 0:
                fragment = new FirstFragment();
                break;
            case 1:
            case 2:
            case 3:
                fragment = new EmptyFragment();
                break;
        }
        setFragment(fragment);
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment).commit();
    }
}