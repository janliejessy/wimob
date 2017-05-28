package com.example.wirelessmobile.menuq;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Appetizers extends AppCompatActivity {
    private List<Food> appetizers = new ArrayList<Food>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appetizers);

        populateAppetizersList();
        populateListView();
        createClickCallback();
    }

    private void populateAppetizersList() {
        appetizers.add(new Food("Nachos", "Crisp and savoury!", R.drawable.nachos, 39999, 2));
        appetizers.add(new Food("Vanilla Cake", "Excellent quality ingredients in a cake", R.drawable.cake, 24999, 0));
        appetizers.add(new Food("Spring Rolls", "Roll'em!", R.drawable.rolls, 25999, 0));
        appetizers.add(new Food("Fresh Salad", "Healthy vegetables and its friends", R.drawable.salad, 27999, 1));
        appetizers.add(new Food("Chicken Skewers", "One word. Exquisite", R.drawable.skewers, 29999, 0));

    }

    private void populateListView() {
        ArrayAdapter<Food> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.appetizersListView);
        list.setAdapter(adapter);
    }

    private void createClickCallback() {
        ListView list = (ListView) findViewById(R.id.appetizersListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                Food clickedFood = appetizers.get(position);
                // String message = "You clicked position " + position + "with the quantity of " + clickedFood.getQuantity();
                String message = "You added " + clickedFood.getQuantity() + " " + clickedFood.getName() + " to cart." ;

                Toast.makeText(Appetizers.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    private class MyListAdapter extends ArrayAdapter<Food> {
        public MyListAdapter() {
            super(Appetizers.this, R.layout.item_view, appetizers);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Make sure we have a view to work with (can be null)
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);
            }

            // find the food to work with
            Food currentFood = appetizers.get(position);

            // fill the view
            ImageView imageView = (ImageView)itemView.findViewById(R.id.item_icon);
            imageView.setImageResource(currentFood.getIconID());

            // Name
            TextView nameText = (TextView) itemView.findViewById(R.id.item_txtName);
            nameText.setText(currentFood.getName());

            // Description
            TextView descText = (TextView) itemView.findViewById(R.id.item_txtDesc);
            descText.setText(currentFood.getDesc());

            // Price
            TextView priceText = (TextView) itemView.findViewById(R.id.item_txtPrice);
            priceText.setText("" + currentFood.getPrice());

            // Quantity
            TextView quantityText = (TextView) itemView.findViewById(R.id.item_txtQuantity);
            quantityText.setText("" + currentFood.getQuantity());

            return itemView;
        }
    }



}
