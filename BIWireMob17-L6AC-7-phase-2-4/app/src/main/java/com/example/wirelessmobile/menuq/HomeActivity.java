package com.example.wirelessmobile.menuq;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.wirelessmobile.menuq.ui.CartListActivity;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavbar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.bottomNavbar_home:
                        break;
                    case R.id.bottomNavbar_service:
                        Intent intent1 = new Intent(HomeActivity.this, ServiceActivity.class);
                        startActivity(intent1);
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
    }
}