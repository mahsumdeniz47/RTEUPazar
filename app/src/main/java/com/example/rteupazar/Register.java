package com.example.rteupazar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class Register extends AppCompatActivity {
    EditText name, surname, email, password;
    Button btnKaydol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.editTextName);
        surname = findViewById(R.id.editTextSurname);
        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.editTextPassword);
       TextView password2 = findViewById(R.id.editTextPassword2);
        btnKaydol = findViewById(R.id.btnKaydol);

        textWatcher textWatcher = new textWatcher();
        name.addTextChangedListener(textWatcher);
        surname.addTextChangedListener(textWatcher);
        email.addTextChangedListener(textWatcher);
        password.addTextChangedListener(textWatcher);
        btnKaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailKontrol = email.getText().toString().substring(email.getText().toString().lastIndexOf("@"));
            if (emailKontrol.equals("@erdogan.edu.tr")){
                if (password.getText().toString().equals(password2.getText().toString())){
                   DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
                    boolean kontrol = databaseHelper.kayitkontrol(email.getText().toString());
                    Log.e("kontrol",kontrol+"");
                   if (kontrol == true){
                       databaseHelper.UyeKayit(name.getText().toString(),surname.getText().toString(),email.getText().toString(),password.getText().toString());
                       Intent intent = new Intent(getApplicationContext(),Login.class);
                       startActivity(intent);
                   }else {
                       Toast.makeText(getApplicationContext(),"Hesap kullanımda",Toast.LENGTH_LONG).show();
                   }

                }
                else {
                    Toast.makeText(getApplicationContext(),"Lütfen şifrenizi doğrulayınız",Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(getApplicationContext(),"Lütfen geçerli bir kurumsal mail giriniz",Toast.LENGTH_LONG).show();
            }




            }
        });



    }

    private   class textWatcher implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length()>0){
                if (name.length()>2 && surname.length()>2 && password.length()>5 && email.getText().toString().length()>15){
                    btnKaydol.setEnabled(true);
                    btnKaydol.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.beyaz));
                    btnKaydol.setBackgroundResource(R.drawable.register_bottom_aktif);
                }
                else {
                    btnKaydol.setEnabled(false);
                    btnKaydol.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.sonukmavi));
                    btnKaydol.setBackgroundResource(R.drawable.register_bottom);
                }

            }else {
                btnKaydol.setEnabled(false);
                btnKaydol.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.sonukmavi));
                btnKaydol.setBackgroundResource(R.drawable.register_bottom);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}