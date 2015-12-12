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
public class TutorialsFragment extends Fragment {

    public static TutorialsFragment newInstance() {

        return new TutorialsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tutorials, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.tutorials_rv);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        return rootView;
    }
}
