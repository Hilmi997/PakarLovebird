package com.example.hilmi.sistempakar.adminpanel;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hilmi.sistempakar.R;
import com.example.hilmi.sistempakar.baseModels.Pager;
import com.example.hilmi.sistempakar.database.SQLiteHelper;
import com.example.hilmi.sistempakar.view.fragments.GejalaFragment;
import com.example.hilmi.sistempakar.view.fragments.KeputusanFragment;
import com.example.hilmi.sistempakar.view.fragments.PenyakitFragment;

import java.util.List;

public class ScreenUpdate extends AppCompatActivity {


    TextInputLayout hintID, hintKode, hintNama, hintYa, hintFullName;
    TextInputEditText edtID, edtKode,edtNama, edtYA, edtFullName;
    Button btnUpdateGejala, btnUpdatePenyakit, btnUpdateKeputusan;
    ProgressDialog pd;



    String TAG = "";

    protected Cursor cursor;
    private SQLiteDatabase sqlGEJALA, sqlPENYAKIT, sqlKEPUTUSAN, sqlPENYAKITSOLUSI, sqlSOLUSI;
    SQLiteHelper sqLiteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_update);
        setTitle("Update Data");
        pd = new ProgressDialog(this);
        pd.setMessage("Waiting....");


        sqLiteHelper = new SQLiteHelper(getApplicationContext());


        edtID = (TextInputEditText) findViewById(R.id.edt_update_id);
        edtKode = (TextInputEditText) findViewById(R.id.edt_update_kode);
        edtNama = (TextInputEditText) findViewById(R.id.edt_update_name);
        edtYA = (TextInputEditText) findViewById(R.id.edt_update_Ya);
        edtFullName = (TextInputEditText) findViewById(R.id.edt_update_fullName);



        hintID = (TextInputLayout)findViewById(R.id.hint_update_id);
        hintKode = (TextInputLayout) findViewById(R.id.hint_update_kode);
        hintNama = (TextInputLayout) findViewById(R.id.hint_update_name);
        hintYa = (TextInputLayout) findViewById(R.id.hint_update_ya);
        hintFullName = (TextInputLayout) findViewById(R.id.hint_update_fullName);





        //PENYAKIT
        if(getIntent().hasExtra("update_penyakit")){
            setTitle("Form Penyakit");
            sqlPENYAKIT = sqLiteHelper.getWritableDatabase();
            cursor = sqlPENYAKIT.rawQuery("SELECT * FROM Penyakit WHERE id= '"
                    + getIntent().getStringExtra("update_penyakit") + "'", null);
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                cursor.moveToPosition(0);
                hintKode.setHint("Kode Penyakit");
                hintNama.setHint("Nama Penyakit");
                hintYa.setHint("Solusi");
                edtID.setText(cursor.getString(0).toString());
                edtKode.setText(cursor.getString(1).toString());
                edtNama.setText(cursor.getString(2).toString());
                edtYA.setText(cursor.getString(3).toString());

                hintFullName.setVisibility(View.INVISIBLE);
                edtFullName.setVisibility(View.INVISIBLE);
                Log.d(TAG, "sqLitePENYAKIT: " + sqlPENYAKIT + cursor);
                System.out.print(edtKode);
                System.out.print(edtNama);


            }else {
                pd.getSecondaryProgress();
                Intent i = new Intent(getApplicationContext(), ScreenAdmin.class);
                startActivity(i);
            }
        }
        //GEJALA
        else if(getIntent().hasExtra("update_gejala")){

            sqlGEJALA = sqLiteHelper.getReadableDatabase();
            cursor = sqlGEJALA.rawQuery("SELECT * FROM Gejala WHERE kode_gejala = '"
                    + getIntent().getStringExtra("update_gejala") + "'", null);
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                cursor.moveToPosition(0);
                setTitle("Form Gejala");
                hintKode.setHint("Kode Gejala");
                hintNama.setHint("Nama Gejala");

                edtID.setText(cursor.getString(0));
                edtKode.setText(cursor.getString(1));
                edtNama.setText(cursor.getString(2));

                System.out.print(edtKode);
                System.out.print(edtNama);

                //disable
                edtYA.setVisibility(View.INVISIBLE);
                hintYa.setVisibility(View.INVISIBLE);
                hintFullName.setVisibility(View.INVISIBLE);
                edtFullName.setVisibility(View.INVISIBLE);
                Log.d(TAG, "onCreate: " + sqlGEJALA + sqLiteHelper);
                Log.e(TAG, "onCreate: " + sqlGEJALA );
            }
        }

        else if (getIntent().hasExtra("update_keputusan")){
            setTitle("Form Keputusan");
            sqlKEPUTUSAN = sqLiteHelper.getReadableDatabase();
            cursor = sqlKEPUTUSAN.rawQuery("SELECT * FROM Keputusan WHERE id= '"
                    + getIntent().getStringExtra("update_keputusan") + "'", null);
//        cursor = form_lihat_gejala.rawQuery("SELECT kode_penyakit, nama_penyakit FROM tbl_penyakit INNER JOIN tbl_solusi ON kode_penyakit = kode_solusi ", null);
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                cursor.moveToPosition(0);

                //alias
                hintKode.setHint("Id");
                hintNama.setHint("Kode Penyakit");
                hintYa.setHint("Kode Gejala");

                edtKode.setText(cursor.getString(0).toString());
                edtNama.setText(cursor.getString(1).toString());
                edtYA.setText(cursor.getString(2).toString());


                edtYA.setAllCaps(true);
                //disable
                hintID.setVisibility(View.INVISIBLE);
                edtID.setVisibility(View.INVISIBLE);
                hintFullName.setVisibility(View.INVISIBLE);
                edtFullName.setVisibility(View.INVISIBLE);
                Log.d(TAG, "penyakit: " + sqlKEPUTUSAN + cursor);
                System.out.print(edtKode);
                System.out.print(edtNama);
                System.out.print(edtYA);
            }
        }

        else if(getIntent().hasExtra("update_akun")){
            sqlSOLUSI = sqLiteHelper.getWritableDatabase();
            cursor = sqlSOLUSI.rawQuery("SELECT * FROM User WHERE id= '"
                    + getIntent().getStringExtra("update_akun") + "'", null);
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                cursor.moveToPosition(0);
                setTitle("Form Akun");
                hintKode.setHint("ID");
                hintNama.setHint("Usernmae");
                hintYa.setHint("Password");
                edtFullName.setHint("FullName");
                edtKode.setFocusableInTouchMode(true);
                edtKode.setKeyListener(null);
                edtKode.setText(cursor.getString(0).toString());
                edtNama.setText(cursor.getString(1).toString());
                edtYA.setText(cursor.getString(2).toString());
                edtFullName.setText(cursor.getString(3).toString());

                edtID.setVisibility(View.INVISIBLE);
                Log.d(TAG, "sqlSolusi: " + sqlSOLUSI + cursor);
                System.out.print(edtKode);
                System.out.print(edtNama);
            }
        }





        final List<String> updateGejala = sqLiteHelper.getListUpdateGejala(); updateGejala.add(cursor.getString(0));
        final List<String> updatePenyakit = sqLiteHelper.getListUpdatePenyakit();
        final List<String> updateKeputusan = sqLiteHelper.getListUpdateKeputusan();


        btnUpdateGejala = (Button) findViewById(R.id.update_gejala);
        btnUpdateGejala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.show();
                if(getIntent().hasExtra("update_penyakit")){
                    setTitle("Form Update Penyakit");
                    if (edtKode.getText().toString().isEmpty() && edtNama.getText().toString().isEmpty()){
                        edtKode.setError("Data Tidak Boleh Kosong");
                    }
                    else {
                        sqlPENYAKIT = sqLiteHelper.getWritableDatabase();
                        sqlPENYAKIT.execSQL("UPDATE Penyakit set nama_penyakit='" + edtNama.getText().toString() +
                                "', nama_penyakit='" + edtNama.getText().toString() +
                                "', solusi='" + edtYA.getText().toString() +
                                "' WHERE id='" + edtID.getText().toString() + "'");
                        Toast.makeText(getApplicationContext(), "Data Penyakit Berhasil Di Update", Toast.LENGTH_LONG).show();
                        System.out.print("update:" + edtKode + edtNama);
                        Log.d(TAG, "update " + edtKode);
                        Log.d(TAG, "onClick: " + edtNama);

                        pd.setProgress(7000);

                        pd.setProgress(9000);
                        Intent i = new Intent(ScreenUpdate.this, ScreenAdmin.class);
                        startActivity(i);

                    }
                }
                else if (getIntent().hasExtra("update_gejala")){
                    setTitle("Form Update Gejala");
                    SQLiteDatabase  sqlUpdateGejala = sqLiteHelper.getWritableDatabase();
                    if (edtKode.getText().toString().isEmpty() && edtNama.getText().toString().isEmpty()) {
                        edtKode.setError("Data Belum Lengkap");
                        Toast.makeText(getApplicationContext(), "Data Belum Lengkap", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onClick: " + sqlUpdateGejala );
                    } else {

                        sqlUpdateGejala.execSQL("UPDATE Gejala SET nama_gejala='" + edtNama.getText().toString() +
                                "' WHERE id='" + edtID.getText().toString() + "'");
                        Toast.makeText(getApplicationContext(), "Data Gejala Berhasil Di Update", Toast.LENGTH_SHORT).show();
                        System.out.print("update:" + edtKode + edtNama);
                        Log.d(TAG, "update " + edtKode);
                        Log.d(TAG, "onClick: " + edtNama);


                        pd.setProgress(9000);
                        Intent i = new Intent(ScreenUpdate.this, ScreenAdmin.class);
                        startActivity(i);
                    }

                }

                else if (getIntent().hasExtra("update_keputusan")){
                    setTitle("Form Update Rulebase");
                    SQLiteDatabase  sqlKEPUTUSAN = sqLiteHelper.getWritableDatabase();
                    if(edtNama.getText().toString().isEmpty() && edtKode.getText().toString().isEmpty()){
                        edtKode.setError("Data belum lengkap....");
                    }
                    sqlKEPUTUSAN.execSQL("UPDATE Keputusan set kode_penyakit='" + edtNama.getText().toString()
                            + "', kode_gejala='" + edtYA.getText().toString()
                            + "' WHERE id='" + edtKode.getText().toString() + "'");
                    Toast.makeText(getApplicationContext(), "Data Rulebase Berhasil Di Update", Toast.LENGTH_LONG).show();
                    System.out.print("update:" + edtKode + edtNama);
                    Log.d(TAG, "update " + edtKode);
                    Log.d(TAG, "onClick: " + edtNama);

                    pd.setProgress(9000);
                    Intent i = new Intent(ScreenUpdate.this, ScreenAdmin.class);
                    startActivity(i);
                }
                else if(getIntent().hasExtra("update_akun")){
                    SQLiteDatabase sqlUpdatAkun = sqLiteHelper.getWritableDatabase();
                    sqlUpdatAkun.execSQL("UPDATE User set password='" + edtYA.getText().toString()
                            +"', username='" + edtNama.getText().toString()
                            +"', fullname='" + edtFullName.getText().toString()
                            + "' WHERE id='" + edtKode.getText().toString() + "'");
                    System.out.print("update:" + sqlUpdatAkun + sqLiteHelper + edtKode + edtNama + edtYA);

                    Toast.makeText(getApplicationContext(), "Data Berhasil Di Update" + sqlUpdatAkun, Toast.LENGTH_SHORT).show();
                    sqLiteHelper.close();

                    pd.setProgress(9000);
                    Intent i = new Intent(ScreenUpdate.this, ScreenAdmin.class);
                    startActivity(i);
                }
            }
        });
    }




}
