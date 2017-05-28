package com.example.wirelessmobile.menuq;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.wirelessmobile.menuq.ui.CartListActivity;

/**
 * Created by Claudia on 28/05/2017.
 */

public class ServiceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        TextView title = (TextView) findViewById(R.id.serviceActivityTitle);
        title.setText("Service");

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavbar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch(item.getItemId()) {
                case R.id.bottomNavbar_home:
                    Intent intent0 = new Intent(ServiceActivity.this, HomeActivity.class);
                    startActivity(intent0);
                    break;
                case R.id.bottomNavbar_service:
                    break;
                case R.id.bottomNavbar_cart:
                    Intent intent2 = new Intent(ServiceActivity.this, CartListActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.bottomNavbar_bill:
                    Intent intent3 = new Intent(ServiceActivity.this, BillActivity.class);
                    startActivity(intent3);
                    break;
            }

            return false;
        }
    });
}
}
