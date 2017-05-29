package com.example.wirelessmobile.menuq.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.example.wirelessmobile.menuq.BottomNavigationViewHelper;
import com.example.wirelessmobile.menuq.R;
import com.example.wirelessmobile.menuq.adapter.FoodAdapter;
import com.example.wirelessmobile.menuq.model.DatabaseLogic;
import com.example.wirelessmobile.menuq.model.FoodMenu;

import java.io.IOException;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements FoodAdapter.itemClickCallback{
    //declaration
    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_NAME = "EXTRA_NAME";
    private static final String EXTRA_DESC = "EXTRA_DESC";
    private static final String EXTRA_ICON = "EXTRA_ICON";
    private static final String EXTRA_PRICE = "EXTRA_PRICE";

    private RecyclerView recView;
    private FoodAdapter adapter;
    private ArrayList listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //to make sure that this code only run once
        //this is to initialize database, if there isn't any data in it
        SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstRun = wmbPreference.getBoolean("FIRSTRUN", true);
        if (isFirstRun)
        {
            // Code to run once
            try {
                DatabaseLogic.openTheDatabase();
            } catch (IOException e) {
                e.printStackTrace();
            }
            SharedPreferences.Editor editor = wmbPreference.edit();
            editor.putBoolean("FIRSTRUN", false);
            editor.commit();
        }

        //take the argument passed from MainMenu.java
        //this is to retrieve the appropriate data according to its category
        String value = "0";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("catID");
            Log.i("value of cat>>", value);
        }
        int catID = Integer.parseInt(value);
        Log.i("CATID >>", String.valueOf(catID));

        //create the recyclerview based on the data obtained from db
        listData = (ArrayList) DatabaseLogic.get_data_from_database(catID);

        recView = (RecyclerView)findViewById(R.id.rec_list);

        recView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new FoodAdapter(DatabaseLogic.get_data_from_database(catID), this);
        recView.setAdapter(adapter);
        adapter.setItemClickCallback(this);

        // BOTTOM NAVBAR
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavbar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        // do something when menu navigation is clicked
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.bottomNavbar_home:
                        Intent intent0 = new Intent(ListActivity.this, HomeActivity.class);
                        startActivity(intent0);
                        break;
                    case R.id.bottomNavbar_service:
                        showSimplePopUp();
                        break;
                    case R.id.bottomNavbar_cart:
                        Intent intent2 = new Intent(ListActivity.this, CartListActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.bottomNavbar_bill:
                        Intent intent3 = new Intent(ListActivity.this, BillActivity.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });
    }

    //this function is to create a new intent based on what the user clicked
    @Override
    public void onItemClick(int p) {
        FoodMenu item = (FoodMenu) listData.get(p);
        Intent i = new Intent(this, DetailActivity.class);

        Bundle extras = new Bundle();
        Log.i("BUNDLE NAME >>", "" +item.getTitle() );
        extras.putString(EXTRA_NAME, item.getTitle());
        Log.i("BUNDLE ID >>", "" +item.getId() );
        extras.putString(EXTRA_DESC, item.getDesc());
        extras.putString(EXTRA_PRICE, String.valueOf(item.getPrice()));
        extras.putInt(EXTRA_ICON, item.getDrawable());
        i.putExtra(BUNDLE_EXTRAS, extras);

        startActivityForResult(i, 0);
    }

    // popup for service
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

