package com.example.compass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreatePassword extends AppCompatActivity {
    public static final String EXTRA_TEXT = "com.example.application.example.EXTRA_TEXT";
    public static final String EXTRA_NUMBER = "com.example.application.example.EXTRA_NUMBER";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);

        Button createButton = (Button) findViewById(R.id.createButton);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });


    }

    public void openActivity2(){
        EditText newPassword = (EditText) findViewById(R.id.newPassword);
        String password = newPassword.getText().toString();

        EditText emContact = (EditText) findViewById(R.id.emContact);
        String phoneNumber = emContact.getText().toString();


        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra(EXTRA_TEXT, password);
        intent.putExtra(EXTRA_NUMBER, phoneNumber);
        startActivity(intent);
    }
}
