package com.example.ikn.brewdogbeer.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ikn.brewdogbeer.R;
import com.example.ikn.brewdogbeer.Util.FavoritesManager;
import com.example.ikn.brewdogbeer.model.BeerModel;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class BeerDetailActivity extends AppCompatActivity {

    public static final String BEER_DATA_KEY = "BEER_DATA_KEY";

    BeerModel selectedBeer;
    ImageView ivBeer;
    TextView tvName, tvAbv, tvIbu, tvSrm, tvBrewed;
    FloatingActionButton fabFavorite;
    Boolean isFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_detail);

        selectedBeer = (BeerModel) getIntent().getSerializableExtra(BEER_DATA_KEY);

        buildActionBar();
        buildViewItems();
        buildContent();

        isFavorite = FavoritesManager.isFavorite(selectedBeer);
        setFabImageResource();
        fabFavorite.setOnClickListener(view -> onFavoriteClick());
    }

    private void buildActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void buildViewItems() {
        ivBeer = findViewById(R.id.iv_beer);
        tvName = findViewById(R.id.tv_name);
        tvAbv = findViewById(R.id.tv_abv);
        tvIbu = findViewById(R.id.tv_ibu);
        tvSrm = findViewById(R.id.tv_srm);
        tvBrewed = findViewById(R.id.tv_brewed);
        fabFavorite = findViewById(R.id.fab_favorite);
    }

    private void buildContent() {
        Picasso.get().load(selectedBeer.getImageUrl()).into(ivBeer);
        tvName.setText(selectedBeer.getName());
        tvAbv.setText(String.format(Locale.ENGLISH, "%.2f", selectedBeer.getAbv()));
        tvIbu.setText(String.format(Locale.ENGLISH,"%.2f", selectedBeer.getIbu()));
        tvSrm.setText(String.format(Locale.ENGLISH,"%.2f", selectedBeer.getSrm()));
        tvBrewed.setText(selectedBeer.getFirstBrewed());
    }

    private void onFavoriteClick() {
        if (isFavorite)
            FavoritesManager.removeOne(selectedBeer);
        else
            FavoritesManager.addOne(selectedBeer);

        isFavorite = !isFavorite;
        setFabImageResource();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setFabImageResource() {
        int drawableId = isFavorite ? R.drawable.ic_star : R.drawable.ic_star_border;
        fabFavorite.setImageResource(drawableId);
        fabFavorite.setTag(drawableId);
    }
}
