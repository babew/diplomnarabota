package com.gradski.transport.varna.activities;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gradski.transport.varna.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lyubomir.babev on 01/06/2017.
 */

public class ComplaintsAndSignalsActivity extends BaseActivity implements View.OnClickListener {

    private EditText mNameEditText;
    private EditText mEmailEditText;
    private EditText mPhoneNumberEditText;
    private EditText mMessageEditText;
    private TextView mSendTextView;

    private boolean mKeyboardVisible = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_complaints_and_signals);

        init();
    }

    private void init() {
        mNameEditText           = (EditText) findViewById(R.id.name_edit_text);
        mEmailEditText          = (EditText) findViewById(R.id.email_edit_text);
        mPhoneNumberEditText    = (EditText) findViewById(R.id.phone_number_edit_text);
        mMessageEditText        = (EditText) findViewById(R.id.message_edit_text);
        mSendTextView           = (TextView) findViewById(R.id.send_text_view);

        setKeyboardListener();

        findViewById(R.id.back_arrow_image_view).setOnClickListener(this);
        mSendTextView.setOnClickListener(this);
        mMessageEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    onSendClicked();
                    return true;
                }
                return false;
            }
        });
    }

    private void setKeyboardListener() {
        final View activityRootView = findViewById(R.id.root_view);

        final DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int maxDifference = displayMetrics.heightPixels * 15 / 100;

        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                activityRootView.getWindowVisibleDisplayFrame(r);

                int screenHeight = getDisplayHeight();
                int heightDiff   = screenHeight - (r.bottom - r.top);
                int resourceId   = getResources().getIdentifier("status_bar_height", "dimen", "android");

                if (resourceId > 0) {
                    heightDiff -= getResources().getDimensionPixelSize(resourceId);
                }

                findViewById(R.id.send_layout).setVisibility(heightDiff > maxDifference ? View.GONE : View.VISIBLE);
            }
        });
    }

    private int getDisplayHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    private boolean validate() {
        if (mNameEditText.getText().toString().trim().equalsIgnoreCase("")) {
            Toast.makeText(this, "Моля, въведете лице за контакт.", Toast.LENGTH_LONG).show();
            return false;
        } else if (mEmailEditText.getText().toString().trim().equalsIgnoreCase("")) {
            Toast.makeText(this, "Моля, въведете имейл.", Toast.LENGTH_LONG).show();
            return false;
        } else if (mPhoneNumberEditText.getText().toString().trim().equalsIgnoreCase("")) {
            Toast.makeText(this, "Моля, въведете телефон.", Toast.LENGTH_LONG).show();
            return false;
        } else if (mMessageEditText.getText().toString().trim().equalsIgnoreCase("")) {
            Toast.makeText(this, "Моля, въведете съобщение.", Toast.LENGTH_LONG).show();
            return false;
        } else if (!isEmailValid(mEmailEditText.getText().toString())) {
            Toast.makeText(this, "Моля, въведете валиден имейл.", Toast.LENGTH_LONG).show();
            return false;
        } else if (!isPhoneNumberValid(mPhoneNumberEditText.getText().toString())) {
            Toast.makeText(this, "Моля, въведете валиден телефон.", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    public boolean isPhoneNumberValid(String phone) {
        return phone != null && !(phone.length() < 6 || phone.length() > 13) && android.util.Patterns.PHONE.matcher(phone).matches();
    }

    public boolean isEmailValid(String strEmail) {
        String 			strExpression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence	oInputStr = strEmail;

        Pattern oPattern = Pattern.compile(strExpression, Pattern.CASE_INSENSITIVE);
        Matcher oMatcher = oPattern.matcher(oInputStr);

        return oMatcher.matches();
    }

    private void onSendClicked() {
        if (validate()) {
            String name         = mNameEditText.getText().toString();
            String email        = mEmailEditText.getText().toString();
            String phoneNumber  = mPhoneNumberEditText.getText().toString();
            String message      = mMessageEditText.getText().toString();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back_arrow_image_view)
            super.onBackPressed();
        else if (v.getId() == mSendTextView.getId())
            onSendClicked();
    }
}
