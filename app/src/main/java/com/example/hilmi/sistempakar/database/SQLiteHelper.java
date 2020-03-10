package com.example.hilmi.sistempakar.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.hilmi.sistempakar.models.Gejala;
import com.example.hilmi.sistempakar.models.LogKonsultasiModels;
import com.example.hilmi.sistempakar.models.User;
import com.example.hilmi.sistempakar.models.Penyakit;
import com.example.hilmi.sistempakar.models.Keputusan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class SQLiteHelper extends SQLiteOpenHelper {
    static SQLiteHelper sqh;
    public static String TAG = "SqliteHelper";
    public static SQLiteDatabase sqLiteDatabase;
    public static Cursor cursor;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "db_lovebird";

    //TABLE
    public static final String TABLE_PENYAKIT = "Penyakit";
    public static final String TABLE_GEJALA = "Gejala";
    public static final String TABLE_KEPUTUSAN = "Keputusan";
    public static final String TABLE_HASIL_DIAGNOSIS = "HasilDiagnosis";

    public static final String TABLE_USERS = "User";

    //KOLOM PENYAKIT
    public static final String KEY_ID = "id";
    public static final String KEY_KODE_PENYAKIT = "kode_penyakit";
    public static final String KEY_NAMA_PENYAKIT = "nama_penyakit";

    public static final String KEY_NAMA = "nama";

    //KOLOM GEJALA
    public static final String KEY_KODE_GEJALA = "kode_gejala";
    public static final String KEY_NAMA_GEJALA = "nama_gejala";

    //KOLOM solusi
    public static final String KEY_NAMA_SOLUSI = "solusi";

    //KOLOM TBL LOGIN
    public static final String KEY_COLUMN_USERNAME = "username";
    public static final String KEY_COLUMN_PASSWORD = "password";
    public static final String KEY_COLUMN_FULLNAME = "fullname";

    //TABLE_HASIIL_DIAGNOSIS
    public static final String KEY_KOLUM_HASIL_PENYAKIT = "hasil_penyakit";
    public static final String KEY_KOLUM_HASIL_GEJALA = "hasil_gejala";
    public static final String KEY_KOLUM_HASIL_SOLUSI = "hasil_solusi";




//    public static final String KEY_CARA = "cara";

    public static SQLiteHelper getInstance(Context context) {
        if (sqh == null) {
            sqh = new SQLiteHelper(context);
        }
        return sqh;
    }

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PENYAKIT_TBL = "CREATE TABLE " + TABLE_PENYAKIT + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_KODE_PENYAKIT + " TEXT NOT NULL,"
                + KEY_NAMA_PENYAKIT + " TEXT,"
                + KEY_NAMA_SOLUSI + " TEXT" + ")";

        String CREATE_GEJALA_TBL = "CREATE TABLE " + TABLE_GEJALA + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_KODE_GEJALA + " TEXT NOT NULL,"
                + KEY_NAMA_GEJALA + " TEXT" + ")";

        String CREATE_KEPUTUSAN_TBL = "CREATE TABLE " + TABLE_KEPUTUSAN + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_KODE_PENYAKIT + " TEXT,"
                + KEY_KODE_GEJALA + " TEXT" + ")";

        String CREATE_TBL_LOGIN = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_COLUMN_USERNAME + " TEXT,"
                + KEY_COLUMN_PASSWORD + " TEXT,"
                + KEY_COLUMN_FULLNAME + " TEXT" + ")";

        String CREATE_TBL_HASIL_DIAGNOSIS = "CREATE TABLE " + TABLE_HASIL_DIAGNOSIS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_KOLUM_HASIL_PENYAKIT + " TEXT,"
                + KEY_KOLUM_HASIL_GEJALA + " TEXT,"
                + KEY_KOLUM_HASIL_SOLUSI + " TEXT" + ")";

        db.execSQL(CREATE_PENYAKIT_TBL);
        db.execSQL(CREATE_GEJALA_TBL);
        db.execSQL(CREATE_KEPUTUSAN_TBL);
        db.execSQL(CREATE_TBL_LOGIN);
        db.execSQL(CREATE_TBL_HASIL_DIAGNOSIS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GEJALA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PENYAKIT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KEPUTUSAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        onCreate(db);
    }

    public Cursor getData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        SQLiteDatabase database2 = getWritableDatabase();
        return database.rawQuery(sql, null);
    }

    //======delete data==================

//===========akhir delete data==========
//=============================table=====================================

    public Penyakit getPenyakit(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PENYAKIT, new String[]{KEY_KODE_PENYAKIT, KEY_NAMA_PENYAKIT, KEY_NAMA_SOLUSI}, KEY_KODE_PENYAKIT + "=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Penyakit penyakit = new Penyakit(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        return penyakit;
    }



    //GET HASIL DIAGNOSIS TABLE PENYAKIT
    //get hasilDiagnosis.java
    public Penyakit getPenyakit(String keyset) {
        SQLiteDatabase db = this.getReadableDatabase();

       Cursor cursor = db.query(TABLE_PENYAKIT, new String[]{KEY_ID, KEY_KODE_PENYAKIT, KEY_NAMA_PENYAKIT, KEY_NAMA_SOLUSI}, KEY_KODE_PENYAKIT + "=?", new String[]{keyset}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Penyakit penyakit = new Penyakit(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        Log.d(TAG, "getPenyakit: " + cursor + db);
        System.out.println("select" + cursor + db + penyakit);
        return penyakit;
    }



    //Screen Hasil Konsultasi
    public Gejala getGejala(String keysetGejala) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_GEJALA, new String[]{KEY_KODE_GEJALA, KEY_NAMA_GEJALA}, KEY_KODE_GEJALA + "=?", new String[]{keysetGejala}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Gejala gejala = new Gejala(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        return gejala;
    }


    //result penyakit
    public List<Penyakit> getAllHama() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Penyakit> penyakits = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_PENYAKIT;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Penyakit penyakit = new Penyakit(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                penyakits.add(penyakit);
            } while (cursor.moveToNext());
        }
        return penyakits;
    }

    //result penyakit di screen hasil diagnosis
    public List<Gejala> getAllGejalaResult() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Gejala> gejalaResult = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_GEJALA;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Gejala gejala = new Gejala(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                gejalaResult.add(gejala);
            } while (cursor.moveToNext());
        }
        return gejalaResult;
    }

    //---------------------------TABLE--------------------------------------------------------------
    //PENYAKIT
    public void addPENYAKIT(Penyakit penyakit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_KODE_PENYAKIT, penyakit.getKode_penyakit());
        cv.put(KEY_NAMA_PENYAKIT, penyakit.getNama_penyakit());
        cv.put(KEY_NAMA_SOLUSI, penyakit.getCara());
        db.insert(TABLE_PENYAKIT, null, cv);
        db.close();
    }

    //GEJALA
    public void addGejala(Gejala gejala) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
            cv.put(KEY_KODE_GEJALA, gejala.getGid());
            cv.put(KEY_NAMA_GEJALA, gejala.getGejala());
            db.insert(TABLE_GEJALA, null, cv);
            db.close();
    }


    //KEPUTUSAN
    public void addKeputusan(Keputusan keputusan) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(KEY_KODE_GEJALA, keputusan.getKode_gejala());
        cv.put(KEY_KODE_PENYAKIT, keputusan.getPid());
        db.insert(TABLE_KEPUTUSAN, null, cv);
        db.close();
    }

    public void addUSER(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_COLUMN_USERNAME, user.getUsername());
        cv.put(KEY_COLUMN_PASSWORD, user.getPassword());
        cv.put(KEY_COLUMN_FULLNAME, user.getFullname());
        db.insert(TABLE_USERS, null, cv);
        db.close();
    }

    public void addLogHasilDiagnosis(LogKonsultasiModels resultsavedlog) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_KOLUM_HASIL_PENYAKIT, resultsavedlog.getResultPenyakit());
        cv.put(KEY_KOLUM_HASIL_GEJALA, resultsavedlog.getResultGejala());
        cv.put(KEY_KOLUM_HASIL_SOLUSI, resultsavedlog.getResultSolusi());
        db.insert(TABLE_HASIL_DIAGNOSIS, null, cv);
        db.close();
    }
//------------------AKHIR TABLE---------------------------------------------------------------------

    public Gejala getGejala(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_GEJALA, new String[]{KEY_KODE_GEJALA, KEY_NAMA_GEJALA}, KEY_KODE_GEJALA + "=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Gejala gejala = new Gejala(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        return gejala;
    }

    public List<Gejala> getAllGejala() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Gejala> gejalas = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_GEJALA;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Gejala gejala = new Gejala(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                gejalas.add(gejala);
            } while (cursor.moveToNext());
        }

        return gejalas;
    }


//    public Keputusan getKeputusan(int id){
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_KEPUTUSAN, new String[] {KEY_ID,KEY_KODE_GEJALA, KEY_KODE_PENYAKIT}, KEY_ID + "=?", new String[] { String.valueOf(id)}, null,null,null);
//
//        if(cursor != null)
//            cursor.moveToFirst();
//
//        Keputusan putusan = new Keputusan(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
//
//        return putusan;
//    }


//===================get result hasilkonsultasi.java===========================================
    public List<Keputusan> getAllKeputusan() {
        String strKode_gejala= null;
        SQLiteDatabase db = this.getReadableDatabase();
        List<Keputusan> keputusans = new ArrayList<>();

//        Cursor cursor = db.query(true, TABLE_KEPUTUSAN, new String[]{KEY_KODE_PENYAKIT,KEY_KODE_GEJALA},
//                KEY_KODE_GEJALA + " LIKE ?", new String[]{ KEY_KODE_GEJALA+ "%" },
//                null, null, null, null);
        String selectQuery = "SELECT * FROM " + TABLE_KEPUTUSAN;
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.d(TAG, "getAllKeputusan: " + cursor + db);
        if(cursor.moveToFirst()) {
            do {
                Keputusan keputusan = new Keputusan(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                keputusans.add(keputusan);
            }while (cursor.moveToNext());
        }
        return keputusans;
    }


//==================TABLE=======================================================

    public List<String> getListStringGejala() {
        List<String> kd_gejala_list = new ArrayList<>();
        List<String> nm_gejala_list = new ArrayList<>();

        cursor = getReadableDatabase().rawQuery("SELECT * FROM Gejala", null);
        cursor.moveToFirst();
        for (int count = 0; count < cursor.getCount(); count++) {
            cursor.moveToPosition(count);//Berpindah Posisi dari no index 0 hingga no index terakhir
            kd_gejala_list.add(cursor.getString(0));//Menambil Data Dari Kolom 1 (Nama)
            nm_gejala_list.add(cursor.getString(1));//Menambil Data Dari Kolom 1 (Nama)

            System.out.print("hasil" + kd_gejala_list);
        }
        return kd_gejala_list;
    }


    public String[][] selectGEJALA() {
        try {
            String arrData[][] = null;
            cursor = sqLiteDatabase.rawQuery("select * from Gejala ", null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getCount()][cursor.getColumnCount()];
                    int i = 0;
                    do {
                        arrData[i][0] = cursor.getString(0);
                        arrData[i][1] = cursor.getString(1);
                        i++;
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            return arrData;
        } catch (Exception e) {
            return null;
        }
    }


    //----------------LIST STRING DATA-----------------------------------------------------------------
    public List<String> getTableGejala() {
        List<String> labels = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_GEJALA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }

    public List<String> getListGejala() {
        List<String> labels = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_GEJALA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }

    public List<String> getListPenyakit() {
        List<String> labels = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PENYAKIT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }

    public List<String> getListKeputusan() {
        List<String> labels = new ArrayList<String>();
        List<String> labels2 = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_KEPUTUSAN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
                labels2.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }
//=============akhir list===================================================================

    public List<String> getTablePenyakit() {
        List<String> labels = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PENYAKIT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return labels;
    }

    public List<String> getTableKeputusan() {
        List<String> labels = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_KEPUTUSAN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }


    public String[][] selectPENYAKIT() {
        try {
            String arrData[][] = null;
            cursor = sqLiteDatabase.rawQuery("SELECT  * from Penyakit", null, null);

            if (cursor.moveToFirst()) {
                arrData = new String[cursor.getCount()][cursor.getColumnCount()];
                int i = 0;
                do {
                    arrData[i][0] = cursor.getString(0);
                    arrData[i][1] = cursor.getString(1);
                    i++;
                }
                while (cursor.moveToNext());
            }
            cursor.close();
            return arrData;
        } catch (Exception e) {
            return null;
        }
    }


//=============TEST data===============================================================

    public ArrayList<HashMap<String, String>> getAllData() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + TABLE_GEJALA;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(KEY_KODE_GEJALA, cursor.getString(0));
                map.put(KEY_NAMA_GEJALA, cursor.getString(1));
                wordList.add(map);
            } while (cursor.moveToNext());
        }

        Log.e("select sqlite ", "" + wordList);

        database.close();
        return wordList;
    }

    public void update(String kode_gejala, String nama_gejala) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_GEJALA + " SET "
                + KEY_KODE_GEJALA + "='" + kode_gejala + "', "
                + KEY_NAMA_GEJALA + "='" + nama_gejala + "'"
                + " WHERE " + KEY_KODE_GEJALA + "=" + "'" + kode_gejala + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void delete(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        String updateQuery = "DELETE FROM " + TABLE_GEJALA + " WHERE " + KEY_KODE_GEJALA + "=" + "'" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }
//====================================AHIR=======================


    public boolean checkUserExist(String username, String password) {
        String[] columns = {"username"};

        sqLiteDatabase = sqh.getReadableDatabase();
        sqLiteDatabase = sqh.getWritableDatabase();

        String selection = "username=? and password =?";
        String[] selectionArgs = {username, password};
        Cursor cursor = sqLiteDatabase.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        close();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

//---------------------------------get LIST UPDATE DATA--------------------------------------------
    //UPDATE.JAVA
//---untuk Get Detil Update Data...
    // column Index, baca Field pertama mulai dari =0
    public List<String> getListUpdateGejala(){
        List<String> ID = new ArrayList<>();
        List<String> listKODE = new ArrayList<>();
        List<String> listGejala = new ArrayList<>();

        cursor = getWritableDatabase().rawQuery("SELECT * FROM Gejala", null, null);
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            do {
                listKODE.add(cursor.getString(1));
                listGejala.add(cursor.getString(2));
                Log.d(TAG, "getListUpdateGejala: " + listKODE + listGejala );
            }
            while (cursor.moveToNext());
        }
        return listKODE;
    }

    public List<String> getListUpdatePenyakit(){
        List<String> listKODE = new ArrayList<>();
        List<String> listPenyakit = new ArrayList<>();
        List<String> listcara = new ArrayList<>();

        cursor = getWritableDatabase().rawQuery("SELECT * FROM Penyakit", null, null);
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            do {
                listKODE.add(cursor.getString(0));
                listPenyakit.add(cursor.getString(1));
                listcara.add(cursor.getString(2));
                Log.d(TAG, "getListUpdatePenyakit: " + listKODE + listPenyakit );
            }
            while (cursor.moveToNext());
        }
        return listKODE;
    }


    public List<String> getListUpdateKeputusan(){
        List<String> listKODE = new ArrayList<>();
        List<String> listKeputusan = new ArrayList<>();

        cursor = getWritableDatabase().rawQuery("SELECT * FROM Keputusan", null, null);
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            do {
                listKODE.add(cursor.getString(0));
                listKeputusan.add(cursor.getString(1));
                Log.d(TAG, "getListUpdatePenyakit: " + listKODE + listKeputusan );
            }
            while (cursor.moveToNext());
        }
        return listKODE;
    }


    public List<String> getListUpdateAkun(){
        List<String> username = new ArrayList<>();
        List<String> password = new ArrayList<>();
        List<String> fullname = new ArrayList<>();


        cursor = getWritableDatabase().rawQuery("SELECT * FROM User", null, null);
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            do {
                username.add(cursor.getString(0));
                password.add(cursor.getString(1));
                fullname.add(cursor.getString(1));

                Log.d(TAG, "getListUpdatePenyakit: " + username + password + fullname );
            }
            while (cursor.moveToNext());
        }
        return username;
    }

    public List<String> getListHistoriKosultasi(){
        List<String> listKODE = new ArrayList<>();
        List<String> listHistoriKonsultasi = new ArrayList<>();

        cursor = getWritableDatabase().rawQuery("SELECT * FROM HasilDiagnosis", null, null);
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            do {
                listKODE.add(cursor.getString(0));
                listHistoriKonsultasi.add(cursor.getString(1));
                Log.d(TAG, "getListUpdateGejala: " + listKODE + listHistoriKonsultasi );
            }
            while (cursor.moveToNext());
        }
        return listKODE;
    }


    public List<String> getListTestData(){
        List<String> listKODE = new ArrayList<>();
        List<String> listPENYAKIT = new ArrayList<>();


        cursor = getWritableDatabase().rawQuery("SELECT * FROM Gejala", null, null);
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            do {
                listKODE.add(cursor.getString(0));
                listPENYAKIT.add(cursor.getString(1));
                Log.d(TAG, "getTableMaster: " + listKODE + listPENYAKIT );
            }
            while (cursor.moveToNext());
        }

//        while (!cursor.isAfterLast()) {
//            listKODE.add(cursor.getString(0));
//            listPENYAKIT.add(cursor.getString(1));
//            listSOLUSI.add(cursor.getString(4));
//
//            cursor.moveToNext();
//            Log.d(TAG, "getTableMaster: " + listKODE + listPENYAKIT + listSOLUSI);
//        }
        return listKODE;
//---------------------AKHIR LIST UPDATE DATA-------------------------------------------------------
    }
}






