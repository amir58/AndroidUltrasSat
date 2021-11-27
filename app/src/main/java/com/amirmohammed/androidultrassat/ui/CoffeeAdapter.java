package com.amirmohammed.androidultrassat.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amirmohammed.androidultrassat.before.ITotalPrice;
import com.amirmohammed.androidultrassat.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.CoffeeHolder> {

    ArrayList<CoffeeModel> coffeeModels;
    ITotalPrice iTotalPrice;

    public CoffeeAdapter(ArrayList<CoffeeModel> coffeeModels, ITotalPrice iTotalPrice) {
        this.coffeeModels = coffeeModels;
        this.iTotalPrice = iTotalPrice;
    }

    @NonNull
    @Override
    public CoffeeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cofee, parent, false);
        CoffeeHolder coffeeHolder = new CoffeeHolder(view);
        return coffeeHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CoffeeHolder holder, int position) {
        CoffeeModel coffeeModel = coffeeModels.get(position);

        holder.textViewName.setText(coffeeModel.name);
        holder.textViewPrice.setText(coffeeModel.price + " EGP");
        holder.textViewQuantity.setText(coffeeModel.quantity + "");

        Glide.with(holder.itemView.getContext()).load(coffeeModel.imageUrl).into(holder.imageViewCoffee);

        holder.imageViewPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coffeeModels.get(position).quantity++;
                notifyItemChanged(position);
                calculateTotalPrice();
            }
        });

        holder.imageViewMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (coffeeModels.get(position).quantity == 0) return;
                coffeeModels.get(position).quantity--;
                notifyDataSetChanged();
                calculateTotalPrice();
            }
        });
    }

    private void calculateTotalPrice(){
        int totalPrice = 0;
        for (int i = 0; i < coffeeModels.size(); i++) {
            totalPrice += coffeeModels.get(i).quantity * coffeeModels.get(i).price;
        }

        iTotalPrice.onPriceChange(totalPrice);
    }

    @Override
    public int getItemCount() {
        return coffeeModels.size();
    }

    class CoffeeHolder extends RecyclerView.ViewHolder {
        ImageView imageViewCoffee, imageViewPlus, imageViewMinus;
        TextView textViewName, textViewPrice, textViewQuantity;

        public CoffeeHolder(@NonNull View itemView) {
            super(itemView);
            imageViewCoffee = itemView.findViewById(R.id.coffee_image);
            imageViewPlus = itemView.findViewById(R.id.coffee_plus);
            imageViewMinus = itemView.findViewById(R.id.coffee_minus);
            textViewName = itemView.findViewById(R.id.coffee_name);
            textViewPrice = itemView.findViewById(R.id.coffee_price);
            textViewQuantity = itemView.findViewById(R.id.coffee_quantity);
        }
    }
}
