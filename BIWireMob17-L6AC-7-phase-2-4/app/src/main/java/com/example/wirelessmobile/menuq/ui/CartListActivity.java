package com.example.wirelessmobile.menuq.ui;

/**
 * Created by jessyjanlie on 5/28/17.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wirelessmobile.menuq.BillActivity;
import com.example.wirelessmobile.menuq.BottomNavigationViewHelper;
import com.example.wirelessmobile.menuq.HomeActivity;
import com.example.wirelessmobile.menuq.R;
import com.example.wirelessmobile.menuq.adapter.DerpAdapter;
import com.example.wirelessmobile.menuq.model.CartHelper;
import com.example.wirelessmobile.menuq.model.DatabaseLogic;
import com.example.wirelessmobile.menuq.model.FoodMenu;

import java.util.ArrayList;
import java.util.List;

public class CartListActivity extends AppCompatActivity {

    private RecyclerView recView;
    private DerpAdapter adapter;
    private ArrayList listData;
    private List<FoodMenu> mCartList;
    private Button orderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_list_activity);
        orderButton  = (Button)findViewById(R.id.btn_order);

        mCartList = CartHelper.getCart();

        listData = (ArrayList) CartHelper.getCart();

        recView = (RecyclerView)findViewById(R.id.rec_list);

        recView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new DerpAdapter(mCartList, this);
        recView.setAdapter(adapter);


        orderButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //ganti masuk realm
                if(!CartHelper.getCart().isEmpty()){
                    DatabaseLogic.add_cart_to_order_list(mCartList);
                    CartHelper.deleteCart();
                    Log.i("after rmv >>", ":");
                    DatabaseLogic.return_all_order();
                }
                if(CartHelper.getCart().isEmpty()){
                    orderButton.setEnabled(false);
                }

            }
        });

        // cart activity
        TextView title = (TextView) findViewById(R.id.cartActivityTitle);
        title.setText("Your Cart");

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavbar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.bottomNavbar_home:
                        Intent intent0 = new Intent(CartListActivity.this, HomeActivity.class);
                        startActivity(intent0);
                        break;
                    case R.id.bottomNavbar_service:
                        showSimplePopUp();
                        break;
                    case R.id.bottomNavbar_cart:
                        break;
                    case R.id.bottomNavbar_bill:
                        Intent intent3 = new Intent(CartListActivity.this, BillActivity.class);
                        startActivity(intent3);
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

}