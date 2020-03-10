package com.example.hilmi.sistempakar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hilmi.sistempakar.adminpanel.ScreenAdmin;
import com.example.hilmi.sistempakar.database.SQLiteHelper;
import com.example.hilmi.sistempakar.database.SessionHelper;
import com.example.hilmi.sistempakar.models.Keputusan;
import com.example.hilmi.sistempakar.models.User;

public class ScreenLogin extends AppCompatActivity {

    TextInputEditText edtUser, edtPass;
    Button btnLogin;

    SQLiteHelper sqLiteHelper;
    String TAG = "LOGIN";
    ProgressDialog pd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_login);

        setTitle("Login Admin");
        pd = new ProgressDialog(this);
        pd.setMessage("Loading......");



        sqLiteHelper = SQLiteHelper.getInstance(this);


        btnLogin = (Button)findViewById(R.id.btnLogin);
        edtUser = (TextInputEditText)findViewById(R.id.edtUsername);
        edtPass = (TextInputEditText)findViewById(R.id.edtPassword);




        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(ScreenLogin.this, ScreenAdmin.class);
//                startActivity(i);

                prosess();
            }
        });
    }
    public void prosess(){
        String keyUserId = edtUser.getText().toString();
        String keyPass = edtPass.getText().toString();
        pd.show();

        boolean isExist = sqLiteHelper.checkUserExist(edtUser.getText().toString(), edtPass.getText().toString());
        if (isExist){
            pd.getSecondaryProgress();
            Intent intent = new Intent(getApplicationContext(), ScreenAdmin.class);
            intent.putExtra("username", edtUser.getText().toString());
            Toast.makeText(getApplicationContext(), "Berhasil Login", Toast.LENGTH_LONG).show();
            startActivity(intent);
            Log.d(TAG, "prosess: " + isExist);
        }
        else if (keyUserId.isEmpty() & keyPass.isEmpty()) {
            pd.hide();
            edtUser.setError("Data Tidak Boleh Kosong...!");
            Snackbar.make(findViewById(R.id.edtUsername), "Data Tidak boleh kosong !!!....", Snackbar.LENGTH_LONG).show();
        }
        else {
            pd.hide();
            edtPass.setError("Silahkan Cek Username dan Password....!!!");
            Snackbar.make(findViewById(R.id.edtPassword), "Silahkan Cek Username dan Password....", Snackbar.LENGTH_LONG).show();
        }
    }}

