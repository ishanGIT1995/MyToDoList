package com.example.mytodolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Categories extends AppCompatActivity implements View.OnClickListener {

    CardView studies ,entertainment,financial,grocery,work,other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        getSupportActionBar().hide();

        studies=(CardView) findViewById(R.id.studyCard);
        entertainment =(CardView) findViewById(R.id.entertaimentCard);
        financial=(CardView) findViewById(R.id.financialCard);
        grocery=(CardView) findViewById(R.id.groceryCard);
        work=(CardView) findViewById(R.id.workCard);
        other=(CardView) findViewById(R.id.otherCard);

        studies.setOnClickListener(this);
        entertainment.setOnClickListener(this);
        financial.setOnClickListener(this);
        grocery.setOnClickListener(this);
        work.setOnClickListener(this);
        other.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {

            case R.id.studyCard:
                i = new Intent(this,Studies.class);
                startActivity(i);
                break;

            case R.id.entertaimentCard:
                i = new Intent(this,Entertainment.class);
                startActivity(i);
                break;

            case R.id.financialCard:
                i = new Intent(this,Financial.class);
                startActivity(i);
                break;

            case R.id.groceryCard:
                i = new Intent(this,Grocery.class);
                startActivity(i);
                break;

            case R.id.workCard:
                i = new Intent(this,Work.class);
                startActivity(i);
                break;

            case R.id.otherCard:
                i = new Intent(this,Other.class);
                startActivity(i);
                break;

        }



    }
}