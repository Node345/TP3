package com.example.uapv1603141.tp3;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ListView listview = (ListView) findViewById(R.id.LV);
        final FloatingActionButton button = (FloatingActionButton) findViewById(R.id.AjoutVille);

        final WeatherDbHelper dbHelper=new WeatherDbHelper(this);
        dbHelper.populate();
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,dbHelper.fetchAllCities(),new String[] {WeatherDbHelper.COLUMN_CITY_NAME,WeatherDbHelper.COLUMN_COUNTRY}, new int[] {android.R.id.text1,android.R.id.text2});


        listview.setAdapter(cursorAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                City city = null;
                Intent intent = new Intent(MainActivity.this, CityActivity.class);

                Cursor itemClicked = (Cursor) parent.getItemAtPosition(position);
                intent.putExtra("Ville", dbHelper.cursorToCity(itemClicked));
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, NewCityActivity.class);
                startActivityForResult(intent,1);
            }
            protected void onActivityResult(int requestCode, int resultCode, Intent data){
                City city = (City) getIntent().getExtras().get("NewVille");
                dbHelper.addCity(city);
            }
        });

    }
}
