package com.winneredge.assetmanager.mystocks;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.winneredge.assetmanager.R;
import com.winneredge.assetmanager.listofassets.ListOfAssetsActivity;
import com.winneredge.assetmanager.wcommons.activities.WActivity;
import com.winneredge.assetmanager.wcommons.database.WStocks;
import com.winneredge.assetmanager.wcommons.utils.CollectionUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class HomeActivity extends WActivity implements NavigationView.OnNavigationItemSelectedListener{

    @InjectView(R.id.activity_home_empty_view)
    View emptyView;

    @InjectView(R.id.fab_add_asset)
    FloatingActionButton fabAdd;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.nav_view)
    NavigationView navigationView;

    @InjectView(R.id.drawer_layout)
    DrawerLayout drawer;

    @InjectView(R.id.stocks_recycler_view)
    RecyclerView stocksRecyclerView;

    private StocksRecyclerViewAdapter stocksRecyclerViewAdapter;

    private static final int ADD_PRODUCT_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        initializeViewBean();
        initPresenter();
        initialiseComponents();
    }

    @OnClick(R.id.fab_add_asset) void addProduct(){
        hideFab();
    }

    private void hideFab() {
         Animation   fabAnimation = AnimationUtils.loadAnimation(this,R.anim.fab_hide);

        fabAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                    fabAdd.setVisibility(View.GONE);
                    Intent addProductIntent = new Intent(HomeActivity.this, ListOfAssetsActivity.class);
                    startActivityForResult(addProductIntent, ADD_PRODUCT_REQUEST_CODE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        fabAdd.startAnimation(fabAnimation);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fabAdd.show();
    }

    @Override
    public void initializeViewBean() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initialiseComponents() {


        setStocksToAdapter();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);

        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);

    }

    private void setStocksToAdapter() {

        List<WStocks> stocks = WStocks.getListOfStocks();
        if(CollectionUtils.isNotEmpty(stocks)){
            emptyView.setVisibility(View.GONE);
            stocksRecyclerViewAdapter = new StocksRecyclerViewAdapter(stocks,this);
            stocksRecyclerView.setHasFixedSize(true);
            stocksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            stocksRecyclerView.setAdapter(stocksRecyclerViewAdapter);
        }else{
            stocksRecyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        drawer.closeDrawer(GravityCompat.START);

        switch (id) {

            case R.id.nav_my_products:
                Toast.makeText(this,"My Products Pressed",Toast.LENGTH_LONG).show();
                break;

            case R.id.nav_about_me:
                Toast.makeText(this, "About me Pressed", Toast.LENGTH_LONG).show();
                break;

            case R.id.nav_settings:
                Toast.makeText(this, "Settings Pressed", Toast.LENGTH_LONG).show();
                break;

            default:
                return true;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
