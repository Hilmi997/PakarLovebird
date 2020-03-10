package com.example.hilmi.sistempakar.view.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hilmi.sistempakar.IndexUtama;
import com.example.hilmi.sistempakar.R;
import com.example.hilmi.sistempakar.adapters.GejalaItemAdapter;
import com.example.hilmi.sistempakar.database.SQLiteHelper;
import com.example.hilmi.sistempakar.models.Gejala;
import com.example.hilmi.sistempakar.models.GejalaListModels;
import com.example.hilmi.sistempakar.models.Keputusan;
import com.example.hilmi.sistempakar.models.Penyakit;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class HasilKonsultasi extends AppCompatActivity implements View.OnClickListener {

    private AdView mAdView;
    private String[] results;
    private String[] resultRulebaseTdkAktiv;
    private HashMap<String, ArrayList<String>> hasMap = new HashMap<>();
    private TextView txtNamaPenyakit, txtListGejala, txtHasilSolusi;
    Button btnUlang, btnSelesai;

    private List<Gejala> listgejalas;
    private GejalaItemAdapter gejalaItemAdapter;
    final String TAG = "hasilKonsultasi.java";

    //Save Log
    SQLiteHelper sqLiteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_hasil_diagnosa);

        MobileAds.initialize(this, "ca-app-pub-5302278666494604/5830743460");
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("ca-app-pub-5302278666494604/5830743460").build();
        mAdView.loadAd(adRequest);

        sqLiteHelper = new SQLiteHelper(this);
        txtNamaPenyakit = (TextView)findViewById(R.id.tvResultNamaPenyakit);
        txtListGejala = (TextView)findViewById(R.id.tvResultNamaGejala);
        txtHasilSolusi = (TextView)findViewById(R.id.tvResultSolusi);
        btnUlang = (Button)findViewById(R.id.btnPeriksaUlang);
        btnSelesai = (Button)findViewById(R.id.btnSelesai);

        listgejalas = SQLiteHelper.getInstance(this).getAllGejala();
        gejalaItemAdapter = new GejalaItemAdapter(listgejalas, this);


        btnUlang.setOnClickListener(this);
        btnSelesai.setOnClickListener(this);
        getBundle();
        chainProcess();
        resulData();
    }


    private void getBundle(){
        if (getIntent().hasExtra("selectedItems")){
            Bundle b = getIntent().getExtras();
            results = b.getStringArray("selectedItems");
        }
        else if (getIntent().hasExtra("RulebaseTidakAktiv")){
            Bundle b = getIntent().getExtras();
            resultRulebaseTdkAktiv = b.getStringArray("RulebaseTidakAktiv");
        }
    }

    private void chainProcess(){
        //getKodeRulebase
        ArrayList<String> strGetKodeRulebase = new ArrayList<>();
        boolean ada = false;
         Keputusan putusan = null;
        for(Keputusan keputusan : SQLiteHelper.getInstance(this).getAllKeputusan()){
            String[] kd = keputusan.getKode_gejala().split(",");
            int i = kd.length;
            int j =0;
            for(String kk : kd){
//                Log.d("Perbandngan1",kk);
                for(String code : results){
//                    Log.d("Perbandngan2",code);
                    if(kk.equals(code)) j++;
                }
            }
            if(i==j && j>0){
                ada = true;
                putusan = keputusan;
                break;
            }
        }
        if(ada && putusan != null) {
            hasMap = new HashMap<>();
            for(String code : results){
                if (hasMap.containsKey(putusan.getKode_gejala())) {
                    hasMap.get(putusan.getPid()).add(code);
                } else if (hasMap.containsKey(putusan.getPid())) {
                    hasMap.get(putusan.getPid()).add(code);
                }
                else {
                    hasMap.put(putusan.getPid(), strGetKodeRulebase);
                    strGetKodeRulebase.add(putusan.getKode_gejala().indexOf(",") + "");
                    strGetKodeRulebase.add(code);
                    hasMap.put(putusan.getPid(), strGetKodeRulebase);
                    System.out.println("rumus1" + strGetKodeRulebase);

                }
            }
            hasMap.put(putusan.getPid(), strGetKodeRulebase);
            System.out.println("rumus2" + strGetKodeRulebase);
        }
        resulData();
    }

    private void resulData() {
        Set<String> keySet = hasMap.keySet();
        float top = -1;
        String keyset = "";
        for (String key : keySet) {
            float ms = Float.parseFloat(hasMap.get(key).get(0));
            float ma = hasMap.get(key).size() - 1;
            float current = ma / ms;
            if (current >= top) {
                top = current;
                keyset = key;
                Log.d(TAG, "Hasil Konsuultasi Kode Penyakit: " + keyset);
                System.out.println(keyset);
            }
        }
        if (keyset == ""){
            keyset = "P10";
            System.out.println("Rule Tidak Akktiv" + keyset);
            Log.d(TAG, "RulebaseTidakAktiv: " + keyset + keySet);
        }
        if (keyset == null && keyset.equals("")){
            Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
            keyset="P10";
            System.out.println("Rule Tidak Akktiv NULL" + keyset);
        }

        //Saved Log hasil Konsultasi
        SQLiteDatabase sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        sqLiteDatabase.execSQL("INSERT INTO HasilDiagnosis (hasil_penyakit, hasil_gejala, hasil_solusi) VALUES('" +
                txtNamaPenyakit.getText().toString() + "', '" +
                txtListGejala .getText().toString() + "', '" +
                txtHasilSolusi.getText().toString() + "')");
        Log.d(TAG, "saveData: " + sqLiteDatabase + sqLiteHelper);




       final Penyakit penyakit = (Penyakit) SQLiteHelper.getInstance(this).getPenyakit(keyset);
        if (getIntent().hasExtra("selectedItemsName")) {
            Bundle b = getIntent().getExtras();
            String[] gjl_name = b.getStringArray("selectedItemsName");
            if (gjl_name.length > 0) {
                int i = 1;
                final StringBuilder aa = new StringBuilder();
                for (String a : gjl_name) {
                    aa.append(i).append(". ").append(a).append("\n");
                    i++;
                }
                txtListGejala.setText(aa);
            }

            //hasil rulebase
            txtNamaPenyakit.setText(penyakit.getNama_penyakit());
            txtHasilSolusi.setText(penyakit.getCara());

            final String strSavedGejala = txtNamaPenyakit.getText().toString().trim();

            System.out.println("result Kode Penyakit" + penyakit.getKode_penyakit());
            System.out.println("result nama penyakit" + penyakit.getNama_penyakit());
            System.out.println("result Solusi" + penyakit.getCara());




        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPeriksaUlang:
                Intent iUlang = new Intent(getApplicationContext(), Konsultasi.class);
                startActivity(iUlang);
                break;
            case R.id.btnSelesai:
                Intent iSelesai = new Intent(getApplicationContext(), IndexUtama.class);
                startActivity(iSelesai);
                Toast.makeText(getApplicationContext(), "Data berhasil Disimpan", Toast.LENGTH_SHORT).show();
                break;
            default:

        }

    }
}

