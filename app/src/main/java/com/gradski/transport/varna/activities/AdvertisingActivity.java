package com.gradski.transport.varna.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.gradski.transport.varna.R;

/**
 * Created by lyubomir.babev on 02/06/2017.
 */

public class AdvertisingActivity extends BaseActivity {

    private String mPhone = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_adversting);

        setClickableInfoTexts();

        findViewById(R.id.back_arrow_image_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdvertisingActivity.super.onBackPressed();
            }
        });
    }

    private void setClickableInfoTexts() {
        TextView        infoNumberTextView  = (TextView) findViewById(R.id.info_number_text_view);
        TextView        infoMailTextView    = (TextView) findViewById(R.id.info_mail_text_view);
        SpannableString numberSpannable     = new SpannableString(getString(R.string.info_number));
        SpannableString mailSpannable       = new SpannableString(getString(R.string.info_mail));
        ClickableSpan   firstNumberSpan     = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                callPhone("052572200");
            }
        };
        ClickableSpan secondNumberSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                callPhone("0882654011");
            }
        };
        ClickableSpan firstMailSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                sendEmail("info@gtvarna.com");
            }
        };
        ClickableSpan secondMailSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                sendEmail("reklama@gtvarna.com");
            }
        };
        numberSpannable.setSpan(firstNumberSpan, getString(R.string.info_number).indexOf(".") + 2, getString(R.string.info_number).indexOf(","), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        numberSpannable.setSpan(secondNumberSpan, getString(R.string.info_number).indexOf(",") + 2, getString(R.string.info_number).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mailSpannable.setSpan(firstMailSpan, getString(R.string.info_mail).indexOf(":") + 2, getString(R.string.info_mail).indexOf(","), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mailSpannable.setSpan(secondMailSpan, getString(R.string.info_mail).indexOf(",") + 2, getString(R.string.info_mail).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        infoNumberTextView.setText(numberSpannable);
        infoNumberTextView.setMovementMethod(LinkMovementMethod.getInstance());
        infoNumberTextView.setHighlightColor(Color.GRAY);
        infoMailTextView.setText(mailSpannable);
        infoMailTextView.setMovementMethod(LinkMovementMethod.getInstance());
        infoMailTextView.setHighlightColor(Color.GRAY);
    }

    private void callPhone(String phone) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            mPhone = phone;
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 0);
            return;
        }

        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        startActivity(intent);
    }

    public void sendEmail(String email){
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/html");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, email);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Въпрос към Градски Транспорт Варна");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
        startActivity(Intent.createChooser(emailIntent, "Изпрати имейл"));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
            callPhone(mPhone);
    }
}
