package com.gradski.transport.varna.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gradski.transport.varna.R;
import com.gradski.transport.varna.adapters.RouteChangesRecyclerAdapter;
import com.gradski.transport.varna.models.RouteChange;

import java.util.ArrayList;

/**
 * Created by lyubomir.babev on 31/05/2017.
 */

public class RouteChangesFragment extends BaseFragment {

    private View                            mView;
    private RecyclerView                    mRecyclerView;
    private LinearLayoutManager             mLinearLayoutManager;
    private RouteChangesRecyclerAdapter     mAdapter;
    private ArrayList<RouteChange>          mRouteChangesArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_route_changes, container, false);

        mRouteChangesArrayList.add(new RouteChange(0, 0, "Допълнителен курс на 118А от 10.07.2017 г.", "Считано от 10.07.2017 г., курс в 06:24 от Почивка ще обслужва през Гробищен парк, запазва се курса в 07:25 от Тополи.Важи само за делнично разписание.Считано от 10.07.2017 г., курс в 06:24 от Почивка ще обслужва през Гробищен парк, запазва се курса в 07:25 от Тополи.Важи само за делнично разписание.Считано от 10.07.2017 г., курс в 06:24 от Почивка ще обслужва през Гробищен парк, запазва се курса в 07:25 от Тополи.Важи само за делнично разписание."));
        mRouteChangesArrayList.add(new RouteChange(0, 0, "Допълнителен курс на 118А от 10.07.2017 г.", "Считано от 10.07.2017 г., курс в 06:24 от Почивка ще обслужва през Гробищен парк, запазва се курса в 07:25 от Тополи.Важи само за делнично разписание."));
        mRouteChangesArrayList.add(new RouteChange(0, 0, "Допълнителен курс на 118А от 10.07.2017 г.", "Считано от 10.07.2017 г., курс в 06:24 от Почивка ще обслужва през Гробищен парк, запазва се курса в 07:25 от Тополи.Важи само за делнично разписание."));
        mRouteChangesArrayList.add(new RouteChange(0, 0, "Допълнителен курс на 118А от 10.07.2017 г.", "Считано от 10.07.2017 г., курс в 06:24 от Почивка ще обслужва през Гробищен парк, запазва се курса в 07:25 от Тополи.Важи само за делнично разписание."));
        mRouteChangesArrayList.add(new RouteChange(0, 0, "Допълнителен курс на 118А от 10.07.2017 г.", "Считано от 10.07.2017 г., курс в 06:24 от Почивка ще обслужва през Гробищен парк, запазва се курса в 07:25 от Тополи.Важи само за делнично разписание."));
        mRouteChangesArrayList.add(new RouteChange(0, 0, "Допълнителен курс на 118А от 10.07.2017 г.", "Считано от 10.07.2017 г., курс в 06:24 от Почивка ще обслужва през Гробищен парк, запазва се курса в 07:25 от Тополи.Важи само за делнично разписание."));
        mRouteChangesArrayList.add(new RouteChange(0, 0, "Допълнителен курс на 118А от 10.07.2017 г.", "Считано от 10.07.2017 г., курс в 06:24 от Почивка ще обслужва през Гробищен парк, запазва се курса в 07:25 от Тополи.Важи само за делнично разписание."));

        init();

        return mView;
    }

    private void init() {
        mRecyclerView           = (RecyclerView) mView.findViewById(R.id.recycler_view);
        mLinearLayoutManager    = new LinearLayoutManager(getActivity());
        mAdapter                = new RouteChangesRecyclerAdapter(mRouteChangesArrayList);

        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
