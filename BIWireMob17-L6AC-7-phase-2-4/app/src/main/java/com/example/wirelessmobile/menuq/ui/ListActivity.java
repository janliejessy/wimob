package com.example.wirelessmobile.menuq.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

        //create the recyclerview based on the data obtain from db
        //
        listData = (ArrayList) DatabaseLogic.get_data_from_database(catID);
        //
        recView = (RecyclerView)findViewById(R.id.rec_list);
        // LayoutManager: GridLayoutManager or StaggeredGridLayoutManager
        recView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new FoodAdapter(DatabaseLogic.get_data_from_database(catID), this);
        recView.setAdapter(adapter);
        adapter.setItemClickCallback(this);
    }

    //this function is too create a new intent based on what the user clicked
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
}

