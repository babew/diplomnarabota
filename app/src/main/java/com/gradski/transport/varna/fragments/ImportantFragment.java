package com.gradski.transport.varna.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gradski.transport.varna.R;
import com.gradski.transport.varna.globalClasses.Utils;
import com.gradski.transport.varna.models.ImportantText;

import java.util.ArrayList;

/**
 * Created by lyubomir.babev on 31/05/2017.
 */

public class ImportantFragment extends BaseFragment {

    private View                        mView;
    private LinearLayout                mTextsLayout;
    private ArrayList<ImportantText>    mTextsArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_important, container, false);

        init();

        getData();

        setText();

        return mView;
    }

    private void init() {
        mTextsLayout = (LinearLayout) mView.findViewById(R.id.texts_layout);
    }

    private void getData() {
        mTextsArrayList.add(new ImportantText(0, "Промени в обслужването от 01.07.2017 г.", Utils.IMPORTANT_TEXT_TYPE_TITLE));
        mTextsArrayList.add(new ImportantText(1, "Уважаеми клиенти,\n" +
                " \n" +
                "Информираме Ви, че считано от 01.07.2017 г. се въвеждат следните промени в обслужване на определени линии от „Градски транспорт“ ЕАД Варна.", Utils.IMPORTANT_TEXT_TYPE_MESSAGE));
        mTextsArrayList.add(new ImportantText(2, "Автобусна линия 148 ще обслужва късни курсове от „Почивка“ и „Владиславово“ в 22:40 и 23:00 часа (валидно за делник и празник);", Utils.IMPORTANT_TEXT_TYPE_LIST));
        mTextsArrayList.add(new ImportantText(3, "Автобусна линия 209 Б ще обслужва курс от „Владиславово“ в 21:40 и от „Панорама“ в 22:40 (валидно за делник и празник);", Utils.IMPORTANT_TEXT_TYPE_LIST));
        mTextsArrayList.add(new ImportantText(4, "Преустановява се обслужването на курсовете по направление на линия 14 А;", Utils.IMPORTANT_TEXT_TYPE_LIST));
        mTextsArrayList.add(new ImportantText(5, "Преустановява се обслужването на курсове по направление на 31 и 31А, чиито маршрут е по бул. „Левски“;", Utils.IMPORTANT_TEXT_TYPE_LIST));
        mTextsArrayList.add(new ImportantText(6, "Преустановява се обслужването на курсове по направление на линия 36 от с. Звездица в 07:00 и 10:00, както курс в  10:30 и 18:10 от Варна за с. Звездица. Курс от Варна ще се изпълнява 17:45 часа;", Utils.IMPORTANT_TEXT_TYPE_LIST));
        mTextsArrayList.add(new ImportantText(7, "Преустановява се обслужването на курс по направление на линия 23 в 12:45 от Варна и 13:30 от с. Каменар.", Utils.IMPORTANT_TEXT_TYPE_LIST));
        mTextsArrayList.add(new ImportantText(8, "Информационен център", Utils.IMPORTANT_TEXT_TYPE_TITLE));
        mTextsArrayList.add(new ImportantText(8, "„Градски транспорт“ ЕАД Варна Ви информира, че от 25.11.2016 г. за Ваше удобство експериментално работи информационен център с телефон за връзка 0700 10 933.\n" +
                "\n" +
                "Работно време за директна връзка всеки ден от 06:00 до 22:00 часа. Извън посочените часове всички обаждания ще бъдат автоматично записвани и обработени в работен ден.\n" +
                "\n" +
                "С цел подобряване качеството на предоставяните услуги, всички разговори ще бъдат записани.\n" +
                "\n" +
                "Вашите предложения, въпроси, жалби и сигнали можете да отправите и чрез контактната форма на адрес http://www.gtvarna.com/zhalbi-i-signali", Utils.IMPORTANT_TEXT_TYPE_MESSAGE));

    }

    private void setText() {
        int counter = 1;
        for (int i = 0; i < mTextsArrayList.size(); i++) {
            ImportantText               importantText   = mTextsArrayList.get(i);
            TextView                    textView        = new TextView(getContext());
            LinearLayout.LayoutParams   layoutParams    = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            int                         margin          = (int)(getResources().getDisplayMetrics().density * 10);

            if (importantText.getType() == Utils.IMPORTANT_TEXT_TYPE_TITLE) {
                layoutParams.setMargins(margin, margin, margin, margin);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                textView.setText(importantText.getText());
                counter = 1;
            } else if (importantText.getType() == Utils.IMPORTANT_TEXT_TYPE_LIST){
                layoutParams.setMargins(0, 0, margin, 0);
                textView.setText("  " + counter + "." + importantText.getText());
                counter++;
            } else {
                layoutParams.setMargins(margin, margin, margin, margin);
                counter = 1;
            }

            textView.setLayoutParams(layoutParams);
            mTextsLayout.addView(textView);
        }
    }
}
