package com.example.wirelessmobile.menuq.ui;

/**
 * Created by jessyjanlie on 5/28/17.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.wirelessmobile.menuq.R;
import com.example.wirelessmobile.menuq.adapter.DerpAdapter;
import com.example.wirelessmobile.menuq.model.CartHelper;
import com.example.wirelessmobile.menuq.model.DatabaseLogic;
import com.example.wirelessmobile.menuq.model.DerpData;
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

            }
        });
    }

}