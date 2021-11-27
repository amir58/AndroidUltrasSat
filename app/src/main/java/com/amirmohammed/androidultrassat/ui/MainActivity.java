package com.amirmohammed.androidultrassat.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.TextView;

import com.amirmohammed.androidultrassat.before.ITotalPrice;
import com.amirmohammed.androidultrassat.R;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<CoffeeModel> coffeeProducts = new ArrayList<>();
    RecyclerView recyclerView;
    TextView textViewTotalPrice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareData();

        textViewTotalPrice = findViewById(R.id.tv_total_price);
        recyclerView = findViewById(R.id.recycler_view);

        CoffeeAdapter coffeeAdapter = new CoffeeAdapter(coffeeProducts, iTotalPrice);

        recyclerView.setAdapter(coffeeAdapter);

    }

    ITotalPrice iTotalPrice = new ITotalPrice() {
        @Override
        public void onPriceChange(int totalPrice) {
            textViewTotalPrice.setText("Total Price " + totalPrice + " EGP");
        }
    };

    private void prepareData() {
        CoffeeModel coffeeModel1 = new CoffeeModel("Americano", 25, "https://www.mcdonalds.eg/Cms_Data/Contents/En/Media/images/700x474-Americano.png");
        coffeeProducts.add(coffeeModel1);

        CoffeeModel coffeeModel2 = new CoffeeModel("Juicy Forest Tea", 30, "https://www.mcdonalds.eg/Cms_Data/Contents/En/Media/images/Juicy-Forest-700.png");
        coffeeProducts.add(coffeeModel2);

        CoffeeModel coffeeModel3 = new CoffeeModel("Espresso", 20, "https://www.mcdonalds.eg/Cms_Data/Contents/En/Media/images/Espresso-R-700x474.png");
        coffeeProducts.add(coffeeModel3);


        CoffeeModel coffeeModel4 = new CoffeeModel("Cappuccino", 20, "https://www.mcdonalds.eg/Cms_Data/Contents/En/Media/images/700x474-Cappucino.png");
        coffeeProducts.add(coffeeModel4);


        CoffeeModel coffeeModel5 = new CoffeeModel("Latte", 25, "https://www.mcdonalds.eg/Cms_Data/Contents/En/Media/images/Menu/McCafe/Lrg-Latte-700x474.png");
        coffeeProducts.add(coffeeModel5);


        CoffeeModel coffeeModel6 = new CoffeeModel("Flat White", 35, "https://www.mcdonalds.eg/Cms_Data/Contents/En/Media/images/700x474-FL.png");
        coffeeProducts.add(coffeeModel6);

        CoffeeModel coffeeModel7 = new CoffeeModel("Hot Chocolate", 30, "https://www.mcdonalds.eg/Cms_Data/Contents/En/Media/images/700x474-Hot-Choco.png");
        coffeeProducts.add(coffeeModel7);


        CoffeeModel coffeeModel8 = new CoffeeModel("Hot Tea", 15, "https://www.mcdonalds.eg/Cms_Data/Contents/En/Media/images/Menu/McCafe/Lrg-Tea-700x474.png");
        coffeeProducts.add(coffeeModel8);


        CoffeeModel coffeeModel9 = new CoffeeModel("Mocha Frappe", 20, "https://www.mcdonalds.eg/Cms_Data/Contents/En/Media/images/700x474-MF.png");
        coffeeProducts.add(coffeeModel9);


        CoffeeModel coffeeModel10 = new CoffeeModel("Peach Strawberry Smoothie", 25, "https://www.mcdonalds.eg/Cms_Data/Contents/En/Media/images/700x474-PST.png");
        coffeeProducts.add(coffeeModel10);

    }

}


