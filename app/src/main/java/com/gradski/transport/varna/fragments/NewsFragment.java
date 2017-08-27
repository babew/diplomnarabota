package com.gradski.transport.varna.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gradski.transport.varna.R;
import com.gradski.transport.varna.adapters.ImportantObjectsRecyclerAdapter;
import com.gradski.transport.varna.adapters.NewsRecyclerAdapter;
import com.gradski.transport.varna.models.ImportantObject;
import com.gradski.transport.varna.models.ImportantText;
import com.gradski.transport.varna.models.News;

import java.util.ArrayList;

/**
 * Created by lyubomir.babev on 31/05/2017.
 */

public class NewsFragment extends BaseFragment {

    private View                            mView;
    private RecyclerView                    mRecyclerView;
    private LinearLayoutManager             mLinearLayoutManager;
    private NewsRecyclerAdapter             mAdapter;
    private ArrayList<News>                 mNewsArrayList = new ArrayList<>();
    private FloatingActionButton            mFab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_with_recycler_view, container, false);

        getData();

        init();

        return mView;
    }

    private void init() {
        mRecyclerView           = (RecyclerView) mView.findViewById(R.id.recycler_view);
        mLinearLayoutManager    = new LinearLayoutManager(getActivity());
        mAdapter                = new NewsRecyclerAdapter(getContext(), mNewsArrayList);
        mFab                    = (FloatingActionButton)    getActivity().findViewById(R.id.bus_fab);

        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0)
                    mFab.hide();
                else
                    mFab.show();
            }
        });
    }

    private void getData() {
        mNewsArrayList.add(new News(0, "Рисунка подарък", "Тази рисунка е подарък на колегите, обслужващи автобусна линия 32! Благодарим на Младен!" ,"26 MAY 2017, 16:00", "http://www.gtvarna.com/uploads/thumbnail/bus_32-c800x500.jpg"));
        mNewsArrayList.add(new News(1, "Намерен телефон", "На 10.04.2017 г. водач на автобус № 12 - Венцислав Николов Берчев, намира и предава смарт телефон, който е върнат на собственика - Димитър Димитров. Същият изказва хиляди благодарности за чесността и коректното поведение на водача Венцислав Берчев." ,"13 АПРИЛ 2017, 14:35", ""));
        mNewsArrayList.add(new News(2, "Похвално писмо", "Днес 15.03.2017 г., в деловодтсвото на Дружеството бе получено следното писмо:\n" +
                "\n" +
                "\"Добър ден,\n" +
                "пише Ви ваша пътничка,която ползва редовно услугите на Градски транспорт АД!\n" +
                "Вчера,ден вторник,14.03.2017 пътувах както обикновено по линия номер 12 в късния след обяд!Случи ми се нещо,което в днешни дни се среща много рядко!\n" +
                "На спирка Терапия, водачът на автобус с номер В 8662 направи нещо похвално,което впечатли мен и останалите пътници в автобуса!В автобуса имаше инвалид с инвалидна количка,чието предвижване беше очевидно затруднено!Освен това на спирката имаше доста спрели леки коли,които допълнително затрудняваха слизането на инвалида с количката!Водачът на автобуса направи няколко маневри ,с които доближи максимално автобуса до тротоара,СТАНА от мястото си,пусна рампата за предвижване на инвалиди,помогна на нуждаещият се да слезе от автобуса и го остави на тротоара!\n" +
                "Защо пиша това писмо ли?Защото сме свикнали да се обаждаме и пишем имейли само когато сме недоволни!Считам,че подобно поведение и държание е похвално и трябва да се знае,че има и добри хора!\"" ,"15 MARCH 2017, 13:00", ""));
    }

}
