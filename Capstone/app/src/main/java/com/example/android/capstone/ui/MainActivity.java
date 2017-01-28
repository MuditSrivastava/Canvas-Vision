package com.example.android.capstone.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.android.capstone.ui.adapter.PagerAdapter;
import com.example.android.capstone.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView myTitle = (TextView) toolbar.getChildAt(0);
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/Satisfy-Regular.ttf");
        myTitle.setTypeface(tf,Typeface.BOLD);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        AdView mAdView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("CADD03C7EC8C7AD4D6073860CC9B3BF7")
                .build();
        mAdView.loadAd(adRequest);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_latest) {
            Intent intent = new Intent(this, NavActivity.class);
            intent.putExtra(NavActivity.Extra_id, "latest");
            this.startActivity(intent);

        } else if (id == R.id.nav_editor) {
            Intent intent = new Intent(this, NavActivity.class);
            intent.putExtra(NavActivity.Extra_id, "editors_choice");
            this.startActivity(intent);

        }  else if (id == R.id.nav_collection) {
            Intent intent = new Intent(this, CollectionActivity.class);
            this.startActivity(intent);

        } else if (id == R.id.nav_about) {
            Intent intent= new Intent(this,AboutActivity.class);
            this.startActivity(intent);

        } else if (id == R.id.nav_license) {
            Intent intent= new Intent(this,LicenseActivity.class);
            this.startActivity(intent);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
