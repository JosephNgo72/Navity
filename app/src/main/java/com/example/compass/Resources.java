package com.example.compass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class Resources extends AppCompatActivity {


    public void physicalButton(View physical){

        Intent viewIntent =
                new Intent("android.intent.action.VIEW",
                        Uri.parse("https://www.helpguide.org/articles/abuse/domestic-violence-and-abuse.htm"));
        startActivity(viewIntent);

    }

    public void mentalButton(View mental){

        Intent viewIntent =
                new Intent("android.intent.action.VIEW",
                        Uri.parse("https://www.thehotline.org/"));
        startActivity(viewIntent);

    }

    public void sexualButton(View sexual){

        Intent viewIntent =
                new Intent("android.intent.action.VIEW",
                        Uri.parse("https://www.rainn.org/"));
        startActivity(viewIntent);

    }

    public void lgbtqButton(View lgbtq){

        Intent viewIntent =
                new Intent("android.intent.action.VIEW",
                        Uri.parse("https://www.loveisrespect.org/is-this-abuse/abusive-lgbtq-relationships//"));
        startActivity(viewIntent);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
    }
}
