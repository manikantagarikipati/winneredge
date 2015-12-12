package com.winneredge.materialnavigationdrawers.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.winneredge.materialnavigationdrawers.R;

/**
 * Created by Manikanta on 12/12/2015.
 */
public class ToolsFragment extends Fragment {


    public static ToolsFragment newInstance() {

        return new ToolsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tools, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.tools_rv);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        return rootView;
    }

}
