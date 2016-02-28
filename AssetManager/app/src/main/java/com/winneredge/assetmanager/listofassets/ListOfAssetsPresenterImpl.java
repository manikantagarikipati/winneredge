package com.winneredge.assetmanager.listofassets;

import android.content.Context;

import com.winneredge.assetmanager.wcommons.database.WAsset;
import com.winneredge.assetmanager.wcommons.utils.CollectionUtils;
import com.winneredge.assetmanager.wcommons.utils.StringUtils;

import java.util.List;

/**
 * Created by Manikanta on 2/28/2016.
 */
public class ListOfAssetsPresenterImpl implements ListOfAssetsPresenter{

    private  Context context;
    private ListOfAssetsViewBean bean;
    private ListOfAssetsView view;

    public ListOfAssetsPresenterImpl(Context context,ListOfAssetsViewBean bean,ListOfAssetsView view){

        this.context = context;
        this.view = view;
        this.bean = bean;
    }

    @Override
    public void systemFetchAssetsList() {
        if(StringUtils.isNotEmpty(bean.getStockId())){
            List<WAsset> assetList = WAsset.getAssetsForStock(bean.getStockId());
            if(CollectionUtils.isNotEmpty(assetList)){
                view.systemFetchAssetsListFailure();
            }else{
                bean.setAssetsList(assetList);
                view.systemFetchAssetsListSuccess();
            }
        }else{
            view.systemFetchAssetsListFailure();
        }

    }
}
