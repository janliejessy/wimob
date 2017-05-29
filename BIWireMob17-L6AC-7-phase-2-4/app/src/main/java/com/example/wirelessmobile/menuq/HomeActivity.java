package com.example.wirelessmobile.menuq;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.wirelessmobile.menuq.ui.CartListActivity;
import com.example.wirelessmobile.menuq.ui.ListActivity;


public class HomeActivity extends AppCompatActivity {
    // menu button declaration
    private Button btn_appetizer;
    private Button btn_main_course;
    private Button btn_dessert;
    private Button btn_drink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // BOTTOM NAVBAR
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavbar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.bottomNavbar_home:
                        break;
                    case R.id.bottomNavbar_service:
                        showSimplePopUp();
                        break;
                    case R.id.bottomNavbar_cart:
                        Intent intent2 = new Intent(HomeActivity.this, CartListActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.bottomNavbar_bill:
                        Intent intent3 = new Intent(HomeActivity.this, BillActivity.class);
                        startActivity(intent3);
                        break;
                }

                return false;
            }
        });



        // onClick listener for appetizer button
        btn_appetizer = (Button) findViewById(R.id.appetizer);
        btn_appetizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListActivity.class);
                intent.putExtra("catID", "1");
                startActivity(intent);
            }
        });

        // onClick listener for main course button
        btn_main_course = (Button) findViewById(R.id.main_course);
        btn_main_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListActivity.class);
                intent.putExtra("catID", "2");
                startActivityForResult(intent, 0);
            }
        });

        // onClick listener for dessert button
        btn_dessert = (Button) findViewById(R.id.dessert);
        btn_dessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListActivity.class);
                intent.putExtra("catID", "3");
                startActivityForResult(intent, 0);
            }
        });

        // onClick listener for drink button
        btn_drink = (Button) findViewById(R.id.drink);
        btn_drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListActivity.class);
                intent.putExtra("catID", "4");
                startActivityForResult(intent, 0);
            }
        });
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