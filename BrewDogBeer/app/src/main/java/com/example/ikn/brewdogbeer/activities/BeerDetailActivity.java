package com.example.ikn.brewdogbeer.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ikn.brewdogbeer.R;
import com.example.ikn.brewdogbeer.model.BeerModel;

public class BeerDetailActivity extends AppCompatActivity {

    public static final String BEER_DATA_KEY = "BEER_DATA_KEY";

    BeerModel beerModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_detail);

        beerModel = (BeerModel) getIntent().getSerializableExtra(BEER_DATA_KEY);
    }
}
