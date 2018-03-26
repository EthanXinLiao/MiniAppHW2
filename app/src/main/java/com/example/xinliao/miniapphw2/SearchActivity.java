package com.example.xinliao.miniapphw2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by Xin Liao on 3/25/2018.
 */

public class SearchActivity extends AppCompatActivity {
    private Button mSearchButton;
    private Context mContext;
    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_item_recipe);
        mContext = this;
        mSearchButton = findViewById(R.id.searchbutton);
        spinner1 = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);


        final ArrayList<Recipe> recipeList = Recipe.getRecipesFromFile("recipes.json", this);
        ArrayList<String> dietlabel = new ArrayList<String>();
        ArrayList<String> servinglabel = new ArrayList<String>();
        ArrayList<String> timelabel = new ArrayList<String>();
        dietlabel.add("No restriction");
        servinglabel.add("No restriction");
        timelabel.add("No restriction");
        timelabel.add("30 minutes or less");

        for (int i = 0; i < recipeList.size(); i++){
            String diet =recipeList.get(i).label;
            String serving = recipeList.get(i).serving_label;
            String time =recipeList.get(i).preptime_label;
            if (!dietlabel.contains(diet)){
                dietlabel.add(diet);
            }
            if (!servinglabel.contains(serving)){
                servinglabel.add(serving);
            }
            if (!timelabel.contains(time)){
                timelabel.add(time);
            }
        }
        servinglabel.add("less than 4");

        ArrayAdapter<String> adpter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, dietlabel);
        ArrayAdapter<String> adpter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, servinglabel);
        ArrayAdapter<String> adpter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, timelabel);

        spinner1.setAdapter(adpter1);
        spinner2.setAdapter(adpter2);
        spinner3.setAdapter(adpter3);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //              String selected_diet = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
       spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
       spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
       mSearchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent detailIntent = new Intent(mContext, ResultActivity.class);

                detailIntent.putExtra("diet", spinner1.getSelectedItem().toString());
                detailIntent.putExtra("serving", spinner2.getSelectedItem().toString() );
                detailIntent.putExtra("time", spinner3.getSelectedItem().toString() );

                startActivity(detailIntent);

            }

        });



    }
}
