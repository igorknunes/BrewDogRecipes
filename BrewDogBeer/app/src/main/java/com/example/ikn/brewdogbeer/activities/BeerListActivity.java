package com.example.ikn.brewdogbeer.activities;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.ikn.brewdogbeer.R;
import com.example.ikn.brewdogbeer.adapters.BeersAdapter;
import com.example.ikn.brewdogbeer.api.BrewDogService;
import com.example.ikn.brewdogbeer.model.BeerModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeerListActivity extends AppCompatActivity implements Callback<List<BeerModel>>, BeersAdapter.Listener  {

    BeersAdapter adapter;
    RecyclerView rvBeers;
    String searchableQuery;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_list);

        rvBeers = findViewById(R.id.rv_beers);
        Intent intent = getIntent();

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            searchableQuery = intent.getStringExtra(SearchManager.QUERY);
        }

        fetchData(1, searchableQuery);
    }

    @Override
    public void onBeerClick(BeerModel beer) {
        Intent intent = new Intent(this, BeerDetailActivity.class);
        intent.putExtra(BeerDetailActivity.BEER_DATA_KEY, beer);
        startActivity(intent);
    }

    @Override
    public void onResponse(Call<List<BeerModel>> call, Response<List<BeerModel>> response) {
        if (response.body() != null) {
            List<BeerModel> cards = response.body();
            displayBeers(cards);
        }
    }

    private void displayBeers (List<BeerModel> beers){
        if (adapter == null) {
            adapter = new BeersAdapter(beers, this);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            rvBeers.setLayoutManager(layoutManager);

//            if (addInfiniteScrollListener) {
//                InfiniteScrollListener listener = new InfiniteScrollListener(layoutManager);
//                listener.setOnLoadMoreListener(this::onLoadMore);
//
//                rvCards.addOnScrollListener(listener);
//            }

            rvBeers.setAdapter(adapter);
        } else {
            if (searchableQuery == null || searchableQuery.isEmpty())
                adapter.addMoreBeers(beers);
            else
                adapter.resetBeersList(beers);
        }
    }

    public void fetchData(int page, String searchQuery) {
        if (searchQuery == null || searchQuery.isEmpty() )
            BrewDogService.getInstance().getBeers(page, BrewDogService.PAGE_SIZE).enqueue(this);
        else{
            BrewDogService.getInstance().getBeersByName(page, BrewDogService.PAGE_SIZE, searchQuery).enqueue(this);
        }
    }

    @Override
    public void onFailure(Call<List<BeerModel>> call, Throwable t) {
        Log.e("Beer List", "DEU RUIM -->> " + t.getLocalizedMessage());
    }
}
