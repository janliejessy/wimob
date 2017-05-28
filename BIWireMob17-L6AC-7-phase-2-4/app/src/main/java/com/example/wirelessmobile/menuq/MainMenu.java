package com.example.wirelessmobile.menuq;

import com.example.wirelessmobile.menuq.ui.CartListActivity;
import com.example.wirelessmobile.menuq.ui.ListActivity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;


public class MainMenu extends AppCompatActivity {

    //alert button declaration
    private Button waiterButton;
    private Button billButton;
    private Button orderButton;

    // menu button declaration
    private Button btn_appetizer;
    private Button btn_main_course;
    private Button btn_dessert;
    private Button btn_drink;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //alert button declaration (to its corresponding ID)
        waiterButton = (Button) findViewById(R.id.waiter);
        billButton = (Button) findViewById(R.id.bill);
        orderButton = (Button) findViewById(R.id.order);

        //waiter onclick listener
        waiterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intend = new Intent(MainMenu.this, CartListActivity.class);
                startActivity(intend);
            }
        });

        //bill onclick listener
        billButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(MainMenu.this).create();
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
                AlertDialog alert = new AlertDialog.Builder(MainMenu.this).create();
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

        // onClick listener for appetizer button
        btn_appetizer = (Button) findViewById(R.id.appetizer);
        btn_appetizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListActivity.class);
                intent.putExtra("catID", "1");
                startActivityForResult(intent, 0);
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
}