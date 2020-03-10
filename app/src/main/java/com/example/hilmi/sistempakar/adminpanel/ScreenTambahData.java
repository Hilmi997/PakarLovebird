package com.example.hilmi.sistempakar.adminpanel;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hilmi.sistempakar.R;
import com.example.hilmi.sistempakar.database.SQLiteHelper;
import com.example.hilmi.sistempakar.models.Gejala;
import com.example.hilmi.sistempakar.view.fragments.GejalaFragment;
import com.example.hilmi.sistempakar.view.fragments.KeputusanFragment;
import com.example.hilmi.sistempakar.view.fragments.PenyakitFragment;

import java.util.List;

public class ScreenTambahData extends AppCompatActivity {

    TextView tvID;
    TextInputLayout hintKode, hintName, hint3;
    TextInputEditText edtKode, edtNama, edtYA;
    Button btnAddGejala, btnAddPenyakit, btnAddKeputusan, btnAddPenyakitSolusi, btnAddSolusi;

    private SQLiteDatabase sqlTambaheGejala, sqlTambahPenyakit, sqlTammbahKEPUTUSAN;
    SQLiteHelper sqLiteHelper;
    protected Cursor cursor;
    String TAG = "";

    Button btnAddData;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_tambah_data);
        setTitle("Tambah Data");
        pd = new ProgressDialog(this);
        pd.setMessage("Waiting.....");



        sqLiteHelper = new SQLiteHelper(this);

        tvID = (TextView) findViewById(R.id.tv_tambah_ID);
        edtKode = (TextInputEditText) findViewById(R.id.edt_add_kode);
        edtNama = (TextInputEditText) findViewById(R.id.edt_add_nama);
        edtYA = (TextInputEditText) findViewById(R.id.edt_add_ya);
        hintKode = (TextInputLayout) findViewById(R.id.hint_add_kode);
        hintName = (TextInputLayout) findViewById(R.id.hint_add_nama);
        hint3 = (TextInputLayout) findViewById(R.id.hint_add_ya);
        btnAddData = (Button) findViewById(R.id.btn_add_DATA);




//====================FAB_ADD_DATA==================================================================
        if (getIntent().hasExtra("fab_add_gejala")) {
            setTitle("Form Tambah Gejala");
//            sqlUpdateGejala = sqLiteHelper.getReadableDatabase();
//            cursor = sqlUpdateGejala.rawQuery("SELECT * FROM Gejala WHERE kode_gejala = '"
//                    + getIntent().getStringExtra("fab_add_gejala") + "'", null);
//            cursor.moveToFirst();

            hint3.setVisibility(View.INVISIBLE);
            edtYA.setVisibility(View.INVISIBLE);
        } else if (getIntent().hasExtra("fab_add_penyakit")) {
            setTitle("Form Tambah Penyakit");
//                sqlUpdateGejala = sqLiteHelper.getReadableDatabase();
//                cursor = sqlUpdatePenyakit.rawQuery("SELECT * FROM Penyakit WHERE kode_penyakit = '"
//                        + getIntent().getStringExtra("fab_add_penyakit") + "'", null);
//                cursor.moveToFirst();

            hintKode.setHint("Kode Penyakit");
            hintName.setHint("Nama Penyakit");
            hint3.setHint("Solusi");
        } else if (getIntent().hasExtra("fab_add_rulebase")) {
            setTitle("Form Tambah RuleBase");
            hintKode.setHint("Kode Penyakit ");
            hintName.setHint("Kode IF GEJALA");
            hint3.setVisibility(View.INVISIBLE);
            hint3.setHint("Jika Tidak");
        }


        final List<String> tambahGejalaList = sqLiteHelper.getListUpdateGejala();
        final List<String> tambahPenyakit = sqLiteHelper.getListUpdatePenyakit();
        final List<String> tambahKeputusan = sqLiteHelper.getListUpdateKeputusan();



        if (cursor==null) {
             sqlTambaheGejala = sqLiteHelper.getWritableDatabase();
                getIntent().hasExtra("fab_add_gejala");
        }
        if (cursor==null) {
            getIntent().hasExtra("fab_add_penyakit");
        }
        if (cursor==null){
            getIntent().hasExtra("fab_add_rulebase");
        }





        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getIntent().hasExtra("fab_add_gejala")) {
                    //write database
                    SQLiteDatabase sql_add_gejala = sqLiteHelper.getWritableDatabase();
                        if (edtKode.getText().toString().isEmpty() && edtNama.getText().toString().isEmpty()) {
                            edtKode.setError("Data Belum Lengkap");
                            Toast.makeText(getApplicationContext(), "Data Belum Lengkap", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onClick: " + sql_add_gejala );
                    } else {
                            int x = 0;
                            for(String a : tambahGejalaList){
                                if(a.equals(edtKode.getText().toString())){
                                    pd.getSecondaryProgress();
                                    edtKode.setError("Data Sudah Tersedia");
//                                    Toast.makeText(getApplicationContext(), "Data sudah tersedia", Toast.LENGTH_SHORT).show();
                                    x++;
                                }
                            }
                            if(x>0) return;

                        sql_add_gejala.execSQL("INSERT INTO Gejala (kode_gejala, nama_gejala) VALUES('" +
                                edtKode.getText().toString() + "', '" +
                                edtNama.getText().toString() + "')");

                        Toast.makeText(getApplicationContext(), "Data Gejala berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                        Snackbar.make(view, "Berhasil", Snackbar.LENGTH_LONG).show();
                        Log.d(TAG, "btnTambahData: " + sql_add_gejala + edtKode + edtNama);
                        System.out.print("tambah data" + sql_add_gejala);


                        pd.dismiss();
                            Intent i = new Intent(ScreenTambahData.this, ScreenAdmin.class);
                            startActivity(i);

                        return;
                    }
                } else if (getIntent().hasExtra("fab_add_penyakit")) {
                    int x = 0;
                    SQLiteDatabase sql_addPenyakit = sqLiteHelper.getWritableDatabase();
                    if (edtKode.getText().toString().isEmpty() && edtNama.getText().toString().isEmpty()){
                        edtKode.setError("Data Tidak Boleh Kosong");
//                        Toast.makeText(getApplicationContext(), "Data Belum Lengkap", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        for (String penyakit : tambahPenyakit) {
                            Toast.makeText(getApplicationContext(), "Data sudah tersedia", Toast.LENGTH_SHORT).show();
                            if (penyakit.equals((edtKode.getText().toString()))) {
                                edtKode.setError("Data Sudah Tersedia");
                                Toast.makeText(getApplicationContext(), "Data sudah tersedia", Toast.LENGTH_SHORT).show();
                                x++;
                            }
                        }
                        if (x > 0) return;
                        sql_addPenyakit.execSQL("INSERT INTO Penyakit (kode_penyakit, nama_penyakit, solusi) VALUES('" +
                                edtKode.getText().toString() + "', '" +
                                edtNama.getText().toString() + "', '" +
                                edtYA.getText().toString() + "')");

                        Toast.makeText(getApplicationContext(), "Data Penyakit berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "tambahPenyakit: " + tambahPenyakit);

                        pd.setProgress(9000);
                        Intent i = new Intent(ScreenTambahData.this, ScreenAdmin.class);
                        startActivity(i);
                        return;
                    }
                }
                //TABLE KEPUTUSAN
                else if (getIntent().hasExtra("fab_add_rulebase")) {
                    int x = 0;
                    SQLiteDatabase sql_addKeputusan = sqLiteHelper.getWritableDatabase();
                    if (edtKode.getText().toString().isEmpty() && edtNama.getText().toString().isEmpty()){
                        edtKode.setError("Data Sudah Tersedia");
                        Toast.makeText(getApplicationContext(), "Data Belum Lengkap", Toast.LENGTH_SHORT).show();
                    }else {
                        for (String keputusan : tambahKeputusan) {
                            if (keputusan.equals(edtKode.getText().toString())) {
                                Toast.makeText(getApplicationContext(), "Data sudah tersedia", Toast.LENGTH_SHORT).show();
                                x++;
                            }
                        }
                        if (x > 0) return;

                        sql_addKeputusan.execSQL("INSERT INTO Keputusan (kode_penyakit, kode_gejala) VALUES('" +
                                edtKode.getText().toString() + "', '" +
                                edtNama.getText().toString() + "')");
                        Log.d(TAG, "addKeputusan: " + sql_addKeputusan + edtKode + edtNama);
                        System.out.print("tambah data" + sql_addKeputusan);

                        pd.setProgress(9000);
                        pd.getSecondaryProgress();
                        pd.dismiss();
                        Intent i = new Intent(ScreenTambahData.this, ScreenAdmin.class);
                        startActivity(i);
                    }
                }
            }
        });
    }
}
