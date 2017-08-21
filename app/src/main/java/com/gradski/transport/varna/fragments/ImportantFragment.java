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
import com.gradski.transport.varna.globalClasses.Utils;
import com.gradski.transport.varna.models.ImportantObject;
import com.gradski.transport.varna.models.ImportantText;

import java.util.ArrayList;

/**
 * Created by lyubomir.babev on 31/05/2017.
 */

public class ImportantFragment extends BaseFragment {

    private View                            mView;
    private RecyclerView                    mRecyclerView;
    private LinearLayoutManager             mLinearLayoutManager;
    private ImportantObjectsRecyclerAdapter mAdapter;
    private ArrayList<ImportantObject>      mImportantObjectsArrayList  = new ArrayList<>();
    private ArrayList<ImportantText>        mTextsArrayList             = new ArrayList<>();
    private FloatingActionButton            mFab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_important, container, false);

        getData();

        init();

        return mView;
    }

    private void init() {
        mRecyclerView           = (RecyclerView) mView.findViewById(R.id.recycler_view);
        mLinearLayoutManager    = new LinearLayoutManager(getActivity());
        mAdapter                = new ImportantObjectsRecyclerAdapter(getContext(), mImportantObjectsArrayList);
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
        mTextsArrayList.add(new ImportantText(0, "Уважаеми клиенти,\n" +
                " \n" +
                "Информираме Ви, че считано от 01.07.2017 г. се въвеждат следните промени в обслужване на определени линии от „Градски транспорт“ ЕАД Варна.", Utils.IMPORTANT_TEXT_TYPE_MESSAGE));
        mTextsArrayList.add(new ImportantText(1, "Автобусна линия 148 ще обслужва късни курсове от „Почивка“ и „Владиславово“ в 22:40 и 23:00 часа (валидно за делник и празник);", Utils.IMPORTANT_TEXT_TYPE_LIST));
        mTextsArrayList.add(new ImportantText(2, "Автобусна линия 209 Б ще обслужва курс от „Владиславово“ в 21:40 и от „Панорама“ в 22:40 (валидно за делник и празник);", Utils.IMPORTANT_TEXT_TYPE_LIST));
        mTextsArrayList.add(new ImportantText(3, "Преустановява се обслужването на курсовете по направление на линия 14 А;", Utils.IMPORTANT_TEXT_TYPE_LIST));
        mTextsArrayList.add(new ImportantText(4, "Преустановява се обслужването на курсове по направление на 31 и 31А, чиито маршрут е по бул. „Левски“;", Utils.IMPORTANT_TEXT_TYPE_LIST));
        mTextsArrayList.add(new ImportantText(5, "Преустановява се обслужването на курсове по направление на линия 36 от с. Звездица в 07:00 и 10:00, както курс в  10:30 и 18:10 от Варна за с. Звездица. Курс от Варна ще се изпълнява 17:45 часа;", Utils.IMPORTANT_TEXT_TYPE_LIST));
        mTextsArrayList.add(new ImportantText(6, "Преустановява се обслужването на курс по направление на линия 23 в 12:45 от Варна и 13:30 от с. Каменар.", Utils.IMPORTANT_TEXT_TYPE_LIST));
        mImportantObjectsArrayList.add(new ImportantObject(0, "Промени в обслужването от 01.07.2017 г.", mTextsArrayList));

        mTextsArrayList = new ArrayList<>();
        mTextsArrayList.add(new ImportantText(8, "„Градски транспорт“ ЕАД Варна Ви информира, че от 25.11.2016 г. за Ваше удобство експериментално работи информационен център с телефон за връзка 0700 10 933.\n" +
                "\n" +
                "Работно време за директна връзка всеки ден от 06:00 до 22:00 часа. Извън посочените часове всички обаждания ще бъдат автоматично записвани и обработени в работен ден.\n" +
                "\n" +
                "С цел подобряване качеството на предоставяните услуги, всички разговори ще бъдат записани.\n" +
                "\n" +
                "Вашите предложения, въпроси, жалби и сигнали можете да отправите и чрез контактната форма на адрес http://www.gtvarna.com/zhalbi-i-signali", Utils.IMPORTANT_TEXT_TYPE_MESSAGE));
        mImportantObjectsArrayList.add(new ImportantObject(0, "Информационен център", mTextsArrayList));
    }

}
