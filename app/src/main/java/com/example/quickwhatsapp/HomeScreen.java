package com.example.quickwhatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hbb20.CountryCodePicker;

import java.net.URLEncoder;

public class HomeScreen extends AppCompatActivity {
    CountryCodePicker ccp;
    String Countrycode="91";
    String URL;
    String No;
    String Text;
    EditText editText;
    EditText phone;
    Button send;
    TextView textView;
    int len=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        ccp = findViewById(R.id.ccp);
        editText=(EditText)findViewById(R.id.editTextMessage);
        phone=(EditText)findViewById(R.id.editTextPhone);
        send=findViewById(R.id.button);
        textView=findViewById(R.id.textView5);
        textView.setVisibility(View.INVISIBLE);
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() == 10) {
                    len = s.length();
                    textView.setVisibility(View.INVISIBLE);
                    No = Countrycode + phone.getText().toString();
                } else {
                    len=s.length();
                    textView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
             if(s!=null&&s.length()>1)
                 Text=editText.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    public void onCountryPickerClick(View view) {
        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                //Alert.showMessage(RegistrationActivity.this, ccp.getSelectedCountryCodeWithPlus());
                Countrycode = ccp.getSelectedCountryCode();
            }
        });
    }
    public void sendClicked(View view)
    {
        if(len==10) {
            try {
                String query = URLEncoder.encode(Text, "utf-8");
                URL = "https://wa.me/" + No + "?text=" + query;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                startActivity(intent);
            } catch (Exception e) {
                  e.printStackTrace();
            }
        }
    }

}