package com.example.hilmi.sistempakar;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.hilmi.sistempakar.view.activity.Konsultasi;
import com.example.hilmi.sistempakar.view.activity.ScreenBantuan;
import com.example.hilmi.sistempakar.view.activity.ScreenGallery;
import com.example.hilmi.sistempakar.view.activity.ScreenLihatData;
import com.example.hilmi.sistempakar.view.activity.ScreenTentangApps;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class IndexUtama extends AppCompatActivity implements View.OnClickListener {

    AdView adBanner;
    AdRequest adRequest;
    private CardView konsulCv;
    private CardView  lihat_dataCv, gallery, bantuanCv, tentangCv, exitCv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_utama);


        MobileAds.initialize(this, "ca-app-pub-5302278666494604/6280307780");
        adBanner = (AdView) findViewById(R.id.addBanner);
        adRequest = new AdRequest.Builder().addTestDevice("5B6BE3C0FAD3C33E3F3B9848680DCF9C"). build();
        adBanner.loadAd(adRequest);
        setTitle(R.string.judul);



        konsulCv = (CardView) findViewById(R.id.konsulCv);
        lihat_dataCv = (CardView) findViewById(R.id.lihat_dataCv);
        bantuanCv = (CardView) findViewById(R.id.bantuanCV);
        tentangCv = (CardView) findViewById(R.id.tentangCv);
        gallery = (CardView) findViewById(R.id.galleryCV);
        exitCv = (CardView) findViewById(R.id.exitCv);

        //add click listener
        konsulCv.setOnClickListener(this);
        lihat_dataCv.setOnClickListener(this);
        bantuanCv.setOnClickListener(this);
        tentangCv.setOnClickListener(this);
        gallery.setOnClickListener(this);
        exitCv.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.konsulCv:
                Intent i =new Intent(getApplicationContext(), Konsultasi.class);
                startActivity(i);
                break;
            case R.id.lihat_dataCv : this.overridePendingTransition(R.anim.anim_right, R.anim.anim_right);
                i = new Intent(this,  ScreenLihatData.class);
                i.putExtra("kode_gejala","G06");
                startActivity(i);
                break;
            case R.id.galleryCV : i = new Intent(this, ScreenGallery.class);
                startActivity(i);
                break;
            case R.id.bantuanCV : this.overridePendingTransition(R.anim.anim_right, R.anim.anim_right);
                i = new Intent(this, ScreenBantuan.class);
                startActivity(i);
                break;
            case R.id.tentangCv :this.overridePendingTransition(R.anim.anim_right, R.anim.anim_right);
//                i = new Intent(this, Test.class);
                i = new Intent(this, ScreenTentangApps.class);
                startActivity(i);
                break;

            case R.id.exitCv :
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setMessage("Yakin Anda ingin keluar?")
                        .setCancelable(false)
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                System.exit(1);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            default: break;
        }
    }




}
