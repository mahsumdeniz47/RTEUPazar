package com.example.rteupazar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class Selling extends AppCompatActivity {
    Bitmap selectedImage;
    ImageView imgSelling;
    File dosya;
    Button btn;
    EditText fiyat, acıklama;
    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    String email;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selling);
        btn = findViewById(R.id.btnPaylasSelling);
        imgSelling = findViewById(R.id.imgResimSelling);
        fiyat = findViewById(R.id.SellingFiyat);
        acıklama = findViewById(R.id.SellingAcıklama);
        Intent intent = getIntent();
        email = intent.getStringExtra("Email");


        spinnerIlceler = findViewById(R.id.spnilceler);
        spinnerMahalle = findViewById(R.id.spnmahalle);
        dataAdapterForIlceler = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ilceler);
        dataAdapterForMerkezMahalle = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, merkezMahalle);
        dataAdapterForArdesenMahalle = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ardesenMahalle);
        dataAdapterForCamlihemsinMahalle= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, camlihemsinMahalle);
        dataAdapterForCayeliMahalle = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cayeliMahalle);
        dataAdapterForDerepazariMahalle = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, derepazariMahalle);
        dataAdapterForFindikliMahalle = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, findikliMahalle);
        dataAdapterForGuneysuMahalle = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, guneysuMahalle);
        dataAdapterForHemsinMahalle = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hemsinMahalle);

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

    }

    public void Save(View view) {
        String price = fiyat.getText().toString();
        String explanation = acıklama.getText().toString();
        String adres = spinnerIlceler.getSelectedItem().toString()+","+spinnerMahalle.getSelectedItem().toString();
        Bitmap resimdosyasi = resimKucultme(selectedImage, 500);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        resimdosyasi.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();


        databaseHelper.UrunPaylas(price,explanation,bytes,email,adres);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("Email",email);
        startActivity(intent);
        finish();

    }


    public Bitmap resimKucultme(Bitmap img, int maksimumsize) {
        //genişlik ve yükseklik
        int width = img.getWidth();
        int height = img.getHeight();
        // oran hesabı bu hesap 1 ve üzeri ise resim yataydır değilse dikeydir
        float oran = (float) width / (float) height;
        if (oran > 1) {
            width = maksimumsize;
            height = (int) (width / oran);
        } else {
            height = maksimumsize;
            width = (int) (height * oran);
        }
        return Bitmap.createScaledBitmap(img, width, height, true);
    }


    public void selectImage(View view) {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        } else {
            Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intentToGallery, 2);

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentToGallery, 2);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            Uri imageData = data.getData();
            try {
                selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageData);
                imgSelling.setImageBitmap(selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private class textWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (s.length() > 0) {

            } else {
                btn.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

}

