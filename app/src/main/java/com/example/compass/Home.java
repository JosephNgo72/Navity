package com.example.compass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    public static final String EXTRA_NUMBER = "com.example.application.example.EXTRA_NUMBER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button assistance = (Button) findViewById(R.id.assistanceButton);
        assistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAssistance();
            }
        });

        Button resources = (Button) findViewById(R.id.resourcesButton);
        resources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openResources();
            }
        });

        Button logo = (Button) findViewById(R.id.nextButton);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCompass();
            }
        });

        Button about = (Button) findViewById(R.id.aboutButton);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAbout();
            }
        });

        Button policeButton = (Button) findViewById(R.id.policeButton);
        policeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri number = Uri.parse("tel:911");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                System.out.println("hello this is working");
                startActivity(callIntent);
            }
        });

        Intent intent = getIntent();
        final String phoneNumber = intent.getStringExtra(MainActivity.EXTRA_NUMBER);
        Button emCallButton = (Button) findViewById(R.id.emCallButton);
        emCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri number = Uri.parse("tel:" + phoneNumber);
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                System.out.println("hello this is working");
                startActivity(callIntent);
            }
        });

        Button emTextButton = (Button) findViewById(R.id.emTextButton);
        emTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSend();
            }
        });

        final int SEND_SMS_PERMISSION_REQUEST_CODE =1;
        emTextButton.setEnabled(false);
        if (checkPermission(Manifest.permission.SEND_SMS)) {
            emTextButton.setEnabled(true);
        }
        else{
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
        }
    }



    public void openAssistance(){
        Intent intent = new Intent(this, Assistance.class);

        Intent intent2 = getIntent();
        final String phoneNumber = intent2.getStringExtra(Assistance.EXTRA_NUMBER);

        intent.putExtra(EXTRA_NUMBER, phoneNumber);

        startActivity(intent);
    }

    public void openResources(){
        Intent intent = new Intent(this, Resources.class);
        startActivity(intent);
    }

    public void openAbout(){
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    public void openCompass(){
        Intent intent = new Intent(this, Compass.class);
        startActivity(intent);
    }

    public void onSend(){
        String emMessage = "Navity: I am in an urgent situation and need assistance immediately.";
        Intent intent = getIntent();
        final String phoneNumber = intent.getStringExtra(CreatePassword.EXTRA_NUMBER);

        if (phoneNumber == null || phoneNumber.length() == 0 ||
                emMessage == null || emMessage.length() == 0){
            return;
        }

        if(checkPermission(Manifest.permission.SEND_SMS)){
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, emMessage, null, null);
            Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }
    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }
}
