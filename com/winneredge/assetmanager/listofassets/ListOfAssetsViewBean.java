package com.winneredge.assetmanager.listofassets;

import com.winneredge.assetmanager.wcommons.database.WAsset;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manikanta on 2/28/2016.
 */
public class ListOfAssetsViewBean {

    private String stockId;

    private List<WAsset> assetsList = new ArrayList<>();

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    private String stockName;

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public List<WAsset> getAssetsList() {
        return assetsList;
    }

    public void setAssetsList(List<WAsset> assetsList) {
        this.assetsList = assetsList;
    }




}
