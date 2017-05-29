package com.example.wirelessmobile.menuq.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wirelessmobile.menuq.BottomNavigationViewHelper;
import com.example.wirelessmobile.menuq.R;
import com.example.wirelessmobile.menuq.model.CartHelper;
import com.example.wirelessmobile.menuq.model.DatabaseLogic;
import com.example.wirelessmobile.menuq.model.FoodMenu;

import java.util.List;

import io.realm.Realm;

public class DetailActivity extends AppCompatActivity {
    //bundles declaration
    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_NAME = "EXTRA_NAME";
    private static final String EXTRA_DESC = "EXTRA_DESC";
    private static final String EXTRA_ICON = "EXTRA_ICON";
    private static final String EXTRA_PRICE = "EXTRA_PRICE";
    private static final String EXTRA_QUANTITY = "EXTRA_QUANTITY";

    private Button addToCartButton;
    private Button btnAdd;
    private Button btnMin;
    private static Realm realm = Realm.getDefaultInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final List<FoodMenu> cart = CartHelper.getCart();


        //take the argument passed from ListActivity.java
        //this is to retrieve the appropriate data to be display
        Bundle extras= getIntent().getBundleExtra(BUNDLE_EXTRAS);
        final FoodMenu item = DatabaseLogic.get_data_from_database_from_name(extras.getString("EXTRA_NAME"));
        ((ImageView)findViewById(R.id.im_item_icon)).setImageResource(item.getDrawable());
        ((TextView)findViewById(R.id.item_txtName)).setText(item.getTitle());
        ((TextView)findViewById(R.id.item_txtDesc)).setText(item.getDesc());
        ((TextView)findViewById(R.id.item_txtPrice)).setText("$" + item.getPrice());

        ((TextView)findViewById(R.id.item_txtQuantity)).setText("" + item.getQty());
        final TextView qtyTxtView  = (TextView) findViewById(R.id.item_txtQuantity);

        addToCartButton = (Button) findViewById(R.id.btn_add_to_cart);
        addToCartButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                TextView qtyTxt  = (TextView) findViewById(R.id.item_txtQuantity);
                // add this foodMenu
                int qtyInText = Integer.parseInt(qtyTxt.getText().toString());
                Log.i("QTY >>", ""+qtyInText);
                if(qtyInText > 0) {
                    cart.add(item);
                    AlertDialog alert = new AlertDialog.Builder(DetailActivity.this).create();
                    alert.setTitle("Note");
                    alert.setMessage("Product(s) added to cart!");
                    alert.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //
                        }
                    });
                    alert.show();
                    setAddButtonToFalse();
                }

            }
        });
        if (cart.contains(item)){
            setAddButtonToFalse();
        }

        // add quantity button
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //add foodMenu ini
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        item.setQty(item.getQty()+1);
                    }
                });

                qtyTxtView.setText(""+item.getQty());
            }
        });

        // decrease quantity button
        btnMin = (Button) findViewById(R.id.btn_min);
        btnMin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //add this feeodMenu
                if(item.getQty() !=0) {
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            item.setQty(item.getQty() - 1);
                        }
                    });

                    qtyTxtView.setText("" + item.getQty());
                }
            }
        });

        // BOTTOM NAVBAR
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavbar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        // do something when menu navigation is clicked
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.bottomNavbar_home:
                        Intent intent0 = new Intent(DetailActivity.this, HomeActivity.class);
                        startActivity(intent0);
                        break;
                    case R.id.bottomNavbar_service:
                        showSimplePopUp();
                        break;
                    case R.id.bottomNavbar_cart:
                        Intent intent2 = new Intent(DetailActivity.this, CartListActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.bottomNavbar_bill:
                        Intent intent3 = new Intent(DetailActivity.this, BillActivity.class);
                        startActivity(intent3);
                        break;
                }

                return false;
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

    private void setAddButtonToFalse(){
        addToCartButton.setEnabled(false);
        addToCartButton.setText("Item in Cart");
    }
}
