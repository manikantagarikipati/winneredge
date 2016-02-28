package com.winneredge.assetmanager.addasset;

import android.content.Context;

/**
 * Created by Manikanta on 2/28/2016.
 */
public class AddAssetPresenterImpl implements AddAssetPresenter {

    private AddAssetView view;
    private AddAssetViewBean bean;
    private Context context;

    public AddAssetPresenterImpl(AddAssetView view,AddAssetViewBean bean,Context context){
        this.view = view;
        this.bean = bean;
        this.context = context;
    }

    @Override
    public void userPressedAddAsset() {

    }

    @Override
    public void userPressedScanBarcode() {
        view.userPressedScanBarcodeSuccess();
    }
}
