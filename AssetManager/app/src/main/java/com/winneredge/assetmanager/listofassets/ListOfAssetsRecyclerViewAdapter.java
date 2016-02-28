package com.winneredge.assetmanager.listofassets;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.winneredge.assetmanager.R;
import com.winneredge.assetmanager.wcommons.database.WAsset;
import com.winneredge.assetmanager.wcommons.utils.MediaUtils;
import com.winneredge.assetmanager.wcommons.utils.StringUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Manikanta on 2/28/2016.
 */
public class ListOfAssetsRecyclerViewAdapter extends RecyclerView.Adapter<ListOfAssetsRecyclerViewAdapter.ListOFAssetsViewHolder> {

    private List<WAsset> assetList;
    private Context context;

    public ListOfAssetsRecyclerViewAdapter(List<WAsset> assetsList,Context context){
        this.assetList = assetsList;
        this.context = context;
    }


    @Override
    public ListOFAssetsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View assetsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.asset_item, parent, false);
        return new ListOFAssetsViewHolder(assetsView);
    }


    @Override
    public void onBindViewHolder(ListOFAssetsViewHolder holder, int position) {

        WAsset asset = assetList.get(position);
        holder.assetBarCode.setText(asset.assetBarCode);
        holder.assetName.setText(asset.assetName);
        holder.assetThumbnail.setImageDrawable(MediaUtils.getRoundedTextIcon(asset.assetName, context));
        holder.assetCreatedDate.setText(asset.assetAddedDate);
        if(StringUtils.isNotEmpty(asset.assetComments)){
            holder.assetComments.setVisibility(View.VISIBLE);
            holder.assetComments.setText(asset.assetComments);
        }else{
            holder.assetComments.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return assetList.size();
    }

    static class ListOFAssetsViewHolder extends RecyclerView.ViewHolder{

        @InjectView(R.id.asset_thumbnail)
        ImageView assetThumbnail;

        @InjectView(R.id.asset_name)
        TextView assetName;

        @InjectView(R.id.asset_created_date)
        TextView assetCreatedDate;

        @InjectView(R.id.asset_comments)
        TextView assetComments;

        @InjectView(R.id.asset_bar_code)
        TextView assetBarCode;

        public ListOFAssetsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }
    }
}
