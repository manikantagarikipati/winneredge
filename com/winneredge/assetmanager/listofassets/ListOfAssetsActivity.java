package com.winneredge.assetmanager.listofassets;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.winneredge.assetmanager.R;
import com.winneredge.assetmanager.wcommons.activities.WActivity;
import com.winneredge.assetmanager.wcommons.constants.GlobalConstants;
import com.winneredge.assetmanager.wcommons.utils.StringUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ListOfAssetsActivity extends WActivity implements ListOfAssetsView{


    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    private ListOfAssetsViewBean bean;
    private ListOfAssetsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_assets);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        initActionBar();
        initializeViewBean();
        initPresenter();
        initialiseComponents();
    }

    private void initActionBar() {
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void initializeViewBean() {
        bean = new ListOfAssetsViewBean();
        if(getIntent()!=null && getIntent().getExtras()!=null){
            bean.setStockId(getIntent().getStringExtra(GlobalConstants.STOCK_ID));
            bean.setStockName(getIntent().getStringExtra(GlobalConstants.STOCK_NAME));
            setToolbarTitle();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void initPresenter() {
        presenter = new ListOfAssetsPresenterImpl(this,bean,this);
    }

    @Override
    public void initialiseComponents() {
        presenter.systemFetchAssetsList();
    }

    @Override
    public void systemFetchAssetsListSuccess() {

    }

    @Override
    public void systemFetchAssetsListFailure() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_stock_name, null);
        final TextInputLayout tilStockName = (TextInputLayout)view.findViewById(R.id.til_stock_name);
        final TextView tvCancel = (TextView)view.findViewById(R.id.tv_cancel);
        final TextView tvOk = (TextView)view.findViewById(R.id.tv_ok);

        alertDialog.setView(view);
        alertDialog.setCancelable(false);

        alertDialog.create();
        final AlertDialog dialog = alertDialog.show();

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });

        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tilStockName.getEditText() != null) {
                    String stockName = tilStockName.getEditText().getText().toString();
                    if (StringUtils.isNotEmpty(stockName)) {
                        dialog.dismiss();
                        bean.setStockName(tilStockName.getEditText().getText().toString());
                        setToolbarTitle();
                    } else {
                        tilStockName.setError("Stock Name cannot be empty");
                    }
                }
            }
        });
    }

    private void setToolbarTitle(){
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle(bean.getStockName());
        }
    }
}
