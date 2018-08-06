package com.example.ikn.brewdogbeer.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ikn.brewdogbeer.R;
import com.example.ikn.brewdogbeer.model.BeerModel;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class BeersAdapter extends RecyclerView.Adapter<BeersAdapter.ViewHolder> {

    private List<BeerModel> beerList;
    private Listener listener;

    public BeersAdapter(List<BeerModel> beers, Listener listener) {
        this.beerList = beers;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.beer_list_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final BeerModel beer = beerList.get(position);

        Picasso.get().load(beer.getImageUrl()).into(holder.ivBeer);
        holder.tvName.setText(beer.getName());
        holder.tvAbv.setText(String.format(Locale.ENGLISH, "%.2f", beer.getAbv()));
        holder.tvIbu.setText(String.format(Locale.ENGLISH,"%.2f", beer.getIbu()));
        holder.tvSrm.setText(String.format(Locale.ENGLISH,"%.2f", beer.getSrm()));
        holder.tvBrewed.setText(beer.getFirstBrewed());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null)
                listener.onBeerClick(beer);
        });
    }

    @Override
    public int getItemCount() {
        return beerList == null ? 0 : beerList.size();
    }

    public void resetBeersList(List<BeerModel> beers) {
        beerList = beers;

        notifyDataSetChanged();
    }

    public void addMoreBeers(List<BeerModel> beers) {
        int lastIndex = beerList.size();
        beerList.addAll(beers);

        notifyItemRangeInserted(lastIndex, beers.size());
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivBeer;
        TextView tvName, tvAbv, tvIbu, tvSrm, tvBrewed;

        public ViewHolder(View itemView) {
            super(itemView);
            ivBeer = itemView.findViewById(R.id.iv_beer);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAbv = itemView.findViewById(R.id.tv_abv);
            tvIbu = itemView.findViewById(R.id.tv_ibu);
            tvSrm = itemView.findViewById(R.id.tv_srm);
            tvBrewed = itemView.findViewById(R.id.tv_brewed);
        }
    }

    public interface Listener {
        void onBeerClick(BeerModel beer);
    }
}
