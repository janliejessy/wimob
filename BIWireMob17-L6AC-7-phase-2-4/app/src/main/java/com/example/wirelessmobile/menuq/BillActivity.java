package com.example.wirelessmobile.menuq;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wirelessmobile.menuq.adapter.BillAdapter;
import com.example.wirelessmobile.menuq.adapter.DerpAdapter;
import com.example.wirelessmobile.menuq.model.CartHelper;
import com.example.wirelessmobile.menuq.model.DatabaseLogic;
import com.example.wirelessmobile.menuq.model.FoodMenu;
import com.example.wirelessmobile.menuq.ui.CartListActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Claudia on 28/05/2017.
 */

public class BillActivity extends AppCompatActivity {

    private RecyclerView recView;
    private BillAdapter adapter;
    private ArrayList listData;
    private Button finishOrderButton;
    private List<FoodMenu> mOrderList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        mOrderList = DatabaseLogic.get_final_order();

        listData = (ArrayList) mOrderList;
        recView = (RecyclerView)findViewById(R.id.rec_list);

        recView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new BillAdapter(mOrderList, this);
        recView.setAdapter(adapter);

        // Finish order button
        finishOrderButton = (Button)findViewById(R.id.btn_finish_order);

        finishOrderButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(BillActivity.this).create();
                alert.setTitle("Finish your order?");
                alert.setButton(AlertDialog.BUTTON_POSITIVE, "YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseLogic.delete_order_from_database();
                        finish();
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
}
