package com.example.hilmi.sistempakar;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hilmi.sistempakar.database.SessionHelper;
import com.example.hilmi.sistempakar.database.SQLiteHelper;
import com.example.hilmi.sistempakar.models.Gejala;
import com.example.hilmi.sistempakar.models.Keputusan;
import com.example.hilmi.sistempakar.models.Penyakit;
import com.example.hilmi.sistempakar.models.Solusi;
import com.example.hilmi.sistempakar.models.User;
import com.google.android.gms.ads.MobileAds;

public class FlashScreen extends AppCompatActivity {

    private int waktu_flash = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileAds.initialize(this, "ca-app-pub-5302278666494604/6280307780");
        setContentView(R.layout.flash_screen);

        if(SessionHelper.getInstance(this).getAppFirstTime()){
            if (SessionHelper.getInstance(this) == null){
                SQLiteHelper.getInstance(this).addPENYAKIT(new Penyakit("P09","TIDAK ADA RULEBASE", "RULEBASE TIDAK AKTIV"));
            }
            SQLiteHelper.getInstance(this).addPENYAKIT(new Penyakit("P01","Penyakit Snot (Infectious Coryza)",
                    "1. Memisahkan burung yang terkena snot dari lovebird lainya\n" +
                    "2. Berikan anti Snot 2 tetes ke mata ataupun ke Airminum, Berikan pada pagi hari, siang hari dan sore hari. Lakukan selama 5 hari berturut-turut maka hasi lovebird akan sembuh dan lincah seperti sediakala\n" +
                    "3. Berikan antibiotik dan Ebot Vit ataupun obat cair khusus penyakit snot\n" +
                    "4. Selalu Menjaga kebersihan kandang"));
            SQLiteHelper.getInstance(this).addPENYAKIT(new Penyakit
                    ("P02","Penyakit Diare",
                    "1. Pindahkan burung agak jauh dari burung lainya (Karantina)\n" +
                    "2. Berikan vitamin untuk menjaga stamina burung, selain menjaga stamina, vitamin tersebut juga berguna untuk mempercepat penyembuhan diare pada lovebird\n" +
                    "3. Kotoran harus cepat dibershikan suaya kotoran tidak menular dan menimbulkan banyak bakteri"));
            SQLiteHelper.getInstance(this).addPENYAKIT(new Penyakit
                    ("P03","Penyakit Nyilet (Prominent Keel)",
                    "1.Lovebird sendirikan di tempat yang tenang dan sejuk\n" +
                    "2.Jangan dimandikan hingga burung sembuh dari nyilet\n" +
                    "3.Berikan obat Anti Nyilet, jika obatnya cair bisa ditaruh di air\n" +
                    "4.Berikan Makanan Extra Fooding kesukaan burung lovebird seperti Jagung dan Kangung\n"));
            SQLiteHelper.getInstance(this).addPENYAKIT(new Penyakit
                    ("P04","Penyakit Egg Binding\n",
                    "1.Berikan asupan nutrisi yang cukup\n" +
                    "2.Menjaga kebersihan kandang\n" +
                    "3.Memberikan ruang yang cukup bersar atau bergerak dan terbang agak bebas mengurangi resiko. karena otot yang terlatih dengan baik akan membantu betina dengan mudah mengeluarkan telur-telurnya \n" +
                    "4.Airminum ganti setiap pagi dan sore hari"));
            SQLiteHelper.getInstance(this).addPENYAKIT(new Penyakit
                    ("P05","Penyakit Berak Kapur (Salmonellosis)",
                    "1.Karantina lovebird supaya tidak menyebar penyakitnya pada burung lainya\n" +
                    "2.Selalu jaga kerbersihan kandang\n" +
                    "3.Berikan vitamin seperti medoxy, koleridin, koleridin, ulfamix, tetacholer, respiratek, neo meditri.Pilihlah obat yang tersedia di toko burung."));
            SQLiteHelper.getInstance(this).addPENYAKIT(new Penyakit
                    ("P06","Penyakit Kutu (Cannary Mite)",
                    "1.Jaga Kebersihan kandang\n" +
                            "2.Mandikkan burung dengan menggunakan shampo burung atau shampo anti kutu\n" +
                            "3.Mandikan burung dengan rebusan air sirih\n" +
                            "4.Jemur burung secara rutin, untuk mengilangkan Penyakit Kutu"));
            SQLiteHelper.getInstance(this).addPENYAKIT(new Penyakit
                    ("P07","Penyakit Ganguan Pernafasan",
                    "1.Jagalah kebersihan kandang\n" +
                            "2.Berikan obat ORNITHO Obat saluran Pernafasan untuk burung kicau Khususnya pada burung lovebird"));
            SQLiteHelper.getInstance(this).addPENYAKIT(new Penyakit
                    ("P08","Penyakit Bubul",
                    "1.Kebersihan sangkar terutama, tempat bertengger burung yang biasa disebut tangkringan\n" +
                            "2.Gunakan salep untuk mengobati kaki burung yang terkena penyakit bubul"));
            SQLiteHelper.getInstance(this).addPENYAKIT(new Penyakit
                    ("P09","Penyakit Tetelo",
                    "1.Menghentikan proses memandikan dan penjemuran pada lovebird.\n" +
                            "2.Tempatkan lovebird ditempat minim suara, dalam keadaan dikerodong(setengah) agar tidak terkena agin secara langsung.\n" +
                            "3.Memberikan makanan bergizi dan vitamin setiap hari serta berikan obat Anti syaraf.\n" ));
            SQLiteHelper.getInstance(this).addPENYAKIT(new Penyakit
                    ("P10","TIDAK ADA RULEBASE",
                    "RULEBASE TIDAK AKTIV"));


            SQLiteHelper.getInstance(this).addGejala(new Gejala("G01","Bagian mata mengeluarkan cairan"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G02","Pembengkakan di daerah bagian mata membengkak"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G03","Mata terlihat sayup"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G04","Kotoran cair"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G05","Kotoran putih seperti kapur"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G06","Tulang dada terlihat  menonjol"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G07","Bagian Perut terlihat membesar"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G08","Bulu terlihat rusak"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G09","Bulu mengembang"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G10","Bulu terlihat adanya kutu"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G11","Hidung burung mengeluarkan cairan"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G12","Bagian Kaki burung membengkak"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G13","Bagian Kuku burung terlihat memanjang"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G14","Bagian kaki burung lemas"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G15","Sering mengeleng-gelengkan kepala seperti stroke"));

            //RuleBase
            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("P01","G01,G02,G03")); //SNOT
            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("P02","G04,G09")); //DIARE
            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("P03","G04,G06")); //NYILET
            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("P04","G03,G07,G08,G14")); //EGG BINDING
            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("P05","G03,G04,G05,G08")); //BERAK KAPUR
            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("P06","G08,G09,G10")); //KUTU
            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("P07","G03,G09,G11")); //GANGUAN PERNAFASAN
            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("P08","G09,G12,G13")); //BUBUL
            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("P09","G03,G09,G14,G15")); //tetelo







            //HASIL KEPUTUSAN
            SQLiteHelper.getInstance(this).addUSER(new User("admin","admin", "hilmi hidayat"));
            SessionHelper.getInstance(this).setAppFirstTime(false);
        }


        //handler
        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {

                                          //setelah loading maka akan langsung berpindah ke home activity
                                          Intent home = new Intent(FlashScreen.this, IndexUtama.class);
                                          startActivity(home);
                                          finish();

                                      }
                                  },

                waktu_flash);

    }
}