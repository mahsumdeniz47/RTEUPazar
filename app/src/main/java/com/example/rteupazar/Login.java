package com.example.rteupazar;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Login extends AppCompatActivity {
    SQLiteDatabase database;
    EditText email, pass;
    Button button;
    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button =  findViewById(R.id.btnGirisYap);
         email = findViewById(R.id.loginEmail);
         pass = findViewById(R.id.loginPassword);


       textWatcher tx = new textWatcher();
        email.addTextChangedListener(tx);
        pass.addTextChangedListener(tx);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean kontrol =  databaseHelper.UyeGiris(email.getText().toString(),pass.getText().toString());
               if (kontrol = true){
                   Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                   intent.putExtra("Email",email.getText().toString());
                   startActivity(intent);
                   finish();
               }else {
                   Toast.makeText(getApplicationContext(),"Email veya şifre yanlış girdiniz lütfen tekrar deneyiniz ",Toast.LENGTH_SHORT).show();
               }

            }
        });
        findViewById(R.id.tvKaydol).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(),Register.class);
                startActivity(intent2);
                finish();
            }
        });
    }


    private class textWatcher implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length()>0){
                if (email.getText().toString().length()>15 && pass.getText().toString().length()>5){
                    button.setEnabled(true);
                    button.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.beyaz));
                    button.setBackgroundResource(R.drawable.register_bottom_aktif);
                }
                else {
                    button.setEnabled(false);
                    button.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.sonukmavi));
                    button.setBackgroundResource(R.drawable.register_bottom);
                }

            }else {
                button.setEnabled(false);
                button.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.sonukmavi));
                button.setBackgroundResource(R.drawable.register_bottom);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

}