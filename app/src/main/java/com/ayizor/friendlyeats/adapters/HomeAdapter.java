package com.ayizor.friendlyeats.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ayizor.friendlyeats.R;
import com.ayizor.friendlyeats.models.Restaurant;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    ArrayList<Restaurant> restaurantList;
    Context context;

    public HomeAdapter(ArrayList<Restaurant> restaurantList, Context context) {
        this.restaurantList = restaurantList;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);
        holder.name.setText(restaurant.getName());
        holder.distance.setText(restaurant.getDistance() + " km");
        holder.address.setText(restaurant.getAddress());
        Glide.with(holder.image.getContext()).load(restaurant.getImage()).into(holder.image);
        if (restaurant.getRating() == 5) {
            holder.rating.setImageResource(R.drawable.five_star);
        }
        if (restaurant.getRating() == 4.5) {
            holder.rating.setImageResource(R.drawable.four_half_star);
        }
        if (restaurant.getRating() == 4) {
            holder.rating.setImageResource(R.drawable.four_star);
        }
        if (restaurant.getRating() == 3.5) {
            holder.rating.setImageResource(R.drawable.three_half_star);
        }
        if (restaurant.getRating() == 3) {
            holder.rating.setImageResource(R.drawable.three_star);
        }
        if (restaurant.getRating() == 2.5) {
            holder.rating.setImageResource(R.drawable.two_half_star);
        }
        if (restaurant.getRating() == 2 || restaurant.getRating() < 2) {
            holder.rating.setImageResource(R.drawable.two_star);
        }
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        ImageView rating, image;
        TextView name, address, distance;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            rating = itemView.findViewById(R.id.iv_restaurant_rating);
            image = itemView.findViewById(R.id.iv_restaurant_image);
            name = itemView.findViewById(R.id.tv_restaurant_name);
            address = itemView.findViewById(R.id.tv_restaurant_address);
            distance = itemView.findViewById(R.id.tv_restaurant_distance);
        }
    }
    public int getLastItemId() {
        return restaurantList.get(restaurantList.size() - 1).getId();
    }
}
