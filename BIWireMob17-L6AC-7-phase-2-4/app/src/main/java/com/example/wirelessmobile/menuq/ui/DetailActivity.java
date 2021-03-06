package com.example.wirelessmobile.menuq.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wirelessmobile.menuq.R;
import com.example.wirelessmobile.menuq.model.CartHelper;
import com.example.wirelessmobile.menuq.model.DatabaseLogic;
import com.example.wirelessmobile.menuq.model.FoodMenu;

import org.w3c.dom.Text;

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

    //alert button declaration
    private Button waiterButton;
    private Button billButton;
    private Button orderButton;
    private Button addToCartButton;
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

        //alert button declaration (to its corresponding ID)
        waiterButton = (Button)findViewById(R.id.waiter);
        billButton   = (Button)findViewById(R.id.bill);
        orderButton  = (Button)findViewById(R.id.order);

        TextView qtyTxtView  = (TextView) findViewById(R.id.item_txtQuantity);

        final int qtys = Integer.parseInt(qtyTxtView.getText().toString());

        addToCartButton = (Button) findViewById(R.id.btn_add_to_cart);
        addToCartButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //add foodMenu ini
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                    item.setQty(qtys);
                    }
                });
                cart.add(item);
                AlertDialog alert = new AlertDialog.Builder(DetailActivity.this).create();
                alert.setTitle("Added to Cart");
                alert.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //
                    }
                });
                alert.show();
                setAddButtonToFalse();

            }
        });
        if (cart.contains(item)){
            setAddButtonToFalse();
        }
        //waiter onclick listener
        waiterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(DetailActivity.this).create();
                alert.setTitle("Please wait");
                alert.setMessage("Our waiter is coming to your table");
                alert.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //
                    }
                });
                alert.show();
            }
        });

        //bill onclick listener
        billButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(DetailActivity.this).create();
                alert.setTitle("Bill");
                alert.setMessage("Your total bill is : $$$. Please come to cashier 1 to pay.");
                alert.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //
                    }
                });
                alert.show();
            }
        });

        //order onclick listener
        orderButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(DetailActivity.this).create();
                alert.setTitle("Order");
                alert.setMessage("You have ordered: 1 apple juice. ");
                alert.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //
                    }
                });
                alert.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //
                    }
                });
                alert.show();
            }
        });
    }

    private void setAddButtonToFalse(){
        addToCartButton.setEnabled(false);
        addToCartButton.setText("Item in Cart");
    }
}
