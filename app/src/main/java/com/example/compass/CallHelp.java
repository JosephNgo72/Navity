package com.example.compass;

import androidx.appcompat.app.AppCompatActivity;
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

public class CallHelp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_help);

        Intent intent = getIntent();
        final String phoneNumber = intent.getStringExtra(MainActivity.EXTRA_NUMBER);

        Button emHot = (Button) findViewById(R.id.emHot);
        emHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri number = Uri.parse("tel:8007997233");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                System.out.println("hello this is working");
                startActivity(callIntent);
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

        Button emCallButton = (Button) findViewById(R.id.emTextButton);
        emCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri number = Uri.parse("tel:" + phoneNumber);
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                System.out.println("hello this is working");
                startActivity(callIntent);
            }
        });

        Button emTextButton = (Button) findViewById(R.id.emCallButton);
        emTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSend();
            }
        });
    }
    public void onSend(){
        String emMessage = "Navity: I am in an urgent situation and need assistance immediately.";

        Intent intent = getIntent();
        final String phoneNumber = intent.getStringExtra(MainActivity.EXTRA_NUMBER);

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
