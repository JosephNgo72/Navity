package com.example.compass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.SQLOutput;
import java.util.ArrayList;


public class Assistance extends AppCompatActivity {
    public static final String EXTRA_NUMBER = "com.example.application.example.EXTRA_NUMBER";
    class Questioner
    {
        private String contactName;
        /**
        private int pa = 0;
        private int ma = 0;
        private int sa = 0;
        private int lgbt = 0;
         */
        private int a = 0;

        private int num = 0;
        private int lgbtNum = 0;
        private String questions[] = {
                "Do you often feel unsafe at home?",
                "Do you feel anxious or nervous when you are around this person?", /** THIS PERSON??? */
                "Do you watch what you are doing in order to avoid making this person angry or upset?",
                "Do you feel obligated or coerced into having sex with this person?",
                "Are you afraid of voicing a different opinion than this person?",
                "Does this person criticize you or embarrass you in front of others?",
                "Does this person check up on what you have been doing, and not believe your answers?",
                "Does this person tell you that they will stop beating you when you start behaving yourself?",
                "Have you stopped seeing your friends or family because of this person’s behavior?",
                "Does this person’s behavior make you feel as if you are wrong?",
                "Does this person threaten to harm you?",
                "Do you try to please this person rather than yourself in order to avoid being hurt?",
                "Does this person keep you from going out or doing things that you want to do?",
                "Do you feel that nothing you do is ever good enough for this person?",
                "Does this person say that if you try to leave them, you will never see your children again?",
                "Does this person say that if you try to leave, they will kill themself?",
                "Is there always an excuse for this person’s behavior? ('The alcohol or drugs made me do it! My job is too stressful! If dinner was on time I wouldn’t have hit you!')",
                "Do you lie to your family, friends and doctor about your bruises, cuts, and/or scratches?"
        };

        private String lgbtQuestions[] = {
                "Do you feel unsafe because of your identity?",
                "Does this person ridicule, mock, or insult you because of your identity?",
                "Does this person physically hurt you because of your identity?",
                "Does this person make you feel ashamed because of your identity?",
                "Does this person try to change your identity? (conversion therapy, dead-naming, claiming you are a different identity, etc)",
                "Does this person say that your identity is not real, made up, or wrong?"
        };

        /**
         * Constructor for objects of class InitialQuestions
         */
        public Questioner(String name)
        {
            this.contactName = name;
        }


        public String initialQuestions(int id)
        {
            String response = "";
            switch(id)
            {
                case 1:
                    response = "More information about your situation will help us better help you. Do you have time to answer some screening questions?";
                    break;
                case 3:
                    response = "Call The National Domestic Violence Hotline?";
                    break;
                case 5:
                    response = "";
                    openResources();

                    break;
            }
            return response;
        }

        public String askScreeningQuestions()
        {
            String question = "";

            question = questions[num];
            num++;
            return question;
        }
        /*
        public String askLGBTQuestions()
        {
            String question = lgbtQuestions[lgbtNum];
            lgbtNum++;
            return question;
        }*/

        public String diagnose()
        {
            String diagnosis = "Based on your answers, we think you may be suffering from ";
            if (this.a > 7)
                diagnosis += "abuse.";
            else
                diagnosis = diagnosis = "Based on your answers, we do not think you are suffering from abuse. However, abuse comes in many forms. Please visit this page for the signs of abuse: thehotline.org/is-this-abuse/";
            //ArrayList<String> arr = new ArrayList<String>();
            /**
            if(this.pa > 3)
                arr.add("physical abuse");
            if(this.ma > 5)
                arr.add("mental/emotional abuse");
            if(this.sa > 0)
                arr.add("sexual abuse");
            if(this.lgbt > 3)
                arr.add("LGBT abuse");
             */
        /**
            for(int i = 0; i < arr.size(); i++)
            {
                diagnosis += arr.get(i);
                if(i+1 == arr.size()-1)
                    diagnosis += ", and ";
                else if(i+1 < arr.size() - 1)
                    diagnosis += ", ";
            }*/

            return diagnosis;
        }
/**
        public void addPA() { this.pa++; }
        public void addMA() { this.ma++; }
        public void addSA() { this.sa++; }
        public void addLGBT() { this.lgbt++; }

        public int getPA() { return this.pa; }
        public int getMA() { return this.ma; }
        public int getSA() { return this.sa; }
        public int getLGBT() { return this.lgbt; }
 */
        public void addA() {this.a++;}
        public int getA() {return this.a;}
        public int getNum(){return this.num;}
        public void addNum() {this.num++;}

    }
    Questioner q = new Questioner("Joseph");
    boolean first = true;
    int counter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistance);


        TextView display = (TextView) findViewById(R.id.display);

        display.setText("Do you need immediate assistance?");


        Button yes = (Button) findViewById(R.id.yes);
        Button no = (Button) findViewById(R.id.no);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(first) {
                    openCallHelp();
                }
                else{
                    TextView display = (TextView) findViewById(R.id.display);
                    q.addA();
                    if(q.getNum()<18)
                            display.setText(q.askScreeningQuestions());
                    else if (q.getNum()==18)
                    {   display.setText(q.diagnose());
                        q.addNum();}
                    else
                        openResources();


                }
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView display = (TextView) findViewById(R.id.display);
                Questioner q = new Questioner("Joseph");
                first = false;
                if(q.getNum()<18)
                    display.setText(q.initialQuestions(counter));
                else if (q.getNum()==18)
                { display.setText(q.diagnose());
                    q.addNum();}
                else
                    openResources();
                counter+=2;
            }
        });
    }

    public void openCallHelp(){

        Intent intent = new Intent(this, CallHelp.class);

        Intent intent2 = getIntent();
        final String phoneNumber = intent2.getStringExtra(CreatePassword.EXTRA_NUMBER);

        intent.putExtra(EXTRA_NUMBER, phoneNumber);
        startActivity(intent);
    }
    public void openResources(){
        Intent intent = new Intent(this, Resources.class);
        startActivity(intent);
    }
}
