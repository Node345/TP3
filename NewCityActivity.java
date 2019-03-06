package com.example.uapv1603141.tp3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class NewCityActivity extends AppCompatActivity {

    private EditText textName, textCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_city);

        textName = (EditText) findViewById(R.id.editNewName);
        textCountry = (EditText) findViewById(R.id.editNewCountry);

        final Button but = (Button) findViewById(R.id.button);

        but.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(textName.getText().length()>0 && textCountry.getText().length()>0){


                    City c = new City(textName.getText().toString(),textCountry.getText().toString());
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("NewVille",c);
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();
                }
                // TODO
            }
        });
    }


}
