package com.winneredge.materialnavigationdrawers.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.winneredge.materialnavigationdrawers.R;
import com.winneredge.materialnavigationdrawers.model.SearchResult;

import java.util.List;

/**
 * Created by Manikanta on 11/30/2015.
 */
public class SearchAdapter extends ArrayAdapter<SearchResult> {

    private boolean mAnimate;
    private AutoCompleteTextView mSearch;
    public SearchAdapter(Context context, List<SearchResult> options, AutoCompleteTextView search) {
        super(context, 0, options);
        mSearch = search;
    }

    int count = 0;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SearchResult option = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.search_option, parent, false);

         /*   if (true) {
                Animation anim = AnimationUtils.loadAnimation(getContext(),
                        R.anim.anim_down);
                anim.setDuration(400);
                convertView.startAnimation(anim);
                if (count == this.getCount()) {
                    mAnimate = false;
                }
                count++;
            }*/
        }

        View border = convertView.findViewById(R.id.border);
        if (position == 0) {
            border.setVisibility(View.VISIBLE);
        } else {
            border.setVisibility(View.GONE);
        }
        final TextView title = (TextView) convertView
                .findViewById(R.id.title);
        title.setText(option.title);
        ImageView icon = (ImageView) convertView.findViewById(R.id.icon);
        icon.setImageDrawable(option.icon);
        ImageView up = (ImageView) convertView.findViewById(R.id.up);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSearch.setText(title.getText().toString());
                mSearch.setSelection(mSearch.getText().length());
            }
        });

        return convertView;
    }
}
