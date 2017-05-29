package com.example.wirelessmobile.menuq;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wirelessmobile.menuq.model.FoodMenu;
import com.example.wirelessmobile.menuq.ui.CartListActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Claudia on 28/05/2017.
 */

public class BillActivity extends AppCompatActivity {
    // sebenernya ini ga prlu kyknya?????
//    private List<Food> appetizers = new ArrayList<Food>();

    private Button finishOrderButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        // Finish order button
        finishOrderButton = (Button)findViewById(R.id.btn_finish_order);

        finishOrderButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(BillActivity.this).create();
                alert.setTitle("Finish your order?");
                alert.setButton(AlertDialog.BUTTON_POSITIVE, "YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(BillActivity.this, FinishOrderActivity.class);
                        startActivity(intent);
                    }
                });
                alert.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alert.show();
            }
        });

//        populateAppetizersList(); -> all food in db
//        populateListView();
//        createClickCallback();

        // BILL ACTIVITY
        TextView title = (TextView) findViewById(R.id.billActivityTitle);
        title.setText("Your Bill");

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavbar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.bottomNavbar_home:
                        Intent intent0 = new Intent(BillActivity.this, HomeActivity.class);
                        startActivity(intent0);
                        break;
                    case R.id.bottomNavbar_service:
                        showSimplePopUp();
                        break;
                    case R.id.bottomNavbar_cart:
                        Intent intent2 = new Intent(BillActivity.this, CartListActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.bottomNavbar_bill:
                        break;
                }

                return false;
            }
        });
    }

    private void showSimplePopUp() {
        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Need help?");
        helpBuilder.setMessage("Please raise your hand should you need our assistance.");
        helpBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                    }
                });
        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }
//    // masukin food ke appetizers
//    private void populateAppetizersList() {
//        appetizers.add(new Food("Nachos", "Crisp and savoury!", R.drawable.nachos, 39999, 2));
//        appetizers.add(new Food("Vanilla Cake", "Excellent quality ingredients in a cake", R.drawable.cake, 24999, 0));
//        appetizers.add(new Food("Spring Rolls", "Roll'em!", R.drawable.rolls, 25999, 0));
//        appetizers.add(new Food("Fresh Salad", "Healthy vegetables and its friends", R.drawable.salad, 27999, 1));
//        appetizers.add(new Food("Chicken Skewers", "One word. Exquisite", R.drawable.skewers, 29999, 0));
//    }
//
//    // buat apa ya ini
//    private void populateListView() {
//        ArrayAdapter<FoodMenu> adapter = new MyListAdapter();
//        ListView list = (ListView) findViewById(R.id.billListView);
//        list.setAdapter(adapter);
//    }
//
//    private class MyListAdapter extends ArrayAdapter<FoodMenu> {
//        public MyListAdapter() {
//            super(BillActivity.this, R.layout.bill_item_view, appetizers);
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            // Make sure we have a view to work with (can be null)
//            View itemView = convertView;
//            if (itemView == null) {
//                itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);
//            }
//
//            // find the food to work with
//            FoodMenu currentFood = appetizers.get(position);
//
//            // Name
//            TextView nameText = (TextView) itemView.findViewById(R.id.item_txtName);
//            nameText.setText(currentFood.getTitle());
//
//            // Price
//            TextView priceText = (TextView) itemView.findViewById(R.id.item_txtPrice);
//            priceText.setText("" + currentFood.getPrice());
//
//             harusnya ada nih quantity
//            // Quantity
//            TextView quantityText = (TextView) itemView.findViewById(R.id.item_txtQuantity);
//            quantityText.setText("" + currentFood.getQuantity());
//
//            return itemView;
//        }
//    }
}
