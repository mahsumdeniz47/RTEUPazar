package com.example.rteupazar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db = new DatabaseHelper(this);
    ArrayAdapter<RteuPazar> arrayAdapter;

    private String[] ilceler = {"Rize(Merkez)","Ardeşen", "Çamlıhemşin", "Çayeli", "Derepazarı", "Fındıklı", "Güneysu", "Hemşin"};
    private String[] merkezMahalle = {"ALİPAŞA MAHALLESİ", "ASMALIK MAHALLESİ", "ATMEYDANI MAHALLESİ", "BAĞDATLI MAHALLESİ", "BALSU MAHALLESİ", "BOĞAZ MAHALLESİ", "BOZKALE MAHALLESİ", "CAMİÖNÜ MAHALLESİ"};
    private String[] ardesenMahalle = {"ADA MAHALLESİ", "BAHAR MAHALLESİ", "BARIŞ MAHALLESİ", "BAŞ MAHALLESİ", "CAMİ MAHALLESİ", "CUMHURİYET MAHALLESİ", "ÇİFTEKAVAK MAHALLESİ", "DENİZ MAHALLESİ"};
    private String[] camlihemsinMahalle = {"AŞAĞIÇAMLICA MAHALLESİ", "AŞAĞIŞİMŞİRLİ MAHALLESİ", "KADIKÖY MAHALLESİ", "KAPLICA MAHALLESİ", "KAVAK MAHALLESİ", "KONAKLAR MAHALLESİ", "MERKEZ MAHALLESİ", "SIRT MAHALLESİ", "YAĞMURLU MAHALLESİ", "YUKARIÇAMLICA MAHALLESİ"};
    private String[] cayeliMahalle = {"ADALAR MAHALLESİ", "ALOĞLU MAHALLESİ", "ALTINTAŞ MAHALLESİ", "ARKADERE MAHALLESİ", "BÜYÜKCAFERPAŞA MAHALLESİ", "BÜYÜKTAŞHANE MAHALLESİ", "ÇAMLICA MAHALLESİ", "ÇATAKLIHOCA MAHALLESİ"};
    private String[] derepazariMahalle = {"ÇALIŞKANLAR MAHALLESİ", "ERİKLİMANI MAHALLESİ", "FIÇICILAR MAHALLESİ", "MERKEZ MAHALLESİ", "SARIYER MAHALLESİ", "SUBAŞI MAHALLESİ", "TERSANE MAHALLESİ"};
    private String[] findikliMahalle = {"AKSU MAHALLESİ", "HÜRRİYET MAHALLESİ", "ILICA MAHALLESİ", "LİMAN MAHALLESİ", "MERKEZ MAHALLESİ", "SAHİL MAHALLESİ", "TATLISU MAHALLESİ", "YENİ MAHALLESİ"};
    private String[] guneysuMahalle = {"ADACAMİ MAHALLESİ", "AŞAĞI KİREMİT MAHALLESİ", "BAŞARAN MAHALLESİ", "BİRLİK MAHALLESİ", "ESKİCAMİ MAHALLESİ", "KÜÇÜKCAMİİ MAHALLESİ", "MERKEZ MAHALLESİ", "ULUCAMİİ MAHALLESİ"};
    private String[] hemsinMahalle = {"BAHAR MAHALLESİ", "MUTLU MAHALLESİ", "ORTAKÖY MAHALLESİ", "YENİKÖY MAHALLESİ"};

    Spinner spinnerIlceler;
    Spinner spinnerMahalle;
    ArrayAdapter<String> dataAdapterForMerkezMahalle;
    ArrayAdapter<String> dataAdapterForArdesenMahalle;
    ArrayAdapter<String> dataAdapterForCamlihemsinMahalle;
    ArrayAdapter<String> dataAdapterForCayeliMahalle;
    ArrayAdapter<String> dataAdapterForDerepazariMahalle;
    ArrayAdapter<String> dataAdapterForFindikliMahalle;
    ArrayAdapter<String> dataAdapterForGuneysuMahalle;
    ArrayAdapter<String> dataAdapterForHemsinMahalle;


    ArrayAdapter<String> dataAdapterForIlceler;

    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView img = findViewById(R.id.imgShare);
        RecyclerView recyclerView = findViewById(R.id.mainRecylerView);

        spinnerIlceler = findViewById(R.id.mainSpnIlceler);
        spinnerMahalle = findViewById(R.id.mainSpnMahalle);
        dataAdapterForIlceler = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ilceler);
        dataAdapterForMerkezMahalle = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, merkezMahalle);
        dataAdapterForArdesenMahalle = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ardesenMahalle);
        dataAdapterForCamlihemsinMahalle= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, camlihemsinMahalle);
        dataAdapterForCayeliMahalle = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cayeliMahalle);
        dataAdapterForDerepazariMahalle = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, derepazariMahalle);
        dataAdapterForFindikliMahalle = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, findikliMahalle);
        dataAdapterForGuneysuMahalle = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, guneysuMahalle);
        dataAdapterForHemsinMahalle = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, hemsinMahalle);
        spinnerIlceler.setAdapter(dataAdapterForIlceler);
        spinnerIlceler.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        spinnerMahalle.setAdapter(dataAdapterForMerkezMahalle);
                        break;
                    case 1:
                        spinnerMahalle.setAdapter(dataAdapterForArdesenMahalle);
                        break;
                    case 2:
                        spinnerMahalle.setAdapter(dataAdapterForCamlihemsinMahalle);
                        break;
                    case 3:
                        spinnerMahalle.setAdapter(dataAdapterForCayeliMahalle);
                        break;
                    case 4:
                        spinnerMahalle.setAdapter(dataAdapterForDerepazariMahalle);
                        break;
                    case 5:
                        spinnerMahalle.setAdapter(dataAdapterForFindikliMahalle);
                        break;
                    case 6:
                        spinnerMahalle.setAdapter(dataAdapterForGuneysuMahalle);
                        break;
                    case 7:
                        spinnerMahalle.setAdapter(dataAdapterForHemsinMahalle);
                        break;
                    default:
                        spinnerMahalle.setAdapter(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Intent intent = getIntent();
        email = intent.getStringExtra("Email");

        ArrayList<RteuPazar> arrayList = db.Listele();
        RecyclerAdapter adapter = new RecyclerAdapter(arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        findViewById(R.id.mainImgFilitre).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.mainLayout).setVisibility(View.GONE);
                findViewById(R.id.filtreLayout).setVisibility(View.VISIBLE);
                            }
        });
        findViewById(R.id.imgfiltreClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.mainLayout).setVisibility(View.VISIBLE);
                findViewById(R.id.filtreLayout).setVisibility(View.GONE);
            }
        });
        findViewById(R.id.btnfiltre).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adres = spinnerIlceler.getSelectedItem().toString()+","+spinnerMahalle.getSelectedItem().toString();
                DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
                ArrayList<RteuPazar> rteuPazars = databaseHelper.secVeri(adres);
                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(rteuPazars);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(recyclerAdapter);
                findViewById(R.id.mainLayout).setVisibility(View.VISIBLE);
                findViewById(R.id.filtreLayout).setVisibility(View.GONE);

            }
        });



        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Selling.class);
                intent.putExtra("Email",email);
                startActivity(intent);
                finish();
            }
        });

    }

}