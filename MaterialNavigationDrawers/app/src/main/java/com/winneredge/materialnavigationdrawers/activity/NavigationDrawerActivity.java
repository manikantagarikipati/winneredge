package com.winneredge.materialnavigationdrawers.activity;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.winneredge.materialnavigationdrawers.R;
import com.winneredge.materialnavigationdrawers.adapter.HomeViewPagerAdapter;
import com.winneredge.materialnavigationdrawers.adapter.SearchAdapter;
import com.winneredge.materialnavigationdrawers.customwidget.ActionBarDrawerToggle;
import com.winneredge.materialnavigationdrawers.model.SearchResult;

import java.util.ArrayList;
import java.util.List;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    private AutoCompleteTextView searchView;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;
    private TextView appName;
    private boolean isNavigationDrawerOpened;
    private List<SearchResult> searchResults = new ArrayList<>();
    private SearchAdapter searchAdapter;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setElevation(0);
        }

        CoordinatorLayout coordinatorLayout = (CoordinatorLayout)findViewById(R.id.mainLayout);
        coordinatorLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(isNavigationDrawerOpened){
                    hideSearchAndEnableAppName();
                }
                return false;
            }
        });

        searchView = (AutoCompleteTextView) findViewById(R.id.searchView);
        appName = (TextView) findViewById(R.id.appName);
        searchView.setVisibility(View.GONE);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        /*view = findViewById(R.id.anchor_view);*/
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setToolbarNavigationClickListener(this);

        drawer.setDrawerListener(toggle);
        toggle.syncState();


        appName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isNavigationDrawerOpened) {

                    searchView.setVisibility(View.VISIBLE);
                    searchView.requestFocus();
                    appName.setVisibility(View.GONE);
                    isNavigationDrawerOpened = true;
                    animateDrawerIndicator(true);
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.toggleSoftInputFromWindow(view.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);

                }
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        for(int i=0;i<10;i++){
            SearchResult searchResult  = new SearchResult("Manikanta"+i, ContextCompat.getDrawable(NavigationDrawerActivity.this, R.drawable.ic_history));
            searchResults.add(searchResult);
        }

        searchAdapter = new SearchAdapter(NavigationDrawerActivity.this,searchResults,searchView);
        searchView.setAdapter(searchAdapter);

        searchView.setDropDownAnchor(toolbar.getId());
        List<String> fragmentTitles = new ArrayList<>();
        fragmentTitles.add("Tutorials");
        fragmentTitles.add("Tools");
        HomeViewPagerAdapter homeViewPagerAdapter = new HomeViewPagerAdapter(getSupportFragmentManager(),fragmentTitles);
        viewPager.setAdapter(homeViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void hideSearchAndEnableAppName() {
        animateDrawerIndicator(false);
        isNavigationDrawerOpened = false;
        searchView.setVisibility(View.GONE);
        appName.setVisibility(View.VISIBLE);
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if(isNavigationDrawerOpened){
            hideSearchAndEnableAppName();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                Toast.makeText(getApplicationContext(),"asdfasdf",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void animateDrawerIndicator(boolean shouldAnimate) {
        ValueAnimator anim;
        if(shouldAnimate) {
            anim = ValueAnimator.ofFloat(0, 1);
        } else {
            anim = ValueAnimator.ofFloat(1, 0);
        }
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float slideOffset = (Float) valueAnimator.getAnimatedValue();
                // You should get the drawer layout and
                // toggler from your fragment to make the animation
                toggle
                        .onDrawerSlide(drawer,
                                slideOffset);
            }
        });
        anim.setInterpolator(new DecelerateInterpolator());
        anim.setDuration(500);
        anim.start();
    }


    @Override
    public void onClick(View view) {
        if(isNavigationDrawerOpened){
            hideSearchAndEnableAppName();
        }
        else{
            toggle.toggle();
        }
    }
}
