package com.winneredge.assetmanager.addasset;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.winneredge.assetmanager.R;
import com.winneredge.assetmanager.wcommons.activities.WActivity;
import com.winneredge.assetmanager.wcommons.constants.GlobalConstants;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import zxing.CaptureActivity;

public class AddAssetActivity extends WActivity implements AddAssetView{

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    private AddAssetViewBean bean;

    private static final int SCAN_BARCODE_REQUEST_CODE = 100;

    private AddAssetPresenter addAssetPresenter;

    @InjectView(R.id.til_barcode)
    TextInputLayout tilBarcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_asset);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        initializeViewBean();
        initActionBar();
        addAssetPresenter = new AddAssetPresenterImpl(this,bean,this);

    }

    private void initActionBar() {
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.add_product));
        }
    }

    @OnClick(R.id.scan_barcode) void scanBarcode(){
        addAssetPresenter.userPressedScanBarcode();
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
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED);
    }

    @Override
    public void initializeViewBean() {

        bean = new AddAssetViewBean();

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initialiseComponents() {

    }

    @Override
    public void userPressedScanBarcodeSuccess() {
        Intent scanBarcodeIntent = new Intent(this, CaptureActivity.class);
        startActivityForResult(scanBarcodeIntent, SCAN_BARCODE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == SCAN_BARCODE_REQUEST_CODE){
                String scannedCode = data.getStringExtra(GlobalConstants.SCANNED_CODE);
                if(tilBarcode.getEditText()!=null){
                    //clear the existing text before setting obtained barcode
                    tilBarcode.getEditText().setText("");
                    tilBarcode.getEditText().append(scannedCode);
                }
            }
        }
    }
}
