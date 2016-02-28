package com.winneredge.assetmanager.mystocks;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.winneredge.assetmanager.R;
import com.winneredge.assetmanager.wcommons.database.WStocks;
import com.winneredge.assetmanager.wcommons.utils.MediaUtils;
import com.winneredge.assetmanager.wcommons.utils.StringUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Manikanta on 2/28/2016.
 */
public class StocksRecyclerViewAdapter extends RecyclerView.Adapter<StocksRecyclerViewAdapter.StockViewHolder> {


    private List<WStocks> stocksList;
    private Context context;

    public StocksRecyclerViewAdapter(List<WStocks> stocksList,Context context){
        this.stocksList = stocksList;
        this.context = context;
    }

    @Override
    public StockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View stockView = LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_item, parent, false);
        return new StockViewHolder(stockView);
    }

    @Override
    public void onBindViewHolder(final StockViewHolder holder, int position) {
        final WStocks stocks = stocksList.get(position);

        holder.stockName.setText(stocks.stockName);
        holder.stockThumbnail.setImageDrawable(MediaUtils.getRoundedTextIcon(stocks.stockName,context));
        if(StringUtils.isEmpty(stocks.stockUpdatedSheetName)){
            holder.stockSheetName.setText(context.getString(R.string.work_sheet_unavailable));
        }else{
            holder.stockSheetName.setText(stocks.stockUpdatedSheetName);
        }
        holder.stockCreatedDate.setText(stocks.stockCreatedDate);
    }

    @Override
    public int getItemCount() {
        return stocksList.size();
    }

    static class StockViewHolder  extends RecyclerView.ViewHolder{

        @InjectView(R.id.stock_sheet_name)
        TextView stockSheetName;

        @InjectView(R.id.stock_created_date) TextView stockCreatedDate;

        @InjectView(R.id.stock_thumbnail)
        ImageView stockThumbnail;

        @InjectView(R.id.stock_name)
        TextView stockName;

        public final View mView;

        StockViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            ButterKnife.inject(this, itemView);
        }
    }


}
