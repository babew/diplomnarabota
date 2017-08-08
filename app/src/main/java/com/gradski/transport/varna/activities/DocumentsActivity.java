package com.gradski.transport.varna.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Toast;

import com.gradski.transport.varna.BuildConfig;
import com.gradski.transport.varna.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by lyubomir.babev on 01/06/2017.
 */

public class DocumentsActivity extends BaseActivity implements View.OnClickListener{

    private String mFileName = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_documents);

        findViewById(R.id.back_arrow_image_view).setOnClickListener(this);
        findViewById(R.id.procedure_layout).setOnClickListener(this);
        findViewById(R.id.ordinance_layout).setOnClickListener(this);
        findViewById(R.id.ordinance2_layout).setOnClickListener(this);
        findViewById(R.id.law_layout).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == findViewById(R.id.back_arrow_image_view)) {
            super.onBackPressed();
        } else if (v == findViewById(R.id.procedure_layout)) {
            loadDocument("gtv_procedure.pdf");
        } else if (v == findViewById(R.id.ordinance_layout)) {
            loadDocument("gtv_ordinance.pdf");
        } else if (v == findViewById(R.id.ordinance2_layout)) {
            loadDocument("gtv_ordinance2.pdf");
        } else if (v == findViewById(R.id.law_layout)) {
            loadDocument("gtv_law.pdf");
        }
    }


    private void loadDocument(String fileName) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            mFileName = fileName;
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            return;
        }

        copyFile(fileName);

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);
        Uri path = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", file);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(path, "application/pdf");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        try {
            DocumentsActivity.this.startActivity(intent);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(DocumentsActivity.this, "Инсталирайте програма която да чете PDF файлове!", Toast.LENGTH_LONG).show();
        }
    }

    private void copyFile(String fileName){
        InputStream     inputStream;
        OutputStream    outputStream;
        AssetManager    assetManager    = getAssets();
        File            file            = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);

        if (file.exists())
            return;

        try {
            inputStream     = assetManager.open(fileName);
            outputStream    = new FileOutputStream(file);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = inputStream.read(buffer)) != -1)
                outputStream.write(buffer, 0, read);

            inputStream.close();
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(DocumentsActivity.this, "Възникна грешка при отварянето на документът!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            loadDocument(mFileName);
    }
}
