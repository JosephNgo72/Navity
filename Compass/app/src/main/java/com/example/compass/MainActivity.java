package com.example.compass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_NUMBER = "com.example.application.example.EXTRA_NUMBER";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        final String password = intent.getStringExtra(CreatePassword.EXTRA_TEXT);



        Button enterButton = (Button) findViewById(R.id.enterButton);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("key");
            //The key argument here must match that used in the other activity
        }


        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText passwordInput = (EditText) findViewById(R.id.passwordInput);
                TextView output = (TextView) findViewById(R.id.output);

                String input = passwordInput.getText().toString();

                if (input.equals(password)){
                    openHome();
                }
                else {
                    output.setText("Your password is incorrect");
                }
            }
        });
    }
    public void openHome(){
        Intent intent = new Intent(this, Home.class);

        Intent intent2 = getIntent();
        final String phoneNumber = intent2.getStringExtra(CreatePassword.EXTRA_NUMBER);

        intent.putExtra(EXTRA_NUMBER, phoneNumber);


        startActivity(intent);
    }
}
